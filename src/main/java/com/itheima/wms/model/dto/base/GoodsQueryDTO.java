package com.itheima.wms.model.dto.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 货品管理
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "货品查询参数")
public class GoodsQueryDTO {

    @ApiModelProperty("货品id")
    private Long id;
    @ApiModelProperty("货品编码")
    private String code;
    @ApiModelProperty("货品名称")
    private String name;
    @ApiModelProperty("货主id")
    private Long ownerId;
    @ApiModelProperty("货主名称")
    private String ownerName;

    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
}
