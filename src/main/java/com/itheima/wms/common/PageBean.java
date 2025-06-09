package com.itheima.wms.common;

import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {

    private List<T> records;
    private long total;
    private long size;

    public static <T> PageBean<T> builder(Page<T> page) {
        return new PageBean<>(page.getResult(), page.getTotal(), page.getPageSize());
    }
}
