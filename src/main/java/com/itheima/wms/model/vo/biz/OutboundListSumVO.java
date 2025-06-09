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
 * 出库清单总计
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "OutboundListSumVO", description = "出库清单总计")
public class OutboundListSumVO implements Serializable {

    @ApiModelProperty(value = "体积总计")
    private BigDecimal volumeTotal;
    @ApiModelProperty(value = "货品总计")
    private Integer goodsTotal;
    @ApiModelProperty(value = "拣货总计")
    private Integer pickingTotal;
}
