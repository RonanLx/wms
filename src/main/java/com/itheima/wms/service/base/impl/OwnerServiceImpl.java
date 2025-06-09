package com.itheima.wms.service.base.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.mapper.base.OwnerLocationMapper;
import com.itheima.wms.mapper.base.OwnerMapper;
import com.itheima.wms.mapper.biz.StockMapper;
import com.itheima.wms.model.dto.base.OwnerDTO;
import com.itheima.wms.model.dto.base.OwnerQueryDTO;
import com.itheima.wms.model.entity.base.Owner;
import com.itheima.wms.model.entity.base.OwnerLocation;
import com.itheima.wms.model.entity.biz.Stock;
import com.itheima.wms.model.vo.base.OwnerDetailVO;
import com.itheima.wms.service.base.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 货主管理
 * </p>
 */
@Slf4j
@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private OwnerLocationMapper ownerLocationMapper;
    @Autowired
    private OwnerMapper ownerMapper;

    @Override
    public PageBean<Owner> page(OwnerQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<Owner> page = ownerMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public List<Owner> list(OwnerQueryDTO data) {
        /*if (data.containsKey("areaId")) {
            Long areaId = Long.valueOf(data.get("areaId").toString());
            List<OwnerLocation> ownerLocationEntities = ownerLocationMapper.getByAreaId(areaId);
            Set<Long> ownerIdSet = ownerLocationEntities.stream().map(ownerLocationEntity -> ownerLocationEntity.getOwnerId()).collect(Collectors.toSet());
            if (CollectionUtils.isEmpty(ownerIdSet)) {
                return new ArrayList<>(0);
            }
            data.put("in_id", ownerIdSet);
            data.remove("areaId");
        }*/
        return ownerMapper.page(data);
    }

    @Override
    public OwnerDetailVO getDetail(Long id) {
        OwnerDetailVO ownerDetailVO = new OwnerDetailVO();
        Owner owner = ownerMapper.getById(id);
        BeanUtils.copyProperties(owner, ownerDetailVO);
        //查询货主的货品总数
        Integer total = stockMapper.getByOwnerId(owner.getId()).stream().mapToInt(Stock::getTotal).sum();
        ownerDetailVO.setGoodsTotal(total);
        return ownerDetailVO;
    }

    @Override
    public void save(OwnerDTO data) {
        ownerMapper.save(data);
    }

    @Override
    public Owner getById(Long id) {
        return ownerMapper.getById(id);
    }

    @Override
    public void updateById(OwnerDTO data) {
        ownerMapper.updateById(data);
    }

    @Override
    @Transactional
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> {
            //判断是否有库区关联，否则不能删除
            List<OwnerLocation> ownerLocations = ownerLocationMapper.getByOwnerId(id);
            if (ownerLocations.size() > 0) {
                throw new RuntimeException("有货主库区相关联，禁止删除");
            }
            ownerMapper.removeById(id);
        });
    }
}

