/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.biz.StockDTO;
import com.itheima.wms.model.dto.biz.StockQueryDTO;
import com.itheima.wms.model.entity.biz.Stock;
import com.itheima.wms.model.vo.biz.StockDetailVO;

import java.util.List;

/**
 * <p>
 * 库存业务接口
 * </p>
 */
public interface StockService {

    PageBean<StockDetailVO> pageDetail(StockQueryDTO data);

    PageBean<Stock> page(StockQueryDTO data);

    List<StockDetailVO> listDetail(StockQueryDTO data);

    List<Stock> list(StockQueryDTO data);

    Stock getByLocationId(Long id);

    void addFrozen(Long location, Integer num);

    void unfreeze(Long location, Integer num);

    void updateById(Stock entity);

    Stock getById(Long id);

    void save(StockDTO data);

    void removeByIds(List<Long> ids);
}
