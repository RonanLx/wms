/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.mapper.biz;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.biz.ReceiptListQueryDTO;
import com.itheima.wms.model.entity.biz.ReceiptList;
import com.itheima.wms.model.vo.biz.ReceiptListDetailVO;
import com.itheima.wms.model.vo.biz.ReceiptListSumVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 入库清单
 * </p>
 */
@Mapper
public interface ReceiptListMapper {

    Page<ReceiptList> page(ReceiptListQueryDTO data);

    Page<ReceiptListDetailVO> pageDetail(ReceiptListQueryDTO data);

    ReceiptListSumVO getReceiptListSumDetail(Long masterId);

    @Select("select * from wms_receipt_list where master_id=#{masterId}")
    List<ReceiptList> getByMasterId(Long masterId);

    void save(ReceiptList receiptList);

    @Select("select * from wms_receipt_list where id=#{id}")
    ReceiptList getById(Long id);

    void updateById(ReceiptList receiptList);

    void removeById(Long id);

    void removeByMasterId(Long masterId);
}
