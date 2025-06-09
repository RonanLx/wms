package com.itheima.wms.model.dto.biz;

import com.itheima.wms.model.entity.biz.IncreaseDecrease;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 损益单
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "损益单")
public class IncreaseDecreaseDTO extends IncreaseDecrease {

}
