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
 * 拣货任务
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Picking", description = "拣货任务")
public class Picking extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 主记录id
     */
    @ApiModelProperty(value = "主记录id")
    private Long masterId;
    /**
     * 拣货任务单号
     */
    @ApiModelProperty(value = "拣货任务单号")
    private String code;
    /**
     * 出库单号
     */
    @ApiModelProperty(value = "出库单号")
    private String outboundCode;
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
     * 拣货数量
     */
    @ApiModelProperty(value = "拣货数量")
    private Integer pickingNum;
    /**
     * 负责人姓名
     */
    @ApiModelProperty(value = "负责人姓名")
    private String personName;
    /**
     * 实拣数量
     */
    @ApiModelProperty(value = "实拣数量")
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
     * 拣货状态  1待分配、2拣货中、3拣货完成
     */
    @ApiModelProperty(value = "拣货状态  1待分配、2拣货中、3拣货完成")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
