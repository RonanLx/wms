/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.mapper.biz;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.biz.OutboundQueryDTO;
import com.itheima.wms.model.entity.biz.Outbound;
import com.itheima.wms.model.vo.biz.OutboundDetailVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * 出库单
 * </p>
 */
@Mapper
public interface OutboundMapper {

    Page<OutboundDetailVO> pageDetail(OutboundQueryDTO data);

    void save(Outbound data);

    @Select("select * from wms_outbound where id=#{id}")
    Outbound getById(Long id);

    void updateById(Outbound data);

    @Delete("delete from wms_outbound where id=#{id}")
    void removeById(Long id);
}
