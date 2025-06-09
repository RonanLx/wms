/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.base.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.mapper.base.GoodsTypeMapper;
import com.itheima.wms.model.dto.base.GoodsTypeDTO;
import com.itheima.wms.model.dto.base.GoodsTypeQueryDTO;
import com.itheima.wms.model.entity.base.GoodsType;
import com.itheima.wms.service.base.GoodsTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 业务实现类
 * 货品类型管理
 * </p>
 */
@Slf4j
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public PageBean<GoodsType> page(GoodsTypeQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<GoodsType> page = goodsTypeMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public List<GoodsType> list(GoodsTypeQueryDTO data) {
        return goodsTypeMapper.page(data);
    }

    @Override
    public void save(GoodsTypeDTO data) {
        goodsTypeMapper.save(data);
    }

    @Override
    public GoodsType getById(Long id) {
        return goodsTypeMapper.getById(id);
    }

    @Override
    public void updateById(GoodsTypeDTO data) {
        goodsTypeMapper.updateById(data);
    }


    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> {
            goodsTypeMapper.removeById(id);
        });
    }
}

