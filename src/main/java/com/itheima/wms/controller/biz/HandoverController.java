/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.controller.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.common.constants.HandoverStatus;
import com.itheima.wms.model.dto.biz.HandoverDTO;
import com.itheima.wms.model.dto.biz.HandoverDistributeDTO;
import com.itheima.wms.model.entity.biz.Handover;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.HandoverDetailVO;
import com.itheima.wms.service.biz.HandoverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * 交接任务
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/handover")
@Api(value = "Handover", tags = "交接任务")
public class HandoverController {

    @Autowired
    private HandoverService handoverService;

    /**
     * 分页查询交接任务明细
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询交接任务明细", notes = "分页查询交接任务明细")
    @GetMapping("/pageDetail")
    public R<PageBean<HandoverDetailVO>> pageDetail(@RequestParam Map data) {
        PageBean<HandoverDetailVO> pageBean = handoverService.pageDetail(data);
        return R.success(pageBean);
    }

    /**
     * 查询交接任务
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询交接任务", notes = "查询交接任务")
    @GetMapping("/{id}")
    public R<Handover> get(@PathVariable Long id) {
        return R.success(handoverService.getById(id));
    }

    /**
     * 新增交接任务
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增交接任务", notes = "新增交接任务")
    @PostMapping
    public R<Void> save(@RequestBody HandoverDTO data) {
        handoverService.save(data);
        return R.success();
    }

    /**
     * 修改交接任务
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改交接任务", notes = "修改交接任务")
    @PutMapping
    public R<Void> update(@RequestBody HandoverDTO data) {
        handoverService.updateById(data);
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
    public R<Void> distribute(@RequestBody @Validated HandoverDistributeDTO data) {
        Handover handover = new Handover();
        handover.setStatus(HandoverStatus.HANDOVER);
        handover.setId(data.getId());
        handover.setHandoverName(data.getHandoverName());
        handoverService.updateById(handover);
        return R.success();
    }

    /**
     * 交接完成
     *
     * @param ids
     * @return 交接完成结果
     */
    @ApiOperation(value = "交接完成", notes = "交接完成")
    @PutMapping("complete")
    public R<BatchVO> complete(@RequestBody List<Long> ids) {
        return R.success(handoverService.complete(ids));
    }

    /**
     * 删除交接任务
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除交接任务", notes = "删除交接任务")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        handoverService.removeByIds(ids);
        return R.success();
    }

}
