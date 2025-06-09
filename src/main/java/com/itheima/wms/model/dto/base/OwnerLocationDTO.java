package com.itheima.wms.model.dto.base;

import com.itheima.wms.model.entity.base.OwnerLocation;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 货主-库位关联表
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "货主-库位关联表")
public class OwnerLocationDTO extends OwnerLocation {

}
