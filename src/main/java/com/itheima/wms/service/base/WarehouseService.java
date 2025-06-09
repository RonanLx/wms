/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.base.WarehouseDTO;
import com.itheima.wms.model.dto.base.WarehouseQueryDTO;
import com.itheima.wms.model.entity.base.Warehouse;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 仓库
 * </p>
 */
public interface WarehouseService {

    //分页条件查询
    PageBean<Warehouse> page(WarehouseQueryDTO data);

    List<Warehouse> list(WarehouseQueryDTO data);

    void save(WarehouseDTO data);

    Warehouse getById(Long id);

    void updateById(WarehouseDTO data);

    Warehouse getByName(String warehouse);

    void removeByIds(List<Long> ids);
}
