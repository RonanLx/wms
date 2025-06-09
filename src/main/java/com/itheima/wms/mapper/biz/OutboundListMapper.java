/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.mapper.biz;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.biz.OutboundListQueryDTO;
import com.itheima.wms.model.entity.biz.OutboundList;
import com.itheima.wms.model.vo.biz.OutboundListDetailVO;
import com.itheima.wms.model.vo.biz.OutboundListSumVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 * Mapper 接口
 * 出库清单
 * </p>
 */
@Mapper
public interface OutboundListMapper {

    Page<OutboundListDetailVO> pageDetail(OutboundListQueryDTO data);

    @Select("select * from wms_outbound_list where master_id=#{masterId}")
    List<OutboundList> getByMasterId(Long masterId);

    OutboundListSumVO sumOutboundListDetail(Long masterId);

    void save(OutboundList entity);

    @Select("select * from wms_outbound_list where id=#{id}")
    OutboundList getById(Long id);

    void updateById(OutboundList entity);

    void removeById(Long id);

    void removeByMasterId(Long masterId);
}
