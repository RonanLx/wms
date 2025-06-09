/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.mapper.biz;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.biz.StockQueryDTO;
import com.itheima.wms.model.entity.biz.Stock;
import com.itheima.wms.model.vo.biz.StockDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 库存
 * </p>
 */
@Mapper
public interface StockMapper {

    Page<StockDetailVO> pageDetail(StockQueryDTO data);

    Page<Stock> page(StockQueryDTO data);

    void save(Stock data);

    @Select("select * from wms_stock where owner_id=#{ownerId}")
    List<Stock> getByOwnerId(Long ownerId);

    @Select("select * from wms_stock where area_id=#{areaId}")
    List<Stock> getByAreaId(Long areaId);

    void updateById(Stock stock);

    @Select("select * from wms_stock where id=#{id}")
    Stock getById(Long id);

    @Select("select * from wms_stock where location_id=#{locationId}")
    Stock getByLocationId(Long locationId);

    void removeById(Long id);
}
