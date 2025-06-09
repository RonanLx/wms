/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Entity implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id")
    public Long id;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createName;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    public LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    public LocalDateTime updateTime;
}