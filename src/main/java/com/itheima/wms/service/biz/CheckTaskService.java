/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.biz.CheckTaskDTO;
import com.itheima.wms.model.entity.biz.CheckTask;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.CheckTaskDetailVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 盘点任务
 * </p>
 */
public interface CheckTaskService {

    BatchVO complete(List<Long> ids);

    BatchVO task(List<Long> ids);

    String task(Long id);

    BatchVO increaseDecrease(List<Long> ids);

    PageBean<CheckTaskDetailVO> pageDetail(Map data);

    PageBean<CheckTask> page(Map data);

    List<CheckTask> list(Map data);

    CheckTask getById(Long id);

    void save(CheckTaskDTO data);

    void updateById(CheckTask data);

    void removeByIds(List<Long> ids);
}
