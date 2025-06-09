package com.itheima.wms.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局默认异常处理
     */
    @ExceptionHandler(Exception.class)
    public R<String> exceptionHandler(Exception ex) {
        //log.error("异常信息：{}", ex.getMessage());
        //ex.printStackTrace(); //输出到控制台
        return R.fail(ex.getMessage());
    }


    @ExceptionHandler(DuplicateKeyException.class)
    public R<String> duplicateKeyException(DuplicateKeyException ex) {
        log.warn("DuplicateKeyException", ex);
        try {
            String[] exMessage = ex.getMessage().split("###");
            String message = exMessage[1].split("Duplicate entry '")[1].split("' for key")[0];
            return R.fail(message + " 已存在!");
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }
}
