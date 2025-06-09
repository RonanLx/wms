/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.controller.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.base.LocationDTO;
import com.itheima.wms.model.dto.base.LocationQueryDTO;
import com.itheima.wms.model.entity.base.Location;
import com.itheima.wms.model.vo.base.LocationDetailVO;
import com.itheima.wms.service.base.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 库位
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/location")
@Api(value = "Location", tags = "库位")
public class LocationController {

    @Autowired
    private LocationService locationService;

    /**
     * 分页查询库位详细信息
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "分页查询库位详细信息", notes = "分页查询库位详细信息")
    @GetMapping("/pageDetail")
    public R<PageBean<LocationDetailVO>> pageDetail(LocationQueryDTO data) {
        log.info("分页查询库位详细信息:{}", data);
        PageBean<LocationDetailVO> pageBean = locationService.pageDetail(data);
        return R.success(pageBean);
    }

    /**
     * 分页查询库位
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询库位", notes = "分页查询库位")
    @GetMapping("/page")
    public R<PageBean<Location>> page(LocationQueryDTO data) {
        log.info("分页查询库位:{}", data);
        PageBean<Location> pageBean = locationService.page(data);
        return R.success(pageBean);
    }


    /**
     * 查询全部库位
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询全部库位", notes = "查询全部库位")
    @GetMapping("/list")
    public R<List<Location>> list(LocationQueryDTO data) {
        log.info("查询全部库位:{}", data);
        List<Location> locationList = locationService.list(data);
        return R.success(locationList);
    }


    /**
     * 查询全部库位详细信息
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询全部库位详细信息", notes = "查询全部库位详细信息")
    @GetMapping("/listDetail")
    public R<List<LocationDetailVO>> listDetail(LocationQueryDTO data) {
        log.info("查询全部库位详细信息:{}", data);
        List<LocationDetailVO> detailVOList = locationService.listDetail(data);
        return R.success(detailVOList);
    }

    /**
     * 新增库位
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增库位", notes = "新增库位")
    @PostMapping
    public R<Void> save(@RequestBody LocationDTO data) {
        log.info("新增库位:{}", data);
        locationService.save(data);
        return R.success();
    }

    /**
     * 查询库位
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询库位", notes = "查询库位")
    @GetMapping("/{id}")
    public R<Location> get(@PathVariable Long id) {
        log.info("查询库位:{}", id);
        Location location = locationService.getById(id);
        return R.success(location);
    }

    /**
     * 修改库位
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改库位", notes = "修改库位")
    @PutMapping
    public R<Void> update(@RequestBody LocationDTO data) {
        log.info("修改库位:{}", data);
        locationService.updateById(data);
        return R.success();
    }

    /**
     * 删除库位
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除库位", notes = "删除库位")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除库位:{}", ids);
        locationService.removeByIds(ids);
        return R.success();
    }
}
