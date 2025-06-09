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
 * 出库清单
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "出库清单查询参数")
public class OutboundListQueryDTO {


    @ApiModelProperty("出库单id")
    private Integer masterId;

    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
}
