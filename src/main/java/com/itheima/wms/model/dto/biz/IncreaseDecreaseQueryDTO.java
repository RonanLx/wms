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
 * 损益单
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "损益单查询参数")
public class IncreaseDecreaseQueryDTO {

    @ApiModelProperty("损益单编号")
    private String code;
    @ApiModelProperty("损益单状态")
    private Integer status;
    @ApiModelProperty("货主名称")
    private String ownerName;
    @ApiModelProperty("货品名称")
    private String goodsName;
    @ApiModelProperty("任务id")
    private Long taskId;
    @ApiModelProperty("任务单号")
    private String taskCode;

    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
}
