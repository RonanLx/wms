package com.itheima.wms.service.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.base.AreaDTO;
import com.itheima.wms.model.dto.base.AreaQueryDTO;
import com.itheima.wms.model.entity.base.Area;
import com.itheima.wms.model.vo.base.AreaDetailVO;
import com.itheima.wms.model.vo.base.AreaLocationTreeVO;
import com.itheima.wms.model.vo.base.AreaOverviewVO;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 库区
 * </p>
 */
public interface AreaService {

    PageBean<AreaDetailVO> pageDetail(AreaQueryDTO data);

    PageBean<Area> page(AreaQueryDTO data);

    List<Area> list(AreaQueryDTO data);

    AreaOverviewVO areaOverview(Long id);

    void save(AreaDTO entity);

    Area getById(Long id);

    void updateById(AreaDTO data);

    void removeByIds(List<Long> ids);

    Area getByName(String area);

    List<AreaLocationTreeVO> listAreaLocationTree(Long warehouseId);
}
