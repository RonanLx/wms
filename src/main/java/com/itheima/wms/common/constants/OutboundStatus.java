/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.common.constants;

public class OutboundStatus {
    // 出库单状态 1新建、2拣货中、3已取消、4拣货完成、5交接中、6交接完成

    public static final Integer NEW = 1;//新建
    public static final Integer PICKING = 2;//拣货中
    public static final Integer CANCEL = 3;//已取消
    public static final Integer PICKING_COMPLETED = 4;//拣货完成
    public static final Integer HANDOVER = 5;//拣货中
    public static final Integer HANDOVER_COMPLETED = 6;//交接完成
}
