/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.dto.base;

import com.itheima.wms.model.entity.base.Warehouse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 仓库
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "仓库")
public class WarehouseDTO extends Warehouse {

}
