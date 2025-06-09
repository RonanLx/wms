package com.itheima.wms.model.dto.biz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 盘点任务
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "盘点任务")
public class CheckTaskDistributeDTO {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("负责人")
    private String personName;
}
