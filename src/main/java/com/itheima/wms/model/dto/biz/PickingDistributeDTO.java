/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.dto.biz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 拣货任务
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "拣货任务")
public class PickingDistributeDTO {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("负责人")
    private String personName;
}
