## 摘要

该代码库包含一个基于Spring Boot 2.7的仓库管理系统(WMS)应用。主程序启动时会输出包含Knife4j文档链接的日志信息，系统配置存储于application.yml文件中，其中设置了服务端口为8080并定义了MySQL数据库连接参数。

系统每月执行库存盘点任务，通过定时任务在每月20日21:00自动生成盘点作业。区域数据导入采用异步Excel解析机制，配合线程池实现高吞吐处理。出库操作在验证订单明细后会生成拣货任务，并相应更新订单状态。

整体而言，该系统实现了仓库、区域、货位、出入库订单、库存盘点等核心功能管理，提供基于Swagger/Knife4j文档的REST API接口。代码基础架构采用MyBatis、PageHelper、Hutool等Spring生态组件实现上述功能。

## Summary

The repository contains a Spring Boot 2.7 application for a warehouse management system (WMS). The main application logs a startup message with a link to the Knife4j documentation page when run
Configuration is stored in application.yml, which sets the server port to 8080 and defines MySQL connection details

Inventory checks are scheduled monthly. The job generates check tasks automatically on the 20th of each month at 21:00
Data import for areas uses an asynchronous Excel parser with a thread pool for high throughput
Outbound operations create picking tasks after verifying order details, updating order status accordingly

Overall, the system manages warehouses, areas, locations, inbound/outbound orders, inventory checks, and provides REST APIs with Swagger/Knife4j documentation. The code base uses MyBatis, PageHelper, Hutool, and other Spring components to implement these features.
