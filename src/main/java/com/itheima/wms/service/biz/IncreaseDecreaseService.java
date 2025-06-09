/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.biz.IncreaseDecreaseDTO;
import com.itheima.wms.model.dto.biz.IncreaseDecreaseQueryDTO;
import com.itheima.wms.model.entity.biz.IncreaseDecrease;
import com.itheima.wms.model.vo.biz.IncreaseDecreaseDetailVO;
import com.itheima.wms.model.vo.biz.IncreaseDecreaseSumVO;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 损益单
 * </p>
 */
public interface IncreaseDecreaseService {

    PageBean<IncreaseDecreaseDetailVO> pageDetail(IncreaseDecreaseQueryDTO data);

    List<IncreaseDecreaseDetailVO> listDetail(IncreaseDecreaseQueryDTO data);

    IncreaseDecreaseSumVO sum(IncreaseDecreaseQueryDTO data);

    PageBean<IncreaseDecrease> page(IncreaseDecreaseQueryDTO data);

    List<IncreaseDecrease> list(IncreaseDecreaseQueryDTO data);

    void change(IncreaseDecreaseDTO data);

    void cancel(IncreaseDecreaseDTO data);

    IncreaseDecrease getById(Long id);

    void save(IncreaseDecreaseDTO data);

    void updateById(IncreaseDecreaseDTO data);

    void removeByIds(List<Long> ids);
}
