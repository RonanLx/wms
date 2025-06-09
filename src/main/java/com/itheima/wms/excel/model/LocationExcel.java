package com.itheima.wms.excel.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class LocationExcel {

    @Excel(name = "所属仓库名称", orderNum = "1")
    private String warehouse;

    @Excel(name = "所属库区名称", orderNum = "2")
    private String area;

    @Excel(name = "库位名称", orderNum = "3")
    private String name;

    @Excel(name = "温度类型", orderNum = "4")
    private String temperatureType;

    @Excel(name = "承重类型", orderNum = "5")
    private String bearingType;

    @Excel(name = "用途属性", orderNum = "6")
    private String useType;

    @Excel(name = "停用状态", orderNum = "7")
    private String status;

    @Excel(name = "承载体积（m3）", orderNum = "9")
    private java.math.BigDecimal maxVolume;

    @Excel(name = "承载上限", orderNum = "10")
    private Integer maxNum;

    @Excel(name = "承载重量", orderNum = "11")
    private java.math.BigDecimal maxWeight;

    @Excel(name = "排", orderNum = "12")
    private Integer row;

    @Excel(name = "列", orderNum = "13")
    private Integer column;

    @Excel(name = "层", orderNum = "14")
    private Integer layer;

    @Excel(name = "长", orderNum = "15")
    private java.math.BigDecimal length;

    @Excel(name = "宽", orderNum = "16")
    private java.math.BigDecimal width;

    @Excel(name = "高", orderNum = "17")
    private java.math.BigDecimal high;
}
