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
 * 库位
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "库位查询参数")
public class LocationQueryDTO {

    @ApiModelProperty("库位名称")
    private String name;
    @ApiModelProperty("仓库名称")
    private String warehouseName;
    @ApiModelProperty("库区名称")
    private String areaName;
    @ApiModelProperty("库位状态")
    private Integer status;
    @ApiModelProperty("库区id")
    private Integer areaId;
    @ApiModelProperty("货主id")
    private Long ownerId;

    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
}
