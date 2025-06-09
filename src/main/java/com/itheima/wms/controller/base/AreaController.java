/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.controller.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.base.AreaDTO;
import com.itheima.wms.model.dto.base.AreaQueryDTO;
import com.itheima.wms.model.entity.base.Area;
import com.itheima.wms.model.vo.base.AreaDetailVO;
import com.itheima.wms.model.vo.base.AreaLocationTreeVO;
import com.itheima.wms.model.vo.base.AreaOverviewVO;
import com.itheima.wms.service.base.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 库区
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/area")
@Api(value = "Area", tags = "库区")
public class AreaController {

    @Autowired
    private AreaService areaService;

    /**
     * 分页查询库区详细信息
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "分页查询库区详细信息", notes = "分页查询库区详细信息")
    @GetMapping("/pageDetail")
    public R<PageBean<AreaDetailVO>> pageDetail(AreaQueryDTO data) {
        log.info("分页查询库区详细信息:{}", data);
        PageBean<AreaDetailVO> pageBean = areaService.pageDetail(data);
        return R.success(pageBean);
    }

    /**
     * 分页查询库区
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询库区", notes = "分页查询库区")
    @GetMapping("/page")
    public R<PageBean<Area>> page(AreaQueryDTO data) {
        log.info("分页查询库区:{}", data);
        PageBean<Area> pageBean = areaService.page(data);
        return R.success(pageBean);
    }

    /**
     * 查询库区列表
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询库区列表", notes = "查询库区列表")
    @GetMapping("/list")
    public R<List<Area>> list(AreaQueryDTO data) {
        log.info("查询库区列表:{}", data);
        List<Area> areaList = areaService.list(data);
        return R.success(areaList);
    }

    /**
     * 查询库区总览
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询库区总览", notes = "查询库区总览")
    @GetMapping("overview/{id}")
    public R<AreaOverviewVO> overview(@PathVariable Long id) {
        log.info("查询库区总览:{}", id);
        AreaOverviewVO areaOverviewVO = areaService.areaOverview(id);
        return R.success(areaOverviewVO);
    }


    /**
     * 查询库区库位树
     *
     * @param warehouseId
     * @return
     */
    @ApiOperation(value = "查询库区库位树", notes = "查询库区库位树")
    @GetMapping("/tree")
    public R<List<AreaLocationTreeVO>> listTree(@RequestParam("仓库id") Long warehouseId) {
        log.info("查询库区库位树:{}", warehouseId);
        List<AreaLocationTreeVO> treeVOList = areaService.listAreaLocationTree(warehouseId);
        return R.success(treeVOList);
    }

    /**
     * 新增库区
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增库区", notes = "新增库区")
    @PostMapping
    public R<Void> save(@RequestBody AreaDTO data) {
        log.info("新增库区:{}", data);
        areaService.save(data);
        return R.success();
    }

    /**
     * 查询库区
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询库区", notes = "查询库区")
    @GetMapping("/{id}")
    public R<Area> get(@PathVariable Long id) {
        log.info("查询库区:{}", id);
        Area area = areaService.getById(id);
        return R.success(area);
    }

    /**
     * 修改库区
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改库区", notes = "修改库区")
    @PutMapping
    public R<Void> update(@RequestBody AreaDTO data) {
        log.info("修改库区:{}", data);
        areaService.updateById(data);
        return R.success();
    }

    /**
     * 删除库区
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除库区", notes = "删除库区")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除库区:{}", ids);
        areaService.removeByIds(ids);
        return R.success();
    }
}
