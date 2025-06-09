package com.itheima.wms.service.biz.impl;

import com.github.pagehelper.Page;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.mapper.biz.StockRecordMapper;
import com.itheima.wms.model.dto.biz.StockRecordDTO;
import com.itheima.wms.model.entity.biz.StockRecord;
import com.itheima.wms.service.biz.StockRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 业务实现类
 * 库存记录
 * </p>
 */
@Slf4j
@Service
public class StockRecordServiceImpl implements StockRecordService {

    @Autowired
    private StockRecordMapper stockRecordMapper;

    @Override
    public PageBean<StockRecord> page(Map data) {
        Page<StockRecord> page = stockRecordMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public List<StockRecord> list(Map data) {
        return stockRecordMapper.page(data);
    }

    @Override
    public StockRecord getById(Long id) {
        return stockRecordMapper.getById(id);
    }

    @Override
    public void save(StockRecordDTO data) {
        stockRecordMapper.save(data);
    }

    @Override
    public void updateById(StockRecordDTO data) {
        stockRecordMapper.updateById(data);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> stockRecordMapper.removeById(id));

    }
}

