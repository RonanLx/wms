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
 * 库存
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Stock", description = "库存")
public class Stock extends Entity {

    private static final long serialVersionUID = 1L;

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
     * 库位id
     */
    @ApiModelProperty(value = "库位id")
    private Long locationId;
    /**
     * 货主id
     */
    @ApiModelProperty(value = "货主id")
    private Long ownerId;
    /**
     * 货品id
     */
    @ApiModelProperty(value = "货品id")
    private Long goodsId;
    /**
     * 总库存
     */
    @ApiModelProperty(value = "总库存")
    private Integer total;
    /**
     * 可用库存
     */
    @ApiModelProperty(value = "可用库存")
    private Integer free;
    /**
     * 冻结库存
     */
    @ApiModelProperty(value = "冻结库存")
    private Integer frozen;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态 0 未满  1 已满")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
