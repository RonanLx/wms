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
@ApiModel(description = "上架任务")
public class GroundingDistributeDTO {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("负责人")
    private String personName;
}
