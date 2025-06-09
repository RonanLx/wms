package com.itheima.wms.model.dto.biz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 收货任务
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "收货任务")
public class ReceivingQueryDTO {

    @ApiModelProperty("收货编号")
    private String code;
    @ApiModelProperty("货主名称")
    private String ownerName;
    @ApiModelProperty("收货单状态")
    private Integer status;

    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
}


