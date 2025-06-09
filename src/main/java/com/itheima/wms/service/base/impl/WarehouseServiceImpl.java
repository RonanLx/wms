package com.itheima.wms.service.base.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.mapper.base.OwnerLocationMapper;
import com.itheima.wms.mapper.base.WarehouseMapper;
import com.itheima.wms.model.dto.base.WarehouseDTO;
import com.itheima.wms.model.dto.base.WarehouseQueryDTO;
import com.itheima.wms.model.entity.base.OwnerLocation;
import com.itheima.wms.model.entity.base.Warehouse;
import com.itheima.wms.service.base.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 仓库
 * </p>
 */
@Slf4j
@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private OwnerLocationMapper ownerLocationMapper;

    @Override
    public PageBean<Warehouse> page(WarehouseQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<Warehouse> page = warehouseMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public List<Warehouse> list(WarehouseQueryDTO data) {
        List<Warehouse> warehouseList = warehouseMapper.page(data);

        //如果查询条件有OwnerId，则过滤掉不属于该货主的仓库
        if (data.getOwnerId() != null) {
            List<OwnerLocation> ownerLocationList = ownerLocationMapper.getByOwnerId(data.getOwnerId());
            Set<Long> warehouseIds = ownerLocationList.stream().map(ownerLocation -> ownerLocation.getWarehouseId()).collect(Collectors.toSet());
            warehouseList = warehouseList.stream().filter(warehouse -> warehouseIds.contains(warehouse.getId())).collect(Collectors.toList());
        }
        return warehouseList;
    }

    @Override
    public void save(WarehouseDTO data) {
        //库区数量默认0
        data.setIncludedNum(0);
        warehouseMapper.save(data);
    }

    @Override
    public Warehouse getById(Long id) {
        return warehouseMapper.getById(id);
    }

    @Override
    public void updateById(WarehouseDTO data) {
        warehouseMapper.updateById(data);
    }

    @Override
    public Warehouse getByName(String warehouse) {
        return warehouseMapper.getByName(warehouse);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> {
            warehouseMapper.removeById(id);
        });
    }

}

