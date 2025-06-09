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
 * 库位
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Location", description = "库位")
public class Location extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库id
     */
    @ApiModelProperty(value = "货主")
    private Long ownerId;

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
     * 温度类型 常温:CW、冷藏:LC、恒温:HW
     */
    @ApiModelProperty(value = "温度类型 常温:CW、冷藏:LC、恒温:HW")
    private String temperatureType;
    /**
     * 承重类型 重型:ZX、轻型:QX
     */
    @ApiModelProperty(value = "承重类型 重型:ZX、轻型:QX")
    private String bearingType;
    /**
     * 用途类型 入库缓存区：RKHCQ 出库缓存区：CKHCQ 存储区：CCQ  分拣区：FJQ 质检区：ZJQ
     */
    @ApiModelProperty(value = "用途类型 入库缓存区：RKHCQ 出库缓存区：CKHCQ 存储区：CCQ  分拣区：FJQ 质检区：ZJQ")
    private String useType;
    /**
     * 使用状态：0空闲 1占用
     */
    @ApiModelProperty(value = "使用状态：0空闲 1未满 2已满")
    private Integer useStatus;
    /**
     * 承载上限
     */
    @ApiModelProperty(value = "承载上限")
    private Integer maxNum;
    /**
     * 承载体积
     */
    @ApiModelProperty(value = "承载体积")
    private java.math.BigDecimal maxVolume;
    /**
     * 承载重量
     */
    @ApiModelProperty(value = "承载重量")
    private java.math.BigDecimal maxWeight;
    /**
     * 排
     */
    @ApiModelProperty(value = "排")
    private Integer locationRow;
    /**
     * 列
     */
    @ApiModelProperty(value = "列")
    private Integer locationColumn;
    /**
     * 层
     */
    @ApiModelProperty(value = "层")
    private Integer locationLayer;
    /**
     * 长
     */
    @ApiModelProperty(value = "长")
    private java.math.BigDecimal locationLength;
    /**
     * 宽
     */
    @ApiModelProperty(value = "宽")
    private java.math.BigDecimal locationWidth;
    /**
     * 高
     */
    @ApiModelProperty(value = "高")
    private java.math.BigDecimal locationHigh;
    /**
     * 状态 0 停用 1启用
     */
    @ApiModelProperty(value = "状态 0 停用 1启用")
    private Integer status;
    /**
     * 状态 0 停用 1启用
     */
    @ApiModelProperty(value = "状态 0 冻结 1正常")
    private Integer frozen;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
