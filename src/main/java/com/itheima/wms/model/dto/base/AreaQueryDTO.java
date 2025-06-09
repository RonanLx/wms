package com.itheima.wms.model.dto.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 库区
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "库区查询参数")
public class AreaQueryDTO {

    @ApiModelProperty("库区id")
    private Long id;
    @ApiModelProperty("仓库id")
    private Long warehouseId;
    @ApiModelProperty("货主id")
    private Long ownerId;
    @ApiModelProperty("库区名称")
    private String name;
    @ApiModelProperty("仓库名称")
    private String warehouseName;
    @ApiModelProperty("库区状态")
    private Integer status;

    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
}


