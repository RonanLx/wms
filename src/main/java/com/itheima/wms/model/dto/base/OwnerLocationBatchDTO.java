package com.itheima.wms.model.dto.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


/**
 * 货主-库位关联表
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel(description = "货主-库位关联表")
public class OwnerLocationBatchDTO {

    @ApiModelProperty("仓库id,库区id,库位id")
    private List<String> idStrArray;//["1,3,10","1,3,11","1,3,12","1,4,13","1,4,14","1,5,15"]

    @ApiModelProperty("货主id")
    private Long ownerId;
}
