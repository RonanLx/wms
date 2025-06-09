/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.controller.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.common.constants.PickingStatus;
import com.itheima.wms.model.dto.biz.PickingDTO;
import com.itheima.wms.model.dto.biz.PickingDistributeDTO;
import com.itheima.wms.model.entity.biz.Picking;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.PickingDetailVO;
import com.itheima.wms.service.biz.PickingService;
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
 * 拣货任务
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/picking")
@Api(value = "Picking", tags = "拣货任务")
public class PickingController {

    @Autowired
    private PickingService pickingService;

    /**
     * 分页查询拣货任务明细
     *
     * @param data 分页查询对象
     * @return 查询结果
     */

    @ApiOperation(value = "分页查询拣货任务明细", notes = "分页查询拣货任务明细")
    @GetMapping("/pageDetail")
    public R<PageBean<PickingDetailVO>> pageDetail(@RequestParam Map data) {
        PageBean<PickingDetailVO> pageBean = pickingService.pageDetail(data);
        return R.success(pageBean);
    }


    /**
     * 查询拣货任务
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询拣货任务", notes = "查询拣货任务")
    @GetMapping("/{id}")
    public R<Picking> get(@PathVariable Long id) {
        return R.success(pickingService.getById(id));
    }

    /**
     * 新增拣货任务
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增拣货任务", notes = "新增拣货任务")
    @PostMapping
    public R<Void> save(@RequestBody PickingDTO data) {
        pickingService.save(data);
        return R.success();
    }

    /**
     * 修改拣货任务
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改拣货任务", notes = "修改拣货任务")
    @PutMapping
    public R<Void> update(@RequestBody PickingDTO data) {
        pickingService.updateById(data);
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
    public R<Void> distribute(@RequestBody @Validated PickingDistributeDTO data) {
        Picking picking = new Picking();
        picking.setStatus(PickingStatus.PICKING);
        picking.setId(data.getId());
        picking.setPersonName(data.getPersonName());
        pickingService.updateById(picking);
        return R.success();
    }

    /**
     * 拣选完成
     *
     * @param ids
     * @return 拣选完成结果
     */
    @ApiOperation(value = "拣选完成", notes = "拣选完成")
    @PutMapping("complete")
    public R<BatchVO> complete(@RequestBody List<Long> ids) {
        return R.success(pickingService.complete(ids));
    }

    /**
     * 删除拣货任务
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除拣货任务", notes = "删除拣货任务")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        pickingService.removeByIds(ids);
        return R.success();
    }

}
