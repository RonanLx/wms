package com.itheima.wms.service.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.constants.*;
import com.itheima.wms.mapper.base.GoodsMapper;
import com.itheima.wms.mapper.biz.*;
import com.itheima.wms.model.dto.biz.GroundingDTO;
import com.itheima.wms.model.dto.biz.GroundingQueryDTO;
import com.itheima.wms.model.entity.base.Goods;
import com.itheima.wms.model.entity.biz.*;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.GroundingDetailVO;
import com.itheima.wms.service.base.CodeFactoryService;
import com.itheima.wms.service.biz.GroundingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 上架任务
 * </p>
 */
@Slf4j
@Service
public class GroundingServiceImpl implements GroundingService {

    @Autowired
    private GroundingMapper groundingMapper;
    @Autowired
    private ReceiptMapper receiptMapper;
    @Autowired
    private IncreaseDecreaseMapper increaseDecreaseMapper;
    @Autowired
    private ReceiptListMapper receiptListMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private LocationPlanMapper locationPlanMapper;
    @Autowired
    private StockRecordMapper stockRecordMapper;
    @Autowired
    private CodeFactoryService codeFactoryService;

    @Override
    @Transactional
    public BatchVO complete(List<Long> ids) {
        BatchVO.BatchVOBuilder batchVOBuilder = BatchVO.builder();
        ids.forEach(id -> {
            Grounding grounding = groundingMapper.getById(id);

            // 校验清单是否有未填写的
            List<ReceiptList> receiptLists = receiptListMapper.getByMasterId(grounding.getMasterId());
            receiptLists = receiptLists.stream().filter(item -> null != item.getGroundingNum()).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(receiptLists)) {
                batchVOBuilder.error(grounding.getCode() + "未完成上架确认");
                return;
            }

            if (GroundingStatus.GROUNDING != grounding.getStatus()) {
                batchVOBuilder.error(grounding.getCode() + "状态不合法");
                return;
            }

            // 修改上架任务状态 为 上架完成
            grounding.setStatus(GroundingStatus.COMPLETED);
            grounding.setCompletionTime(LocalDateTime.now());
            groundingMapper.updateById(grounding);

            // 修改入库单状态 为 上架完成
            Receipt receipt = receiptMapper.getById(grounding.getMasterId());
            receipt.setStatus(ReceiptStatus.GROUNDING_COMPLETED);
            receiptMapper.updateById(receipt);

            // 解冻上架库存
            receiptLists.forEach(receiptList -> {
                List<LocationPlan> locationPlanList = locationPlanMapper.getByReceiptListId(receiptList.getId());
                locationPlanList.forEach(locationPlan -> {

                    Stock stock = stockMapper.getByLocationId(locationPlan.getLocationId());
                    stock.setFree(stock.getFree() + locationPlan.getNum()); //可用库存
                    stock.setFrozen(stock.getFrozen() - locationPlan.getNum()); //冻结库存
                    stock.setGoodsId(receiptList.getGoodsId());
                    stockMapper.updateById(stock);

                    StockRecord stockRecord = new StockRecord();
                    stockRecord.setSourceId(receiptList.getId());
                    stockRecord.setLocationId(locationPlan.getLocationId());
                    stockRecord.setType(StockRecordType.RK);// 类型入库
                    stockRecord.setAlteration(locationPlan.getNum());
                    stockRecord.setWay(StockRecordWay.POSITIVE);
                    //stockRecord.setOriginal(original);
                    //stockRecord.setOriginalFree(originalFree);
                    //stockRecord.setResult(stockRecord.getOriginal());
                    //stockRecord.setResultFree(stockRecordEntity.getOriginalFree() + stockRecordEntity.getAlteration());
                    stockRecordMapper.save(stockRecord);
                });
            });

            // 生成损益单
            if (grounding.getDifferenceNum() != 0) {
                // 差异数大于0 查询明细 生成损益单
                receiptLists.stream().filter(receiptList -> receiptList.getGroundingDifferenceNum() != 0).forEach(receiptList -> {
                    // 查询货品信息
                    Goods goods = goodsMapper.getById(receiptList.getGoodsId());
                    // 根据库位id查询库存id
                    IncreaseDecrease increaseDecrease = new IncreaseDecrease();
                    increaseDecrease.setCode(codeFactoryService.getNextCodeByType("SY"));
                    increaseDecrease.setIdNum(receiptList.getGroundingDifferenceNum());// 上架差异数量

                    List<LocationPlan> locationPlans = locationPlanMapper.getByReceiptListId(receiptList.getId());
                    Stock stock = stockMapper.getByLocationId(locationPlans.get(0).getLocationId());
                    increaseDecrease.setStockId(stock.getId());

                    if (null != goods.getPrice() && null != increaseDecrease.getIdNum()) {
                        increaseDecrease.setIdMoney(goods.getPrice().multiply(new BigDecimal(increaseDecrease.getIdNum())));
                    }
                    increaseDecrease.setIdSource(IncreaseDecreaseIdSource.SJ);
                    increaseDecrease.setTaskId(grounding.getId());
                    increaseDecrease.setTaskCode(grounding.getCode());
                    increaseDecrease.setStatus(IncreaseDecreaseStatus.CHANGE);
                    //新增损益单
                    increaseDecreaseMapper.save(increaseDecrease);

                });
            }
            batchVOBuilder.result(grounding.getCode());
        });
        return batchVOBuilder.build();
    }


    @Override
    public PageBean<GroundingDetailVO> pageDetail(GroundingQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<GroundingDetailVO> page = groundingMapper.pageDetail(data);
        return PageBean.builder(page);
    }

    @Override
    public PageBean<Grounding> page(GroundingQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<Grounding> page = groundingMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public List<Grounding> list(GroundingQueryDTO data) {
        return groundingMapper.page(data);
    }

    @Override
    public Grounding getById(Long id) {
        return groundingMapper.getById(id);
    }

    @Override
    public void save(GroundingDTO data) {
        groundingMapper.insert(data);
    }

    @Override
    public void distribute(GroundingDTO data) {
        data.setStatus(GroundingStatus.GROUNDING); //上架中
        groundingMapper.updateById(data);
    }

    @Override
    public void updateById(GroundingDTO data) {
        groundingMapper.updateById(data);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> {
            groundingMapper.removeById(id);
        });
    }
}

