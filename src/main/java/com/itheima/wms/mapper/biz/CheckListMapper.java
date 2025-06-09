package com.itheima.wms.mapper.biz;

import com.github.pagehelper.Page;
import com.itheima.wms.model.entity.biz.CheckList;
import com.itheima.wms.model.vo.biz.CheckListDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * 盘点清单
 * </p>
 */
@Mapper
public interface CheckListMapper {

    Page<CheckList> page(Map data);

    Page<CheckListDetailVO> selectCheckListDetail(@Param("params") Map data);

    List<CheckList> getByMasterId(Long id);

    Integer sumStockNumByMasterId(Long id);

    List<CheckList> getByMap(Map map);

    void save(CheckList checkList);

    List selectStockIdByMasterId(Object masterId);

    void removeById(Serializable id);

    CheckList getById(Serializable id);

    void updateById(CheckList entity);

    Map<String, Object> sumByMap(Map map);

    List<CheckList> selectByMap(Map map);

    void removeByMasterId(Long masterId);
}
