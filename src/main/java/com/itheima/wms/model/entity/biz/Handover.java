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
 * 交接任务
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Handover", description = "交接任务")
public class Handover extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 主记录id
     */
    @ApiModelProperty(value = "主记录id")
    private Long masterId;
    /**
     * 交接任务单号
     */
    @ApiModelProperty(value = "交接任务单号")
    private String code;
    /**
     * 出库单号
     */
    @ApiModelProperty(value = "出库单号")
    private String outboundCode;
    /**
     * 配送方式
     */
    @ApiModelProperty(value = "配送方式")
    private String deliveryType;
    /**
     * 承运商id
     */
    @ApiModelProperty(value = "承运商id")
    private Long carrierId;
    /**
     * 运单编号
     */
    @ApiModelProperty(value = "运单编号")
    private String billCode;
    /**
     * 交接员
     */
    @ApiModelProperty(value = "交接员")
    private String handoverName;
    /**
     * 交接完成时间
     */
    @ApiModelProperty(value = "交接完成时间")
    private java.time.LocalDateTime completionTime;
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
     * 交接状态 1新建、2交接中、3交接完成
     */
    @ApiModelProperty(value = "交接状态 1新建、2交接中、3交接完成")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
