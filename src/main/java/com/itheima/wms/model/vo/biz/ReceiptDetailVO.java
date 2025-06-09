package com.itheima.wms.model.vo.biz;

import com.itheima.wms.model.entity.base.Owner;
import com.itheima.wms.model.entity.biz.Grounding;
import com.itheima.wms.model.entity.biz.Receipt;
import com.itheima.wms.model.entity.biz.Receiving;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 展示层
 * 入库单详情
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReceiptDetailVO", description = "入库单详情")
public class ReceiptDetailVO extends Receipt {

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;
    @ApiModelProperty(value = "库区名称")
    private String areaName;
    @ApiModelProperty(value = "货主名称")
    private String ownerName;
    @ApiModelProperty(value = "货主编号")
    private String ownerCode;

    @ApiModelProperty(value = "运营商名称")
    private String carrierName;

    @ApiModelProperty(value = "状态节点时间，无效字段，可以忽略")
    private List<String> timeArray = List.of();

    @ApiModelProperty(value = "货主")
    private Owner owner;

    @ApiModelProperty(value = "体积总计")
    private BigDecimal volumeTotal;
    @ApiModelProperty(value = "货品总计")
    private Integer goodsTotal;

    @ApiModelProperty(value = "收货任务")
    private Receiving receiving;
    @ApiModelProperty(value = "上架任务")
    private Grounding grounding;

    @ApiModelProperty(value = "损益合计")
    private BigDecimal idMoney;

    @ApiModelProperty(value = "损益列表")
    private List<IncreaseDecreaseDetailVO> idList;
}
