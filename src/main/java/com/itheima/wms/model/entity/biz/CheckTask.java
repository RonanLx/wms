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
 * 盘点任务
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CheckTask", description = "盘点任务")
public class CheckTask extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 主记录id
     */
    @ApiModelProperty(value = "主记录id")
    private Long masterId;
    /**
     * 任务单号
     */
    @ApiModelProperty(value = "任务单号")
    private String code;
    /**
     * 盘点单号
     */
    @ApiModelProperty(value = "盘点单号")
    private String checkCode;
    /**
     * 计划作业时间
     */
    @ApiModelProperty(value = "计划作业时间")
    private java.time.LocalDateTime planTaskTime;
    /**
     * 货主id
     */
    @ApiModelProperty(value = "货主id")
    private Long ownerId;
    /**
     * 盘点维度 库位:KW、货品:HP
     */
    @ApiModelProperty(value = "盘点维度 库位:KW、货品:HP")
    private String dimension;
    /**
     * 盘点类型 随机盘点:SJPD、计划盘点:JSPD
     */
    @ApiModelProperty(value = "盘点类型 随机盘点:SJPD、计划盘点:JSPD")
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
     * 库存总数
     */
    @ApiModelProperty(value = "库存总数")
    private Integer stockTotal;
    /**
     * 盘点次数
     */
    @ApiModelProperty(value = "盘点次数")
    private Integer checkNum;
    /**
     * 负责人姓名
     */
    @ApiModelProperty(value = "负责人姓名")
    private String personName;
    /**
     * 盘点数量
     */
    @ApiModelProperty(value = "盘点数量")
    private Integer checkTotal;
    /**
     * 数量差异
     */
    @ApiModelProperty(value = "数量差异")
    private Integer differenceNum;
    /**
     * 任务状态 1待分配、2盘点中、3盘点已完成、4已生成损益、5已生成复盘
     */
    @ApiModelProperty(value = "任务状态 1待分配、2盘点中、3盘点已完成、4已生成损益、5已生成复盘")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
