/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.vo.biz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 展示层
 * 批量请求返回值
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "BatchVO", description = "批量请求返回值")
public class BatchVO implements Serializable {

    @ApiModelProperty(value = "结果")
    @Singular
    private List<String> results;

    @ApiModelProperty(value = "异常")
    @Singular
    private List<String> errors;

}
