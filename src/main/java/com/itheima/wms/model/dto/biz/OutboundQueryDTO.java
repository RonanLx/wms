/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.dto.biz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 出库单
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "出库单查询参数")
public class OutboundQueryDTO {

    @ApiModelProperty("出库单编号")
    private String code;
    @ApiModelProperty("运单编号")
    private String billCode;
    @ApiModelProperty("货主名称")
    private String ownerName;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
}
