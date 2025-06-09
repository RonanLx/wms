/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.mapper.base;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.base.LocationQueryDTO;
import com.itheima.wms.model.entity.base.Location;
import com.itheima.wms.model.vo.base.LocationDetailVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 库位
 * </p>
 */
@Mapper
public interface LocationMapper {

    Page<LocationDetailVO> pageDetail(LocationQueryDTO data);

    Page<Location> page(LocationQueryDTO data);

    @Select("select * from wms_location where area_id=#{areaId}")
    List<Location> getByAreaId(Long areaId);

    void save(Location entity);

    @Select("select * from wms_location where id=#{id}")
    Location getById(Long id);

    void updateById(Location location);

    @Update("update wms_location set owner_id=#{ownerId} where id=#{id}")
    void updateOwnerIdById(Long id, Long ownerId);

    @Delete("delete from wms_location where id=#{id}")
    void removeById(Long id);

    @Select("select count(*) from wms_location where area_id=#{areaId}")
    int countByAreaId(Long areaId);

}
