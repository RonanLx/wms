package com.itheima.wms.model.dto.biz;

import com.itheima.wms.model.entity.biz.Handover;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 交接任务
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "交接任务")
public class HandoverDTO extends Handover {

}
