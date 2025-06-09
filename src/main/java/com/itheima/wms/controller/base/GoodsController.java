package com.itheima.wms.controller.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.base.GoodsDTO;
import com.itheima.wms.model.dto.base.GoodsQueryDTO;
import com.itheima.wms.model.entity.base.Goods;
import com.itheima.wms.model.vo.base.GoodsDetailVO;
import com.itheima.wms.service.base.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 货品管理
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/goods")
@Api(value = "Goods", tags = "货品管理")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询货品详情
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "分页查询货品详情", notes = "分页查询货品详情")
    @GetMapping("/pageDetail")
    public R<PageBean<GoodsDetailVO>> pageDetail(GoodsQueryDTO data) {
        log.info("分页查询货品详情:{}", data);
        PageBean<GoodsDetailVO> pageBean = goodsService.pageDetail(data);
        return R.success(pageBean);
    }

    /**
     * 分页查询货品管理
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询货品管理", notes = "分页查询货品管理")
    @GetMapping("/page")
    public R<PageBean<Goods>> page(GoodsQueryDTO data) {
        log.info("分页查询货品管理:{}", data);
        PageBean<Goods> pageBean = goodsService.page(data);
        return R.success(pageBean);
    }

    /**
     * 查询全部货品管理
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询全部货品管理", notes = "查询全部货品管理")
    @GetMapping("/list")
    public R<List<Goods>> list(GoodsQueryDTO data) {
        log.info("查询全部货品管理:{}", data);
        List<Goods> goodsList = goodsService.list(data);
        return R.success(goodsList);
    }

    /**
     * 模糊查询货品
     * @param data
     * @return
     */
    @ApiOperation(value = "模糊查询货品", notes = "模糊查询货品")
    @GetMapping("/vague")
    public R<List<Goods>> vague(GoodsQueryDTO data) {
        log.info("模糊查询货品:{}", data);
        List<Goods> goodsList = goodsService.list(data);
        return R.success(goodsList);
    }

    /**
     * 新增货品管理
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增货品管理", notes = "新增货品管理")
    @PostMapping
    public R<Void> save(@RequestBody GoodsDTO data) {
        log.info("新增货品管理:{}", data);
        goodsService.save(data);
        return R.success();
    }

    /**
     * 查询货品管理
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询货品管理", notes = "查询货品管理")
    @GetMapping("/{id}")
    public R<Goods> get(@PathVariable Long id) {
        log.info("查询货品管理:{}", id);
        Goods goods = goodsService.getById(id);
        return R.success(goods);
    }

    /**
     * 修改货品管理
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改货品管理", notes = "修改货品管理")
    @PutMapping
    public R<Void> update(@RequestBody GoodsDTO data) {
        log.info("修改货品管理:{}", data);
        goodsService.updateById(data);
        return R.success();
    }

    /**
     * 删除货品管理
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除货品管理", notes = "删除货品管理")
    @DeleteMapping
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除货品管理:{}", ids);
        goodsService.removeByIds(ids);
        return R.success();
    }

}
