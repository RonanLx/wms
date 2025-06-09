/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.common.constants;

/**
 * 盘点单状态
 */
public class CheckStatus {

    //盘点单状态 1新建、2一盘中、3一盘完成、4复盘中、5复盘完成、6已取消
    public static final Integer NEW = 1; //新建
    public static final Integer CHECK = 2; //一盘中
    public static final Integer CHECK_COMPLETED = 3; //一盘完成
    public static final Integer RECHECK = 4; //复盘中
    public static final Integer RECHECK_COMPLETED = 5; //复盘完成
    public static final Integer CANCEL = 6; //已取消
}
