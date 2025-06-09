/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.controller.base;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.base.OwnerStrategyDTO;
import com.itheima.wms.model.entity.base.OwnerStrategy;
import com.itheima.wms.model.vo.base.OwnerStrategyDetailVO;
import com.itheima.wms.service.base.OwnerStrategyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * 货主策略关系
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/ownerStrategy")
@Api(value = "OwnerStrategy", tags = "货主策略关系")
public class OwnerStrategyController {

    @Autowired
    private OwnerStrategyService ownerStrategyService;

    /**
     * 分页查询货主-策略关联表
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询货主策略关系", notes = "分页查询货主策略关系")
    @GetMapping("/page")
    public R<PageBean<OwnerStrategy>> page(@RequestParam Map data) {
        log.info("分页查询货主策略关系:{}", data);
        PageBean<OwnerStrategy> pageBean = ownerStrategyService.page(data);
        return R.success(pageBean);
    }

    /**
     * 分页查询货主-库位关联表
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询货主策略关系明细", notes = "分页查询货主策略关系明细")
    @GetMapping("/pageDetail")
    public R<PageBean<OwnerStrategyDetailVO>> pageDetail(@RequestParam Map data) {
        log.info("分页查询货主策略关系明细:{}", data);
        PageBean<OwnerStrategyDetailVO> pageBean = ownerStrategyService.pageDetail(data);
        return R.success(pageBean);
    }


    @ApiOperation(value = "查询全部货主策略关系", notes = "查询全部货主策略关系")
    @GetMapping("/list")
    public R<List<OwnerStrategy>> list(@RequestParam Map data) {
        log.info("查询全部货主策略关系:{}", data);
        List<OwnerStrategy> ownerStrategyList = ownerStrategyService.list(data);
        return R.success(ownerStrategyList);
    }

    /**
     * 查询货主-策略关联表
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询货主策略关系", notes = "查询货主策略关系")
    @GetMapping("/{id}")
    public R<OwnerStrategy> get(@PathVariable Long id) {
        log.info("查询货主策略关系:{}", id);
        OwnerStrategy ownerStrategy = ownerStrategyService.getById(id);
        return R.success(ownerStrategy);
    }

    /**
     * 新增货主-策略关联表
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增货主策略关系", notes = "新增货主策略关系")
    @PostMapping
    public R<Void> save(@RequestBody OwnerStrategyDTO data) {
        log.info("新增货主策略关系:{}", data);
        ownerStrategyService.save(data);
        return R.success();
    }

    /**
     * 修改货主-策略关联表
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改货主策略关系", notes = "修改货主策略关系")
    @PutMapping
    public R<Void> update(@RequestBody OwnerStrategyDTO data) {
        log.info("修改货主策略关系:{}", data);
        ownerStrategyService.updateById(data);
        return R.success();
    }

    /**
     * 删除货主-策略关联表
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除货主策略关系", notes = "删除货主策略关系")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除货主策略关系:{}", ids);
        ownerStrategyService.removeByIds(ids);
        return R.success();
    }

}
