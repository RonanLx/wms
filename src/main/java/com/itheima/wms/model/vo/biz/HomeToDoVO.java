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
 * 首页代办
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "HomeToDoVO", description = "首页代办")
public class HomeToDoVO implements Serializable {

    @ApiModelProperty("入库单 代办数量")
    private Integer receiptNew;
    @ApiModelProperty("出库单 代办数量")
    private Integer outboundNew;
    @ApiModelProperty("盘点 代办数量")
    private Integer checkNew;


}
