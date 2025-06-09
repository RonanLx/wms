package com.itheima.wms.model.vo.base;

import com.itheima.wms.model.entity.base.Area;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 库区
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AreaDetailVO", description = "库区")
public class AreaDetailVO extends Area {

    @ApiModelProperty("仓库名称")
    private String warehouseName;


}
