package com.itheima.wms.mapper.biz;


import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.biz.StatusRecordDTO;
import com.itheima.wms.model.entity.biz.StatusRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * 状态记录
 * </p>
 */
@Mapper
public interface StatusRecordMapper {

    Page<StatusRecord> page(Map data);

    StatusRecord getById(Long id);

    void save(StatusRecordDTO data);

    void updateById(StatusRecordDTO data);

    void removeById(Long id);
}
