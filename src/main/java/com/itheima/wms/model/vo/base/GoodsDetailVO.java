/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.vo.base;

import com.itheima.wms.model.entity.base.Goods;
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
 * 货品管理
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "GoodsDetailVO", description = "货品管理")
public class GoodsDetailVO extends Goods {

    @ApiModelProperty(value = "货主名称")
    private String ownerName;
    @ApiModelProperty(value = "货主编号")
    private String ownerCode;
    @ApiModelProperty(value = "货品类型名称")
    private String goodsTypeName;
    @ApiModelProperty(value = "库区名称")
    private String areaName;
}
