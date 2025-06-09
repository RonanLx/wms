package com.itheima.wms.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class R<T> {

    public static final int SUCCESS_CODE = 0;
    public static final int FAIL_CODE = -1;

    @ApiModelProperty("响应编码")
    private int code;

    @ApiModelProperty("响应数据")
    private T data;

    @ApiModelProperty("提示消息")
    private String msg;

    public static <T> R<T> success(T data) {
        return new R<>(SUCCESS_CODE, data, "ok");
    }

    public static <T> R<T> success() {
        return new R<>(SUCCESS_CODE, null, "ok");
    }

    public static <T> R<T> fail(String msg) {
        return new R<>(FAIL_CODE, null, msg);
    }
}
