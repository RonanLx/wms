package com.itheima.wms.model.vo.biz;

import com.itheima.wms.model.entity.biz.Handover;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 展示层
 * 交接任务详情
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "HandoverDetailVO", description = "交接任务详情")
public class HandoverDetailVO extends Handover {

    @ApiModelProperty(value = "承运商名称")
    private String carrierName;
}
