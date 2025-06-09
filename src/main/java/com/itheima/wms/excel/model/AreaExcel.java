/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.excel.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class AreaExcel {

    @Excel(name = "所属仓库名称", orderNum = "1")
    private String warehouse;
    @Excel(name = "库区名称", orderNum = "2")
    private String name;
    @Excel(name = "温度类型", orderNum = "3")
    private String temperatureType;
    @Excel(name = "承重类型", orderNum = "4")
    private String bearingType;
    @Excel(name = "库区类型", orderNum = "5")
    private String useType;
    @Excel(name = "用途属性", orderNum = "6")
    private String type;
    @Excel(name = "库区状态", orderNum = "7")
    private String status;
    @Excel(name = "负责人", orderNum = "8")
    private String personName;
    @Excel(name = "联系电话", orderNum = "9")
    private String phone;
}
