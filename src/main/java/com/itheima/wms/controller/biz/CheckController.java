package com.itheima.wms.controller.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.biz.CheckDTO;
import com.itheima.wms.model.entity.biz.Check;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.CheckDetailVO;
import com.itheima.wms.service.biz.CheckService;
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
 * 盘点单
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/check")
@Api(value = "Check", tags = "盘点单")
public class CheckController {

    @Autowired
    private CheckService checkService;

    /**
     * 分页查询盘点单
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询盘点单", notes = "分页查询盘点单")
    @GetMapping("/page")
    public R<PageBean<Check>> page(@RequestParam Map data) {
        PageBean<Check> pageBean = checkService.page(data);
        return R.success(pageBean);
    }

    /**
     * 分页查询盘点单明细
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "分页查询盘点单明细", notes = "分页查询盘点单明细")
    @GetMapping("/pageDetail")
    public R<PageBean<CheckDetailVO>> pageDetail(@RequestParam Map data) {
        PageBean<CheckDetailVO> pageBean = checkService.pageDetail(data);
        return R.success(pageBean);
    }

    /**
     * 查询全部盘点单
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询全部盘点单", notes = "查询全部盘点单")
    @GetMapping("/list")
    public R<List<Check>> list(@RequestParam Map data) {
        List<Check> checkArray = checkService.list(data);
        return R.success(checkArray);
    }

    /**
     * 查询盘点单
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询盘点单", notes = "查询盘点单")
    @GetMapping("/{id}")
    public R<Check> get(@PathVariable Long id) {
        return R.success(checkService.getById(id));
    }

    /**
     * 查询详情
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询详情", notes = "查询详情")
    @GetMapping("detail/{id}")
    public R<CheckDetailVO> getDetail(@PathVariable Long id) {
        return R.success(checkService.getDetail(id));
    }

    /**
     * 新增盘点单
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增盘点单", notes = "新增盘点单不为空的字段")
    @PostMapping
    public R<Void> save(@RequestBody CheckDTO data) {
        checkService.save(data);
        return R.success();
    }

    /**
     * 生成盘点任务
     *
     * @param ids 新增对象
     * @return 生成 结果
     */
    @ApiOperation(value = "生成盘点任务", notes = "生成盘点任务")
    @PostMapping("task")
    public R<BatchVO> checkTask(@RequestBody List<Long> ids) {
        BatchVO batchVO = checkService.task(ids);
        return R.success(batchVO);
    }

    /**
     * 修改盘点单
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改盘点单", notes = "修改盘点单不为空的字段")
    @PutMapping
    public R<Void> update(@RequestBody CheckDTO data) {
        checkService.updateById(data);
        return R.success();
    }

    /**
     * 取消盘点单
     *
     * @return 取消结果
     */
    @ApiOperation(value = "取消盘点单", notes = "取消盘点单")
    @PutMapping("cancel")
    public R<Void> cancel(@RequestBody CheckDTO data) {
        checkService.cancel(data);
        return R.success();
    }

    /**
     * 删除盘点单
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除盘点单", notes = "根据id物理删除盘点单")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        checkService.removeByIds(ids);
        return R.success();
    }

}
