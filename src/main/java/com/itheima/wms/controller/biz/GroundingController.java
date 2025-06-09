package com.itheima.wms.controller.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.biz.GroundingDTO;
import com.itheima.wms.model.dto.biz.GroundingQueryDTO;
import com.itheima.wms.model.entity.biz.Grounding;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.GroundingDetailVO;
import com.itheima.wms.service.biz.GroundingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 上架任务
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/grounding")
@Api(value = "Grounding", tags = "上架任务")
public class GroundingController {

    @Autowired
    private GroundingService groundingService;


    /**
     * 分页查询上架任务明细
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "分页查询上架任务明细", notes = "分页查询上架任务明细")
    @GetMapping("/pageDetail")
    public R<PageBean<GroundingDetailVO>> pageDetail(GroundingQueryDTO data) {
        log.info("分页查询上架任务明细:{}", data);
        PageBean<GroundingDetailVO> pageBean = groundingService.pageDetail(data);
        return R.success(pageBean);
    }

    /**
     * 查询全部上架任务
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询全部上架任务", notes = "查询全部上架任务")
    @GetMapping("/list")
    public R<List<Grounding>> list(GroundingQueryDTO data) {
        log.info("查询全部上架任务:{}", data);
        List<Grounding> groundingArray = groundingService.list(data);
        return R.success(groundingArray);
    }

    /**
     * 查询上架任务
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询上架任务", notes = "查询上架任务")
    @GetMapping("/{id}")
    public R<Grounding> get(@PathVariable Long id) {
        log.info("查询上架任务:{}", id);
        return R.success(groundingService.getById(id));
    }

    /**
     * 新增上架任务
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增上架任务", notes = "新增上架任务")
    @PostMapping
    public R<Void> save(@RequestBody GroundingDTO data) {
        log.info("新增上架任务:{}", data);
        groundingService.save(data);
        return R.success();
    }

    /**
     * 修改上架任务
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改上架任务", notes = "修改上架任务")
    @PutMapping
    public R<Void> update(@RequestBody GroundingDTO data) {
        log.info("修改上架任务:{}", data);
        groundingService.updateById(data);
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
    public R<Void> distribute(@RequestBody GroundingDTO data) {
        log.info("分配:{}", data);
        groundingService.distribute(data);
        return R.success();
    }

    /**
     * 上架完成
     *
     * @param ids
     * @return 上架完成结果
     */
    @ApiOperation(value = "上架完成", notes = "上架完成")
    @PutMapping("complete")
    public R<BatchVO> complete(@RequestBody List<Long> ids) {
        log.info("上架完成:{}", ids);
        return R.success(groundingService.complete(ids));
    }


    /**
     * 删除上架任务
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除上架任务", notes = "删除上架任务")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除上架任务:{}", ids);
        groundingService.removeByIds(ids);
        return R.success();
    }

}
