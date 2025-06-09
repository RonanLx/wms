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
 * 盘点清单
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CheckList", description = "盘点清单")
public class CheckList extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 主记录id
     */
    @ApiModelProperty(value = "主记录id")
    private Long masterId;
    /**
     * 库存
     */
    @ApiModelProperty(value = "库存")
    private Long stockId;
    /**
     * 库存数量
     */
    @ApiModelProperty(value = "库存数量")
    private Integer stockNum;
    /**
     * 盘点数量
     */
    @ApiModelProperty(value = "盘点数量")
    private Integer checkNum;
    /**
     * 数量差异
     */
    @ApiModelProperty(value = "数量差异")
    private Integer differenceNum;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态 1 一盘，2 复盘")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
