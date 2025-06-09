package com.itheima.wms.controller.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.base.GoodsTypeDTO;
import com.itheima.wms.model.dto.base.GoodsTypeQueryDTO;
import com.itheima.wms.model.entity.base.GoodsType;
import com.itheima.wms.service.base.GoodsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 货品类型管理
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/goodsType")
@Api(value = "GoodsType", tags = "货品类型管理")
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * 分页查询货品类型
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询货品类型", notes = "分页查询货品类型")
    @GetMapping("/page")
    public R<PageBean<GoodsType>> page(GoodsTypeQueryDTO data) {
        log.info("分页查询货品类型:{}", data);
        PageBean<GoodsType> pageBean = goodsTypeService.page(data);
        return R.success(pageBean);
    }


    /**
     * 查询全部货品类型管理
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询全部货品类型管理", notes = "查询全部货品类型管理")
    @GetMapping("/list")
    public R<List<GoodsType>> list(GoodsTypeQueryDTO data) {
        log.info("查询全部货品类型管理:{}", data);
        List<GoodsType> goodsTypeList = goodsTypeService.list(data);
        return R.success(goodsTypeList);
    }

    /**
     * 模糊查询货品类型
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "模糊查询货品类型", notes = "模糊查询货品类型")
    @GetMapping("/vague")
    public R<List<GoodsType>> vague(GoodsTypeQueryDTO data) {
        log.info("模糊查询货品类型:{}", data);
        List<GoodsType> goodsTypes = goodsTypeService.list(data);
        return R.success(goodsTypes);
    }

    /**
     * 查询货品类型管理
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询货品类型管理", notes = "查询货品类型管理")
    @GetMapping("/{id}")
    public R<GoodsType> get(@PathVariable Long id) {
        log.info("查询货品类型管理:{}", id);
        GoodsType goodsType = goodsTypeService.getById(id);
        return R.success(goodsType);
    }

    /**
     * 新增货品类型管理
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增货品类型管理", notes = "新增货品类型管理")
    @PostMapping
    public R<Void> save(@RequestBody GoodsTypeDTO data) {
        log.info("新增货品类型管理:{}", data);
        goodsTypeService.save(data);
        return R.success();
    }

    /**
     * 修改货品类型管理
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改货品类型管理", notes = "修改货品类型管理")
    @PutMapping
    public R<Void> update(@RequestBody GoodsTypeDTO data) {
        log.info("修改货品类型管理:{}", data);
        goodsTypeService.updateById(data);
        return R.success();
    }

    /**
     * 删除货品类型管理
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除货品类型管理", notes = "删除货品类型管理")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除货品类型管理:{}", ids);
        goodsTypeService.removeByIds(ids);
        return R.success();
    }

}
