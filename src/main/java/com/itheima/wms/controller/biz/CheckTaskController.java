package com.itheima.wms.controller.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.common.constants.CheckTaskStatus;
import com.itheima.wms.model.dto.biz.CheckTaskDTO;
import com.itheima.wms.model.dto.biz.CheckTaskDistributeDTO;
import com.itheima.wms.model.entity.biz.CheckTask;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.CheckTaskDetailVO;
import com.itheima.wms.service.biz.CheckTaskService;
import com.itheima.wms.service.biz.StatusRecordService;
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
 * 盘点任务
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/checkTask")
@Api(value = "CheckTask", tags = "盘点任务")
public class CheckTaskController {

    @Autowired
    private CheckTaskService checkTaskService;
    @Autowired
    private StatusRecordService statusRecordService;

    /**
     * 分页查询盘点任务
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询盘点任务", notes = "分页查询盘点任务")
    @GetMapping("/page")
    public R<PageBean<CheckTask>> page(@RequestParam Map data) {
        PageBean<CheckTask> pageBean = checkTaskService.page(data);
        return R.success(pageBean);
    }

    @ApiOperation(value = "分页查询盘点任务明细", notes = "分页查询盘点任务明细")
    @GetMapping("/pageDetail")
    public R<PageBean<CheckTaskDetailVO>> pageDetail(@RequestParam Map data) {
        PageBean<CheckTaskDetailVO> pageBean = checkTaskService.pageDetail(data);
        return R.success(pageBean);
    }

    @ApiOperation(value = "查询全部盘点任务", notes = "查询全部盘点任务")
    @GetMapping("/list")
    public R<List<CheckTask>> list(@RequestParam Map data) {
        List<CheckTask> checkTaskArray = checkTaskService.list(data);
        return R.success(checkTaskArray);
    }

    /**
     * 查询盘点任务
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询盘点任务", notes = "查询盘点任务")
    @GetMapping("/{id}")
    public R<CheckTask> get(@PathVariable Long id) {
        return R.success(checkTaskService.getById(id));
    }

    /**
     * 新增盘点任务
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增盘点任务", notes = "新增盘点任务不为空的字段")
    @PostMapping
    public R<Void> save(@RequestBody CheckTaskDTO data) {
        checkTaskService.save(data);
        return R.success();
    }

    /**
     * 修改盘点任务
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改盘点任务", notes = "修改盘点任务不为空的字段")
    @PutMapping
    public R<Void> update(@RequestBody CheckTaskDTO data) {
        checkTaskService.updateById(data);
        return R.success();
    }

    /**
     * 分配
     *
     * @param data 分配对象
     * @return 分配结果
     */
    @ApiOperation(value = "分配", notes = "分配")
    @PutMapping("distribute")
    public R<Void> distribute(@RequestBody CheckTaskDistributeDTO data) {
        CheckTask checkTask = new CheckTask();
        checkTask.setStatus(CheckTaskStatus.CHECK);
        checkTask.setId(data.getId());
        checkTask.setPersonName(data.getPersonName());
        checkTaskService.updateById(checkTask);
        return R.success();
    }

    /**
     * 盘点完成
     *
     * @param ids
     * @return 盘点完成结果
     */
    @ApiOperation(value = "盘点完成", notes = "盘点完成")
    @PutMapping("complete")
    public R<BatchVO> complete(@RequestBody List<Long> ids) {
        return R.success(checkTaskService.complete(ids));
    }

    /**
     * 生成复盘任务
     *
     * @param ids 生成集合
     * @return 生成 结果
     */
    @ApiOperation(value = "生成复盘任务", notes = "")
    @PostMapping("task")
    public R<BatchVO> checkTask(@RequestBody List<Long> ids) {
        return R.success(checkTaskService.task(ids));
    }

    /**
     * 生成损益单
     *
     * @param ids 生成集合
     * @return 生成 结果
     */
    @ApiOperation(value = "生成损益单", notes = "")
    @PostMapping("increaseDecrease")
    public R<BatchVO> increaseDecrease(@RequestBody List<Long> ids) {
        return R.success(checkTaskService.increaseDecrease(ids));
    }

    /**
     * 删除盘点任务
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除盘点任务", notes = "根据id物理删除盘点任务")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        checkTaskService.removeByIds(ids);
        return R.success();
    }

}
