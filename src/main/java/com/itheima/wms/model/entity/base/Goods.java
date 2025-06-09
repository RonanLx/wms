package com.itheima.wms.model.entity.base;

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
 * 货品管理
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Goods", description = "货品管理")
public class Goods extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 货品类型ID
     */
    @ApiModelProperty(value = "货品类型ID")
    private Long goodsTypeId;
    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 条码
     */
    @ApiModelProperty(value = "条码")
    private String barCode;
    /**
     * 货主名称
     */
    @ApiModelProperty(value = "货主名称")
    private Long ownerId;
    /**
     * 质检方式 不处理:BCL、全检:QJ、抽检:CJ
     */
    @ApiModelProperty(value = "质检方式 不处理:BCL、全检:QJ、抽检:CJ")
    private String inspectionType;
    /**
     * 温度要求 常温:CW、冷藏:LC、恒温:HW
     */
    @ApiModelProperty(value = "温度要求 常温:CW、冷藏:LC、恒温:HW")
    private String temperatureType;
    /**
     * 承重要求 重型:ZX、轻型:QX 中型:BX
     */
    @ApiModelProperty(value = "承重要求 重型:ZX、轻型:QX、中型:BX")
    private String bearingType;
    /**
     * 体积
     */
    @ApiModelProperty(value = "体积")
    private java.math.BigDecimal volume;
    /**
     * 仓库id
     */
    @ApiModelProperty(value = "仓库id")
    private Long warehouseId;
    /**
     * 库区id
     */
    @ApiModelProperty(value = "库区id")
    private Long areaId;
    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private java.math.BigDecimal price;
    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;
    /**
     * 保质天数
     */
    @ApiModelProperty(value = "保质天数")
    private Integer guaranteeDay;
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
