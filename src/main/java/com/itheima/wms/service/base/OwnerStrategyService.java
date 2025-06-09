package com.itheima.wms.service.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.base.OwnerStrategyDTO;
import com.itheima.wms.model.entity.base.OwnerStrategy;
import com.itheima.wms.model.vo.base.OwnerStrategyDetailVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 货主-策略关联表
 * </p>
 */
public interface OwnerStrategyService {

    PageBean<OwnerStrategyDetailVO> pageDetail(Map data);

    void removeByIds(List<Long> ids);

    void updateById(OwnerStrategyDTO data);

    void save(OwnerStrategyDTO data);

    OwnerStrategy getById(Long id);

    List<OwnerStrategy> list(Map data);

    PageBean<OwnerStrategy> page(Map data);
}
