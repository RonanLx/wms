/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.dto.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 承运商
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "承运商查询参数")
public class CarrierQueryDTO {

    @ApiModelProperty("承运商id")
    private Long id;
    @ApiModelProperty("承运商编码")
    private String like_code;
    @ApiModelProperty("承运商名称")
    private String like_name;
    @ApiModelProperty("联系人姓名")
    private String like_personName;

    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
}
