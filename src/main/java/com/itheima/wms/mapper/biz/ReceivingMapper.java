/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.mapper.biz;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.biz.ReceivingQueryDTO;
import com.itheima.wms.model.entity.biz.Receiving;
import com.itheima.wms.model.vo.biz.ReceivingDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * 收货任务
 * </p>
 */
@Mapper
public interface ReceivingMapper {

    Page<ReceivingDetailVO> pageDetail(ReceivingQueryDTO data);

    Page<Receiving> page(ReceivingQueryDTO data);

    void insert(Receiving receiving);

    void updateById(Receiving receiving);

    @Select("select * from wms_receiving where master_id=#{masterId}")
    Receiving getByMasterId(Long masterId);

    @Select("select * from wms_receiving where id=#{id}")
    Receiving getById(Long id);

    void removeById(Long id);
}
