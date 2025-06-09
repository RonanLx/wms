/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.common.constants;


import cn.hutool.core.map.MapUtil;

import java.util.Map;

/**
 * 常量表
 */
public class ConstantTable {

    //温度类型 常温:CW、冷藏:LC、恒温:HW
    public static final Map<String, String> TEMPERATURE_TYPE_NAME = Map.of(
            "CW", "常温",
            "LC", "冷藏",
            "HW", "恒温");


    public static final Map<String, String> TEMPERATURE_TYPE_CODE = Map.of(
            "常温", "CW",
            "冷藏", "LC",
            "恒温", "HW");

    //承重类型 重型:ZX、中型:OX、轻型:QX
    public static final Map<String, String> BEARING_TYPE_NAME = Map.of(
            "ZX", "重型货架",
            "OX", "中型货架",
            "QX", "轻型货架");

    public static final Map<String, String> BEARING_TYPE_CODE = Map.of(
            "重型货架", "ZX",
            "中型货架", "OX",
            "轻型货架", "QX");

    //用途类型 入库缓存区：RKHCQ 出库缓存区：CKHCQ 存储区：CCQ  分拣区：FJQ 质检区：ZJQ
    public static final Map<String, String> USE_TYPE_NAME = Map.of(
            "RKHCQ", "入库缓存区",
            "CKHCQ", "出库缓存区",
            "CCQ", "存储区",
            "FJQ", "分拣区",
            "ZJQ", "质检区");

    public static final Map<String, String> USE_TYPE_CODE = Map.of(
            "入库缓存区", "RKHCQ",
            "出库缓存区", "CKHCQ",
            "存储区", "CCQ",
            "分拣区", "FJQ",
            "质检区", "ZJQ");

    //状态 0 停用 1启用
    public static final Map<Integer, String> STATUS_NAME = Map.of(
            0, "停用",
            1, "可用");

    public static final Map<String, Integer> STATUS_CODE = Map.of(
            "停用", 0,
            "可用", 1);

    //使用状态 0 空闲 1占用
    public static final Map<Integer, String> USE_STATUS_NAME = Map.of(
            0, "空闲",
            1, "占用",
            2, "已满");

    public static final Map<String, Integer> USE_STATUS_CODE = Map.of(
            "空闲", 0,
            "未满", 1,
            "已满", 2);

}
