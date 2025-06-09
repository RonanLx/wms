package com.itheima.wms.model.vo.base;

import com.itheima.wms.model.entity.base.OwnerStrategy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


/**
 * 货主策略
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OwnerStrategyDetailVO", description = "货主策略")
public class OwnerStrategyDetailVO extends OwnerStrategy {

    @ApiModelProperty("仓库名称")
    private String warehouseName;
    @ApiModelProperty("库区名称")
    private String areaName;


    @ApiModelProperty(value = "上架策略")
    private String launchStrategyLabel = "默认B2B上架";
    @ApiModelProperty(value = "拣货策略")
    private String pickingStrategyLabel = "默认B2B拣货";
    @ApiModelProperty(value = "盘点策略")
    private String inventoryStrategyLabel = "默认B2B盘点";

}
