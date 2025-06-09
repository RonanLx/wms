package com.itheima.wms.service.base.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.mapper.base.CarrierMapper;
import com.itheima.wms.model.dto.base.CarrierDTO;
import com.itheima.wms.model.dto.base.CarrierQueryDTO;
import com.itheima.wms.model.entity.base.Carrier;
import com.itheima.wms.service.base.CarrierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 业务实现类
 * 承运商
 * </p>
 */
@Slf4j
@Service
public class CarrierServiceImpl implements CarrierService {

    @Autowired
    private CarrierMapper carrierMapper;

    @Override
    public PageBean<Carrier> page(CarrierQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<Carrier> page = carrierMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public List<Carrier> list(CarrierQueryDTO data) {
        return carrierMapper.page(data);
    }

    @Override
    public Carrier getById(Long id) {
        return carrierMapper.getById(id);
    }

    @Override
    public void save(CarrierDTO data) {
        carrierMapper.save(data);
    }

    @Override
    public void updateById(CarrierDTO data) {
        carrierMapper.updateById(data);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> {
            carrierMapper.removeById(id);
        });
    }
}

