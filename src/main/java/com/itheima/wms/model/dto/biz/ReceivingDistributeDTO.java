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
public class ReceivingDistributeDTO {
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("收货人")
    private String receiverName;
}
