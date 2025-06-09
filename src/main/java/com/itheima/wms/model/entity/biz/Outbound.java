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
 * 出库单
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Outbound", description = "出库单")
public class Outbound extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 出库单号
     */
    @ApiModelProperty(value = "出库单号")
    private String code;
    /**
     * 运单编号
     */
    @ApiModelProperty(value = "运单编号")
    private String billCode;
    /**
     * 出库类型
     */
    @ApiModelProperty(value = "出库类型")
    private String type;
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
     * 计划出库时间
     */
    @ApiModelProperty(value = "计划出库时间")
    private java.time.LocalDateTime planOutTime;
    /**
     * 货品数量
     */
    @ApiModelProperty(value = "货品数量")
    private Integer goodsNum;
    /**
     * 波次执行状态 0失败，1成功
     */
    @ApiModelProperty(value = "波次执行状态 0失败，1成功")
    private Integer waveStatus;
    /**
     * 承运商id
     */
    @ApiModelProperty(value = "承运商id")
    private Long carrierId;
    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String license;
    /**
     * 司机姓名
     */
    @ApiModelProperty(value = "司机姓名")
    private String driverName;
    /**
     * 司机电话
     */
    @ApiModelProperty(value = "司机电话")
    private String driverPhone;
    /**
     * 收货人姓名
     */
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;

    /**
     * 出库单状态 1新建、2拣货中、3已取消、4拣货完成、5交接中、6交接完成
     */
    @ApiModelProperty(value = "出库单状态 1新建、2拣货中、3已取消、4拣货完成、5交接中、6交接完成")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
