package com.itheima.wms.controller.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.base.WarehouseDTO;
import com.itheima.wms.model.dto.base.WarehouseQueryDTO;
import com.itheima.wms.model.entity.base.Warehouse;
import com.itheima.wms.service.base.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 仓库
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/warehouse")
@Api(value = "Warehouse", tags = "仓库")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    /**
     * 分页查询仓库
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询仓库", notes = "分页查询仓库")
    @GetMapping("/page")
    public R<PageBean<Warehouse>> page(WarehouseQueryDTO data) {
        log.info("分页查询仓库:{}", data);
        PageBean<Warehouse> pageBean = warehouseService.page(data);
        return R.success(pageBean);
    }

    /**
     * 查询全部仓库列表
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询全部仓库列表", notes = "查询全部仓库列表")
    @GetMapping("/list")
    public R<List<Warehouse>> list(WarehouseQueryDTO data) {
        log.info("查询全部仓库列表:{}", data);
        List<Warehouse> warehouseList = warehouseService.list(data);
        return R.success(warehouseList);
    }

    /**
     * 新增仓库
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增仓库", notes = "新增仓库")
    @PostMapping
    public R<Void> save(@RequestBody WarehouseDTO data) {
        log.info("新增仓库:{}", data);
        warehouseService.save(data);
        return R.success();
    }

    /**
     * 根据id查询仓库
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "根据id查询仓库", notes = "根据id查询仓库")
    @GetMapping("/{id}")
    public R<Warehouse> get(@PathVariable Long id) {
        log.info("根据id查询仓库:{}", id);
        Warehouse warehouse = warehouseService.getById(id);
        return R.success(warehouse);
    }

    /**
     * 修改仓库|停用启用
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改仓库|停用启用", notes = "修改仓库|停用启用")
    @PutMapping
    public R<Void> update(@RequestBody WarehouseDTO data) {
        log.info("修改仓库|停用启用:{}", data);
        warehouseService.updateById(data);
        return R.success();
    }

    /**
     * 删除仓库
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除仓库", notes = "删除仓库")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除仓库:{}", ids);
        warehouseService.removeByIds(ids);
        return R.success();
    }

}
