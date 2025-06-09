/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.dto.biz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


/**
 * 批量盘点清单
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "批量盘点清单")
public class ReceiptListBatchDTO {

    @ApiModelProperty("货品id集合")
    private List<Long> goodsIds;

    @ApiModelProperty("入库单id")
    private Long masterId;
}
