package com.itheima.wms.service.base.impl;

import com.github.pagehelper.Page;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.mapper.base.OwnerStrategyMapper;
import com.itheima.wms.model.dto.base.OwnerStrategyDTO;
import com.itheima.wms.model.entity.base.OwnerStrategy;
import com.itheima.wms.model.vo.base.OwnerStrategyDetailVO;
import com.itheima.wms.service.base.OwnerStrategyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 业务实现类
 * 货主-策略关联表
 * </p>
 */
@Slf4j
@Service
public class OwnerStrategyServiceImpl implements OwnerStrategyService {

    @Autowired
    private OwnerStrategyMapper ownerStrategyMapper;


    @Override
    public PageBean<OwnerStrategyDetailVO> pageDetail(Map data) {
        Page<OwnerStrategyDetailVO> page = ownerStrategyMapper.selectOwnerStrategyDetail(data);
        return PageBean.builder(page);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> {
            ownerStrategyMapper.removeById(id);
        });
    }

    @Override
    public void updateById(OwnerStrategyDTO data) {
        ownerStrategyMapper.updateById(data);
    }

    @Override
    public void save(OwnerStrategyDTO data) {
        ownerStrategyMapper.save(data);
    }

    @Override
    public OwnerStrategy getById(Long id) {
        return ownerStrategyMapper.getById(id);
    }

    @Override
    public List<OwnerStrategy> list(Map data) {
        return ownerStrategyMapper.list(data);
    }

    @Override
    public PageBean<OwnerStrategy> page(Map data) {
        return ownerStrategyMapper.page(data);
    }
}

