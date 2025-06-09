/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.base.OwnerLocationBatchDTO;
import com.itheima.wms.model.dto.base.OwnerLocationDTO;
import com.itheima.wms.model.dto.base.OwnerLocationQueryDTO;
import com.itheima.wms.model.entity.base.OwnerLocation;
import com.itheima.wms.model.vo.base.OwnerLocationDetailVO;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 货主-库位关联表
 * </p>
 */
public interface OwnerLocationService {

    PageBean<OwnerLocation> page(OwnerLocationQueryDTO data);

    PageBean<OwnerLocationDetailVO> pageDetail(OwnerLocationQueryDTO data);

    List<OwnerLocation> list(OwnerLocationQueryDTO data);

    void removeByIds(List<Long> ids);

    void save(OwnerLocationDTO data);

    void saveBatch(OwnerLocationBatchDTO data);

    OwnerLocation getById(Long id);

    void updateById(OwnerLocationDTO data);
}
