package com.itheima.wms.model.dto.base;

import com.itheima.wms.model.entity.base.OwnerStrategy;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 货主-策略关联表
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "货主-策略关联表")
public class OwnerStrategyDTO extends OwnerStrategy {

}
