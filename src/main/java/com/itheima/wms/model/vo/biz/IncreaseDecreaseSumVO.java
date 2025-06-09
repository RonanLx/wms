package com.itheima.wms.model.vo.biz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 损益单
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "IncreaseDecreaseSumVO", description = "损益单")
public class IncreaseDecreaseSumVO implements Serializable {

    @ApiModelProperty(value = "总计")
    private Integer total;
    @ApiModelProperty(value = "总金额")
    private BigDecimal money;

}
