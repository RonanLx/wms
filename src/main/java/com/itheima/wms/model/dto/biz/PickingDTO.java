package com.itheima.wms.model.dto.biz;

import com.itheima.wms.model.entity.biz.Picking;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 拣货任务
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "拣货任务")
public class PickingDTO extends Picking {

}
