/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.biz.OutboundDTO;
import com.itheima.wms.model.dto.biz.OutboundQueryDTO;
import com.itheima.wms.model.entity.biz.Outbound;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.OutboundDetailVO;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 出库单
 * </p>
 */
public interface OutboundService {

    PageBean<OutboundDetailVO> pageDetail(OutboundQueryDTO data);

    void save(OutboundDTO data);

    BatchVO picking(List<Long> ids);

    OutboundDetailVO getDetail(Long id);

    void cancel(OutboundDTO data);

    Outbound getById(Long id);

    void updateById(OutboundDTO data);

    void removeByIds(List<Long> ids);
}
