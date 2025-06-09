package com.itheima.wms.model.entity.biz;

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
 * 库位方案
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "LocationPlan", description = "库位方案")
public class LocationPlan extends Entity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库id")
    private Long warehouseId;

    @ApiModelProperty(value = "库区id")
    private Long areaId;

    @ApiModelProperty(value = "库位id")
    private Long locationId;

    @ApiModelProperty(value = "入库明细id")
    private Long receiptListId;

    @ApiModelProperty(value = "货品id")
    private Long goodsId;

    @ApiModelProperty(value = "数量")
    private Integer num;

    /**
     * 状态 0 停用 1启用
     */
    @ApiModelProperty(value = "状态 0 推荐 1使用")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
