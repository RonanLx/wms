/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.biz.PickingDTO;
import com.itheima.wms.model.entity.biz.Picking;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.PickingDetailVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 拣货任务
 * </p>
 */
public interface PickingService {

    PageBean<PickingDetailVO> pageDetail(Map data);

    Picking getById(Long id);

    void save(PickingDTO data);

    void updateById(Picking data);

    BatchVO complete(List<Long> ids);

    void removeByIds(List<Long> ids);
}
