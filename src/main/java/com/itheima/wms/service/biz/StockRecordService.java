package com.itheima.wms.service.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.biz.StockRecordDTO;
import com.itheima.wms.model.entity.biz.StockRecord;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 库存记录业务接口
 * </p>
 */
public interface StockRecordService {

    PageBean<StockRecord> page(Map data);

    List<StockRecord> list(Map data);

    StockRecord getById(Long id);

    void save(StockRecordDTO data);

    void updateById(StockRecordDTO data);

    void removeByIds(List<Long> ids);
}
