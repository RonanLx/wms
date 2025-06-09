/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.mapper.base;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.base.CarrierDTO;
import com.itheima.wms.model.dto.base.CarrierQueryDTO;
import com.itheima.wms.model.entity.base.Carrier;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * 承运商
 * </p>
 */
@Mapper
public interface CarrierMapper {

    Page<Carrier> page(CarrierQueryDTO data);

    Carrier getById(Long id);

    void save(CarrierDTO data);

    void updateById(CarrierDTO data);

    void removeById(Long id);
}
