package com.itheima.wms.model.dto.biz;

import com.itheima.wms.model.entity.biz.Receipt;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 入库单
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "入库单")
public class ReceiptDTO extends Receipt {

}
