/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.controller.biz;

import com.itheima.wms.common.PageBean;
import com.itheima.wms.common.R;
import com.itheima.wms.model.dto.biz.IncreaseDecreaseDTO;
import com.itheima.wms.model.dto.biz.IncreaseDecreaseQueryDTO;
import com.itheima.wms.model.entity.biz.IncreaseDecrease;
import com.itheima.wms.model.vo.biz.IncreaseDecreaseDetailVO;
import com.itheima.wms.model.vo.biz.IncreaseDecreaseSumVO;
import com.itheima.wms.service.biz.IncreaseDecreaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 损益单
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/increaseDecrease")
@Api(value = "IncreaseDecrease", tags = "损益单")
public class IncreaseDecreaseController {

    @Autowired
    private IncreaseDecreaseService increaseDecreaseService;

    /**
     * 分页查询损益单明细
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "分页查询损益单明细", notes = "分页查询损益单明细")
    @GetMapping("/pageDetail")
    public R<PageBean<IncreaseDecreaseDetailVO>> pageDetail(IncreaseDecreaseQueryDTO data) {
        log.info("分页查询损益单明细:{}", data);
        PageBean<IncreaseDecreaseDetailVO> pageBean = increaseDecreaseService.pageDetail(data);
        return R.success(pageBean);
    }

    /**
     * 查询全部损益单
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询全部损益单", notes = "查询全部损益单")
    @GetMapping("/list")
    public R<List<IncreaseDecrease>> list(IncreaseDecreaseQueryDTO data) {
        log.info("查询全部损益单:{}", data);
        List<IncreaseDecrease> increaseDecreaseArray = increaseDecreaseService.list(data);
        return R.success(increaseDecreaseArray);
    }

    /**
     * 查询损益单明细
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "查询损益单明细", notes = "查询损益单明细")
    @GetMapping("/listDetail")
    public R<List<IncreaseDecreaseDetailVO>> listDetail(IncreaseDecreaseQueryDTO data) {
        log.info("查询损益单明细:{}", data);
        List<IncreaseDecreaseDetailVO> list = increaseDecreaseService.listDetail(data);
        return R.success(list);
    }

    /**
     * 汇总总数与总金额
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "汇总总数与总金额", notes = "汇总总数与总金额")
    @GetMapping("/sum")
    public R<IncreaseDecreaseSumVO> sum(IncreaseDecreaseQueryDTO data) {
        log.info("汇总总数与总金额:{}", data);
        IncreaseDecreaseSumVO increaseDecreaseSumVO = increaseDecreaseService.sum(data);
        return R.success(increaseDecreaseSumVO);
    }

    /**
     * 查询损益单
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询损益单", notes = "查询损益单")
    @GetMapping("/{id}")
    public R<IncreaseDecrease> get(@PathVariable Long id) {
        log.info("查询损益单:{}", id);
        return R.success(increaseDecreaseService.getById(id));
    }

    /**
     * 新增损益单
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增损益单", notes = "新增损益单")
    @PostMapping
    public R<Void> save(@RequestBody IncreaseDecreaseDTO data) {
        log.info("新增损益单:{}", data);
        increaseDecreaseService.save(data);
        return R.success();
    }

    /**
     * 修改损益单
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改损益单", notes = "修改损益单")
    @PutMapping
    public R<Void> update(@RequestBody IncreaseDecreaseDTO data) {
        log.info("修改损益单:{}", data);
        increaseDecreaseService.updateById(data);
        return R.success();
    }

    /**
     * 调整库存
     *
     * @param data 调整库存
     * @return 修改结果
     */
    @ApiOperation(value = "调整库存", notes = "调整库存")
    @PutMapping("change")
    public R<Void> change(@RequestBody IncreaseDecreaseDTO data) {
        log.info("调整库存:{}", data);
        increaseDecreaseService.change(data);
        return R.success();
    }

    /**
     * 取消调整
     *
     * @param data 取消调整
     * @return 修改结果
     */
    @ApiOperation(value = "取消调整", notes = "取消调整")
    @PutMapping("cancel")
    public R<Void> cancel(@RequestBody IncreaseDecreaseDTO data) {
        log.info("取消调整:{}", data);
        increaseDecreaseService.cancel(data);
        return R.success();
    }

    /**
     * 删除损益单
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除损益单", notes = "根据id物理删除损益单")
    @DeleteMapping
    public R<Void> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("删除损益单:{}", ids);
        increaseDecreaseService.removeByIds(ids);
        return R.success();
    }

}
