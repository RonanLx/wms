/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.biz.impl;

import com.github.pagehelper.Page;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.constants.*;
import com.itheima.wms.mapper.biz.*;
import com.itheima.wms.model.dto.biz.PickingDTO;
import com.itheima.wms.model.entity.biz.*;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.PickingDetailVO;
import com.itheima.wms.service.base.CodeFactoryService;
import com.itheima.wms.service.biz.PickingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 拣货任务
 * </p>
 */
@Slf4j
@Service
public class PickingServiceImpl implements PickingService {

    @Autowired
    private PickingMapper pickingMapper;
    @Autowired
    private HandoverMapper handoverMapper;
    @Autowired
    private OutboundMapper outboundMapper;
    @Autowired
    private OutboundListMapper outboundListMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StockRecordMapper stockRecordMapper;
    @Autowired
    private CodeFactoryService codeFactoryService;

    @Override
    @Transactional
    public BatchVO complete(List<Long> ids) {
        BatchVO.BatchVOBuilder batchVOBuilder = BatchVO.builder();
        ids.forEach(id -> {
            Picking picking = pickingMapper.getById(id);

            // 校验清单是否有未填写的
            List<OutboundList> outboundListEntities = outboundListMapper.getByMasterId(picking.getMasterId());
            outboundListEntities = outboundListEntities.stream().filter(item -> null != item.getPickingNum()).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(outboundListEntities)) {
                log.info("id:{} 清单未填写完成", id);
                batchVOBuilder.error(picking.getCode() + "未完成拣货确认");
                return;
            }

            if (PickingStatus.PICKING != picking.getStatus()) {
                log.info("id:{} 状态不正确", id);
                batchVOBuilder.error(picking.getCode() + "状态不合法");
                return;
            }

            // 修改拣货任务状态 为 拣选完成
            picking.setStatus(PickingStatus.COMPLETED);
            picking.setCompletionTime(LocalDateTime.now());
            pickingMapper.updateById(picking);

            // 修改出库单状态 为 拣选完成
            Outbound outbound = outboundMapper.getById(picking.getMasterId());
            outbound.setStatus(OutboundStatus.PICKING_COMPLETED);
            outboundMapper.updateById(outbound);

            //  生成损益  和产品确认 不需要生成损益。

            // 生成交接任务
            Handover handover = new Handover();
            BeanUtils.copyProperties(picking, handover);
            BeanUtils.copyProperties(outbound, handover);
            handover.setMasterId(outbound.getId());
            handover.setCode(codeFactoryService.getNextCodeByType("JJRW"));
            handover.setStatus(HandoverStatus.NEW);  //初始状态
            handover.setId(null);
            handoverMapper.save(handover);

            // 修改出库单状态 为 交接中
            outbound.setStatus(OutboundStatus.HANDOVER);
            outboundMapper.updateById(outbound);

            // 减库存 （之前已经被冻结） 直接修改冻结库存 总库存即可
            subStock(outboundListEntities);

            batchVOBuilder.result(handover.getCode());
        });
        return batchVOBuilder.build();
    }

    /**
     * 减库存
     *
     * @param outboundListEntities
     */
    private void subStock(List<OutboundList> outboundListEntities) {
        outboundListEntities.forEach(outboundListEntity -> {
            Long stockId = outboundListEntity.getStockId();
            Integer dbPickingNum = outboundListEntity.getPickingNum();

            Stock stock = stockMapper.getById(stockId);
            Integer original = stock.getTotal();
            Integer originalFree = stock.getFree();

            stock.setFrozen(stock.getFrozen() - dbPickingNum);
            stock.setTotal(stock.getFree() + stock.getFrozen());
            stockMapper.updateById(stock);

            StockRecord stockRecord = new StockRecord();
            stockRecord.setSourceId(outboundListEntity.getId());
            stockRecord.setLocationId(stock.getLocationId());
            stockRecord.setType(StockRecordType.JH);// 拣货
            stockRecord.setOriginal(original);
            stockRecord.setOriginalFree(originalFree);
            stockRecord.setWay(StockRecordWay.NEGATIVE);
            stockRecord.setAlteration(dbPickingNum);
            stockRecord.setResult(stock.getTotal());
            stockRecord.setResultFree(stock.getFree());
            stockRecordMapper.save(stockRecord);
        });
    }

    @Override
    public PageBean<PickingDetailVO> pageDetail(Map data) {
        Page<PickingDetailVO> page = pickingMapper.pageDetail(data);
        return PageBean.builder(page);
    }


    @Override
    public Picking getById(Long id) {
        return pickingMapper.getById(id);
    }

    @Override
    public void save(PickingDTO data) {
        pickingMapper.save(data);
    }

    @Override
    public void updateById(Picking data) {
        pickingMapper.updateById(data);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> pickingMapper.removeById(id));
    }
}

