package com.itheima.wms.model.vo.base;

import com.itheima.wms.model.entity.base.Owner;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OwnerDetailVO", description = "货主明细")
public class OwnerDetailVO extends Owner {

    @ApiModelProperty(value = "货品总量")
    private Integer goodsTotal;
}
