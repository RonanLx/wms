/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.controller.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.biz.OutboundDTO;
import com.itheima.wms.model.dto.biz.OutboundQueryDTO;
import com.itheima.wms.model.entity.biz.Outbound;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.OutboundDetailVO;
import com.itheima.wms.service.biz.OutboundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 出库单
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/outbound")
@Api(value = "Outbound", tags = "出库单")
public class OutboundController {

    @Autowired
    private OutboundService outboundService;

    /**
     * 分页查询出库单明细
     *
     * @param data 分页查询对象
     * @return 查询结果
     */

    @ApiOperation(value = "分页查询出库单明细", notes = "分页查询出库单明细")
    @GetMapping("/pageDetail")
    public R<PageBean<OutboundDetailVO>> pageDetail(OutboundQueryDTO data) {
        PageBean<OutboundDetailVO> pageBean = outboundService.pageDetail(data);
        return R.success(pageBean);
    }


    /**
     * 查询出库单
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询出库单", notes = "查询出库单")
    @GetMapping("/{id}")
    public R<Outbound> get(@PathVariable Long id) {
        return R.success(outboundService.getById(id));
    }

    /**
     * 查询详情
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询详情", notes = "查询详情")
    @GetMapping("detail/{id}")
    public R<OutboundDetailVO> getDetail(@PathVariable Long id) {
        return R.success(outboundService.getDetail(id));
    }

    /**
     * 新增出库单
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增出库单", notes = "新增出库单")
    @PostMapping
    public R<Outbound> save(@RequestBody OutboundDTO data) {
        outboundService.save(data);
        return R.success(data);
    }

    /**
     * 生成拣货任务
     *
     * @param ids
     * @return 生成 结果
     */
    @ApiOperation(value = "生成拣货任务", notes = "生成拣货任务")
    @PostMapping("picking")
    public R<BatchVO> picking(@RequestBody List<Long> ids) {
        return R.success(outboundService.picking(ids));
    }


    /**
     * 修改出库单
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改出库单", notes = "修改出库单")
    @PutMapping
    public R<Outbound> update(@RequestBody OutboundDTO data) {
        outboundService.updateById(data);
        return R.success(data);
    }

    /**
     * 取消出库单
     *
     * @return 取消结果
     */
    @ApiOperation(value = "取消出库单", notes = "取消出库单")
    @PutMapping("cancel")
    public R<Void> cancel(@RequestBody OutboundDTO data) {
        outboundService.cancel(data);
        return R.success();
    }

    /**
     * 删除出库单
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除出库单", notes = "删除出库单")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        outboundService.removeByIds(ids);
        return R.success();
    }

}
