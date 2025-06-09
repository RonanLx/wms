/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.biz.ReceiptListBatchDTO;
import com.itheima.wms.model.dto.biz.ReceiptListDTO;
import com.itheima.wms.model.dto.biz.ReceiptListQueryDTO;
import com.itheima.wms.model.entity.biz.ReceiptList;
import com.itheima.wms.model.vo.biz.ReceiptListDetailVO;
import com.itheima.wms.model.vo.biz.ReceiptListSumVO;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 入库清单
 * </p>
 */
public interface ReceiptListService {

    PageBean<ReceiptListDetailVO> pageDetail(ReceiptListQueryDTO data);

    List<Long> goodsIds(ReceiptListQueryDTO data);

    PageBean<ReceiptList> page(ReceiptListQueryDTO data);

    List<ReceiptList> list(ReceiptListQueryDTO data);

    void saveBatch(ReceiptListBatchDTO receiptListBatchDTO);

    void updateById(ReceiptListDTO dto);

    ReceiptList getById(Long id);

    ReceiptListSumVO getReceiptListSumDetail(Long masterId);

    void save(ReceiptListDTO data);

    void removeByIds(List<Long> ids);

    void removeByMasterId(Long masterId);
}
