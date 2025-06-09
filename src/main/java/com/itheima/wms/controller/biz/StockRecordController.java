package com.itheima.wms.controller.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.biz.StockRecordDTO;
import com.itheima.wms.model.entity.biz.StockRecord;
import com.itheima.wms.service.biz.StockRecordService;
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
 * 库存记录
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/stockRecord")
@Api(value = "StockRecord", tags = "库存记录")
public class StockRecordController {

    @Autowired
    private StockRecordService stockRecordService;

    /**
     * 分页查询库存记录
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询库存记录", notes = "分页查询库存记录")
    @GetMapping("/page")
    public R<PageBean<StockRecord>> page(@RequestParam Map data) {
        log.info("分页查询库存记录:{}", data);
        PageBean<StockRecord> pageBean = stockRecordService.page(data);
        return R.success(pageBean);
    }

    /**
     * 查询全部库存记录
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询全部库存记录", notes = "查询全部库存记录")
    @GetMapping("/list")
    public R<List<StockRecord>> list(@RequestParam Map data) {
        log.info("查询全部库存记录:{}", data);
        List<StockRecord> stockRecordArray = stockRecordService.list(data);
        return R.success(stockRecordArray);
    }

    /**
     * 查询库存记录
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询库存记录", notes = "查询库存记录")
    @GetMapping("/{id}")
    public R<StockRecord> get(@PathVariable Long id) {
        log.info("查询库存记录:{}", id);
        return R.success(stockRecordService.getById(id));
    }

    /**
     * 新增库存记录
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增库存记录", notes = "新增库存记录")
    @PostMapping
    public R<Void> save(@RequestBody StockRecordDTO data) {
        log.info("新增库存记录:{}", data);
        stockRecordService.save(data);
        return R.success();
    }

    /**
     * 修改库存记录
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改库存记录", notes = "修改库存记录")
    @PutMapping
    public R<Void> update(@RequestBody StockRecordDTO data) {
        log.info("修改库存记录:{}", data);
        stockRecordService.updateById(data);
        return R.success();
    }

    /**
     * 删除库存记录
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除库存记录", notes = "删除库存记录")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除库存记录:{}", ids);
        stockRecordService.removeByIds(ids);
        return R.success();
    }

}
