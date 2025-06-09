/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.common.constants;

public class ReceiptStatus {
    // 入库单状态 1新建、2收货中、3已取消、4收货完成、5上架中、6上架完成

    public static final Integer NEW = 1;//新建
    public static final Integer RECEIVING = 2;//收货中
    public static final Integer CANCEL = 3;//已取消
    public static final Integer RECEIVING_COMPLETED = 4;//收货完成
    public static final Integer GROUNDING = 5;//上架中
    public static final Integer GROUNDING_COMPLETED = 6;//上架完成
}
