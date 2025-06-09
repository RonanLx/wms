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
 * 库存记录
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "StockRecord", description = "库存记录")
public class StockRecord extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 来源id
     */
    @ApiModelProperty(value = "来源id")
    private Long sourceId;
    /**
     * 库位id
     */
    @ApiModelProperty(value = "库位id")
    private Long locationId;
    /**
     * 任务类型
     */
    @ApiModelProperty(value = "任务类型")
    private String type;
    /**
     * 原始总库存
     */
    @ApiModelProperty(value = "原始总库存")
    private Integer original;

    @ApiModelProperty(value = "原始可用库存")
    private Integer originalFree;
    /**
     * 变动方式 + -
     */
    @ApiModelProperty(value = "变动方式 + -")
    private String way;
    /**
     * 操作库存
     */
    @ApiModelProperty(value = "操作库存")
    private Integer alteration;
    /**
     * 操作后库存
     */
    @ApiModelProperty(value = "操作后总库存")
    private Integer result;

    @ApiModelProperty(value = "操作后可用库存")
    private Integer resultFree;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
