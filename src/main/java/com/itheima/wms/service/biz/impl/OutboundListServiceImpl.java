package com.itheima.wms.service.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.constants.StockRecordType;
import com.itheima.wms.common.constants.StockRecordWay;
import com.itheima.wms.mapper.biz.*;
import com.itheima.wms.model.dto.biz.OutboundListBatchDTO;
import com.itheima.wms.model.dto.biz.OutboundListDTO;
import com.itheima.wms.model.dto.biz.OutboundListQueryDTO;
import com.itheima.wms.model.entity.biz.*;
import com.itheima.wms.model.vo.biz.OutboundListDetailVO;
import com.itheima.wms.model.vo.biz.OutboundListSumVO;
import com.itheima.wms.service.biz.OutboundListService;
import com.itheima.wms.service.biz.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 出库清单
 * </p>
 */
@Slf4j
@Service
public class OutboundListServiceImpl implements OutboundListService {

    @Autowired
    private OutboundListMapper outboundListMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StockService stockService;
    @Autowired
    private OutboundMapper outboundMapper;
    @Autowired
    private PickingMapper pickingMapper;
    @Autowired
    private StockRecordMapper stockRecordMapper;

    @Override
    public PageBean<OutboundListDetailVO> pageDetail(OutboundListQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<OutboundListDetailVO> page = outboundListMapper.pageDetail(data);
        return PageBean.builder(page);
    }

    @Override
    public OutboundListSumVO getSumByMasterId(Long masterId) {
        return outboundListMapper.sumOutboundListDetail(masterId);
    }

    @Override
    @Transactional
    public void saveBatch(OutboundListBatchDTO outboundListBatchDTO) {
        outboundListBatchDTO.getStockIds().forEach(id -> {
            Stock stock = stockMapper.getById(id);
            OutboundList outboundList = OutboundList.builder()
                    .masterId(outboundListBatchDTO.getMasterId())
                    .stockId(stock.getId())
                    .status(1)
                    .build();
            outboundListMapper.save(outboundList);
        });
    }


    @Override
    public List<Long> stockIds(Long masterId) {
        List<OutboundList> ids = outboundListMapper.getByMasterId(masterId);
        return ids.stream().map(outboundList -> outboundList.getStockId()).collect(Collectors.toList());
    }

    @Override
    public void cancelBatch(List<OutboundList> outboundListEntities) {
        outboundListEntities.forEach(outboundListEntity -> unfrozenStock(outboundListEntity));
    }

    @Override
    public OutboundList getById(Long id) {
        return outboundListMapper.getById(id);
    }

    @Override
    public void save(OutboundListDTO data) {
        outboundListMapper.save(data);
    }

    @Override
    public void removeByMasterId(Long masterId) {
        outboundListMapper.removeByMasterId(masterId);
    }

    @Override
    @Transactional
    public void updateById(OutboundListDTO data) {
        OutboundList dbEntity = outboundListMapper.getById(data.getId());

        List<OutboundList> outboundLists = outboundListMapper.getByMasterId(dbEntity.getMasterId());
        // 货品数量汇总
        if (null != data.getOutboundNum()) { //发货数量
            dbEntity.setOutboundNum(data.getOutboundNum());

            Integer totalNum = outboundLists.stream().mapToInt(outboundList -> outboundList.getOutboundNum() != null ? outboundList.getOutboundNum() : 0).sum();
            outboundMapper.updateById(Outbound.builder().id(dbEntity.getMasterId()).goodsNum(totalNum).build());

            // 冻结库存 旧数据库存操作  增加库存操作记录
            Stock stock = stockMapper.getById(dbEntity.getStockId());
            /*StockRecord stockRecord = StockRecord.builder()
                    .sourceId(dbEntity.getId())
                    .type(StockRecordType.CK)
                    .locationId(stock.getLocationId())
                    .build();*/
            List<StockRecord> list = stockRecordMapper.list(Map.of());
            if (list.size() == 1) {
                StockRecord stockRecord = list.get(0);
                stockRecord.setAlteration(dbEntity.getOutboundNum());
                stockRecordMapper.updateById(stockRecord);
            }

            stock.setFree(stock.getFree() - dbEntity.getOutboundNum()); //可用库存
            stock.setFrozen(stock.getFrozen() - dbEntity.getOutboundNum()); //冻结库存
            stockMapper.updateById(stock);
        }

        if (null != data.getPickingNum()) { // 实拣数量
            dbEntity.setDifferenceNum(data.getPickingNum() - dbEntity.getOutboundNum());
            dbEntity.setPickingNum(data.getPickingNum());


            Integer pickingNumTotal = outboundLists.stream().mapToInt(outboundList -> outboundList.getPickingNum() != null ? outboundList.getPickingNum() : 0).sum();
            Integer differenceNumTotal = outboundLists.stream().mapToInt(outboundList -> outboundList.getDifferenceNum() != null ? outboundList.getDifferenceNum() : 0).sum();

            Picking picking = pickingMapper.getByMasterId(dbEntity.getMasterId());
            picking.setRealNum(pickingNumTotal);
            picking.setDifferenceNum(differenceNumTotal);
            pickingMapper.updateById(picking);

            // 解冻库存
            Stock stock = stockMapper.getById(dbEntity.getStockId());
            List<StockRecord> list = stockRecordMapper.list(Map.of());
            if (list.size() == 1) {
                StockRecord stockRecord = list.get(0);
                stockRecord.setAlteration(dbEntity.getOutboundNum());
                stockRecordMapper.updateById(stockRecord);
            }

            stock.setTotal(stock.getTotal() - dbEntity.getOutboundNum()); //总库存
            stock.setFrozen(stock.getFrozen() - dbEntity.getOutboundNum()); //冻结库存
            stockMapper.updateById(stock);
        }

        outboundListMapper.updateById(dbEntity);
    }

    @Override
    @Transactional
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> {
            unfrozenStock(outboundListMapper.getById(id));
            outboundListMapper.removeById(id);
        });
    }


    /**
     * 解冻库存
     *
     * @param dbEntity
     */
    private void unfrozenStock(OutboundList dbEntity) {
        log.info("解冻出库明细：{}", dbEntity);
        int num = dbEntity.getOutboundNum();
        if (null != dbEntity.getPickingNum() && dbEntity.getPickingNum() > 0) {
            num = dbEntity.getPickingNum();
        }

        if (num <= 0) {
            log.info("无需解冻:{}", dbEntity);
            return;
        }

        // 库存冻结  增加库存操作记录
        Stock stock = stockMapper.getById(dbEntity.getStockId());
        Integer original = stock.getTotal();
        Integer originalFree = stock.getFree();

        stockService.unfreeze(stock.getId(), num); // 解冻记录

        StockRecord stockRecord = new StockRecord();
        stockRecord.setSourceId(dbEntity.getId());
        stockRecord.setLocationId(stock.getLocationId());
        stockRecord.setType(StockRecordType.CK);// 类型出库
        stockRecord.setOriginal(original);
        stockRecord.setOriginalFree(originalFree);
        stockRecord.setWay(StockRecordWay.UNFREEZE);
        stockRecord.setAlteration(num);
        stockRecord.setResult(stockRecord.getOriginal());
        stockRecord.setResultFree(stockRecord.getOriginalFree() + stockRecord.getAlteration());
        stockRecordMapper.save(stockRecord);
    }
}

