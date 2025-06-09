/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.dto.base;

import com.itheima.wms.model.entity.base.GoodsType;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 货品类型管理
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "货品类型管理")
public class GoodsTypeDTO extends GoodsType {

}
