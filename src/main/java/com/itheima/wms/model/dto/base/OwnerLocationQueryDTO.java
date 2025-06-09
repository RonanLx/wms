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
 * 货主-库位关联表
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "货主-库位查询参数")
public class OwnerLocationQueryDTO {

    @ApiModelProperty("货主id")
    private Long ownerId;

    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
}
