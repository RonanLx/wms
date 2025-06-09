package com.itheima.wms.service.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.biz.CheckDTO;
import com.itheima.wms.model.entity.base.Area;
import com.itheima.wms.model.entity.biz.Check;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.CheckDetailVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 盘点单
 * </p>
 */
public interface CheckService {

    PageBean<Check> page(Map data);

    /**
     * 生成盘点任务
     *
     * @param ids
     * @return
     */
    BatchVO task(List<Long> ids);

    /**
     * 根据id查询盘点单详情
     *
     * @param id
     * @return
     */
    CheckDetailVO getDetail(Long id);

    /**
     * 分页查询盘点单详情
     */
    PageBean<CheckDetailVO> pageDetail(Map data);

    /**
     * 自动生成盘点单
     *
     * @param area
     * @return
     */
    Long generatorCheck(Area area);

    void save(Check entity);

    /**
     * 取消盘点单
     *
     * @param data
     */
    void cancel(CheckDTO data);

    List<Check> list(Map data);

    Check getById(Long id);

    void updateById(CheckDTO data);

    void removeByIds(List<Long> ids);

}
