/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.base.GoodsDTO;
import com.itheima.wms.model.dto.base.GoodsQueryDTO;
import com.itheima.wms.model.entity.base.Goods;
import com.itheima.wms.model.vo.base.GoodsDetailVO;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 货品管理
 * </p>
 */
public interface GoodsService {

    PageBean<GoodsDetailVO> pageDetail(GoodsQueryDTO data);

    PageBean<Goods> page(GoodsQueryDTO data);

    List<Goods> list(GoodsQueryDTO data);

    void save(GoodsDTO data);

    Goods getById(Long id);

    void updateById(GoodsDTO data);

    void removeByIds(List<Long> ids);
}
