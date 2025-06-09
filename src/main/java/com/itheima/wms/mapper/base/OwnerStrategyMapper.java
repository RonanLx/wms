package com.itheima.wms.mapper.base;

import com.github.pagehelper.Page;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.base.OwnerStrategyDTO;
import com.itheima.wms.model.entity.base.OwnerStrategy;
import com.itheima.wms.model.vo.base.OwnerStrategyDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * 货主-策略关联表
 * </p>
 */
@Mapper
public interface OwnerStrategyMapper {

    Page<OwnerStrategyDetailVO> selectOwnerStrategyDetail(@Param("params") Map data);

    void removeById(Long id);

    void updateById(OwnerStrategyDTO data);

    void save(OwnerStrategyDTO data);

    OwnerStrategy getById(Long id);

    List<OwnerStrategy> list(Map data);

    PageBean<OwnerStrategy> page(Map data);
}
