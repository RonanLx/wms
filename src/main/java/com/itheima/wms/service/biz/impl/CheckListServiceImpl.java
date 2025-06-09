package com.itheima.wms.service.biz.impl;

import com.github.pagehelper.Page;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.constants.LocationFrozen;
import com.itheima.wms.common.constants.StockRecordType;
import com.itheima.wms.common.constants.StockRecordWay;
import com.itheima.wms.mapper.base.LocationMapper;
import com.itheima.wms.mapper.biz.CheckListMapper;
import com.itheima.wms.mapper.biz.CheckTaskMapper;
import com.itheima.wms.mapper.biz.StockMapper;
import com.itheima.wms.mapper.biz.StockRecordMapper;
import com.itheima.wms.model.dto.biz.CheckListBatchDTO;
import com.itheima.wms.model.dto.biz.CheckListDTO;
import com.itheima.wms.model.entity.base.Location;
import com.itheima.wms.model.entity.biz.CheckList;
import com.itheima.wms.model.entity.biz.CheckTask;
import com.itheima.wms.model.entity.biz.Stock;
import com.itheima.wms.model.entity.biz.StockRecord;
import com.itheima.wms.model.vo.biz.CheckListDetailVO;
import com.itheima.wms.model.vo.biz.CheckListSumVO;
import com.itheima.wms.service.biz.CheckListService;
import com.itheima.wms.service.biz.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 盘点清单
 * </p>
 */
@Slf4j
@Service
public class CheckListServiceImpl implements CheckListService {

    @Autowired
    private CheckListMapper checkListMapper;
    @Autowired
    private StockRecordMapper stockRecordMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StockService stockService;
    @Autowired
    private CheckTaskMapper checkTaskMapper;
    @Autowired
    private LocationMapper locationMapper;

    @Override
    @Transactional
    public void saveBatchByStockIds(CheckListBatchDTO checkListBatchDTO) {
        List<Stock> stockEntities = checkListBatchDTO.getStockIds().stream().map(stockId ->
                stockMapper.getById(stockId)
        ).collect(Collectors.toList());
        List<CheckList> checkListEntities = new ArrayList<>();
        for (Stock stock : stockEntities) {
            CheckList checkList = CheckList.builder().masterId(checkListBatchDTO.getMasterId()).stockId(stock.getId()).stockNum(stock.getFree()).status(1).build();
            checkListMapper.save(checkList);
        }
    }

    @Override
    public void saveBatch(List<CheckList> entityList) {
        // 冻结库存
        entityList.forEach(checkListEntity -> {
            checkListMapper.save(checkListEntity);
            frozenStock(checkListEntity);
        });
    }

    @Override
    public PageBean<CheckListDetailVO> pageDetail(Map data) {
        Page<CheckListDetailVO> page = checkListMapper.selectCheckListDetail(data);
        return PageBean.builder(page);
    }

    @Override
    public CheckListSumVO getSumByMasterId(Long masterId) {
        Map map = Map.of("master_id", masterId, "status", 1); // 查询一盘即可
        List<CheckList> checkListEntities = checkListMapper.getByMap(map);
        return CheckListSumVO.builder().locationTotal(checkListEntities.size()).goodsTotal(checkListEntities.stream().mapToInt(CheckList::getStockNum).sum()).build();
    }

    @Override
    public List<Long> stockIds(Map data) {
        List<Long> ids = checkListMapper.selectStockIdByMasterId(data.get("masterId"));
        return ids;
    }

    @Override
    public void cancelBatch(List<CheckList> checkListEntities) {
        checkListEntities.forEach(checkListEntity -> unfreezeStock(checkListEntity));
    }

    @Override
    public PageBean<CheckList> page(Map data) {
        Page<CheckList> page = checkListMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public List<CheckList> list(Map data) {
        return checkListMapper.page(data);
    }

    @Override
    public CheckList getById(Long id) {
        return checkListMapper.getById(id);
    }

    @Override
    public void save(CheckListDTO data) {
        checkListMapper.save(data);
    }

    @Override
    public void removeByMasterId(Long masterId) {
        checkListMapper.removeByMasterId(masterId);
    }

    @Override
    @Transactional
    public void removeByIds(List<Long> idList) {
        idList.forEach(id -> {
            unfreezeStock(checkListMapper.getById(id));
            checkListMapper.removeById(id);
        });
    }

    @Override
    @Transactional
    public void updateById(CheckList entity) {
        CheckList dbEntity = checkListMapper.getById(entity.getId());
        if (null != entity.getCheckNum()) {
            entity.setDifferenceNum(entity.getCheckNum() - dbEntity.getStockNum());
        }
        checkListMapper.updateById(entity);
        dbEntity = checkListMapper.getById(entity.getId());

        // 实盘数量
        if (null != entity.getCheckNum()) {
            /*queryWrapper.select("IFNULL(sum(check_num),0) as totalNum,IFNULL(sum(abs(difference_num)),0) as differenceNum")*/
            Map map = Map.of("master_id", dbEntity.getMasterId(), "status", dbEntity.getStatus());
            Map<String, Object> res = checkListMapper.sumByMap(map);
            Integer totalNum = ((java.math.BigDecimal) res.get("totalNum")).intValue();
            Integer differenceNum = ((java.math.BigDecimal) res.get("differenceNum")).intValue();

            CheckTask checkTask = CheckTask.builder()
                    .checkTotal(totalNum)
                    .differenceNum(differenceNum)
                    .masterId(dbEntity.getMasterId())
                    .status(dbEntity.getStatus())
                    .build();
            checkTaskMapper.update(checkTask);
        }
    }

    /**
     * 冻结库存
     *
     * @param entity
     */
    private void frozenStock(CheckList entity) {
        Long stockId = entity.getStockId();
        Integer stockNum = entity.getStockNum();

        Stock stock = stockMapper.getById(stockId);
        locationMapper.updateById(Location.builder().id(stock.getLocationId()).frozen(LocationFrozen.FROZEN).build());

        Integer original = stock.getTotal();
        Integer originalFree = stock.getFree();

        if (stockNum == null || stockNum == 0) {
            throw new RuntimeException("库位没有可盘点的库存");
        }

        stockService.addFrozen(stockId, stockNum);

        StockRecord stockRecord = new StockRecord();
        stockRecord.setSourceId(entity.getId());
        stockRecord.setLocationId(stock.getLocationId());
        stockRecord.setType(StockRecordType.PD);// 类型盘点
        stockRecord.setOriginal(original);
        stockRecord.setOriginalFree(originalFree);
        stockRecord.setWay(StockRecordWay.FROZEN);
        stockRecord.setAlteration(stockNum);
        stockRecord.setResult(original);
        stockRecord.setResultFree(originalFree - stockNum);
        stockRecordMapper.save(stockRecord);
    }

    /**
     * 解冻库存
     *
     * @param entity
     */
    private void unfreezeStock(CheckList entity) {
        Long stockId = entity.getStockId();
        Integer stockNum = entity.getStockNum();

        Stock stock = stockMapper.getById(stockId);
        locationMapper.updateById(Location.builder().id(stock.getLocationId()).frozen(LocationFrozen.NORMAL).build());
        Integer original = stock.getTotal();
        Integer originalFree = stock.getFree();

        stockService.unfreeze(stockId, stockNum);

        StockRecord stockRecord = new StockRecord();
        stockRecord.setSourceId(entity.getId());
        stockRecord.setLocationId(stock.getLocationId());
        stockRecord.setType(StockRecordType.PD);// 类型盘点
        stockRecord.setOriginal(original);
        stockRecord.setOriginalFree(originalFree);
        stockRecord.setWay(StockRecordWay.UNFREEZE);
        stockRecord.setAlteration(stockNum);
        stockRecord.setResult(original);
        stockRecord.setResultFree(originalFree + stockNum);
        stockRecordMapper.save(stockRecord);
    }
}


