/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.mapper.biz;

import com.github.pagehelper.Page;
import com.itheima.wms.model.entity.biz.CheckTask;
import com.itheima.wms.model.vo.biz.CheckTaskDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * 盘点任务
 * </p>
 */
@Mapper
public interface CheckTaskMapper {

    Page<CheckTaskDetailVO> selectCheckTaskDetail(@Param("params") Map data);

    int countByMasterId(Long id);

    void insert(CheckTask checkTask);

    List<CheckTask> getByMasterId(Long id);

    void update(CheckTask checkTask);

    CheckTask getById(Long id);

    void updateById(CheckTask checkTask);

    Page<CheckTask> page(Map data);

    void removeById(Long id);
}
