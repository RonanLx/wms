package com.itheima.wms.controller.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.base.OwnerDTO;
import com.itheima.wms.model.dto.base.OwnerQueryDTO;
import com.itheima.wms.model.entity.base.Owner;
import com.itheima.wms.model.vo.base.OwnerDetailVO;
import com.itheima.wms.service.base.OwnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 货主管理
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/owner")
@Api(value = "Owner", tags = "货主管理")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    /**
     * 分页查询货主管理
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询货主管理", notes = "分页查询货主管理")
    @GetMapping("/page")
    public R<PageBean<Owner>> page(OwnerQueryDTO data) {
        log.info("分页查询货主管理:{}", data);
        PageBean<Owner> pageBean = ownerService.page(data);
        return R.success(pageBean);
    }

    /**
     * 查询全部货主管理
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询全部货主管理", notes = "查询全部货主管理")
    @GetMapping("/list")
    public R<List<Owner>> list(OwnerQueryDTO data) {
        log.info("查询全部货主管理:{}", data);
        List<Owner> owners = ownerService.list(data);
        return R.success(owners);
    }

    /**
     * 模糊查询货主
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "模糊查询货主", notes = "模糊查询货主")
    @GetMapping("/vague")
    public R<List<Owner>> vague(OwnerQueryDTO data) {
        log.info("模糊查询货主:{}", data);
        List<Owner> owners = ownerService.list(data);
        return R.success(owners);
    }

    /**
     * 查询货主管理
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询货主管理", notes = "查询货主管理")
    @GetMapping("/{id}")
    public R<Owner> get(@PathVariable Long id) {
        log.info("查询货主管理:{}", id);
        Owner owner = ownerService.getById(id);
        return R.success(owner);
    }

    /**
     * 查询货主管理明细
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询货主管理明细", notes = "查询货主管理明细")
    @GetMapping("detail/{id}")
    public R<OwnerDetailVO> getDetail(@PathVariable Long id) {
        log.info("查询货主管理明细:{}", id);
        OwnerDetailVO ownerDetailVO = ownerService.getDetail(id);
        return R.success(ownerDetailVO);
    }

    /**
     * 新增货主管理
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增货主管理", notes = "新增货主管理")
    @PostMapping
    public R<Void> save(@RequestBody OwnerDTO data) {
        log.info("新增货主管理:{}", data);
        ownerService.save(data);
        return R.success();
    }

    /**
     * 修改货主管理
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改货主管理", notes = "修改货主管理")
    @PutMapping
    public R<Void> update(@RequestBody OwnerDTO data) {
        log.info("修改货主管理:{}", data);
        ownerService.updateById(data);
        return R.success();
    }

    /**
     * 删除货主管理
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除货主管理", notes = "删除货主管理")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除货主管理:{}", ids);
        ownerService.removeByIds(ids);
        return R.success();
    }

}
