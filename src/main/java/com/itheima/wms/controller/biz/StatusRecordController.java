/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.controller.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.biz.StatusRecordDTO;
import com.itheima.wms.model.entity.biz.StatusRecord;
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
 * 状态记录
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/statusRecord")
@Api(value = "StatusRecord", tags = "状态记录")
public class StatusRecordController {

    @Autowired
    private StatusRecordService statusRecordService;

    /**
     * 分页查询状态记录
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询状态记录", notes = "分页查询状态记录")
    @GetMapping("/page")
    public R<PageBean<StatusRecord>> page(@RequestParam Map data) {
        PageBean<StatusRecord> pageBean = statusRecordService.page(data);
        return R.success(pageBean);
    }

    @ApiOperation(value = "查询全部状态记录", notes = "查询全部状态记录")
    @GetMapping("/list")
    public R<List<StatusRecord>> list(@RequestParam Map data) {
        List<StatusRecord> statusRecordArray = statusRecordService.list(data);
        return R.success(statusRecordArray);
    }

    /**
     * 查询状态记录
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询状态记录", notes = "查询状态记录")
    @GetMapping("/{id}")
    public R<StatusRecord> get(@PathVariable Long id) {
        return R.success(statusRecordService.getById(id));
    }

    /**
     * 新增状态记录
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增状态记录", notes = "新增状态记录不为空的字段")
    @PostMapping
    public R<Void> save(@RequestBody StatusRecordDTO data) {
        statusRecordService.save(data);
        return R.success();
    }

    /**
     * 修改状态记录
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改状态记录", notes = "修改状态记录不为空的字段")
    @PutMapping
    public R<Void> update(@RequestBody StatusRecordDTO data) {
        statusRecordService.updateById(data);
        return R.success();
    }

    /**
     * 删除状态记录
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除状态记录", notes = "根据id物理删除状态记录")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        statusRecordService.removeByIds(ids);
        return R.success();
    }

}
