/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.mapper.biz;

import com.github.pagehelper.Page;
import com.itheima.wms.model.entity.biz.Check;
import com.itheima.wms.model.vo.biz.CheckDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * 盘点单
 * </p>
 */
@Mapper
public interface CheckMapper {

    Page<CheckDetailVO> selectCheckDetail(@Param("params") Map data);

    void save(Check entity);

    @Select("select * from wms_check where id = #{id}")
    Check getById(Long id);

    void updateById(Check check);

    Page<Check> page(Map data);

    void removeById(Long id);

    List<Check> listByStatus(Integer status);
}
