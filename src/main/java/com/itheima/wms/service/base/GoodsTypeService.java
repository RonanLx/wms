/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.base.GoodsTypeDTO;
import com.itheima.wms.model.dto.base.GoodsTypeQueryDTO;
import com.itheima.wms.model.entity.base.GoodsType;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 货品类型管理
 * </p>
 */
public interface GoodsTypeService {

    PageBean<GoodsType> page(GoodsTypeQueryDTO data);

    List<GoodsType> list(GoodsTypeQueryDTO map);

    void save(GoodsTypeDTO data);

    GoodsType getById(Long id);

    void updateById(GoodsTypeDTO data);

    void removeByIds(List<Long> ids);
}
