package com.itheima.wms.service.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.constants.StockRecordType;
import com.itheima.wms.common.constants.StockRecordWay;
import com.itheima.wms.common.constants.StockStatus;
import com.itheima.wms.mapper.base.GoodsMapper;
import com.itheima.wms.mapper.biz.*;
import com.itheima.wms.model.dto.biz.ReceiptListBatchDTO;
import com.itheima.wms.model.dto.biz.ReceiptListDTO;
import com.itheima.wms.model.dto.biz.ReceiptListQueryDTO;
import com.itheima.wms.model.entity.base.Goods;
import com.itheima.wms.model.entity.biz.*;
import com.itheima.wms.model.vo.biz.ReceiptListDetailVO;
import com.itheima.wms.model.vo.biz.ReceiptListSumVO;
import com.itheima.wms.service.biz.ReceiptListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 入库清单
 * </p>
 */
@Slf4j
@Service
public class ReceiptListServiceImpl implements ReceiptListService {

    @Autowired
    private ReceiptListMapper receiptListMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private ReceiptMapper receiptMapper;
    @Autowired
    private ReceivingMapper receivingMapper;
    @Autowired
    private GroundingMapper groundingMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StockRecordMapper stockRecordMapper;
    @Autowired
    private LocationPlanMapper locationPlanMapper;

    @Override
    public PageBean<ReceiptListDetailVO> pageDetail(ReceiptListQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<ReceiptListDetailVO> page = receiptListMapper.pageDetail(data);
        page.getResult().forEach(receiptListDetailVO -> {
            List<LocationPlan> locationPlanList = locationPlanMapper.getByReceiptListId(receiptListDetailVO.getId());
            receiptListDetailVO.setLocationPlanEntities(locationPlanList);
        });
        return PageBean.builder(page);
    }

    @Override
    public PageBean<ReceiptList> page(ReceiptListQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<ReceiptList> page = receiptListMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public List<Long> goodsIds(ReceiptListQueryDTO data) {
        return receiptListMapper.getByMasterId(data.getMasterId())
                .stream().map(receiptList -> receiptList.getGoodsId())
                .collect(Collectors.toList());
    }


    @Override
    public List<ReceiptList> list(ReceiptListQueryDTO data) {
        return receiptListMapper.page(data);
    }


    @Override
    @Transactional
    public void saveBatch(ReceiptListBatchDTO receiptListBatchDTO) {
        List<Goods> goodsEntities = receiptListBatchDTO.getGoodsIds()
                .stream().map(id -> goodsMapper.getById(id))
                .collect(Collectors.toList());
        Receipt receipt = receiptMapper.getById(receiptListBatchDTO.getMasterId());

        for (Goods goods : goodsEntities) {
            ReceiptList receiptList = ReceiptList.builder()
                    .masterId(receiptListBatchDTO.getMasterId())
                    .goodsId(goods.getId())
                    .ownerId(goods.getOwnerId())
                    .areaId(receipt.getAreaId())
                    .warehouseId(receipt.getWarehouseId())
                    .build();
            receiptListMapper.save(receiptList);
        }
    }

    /**
     * 根据货主的上架策略，系统给推荐库位。
     * 如果没有符合的上架策略，则按以下逻辑推荐：
     * 在货主的使用库位范围内，寻找存储类型库区，按以下规则上架：
     * 1.货品有指定库位上指定不满库位，否则上指定库位的空库位
     * 2.货品没有指定库位，根据一品一位的原则，搜索是否有此货品的未满的库位，优先放未满库位
     * 3，否则选择空库位放货品，按照库位号大小依次上架
     */


    /**
     *
     * @param masterId
     * @return
     */
    @Override
    public ReceiptListSumVO getReceiptListSumDetail(Long masterId) {
        return receiptListMapper.getReceiptListSumDetail(masterId);
    }

    @Override
    @Transactional
    public void updateById(ReceiptListDTO dto) {
        ReceiptList receiptList = receiptListMapper.getById(dto.getId());

        //实收数量差异
        if (null != dto.getRealNum()) {
            dto.setRealDifferenceNum(dto.getRealNum() - receiptList.getPlanNum());
        }
        //上架数量差异
        if (null != dto.getGroundingNum()) {
            dto.setGroundingDifferenceNum(dto.getGroundingNum() - receiptList.getRealNum());
        }
        receiptListMapper.updateById(dto);

        List<ReceiptList> receiptLists = receiptListMapper.getByMasterId(receiptList.getMasterId());
        // 货品数量汇总
        if (null != dto.getPlanNum()) {
            int totalNum = receiptListMapper.getByMasterId(receiptList.getMasterId()).stream().mapToInt(rl -> rl.getPlanNum() == null ? 0 : rl.getPlanNum()).sum();
            receiptMapper.updateById(Receipt.builder().id(receiptList.getMasterId()).planNum(totalNum).build());
        }
        // 实收数量汇总
        if (null != dto.getRealNum()) {
            Integer totalNum = receiptLists.stream().mapToInt(rl -> rl.getRealNum() == null ? 0 : rl.getRealNum()).sum();
            Integer differenceNum = receiptLists.stream().mapToInt(rl -> rl.getRealDifferenceNum() == null ? 0 : rl.getRealDifferenceNum()).sum();

            Receiving receiving = receivingMapper.getByMasterId(receiptList.getMasterId());
            receiving.setRealNum(totalNum);
            receiving.setDifferenceNum(differenceNum);
            receivingMapper.updateById(receiving);
        }

        // 实上数量
        if (null != dto.getGroundingNum()) {
            Integer totalNum = receiptLists.stream().mapToInt(rl -> rl.getGroundingNum() == null ? 0 : rl.getGroundingNum()).sum();
            Integer differenceNum = receiptLists.stream().mapToInt(rl -> rl.getGroundingDifferenceNum() == null ? 0 : rl.getGroundingDifferenceNum()).sum();

            Grounding grounding = groundingMapper.getByMasterId(receiptList.getMasterId());
            grounding.setGroundingNum(totalNum);
            grounding.setDifferenceNum(differenceNum);
            groundingMapper.updateById(grounding);
        }

        // 实上库位，更新库存，需要冻结
        if (!CollectionUtils.isEmpty(dto.getLocationPlanEntities())) {
            //先移除旧数据
            List<LocationPlan> oldPlanList = locationPlanMapper.getByReceiptListId(receiptList.getId());
            oldPlanList.forEach(oldPlan -> {
                locationPlanMapper.removeById(oldPlan.getId());
                //旧数据库存操作
                StockRecord stockRecord = StockRecord.builder()
                        .sourceId(receiptList.getId())
                        .type(StockRecordType.RK)
                        .locationId(oldPlan.getLocationId())
                        .build();
                stockRecordMapper.remove(stockRecord);

                Stock stock = stockMapper.getByLocationId(oldPlan.getLocationId());
                if (stock != null) {
                    stock.setTotal(stock.getTotal() - oldPlan.getNum()); //总库存
                    stock.setFrozen(stock.getFrozen() - oldPlan.getNum()); //冻结库存
                    stockMapper.updateById(stock);
                }
            });

            //再添加新数据
            dto.getLocationPlanEntities().forEach(newPlan -> {
                newPlan.setReceiptListId(receiptList.getId());
                newPlan.setGoodsId(receiptList.getGoodsId());
                System.out.println(newPlan);
                locationPlanMapper.save(newPlan);
                //冻结库存
                StockRecord stockRecord = StockRecord.builder()
                        .sourceId(receiptList.getId())
                        .type(StockRecordType.RK)
                        .locationId(newPlan.getLocationId())
                        .way(StockRecordWay.FROZEN)
                        .alteration(newPlan.getNum())//操作数量
                        .build();
                stockRecordMapper.save(stockRecord);

                Stock stock = stockMapper.getByLocationId(newPlan.getLocationId());
                if (stock != null) {
                    stock.setTotal(stock.getTotal() + newPlan.getNum()); //总库存
                    stock.setFrozen(stock.getFrozen() + newPlan.getNum()); //冻结库存
                    stockMapper.updateById(stock);
                } else {
                    stock = Stock.builder()
                            .frozen(newPlan.getNum())
                            .free(0)
                            .total(newPlan.getNum())
                            .goodsId(receiptList.getGoodsId())
                            .ownerId(receiptList.getOwnerId())
                            .status(StockStatus.UNFULL)
                            .warehouseId(newPlan.getWarehouseId())
                            .areaId(newPlan.getAreaId())
                            .locationId(newPlan.getLocationId())
                            .build();
                    stockMapper.save(stock);
                }
            });
        }
    }

    @Override
    public ReceiptList getById(Long id) {
        return receiptListMapper.getById(id);
    }

    @Override
    public void save(ReceiptListDTO data) {
        receiptListMapper.save(data);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> receiptListMapper.removeById(id));
    }

    @Override
    public void removeByMasterId(Long masterId) {
        receiptListMapper.removeByMasterId(masterId);
    }
}

