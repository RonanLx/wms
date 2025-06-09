package com.itheima.wms.controller.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.biz.ReceiptDTO;
import com.itheima.wms.model.dto.biz.ReceiptQueryDTO;
import com.itheima.wms.model.entity.biz.Receipt;
import com.itheima.wms.model.vo.biz.BatchVO;
import com.itheima.wms.model.vo.biz.ReceiptDetailVO;
import com.itheima.wms.service.biz.ReceiptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 入库单
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/receipt")
@Api(value = "Receipt", tags = "入库单")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    /**
     * 分页查询入库单明细
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "分页查询入库单明细", notes = "分页查询入库单明细")
    @GetMapping("/pageDetail")
    public R<PageBean<ReceiptDetailVO>> pageDetail(ReceiptQueryDTO data) {
        log.info("分页查询入库单明细:{}", data);
        PageBean<ReceiptDetailVO> pageBean = receiptService.pageDetail(data);
        return R.success(pageBean);
    }

    /**
     * 查询入库单
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询入库单", notes = "查询入库单")
    @GetMapping("/{id}")
    public R<Receipt> get(@PathVariable Long id) {
        log.info("查询入库单:{}", id);
        return R.success(receiptService.getById(id));
    }

    /**
     * 查询详情
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询详情", notes = "查询详情")
    @GetMapping("detail/{id}")
    public R<ReceiptDetailVO> getDetailById(@PathVariable Long id) {
        log.info("查询详情:{}", id);
        ReceiptDetailVO receiptDetailVO = receiptService.getDetailById(id);
        return R.success(receiptDetailVO);
    }

    /**
     * 新增入库单
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增入库单", notes = "新增入库单")
    @PostMapping
    public R<Receipt> save(@RequestBody ReceiptDTO data) {
        log.info("新增入库单:{}", data);
        Receipt receipt = receiptService.save(data);
        return R.success(receipt);
    }

    /**
     * 生成收货任务
     *
     * @param ids
     * @return 生成 结果
     */
    @ApiOperation(value = "生成收货任务", notes = "生成收货任务")
    @PostMapping("receiving")
    public R<BatchVO> receiving(@RequestBody List<Long> ids) {
        log.info("生成收货任务:{}", ids);
        BatchVO batchVO = receiptService.receiving(ids);
        return R.success(batchVO);
    }


    /**
     * 修改入库单
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改入库单", notes = "修改入库单")
    @PutMapping
    public R<Receipt> update(@RequestBody ReceiptDTO data) {
        log.info("修改入库单:{}", data);
        receiptService.updateById(data);
        return R.success(data);
    }

    /**
     * 取消入库单
     *
     * @return 取消结果
     */
    @ApiOperation(value = "取消入库单", notes = "取消入库单")
    @PutMapping("cancel")
    public R<Void> cancel(@RequestBody ReceiptDTO data) {
        log.info("取消入库单:{}", data);
        receiptService.updateById(data);
        return R.success();
    }

    /**
     * 删除入库单
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除入库单", notes = "删除入库单")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除入库单:{}", ids);
        receiptService.removeByIds(ids);
        return R.success();
    }

}
