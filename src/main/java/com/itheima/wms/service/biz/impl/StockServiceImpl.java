package com.itheima.wms.service.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.constants.StockStatus;
import com.itheima.wms.mapper.base.GoodsMapper;
import com.itheima.wms.mapper.base.LocationMapper;
import com.itheima.wms.mapper.biz.StockMapper;
import com.itheima.wms.model.dto.biz.StockDTO;
import com.itheima.wms.model.dto.biz.StockQueryDTO;
import com.itheima.wms.model.entity.base.Location;
import com.itheima.wms.model.entity.biz.Stock;
import com.itheima.wms.model.vo.biz.StockDetailVO;
import com.itheima.wms.service.biz.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 业务实现类
 * 库存
 * </p>
 */
@Slf4j
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private StockMapper stockMapper;


    /**
     * 分页查询库存详情
     *
     * @return
     */
    @Override
    public PageBean<StockDetailVO> pageDetail(StockQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<StockDetailVO> page = stockMapper.pageDetail(data);
        return PageBean.builder(page);
    }

    /**
     * 查询库存详情列表
     *
     * @param data
     * @return
     */
    @Override
    public List<StockDetailVO> listDetail(StockQueryDTO data) {
        return stockMapper.pageDetail(data);
    }

    @Override
    public PageBean<Stock> page(StockQueryDTO data) {
        //开启分页
        int current = data.getCurrent();
        int size = data.getSize();
        PageHelper.startPage(current, size);

        Page<Stock> page = stockMapper.page(data);
        return PageBean.builder(page);
    }

    @Override
    public List<Stock> list(StockQueryDTO data) {
        return stockMapper.page(data);
    }

    /**
     * 根据库位ID查询库存
     *
     * @param locationId
     * @return
     */
    @Override
    public Stock getByLocationId(Long locationId) {
        Stock stock = stockMapper.getByLocationId(locationId);
        if (stock == null) {
            //初始化库位对应的库存
            Location location = locationMapper.getById(locationId);
            stock = Stock.builder()
                    .frozen(0)
                    .free(0)
                    .total(0)
                    // TODO .goodsId(location.get)
                    .ownerId(location.getOwnerId())
                    .status(StockStatus.UNFULL)
                    .warehouseId(location.getWarehouseId())
                    .areaId(location.getAreaId())
                    .locationId(location.getId())
                    .build();
            stockMapper.save(stock);
        }
        return stock;
    }

    /**
     * 增加冻结库存
     *
     * @param locationId
     * @param num
     */
    @Override
    public void addFrozen(Long locationId, Integer num) {
        Stock stock = getByLocationId(locationId);
        //total = free + frozen
        stock.setFrozen(stock.getFrozen() + num);//冻结库存
        stock.setTotal(stock.getTotal() + num);//可用库存
        stockMapper.updateById(stock);
    }

    /**
     * 解冻库存
     *
     * @param locationId
     * @param num
     */
    @Override
    public void unfreeze(Long locationId, Integer num) {
        Stock stock = getByLocationId(locationId);
        //total = free + frozen
        stock.setFrozen(stock.getFrozen() - num);//冻结库存
        stock.setFree(stock.getFree() + num);//可用库存
        stockMapper.updateById(stock);
    }

    @Override
    public void updateById(Stock entity) {
        //Stock dbEntity = stockMapper.getById(entity.getId());
        // 计算是否已满
        //Goods goods = goodsMapper.getById(entity.getGoodsId());
        //Location location = locationMapper.getById(entity.getLocationId());

        //entity.setStatus(StockStatus.UNFULL); // 未满
        //location.setUseStatus(LocationUseStatus.UNFULL); // 库存已占用

        // 最大承载个数
        //Integer maxNum = location.getMaxVolume().divide(goods.getVolume(), 2, BigDecimal.ROUND_HALF_UP).intValue();
        // 已存在库存个数
        //Integer dbTotal = (dbEntity != null && dbEntity.getTotal() > 0) ? dbEntity.getTotal() : 0;
        // 将要变更的库存个数
        //Integer total = entity.getTotal();

            /*log.info("库存变更信息 maxNum:{} dbTotal:{} total:{}", maxNum, dbEntity, total);

            if (total > maxNum) {
                throw new RuntimeException(location.getName() + " 库位最多还可承载 " + (maxNum - dbTotal) + " 个 " + goods.getName());
            }

            if (maxNum == total) {
                entity.setStatus(StockStatus.FULL);// 已满
                location.setUseStatus(LocationUseStatus.FULL);
            }*/

        //location.setMaxNum(maxNum);
        //locationMapper.updateById(location);

        stockMapper.updateById(entity);

    }

    @Override
    public Stock getById(Long id) {
        return stockMapper.getById(id);
    }

    @Override
    public void save(StockDTO data) {
        stockMapper.save(data);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        ids.forEach(id -> stockMapper.removeById(id));

    }
}

