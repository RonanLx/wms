/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.biz.impl;

import com.github.pagehelper.Page;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.mapper.biz.StatusRecordMapper;
import com.itheima.wms.model.dto.biz.StatusRecordDTO;
import com.itheima.wms.model.entity.Entity;
import com.itheima.wms.model.entity.biz.StatusRecord;
import com.itheima.wms.service.biz.StatusRecordService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 业务实现类
 * 状态记录
 * </p>
 */
@Slf4j
@Service
public class StatusRecordServiceImpl implements StatusRecordService {

    @Autowired
    private StatusRecordMapper statusRecordMapper;

    @SneakyThrows
    @Override
    public void saveStatus(Entity entity) {
        /*Class<Entity> mClass = (Class<Entity>) entity.getClass();
        Method idMethod = mClass.getMethod("getId");
        Long id = (Long) idMethod.invoke(entity);
        Method statusMethod = mClass.getMethod("getStatus");
        Integer status = (Integer) statusMethod.invoke(entity);

        StatusRecordEntity statusRecordEntity = new StatusRecordEntity();
        statusRecordEntity.setBusinessId(id);
        statusRecordEntity.setBusinessModel(mClass.getName());
        statusRecordEntity.setBusinessType(mClass.getSimpleName());
        statusRecordEntity.setBusinessStatus(status.toString());
        statusRecordEntity.setHappenTime(LocalDateTime.now());
        statusRecordMapper.save(statusRecordEntity);*/
    }

    @Override
    public PageBean<StatusRecord> page(Map data) {
        Page<StatusRecord> page = statusRecordMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public List<StatusRecord> list(Map data) {
        return statusRecordMapper.page(data);
    }

    @Override
    public StatusRecord getById(Long id) {
        return statusRecordMapper.getById(id);
    }

    @Override
    public void save(StatusRecordDTO data) {
        statusRecordMapper.save(data);
    }

    @Override
    public void updateById(StatusRecordDTO data) {
        statusRecordMapper.updateById(data);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> statusRecordMapper.removeById(id));
    }

}

