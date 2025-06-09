/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.controller.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.biz.ReceivingDTO;
import com.itheima.wms.model.dto.biz.ReceivingQueryDTO;
import com.itheima.wms.model.entity.biz.Receiving;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.ReceivingDetailVO;
import com.itheima.wms.service.biz.ReceivingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 收货任务
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/receiving")
@Api(value = "Receiving", tags = "收货任务")
public class ReceivingController {

    @Autowired
    private ReceivingService receivingService;

    /**
     * 分页查询收货任务明细
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "分页查询收货任务明细", notes = "分页查询收货任务明细")
    @GetMapping("/pageDetail")
    public R<PageBean<ReceivingDetailVO>> pageDetail(ReceivingQueryDTO data) {
        log.info("分页查询收货任务明细:{}", data);
        PageBean<ReceivingDetailVO> pageBean = receivingService.pageDetail(data);
        return R.success(pageBean);
    }

    /**
     * 查询全部收货任务
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询全部收货任务", notes = "查询全部收货任务")
    @GetMapping("/list")
    public R<List<Receiving>> list(ReceivingQueryDTO data) {
        log.info("查询全部收货任务:{}", data);
        List<Receiving> receivingArray = receivingService.list(data);
        return R.success(receivingArray);
    }

    /**
     * 查询收货任务
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询收货任务", notes = "查询收货任务")
    @GetMapping("/{id}")
    public R<Receiving> get(@PathVariable Long id) {
        log.info("查询收货任务:{}", id);
        return R.success(receivingService.getById(id));
    }

    /**
     * 新增收货任务
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增收货任务", notes = "新增收货任务")
    @PostMapping
    public R<Void> save(@RequestBody ReceivingDTO data) {
        log.info("新增收货任务:{}", data);
        receivingService.save(data);
        return R.success();
    }

    /**
     * 修改收货任务
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改收货任务", notes = "修改收货任务")
    @PutMapping
    public R<Void> update(@RequestBody ReceivingDTO data) {
        log.info("修改收货任务:{}", data);
        receivingService.updateById(data);
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
    public R<Void> distribute(@RequestBody ReceivingDTO data) {
        log.info("分配:{}", data);
        receivingService.distribute(data);
        return R.success();
    }

    /**
     * 收货完成
     *
     * @param ids
     * @return 收货完成结果
     */
    @ApiOperation(value = "收货完成", notes = "收货完成")
    @PutMapping("complete")
    public R<BatchVO> complete(@RequestBody List<Long> ids) {
        log.info("收货完成:{}", ids);
        BatchVO batchVO = receivingService.complete(ids);
        return R.success(batchVO);
    }

    /**
     * 删除收货任务
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除收货任务", notes = "删除收货任务")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除收货任务:{}", ids);
        receivingService.removeByIds(ids);
        return R.success();
    }

}
