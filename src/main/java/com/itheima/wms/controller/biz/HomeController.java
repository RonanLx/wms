/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.controller.biz;

import com.google.common.collect.ImmutableMap;
import com.itheima.wms.common.R;
import com.itheima.wms.mapper.base.AreaMapper;
import com.itheima.wms.mapper.biz.*;
import com.itheima.wms.model.vo.biz.HomeToDoVO;
import com.itheima.wms.service.biz.StockService;
import com.itheima.wms.utils.DateBuilder;
import com.itheima.wms.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * 首页
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/home")
@Api(value = "Home", tags = "首页")
public class HomeController {

    @Autowired
    private ReceiptMapper receiptMapper;
    @Autowired
    private OutboundMapper outboundMapper;
    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    private StockService stockService;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private GroundingMapper groundingMapper;
    @Autowired
    private PickingMapper pickingMapper;


    @ApiOperation(value = "待办信息", notes = "待办信息")
    @GetMapping("/todo")
    public R<List<Map>> todo(@RequestParam Map data) {
        List<Map> result = new ArrayList<>();
        HomeToDoVO homeToDoVO = new HomeToDoVO();
        Map<String, Object> map = Map.of("status", 1, "warehouse_id", data.get("warehouseId"));
        //homeToDoVO.setReceiptNew(receiptMapper.selectCountByMap(map));
        //homeToDoVO.setOutboundNew(outboundMapper.selectCountByMap(map));
        //homeToDoVO.setCheckNew(checkMapper.selectCountByMap(map));
        result.add(ImmutableMap.of("name", "入库单", "value1", homeToDoVO.getReceiptNew(), "value2", homeToDoVO.getReceiptNew()));
        result.add(ImmutableMap.of("name", "出库单", "value1", homeToDoVO.getOutboundNew(), "value2", homeToDoVO.getOutboundNew()));
        result.add(ImmutableMap.of("name", "盘点单", "value1", homeToDoVO.getCheckNew(), "value2", homeToDoVO.getCheckNew()));
        return R.success(result);
    }

    @ApiOperation(value = "库存使用情况", notes = "库存使用情况")
    @GetMapping("/stockUseStatus")
    public R<Object> stockUseStatus(@RequestParam Map data) {
        /*queryWrapper.select("IFNULL(sum(frozen),0) as frozenNum,IFNULL(sum(free),0) as freeNum");*/
        Map<String, Object> map = Map.of("warehouse_id", data.get("warehouseId"));
        Map<String, Object> res = Map.of();//stockService.sumFrozenByMap(map);

        Integer frozenNum = ((BigDecimal) res.get("frozenNum")).intValue();
        Integer freeNum = ((BigDecimal) res.get("freeNum")).intValue();
        List<Map> list = new ArrayList<>();
        list.add(ImmutableMap.of("value", frozenNum, "name", "冻结库存"));
        list.add(ImmutableMap.of("value", freeNum, "name", "可用库存"));
        return R.success(list);
    }


    @ApiOperation(value = "库区使用情况", notes = "库区使用情况")
    @GetMapping("/areaUseStatus")
    public R<Object> areaUseStatus(@RequestParam Map data) {
        List<Map> result = areaMapper.areaUseStatus(data);
        return R.success(result);
    }

    @ApiOperation(value = "出入库信息", notes = "出入库信息")
    @GetMapping("/sumList")
    public R<Object> sumList(@RequestParam Map data) {
        List<String> x;
        List<Integer> xk;
        if (!data.containsKey("dimension")) {
            data.put("dimension", "e");
        }
        Date startTime = null;
        Date endTime = null;
        if (null == data.get("startCreateTime")) {
            switch (data.get("dimension").toString()) {
                case "w":
                    startTime = DateBuilder.getTimesWeekmorning();
                    endTime = DateBuilder.getTimesWeeknight();
                    break;
                case "e":
                    startTime = DateBuilder.getTimesMonthmorning();
                    endTime = DateBuilder.getTimesMonthnight();
                    break;
                case "c":
                    startTime = DateBuilder.getCurrentYearStartTime();
                    endTime = DateBuilder.getCurrentYearEndTime();
                default:
                    break;
            }
            data.put("startCreateTime", DateUtils.formatAsDateTime(startTime));
            data.put("endCreateTime", DateUtils.formatAsDateTime(endTime));
        }

        if (data.get("dimension").toString().equals("c")) {
            x = Arrays.asList("1月", "2月", "3月", "4月", "5月", "7月", "8月", "9月", "10月", "11月", "12月");
            xk = Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12);
        } else {
            List<String> day = DateUtils.getBetweenDay(startTime, endTime);
            day.remove(day.size() - 1);
            if (day.size() == 7) {
                x = Arrays.asList("周一", "周二", "周三", "周四", "周五", "周六", "周日");
            } else {
                x = new ArrayList<>();
                for (int i = 0; i < day.size(); i++) {
                    x.add((i + 1) + "日");
                }
            }
            xk = day.stream().map(item -> Integer.parseInt(item.split("-")[2])).collect(Collectors.toList());
            if (data.get("dimension").toString().equals("w")) {
                data.put("dimension", "e");
            }
        }


        List<Map> groundings = groundingMapper.sumGroupByDate(data);
        List<Map> pickings = pickingMapper.sumGroupByDate(data);

        Map<Integer, String> groundingsMap = groundings.stream().collect(Collectors.toMap(item -> Integer.parseInt(item.get("date").toString()), item -> item.get("num").toString()));
        Map<Integer, String> pickingsMap = pickings.stream().collect(Collectors.toMap(item -> Integer.parseInt(item.get("date").toString()), item -> item.get("num").toString()));

        List<String> groundingArray = new ArrayList<>();
        List<String> pickingArray = new ArrayList<>();
        xk.forEach(item -> {
            if (groundingsMap.containsKey(item)) {
                groundingArray.add(groundingsMap.get(item));
            } else {
                groundingArray.add("0");
            }

            if (pickingsMap.containsKey(item)) {
                pickingArray.add(pickingsMap.get(item));
            } else {
                pickingArray.add("0");
            }
        });

        HashMap[] hashMap = new HashMap[]{new HashMap() {{
            put("name", "入库");
            put("data", groundingArray);
        }}, new HashMap() {{
            put("name", "出库");
            put("data", pickingArray);
        }}};
        Map result = new HashMap();
        result.put("data", hashMap);
        result.put("x", x);
        return R.success(result);
    }

    //代办信息：按照入库单，出库单，盘点单 这个顺序放入数组中
    //[
    //	{
    //		name:'入库单',
    //		value1:5,
    //		value2:3
    //	},
    //	{
    //		name:'出库单',
    //		value1:5,
    //		value2:3
    //	},
    //	{
    //		name:'盘点单',
    //		value1:5,
    //		value2:3
    //	}
    //]
    //
    //入库出库信息：
    //	查询条件都可以
    //	返回数据的话:一个对象返回
    //	横坐标数据：data: ['入库', '出库'],
    //	纵坐标数据：data:[{name:'入库'， data: [320, 332, 301, 334, 390, 200, 300]},
    //					 {name:'出库'， data: [220, 182, 191, 234, 290, 120, 340]}
    //					]
    //库存使用		data: [
    //              { value: 300, name: '已用' },
    //              { value: 3900, name: '剩余' }
    //            ],
    //库区使用情况：
    //	扇形分类：data: ['拣货区', '出库区', '暂存库区', '进货暂存区', '出货暂存区']
    //	详细数据格式： data: [
    //              { value: 348, name: '拣货区' },
    //              { value: 487, name: '出库区' },
    //              { value: 677, name: '暂存库区' },
    //              { value: 126, name: '进货暂存区' },
    //              { value: 298, name: '出货暂存区' }
    //            ],
}
