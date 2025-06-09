package com.itheima.wms.controller.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.base.OwnerLocationBatchDTO;
import com.itheima.wms.model.dto.base.OwnerLocationDTO;
import com.itheima.wms.model.dto.base.OwnerLocationQueryDTO;
import com.itheima.wms.model.entity.base.OwnerLocation;
import com.itheima.wms.model.vo.base.OwnerLocationDetailVO;
import com.itheima.wms.service.base.OwnerLocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 货主库位关系
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/ownerLocation")
@Api(value = "OwnerLocation", tags = "货主库位关系")
public class OwnerLocationController {

    @Autowired
    private OwnerLocationService ownerLocationService;

    /**
     * 分页查询货主库位信息
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询货主库位信息", notes = "分页查询货主库位信息")
    @GetMapping("/page")
    public R<PageBean<OwnerLocation>> page(OwnerLocationQueryDTO data) {
        log.info("分页查询货主库位信息:{}", data);
        PageBean<OwnerLocation> pageBean = ownerLocationService.page(data);
        return R.success(pageBean);
    }

    /**
     * 分页查询货主库位详细信息
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询货主库位详细信息", notes = "分页查询货主库位详细信息")
    @GetMapping("/pageDetail")
    public R<PageBean<OwnerLocationDetailVO>> pageDetail(OwnerLocationQueryDTO data) {
        log.info("分页查询货主库位详细信息:{}", data);
        PageBean<OwnerLocationDetailVO> pageBean = ownerLocationService.pageDetail(data);
        return R.success(pageBean);
    }

    /**
     * 查询全部货主库位信息
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询全部货主库位信息", notes = "查询全部货主库位信息")
    @GetMapping("/list")
    public R<List<OwnerLocation>> list(OwnerLocationQueryDTO data) {
        log.info("查询全部货主库位信息:{}", data);
        List<OwnerLocation> ownerLocations = ownerLocationService.list(data);
        return R.success(ownerLocations);
    }

    /**
     * 查询货主库位信息
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询货主库位信息", notes = "查询货主库位信息")
    @GetMapping("/{id}")
    public R<OwnerLocation> get(@PathVariable Long id) {
        log.info("查询货主库位信息:{}", id);
        OwnerLocation ownerLocation = ownerLocationService.getById(id);
        return R.success(ownerLocation);
    }

    /**
     * 新增货主库位信息
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增货主库位信息", notes = "新增货主库位信息")
    @PostMapping
    public R<Void> save(@RequestBody OwnerLocationDTO data) {
        log.info("新增货主库位信息:{}", data);
        ownerLocationService.save(data);
        return R.success();
    }

    /**
     * 批量新增货主库位信息
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "批量新增货主库位信息", notes = "批量新增货主库位信息")
    @PostMapping("batch")
    public R<Void> batchSave(@RequestBody OwnerLocationBatchDTO data) {
        log.info("批量新增货主库位信息:{}", data);
        ownerLocationService.saveBatch(data);
        return R.success();
    }

    /**
     * 修改货主库位信息
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改货主库位信息", notes = "修改货主库位信息")
    @PutMapping
    public R<Void> update(@RequestBody OwnerLocationDTO data) {
        log.info("修改货主库位信息:{}", data);
        ownerLocationService.updateById(data);
        return R.success();
    }

    /**
     * 删除货主库位信息
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除货主库位信息", notes = "删除货主库位信息")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除货主库位信息:{}", ids);
        ownerLocationService.removeByIds(ids);
        return R.success();
    }

}
