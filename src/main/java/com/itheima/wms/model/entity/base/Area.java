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
 * 库区
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Area", description = "库区")
public class Area extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库id
     */
    @ApiModelProperty(value = "仓库id")
    private Long warehouseId;

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
     * 负责人姓名
     */
    @ApiModelProperty(value = "负责人姓名")
    private String personName;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String phone;

    /**
     * 库位数量
     */
    @ApiModelProperty(value = "库位数量")
    private Integer includedNum;

    /**
     * 状态 0停用 1启用
     */
    @ApiModelProperty(value = "状态 0停用 1启用")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
