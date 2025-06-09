package com.itheima.wms.model.dto.biz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 交接任务
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "交接任务")
public class HandoverDistributeDTO {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("交接员")
    private String handoverName;
}
