package com.itheima.wms.model.dto.biz;

import com.itheima.wms.model.entity.biz.Stock;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 库存
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "库存")
public class StockDTO extends Stock {

}
