package com.itheima.wms.model.vo.biz;

import com.itheima.wms.model.entity.biz.Receiving;
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
 * 收货任务详情
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReceivingDetailVO", description = "收货任务详情")
public class ReceivingDetailVO extends Receiving {
    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;
    @ApiModelProperty(value = "库区名称")
    private String areaName;
    @ApiModelProperty(value = "货主名称")
    private String ownerName;
    @ApiModelProperty(value = "货主编号")
    private String ownerCode;

}
