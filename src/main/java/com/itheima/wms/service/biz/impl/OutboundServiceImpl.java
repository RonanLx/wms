/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.constants.OutboundStatus;
import com.itheima.wms.common.constants.PickingStatus;
import com.itheima.wms.mapper.base.AreaMapper;
import com.itheima.wms.mapper.base.CarrierMapper;
import com.itheima.wms.mapper.base.OwnerMapper;
import com.itheima.wms.mapper.base.WarehouseMapper;
import com.itheima.wms.mapper.biz.*;
import com.itheima.wms.model.dto.biz.IncreaseDecreaseQueryDTO;
import com.itheima.wms.model.dto.biz.OutboundDTO;
import com.itheima.wms.model.dto.biz.OutboundQueryDTO;
import com.itheima.wms.model.entity.base.Area;
import com.itheima.wms.model.entity.base.Warehouse;
import com.itheima.wms.model.entity.biz.*;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.IncreaseDecreaseDetailVO;
import com.itheima.wms.model.vo.biz.OutboundDetailVO;
import com.itheima.wms.model.vo.biz.OutboundListSumVO;
import com.itheima.wms.service.base.CodeFactoryService;
import com.itheima.wms.service.biz.OutboundListService;
import com.itheima.wms.service.biz.OutboundService;
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
 * 出库单
 * </p>
 */
@Slf4j
@Service
public class OutboundServiceImpl implements OutboundService {

    @Autowired
    private OutboundMapper outboundMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private PickingMapper pickingMapper;
    @Autowired
    private HandoverMapper handoverMapper;
    @Autowired
    private OwnerMapper ownerMapper;
    @Autowired
    private CarrierMapper carrierMapper;
    @Autowired
    private OutboundListMapper outboundListMapper;
    @Autowired
    private OutboundListService outboundListService;
    @Autowired
    private IncreaseDecreaseMapper increaseDecreaseMapper;
    @Autowired
    private CodeFactoryService codeFactoryService;


    @Override
    public PageBean<OutboundDetailVO> pageDetail(OutboundQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<OutboundDetailVO> page = outboundMapper.pageDetail(data);
        return PageBean.builder(page);
    }


    @Override
    public OutboundDetailVO getDetail(Long id) {
        OutboundDetailVO outboundDetailVO = new OutboundDetailVO();
        Outbound outbound = outboundMapper.getById(id);
        BeanUtils.copyProperties(outbound, outboundDetailVO);

        Picking picking = pickingMapper.getByMasterId(id);
        outboundDetailVO.setPicking(picking);

        Handover handover = handoverMapper.getByMasterId(id);
        outboundDetailVO.setHandover(handover);

        // 构建状态时间
        List<String> timeArray = List.of();
        outboundDetailVO.setTimeArray(timeArray);

        // 仓库名称
        Warehouse warehouse = warehouseMapper.getById(outbound.getWarehouseId());
        outboundDetailVO.setWarehouseName(warehouse == null ? String.valueOf(outbound.getWarehouseId()) : warehouse.getName());
        // 库区名称
        Area area = areaMapper.getById(outbound.getAreaId());
        outboundDetailVO.setAreaName(area == null ? String.valueOf(outbound.getAreaId()) : area.getName());

        // 货主
        outboundDetailVO.setOwner(ownerMapper.getById(outbound.getOwnerId()));
        outboundDetailVO.setOwnerName(outboundDetailVO.getOwner().getName());
        outboundDetailVO.setOwnerCode(outboundDetailVO.getOwner().getCode());

        // 承运商
        outboundDetailVO.setCarrierName(carrierMapper.getById(outbound.getCarrierId()).getName());

        // 货品信息详情
        OutboundListSumVO outboundListSumVO = outboundListMapper.sumOutboundListDetail(outboundDetailVO.getId());
        outboundDetailVO.setGoodsTotal(outboundListSumVO.getGoodsTotal());
        outboundDetailVO.setVolumeTotal(outboundListSumVO.getVolumeTotal());

        // 损益
        if (null != picking) {
            List<IncreaseDecreaseDetailVO> increaseDecreaseEntities = increaseDecreaseMapper.pageDetail(IncreaseDecreaseQueryDTO.builder().taskId(picking.getId()).build());
            if (!CollectionUtils.isEmpty(increaseDecreaseEntities)) {
                outboundDetailVO.setIdMoney(increaseDecreaseEntities.stream().filter(item -> item.getIdMoney() != null).map(IncreaseDecrease::getIdMoney).reduce(BigDecimal.ZERO, BigDecimal::add));
                outboundDetailVO.setIdList(increaseDecreaseEntities);
            }
        }
        return outboundDetailVO;
    }


    @Override
    public void save(OutboundDTO data) {
        outboundMapper.save(data);
    }

    @Override
    @Transactional
    public BatchVO picking(List<Long> ids) {
        BatchVO.BatchVOBuilder batchVOBuilder = BatchVO.builder();

        ids.forEach(id -> {
            Outbound outbound = outboundMapper.getById(id);
            // 校验清单是否有未填写的
            List<OutboundList> outboundListEntities = outboundListMapper.getByMasterId(id);
            outboundListEntities = outboundListEntities.stream().filter(item -> (null != item.getOutboundNum() && 0 != item.getOutboundNum())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(outboundListEntities)) {
                log.info("id:{} 清单未填写完成", id);
                batchVOBuilder.error(outbound.getCode() + "未完成出库清单");
                return;
            }

            if (OutboundStatus.NEW != outbound.getStatus()) {
                log.info("id:{} 状态不正确", id);
                batchVOBuilder.error(outbound.getCode() + "状态不合法");
                return;
            }

            // 修改出库单状态为  拣货中
            outbound.setStatus(OutboundStatus.PICKING);
            outboundMapper.updateById(outbound);

            // 生成拣货任务
            Picking picking = new Picking();
            BeanUtils.copyProperties(outbound, picking);
            picking.setMasterId(outbound.getId());
            picking.setCode(codeFactoryService.getNextCodeByType("JHRW"));
            picking.setOutboundCode(outbound.getCode());
            picking.setPickingNum(outbound.getGoodsNum());
            picking.setStatus(PickingStatus.NEW);  //初始状态
            picking.setId(null);
            pickingMapper.save(picking);

            batchVOBuilder.result(picking.getCode());
        });

        return batchVOBuilder.build();
    }


    @Override
    @Transactional
    public void cancel(OutboundDTO data) {
        Outbound outbound = outboundMapper.getById(data.getId());
        if (null == outbound) {
            throw new RuntimeException("出库单未找到");
        }
        if (outbound.getStatus() != OutboundStatus.NEW) {
            throw new RuntimeException("只能取消新建的出库单");
        }
        outbound.setStatus(OutboundStatus.CANCEL);
        outboundMapper.updateById(outbound);

        List<OutboundList> outboundListEntities = outboundListMapper.getByMasterId(outbound.getId());
        log.info("取消出库单明细：{}", outboundListEntities);
        outboundListService.cancelBatch(outboundListEntities);
    }


    @Override
    public Outbound getById(Long id) {
        return outboundMapper.getById(id);
    }

    @Override
    public void updateById(OutboundDTO data) {
        outboundMapper.updateById(data);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> outboundMapper.removeById(id));

    }
}

