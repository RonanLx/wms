package com.itheima.wms.model.dto.biz;

import com.itheima.wms.model.entity.biz.Grounding;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 上架任务
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "上架任务")
public class GroundingDTO extends Grounding {

}
