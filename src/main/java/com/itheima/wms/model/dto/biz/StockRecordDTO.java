/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.dto.biz;

import com.itheima.wms.model.entity.biz.StockRecord;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 库存记录
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "库存记录")
public class StockRecordDTO extends StockRecord {

}
