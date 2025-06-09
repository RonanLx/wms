package com.itheima.wms.model.vo.biz;

import com.itheima.wms.model.entity.biz.IncreaseDecrease;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 损益单
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "IncreaseDecreaseDetailVO", description = "损益单")
public class IncreaseDecreaseDetailVO extends IncreaseDecrease {

    @ApiModelProperty("仓库名称")
    private String warehouseName;
    @ApiModelProperty("库区名称")
    private String areaName;
    @ApiModelProperty("库位名称")
    private String locationName;
    @ApiModelProperty("货品名称")
    private String goodsName;
    @ApiModelProperty("货主名称")
    private String ownerName;

}
