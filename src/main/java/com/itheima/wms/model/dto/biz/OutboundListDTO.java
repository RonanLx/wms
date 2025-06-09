/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.dto.biz;

import com.itheima.wms.model.entity.biz.OutboundList;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 出库清单
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "出库清单")
public class OutboundListDTO extends OutboundList {

}
