/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.dto.base;

import com.itheima.wms.model.entity.base.Area;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 库区
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "库区")
public class AreaDTO extends Area {

}


