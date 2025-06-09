/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.job;

import com.itheima.wms.common.constants.CheckStatus;
import com.itheima.wms.mapper.base.AreaMapper;
import com.itheima.wms.mapper.biz.CheckMapper;
import com.itheima.wms.model.dto.base.AreaQueryDTO;
import com.itheima.wms.model.dto.biz.CheckDTO;
import com.itheima.wms.model.entity.base.Area;
import com.itheima.wms.model.entity.biz.Check;
import com.itheima.wms.service.biz.CheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class CheckJob {

    /**
     * 计划盘点：为定时月盘点，和周盘点。月盘点，每个月20号21点，自动根据仓库生成每个仓库每个库区，按库位进行 的月盘点单。
     * <p>
     * 周盘点，每周五21点系统自动生成每个仓库，每个库区按库位的周盘点（取消）。
     */
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    private CheckService checkService;

    @Scheduled(cron = "0 0 21 20 * ?")     // 每个月20号21点
    //@Scheduled(cron = "0 3 17 * * ?")
    public void check() {
        log.info("定时盘点任务启动=================");
        List<Long> ids = new ArrayList<>();
        // 查询全部库区
        List<Area> areaEntities = areaMapper.page(AreaQueryDTO.builder().build());
        log.info("全部库区：{}", areaEntities);
        // 遍历仓库生成盘点单
        areaEntities.forEach(areaEntity -> {
            try {
                Long id = checkService.generatorCheck(areaEntity);
                ids.add(id);
                log.info("库区：{} 已生成盘点单", areaEntity);
            } catch (Exception e) {
                log.warn("库区：{} 生成盘点单异常", areaEntity, e);
            }
        });

        // 生成盘点任务
        checkService.task(ids);

        log.info("定时盘点任务结束=================");
    }


    //@Scheduled(cron = "0 0/10 * * * ?") // 每十分钟一次
    public void checkExpire() {
        log.info("定时盘点任务 过期扫描 启动=================");
        List<Check> checks = checkMapper.listByStatus(CheckStatus.NEW);

        checks.forEach(checkEntity -> {
            if (checkEntity.getPlanCheckTime().compareTo(LocalDateTime.now()) < 0) {
                log.info("任务：{} 已过期", checkEntity);
                CheckDTO checkDTO = new CheckDTO();
                checkDTO.setId(checkEntity.getId());
                checkService.cancel(checkDTO);
            }
        });

        log.info("定时盘点任务 过期扫描 结束=================");
    }

}
