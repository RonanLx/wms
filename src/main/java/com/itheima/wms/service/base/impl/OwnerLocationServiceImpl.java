/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.base.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.mapper.base.LocationMapper;
import com.itheima.wms.mapper.base.OwnerLocationMapper;
import com.itheima.wms.model.dto.base.OwnerLocationBatchDTO;
import com.itheima.wms.model.dto.base.OwnerLocationDTO;
import com.itheima.wms.model.dto.base.OwnerLocationQueryDTO;
import com.itheima.wms.model.entity.base.Location;
import com.itheima.wms.model.entity.base.OwnerLocation;
import com.itheima.wms.model.vo.base.OwnerLocationDetailVO;
import com.itheima.wms.service.base.OwnerLocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 业务实现类
 * 货主-库位关联表
 * </p>
 */
@Slf4j
@Service
public class OwnerLocationServiceImpl implements OwnerLocationService {

    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private OwnerLocationMapper ownerLocationMapper;


    @Override
    public PageBean<OwnerLocation> page(OwnerLocationQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<OwnerLocation> page = ownerLocationMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public PageBean<OwnerLocationDetailVO> pageDetail(OwnerLocationQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<OwnerLocationDetailVO> page = ownerLocationMapper.pageDetail(data);
        return PageBean.builder(page);
    }

    @Override
    public List<OwnerLocation> list(OwnerLocationQueryDTO data) {
        return ownerLocationMapper.page(data);
    }

    @Override
    public void save(OwnerLocationDTO data) {
        ownerLocationMapper.save(data);
    }

    @Override
    public void saveBatch(OwnerLocationBatchDTO data) {
        data.getIdStrArray().forEach(idStr -> {
            String[] ids = idStr.split(",");//1,2,2
            OwnerLocation ownerLocation = OwnerLocation.builder()
                    .ownerId(data.getOwnerId())
                    .warehouseId(Long.valueOf(ids[0]))//仓库id
                    .areaId(Long.valueOf(ids[1]))//库区id
                    .locationId(Long.valueOf(ids[2]))//库位id
                    .build();
            //添加库区库位关系
            ownerLocationMapper.save(ownerLocation);

            //更新库区
            Location location = new Location();
            location.setId(ownerLocation.getLocationId());
            location.setOwnerId(data.getOwnerId());
            locationMapper.updateById(location);
        });
    }

    @Override
    public OwnerLocation getById(Long id) {
        return ownerLocationMapper.getById(id);
    }


    @Override
    public void updateById(OwnerLocationDTO data) {
        ownerLocationMapper.updateById(data);
    }

    @Override
    @Transactional
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> {
            OwnerLocation ownerLocation = ownerLocationMapper.getById(id);
            //更新库区
            locationMapper.updateOwnerIdById(ownerLocation.getLocationId(), null);
            //删除货主库区关系
            ownerLocationMapper.removeById(id);
        });
    }
}

