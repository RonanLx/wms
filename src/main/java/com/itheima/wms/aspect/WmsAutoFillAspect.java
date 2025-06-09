/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.aspect;

import com.itheima.wms.model.entity.Entity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 公共字段自动填充切面类
 */
@Slf4j
@Aspect
@Component
public class WmsAutoFillAspect {

    /**
     * 针对添加操作
     *
     * @param jp
     */
    @SneakyThrows
    @Before("execution(* com.itheima.wms.mapper..*.*.save*(com.itheima.wms.model.entity.Entity+))")
    public void beforeSaveAutoFill(JoinPoint jp) {
        Entity entity = (Entity) jp.getArgs()[0];
        entity.setCreateName("admin");
        entity.setUpdateName("admin");

        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 针对修改操作
     *
     * @param jp
     */
    @SneakyThrows
    @Before("execution(* com.itheima.wms.mapper..*.*.update*(com.itheima.wms.model.entity.Entity+))")
    public void beforeUpdateAutoFill(JoinPoint jp) {
        Entity entity = (Entity) jp.getArgs()[0];

        entity.setUpdateName("admin");
        entity.setUpdateTime(LocalDateTime.now());
    }
}
