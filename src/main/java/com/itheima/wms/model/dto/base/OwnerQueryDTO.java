/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.dto.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 货主
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "货主查询条件")
public class OwnerQueryDTO {

    @ApiModelProperty("货主id")
    private Long id;
    @ApiModelProperty("货主编码")
    private String like_code;
    @ApiModelProperty("货主名称")
    private String like_name;
    @ApiModelProperty("联系人姓名")
    private String like_personName;
    @ApiModelProperty("货主模糊查询")
    private String params;

    @ApiModelProperty("当前页码")
    private Integer current;
    @ApiModelProperty("每页条数")
    private Integer size;
    @ApiModelProperty("排序字段，不用处理，可忽略")
    private String descs;
}
