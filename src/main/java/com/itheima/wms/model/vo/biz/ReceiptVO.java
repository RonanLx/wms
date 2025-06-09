/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.vo.biz;

import com.itheima.wms.model.entity.biz.Receipt;
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
 * 入库单
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReceiptVO", description = "入库单")
public class ReceiptVO extends Receipt {

    @ApiModelProperty(value = "货主名称")
    private String ownerName;
    @ApiModelProperty(value = "货主编号")
    private String ownerCode;
    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;
    @ApiModelProperty(value = "库区名称")
    private String areaName;
}
