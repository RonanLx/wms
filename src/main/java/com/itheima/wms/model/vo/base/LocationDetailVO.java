/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.vo.base;

import com.itheima.wms.model.entity.base.Location;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 库位
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "LocationDetailVO", description = "库位")
public class LocationDetailVO extends Location {

    @ApiModelProperty("仓库名称")
    private String warehouseName;
    @ApiModelProperty("仓库编码")
    private String warehouseCode;

    @ApiModelProperty("库区名称")
    private String areaName;
    @ApiModelProperty("库区编码")
    private String areaCode;

    @ApiModelProperty("货品名称")
    private String goodsName;
    @ApiModelProperty("货品总数")
    private Integer goodsTotal;


}
