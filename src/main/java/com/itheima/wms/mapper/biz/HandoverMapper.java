package com.itheima.wms.mapper.biz;

import com.github.pagehelper.Page;
import com.itheima.wms.model.entity.biz.Handover;
import com.itheima.wms.model.vo.biz.HandoverDetailVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * 交接任务
 * </p>
 */
@Mapper
public interface HandoverMapper {

    Page<HandoverDetailVO> pageDetail(@Param("params") Map data);

    @Select("select * from wms_handover where master_id=#{masterId}")
    Handover getByMasterId(Long masterId);

    void save(Handover handover);

    @Select("select * from wms_handover where id=#{id}")
    Handover getById(Long id);

    void updateById(Handover handover);

    @Delete("delete from wms_handover where id=#{id}")
    void removeById(Long id);
}
