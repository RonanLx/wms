/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.base.LocationDTO;
import com.itheima.wms.model.dto.base.LocationQueryDTO;
import com.itheima.wms.model.entity.base.Location;
import com.itheima.wms.model.vo.base.LocationDetailVO;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 库位
 * </p>
 */
public interface LocationService {

    PageBean<LocationDetailVO> pageDetail(LocationQueryDTO data);

    PageBean<Location> page(LocationQueryDTO data);

    List<Location> list(LocationQueryDTO data);

    List<LocationDetailVO> listDetail(LocationQueryDTO data);

    void save(Location entity);

    Location getById(Long id);

    void updateById(LocationDTO data);

    void removeByIds(List<Long> ids);
}
