package com.itheima.wms.model.dto.biz;

import com.itheima.wms.model.entity.biz.Check;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 盘点单
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "盘点单")
public class CheckDTO extends Check {

}
