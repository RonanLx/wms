/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.entity.biz;

import com.itheima.wms.model.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 实体类
 * 损益单
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "IncreaseDecrease", description = "损益单")
public class IncreaseDecrease extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 损益单号
     */
    @ApiModelProperty(value = "损益单号")
    private String code;
    /**
     * 库存
     */
    @ApiModelProperty(value = "库存id")
    private Long stockId;
    /**
     * 损益数量
     */
    @ApiModelProperty(value = "损益数量")
    private Integer idNum;
    /**
     * 损益金额
     */
    @ApiModelProperty(value = "损益金额")
    private java.math.BigDecimal idMoney;
    /**
     * 损益来源 盘点:PD、上架:SJ、拣货:JH
     */
    @ApiModelProperty(value = "损益来源 盘点:PD、上架:SJ、拣货:JH")
    private String idSource;
    /**
     * 任务id
     */
    @ApiModelProperty(value = "任务id")
    private Long taskId;
    /**
     * 任务单号
     */
    @ApiModelProperty(value = "任务单号")
    private String taskCode;
    /**
     * 损益单状态 1.新建、2已调整
     */
    @ApiModelProperty(value = "损益单状态 1.新建、2已调整")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
