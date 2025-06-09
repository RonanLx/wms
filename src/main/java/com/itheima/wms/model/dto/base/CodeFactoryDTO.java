package com.itheima.wms.model.dto.base;

import com.itheima.wms.model.entity.base.CodeFactory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 编号工厂
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "编号工厂")
public class CodeFactoryDTO extends CodeFactory {

}
