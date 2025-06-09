/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.dto.base;

import com.itheima.wms.model.entity.base.Location;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 库位
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "库位")
public class LocationDTO extends Location {

}
