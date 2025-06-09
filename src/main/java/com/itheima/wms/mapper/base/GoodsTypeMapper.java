package com.itheima.wms.mapper.base;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.base.GoodsTypeDTO;
import com.itheima.wms.model.dto.base.GoodsTypeQueryDTO;
import com.itheima.wms.model.entity.base.GoodsType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * 货品类型管理
 * </p>
 */
@Mapper
public interface GoodsTypeMapper {

    Page<GoodsType> page(GoodsTypeQueryDTO data);

    void save(GoodsTypeDTO data);

    @Select("select * from wms_goods_type where id=#{id}")
    GoodsType getById(Long id);

    void updateById(GoodsType data);

    @Delete("delete from wms_goods_type where id=#{id}")
    void removeById(Long id);
}
