/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.biz.impl;

import com.github.pagehelper.Page;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.constants.*;
import com.itheima.wms.mapper.base.GoodsMapper;
import com.itheima.wms.mapper.biz.*;
import com.itheima.wms.model.dto.biz.CheckTaskDTO;
import com.itheima.wms.model.entity.base.Goods;
import com.itheima.wms.model.entity.biz.*;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.CheckTaskDetailVO;
import com.itheima.wms.service.base.CodeFactoryService;
import com.itheima.wms.service.biz.CheckTaskService;
import com.itheima.wms.service.biz.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 盘点任务
 * </p>
 */
@Slf4j
@Service
public class CheckTaskServiceImpl implements CheckTaskService {

    @Autowired
    private CheckTaskMapper checkTaskMapper;
    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    private CheckListMapper checkListMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StockService stockService;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private IncreaseDecreaseMapper increaseDecreaseMapper;
    @Autowired
    private StockRecordMapper stockRecordMapper;
    @Autowired
    private CodeFactoryService codeFactoryService;


    @Override
    @Transactional
    public BatchVO complete(List<Long> ids) {
        BatchVO.BatchVOBuilder batchVOBuilder = BatchVO.builder();

        ids.forEach(id -> {
            CheckTask checkTask = checkTaskMapper.getById(id);
            if (null == checkTask) {
                log.info("id:{} 未找到", id);
                return;
            }

            // 校验清单是否有未填写的
            List<CheckList> checkListEntities = checkListMapper.getByMasterId(checkTask.getMasterId());
            checkListEntities = checkListEntities.stream().filter(item -> null != item.getCheckNum()).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(checkListEntities)) {
                log.info("id:{} 清单未填写完成", id);
                batchVOBuilder.error(checkTask.getCode() + "未完成盘点单录入");
                return;
            }

            if (CheckTaskStatus.CHECK != checkTask.getStatus()) {
                log.info("id:{} 状态不符 status:{}", id, checkTask.getStatus());
                batchVOBuilder.error(checkTask.getCode() + "状态不合法");
                return;
            }

            // 修改盘点任务 为 盘点已完成
            checkTask.setStatus(CheckTaskStatus.CHECK_COMPLETED);
            checkTaskMapper.updateById(checkTask);

            Check check = checkMapper.getById(checkTask.getMasterId());
            // 修改盘点单状态为 一盘完成
            if (1 == checkTask.getCheckNum()) {
                // 一盘
                check.setStatus(CheckStatus.CHECK_COMPLETED);
                // 只保留一盘清单
                checkListEntities = checkListEntities.stream().filter(item -> item.getStatus() == 1).collect(Collectors.toList());
            } else {
                // 复盘
                check.setStatus(CheckStatus.RECHECK_COMPLETED);
                // 只保留复盘清单
                checkListEntities = checkListEntities.stream().filter(item -> item.getStatus() == 2).collect(Collectors.toList());
            }
            checkMapper.updateById(check);

            // 解冻
            unfreezeStock(checkListEntities);

            batchVOBuilder.result(check.getCode());
        });

        return batchVOBuilder.build();
    }

    /**
     * 解冻库存
     *
     * @param checkListEntities
     */
    private void unfreezeStock(List<CheckList> checkListEntities) {
        checkListEntities.forEach(checkListEntity -> {
            Long stockId = checkListEntity.getStockId();
            Integer stockNum = checkListEntity.getStockNum();
            Stock stock = stockMapper.getById(stockId);
            log.info("盘点任务解冻 checkListEntity:{} stockEntity:{}", checkListEntity, stock);
            Integer original = stock.getTotal();
            Integer originalFree = stock.getFree();
            stockService.unfreeze(stockId, stockNum);

            StockRecord stockRecord = new StockRecord();
            stockRecord.setSourceId(checkListEntity.getId());
            stockRecord.setLocationId(stock.getLocationId());
            stockRecord.setType(StockRecordType.PD);// 类型出库
            stockRecord.setOriginal(original);
            stockRecord.setOriginalFree(originalFree);
            stockRecord.setWay(StockRecordWay.UNFREEZE);
            stockRecord.setAlteration(stockNum);
            stockRecord.setResult(original);
            stockRecord.setResultFree(originalFree + stockNum);
            stockRecordMapper.save(stockRecord);
        });
    }

    @Override
    public BatchVO task(List<Long> ids) {
        BatchVO.BatchVOBuilder batchVOBuilder = BatchVO.builder();
        ids.forEach(id -> {
            try {
                String code = task(id);
                batchVOBuilder.result(code);
            } catch (RuntimeException e) {
                log.warn("id:{} 复盘任务生成失败", id, e);
                batchVOBuilder.error(e.getMessage());
            } catch (Exception e) {
                log.error("id:{} 复盘任务生成异常", id, e);
                batchVOBuilder.error("位置异常");
            }

        });
        return batchVOBuilder.build();
    }

    @Override
    @Transactional
    public String task(Long id) {
        CheckTask checkTask = checkTaskMapper.getById(id);

        if (null == checkTask) {
            log.info("id:{} 未找到", id);
        }

        if (checkTask.getCheckNum() == CheckTaskCheckNum.RECHECK) {
            log.info("id:{} 复盘任务无法生成复盘", id);
            throw new RuntimeException(checkTask.getCode() + "复盘任务无法生成复盘");
        }

        if (checkTask.getStatus() == CheckTaskStatus.RECHECK) {
            log.info("id:{} 已生成复盘任务", id);
            throw new RuntimeException(checkTask.getCode() + "已生成复盘任务");
        }

        if (checkTask.getStatus() != CheckTaskStatus.CHECK_COMPLETED) {
            log.info("id:{} 一盘未完成", id);
            throw new RuntimeException(checkTask.getCode() + "未完成一盘");
        }

        try {
            checkTask.setStatus(CheckTaskStatus.RECHECK);
            checkTaskMapper.updateById(checkTask);

            Check check = checkMapper.getById(checkTask.getMasterId());
            check.setStatus(CheckStatus.RECHECK);
            checkMapper.updateById(check);

            // 复制原有盘点任务
            checkTask.setId(null);
            checkTask.setCode(codeFactoryService.getNextCodeByType("PDRW"));
            checkTask.setCheckNum(CheckListStatus.RECHECK); // 设置盘点次数为 2
            checkTask.setStatus(CheckTaskStatus.NEW);
            checkTask.setCheckTotal(null);
            checkTask.setPersonName(null);
            checkTask.setDifferenceNum(null);
            checkTask.setCreateTime(LocalDateTime.now());
            checkTask.setUpdateTime(LocalDateTime.now());
            checkTaskMapper.insert(checkTask);

            // 复制盘点单清单
            List<CheckList> checkListEntities = checkListMapper.getByMasterId(checkTask.getMasterId());
            checkListEntities.forEach(item -> {
                item.setId(null);
                item.setStatus(CheckListStatus.RECHECK);
                item.setDifferenceNum(null);
                item.setCheckNum(null);
                checkListMapper.save(item);
            });
        } catch (RuntimeException e) {
            throw new RuntimeException(checkTask.getCode() + e.getMessage());
        }
        return checkTask.getCode();
    }

    @Override
    @Transactional
    public BatchVO increaseDecrease(List<Long> ids) {
        BatchVO.BatchVOBuilder batchVOBuilder = BatchVO.builder();

        ids.forEach(id -> {
            CheckTask checkTask = checkTaskMapper.getById(id);

            if (null == checkTask) {
                log.info("id:{} 未找到", id);
                return;
            }

            if (checkTask.getDifferenceNum() == null || checkTask.getDifferenceNum() == 0) {
                batchVOBuilder.error(checkTask.getCode() + "没有损益");
                return;
            }

            // 差异数大于0 查询明细 生成损益单
            Map map = Map.of("master_id", checkTask.getMasterId(),
                    "status", checkTask.getCheckNum(),
                    "difference_num", 0);
            List<CheckList> checkListEntities = checkListMapper.selectByMap(map);
            List<IncreaseDecrease> increaseDecreaseList = new ArrayList<>();
            for (CheckList checkList : checkListEntities) {
                Stock stock = stockMapper.getById(checkList.getStockId());
                // 查询货品信息
                Goods goods = goodsMapper.getById(stock.getGoodsId());

                IncreaseDecrease increaseDecrease = new IncreaseDecrease();
                increaseDecrease.setCode(codeFactoryService.getNextCodeByType("SY"));
                increaseDecrease.setStockId(stock.getId());
                increaseDecrease.setIdNum(checkList.getDifferenceNum());// 差异数量
                if (null != goods.getPrice()) {
                    increaseDecrease.setIdMoney(goods.getPrice().multiply(new BigDecimal(increaseDecrease.getIdNum())));
                }
                increaseDecrease.setIdSource(IncreaseDecreaseIdSource.PD);
                increaseDecrease.setTaskId(checkList.getId());
                increaseDecrease.setTaskCode(checkTask.getCode());
                increaseDecrease.setStatus(IncreaseDecreaseStatus.NEW);

                increaseDecreaseMapper.save(increaseDecrease);
            }
            if (!CollectionUtils.isEmpty(increaseDecreaseList)) {
                checkTask.setStatus(CheckTaskStatus.INCREASE_DECREASE);
                checkTaskMapper.updateById(checkTask);
                batchVOBuilder.results(increaseDecreaseList.stream().map(item -> item.getCode()).collect(Collectors.toList()));
            }
        });
        return batchVOBuilder.build();
    }

    @Override
    public PageBean<CheckTaskDetailVO> pageDetail(Map data) {
        Page<CheckTaskDetailVO> page = checkTaskMapper.selectCheckTaskDetail(data);
        return PageBean.builder(page);
    }

    @Override
    public PageBean<CheckTask> page(Map data) {
        Page<CheckTask> page = checkTaskMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public List<CheckTask> list(Map data) {
        return checkTaskMapper.page(data);
    }

    @Override
    public CheckTask getById(Long id) {
        return checkTaskMapper.getById(id);
    }

    @Override
    public void save(CheckTaskDTO data) {
        checkTaskMapper.insert(data);
    }

    @Override
    public void updateById(CheckTask data) {
        checkTaskMapper.updateById(data);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> checkTaskMapper.removeById(id));
    }
}

