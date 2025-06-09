package com.itheima.wms.controller.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.biz.ReceiptListBatchDTO;
import com.itheima.wms.model.dto.biz.ReceiptListDTO;
import com.itheima.wms.model.dto.biz.ReceiptListQueryDTO;
import com.itheima.wms.model.entity.biz.ReceiptList;
import com.itheima.wms.model.vo.biz.ReceiptListDetailVO;
import com.itheima.wms.model.vo.biz.ReceiptListSumVO;
import com.itheima.wms.service.biz.ReceiptListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 入库清单
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/receiptList")
@Api(value = "ReceiptList", tags = "入库清单")
public class ReceiptListController {

    @Autowired
    private ReceiptListService receiptListService;

    /**
     * 分页查询入库清单明细
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "分页查询入库清单明细", notes = "分页查询入库清单明细")
    @GetMapping("/pageDetail")
    public R<PageBean<ReceiptListDetailVO>> pageDetail(ReceiptListQueryDTO data) {
        log.info("分页查询入库清单明细:{}", data);
        PageBean<ReceiptListDetailVO> pageBean = receiptListService.pageDetail(data);
        return R.success(pageBean);
    }

    /**
     * 查询入库清单关联的商品id集合
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询入库清单关联的商品id集合", notes = "查询入库清单关联的商品id集合")
    @GetMapping("/goodsIds")
    public R<List<Long>> goodsIds(ReceiptListQueryDTO data) {
        log.info("查询入库清单关联的商品id集合:{}", data);
        List<Long> ids = receiptListService.goodsIds(data);
        return R.success(ids);
    }

    /**
     * 查询全部入库清单
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询全部入库清单", notes = "查询全部入库清单")
    @GetMapping("/list")
    public R<List<ReceiptList>> list(ReceiptListQueryDTO data) {
        log.info("查询全部入库清单:{}", data);
        List<ReceiptList> receiptListArray = receiptListService.list(data);
        return R.success(receiptListArray);
    }

    /**
     * 查询入库清单
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询入库清单", notes = "查询入库清单")
    @GetMapping("/{id}")
    public R<ReceiptList> get(@PathVariable Long id) {
        log.info("查询入库清单:{}", id);
        return R.success(receiptListService.getById(id));
    }

    /**
     * 查询合计信息
     *
     * @param masterId 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询合计信息", notes = "查询合计信息")
    @GetMapping("/sum/{masterId}")
    public R<ReceiptListSumVO> getReceiptListSumDetail(@PathVariable Long masterId) {
        log.info("查询合计信息:{}", masterId);
        return R.success(receiptListService.getReceiptListSumDetail(masterId));
    }

    /**
     * 新增入库清单
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增入库清单", notes = "新增入库清单")
    @PostMapping
    public R<Void> save(@RequestBody ReceiptListDTO data) {
        log.info("新增入库清单:{}", data);
        receiptListService.save(data);
        return R.success();
    }

    /**
     * 批量新增入库清单
     *
     * @param receiptListBatchDTO 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "批量新增入库清单", notes = "批量新增入库清单")
    @PostMapping("batch")
    public R<Void> saveBatch(@RequestBody ReceiptListBatchDTO receiptListBatchDTO) {
        log.info("批量新增入库清单:{}", receiptListBatchDTO);
        receiptListService.saveBatch(receiptListBatchDTO);
        return R.success();
    }

    /**
     * 修改入库清单
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改入库清单", notes = "修改入库清单")
    @PutMapping
    public R<Void> update(@RequestBody ReceiptListDTO data) {
        log.info("修改入库清单:{}", data);
        receiptListService.updateById(data);
        return R.success();
    }

    /**
     * 删除入库清单
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除入库清单", notes = "删除入库清单")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除入库清单:{}", ids);
        receiptListService.removeByIds(ids);
        return R.success();
    }

    /**
     * 根据入库单id删除清单
     *
     * @param masterId 入库单id
     * @return 删除结果
     */
    @ApiOperation(value = "根据入库单id删除清单", notes = "根据入库单id删除清单")
    @DeleteMapping("masterId")
    public R<Void> delete(@RequestParam(value = "masterId") Long masterId) {
        log.info("根据入库单id删除清单:{}", masterId);
        receiptListService.removeByMasterId(masterId);
        return R.success();
    }
}
