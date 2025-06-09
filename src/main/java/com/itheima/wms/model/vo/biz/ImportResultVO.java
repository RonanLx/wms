package com.itheima.wms.model.vo.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ImportResultVO {
    private Integer total;
    private Integer success;
    private Integer fail;
    private Collection<String> message;
}
