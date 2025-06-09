package com.itheima.wms.service.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.constants.ReceiptStatus;
import com.itheima.wms.common.constants.ReceivingStatus;
import com.itheima.wms.mapper.base.AreaMapper;
import com.itheima.wms.mapper.base.CarrierMapper;
import com.itheima.wms.mapper.base.OwnerMapper;
import com.itheima.wms.mapper.base.WarehouseMapper;
import com.itheima.wms.mapper.biz.*;
import com.itheima.wms.model.dto.biz.IncreaseDecreaseQueryDTO;
import com.itheima.wms.model.dto.biz.ReceiptDTO;
import com.itheima.wms.model.dto.biz.ReceiptQueryDTO;
import com.itheima.wms.model.entity.base.Area;
import com.itheima.wms.model.entity.base.Warehouse;
import com.itheima.wms.model.entity.biz.*;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.IncreaseDecreaseDetailVO;
import com.itheima.wms.model.vo.biz.ReceiptDetailVO;
import com.itheima.wms.model.vo.biz.ReceiptListSumVO;
import com.itheima.wms.service.base.CodeFactoryService;
import com.itheima.wms.service.biz.ReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 入库单
 * </p>
 */
@Slf4j
@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptMapper receiptMapper;
    @Autowired
    private OwnerMapper ownerMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private ReceivingMapper receivingMapper;
    @Autowired
    private GroundingMapper groundingMapper;
    @Autowired
    private CarrierMapper carrierMapper;
    @Autowired
    private ReceiptListMapper receiptListMapper;
    @Autowired
    private IncreaseDecreaseMapper increaseDecreaseMapper;
    @Autowired
    private CodeFactoryService codeFactoryService;


    @Override
    public PageBean<ReceiptDetailVO> pageDetail(ReceiptQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<ReceiptDetailVO> page = receiptMapper.pageDetail(data);
        return PageBean.builder(page);
    }


    @Override
    public Receipt save(ReceiptDTO data) {
        receiptMapper.save(data);
        return data;
    }


    @Override
    @Transactional
    public BatchVO receiving(List<Long> ids) {
        BatchVO.BatchVOBuilder batchVOBuilder = BatchVO.builder();
        ids.forEach(id -> {
            Receipt receipt = receiptMapper.getById(id);
            // 校验清单是否有未填写的
            List<ReceiptList> receiptLists = receiptListMapper.getByMasterId(id);
            receiptLists = receiptLists.stream().filter(item -> (null != item.getPlanNum() && 0 != item.getPlanNum())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(receiptLists)) {
                batchVOBuilder.error(receipt.getCode() + "未完成货品清单");
                return;
            }

            if (ReceiptStatus.NEW != receipt.getStatus()) {
                batchVOBuilder.error(receipt.getCode() + "状态不合法");
                return;
            }

            // 修改入库单状态为  收货中
            receipt.setStatus(ReceiptStatus.RECEIVING);
            receiptMapper.updateById(receipt);

            // 生成收货任务
            Receiving receiving = new Receiving();
            BeanUtils.copyProperties(receipt, receiving);
            receiving.setMasterId(receipt.getId());
            // 自动生成收货任务编号
            receiving.setCode(codeFactoryService.getNextCodeByType("SHRW"));
            receiving.setReceiptCode(receipt.getCode());
            receiving.setStatus(ReceivingStatus.NEW);  //初始状态 待分配
            receiving.setId(null);
            receivingMapper.insert(receiving);

            batchVOBuilder.result(receiving.getCode());
        });
        return batchVOBuilder.build();
    }

    /**
     * 入库单详情
     *
     * @param id
     * @return 基础信息
     */
    @Override
    public ReceiptDetailVO getDetailById(Long id) {
        ReceiptDetailVO receiptDetailVO = new ReceiptDetailVO();
        Receipt receipt = receiptMapper.getById(id);
        BeanUtils.copyProperties(receipt, receiptDetailVO);

        Receiving receiving = receivingMapper.getByMasterId(id);
        receiptDetailVO.setReceiving(receiving);

        Grounding grounding = groundingMapper.getByMasterId(id);
        receiptDetailVO.setGrounding(grounding);

        // 仓库名称
        Warehouse warehouse = warehouseMapper.getById(receipt.getWarehouseId());
        receiptDetailVO.setWarehouseName(warehouse == null ? String.valueOf(receipt.getWarehouseId()) : warehouse.getName());
        // 库区名称
        Area area = areaMapper.getById(receipt.getAreaId());
        receiptDetailVO.setAreaName(area == null ? String.valueOf(receipt.getAreaId()) : area.getName());

        // 货主信息
        receiptDetailVO.setOwner(ownerMapper.getById(receipt.getOwnerId()));
        // 货品信息详情
        ReceiptListSumVO receiptListSumVO = receiptListMapper.getReceiptListSumDetail(receipt.getId());
        receiptDetailVO.setGoodsTotal(receiptListSumVO.getGoodsTotal());
        receiptDetailVO.setVolumeTotal(receiptListSumVO.getVolumeTotal());

        // 损益
        if (null != grounding) {
            List<IncreaseDecreaseDetailVO> detailVOList = increaseDecreaseMapper.pageDetail(IncreaseDecreaseQueryDTO.builder().taskId(grounding.getId()).build());
            if (!CollectionUtils.isEmpty(detailVOList)) {
                receiptDetailVO.setIdMoney(detailVOList.stream().filter(item -> item.getIdMoney() != null).map(IncreaseDecrease::getIdMoney).reduce(BigDecimal.ZERO, BigDecimal::add));
                receiptDetailVO.setIdList(detailVOList);
            }
        }
        return receiptDetailVO;
    }


    @Override
    public Receipt getById(Long id) {
        return receiptMapper.getById(id);
    }

    @Override
    public void updateById(Receipt data) {
        data.setStatus(ReceiptStatus.CANCEL);
        receiptMapper.updateById(data);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> receiptMapper.removeById(id));
    }
}

