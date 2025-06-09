package com.itheima.wms.mapper.base;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.base.OwnerQueryDTO;
import com.itheima.wms.model.entity.base.Owner;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * 货主
 * </p>
 */
@Mapper
public interface OwnerMapper {

    Page<Owner> page(OwnerQueryDTO data);

    void save(Owner data);

    @Select("select * from wms_owner where id=#{id}")
    Owner getById(Long id);

    void updateById(Owner data);

    @Delete("delete from wms_owner where id=#{id}")
    void removeById(Long id);
}
