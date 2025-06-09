/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


/**
 * 库区总览
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AreaOverviewVO", description = "库区总览")
public class AreaOverviewVO implements Serializable {

    @ApiModelProperty("总计")
    private Integer total;

    @ApiModelProperty("停用")
    private Integer stop;

    @ApiModelProperty("占用")
    private Integer use;

    @ApiModelProperty("空闲")
    private Integer free;

}
