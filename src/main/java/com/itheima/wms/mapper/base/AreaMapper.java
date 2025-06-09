package com.itheima.wms.mapper.base;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.base.AreaQueryDTO;
import com.itheima.wms.model.entity.base.Area;
import com.itheima.wms.model.vo.base.AreaDetailVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * 库区
 * </p>
 */
@Mapper
public interface AreaMapper {

    Page<AreaDetailVO> pageDetail(AreaQueryDTO data);

    Page<Area> page(AreaQueryDTO data);

    void save(Area entity);

    @Select("select * from wms_area where id=#{id}")
    Area getById(Long id);

    void updateById(Area area);

    @Select("select * from wms_area where warehouse_id=#{warehouseId}")
    List<Area> getByWarehouseId(Long warehouseId);

    List<Map> areaUseStatus(Map data);

    @Delete("delete from wms_area where id=#{id}")
    void removeById(Long id);

    @Select("select * from wms_area where name=#{name}")
    Area getByName(String name);
}
