/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.vo.biz;

import com.itheima.wms.model.entity.biz.CheckList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 盘点单清单明细
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CheckListDetailVO", description = "盘点单清单明细")
public class CheckListDetailVO extends CheckList {

    @ApiModelProperty("仓库名称")
    private String warehouseName;
    @ApiModelProperty("仓库编码")
    private String warehouseCode;
    @ApiModelProperty("库区名称")
    private String areaName;
    @ApiModelProperty("库区编码")
    private String areaCode;
    @ApiModelProperty("库位名称")
    private String locationName;
    @ApiModelProperty("库位编码")
    private String locationCode;
    @ApiModelProperty("货品名称")
    private String goodsName;
    @ApiModelProperty("货品编码")
    private String goodsCode;
    @ApiModelProperty("货品条码")
    private String goodsBarCode;
    @ApiModelProperty("货主名称")
    private String ownerName;
    @ApiModelProperty("货主编码")
    private String ownerCode;


}
