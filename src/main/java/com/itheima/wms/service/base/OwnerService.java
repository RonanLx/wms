package com.itheima.wms.service.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.base.OwnerDTO;
import com.itheima.wms.model.dto.base.OwnerQueryDTO;
import com.itheima.wms.model.entity.base.Owner;
import com.itheima.wms.model.vo.base.OwnerDetailVO;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 货主管理
 * </p>
 */
public interface OwnerService {

    PageBean<Owner> page(OwnerQueryDTO data);

    List<Owner> list(OwnerQueryDTO data);

    Owner getById(Long id);

    OwnerDetailVO getDetail(Long id);

    void save(OwnerDTO data);

    void updateById(OwnerDTO data);

    void removeByIds(List<Long> ids);
}
