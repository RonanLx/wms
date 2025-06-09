package com.itheima.wms.model.dto.base;

import com.itheima.wms.model.entity.base.Carrier;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 承运商
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "承运商")
public class CarrierDTO extends Carrier {

}
