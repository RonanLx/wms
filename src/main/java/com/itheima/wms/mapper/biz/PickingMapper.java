/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.mapper.biz;

import com.github.pagehelper.Page;
import com.itheima.wms.model.entity.biz.Picking;
import com.itheima.wms.model.vo.biz.PickingDetailVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * 拣货任务
 * </p>
 */
@Mapper
public interface PickingMapper {

    Page<PickingDetailVO> pageDetail(@Param("params") Map data);

    List<Map> sumGroupByDate(@Param("params") Map data);

    void save(Picking picking);

    @Select("select * from wms_picking where master_id=#{masterId}")
    Picking getByMasterId(Long masterId);

    @Select("select * from wms_picking where id=#{id}")
    Picking getById(Long id);

    void updateById(Picking picking);

    @Delete("delete from wms_picking where id=#{id}")
    void removeById(Long id);
}
