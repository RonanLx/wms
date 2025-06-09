package com.itheima.wms.service.biz.impl;

import com.github.pagehelper.Page;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.constants.HandoverStatus;
import com.itheima.wms.common.constants.OutboundStatus;
import com.itheima.wms.mapper.biz.HandoverMapper;
import com.itheima.wms.mapper.biz.OutboundListMapper;
import com.itheima.wms.mapper.biz.OutboundMapper;
import com.itheima.wms.model.dto.biz.HandoverDTO;
import com.itheima.wms.model.entity.biz.Handover;
import com.itheima.wms.model.entity.biz.Outbound;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.HandoverDetailVO;
import com.itheima.wms.service.biz.HandoverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 业务实现类
 * 交接任务
 * </p>
 */
@Slf4j
@Service
public class HandoverServiceImpl implements HandoverService {

    @Autowired
    private HandoverMapper handoverMapper;
    @Autowired
    private OutboundMapper outboundMapper;
    @Autowired
    private OutboundListMapper outboundListMapper;

    @Override
    @Transactional
    public BatchVO complete(List<Long> ids) {
        BatchVO.BatchVOBuilder batchVOBuilder = BatchVO.builder();
        ids.forEach(id -> {
            Handover handover = handoverMapper.getById(id);

            if (HandoverStatus.HANDOVER != handover.getStatus()) {
                log.info("id:{} 状态不正确", id);
                batchVOBuilder.error(handover.getCode() + "状态不合法");
                return;
            }

            // 修改交接任务状态 为 交接完成
            handover.setStatus(HandoverStatus.COMPLETED);
            handover.setCompletionTime(LocalDateTime.now());
            handoverMapper.updateById(handover);

            // 修改入库单状态 为 交接完成
            Outbound outbound = outboundMapper.getById(handover.getMasterId());
            outbound.setStatus(OutboundStatus.HANDOVER_COMPLETED);
            outboundMapper.updateById(outbound);

            batchVOBuilder.result(handover.getCode());
        });
        return batchVOBuilder.build();
    }

    @Override
    public PageBean<HandoverDetailVO> pageDetail(Map data) {
        Page<HandoverDetailVO> page = handoverMapper.pageDetail(data);
        return PageBean.builder(page);
    }


    @Override
    public Handover getById(Long id) {
        return handoverMapper.getById(id);
    }

    @Override
    public void save(HandoverDTO data) {
        handoverMapper.save(data);
    }

    @Override
    public void updateById(Handover data) {
        handoverMapper.updateById(data);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> {
            handoverMapper.removeById(id);
        });
    }
}

