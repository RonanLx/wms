/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.excel.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.handler.inter.IReadHandler;
import cn.hutool.core.lang.UUID;
import com.itheima.wms.common.constants.ConstantTable;
import com.itheima.wms.excel.model.AreaExcel;
import com.itheima.wms.excel.service.AreaExcelService;
import com.itheima.wms.mapper.base.AreaMapper;
import com.itheima.wms.model.entity.base.Area;
import com.itheima.wms.model.entity.base.Warehouse;
import com.itheima.wms.model.vo.biz.ImportResultVO;
import com.itheima.wms.service.base.WarehouseService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.TreeMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Scope("prototype")// 多例模式
@Slf4j
public class AreaExcelServiceImpl implements AreaExcelService {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private AreaMapper areaMapper;

    private ImportParams importParams = new ImportParams();
    // 错误信息
    private ConcurrentHashMap<Integer, String> resultMap = new ConcurrentHashMap<>();

    //CPU 密集型：线程数量=cpu核心数量
    //IO 密集型：线程数量=cpu核心数量*2
    private Integer threadCount = Runtime.getRuntime().availableProcessors() * 2;

    // 队列最大条数 超过10W则出现阻塞生产者情况
    private int capacity = 100000;

    private ExecutorService executorService = new ThreadPoolExecutor(threadCount, threadCount, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(capacity));

    // 总量计数
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    // 成功数量 计数
    private AtomicInteger atomicSuccess = new AtomicInteger(0);

    /**
     * 异步解析excel
     *
     * @param file
     * @return
     */
    @SneakyThrows
    @Override
    public ImportResultVO analysis(MultipartFile file) {

        ExcelImportUtil.importExcelBySax(file.getInputStream(), AreaExcel.class, importParams, new IReadHandler<AreaExcel>() {
            @SneakyThrows
            @Override
            public void handler(AreaExcel item) {
                log.info("importExcelBySax handler:{}", item);
                int i = atomicInteger.addAndGet(1);
                // 解析后的数据直接放入队列
                executorService.execute(() -> {
                    try {
                        Area area = new Area();
                        if (StringUtils.isBlank(item.getWarehouse())) {
                            resultMap.put(i, "行" + i + " 所属仓库为空");
                            return;
                        }
                        Warehouse warehouse = warehouseService.getByName(item.getWarehouse());
                        if (null == warehouse || null == warehouse.getId()) {
                            resultMap.put(i, "行" + i + " 所属仓库为空");
                            return;
                        }
                        area.setWarehouseId(warehouse.getId());

                        if (StringUtils.isBlank(item.getName())) {
                            resultMap.put(i, "行" + i + " 库区名称为空");
                            return;
                        }
                        area.setName(item.getName());

                        String temperatureType = ConstantTable.TEMPERATURE_TYPE_CODE.get(item.getTemperatureType());
                        if (StringUtils.isBlank(temperatureType)) {
                            resultMap.put(i, "行" + i + " 温度类型为空");
                            return;
                        }
                        area.setTemperatureType(temperatureType);

                        String bearingType = ConstantTable.BEARING_TYPE_CODE.get(item.getBearingType());
                        if (StringUtils.isBlank(bearingType)) {
                            resultMap.put(i, "行" + i + " 承重类型为空");
                            return;
                        }
                        area.setBearingType(bearingType);

                        String useType = ConstantTable.USE_TYPE_CODE.get(item.getUseType());
                        if (StringUtils.isBlank(useType)) {
                            resultMap.put(i, "行" + i + " 用途类型为空");
                            return;
                        }
                        area.setUseType(useType);

                        Integer status = ConstantTable.STATUS_CODE.get(item.getStatus());
                        if (null == status) {
                            resultMap.put(i, "行" + i + " 库区状态为空");
                            return;
                        }
                        area.setStatus(status);

                        if (StringUtils.isBlank(item.getPersonName())) {
                            resultMap.put(i, "行" + i + " 负责人为空");
                            return;
                        }
                        area.setPersonName(item.getPersonName());

                        //areaEntity.setCode(CodeWorker.nextCode(CodeConstant.AREA));
                        area.setCode(UUID.fastUUID().toString());

                        areaMapper.save(area);
                        atomicSuccess.addAndGet(1);
                    } catch (Exception e) {
                        if (e.getClass().getName().equals("org.springframework.dao.DuplicateKeyException")) {
                            resultMap.put(i, "行" + i + " 库区名称重复");
                        } else {
                            log.warn("入库异常 {} ：", e.getClass().getName(), e);
                            resultMap.put(i, "行" + i + " 库区导入失败");
                        }
                    }
                });
            }

            @Override
            public void doAfterAll() {

            }
        });

        ImportResultVO importResultVO = new ImportResultVO();
        // 等待结果
        while (true) {
            if (atomicSuccess.get() + resultMap.size() == atomicInteger.get()) {
                TreeMap treeMap = new TreeMap();
                treeMap.putAll(resultMap);
                importResultVO.setMessage(treeMap.values());
                break;
            } else {
                Thread.sleep(10);
            }
        }

        importResultVO.setSuccess(atomicSuccess.get());
        importResultVO.setFail(resultMap.size());
        importResultVO.setTotal(atomicInteger.get());

        return importResultVO;
    }
}
