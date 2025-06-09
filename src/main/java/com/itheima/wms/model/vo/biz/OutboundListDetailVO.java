/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.vo.biz;

import com.itheima.wms.model.entity.biz.OutboundList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 展示层
 * 出库单明细详情
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OutboundListDetailVO", description = "出库单明细详情")
public class OutboundListDetailVO extends OutboundList {

    @ApiModelProperty("货主名称")
    private String ownerName;
    @ApiModelProperty("货品编码")
    private String goodsCode;
    @ApiModelProperty("货品条码")
    private String goodsBarCode;
    @ApiModelProperty("货品名称")
    private String goodsName;
    @ApiModelProperty("货品单价")
    private java.math.BigDecimal goodsPrice;
    @ApiModelProperty("保质天数")
    private Integer goodsGuaranteeDay;
    @ApiModelProperty("单位")
    private String goodsUnit;
    @ApiModelProperty("体积")
    private java.math.BigDecimal goodsVolume;
    @ApiModelProperty("货品类型")
    private String goodsTypeName;
    @ApiModelProperty("货品数量")
    private String stockTotal;
    @ApiModelProperty("货品库存")
    private String stockFree;
    @ApiModelProperty("库位名称")
    private String locationName;
    @ApiModelProperty("库位编码")
    private String locationCode;
    @ApiModelProperty("库区名称")
    private String areaName;
    @ApiModelProperty("库区编码")
    private String areaCode;
}
