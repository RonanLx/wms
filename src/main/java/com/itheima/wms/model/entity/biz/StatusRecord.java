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
 * 状态记录
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "StatusRecord", description = "状态记录")
public class StatusRecord extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 业务id
     */
    @ApiModelProperty(value = "业务id")
    private Long businessId;
    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型")
    private String businessType;
    /**
     * 业务模型
     */
    @ApiModelProperty(value = "业务模型")
    private String businessModel;
    /**
     * 业务状态
     */
    @ApiModelProperty(value = "业务状态")
    private String businessStatus;
    /**
     * 业务状态标签
     */
    @ApiModelProperty(value = "业务状态标签")
    private String businessStatusLabel;
    /**
     * 发生时间
     */
    @ApiModelProperty(value = "发生时间")
    private java.time.LocalDateTime happenTime;
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
