/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.controller.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.base.CarrierDTO;
import com.itheima.wms.model.dto.base.CarrierQueryDTO;
import com.itheima.wms.model.entity.base.Carrier;
import com.itheima.wms.service.base.CarrierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 承运商
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/carrier")
@Api(value = "Carrier", tags = "承运商")
public class CarrierController {

    @Autowired
    private CarrierService carrierService;

    /**
     * 分页查询承运商
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询承运商", notes = "分页查询承运商")
    @GetMapping("/page")
    public R<PageBean<Carrier>> page(CarrierQueryDTO data) {
        log.info("分页查询承运商:{}", data);
        PageBean<Carrier> pageBean = carrierService.page(data);
        return R.success(pageBean);
    }

    /**
     * 查询全部承运商
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询全部承运商", notes = "查询全部承运商")
    @GetMapping("/list")
    public R<List<Carrier>> list(CarrierQueryDTO data) {
        log.info("查询全部承运商:{}", data);
        List<Carrier> carrierList = carrierService.list(data);
        return R.success(carrierList);
    }

    /**
     * 查询承运商
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询承运商", notes = "查询承运商")
    @GetMapping("/{id}")
    public R<Carrier> get(@PathVariable Long id) {
        log.info("查询承运商:{}", id);
        Carrier carrier = carrierService.getById(id);
        return R.success(carrier);
    }

    /**
     * 新增承运商
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增承运商", notes = "新增承运商")
    @PostMapping
    public R<Void> save(@RequestBody CarrierDTO data) {
        log.info("新增承运商:{}", data);
        carrierService.save(data);
        return R.success();
    }

    /**
     * 修改承运商
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改承运商", notes = "修改承运商")
    @PutMapping
    public R<Void> update(@RequestBody CarrierDTO data) {
        log.info("修改承运商:{}", data);
        carrierService.updateById(data);
        return R.success();
    }

    /**
     * 删除承运商
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除承运商", notes = "删除承运商")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除承运商:{}", ids);
        carrierService.removeByIds(ids);
        return R.success();
    }

}
