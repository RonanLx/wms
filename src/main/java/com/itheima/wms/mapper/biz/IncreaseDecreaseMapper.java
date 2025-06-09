/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.mapper.biz;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.biz.IncreaseDecreaseQueryDTO;
import com.itheima.wms.model.entity.biz.IncreaseDecrease;
import com.itheima.wms.model.vo.biz.IncreaseDecreaseDetailVO;
import com.itheima.wms.model.vo.biz.IncreaseDecreaseSumVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * 损益单
 * </p>
 */
@Mapper
public interface IncreaseDecreaseMapper {

    Page<IncreaseDecrease> page(IncreaseDecreaseQueryDTO data);

    Page<IncreaseDecreaseDetailVO> pageDetail(IncreaseDecreaseQueryDTO data);

    void save(IncreaseDecrease data);

    IncreaseDecreaseSumVO sum(IncreaseDecreaseQueryDTO data);

    @Select("select * from wms_increase_decrease where id=#{id}")
    IncreaseDecrease getById(Long id);

    void updateById(IncreaseDecrease data);

    void removeById(Long id);
}
