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
 * 仓库
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Warehouse", description = "仓库")
public class Warehouse extends Entity {

    private static final long serialVersionUID = 1L;

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
     * 类型 中转仓:ZZ、加工仓:JG、储备仓:CB、冷藏仓:LC
     */
    @ApiModelProperty(value = "类型 中转仓:ZZ、加工仓:JG、储备仓:CB、冷藏仓:LC")
    private String type;

    @ApiModelProperty(value = "省市区描述")
    private String location;
    /**
     * 省
     */
    @ApiModelProperty(value = "省")
    private String province;
    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String city;
    /**
     * 区
     */
    @ApiModelProperty(value = "区")
    private String area;
    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String address;
    /**
     * 面积
     */
    @ApiModelProperty(value = "面积")
    private java.math.BigDecimal surface;
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
     * 库区数量
     */
    @ApiModelProperty(value = "库区数量")
    private Integer includedNum;
    /**
     * 状态 0 停用 1启用
     */
    @ApiModelProperty(value = "状态 0 停用 1启用")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
