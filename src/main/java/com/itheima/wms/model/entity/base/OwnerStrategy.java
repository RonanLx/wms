package com.itheima.wms.model.entity.base;

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
 * 货主-策略关联表
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OwnerStrategy", description = "货主-策略关联表")
public class OwnerStrategy extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 货主id
     */
    @ApiModelProperty(value = "货主id")
    private Long ownerId;
    /**
     * 仓库id
     */
    @ApiModelProperty(value = "仓库id")
    private Long warehouseId;
    /**
     * 库区id
     */
    @ApiModelProperty(value = "库区id")
    private Long areaId;
    /**
     * 上架策略
     */
    @ApiModelProperty(value = "上架策略")
    private Long launchStrategy;
    /**
     * 拣货策略
     */
    @ApiModelProperty(value = "拣货策略")
    private Long pickingStrategy;
    /**
     * 盘点策略
     */
    @ApiModelProperty(value = "盘点策略")
    private Long inventoryStrategy;
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
