package com.itheima.wms.model.dto.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 货品类型管理
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "货品类型查询参数")
public class GoodsTypeQueryDTO {

    @ApiModelProperty("货品类型编码")
    private String like_code;
    @ApiModelProperty("货品类型名称")
    private String like_name;
    @ApiModelProperty("货品模糊查询")
    private String params;


    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
    @ApiModelProperty("排序字段，不用处理，可忽略")
    private String descs;
}