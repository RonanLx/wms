package com.itheima.wms.model.dto.biz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 上架任务
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "上架任务查询参数")
public class GroundingQueryDTO {

    @ApiModelProperty("上架编号")
    private String code;
    @ApiModelProperty("入库单编号")
    private String receiptCode;
    @ApiModelProperty("货主名称")
    private String ownerName;

    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
}
