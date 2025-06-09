/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.vo.biz;

import com.itheima.wms.model.entity.biz.Stock;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 库存
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "StockDetailVO", description = "库存明细")
public class StockDetailVO extends Stock {

    @ApiModelProperty("仓库名称")
    private String warehouseName;
    @ApiModelProperty("库区名称")
    private String areaName;
    @ApiModelProperty("库位名称")
    private String locationName;
    @ApiModelProperty("库位编码")
    private String locationCode;
    @ApiModelProperty("货品名称")
    private String goodsName;
    @ApiModelProperty("货品编码")
    private String goodsCode;
    @ApiModelProperty("货品条码")
    private String goodsBarCode;
    @ApiModelProperty("货品单位")
    private String goodsUnit;
    @ApiModelProperty("货品类型名称")
    private String goodsTypeName;
    @ApiModelProperty("货主名称")
    private String ownerName;
    @ApiModelProperty("货主编码")
    private String ownerCode;
}
