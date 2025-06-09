/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.base.CarrierDTO;
import com.itheima.wms.model.dto.base.CarrierQueryDTO;
import com.itheima.wms.model.entity.base.Carrier;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 承运商
 * </p>
 */
public interface CarrierService {

    PageBean<Carrier> page(CarrierQueryDTO data);

    List<Carrier> list(CarrierQueryDTO data);

    Carrier getById(Long id);

    void save(CarrierDTO data);

    void updateById(CarrierDTO data);

    void removeByIds(List<Long> ids);
}
