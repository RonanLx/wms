/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.biz.CheckListBatchDTO;
import com.itheima.wms.model.dto.biz.CheckListDTO;
import com.itheima.wms.model.entity.biz.CheckList;
import com.itheima.wms.model.vo.biz.CheckListDetailVO;
import com.itheima.wms.model.vo.biz.CheckListSumVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 盘点清单
 * </p>
 */
public interface CheckListService {

    void saveBatchByStockIds(CheckListBatchDTO checkListBatchDTO);

    PageBean<CheckListDetailVO> pageDetail(Map data);

    void saveBatch(List<CheckList> entityList);

    CheckListSumVO getSumByMasterId(Long masterId);

    void removeByIds(List<Long> idList);

    List<Long> stockIds(Map data);

    void updateById(CheckList entity);

    void cancelBatch(List<CheckList> checkListEntities);

    PageBean<CheckList> page(Map data);

    List<CheckList> list(Map data);

    CheckList getById(Long id);

    void save(CheckListDTO data);

    void removeByMasterId(Long masterId);
}
