package com.itheima.wms.model.dto.biz;

import com.itheima.wms.model.entity.biz.Receiving;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 收货任务
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "收货任务")
public class ReceivingDTO extends Receiving {

}
