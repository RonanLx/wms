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
 * 入库单
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "入库单查询参数")
public class ReceiptQueryDTO {

    @ApiModelProperty("入库单号")
    private String code;
    @ApiModelProperty("运单编号")
    private String billCode;

    @ApiModelProperty("货主名称")
    private String ownerName;
    @ApiModelProperty("仓库名称")
    private String warehouseName;
    @ApiModelProperty("库区名称")
    private String areaName;

    @ApiModelProperty("仓库id")
    private Long warehouseId;
    @ApiModelProperty("库区id")
    private Long areaId;
    @ApiModelProperty("货主id")
    private Long ownerId;
    @ApiModelProperty("入库单状态")
    private Integer status;

    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
}
