/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.controller.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.biz.StockDTO;
import com.itheima.wms.model.dto.biz.StockQueryDTO;
import com.itheima.wms.model.entity.biz.Stock;
import com.itheima.wms.model.vo.biz.StockDetailVO;
import com.itheima.wms.service.biz.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 实时库存
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/stock")
@Api(value = "Stock", tags = "实时库存")
public class StockController {

    @Autowired
    private StockService stockService;


    /**
     * 分页查询明细库存
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "分页查询明细库存", notes = "分页查询明细库存")
    @GetMapping("/pageDetail")
    public R<PageBean<StockDetailVO>> pageDetail(StockQueryDTO data) {
        log.info("分页查询明细库存:{}", data);
        PageBean<StockDetailVO> pageBean = stockService.pageDetail(data);
        return R.success(pageBean);
    }

    /**
     * 查询全部库存
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询全部库存", notes = "查询全部库存")
    @GetMapping("/list")
    public R<List<Stock>> list(StockQueryDTO data) {
        log.info("查询全部库存:{}", data);
        List<Stock> stockArray = stockService.list(data);
        return R.success(stockArray);
    }

    /**
     * 查询库存
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询库存", notes = "查询库存")
    @GetMapping("/{id}")
    public R<Stock> get(@PathVariable Long id) {
        log.info("查询库存:{}", id);
        return R.success(stockService.getById(id));
    }

    /**
     * 新增库存
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增库存", notes = "新增库存")
    @PostMapping
    public R<Void> save(@RequestBody StockDTO data) {
        log.info("新增库存:{}", data);
        stockService.save(data);
        return R.success();
    }

    /**
     * 修改库存
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改库存", notes = "修改库存")
    @PutMapping
    public R<Void> update(@RequestBody StockDTO data) {
        log.info("修改库存:{}", data);
        stockService.updateById(data);
        return R.success();
    }

    /**
     * 删除库存
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除库存", notes = "删除库存")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除库存:{}", ids);
        stockService.removeByIds(ids);
        return R.success();
    }

}
