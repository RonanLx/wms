/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.constants.GroundingStatus;
import com.itheima.wms.common.constants.ReceiptStatus;
import com.itheima.wms.common.constants.ReceivingStatus;
import com.itheima.wms.mapper.biz.GroundingMapper;
import com.itheima.wms.mapper.biz.ReceiptListMapper;
import com.itheima.wms.mapper.biz.ReceiptMapper;
import com.itheima.wms.mapper.biz.ReceivingMapper;
import com.itheima.wms.model.dto.biz.ReceivingDTO;
import com.itheima.wms.model.dto.biz.ReceivingQueryDTO;
import com.itheima.wms.model.entity.biz.Grounding;
import com.itheima.wms.model.entity.biz.Receipt;
import com.itheima.wms.model.entity.biz.ReceiptList;
import com.itheima.wms.model.entity.biz.Receiving;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.ReceivingDetailVO;
import com.itheima.wms.service.base.CodeFactoryService;
import com.itheima.wms.service.biz.ReceivingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 收货任务
 * </p>
 */
@Slf4j
@Service
public class ReceivingServiceImpl implements ReceivingService {

    @Autowired
    private ReceivingMapper receivingMapper;
    @Autowired
    private ReceiptMapper receiptMapper;
    @Autowired
    private ReceiptListMapper receiptListMapper;
    @Autowired
    private GroundingMapper groundingMapper;
    @Autowired
    private CodeFactoryService codeFactoryService;

    @Override
    @Transactional
    public BatchVO complete(List<Long> ids) {
        BatchVO.BatchVOBuilder batchVOBuilder = BatchVO.builder();
        ids.forEach(id -> {
            Receiving receiving = receivingMapper.getById(id);
            // 校验清单是否有未填写的
            List<ReceiptList> receiptLists = receiptListMapper.getByMasterId(receiving.getMasterId());
            receiptLists = receiptLists.stream().filter(item -> null != item.getRealNum()).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(receiptLists)) {
                batchVOBuilder.error(receiving.getCode() + "未完成收货确认");
                return;
            }

            if (receiving.getStatus() != ReceivingStatus.RECEIVING) {
                batchVOBuilder.error(receiving.getCode() + "状态不合法");
                return;
            }

            // 修改收货任务状态 为 收货完成
            receiving.setStatus(ReceivingStatus.COMPLETED);
            receiving.setCompletionTime(LocalDateTime.now());
            receivingMapper.updateById(receiving);

            // 修改入库单状态 为 收货完成
            Receipt receipt = receiptMapper.getById(receiving.getMasterId());
            receipt.setStatus(ReceiptStatus.RECEIVING_COMPLETED);
            receiptMapper.updateById(receipt);

            // 生成上架任务
            Grounding grounding = new Grounding();
            BeanUtils.copyProperties(receipt, grounding);
            grounding.setMasterId(receipt.getId());
            // 自动生成上架任务编号
            grounding.setCode(codeFactoryService.getNextCodeByType("SJRW"));
            grounding.setReceiptCode(receipt.getCode());
            grounding.setStatus(GroundingStatus.NEW);  //初始状态 待分配
            grounding.setRealNum(receiving.getRealNum());
            grounding.setId(null);
            groundingMapper.insert(grounding);

            // 修改入库单状态 为 上架中
            receipt.setStatus(ReceiptStatus.GROUNDING);
            receiptMapper.updateById(receipt);
            batchVOBuilder.result(grounding.getCode());
        });
        return batchVOBuilder.build();
    }

    @Override
    public PageBean<ReceivingDetailVO> pageDetail(ReceivingQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<ReceivingDetailVO> page = receivingMapper.pageDetail(data);
        return PageBean.builder(page);
    }

    @Override
    public PageBean<Receiving> page(ReceivingQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<Receiving> page = receivingMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public List<Receiving> list(ReceivingQueryDTO data) {
        return receivingMapper.page(data);
    }

    @Override
    public Receiving getById(Long id) {
        return receivingMapper.getById(id);
    }

    @Override
    public void save(ReceivingDTO data) {
        receivingMapper.insert(data);
    }

    @Override
    public void distribute(ReceivingDTO data) {
        data.setStatus(ReceivingStatus.RECEIVING);
        receivingMapper.updateById(data);
    }

    @Override
    public void updateById(ReceivingDTO data) {
        receivingMapper.updateById(data);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> receivingMapper.removeById(id));
    }

}

