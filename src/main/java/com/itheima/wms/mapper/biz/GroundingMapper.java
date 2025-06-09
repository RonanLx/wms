/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.mapper.biz;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.biz.GroundingQueryDTO;
import com.itheima.wms.model.entity.biz.Grounding;
import com.itheima.wms.model.vo.biz.GroundingDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * 上架任务
 * </p>
 */
@Mapper
public interface GroundingMapper {

    Page<GroundingDetailVO> pageDetail(GroundingQueryDTO data);

    Page<Grounding> page(GroundingQueryDTO data);

    void insert(Grounding grounding);

    void updateById(Grounding grounding);

    List<Map> sumGroupByDate(@Param("params") Map data);

    @Select("select * from wms_grounding where master_id=#{masterId}")
    Grounding getByMasterId(Long masterId);

    Integer countByMasterId(Long masterId);

    @Select("select * from wms_grounding where id=#{id}")
    Grounding getById(Long id);

    void removeById(Long id);
}
