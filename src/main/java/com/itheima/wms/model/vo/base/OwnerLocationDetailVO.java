/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.vo.base;

import com.itheima.wms.model.entity.base.OwnerLocation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 货主库位
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OwnerLocationDetailVO", description = "货主库位")
public class OwnerLocationDetailVO extends OwnerLocation {

    @ApiModelProperty("仓库名称")
    private String warehouseName;
    @ApiModelProperty("库区名称")
    private String areaName;
    @ApiModelProperty("库位名称")
    private String locationName;
    @ApiModelProperty("库位编码")
    private String locationCode;

}
