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
 * 入库单
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Receipt", description = "入库单")
public class Receipt extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 入库单号
     */
    @ApiModelProperty(value = "入库单号")
    private String code;
    /**
     * 运单编号
     */
    @ApiModelProperty(value = "运单编号")
    private String billCode;
    /**
     * 计划到达时间
     */
    @ApiModelProperty(value = "计划到达时间")
    private java.time.LocalDateTime planArrivalTime;
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
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String license;
    /**
     * 发货人姓名
     */
    @ApiModelProperty(value = "发货人姓名")
    private String shipperName;
    /**
     * 送货人姓名
     */
    @ApiModelProperty(value = "送货人姓名")
    private String deliveryName;
    /**
     * 送货人电话
     */
    @ApiModelProperty(value = "送货人电话")
    private String deliveryPhone;
    /**
     * 预计到货数
     */
    @ApiModelProperty(value = "预计到货数")
    private Integer planNum;
    /**
     * 入库单状态 1新建、2收货中、3已取消、4收货完成、5上架中、6上架完成
     */
    @ApiModelProperty(value = "入库单状态 1新建、2收货中、3已取消、4收货完成、5上架中、6上架完成")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
