/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.vo.biz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 展示层
 * 入库清单总计
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ReceiptListSumVO", description = "入库清单总计")
public class ReceiptListSumVO implements Serializable {

    @ApiModelProperty(value = "体积总计")
    private BigDecimal volumeTotal;
    @ApiModelProperty(value = "货品总计")
    private Integer goodsTotal;
    @ApiModelProperty(value = "实收货品总计")
    private Integer goodsRealTotal;
    @ApiModelProperty(value = "实上货品总计")
    private Integer goodsGroundingTotal;

    @ApiModelProperty(value = "总条数")
    private Integer total;
    @ApiModelProperty(value = "实收条数")
    private Integer realTotal;
    @ApiModelProperty(value = "上架条数")
    private Integer groundingTotal;
}
