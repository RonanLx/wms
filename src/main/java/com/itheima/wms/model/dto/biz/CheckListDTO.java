/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.dto.biz;

import com.itheima.wms.model.entity.biz.CheckList;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 盘点清单
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "盘点清单")
public class CheckListDTO extends CheckList {

}
