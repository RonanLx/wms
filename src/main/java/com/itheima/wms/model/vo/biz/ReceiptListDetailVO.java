package com.itheima.wms.model.vo.biz;

import com.itheima.wms.model.entity.biz.LocationPlan;
import com.itheima.wms.model.entity.biz.ReceiptList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * <p>
 * 展示层
 * 入库单明细详情
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReceiptListDetailVO", description = "入库单明细详情")
public class ReceiptListDetailVO extends ReceiptList {
    @ApiModelProperty("货主名称")
    private String ownerName;
    @ApiModelProperty("货品编码")
    private String goodsCode;
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
    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;
    @ApiModelProperty(value = "推荐库区")
    private String areaName;

    @ApiModelProperty(value = "库位方案")
    private List<LocationPlan> locationPlanEntities = List.of();
    @ApiModelProperty(value = "推荐库位方案集合，无效字段，可忽略")
    private List<LocationPlan> recommendLocationPlanEntities = List.of();
}
