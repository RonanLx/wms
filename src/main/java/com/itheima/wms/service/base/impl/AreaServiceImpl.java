/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.base.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.constants.LocationStatus;
import com.itheima.wms.common.constants.LocationUseStatus;
import com.itheima.wms.mapper.base.AreaMapper;
import com.itheima.wms.mapper.base.LocationMapper;
import com.itheima.wms.mapper.base.OwnerLocationMapper;
import com.itheima.wms.mapper.base.WarehouseMapper;
import com.itheima.wms.mapper.biz.StockMapper;
import com.itheima.wms.model.dto.base.AreaDTO;
import com.itheima.wms.model.dto.base.AreaQueryDTO;
import com.itheima.wms.model.entity.base.Area;
import com.itheima.wms.model.entity.base.Location;
import com.itheima.wms.model.entity.base.OwnerLocation;
import com.itheima.wms.model.entity.base.Warehouse;
import com.itheima.wms.model.entity.biz.Stock;
import com.itheima.wms.model.vo.base.AreaDetailVO;
import com.itheima.wms.model.vo.base.AreaLocationTreeVO;
import com.itheima.wms.model.vo.base.AreaOverviewVO;
import com.itheima.wms.service.base.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 库区
 * </p>
 */
@Slf4j
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private OwnerLocationMapper ownerLocationMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private StockMapper stockMapper;


    @Override
    public PageBean<AreaDetailVO> pageDetail(AreaQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<AreaDetailVO> page = areaMapper.pageDetail(data);
        return PageBean.builder(page);
    }


    @Override
    public List<Area> list(AreaQueryDTO data) {
        List<Area> areaList = areaMapper.page(data);

        //如果查询条件有OwnerId，则过滤掉不属于该货主的库区
        if (data.getOwnerId() != null) {
            List<OwnerLocation> ownerLocationList = ownerLocationMapper.getByOwnerId(data.getOwnerId());
            Set<Long> areaIds = ownerLocationList.stream().map(ownerLocation -> ownerLocation.getAreaId()).collect(Collectors.toSet());
            areaList = areaList.stream().filter(area -> areaIds.contains(area.getId())).collect(Collectors.toList());
        }
        return areaList;
    }

    @Override
    public PageBean<Area> page(AreaQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<Area> page = areaMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    @Transactional
    public void save(AreaDTO area) {
        areaMapper.save(area);
        //更新库区数量
        Integer count = areaMapper.getByWarehouseId(area.getWarehouseId()).size();
        warehouseMapper.updateById(Warehouse.builder().includedNum(count).id(area.getWarehouseId()).build());
    }

    @Override
    public Area getById(Long id) {
        return areaMapper.getById(id);
    }

    @Override
    public void updateById(AreaDTO data) {
        areaMapper.updateById(data);
        //更新库区数量
        Integer count = areaMapper.getByWarehouseId(data.getWarehouseId()).size();
        warehouseMapper.updateById(Warehouse.builder().includedNum(count).id(data.getWarehouseId()).build());
    }

    @Override
    @Transactional
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> {
            Area area = areaMapper.getById(id);
            areaMapper.removeById(id);
            //更新库区数量
            Integer count = areaMapper.getByWarehouseId(area.getWarehouseId()).size();
            warehouseMapper.updateById(Warehouse.builder().includedNum(count).id(area.getWarehouseId()).build());
        });
    }

    @Override
    public AreaOverviewVO areaOverview(Long id) {
        AreaOverviewVO areaOverviewVO = new AreaOverviewVO();
        //库区总览：库存总计、停用库位、占用库位、空闲库位
        List<Location> locations = locationMapper.getByAreaId(id);

        long stop = locations.stream().filter(location -> Objects.equals(location.getStatus(), LocationStatus.DISABLE)).count();
        long use = locations.stream().filter(location -> Objects.equals(location.getStatus(), LocationStatus.ENABLE) && !Objects.equals(location.getUseStatus(), LocationUseStatus.FREE)).count();
        long free = locations.stream().filter(location -> Objects.equals(location.getStatus(), LocationStatus.ENABLE) && Objects.equals(location.getUseStatus(), LocationUseStatus.FREE)).count();
        long total = stockMapper.getByAreaId(id).stream().mapToInt(Stock::getTotal).sum();

        areaOverviewVO.setTotal((int) total);//库位总计
        areaOverviewVO.setStop((int) stop);//停用库位
        areaOverviewVO.setUse((int) use);//占用库位
        areaOverviewVO.setFree((int) free);//空闲库位

        return areaOverviewVO;
    }

    @Override
    public Area getByName(String name) {
        return areaMapper.getByName(name);
    }

    @Override
    public List<AreaLocationTreeVO> listAreaLocationTree(Long warehouseId) {
        //查询仓库下的所以库区
        List<Area> areaList = areaMapper.getByWarehouseId(warehouseId);
        return areaList.stream().map(area -> {
            //查询每个库区下的库位
            List<Location> locationList = locationMapper.getByAreaId(area.getId());
            //将库区库位转换为AreaLocationTreeVO
            List<AreaLocationTreeVO> children = locationList.stream().map(location -> AreaLocationTreeVO.builder().label(location.getName()).value(location.getId()).ownerId(location.getOwnerId()).build()).collect(Collectors.toList());
            return AreaLocationTreeVO.builder().label(area.getName()).value(area.getId()).children(children).build();
        }).collect(Collectors.toList());
    }

}

