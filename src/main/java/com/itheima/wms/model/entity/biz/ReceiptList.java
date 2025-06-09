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
 * 入库清单
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReceiptList", description = "入库清单")
public class ReceiptList extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 主记录id
     */
    @ApiModelProperty(value = "主记录id")
    private Long masterId;
    /**
     * 货品id
     */
    @ApiModelProperty(value = "货品id")
    private Long goodsId;
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
     * 库位id
     */
    @ApiModelProperty(value = "库位方案id")
    private Long locationPlanId;
    /**
     * 预计到货数
     */
    @ApiModelProperty(value = "预计到货数")
    private Integer planNum;
    /**
     * 实收货数
     */
    @ApiModelProperty(value = "实收货数")
    private Integer realNum;
    /**
     * 实收数量差异
     */
    @ApiModelProperty(value = "实收数量差异")
    private Integer realDifferenceNum;
    /**
     * 上架数量
     */
    @ApiModelProperty(value = "上架数量")
    private Integer groundingNum;
    /**
     * 上架数量差异
     */
    @ApiModelProperty(value = "上架数量差异")
    private Integer groundingDifferenceNum;
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
