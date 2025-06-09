package com.itheima.wms.controller.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.biz.CheckListBatchDTO;
import com.itheima.wms.model.dto.biz.CheckListDTO;
import com.itheima.wms.model.entity.biz.CheckList;
import com.itheima.wms.model.vo.biz.CheckListDetailVO;
import com.itheima.wms.model.vo.biz.CheckListSumVO;
import com.itheima.wms.service.biz.CheckListService;
import com.itheima.wms.service.biz.StockService;
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
 * 盘点清单
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/checkList")
@Api(value = "CheckList", tags = "盘点清单")
public class CheckListController {

    @Autowired
    private CheckListService checkListService;
    @Autowired
    private StockService stockService;

    /**
     * 分页查询盘点清单
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询盘点清单", notes = "分页查询盘点清单")
    @GetMapping("/page")
    public R<PageBean<CheckList>> page(@RequestParam Map data) {
        PageBean<CheckList> pageBean = checkListService.page(data);
        return R.success(pageBean);
    }

    @ApiOperation(value = "分页查询盘点清单明细", notes = "分页查询盘点清单明细")
    @GetMapping("/pageDetail")
    public R<PageBean<CheckListDetailVO>> pageDetail(@RequestParam Map data) {
        PageBean<CheckListDetailVO> pageBean = checkListService.pageDetail(data);
        return R.success(pageBean);
    }

    @ApiOperation(value = "查询盘点清单关联的库位id集合", notes = "查询盘点清单关联的库位id集合")
    @GetMapping("/stockIds")
    public R<List<Long>> stockIds(@RequestParam Map data) {
        List<Long> ids = checkListService.stockIds(data);
        return R.success(ids);
    }

    @ApiOperation(value = "查询全部盘点清单", notes = "查询全部盘点清单")
    @GetMapping("/list")
    public R<List<CheckList>> list(@RequestParam Map data) {
        List<CheckList> checkListArray = checkListService.list(data);
        return R.success(checkListArray);
    }

    /**
     * 查询盘点清单
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询盘点清单", notes = "查询盘点清单")
    @GetMapping("/{id}")
    public R<CheckList> get(@PathVariable Long id) {
        return R.success(checkListService.getById(id));
    }

    /**
     * 查询合计信息
     *
     * @param masterId 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询合计信息", notes = "查询合计信息")
    @GetMapping("/sum/{masterId}")
    public R<CheckListSumVO> getSumByMasterId(@PathVariable Long masterId) {
        return R.success(checkListService.getSumByMasterId(masterId));
    }

    /**
     * 批量新增盘点清单
     *
     * @param checkListBatchDTO 批量新增模型
     * @return 新增结果
     */
    @ApiOperation(value = "批量新增盘点清单", notes = "批量新增盘点清单不为空的字段")
    @PostMapping("batch")
    public R<Void> saveBatch(@RequestBody @Validated CheckListBatchDTO checkListBatchDTO) {
        checkListService.saveBatchByStockIds(checkListBatchDTO);
        return R.success();
    }

    /**
     * 新增盘点清单
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增盘点清单", notes = "新增盘点清单不为空的字段")
    @PostMapping
    public R<Void> save(@RequestBody CheckListDTO data) {
        checkListService.save(data);
        return R.success();
    }


    /**
     * 修改盘点清单
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改盘点清单", notes = "修改盘点清单不为空的字段")
    @PutMapping
    public R<Void> update(@RequestBody CheckListDTO data) {
        checkListService.updateById(data);
        return R.success();
    }

    /**
     * 删除盘点清单
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除盘点清单", notes = "根据id物理删除盘点清单")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        checkListService.removeByIds(ids);
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
        checkListService.removeByMasterId(masterId);
        return R.success();
    }
}
