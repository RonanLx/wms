/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.biz.ReceivingDTO;
import com.itheima.wms.model.dto.biz.ReceivingQueryDTO;
import com.itheima.wms.model.entity.biz.Receiving;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.ReceivingDetailVO;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 收货任务
 * </p>
 */
public interface ReceivingService {

    PageBean<ReceivingDetailVO> pageDetail(ReceivingQueryDTO data);

    PageBean<Receiving> page(ReceivingQueryDTO data);

    List<Receiving> list(ReceivingQueryDTO data);

    BatchVO complete(List<Long> ids);

    Receiving getById(Long id);

    void save(ReceivingDTO data);

    void updateById(ReceivingDTO data);

    void distribute(ReceivingDTO data);

    void removeByIds(List<Long> ids);
}
