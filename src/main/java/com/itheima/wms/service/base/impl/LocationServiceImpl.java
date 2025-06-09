package com.itheima.wms.service.base.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.constants.StockStatus;
import com.itheima.wms.mapper.base.AreaMapper;
import com.itheima.wms.mapper.base.LocationMapper;
import com.itheima.wms.mapper.base.OwnerLocationMapper;
import com.itheima.wms.mapper.biz.StockMapper;
import com.itheima.wms.model.dto.base.LocationDTO;
import com.itheima.wms.model.dto.base.LocationQueryDTO;
import com.itheima.wms.model.entity.base.Area;
import com.itheima.wms.model.entity.base.Location;
import com.itheima.wms.model.entity.base.OwnerLocation;
import com.itheima.wms.model.entity.biz.Stock;
import com.itheima.wms.model.vo.base.LocationDetailVO;
import com.itheima.wms.service.base.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 库位
 * </p>
 */
@Slf4j
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private OwnerLocationMapper ownerLocationMapper;


    @Override
    public PageBean<LocationDetailVO> pageDetail(LocationQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<LocationDetailVO> page = locationMapper.pageDetail(data);
        return PageBean.builder(page);
    }


    @Override
    public PageBean<Location> page(LocationQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<Location> page = locationMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public List<LocationDetailVO> listDetail(LocationQueryDTO data) {
        return locationMapper.pageDetail(data);
    }

    @Override
    public List<Location> list(LocationQueryDTO data) {
        List<Location> locationList = locationMapper.page(data);
        //如果查询条件有OwnerId，则过滤掉不属于该货主的库位
        if (data.getOwnerId() != null) {
            List<OwnerLocation> ownerLocationList = ownerLocationMapper.getByOwnerId(data.getOwnerId());
            List<Long> locationIds = ownerLocationList.stream().map(ownerLocation -> ownerLocation.getLocationId()).collect(Collectors.toList());
            locationList = locationList.stream().filter(location -> locationIds.contains(location.getId())).collect(Collectors.toList());
        }
        return locationList;
    }

    @Override
    public void save(Location location) {
        locationMapper.save(location); //主键自增返回

        //修改库区的库位数量
        Integer count = locationMapper.countByAreaId(location.getAreaId());
        areaMapper.updateById(Area.builder().includedNum(count).id(location.getAreaId()).build());

        //初始化库位对应的库存
        Stock stock = Stock.builder()
                .frozen(0)
                .free(0)
                .total(0)
                .status(StockStatus.UNFULL)
                .warehouseId(location.getWarehouseId())
                .areaId(location.getAreaId())
                .locationId(location.getId())
                .build();
        stockMapper.save(stock);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> {
            Location entity = locationMapper.getById(id);
            locationMapper.removeById(id);

            //修改库区的库位数量
            Integer count = locationMapper.countByAreaId(entity.getAreaId());
            areaMapper.updateById(Area.builder().includedNum(count).id(entity.getAreaId()).build());
        });
    }

    @Override
    public Location getById(Long id) {
        return locationMapper.getById(id);
    }

    @Override
    public void updateById(LocationDTO data) {
        locationMapper.updateById(data);

        //修改库区的库位数量
        Integer count = locationMapper.countByAreaId(data.getAreaId());
        areaMapper.updateById(Area.builder().includedNum(count).id(data.getAreaId()).build());
    }
}

