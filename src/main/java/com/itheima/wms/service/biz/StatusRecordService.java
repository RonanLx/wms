package com.itheima.wms.service.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.biz.StatusRecordDTO;
import com.itheima.wms.model.entity.Entity;
import com.itheima.wms.model.entity.biz.StatusRecord;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 状态记录
 * </p>
 */
public interface StatusRecordService {

    void saveStatus(Entity entity);

    PageBean<StatusRecord> page(Map data);

    List<StatusRecord> list(Map data);

    StatusRecord getById(Long id);

    void save(StatusRecordDTO data);

    void updateById(StatusRecordDTO data);

    void removeByIds(List<Long> ids);
}
