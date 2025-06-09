package com.itheima.wms.controller.base;

import com.itheima.wms.common.R;
import com.itheima.wms.service.base.CodeFactoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 编号工厂
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/codeFactory")
@Api(value = "CodeFactory", tags = "编号工厂")
public class CodeFactoryController {

    @Autowired
    private CodeFactoryService codeFactoryService;

    /**
     * 获取下一个对象
     *
     * @param type 类型
     * @return 查询结果
     */
    @ApiOperation(value = "根据类型获取下一个编码", notes = "根据类型获取下一个编码")
    @GetMapping("/next/{type}")
    public R<String> get(@PathVariable String type) {
        log.info("根据类型获取下一个编码:{}", type);
        String code = codeFactoryService.getNextCodeByType(type);
        return R.success(code);
    }
}
