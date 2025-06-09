package com.itheima.wms.service.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.biz.OutboundListBatchDTO;
import com.itheima.wms.model.dto.biz.OutboundListDTO;
import com.itheima.wms.model.dto.biz.OutboundListQueryDTO;
import com.itheima.wms.model.entity.biz.OutboundList;
import com.itheima.wms.model.vo.biz.OutboundListDetailVO;
import com.itheima.wms.model.vo.biz.OutboundListSumVO;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 出库清单
 * </p>
 */
public interface OutboundListService {

    PageBean<OutboundListDetailVO> pageDetail(OutboundListQueryDTO data);

    void saveBatch(OutboundListBatchDTO outboundListBatchDTO);

    OutboundListSumVO getSumByMasterId(Long masterId);

    void removeByIds(List<Long> ids);

    void updateById(OutboundListDTO data);

    List<Long> stockIds(Long masterId);

    void cancelBatch(List<OutboundList> outboundListEntities);

    OutboundList getById(Long id);

    void save(OutboundListDTO data);

    void removeByMasterId(Long masterId);
}
