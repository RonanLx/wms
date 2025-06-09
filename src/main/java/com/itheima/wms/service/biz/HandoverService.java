package com.itheima.wms.service.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.biz.HandoverDTO;
import com.itheima.wms.model.entity.biz.Handover;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.HandoverDetailVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 交接任务
 * </p>
 */
public interface HandoverService {

    BatchVO complete(List<Long> ids);

    PageBean<HandoverDetailVO> pageDetail(Map data);

    Handover getById(Long id);

    void save(HandoverDTO data);

    void updateById(Handover data);

    void removeByIds(List<Long> ids);
}
