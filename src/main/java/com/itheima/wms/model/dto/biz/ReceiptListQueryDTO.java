package com.itheima.wms.model.dto.biz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 入库清单
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "入库清单查询参数")
public class ReceiptListQueryDTO {

    @ApiModelProperty("入库单id")
    private Long masterId;

    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
}
