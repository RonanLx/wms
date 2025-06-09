package com.itheima.wms.model.dto.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 仓库
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "仓库查询参数")
public class WarehouseQueryDTO {

    @ApiModelProperty("仓库编码")
    private String like_code;
    @ApiModelProperty("仓库名称")
    private String like_name;
    @ApiModelProperty("仓库状态")
    private Integer status;

    @ApiModelProperty("货主id")
    private Long ownerId;

    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
    @ApiModelProperty("排序字段，不用处理，可忽略")
    private String descs;

}
