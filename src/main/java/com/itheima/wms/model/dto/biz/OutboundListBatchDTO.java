/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.dto.biz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


/**
 * 批量出库清单
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "批量出库清单")
public class OutboundListBatchDTO {

    @ApiModelProperty("库存id集合")
    private List<Long> stockIds;

    @ApiModelProperty("主记录id")
    private Long masterId;
}
