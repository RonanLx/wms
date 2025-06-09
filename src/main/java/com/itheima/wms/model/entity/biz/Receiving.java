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
 * 收货任务
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Receiving", description = "收货任务")
public class Receiving extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 入库单id
     */
    @ApiModelProperty(value = "入库单id")
    private Long masterId;
    /**
     * 收货任务单号
     */
    @ApiModelProperty(value = "收货任务单号")
    private String code;
    /**
     * 入库单号
     */
    @ApiModelProperty(value = "入库单号")
    private String receiptCode;
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
     * 预计到货数
     */
    @ApiModelProperty(value = "预计到货数")
    private Integer planNum;
    /**
     * 收货人姓名
     */
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;
    /**
     * 实收货数
     */
    @ApiModelProperty(value = "实收货数")
    private Integer realNum;
    /**
     * 数量差异
     */
    @ApiModelProperty(value = "数量差异")
    private Integer differenceNum;
    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    private java.time.LocalDateTime completionTime;
    /**
     * 收货状态 1待分配、2收货中、3收货完成、4已取消
     */
    @ApiModelProperty(value = "收货状态 1待分配、2收货中、3收货完成、4已取消")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
