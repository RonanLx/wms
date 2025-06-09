package com.itheima.wms.mapper.biz;

import com.github.pagehelper.Page;
import com.itheima.wms.model.dto.biz.ReceiptQueryDTO;
import com.itheima.wms.model.entity.biz.Receipt;
import com.itheima.wms.model.vo.biz.ReceiptDetailVO;
import com.itheima.wms.model.vo.biz.ReceiptVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * 入库单
 * </p>
 */
@Mapper
public interface ReceiptMapper {

    Page<ReceiptVO> page(ReceiptQueryDTO data);

    Page<ReceiptDetailVO> pageDetail(ReceiptQueryDTO data);

    void save(Receipt entity);

    @Select("select * from wms_receipt where id=#{id}")
    Receipt getById(Long id);

    void updateById(Receipt receipt);

    @Delete("delete from wms_receipt where id=#{id}")
    void removeById(Long id);
}
