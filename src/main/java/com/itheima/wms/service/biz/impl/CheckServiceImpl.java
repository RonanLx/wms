package com.itheima.wms.service.biz.impl;

import com.github.pagehelper.Page;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.constants.CheckListStatus;
import com.itheima.wms.common.constants.CheckStatus;
import com.itheima.wms.common.constants.CheckTaskStatus;
import com.itheima.wms.mapper.base.AreaMapper;
import com.itheima.wms.mapper.base.OwnerLocationMapper;
import com.itheima.wms.mapper.base.WarehouseMapper;
import com.itheima.wms.mapper.biz.*;
import com.itheima.wms.model.dto.biz.CheckDTO;
import com.itheima.wms.model.dto.biz.IncreaseDecreaseQueryDTO;
import com.itheima.wms.model.entity.base.Area;
import com.itheima.wms.model.entity.base.Warehouse;
import com.itheima.wms.model.entity.biz.*;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.CheckDetailVO;
import com.itheima.wms.model.vo.biz.IncreaseDecreaseDetailVO;
import com.itheima.wms.service.base.CodeFactoryService;
import com.itheima.wms.service.biz.CheckListService;
import com.itheima.wms.service.biz.CheckService;
import com.itheima.wms.service.biz.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 盘点单
 * </p>
 */
@Slf4j
@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    private CheckListService checkListService;
    @Autowired
    private CheckTaskMapper checkTaskMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private IncreaseDecreaseMapper increaseDecreaseMapper;
    @Autowired
    private OwnerLocationMapper ownerLocationMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StockService stockService;
    @Autowired
    private CheckListMapper checkListMapper;
    @Autowired
    private CodeFactoryService codeFactoryService;


    @Override
    public void save(Check entity) {
        /*if (null != entity.getOwnerId()) {
            OwnerLocation ownerLocation = ownerLocationMapper.getByOwnerId(entity.getOwnerId());
            entity.setWarehouseId(ownerLocation.getWarehouseId());
        }*/
        checkMapper.save(entity);
    }

    @Override
    public PageBean<Check> page(Map data) {
        Page<Check> page = checkMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    @Transactional
    public BatchVO task(List<Long> ids) {
        BatchVO.BatchVOBuilder batchVOBuilder = BatchVO.builder();
        ids.forEach(id -> {
            Check check = checkMapper.getById(id);
            if (null == check) {
                log.info("id:{} 未找到", id);
                return;
            }

            // 校验清单是否有未填写的
            List<CheckList> checkListEntities = checkListMapper.getByMasterId(id);
            checkListEntities = checkListEntities.stream().filter(item -> (null != item.getStockNum() && 0 != item.getStockNum())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(checkListEntities)) {
                log.info("id:{} 清单未填写完成", id);
                batchVOBuilder.error(check.getCode() + "未完成盘点单清单");
                return;
            }

            if (CheckStatus.NEW != check.getStatus()) {
                log.info("id:{} 状态不符 status:{}", id, check.getStatus());
                batchVOBuilder.error(check.getCode() + "状态不合法");
                return;
            }

            int cou = checkTaskMapper.countByMasterId(check.getId());
            if (cou > 1) {
                log.info("id:{} 已生成盘点任务", id);
                batchVOBuilder.error(check.getCode() + "已生成盘点任务");
                return;
            }
            // 修改盘点单状态 为 一盘中
            check.setStatus(CheckStatus.CHECK);
            checkMapper.updateById(check);

            // 查询清单统计总库存
            /*queryWrapper.select("IFNULL(sum(stock_num),0) as totalNum")*/
            Integer totalNum = checkListMapper.sumStockNumByMasterId(check.getId());

            // 生成盘点任务
            CheckTask checkTask = new CheckTask();
            BeanUtils.copyProperties(check, checkTask);
            checkTask.setPlanTaskTime(check.getPlanCheckTime());
            checkTask.setMasterId(check.getId());
            checkTask.setCode(codeFactoryService.getNextCodeByType("PDRW"));
            checkTask.setCheckCode(check.getCode());
            checkTask.setStockTotal(totalNum);  //库存总数
            checkTask.setCheckNum(CheckListStatus.CHECK);  //一盘
            checkTask.setStatus(CheckTaskStatus.NEW);  //初始状态
            checkTask.setId(null);
            checkTaskMapper.insert(checkTask);

            batchVOBuilder.result(checkTask.getCode());
        });
        return batchVOBuilder.build();
    }

    @Override
    public CheckDetailVO getDetail(Long id) {
        CheckDetailVO checkDetailVO = new CheckDetailVO();
        Check check = checkMapper.getById(id);
        BeanUtils.copyProperties(check, checkDetailVO);

        // 状态变更时间
        List<String> timeArray = List.of();
        checkDetailVO.setTimeArray(timeArray);
        // 仓库名称
        Warehouse warehouse = warehouseMapper.getById(check.getWarehouseId());
        checkDetailVO.setWarehouseName(warehouse.getName());
        // 库区名称
        Area area = areaMapper.getById(check.getAreaId());
        checkDetailVO.setAreaName(area == null ? "" : area.getName());

        // 盘点任务
        List<CheckTask> checkTasks = checkTaskMapper.getByMasterId(check.getId());
        for (CheckTask checkTask : checkTasks) {
            List<IncreaseDecreaseDetailVO> increaseDecreaseEntities = increaseDecreaseMapper.pageDetail(IncreaseDecreaseQueryDTO.builder().taskId(checkTask.getMasterId()).build());
            if (1 == checkTask.getCheckNum()) {
                // 一盘
                if (!CollectionUtils.isEmpty(increaseDecreaseEntities)) {
                    checkDetailVO.setId1Money(increaseDecreaseEntities.stream().filter(item -> item.getIdMoney() != null).map(IncreaseDecrease::getIdMoney).reduce(BigDecimal.ZERO, BigDecimal::add));
                    checkDetailVO.setId1List(increaseDecreaseEntities);
                }
                checkDetailVO.setCheckTask1(checkTask);
            } else {
                // 复盘
                if (!CollectionUtils.isEmpty(increaseDecreaseEntities)) {
                    checkDetailVO.setId2Money(increaseDecreaseEntities.stream().filter(item -> item.getIdMoney() != null).map(IncreaseDecrease::getIdMoney).reduce(BigDecimal.ZERO, BigDecimal::add));
                    checkDetailVO.setId2List(increaseDecreaseEntities);
                }
                checkDetailVO.setCheckTask2(checkTask);
            }
        }

        // 盘点清单 总计
        Map map = Map.of("master_id", check.getId());
        if (null == checkDetailVO.getCheckTask2()) {
            map.put("status", CheckListStatus.CHECK);
        } else {
            map.put("status", CheckListStatus.RECHECK);
        }
        List<CheckList> checkListEntities = checkListMapper.getByMap(map);
        checkDetailVO.setLocationTotal(checkListEntities.size());
        checkDetailVO.setGoodsTotal(checkListEntities.stream().mapToInt(CheckList::getStockNum).sum());


        return checkDetailVO;
    }

    @Override
    public PageBean<CheckDetailVO> pageDetail(Map data) {
        Page<CheckDetailVO> page = checkMapper.selectCheckDetail(data);
        return PageBean.builder(page);
    }

    @Override
    @Transactional
    public Long generatorCheck(Area area) {
        Check check = new Check();
        check.setStatus(CheckStatus.NEW);
        check.setAreaId(null);
        check.setCode(codeFactoryService.getNextCodeByType("PD"));
        check.setDimension("KW");
        check.setOwnerId(null);
        check.setPlanCheckTime(LocalDateTime.now().withMinute(0).withSecond(0).minusSeconds(0));
        check.setReason("GH");
        check.setType("JHPD");
        check.setWarehouseId(area.getWarehouseId());
        check.setAreaId(area.getId());
        this.save(check);

        List<Stock> stockEntities = stockMapper.getByAreaId(area.getId());
        log.info("自动生成盘点单 全部库存:{}", stockEntities);
        // 遍历生成清单
        stockEntities.forEach(stockEntity -> {
            CheckList checkList = new CheckList();
            checkList.setMasterId(check.getId());
            checkList.setStatus(CheckListStatus.CHECK);
            checkList.setStockId(stockEntity.getId());
            checkList.setStockNum(stockEntity.getTotal());
            checkListMapper.save(checkList);
            // 盘点中需要冻结库存
            stockEntity.setFrozen(stockEntity.getTotal());
            stockEntity.setFree(0);
            stockService.updateById(stockEntity);
        });

        return check.getId();
    }

    @Override
    @Transactional
    public void cancel(CheckDTO data) {
        Check check = checkMapper.getById(data.getId());
        if (null == check) {
            throw new RuntimeException("盘点单未找到");
        }
        if (check.getStatus() != CheckStatus.NEW) {
            throw new RuntimeException("只能取消新建的盘点单");
        }
        check.setStatus(CheckStatus.CANCEL);
        checkMapper.updateById(check);

        Map map = Map.of("master_id", check.getId(), "status", CheckListStatus.CHECK); // 查询一盘即可
        List<CheckList> checkList = checkListMapper.getByMap(map);
        checkListService.cancelBatch(checkList);
    }

    @Override
    public List<Check> list(Map data) {
        return checkMapper.page(data);
    }

    @Override
    public Check getById(Long id) {
        return checkMapper.getById(id);
    }

    @Override
    public void updateById(CheckDTO data) {
        checkMapper.updateById(data);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> {
            checkMapper.removeById(id);
        });
    }
}

