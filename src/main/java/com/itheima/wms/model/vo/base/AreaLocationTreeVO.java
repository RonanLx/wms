/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 库区库位树
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AreaLocationTreeVO", description = "库区库位树")
public class AreaLocationTreeVO {

    @ApiModelProperty("库区|库位名称")
    private String label;

    @ApiModelProperty("库区|库位id")
    private Long value;

    @ApiModelProperty("货主id")
    private Long ownerId;

    @ApiModelProperty("库区库位树")
    private List<AreaLocationTreeVO> children;
}
