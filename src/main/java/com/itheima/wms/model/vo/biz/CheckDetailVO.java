package com.itheima.wms.model.vo.biz;

import com.itheima.wms.model.entity.biz.Check;
import com.itheima.wms.model.entity.biz.CheckTask;
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
 * 盘点单详情
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CheckDetailVO", description = "盘点单详情")
public class CheckDetailVO extends Check {

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;
    @ApiModelProperty(value = "库区名称")
    private String areaName;
    @ApiModelProperty("货主名称")
    private String ownerName;

    @ApiModelProperty(value = "状态节点时间")
    private List<String> timeArray;

    @ApiModelProperty(value = "一盘明细")
    private CheckTask checkTask1;
    @ApiModelProperty(value = "复盘明细")
    private CheckTask checkTask2;

    @ApiModelProperty(value = "一盘损益合计")
    private BigDecimal id1Money;
    @ApiModelProperty(value = "复盘损益合计")
    private BigDecimal id2Money;

    @ApiModelProperty(value = "一盘损益列表")
    private List<IncreaseDecreaseDetailVO> id1List;
    @ApiModelProperty(value = "复盘损益列表")
    private List<IncreaseDecreaseDetailVO> id2List;

    @ApiModelProperty(value = "库位总计")
    private Integer locationTotal;
    @ApiModelProperty(value = "货品总计")
    private Integer goodsTotal;


}
