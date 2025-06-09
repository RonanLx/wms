package com.itheima.wms.mapper.base;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.base.OwnerLocationQueryDTO;
import com.itheima.wms.model.entity.base.OwnerLocation;
import com.itheima.wms.model.vo.base.OwnerLocationDetailVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 货主-库位关联表
 * </p>
 */
@Mapper
public interface OwnerLocationMapper {

    Page<OwnerLocationDetailVO> pageDetail(OwnerLocationQueryDTO data);

    Page<OwnerLocation> page(OwnerLocationQueryDTO data);

    @Select("select * from wms_owner_location where id=#{id}")
    OwnerLocation getById(Long id);

    @Select("select * from wms_owner_location where owner_id=#{ownerId}")
    List<OwnerLocation> getByOwnerId(Long ownerId);

    @Select("select * from wms_owner_location where area_id=#{areaId}")
    List<OwnerLocation> getByAreaId(Long areaId);

    void save(OwnerLocation data);

    @Delete("delete from wms_owner_location where id=#{id}")
    void removeById(Long id);

    void updateById(OwnerLocation data);
}
