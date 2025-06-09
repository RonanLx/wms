package com.itheima.wms.model.dto.biz;

import com.itheima.wms.model.entity.biz.LocationPlan;
import com.itheima.wms.model.entity.biz.ReceiptList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


/**
 * 入库清单
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "入库清单")
public class ReceiptListDTO extends ReceiptList {

    @ApiModelProperty(value = "库位方案集合")
    private List<LocationPlan> locationPlanEntities;
}
