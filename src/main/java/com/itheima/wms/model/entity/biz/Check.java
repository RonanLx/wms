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
 * 盘点单
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Check", description = "盘点单")
public class Check extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 盘点单号
     */
    @ApiModelProperty(value = "盘点单号")
    private String code;
    /**
     * 货主id
     */
    @ApiModelProperty(value = "货主id")
    private Long ownerId;
    /**
     * 计划盘点时间
     */
    @ApiModelProperty(value = "计划盘点时间")
    private java.time.LocalDateTime planCheckTime;
    /**
     * 盘点原因 规划:GH、货主:HZ、差异:CY
     */
    @ApiModelProperty(value = "盘点原因 规划:GH、货主:HZ、差异:CY")
    private String reason;
    /**
     * 盘点维度 库位:KW、货品:HP
     */
    @ApiModelProperty(value = "盘点维度 库位:KW、货品:HP")
    private String dimension;
    /**
     * 盘点类型 随机盘点:SJPD、计划盘点:JHPD
     */
    @ApiModelProperty(value = "盘点类型 随机盘点:SJPD、计划盘点:JHPD")
    private String type;
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
     * 盘点单状态 1新建、2一盘中、3一盘完成、4复盘中、5复盘完成、6已取消
     */
    @ApiModelProperty(value = "盘点单状态 1新建、2一盘中、3一盘完成、4复盘中、5复盘完成、6已取消")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
