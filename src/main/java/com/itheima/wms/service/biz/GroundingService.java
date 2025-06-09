package com.itheima.wms.service.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.biz.GroundingDTO;
import com.itheima.wms.model.dto.biz.GroundingQueryDTO;
import com.itheima.wms.model.entity.biz.Grounding;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.GroundingDetailVO;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 上架任务
 * </p>
 */
public interface GroundingService {

    PageBean<GroundingDetailVO> pageDetail(GroundingQueryDTO data);

    PageBean<Grounding> page(GroundingQueryDTO data);

    List<Grounding> list(GroundingQueryDTO data);

    BatchVO complete(List<Long> ids);

    Grounding getById(Long id);

    void save(GroundingDTO data);

    void distribute(GroundingDTO grounding);

    void updateById(GroundingDTO data);

    void removeByIds(List<Long> ids);
}
