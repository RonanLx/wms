package com.itheima.wms.mapper.base;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.base.WarehouseQueryDTO;
import com.itheima.wms.model.entity.base.Warehouse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * 仓库
 * </p>
 */
@Mapper
public interface WarehouseMapper {

    Page<Warehouse> page(WarehouseQueryDTO data);

    void save(Warehouse data);

    @Select("select * from wms_warehouse where id=#{id}")
    Warehouse getById(Long id);

    void updateById(Warehouse data);

    @Delete("delete from wms_warehouse where id=#{id}")
    void removeById(Long id);

    @Select("select * from wms_warehouse where name=#{name}")
    Warehouse getByName(String name);

}
