/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.mapper.biz;

import com.github.pagehelper.Page;
import com.itheima.wms.model.entity.biz.StockRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * 库存记录
 * </p>
 */
@Mapper
public interface StockRecordMapper {

    void save(StockRecord stockRecord);

    List<StockRecord> list(Map data);

    void updateById(StockRecord stockRecord);

    Page<StockRecord> page(Map data);

    StockRecord getById(Long id);

    void remove(StockRecord stockRecord);

    void removeById(Long id);

}
