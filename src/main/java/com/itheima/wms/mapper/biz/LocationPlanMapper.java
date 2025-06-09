package com.itheima.wms.mapper.biz;

import com.itheima.wms.model.entity.biz.LocationPlan;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 库位方案
 * </p>
 */
@Mapper
public interface LocationPlanMapper {

    @Select("select * from wms_location_plan where receipt_list_id=#{receiptListId}")
    List<LocationPlan> getByReceiptListId(Long receiptListId);

    void save(LocationPlan locationPlan);

    @Delete("delete from wms_location_plan where id=#{id}")
    void removeById(Long id);
}
