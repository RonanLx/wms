/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.mapper.base;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.base.GoodsQueryDTO;
import com.itheima.wms.model.entity.base.Goods;
import com.itheima.wms.model.vo.base.GoodsDetailVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * 货品管理
 * </p>
 */
@Mapper
public interface GoodsMapper {

    Page<GoodsDetailVO> pageDetail(GoodsQueryDTO data);

    Page<Goods> page(GoodsQueryDTO data);

    void save(Goods data);

    @Select("select * from wms_goods where id=#{id}")
    Goods getById(Long id);

    void updateById(Goods data);

    @Delete("delete from wms_goods where id=#{id}")
    void removeById(Long id);
}
