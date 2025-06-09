/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.vo.biz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * <p>
 * 展示层
 * 盘点清单总计
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CheckListSumVO", description = "盘点清单总计")
public class CheckListSumVO implements Serializable {

    @ApiModelProperty(value = "总计总计")
    private Integer locationTotal;
    @ApiModelProperty(value = "货品总计")
    private Integer goodsTotal;
}
