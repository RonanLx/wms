/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.service.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.model.dto.biz.ReceiptDTO;
import com.itheima.wms.model.dto.biz.ReceiptQueryDTO;
import com.itheima.wms.model.entity.biz.Receipt;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.ReceiptDetailVO;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 入库单
 * </p>
 */
public interface ReceiptService {

    PageBean<ReceiptDetailVO> pageDetail(ReceiptQueryDTO data);

    Receipt save(ReceiptDTO data);

    Receipt getById(Long id);

    BatchVO receiving(List<Long> ids);

    ReceiptDetailVO getDetailById(Long id);

    void updateById(Receipt data);

    void removeByIds(List<Long> ids);
}
