package com.itheima.wms.controller.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.biz.OutboundListBatchDTO;
import com.itheima.wms.model.dto.biz.OutboundListDTO;
import com.itheima.wms.model.dto.biz.OutboundListQueryDTO;
import com.itheima.wms.model.entity.biz.OutboundList;
import com.itheima.wms.model.vo.biz.OutboundListDetailVO;
import com.itheima.wms.model.vo.biz.OutboundListSumVO;
import com.itheima.wms.service.biz.OutboundListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 出库清单
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/outboundList")
@Api(value = "OutboundList", tags = "出库清单")
public class OutboundListController {

    @Autowired
    private OutboundListService outboundListService;

    @ApiOperation(value = "分页查询出库清单明细", notes = "分页查询出库清单明细")
    @GetMapping("/pageDetail")
    public R<PageBean<OutboundListDetailVO>> pageDetail(OutboundListQueryDTO data) {
        PageBean<OutboundListDetailVO> pageBean = outboundListService.pageDetail(data);
        return R.success(pageBean);
    }

    @ApiOperation(value = "查询出库清单关联的库存id集合", notes = "查询出库清单关联的库存id集合")
    @GetMapping("/stockIds")
    public R<List<Long>> stockIds(Long masterId) {
        List<Long> ids = outboundListService.stockIds(masterId);
        return R.success(ids);
    }

    /**
     * 查询出库清单
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询出库清单", notes = "查询出库清单")
    @GetMapping("/{id}")
    public R<OutboundList> get(@PathVariable Long id) {
        return R.success(outboundListService.getById(id));
    }

    /**
     * 查询合计信息
     *
     * @param masterId 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询合计信息", notes = "查询合计信息")
    @GetMapping("/sum/{masterId}")
    public R<OutboundListSumVO> getSumByMasterId(@PathVariable Long masterId) {
        return R.success(outboundListService.getSumByMasterId(masterId));
    }

    /**
     * 新增出库清单
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增出库清单", notes = "新增出库清单不为空的字段")
    @PostMapping
    public R<Void> save(@RequestBody OutboundListDTO data) {
        outboundListService.save(data);
        return R.success();
    }

    /**
     * 批量新增出库清单
     *
     * @param outboundListBatchDTO 批量新增模型
     * @return 新增结果
     */
    @ApiOperation(value = "批量新增出库清单", notes = "批量新增出库清单")
    @PostMapping("batch")
    public R<Void> saveBatch(@RequestBody OutboundListBatchDTO outboundListBatchDTO) {
        outboundListService.saveBatch(outboundListBatchDTO);
        return R.success();
    }

    /**
     * 修改出库清单
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改出库清单", notes = "修改出库清单不为空的字段")
    @PutMapping
    public R<Void> update(@RequestBody OutboundListDTO data) {
        outboundListService.updateById(data);
        return R.success();
    }

    /**
     * 删除出库清单
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除出库清单", notes = "根据id物理删除出库清单")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        outboundListService.removeByIds(ids);
        return R.success();
    }

    /**
     * 根据masterId删除清单
     *
     * @param masterId 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "根据masterId删除清单", notes = "根据masterId删除清单")
    @DeleteMapping("masterId")
    public R<Void> delete(@RequestParam(value = "masterId", required = true) Long masterId) {
        outboundListService.removeByMasterId(masterId);
        return R.success();
    }
}
