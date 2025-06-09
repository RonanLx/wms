package com.itheima.wms.model.dto.biz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 库存
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "库存查询参数")
public class StockQueryDTO {

    @ApiModelProperty("仓库id")
    private Long warehouseId;
    @ApiModelProperty("库区id")
    private Long areaId;
    @ApiModelProperty("库位id")
    private Long locationId;
    @ApiModelProperty("货主名称")
    private String ownerName;
    @ApiModelProperty("货主名称")
    private String owner;
    @ApiModelProperty("货主id")
    private Long ownerId;
    @ApiModelProperty("货品名称")
    private String goodsName;
    @ApiModelProperty("货品名称")
    private String goods;

    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
}
