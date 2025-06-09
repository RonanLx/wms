/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.biz.impl;

import com.github.pagehelper.Page;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.constants.IncreaseDecreaseStatus;
import com.itheima.wms.common.constants.StockRecordType;
import com.itheima.wms.mapper.biz.IncreaseDecreaseMapper;
import com.itheima.wms.mapper.biz.StockMapper;
import com.itheima.wms.mapper.biz.StockRecordMapper;
import com.itheima.wms.model.dto.biz.IncreaseDecreaseDTO;
import com.itheima.wms.model.dto.biz.IncreaseDecreaseQueryDTO;
import com.itheima.wms.model.entity.biz.IncreaseDecrease;
import com.itheima.wms.model.entity.biz.Stock;
import com.itheima.wms.model.entity.biz.StockRecord;
import com.itheima.wms.model.vo.biz.IncreaseDecreaseDetailVO;
import com.itheima.wms.model.vo.biz.IncreaseDecreaseSumVO;
import com.itheima.wms.service.biz.IncreaseDecreaseService;
import com.itheima.wms.service.biz.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 业务实现类
 * 损益单
 * </p>
 */
@Slf4j
@Service
public class IncreaseDecreaseServiceImpl implements IncreaseDecreaseService {

    @Autowired
    private IncreaseDecreaseMapper increaseDecreaseMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StockService stockService;
    @Autowired
    private StockRecordMapper stockRecordMapper;

    @Override
    public PageBean<IncreaseDecreaseDetailVO> pageDetail(IncreaseDecreaseQueryDTO data) {
        Page<IncreaseDecreaseDetailVO> page = increaseDecreaseMapper.pageDetail(data);
        return PageBean.builder(page);
    }

    @Override
    public List<IncreaseDecreaseDetailVO> listDetail(IncreaseDecreaseQueryDTO data) {
        return increaseDecreaseMapper.pageDetail(data);
    }

    @Override
    @Transactional
    public void change(IncreaseDecreaseDTO data) {
        IncreaseDecrease increaseDecrease = increaseDecreaseMapper.getById(data.getId());
        increaseDecrease.setStatus(IncreaseDecreaseStatus.CHANGE);
        increaseDecreaseMapper.updateById(increaseDecrease);

        Stock stock = stockMapper.getById(increaseDecrease.getStockId());
        Integer original = stock.getTotal();
        Integer originalFree = stock.getFree();

        stock.setTotal(stock.getTotal() + increaseDecrease.getIdNum());
        stock.setFree(stock.getTotal() - stock.getFrozen());
        stockService.updateById(stock);

        StockRecord stockRecord = new StockRecord();
        stockRecord.setSourceId(increaseDecrease.getId());
        stockRecord.setLocationId(stock.getLocationId());
        stockRecord.setType(StockRecordType.SY);// 类型损益
        stockRecord.setOriginal(original);
        stockRecord.setOriginalFree(originalFree);
        stockRecord.setWay(increaseDecrease.getIdNum() > 0 ? "+" : "-");
        stockRecord.setAlteration(Math.abs(increaseDecrease.getIdNum()));
        stockRecord.setResult(stockRecord.getOriginal() + stockRecord.getAlteration());
        stockRecord.setResultFree(stockRecord.getOriginalFree() + stockRecord.getAlteration());
        stockRecordMapper.save(stockRecord);
    }

    @Override
    public void cancel(IncreaseDecreaseDTO data) {
        IncreaseDecrease increaseDecrease = increaseDecreaseMapper.getById(data.getId());
        increaseDecrease.setStatus(IncreaseDecreaseStatus.CANCEL);
        increaseDecreaseMapper.updateById(increaseDecrease);
    }

    @Override
    public IncreaseDecreaseSumVO sum(IncreaseDecreaseQueryDTO data) {
        return increaseDecreaseMapper.sum(data);
    }

    @Override
    public PageBean<IncreaseDecrease> page(IncreaseDecreaseQueryDTO data) {
        Page<IncreaseDecrease> page = increaseDecreaseMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public List<IncreaseDecrease> list(IncreaseDecreaseQueryDTO data) {
        return increaseDecreaseMapper.page(data);
    }

    @Override
    public IncreaseDecrease getById(Long id) {
        return increaseDecreaseMapper.getById(id);
    }

    @Override
    public void save(IncreaseDecreaseDTO data) {
        increaseDecreaseMapper.save(data);
    }

    @Override
    public void updateById(IncreaseDecreaseDTO data) {
        increaseDecreaseMapper.updateById(data);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> increaseDecreaseMapper.removeById(id));
    }
}

