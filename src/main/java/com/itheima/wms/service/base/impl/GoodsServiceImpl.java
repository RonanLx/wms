/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.base.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.mapper.base.GoodsMapper;
import com.itheima.wms.model.dto.base.GoodsDTO;
import com.itheima.wms.model.dto.base.GoodsQueryDTO;
import com.itheima.wms.model.entity.base.Goods;
import com.itheima.wms.model.vo.base.GoodsDetailVO;
import com.itheima.wms.service.base.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 业务实现类
 * 货品管理
 * </p>
 */
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageBean<Goods> page(GoodsQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<Goods> page = goodsMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public PageBean<GoodsDetailVO> pageDetail(GoodsQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<GoodsDetailVO> page = goodsMapper.pageDetail(data);
        return PageBean.builder(page);
    }

    @Override
    public List<Goods> list(GoodsQueryDTO map) {
        return goodsMapper.page(map);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> {
            goodsMapper.removeById(id);
        });
    }

    @Override
    public Goods getById(Long id) {
        return goodsMapper.getById(id);
    }

    @Override
    public void save(GoodsDTO data) {
        goodsMapper.save(data);
    }

    @Override
    public void updateById(GoodsDTO data) {
        goodsMapper.updateById(data);
    }

}

