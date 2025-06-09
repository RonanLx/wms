package com.itheima.wms.common.constants;

/**
 * 盘点任务状态
 */
public class CheckTaskStatus {

    //盘点任务状态：1待分配、2盘点中、3盘点已完成、4已生成损益、5已生成复盘
    public static final Integer NEW = 1;//待分配
    public static final Integer CHECK = 2;//盘点中
    public static final Integer CHECK_COMPLETED = 3;//盘点已完成
    public static final Integer INCREASE_DECREASE = 4;//已生成损益
    public static final Integer RECHECK = 5;//已生成复盘
}
