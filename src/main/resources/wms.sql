/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : wms

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 22/02/2024 11:09:18
*/

CREATE
    DATABASE IF NOT EXISTS wms;
USE
    wms;

SET NAMES utf8mb4;
SET
    FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wms_area
-- ----------------------------
DROP TABLE IF EXISTS `wms_area`;
CREATE TABLE `wms_area`
(
    `id`               bigint                                                        NOT NULL AUTO_INCREMENT,
    `warehouse_id`     bigint                                                        NULL DEFAULT NULL COMMENT '仓库id',
    `code`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '编码',
    `name`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '名称',
    `temperature_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '温度类型 常温：CW、冷藏：LC、恒温：HW',
    `bearing_type`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '承重类型 重型：ZX、轻型：QX',
    `use_type`         varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '用途类型 入库缓存区：RKHCQ 出库缓存区：CKHCQ 存储区：CCQ 分拣区：FJQ 质检区：ZJQ',
    `person_name`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '负责人姓名',
    `phone`            varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '联系电话',
    `included_num`     int                                                           NULL DEFAULT NULL COMMENT '库位数量',
    `status`           int                                                           NULL DEFAULT NULL COMMENT '状态 0 停用 1启用',
    `remark`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`      datetime                                                      NULL DEFAULT NULL,
    `update_time`      datetime                                                      NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `code` (`code`) USING BTREE,
    INDEX `name` (`name`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 23
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '库区表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_area
-- ----------------------------
INSERT INTO `wms_area`
VALUES (1, 1, 'KQ000001', '入库缓存区', 'CW', 'ZX', 'RKHCQ', '王一博', '18899998888', 5, 1, '入库缓存区', 'admin', 'admin',
        '2024-02-03 10:43:20', '2024-02-20 11:41:16');
INSERT INTO `wms_area`
VALUES (2, 1, 'KQ000002', '出库缓存区', 'CW', 'ZX', 'CKHCQ', '赵攀', '18899998888', 4, 1, '出库缓存区', 'admin', 'admin',
        '2024-02-03 10:43:20', '2024-02-03 10:43:20');
INSERT INTO `wms_area`
VALUES (3, 1, 'KQ000003', '储存区', 'CW', 'ZX', 'CCQ', '陈立', '18899998888', 3, 1, '储存区', 'admin', 'admin',
        '2024-02-03 10:43:20', '2024-02-03 10:43:20');
INSERT INTO `wms_area`
VALUES (4, 1, 'KQ000004', '分拣区', 'LC', 'ZX', 'FJQ', '王丽丽', '18899998888', 2, 1, '分拣区', 'admin', 'admin',
        '2024-02-03 10:43:20', '2024-02-03 10:43:20');
INSERT INTO `wms_area`
VALUES (5, 1, 'KQ000005', '质检区', 'LC', 'ZX', 'ZJQ', '王天', '18899998888', 1, 1, '质检区', 'admin', 'admin',
        '2024-02-03 10:43:20', '2024-02-03 10:43:20');
INSERT INTO `wms_area`
VALUES (6, 2, 'KQ000006', '储存区', 'HW', 'QX', 'CCQ', '王天', '18899998888', 2, 1, '储存区', 'admin', 'admin',
        '2024-02-03 10:43:20', '2024-02-03 10:43:20');
INSERT INTO `wms_area`
VALUES (7, 3, 'KQ000007', '分拣区', 'CW', 'QX', 'FJQ', '张华', '18899998888', 1, 1, '分拣区', 'admin', 'admin',
        '2024-02-03 10:43:20', '2024-02-03 10:43:20');
INSERT INTO `wms_area`
VALUES (8, 4, 'KQ000008', '质检区', 'CW', 'QX', 'ZJQ', '张华', '18899998888', 2, 1, '质检区', 'admin', 'admin',
        '2024-02-03 10:43:20', '2024-02-03 10:43:20');

-- ----------------------------
-- Table structure for wms_carrier
-- ----------------------------
DROP TABLE IF EXISTS `wms_carrier`;
CREATE TABLE `wms_carrier`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT,
    `code`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '编码',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '名称',
    `person_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '联系人姓名',
    `phone`       varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '联系电话',
    `email`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '联系邮箱',
    `location`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省市区描述',
    `province`    varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '省',
    `city`        varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '市',
    `area`        varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '区',
    `address`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
    `status`      int                                                           NULL DEFAULT NULL COMMENT '状态',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time` datetime                                                      NULL DEFAULT NULL,
    `update_time` datetime                                                      NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '承运商'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_carrier
-- ----------------------------
INSERT INTO `wms_carrier`
VALUES (1, 'CYS000001', '顺丰物流', '王卫', '19900000000', 'ww@itcast.com\n', '天津市/市辖区/河西区', '120000', '129900', '120103',
        '太平大街101号', 1, NULL, 'admin', 'admin', '2024-02-03 11:54:14', '2024-02-03 11:54:14');
INSERT INTO `wms_carrier`
VALUES (2, 'CYS000002', '圆通物流', '喻会蛟', '18800009999', 'yhj@itcast.cn', '天津市/市辖区/河西区', '120000', '129900', '120103',
        '太平大街102号', 1, NULL, 'admin', 'admin', '2024-02-03 11:54:14', '2024-02-03 11:54:14');

-- ----------------------------
-- Table structure for wms_check
-- ----------------------------
DROP TABLE IF EXISTS `wms_check`;
CREATE TABLE `wms_check`
(
    `id`              bigint                                                        NOT NULL AUTO_INCREMENT,
    `code`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '盘点单号',
    `owner_id`        bigint                                                        NULL DEFAULT NULL COMMENT '货主id',
    `plan_check_time` datetime                                                      NULL DEFAULT NULL COMMENT '计划盘点时间',
    `reason`          varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '盘点原因: GH规划 HZ货主 CY差异',
    `dimension`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盘点维度: KW库位 HP货品',
    `type`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盘点类型 随机盘点:SJPD、计划盘点:JHPD',
    `warehouse_id`    bigint                                                        NULL DEFAULT NULL,
    `area_id`         bigint                                                        NULL DEFAULT NULL,
    `status`          int                                                           NULL DEFAULT NULL COMMENT '盘点单状态:1新建 2一盘中 3一盘完成 4复盘中 5复盘完成 6已取消',
    `remark`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `create_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`     datetime                                                      NULL DEFAULT NULL,
    `update_time`     datetime                                                      NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '盘点单'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_check
-- ----------------------------
INSERT INTO `wms_check`
VALUES (1, 'PD10012', NULL, '2024-02-24 00:00:00', 'GH', 'KW', 'SJPD', 1, 3, 5, NULL, 'admin', 'admin',
        '2024-02-22 09:56:35', '2024-02-22 09:59:57');
INSERT INTO `wms_check`
VALUES (2, 'PD10013', NULL, '2024-02-22 10:00:35', 'GH', 'KW', 'SJPD', 1, 3, 3, NULL, 'admin', 'admin',
        '2024-02-22 10:00:37', '2024-02-22 10:01:00');

-- ----------------------------
-- Table structure for wms_check_list
-- ----------------------------
DROP TABLE IF EXISTS `wms_check_list`;
CREATE TABLE `wms_check_list`
(
    `id`             bigint                                                        NOT NULL AUTO_INCREMENT,
    `master_id`      bigint                                                        NULL DEFAULT NULL COMMENT '主记录id',
    `stock_id`       bigint                                                        NULL DEFAULT NULL COMMENT '库存',
    `stock_num`      int                                                           NULL DEFAULT NULL COMMENT '库存数量',
    `check_num`      int                                                           NULL DEFAULT NULL COMMENT '盘点数量',
    `difference_num` int                                                           NULL DEFAULT NULL COMMENT '数量差异',
    `status`         int                                                           NULL DEFAULT NULL COMMENT '状态 1 一盘，2 复盘',
    `remark`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`    datetime                                                      NULL DEFAULT NULL,
    `update_time`    datetime                                                      NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '盘点清单表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_check_list
-- ----------------------------
INSERT INTO `wms_check_list`
VALUES (1, 1, 1, 250, 240, -10, 1, NULL, 'admin', 'admin', '2024-02-22 09:56:56', '2024-02-22 09:58:17');
INSERT INTO `wms_check_list`
VALUES (2, 1, 1, 250, 250, 0, 2, NULL, 'admin', 'admin', '2024-02-22 09:59:18', '2024-02-22 09:59:52');
INSERT INTO `wms_check_list`
VALUES (3, 2, 2, 100, 90, -10, 1, NULL, 'admin', 'admin', '2024-02-22 10:00:40', '2024-02-22 10:00:57');

-- ----------------------------
-- Table structure for wms_check_task
-- ----------------------------
DROP TABLE IF EXISTS `wms_check_task`;
CREATE TABLE `wms_check_task`
(
    `id`             bigint                                                        NOT NULL AUTO_INCREMENT COMMENT 'id',
    `master_id`      bigint                                                        NULL DEFAULT NULL COMMENT '主记录id',
    `code`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '任务单号',
    `check_code`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '盘点单号',
    `plan_task_time` datetime                                                      NULL DEFAULT NULL COMMENT '计划作业时间',
    `owner_id`       bigint                                                        NULL DEFAULT NULL COMMENT '货主id',
    `dimension`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '盘点维度 库位：KW、货品：HP',
    `type`           varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '盘点类型 随机盘点：SJPD、计划盘点：JSPD',
    `warehouse_id`   bigint                                                        NULL DEFAULT NULL COMMENT '仓库id',
    `area_id`        bigint                                                        NULL DEFAULT NULL COMMENT '库区id',
    `stock_total`    int                                                           NULL DEFAULT NULL COMMENT '库存总数',
    `check_num`      int                                                           NULL DEFAULT NULL COMMENT '盘点次数',
    `person_name`    varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '负责人姓名',
    `check_total`    int                                                           NULL DEFAULT NULL COMMENT '盘点数量',
    `difference_num` int                                                           NULL DEFAULT NULL COMMENT '数量差异',
    `status`         int                                                           NULL DEFAULT NULL COMMENT '任务状态 1待分配、2盘点中、3盘点已完成、4已生成损益、5已生成复盘',
    `remark`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`    datetime                                                      NULL DEFAULT NULL,
    `update_time`    datetime                                                      NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '盘点任务表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_check_task
-- ----------------------------
INSERT INTO `wms_check_task`
VALUES (1, 1, 'PDRW10004', 'PD10012', '2024-02-24 00:00:00', NULL, 'KW', 'SJPD', 1, 3, 250, 1, '张文礼', 240, 10, 5, NULL,
        'admin', 'admin', '2024-02-22 09:57:28', '2024-02-22 09:59:18');
INSERT INTO `wms_check_task`
VALUES (2, 1, 'PDRW10005', 'PD10012', '2024-02-24 00:00:00', NULL, 'KW', 'SJPD', 1, 3, 250, 2, '张文礼', 250, 0, 3, NULL,
        'admin', 'admin', '2024-02-22 09:59:18', '2024-02-22 09:59:57');
INSERT INTO `wms_check_task`
VALUES (3, 2, 'PDRW10006', 'PD10013', '2024-02-22 10:00:35', NULL, 'KW', 'SJPD', 1, 3, 100, 1, '张文礼', 90, 10, 4, NULL,
        'admin', 'admin', '2024-02-22 10:00:46', '2024-02-22 10:01:01');

-- ----------------------------
-- Table structure for wms_code_factory
-- ----------------------------
DROP TABLE IF EXISTS `wms_code_factory`;
CREATE TABLE `wms_code_factory`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT,
    `type`        varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '类型',
    `content`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '内容/计数值',
    `status`      int                                                           NULL DEFAULT NULL COMMENT '状态',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time` datetime                                                      NULL DEFAULT NULL,
    `update_time` datetime                                                      NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '编号工厂表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_code_factory
-- ----------------------------
INSERT INTO `wms_code_factory`
VALUES (1, 'CK', '10009', 1, 'CK', 'admin', 'admin', '2024-02-20 10:26:07', '2024-02-22 09:24:16');
INSERT INTO `wms_code_factory`
VALUES (2, 'KQ', '10001', 1, 'KQ', 'admin', 'admin', '2024-02-20 10:26:07', '2024-02-20 10:26:07');
INSERT INTO `wms_code_factory`
VALUES (3, 'KW', '10000', 1, 'KW', 'admin', 'admin', '2024-02-20 10:26:07', '2024-02-20 10:26:07');
INSERT INTO `wms_code_factory`
VALUES (4, 'HP', '10021', 1, 'HP', 'admin', 'admin', '2024-02-20 10:26:07', '2024-02-22 09:45:30');
INSERT INTO `wms_code_factory`
VALUES (5, 'HPLX', '10001', 1, 'HPLX', 'admin', 'admin', '2024-02-20 10:26:07', '2024-02-20 10:26:07');
INSERT INTO `wms_code_factory`
VALUES (6, 'HZ', '10039', 1, 'HZ', 'admin', 'admin', '2024-02-20 10:26:07', '2024-02-20 15:22:10');
INSERT INTO `wms_code_factory`
VALUES (7, 'PD', '10013', 1, 'PD', 'admin', 'admin', '2024-02-20 10:26:07', '2024-02-22 10:00:28');
INSERT INTO `wms_code_factory`
VALUES (8, 'RK', '10033', 1, 'RK', 'admin', 'admin', '2024-02-20 10:26:07', '2024-02-22 10:05:57');
INSERT INTO `wms_code_factory`
VALUES (9, 'SHRW', '10013', NULL, NULL, 'admin', 'admin', '2024-02-20 15:54:40', '2024-02-22 10:08:24');
INSERT INTO `wms_code_factory`
VALUES (10, 'SJRW', '10012', NULL, NULL, 'admin', 'admin', '2024-02-20 16:05:52', '2024-02-22 09:40:17');
INSERT INTO `wms_code_factory`
VALUES (11, 'JHRW', '10005', NULL, NULL, 'admin', 'admin', '2024-02-20 16:37:58', '2024-02-22 09:50:48');
INSERT INTO `wms_code_factory`
VALUES (12, 'JJRW', '10004', NULL, NULL, 'admin', 'admin', '2024-02-20 16:42:12', '2024-02-22 09:52:40');
INSERT INTO `wms_code_factory`
VALUES (13, 'PDRW', '10006', NULL, NULL, 'admin', 'admin', '2024-02-21 14:38:05', '2024-02-22 10:00:46');
INSERT INTO `wms_code_factory`
VALUES (14, 'SY', '10001', NULL, NULL, 'admin', 'admin', '2024-02-21 15:19:35', '2024-02-22 10:01:01');

-- ----------------------------
-- Table structure for wms_goods
-- ----------------------------
DROP TABLE IF EXISTS `wms_goods`;
CREATE TABLE `wms_goods`
(
    `id`               bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `goods_type_id`    bigint                                                        NULL DEFAULT NULL COMMENT '货品类型ID',
    `code`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '编码',
    `name`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '名称',
    `bar_code`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '条码',
    `owner_id`         bigint                                                        NULL DEFAULT NULL COMMENT '货主ID',
    `inspection_type`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '质检方式',
    `temperature_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '温度要求',
    `bearing_type`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '承重要求',
    `volume`           decimal(10, 2)                                                NULL DEFAULT NULL COMMENT '体积',
    `warehouse_id`     bigint                                                        NULL DEFAULT NULL COMMENT '仓库ID',
    `area_id`          bigint                                                        NULL DEFAULT NULL COMMENT '库区ID',
    `price`            decimal(10, 2)                                                NULL DEFAULT NULL COMMENT '单价',
    `unit`             varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '单位',
    `guarantee_day`    int                                                           NULL DEFAULT NULL COMMENT '保质天数',
    `status`           int                                                           NULL DEFAULT NULL COMMENT '状态',
    `remark`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`      datetime                                                      NULL DEFAULT NULL,
    `update_time`      datetime                                                      NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '货品管理表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_goods
-- ----------------------------
INSERT INTO `wms_goods`
VALUES (1, 1, 'HP000001', '冷冻肉', '2025122300001', 1, 'BCL', 'LC', 'ZX', 5.00, 1, 3, 10.00, '盒', 365, 1, '冷冻肉', 'admin',
        'admin', '2024-02-03 11:31:54', '2024-02-20 14:55:44');
INSERT INTO `wms_goods`
VALUES (2, 1, 'HP000002', '牛肉酱', '2025122300002', 1, 'BCL', 'CW', 'ZX', 5.00, 1, 3, 15.00, '盒', 365, 1, '牛肉酱', 'admin',
        'admin', '2024-02-03 11:31:54', '2024-02-20 14:55:37');
INSERT INTO `wms_goods`
VALUES (3, 2, 'HP000003', '棉衣', '2025122300011', 1, 'BCL', 'CW', 'BX', 5.00, 2, 3, 100.00, '件', 365, 1, '棉衣', 'admin',
        'admin', '2024-02-03 11:31:54', '2024-02-20 15:11:55');
INSERT INTO `wms_goods`
VALUES (4, 2, 'HP000004', '羽绒服', '2025122300012', 2, 'BCL', 'CW', 'ZX', 5.00, 2, 6, 150.00, '件', 365, 1, '羽绒服', 'admin',
        'admin', '2024-02-03 11:31:54', '2024-02-20 15:01:40');

-- ----------------------------
-- Table structure for wms_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `wms_goods_type`;
CREATE TABLE `wms_goods_type`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `code`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '编码',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '名称',
    `status`      int                                                           NULL DEFAULT NULL COMMENT '状态',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time` datetime                                                      NULL DEFAULT NULL,
    `update_time` datetime                                                      NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '货品类型表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_goods_type
-- ----------------------------
INSERT INTO `wms_goods_type`
VALUES (1, 'HPLX-00001', '食品', 1, '食品', 'admin', 'admin', '2024-02-03 11:19:50', '2024-02-03 11:19:50');
INSERT INTO `wms_goods_type`
VALUES (2, 'HPLX-00002', '衣物', 1, '衣物', 'admin', 'admin', '2024-02-03 11:19:50', '2024-02-03 11:19:50');

-- ----------------------------
-- Table structure for wms_grounding
-- ----------------------------
DROP TABLE IF EXISTS `wms_grounding`;
CREATE TABLE `wms_grounding`
(
    `id`              bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主记录id',
    `code`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '上架任务单号',
    `receipt_code`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '入库单号',
    `owner_id`        bigint                                                        NULL DEFAULT NULL COMMENT '货主id',
    `warehouse_id`    bigint                                                        NULL DEFAULT NULL COMMENT '仓库id',
    `area_id`         bigint                                                        NULL DEFAULT NULL COMMENT '库区id',
    `master_id`       bigint                                                        NULL DEFAULT NULL,
    `person_name`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '负责人',
    `plan_num`        int                                                           NULL DEFAULT 0 COMMENT '预计到货数',
    `real_num`        int                                                           NULL DEFAULT 0 COMMENT '实收货数',
    `grounding_num`   int                                                           NULL DEFAULT 0 COMMENT '上架数量',
    `difference_num`  int                                                           NULL DEFAULT 0 COMMENT '数量差异',
    `completion_time` datetime                                                      NULL DEFAULT CURRENT_TIMESTAMP COMMENT '完成时间',
    `status`          int                                                           NULL DEFAULT 0 COMMENT '上架情况 1待分配、2上架中、3上架完成、4已取消',
    `remark`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
    `create_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT '' COMMENT '创建人',
    `update_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT '' COMMENT '更新人',
    `create_time`     datetime                                                      NULL DEFAULT NULL,
    `update_time`     datetime                                                      NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '上架任务表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_grounding
-- ----------------------------
INSERT INTO `wms_grounding`
VALUES (1, 'SJRW10010', 'RK10028', 1, 1, NULL, 1, '张文礼', 600, 600, 600, 0, '2024-02-21 17:12:28', 3, 'e', 'admin',
        'admin', '2024-02-21 17:10:28', '2024-02-21 17:12:28');
INSERT INTO `wms_grounding`
VALUES (2, 'SJRW10011', 'RK10029', 1, 1, NULL, 2, '张文礼', 150, 150, 150, 0, '2024-02-21 17:21:37', 3, 'erewr', 'admin',
        'admin', '2024-02-21 17:20:16', '2024-02-21 17:21:37');
INSERT INTO `wms_grounding`
VALUES (3, 'SJRW10012', 'RK10031', 1, 1, NULL, 3, '张文礼', 10, 10, 10, 0, '2024-02-22 09:41:56', 3, 'retret', 'admin',
        'admin', '2024-02-22 09:40:17', '2024-02-22 09:41:56');

-- ----------------------------
-- Table structure for wms_handover
-- ----------------------------
DROP TABLE IF EXISTS `wms_handover`;
CREATE TABLE `wms_handover`
(
    `id`              bigint                                                        NOT NULL AUTO_INCREMENT,
    `master_id`       bigint                                                        NULL DEFAULT NULL COMMENT '主记录id',
    `code`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '交接任务单号',
    `outbound_code`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '出库单号',
    `delivery_type`   varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '配送方式',
    `carrier_id`      bigint                                                        NULL DEFAULT NULL COMMENT '承运商id',
    `bill_code`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '运单编号',
    `handover_name`   varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '交接员',
    `completion_time` datetime                                                      NULL DEFAULT NULL COMMENT '交接完成时间',
    `driver_name`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '司机姓名',
    `driver_phone`    varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '司机电话',
    `status`          int                                                           NULL DEFAULT NULL COMMENT '交接状态 1新建、2交接中、3交接完成',
    `remark`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`     datetime                                                      NULL DEFAULT NULL,
    `update_time`     datetime                                                      NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '交接任务表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_handover
-- ----------------------------
INSERT INTO `wms_handover`
VALUES (1, 1, 'JJRW10003', 'HP10019', NULL, 1, 'SF202412121234', '张文礼', '2024-02-21 17:18:26', '小张司机', '19900000000', 3,
        'pp', 'admin', 'admin', '2024-02-21 17:18:15', '2024-02-21 17:18:26');
INSERT INTO `wms_handover`
VALUES (2, 2, 'JJRW10004', 'HP10021', NULL, 1, '324324', '张文礼', '2024-02-22 09:53:05', '234', '18834343434', 3, '',
        'admin', 'admin', '2024-02-22 09:52:40', '2024-02-22 09:53:05');

-- ----------------------------
-- Table structure for wms_increase_decrease
-- ----------------------------
DROP TABLE IF EXISTS `wms_increase_decrease`;
CREATE TABLE `wms_increase_decrease`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `code`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '损益单号',
    `stock_id`    bigint                                                        NULL DEFAULT NULL COMMENT '库存',
    `id_num`      int                                                           NULL DEFAULT NULL COMMENT '损益数量',
    `id_money`    decimal(10, 2)                                                NULL DEFAULT NULL COMMENT '损益金额',
    `id_source`   varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '损益来源 盘点：PD、上架：SJ、拣货：JH',
    `task_id`     bigint                                                        NULL DEFAULT NULL COMMENT '任务id',
    `task_code`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '任务单号',
    `status`      int                                                           NULL DEFAULT 1 COMMENT '损益单状态 1.新建、2已调整',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time` datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '损益单表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_increase_decrease
-- ----------------------------
INSERT INTO `wms_increase_decrease`
VALUES (1, 'SY10001', 2, -10, -150.00, 'PD', 3, 'PDRW10006', 3, NULL, 'admin', 'admin', '2024-02-22 10:01:01',
        '2024-02-22 10:02:19');

-- ----------------------------
-- Table structure for wms_location
-- ----------------------------
DROP TABLE IF EXISTS `wms_location`;
CREATE TABLE `wms_location`
(
    `id`               bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `warehouse_id`     bigint                                                        NULL DEFAULT NULL COMMENT '仓库id',
    `area_id`          bigint                                                        NULL DEFAULT NULL COMMENT '库区id',
    `owner_id`         bigint                                                        NULL DEFAULT NULL COMMENT '货主id',
    `code`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '编码',
    `name`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '名称',
    `temperature_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '温度类型',
    `bearing_type`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '承重类型',
    `use_type`         varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '用途类型',
    `use_status`       int                                                           NULL DEFAULT NULL COMMENT '使用状态：0空闲 1占用',
    `max_num`          bigint                                                        NULL DEFAULT NULL COMMENT '承载上限',
    `max_volume`       decimal(10, 2)                                                NULL DEFAULT NULL COMMENT '承载体积',
    `max_weight`       decimal(10, 2)                                                NULL DEFAULT NULL COMMENT '承载重量',
    `location_row`     int                                                           NULL DEFAULT NULL COMMENT '排',
    `location_column`  int                                                           NULL DEFAULT NULL COMMENT '列',
    `location_layer`   int                                                           NULL DEFAULT NULL COMMENT '层',
    `location_length`  decimal(10, 2)                                                NULL DEFAULT NULL COMMENT '长',
    `location_width`   decimal(10, 2)                                                NULL DEFAULT NULL COMMENT '宽',
    `location_high`    decimal(10, 2)                                                NULL DEFAULT NULL COMMENT '高',
    `status`           int                                                           NULL DEFAULT NULL COMMENT '状态 0 停用 1启用',
    `frozen`           int                                                           NULL DEFAULT NULL COMMENT '状态 0 冻结 1正常',
    `remark`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`      datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 27
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '库位表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_location
-- ----------------------------
INSERT INTO `wms_location`
VALUES (1, 1, 1, 1, 'KW000001', 'H1-1-1', 'CW', 'ZX', 'RKHCQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'RKHCQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-21 17:02:18');
INSERT INTO `wms_location`
VALUES (2, 1, 1, 1, 'KW000002', 'H1-1-2', 'CW', 'ZX', 'RKHCQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'RKHCQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-20 17:24:41');
INSERT INTO `wms_location`
VALUES (3, 1, 1, 1, 'KW000003', 'H1-1-3', 'LC', 'ZX', 'RKHCQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'RKHCQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-20 16:59:18');
INSERT INTO `wms_location`
VALUES (4, 1, 1, 1, 'KW000004', 'H1-2-4', 'LC', 'ZX', 'RKHCQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'RKHCQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-20 15:20:16');
INSERT INTO `wms_location`
VALUES (5, 1, 1, 1, 'KW000005', 'H1-2-5', 'LC', 'ZX', 'RKHCQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'RKHCQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-20 15:20:16');
INSERT INTO `wms_location`
VALUES (6, 1, 2, 1, 'KW000006', 'H1-2-6', 'CW', 'ZX', 'CKHCQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'CKHCQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-21 17:02:18');
INSERT INTO `wms_location`
VALUES (7, 1, 2, 1, 'KW000007', 'H1-3-7', 'CW', 'QX', 'CKHCQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'CKHCQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-20 15:20:16');
INSERT INTO `wms_location`
VALUES (8, 1, 2, 1, 'KW000008', 'H1-3-8', 'CW', 'QX', 'CKHCQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'CKHCQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-20 15:20:16');
INSERT INTO `wms_location`
VALUES (9, 1, 2, 1, 'KW000009', 'H1-3-9', 'CW', 'QX', 'CKHCQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'CKHCQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-20 15:20:16');
INSERT INTO `wms_location`
VALUES (10, 1, 3, 1, 'KW000010', 'H1-3-10', 'CW', 'QX', 'CCQ', 1, 2000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 0, 'CCQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-22 09:59:18');
INSERT INTO `wms_location`
VALUES (11, 1, 3, 1, 'KW000011', 'H2-4-11', 'CW', 'QX', 'CCQ', 1, 2000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 0, 'CCQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-22 10:00:40');
INSERT INTO `wms_location`
VALUES (12, 1, 3, 1, 'KW000012', 'H2-4-12', 'CW', 'QX', 'CCQ', 1, 2000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'CCQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-21 17:18:15');
INSERT INTO `wms_location`
VALUES (13, 1, 4, 1, 'KW000013', 'H2-4-13', 'CW', 'QX', 'FJQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'FJQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-20 15:20:16');
INSERT INTO `wms_location`
VALUES (14, 1, 4, 1, 'KW000014', 'H2-5-14', 'HW', 'BX', 'FJQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'FJQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-20 15:20:16');
INSERT INTO `wms_location`
VALUES (15, 1, 5, 1, 'KW000015', 'H2-5-15', 'HW', 'BX', 'ZJQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'ZJQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-20 15:20:16');
INSERT INTO `wms_location`
VALUES (16, 2, 6, 2, 'KW000016', 'H2-5-16', 'HW', 'BX', 'CCQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'CCQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-21 11:45:28');
INSERT INTO `wms_location`
VALUES (17, 2, 6, 2, 'KW000017', 'H2-5-17', 'HW', 'BX', 'CCQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'CCQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-20 15:20:54');
INSERT INTO `wms_location`
VALUES (18, 3, 7, 3, 'KW000018', 'H2-5-18', 'HW', 'BX', 'FJQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'FJQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-20 15:21:17');
INSERT INTO `wms_location`
VALUES (19, 4, 8, 3, 'KW000019', 'H2-5-19', 'HW', 'BX', 'ZJQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'ZJQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-20 15:22:05');
INSERT INTO `wms_location`
VALUES (20, 4, 8, 3, 'KW000020', 'H2-5-20', 'HW', 'BX', 'ZJQ', 0, 10000, 10000.00, 10000.00, 1000, 1000, 1000, 10000.00,
        10000.00, 10000.00, 1, 1, 'ZJQ', 'admin', 'admin', '2024-02-03 10:57:54', '2024-02-20 15:22:05');

-- ----------------------------
-- Table structure for wms_location_plan
-- ----------------------------
DROP TABLE IF EXISTS `wms_location_plan`;
CREATE TABLE `wms_location_plan`
(
    `id`              bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `warehouse_id`    bigint                                                        NULL DEFAULT NULL COMMENT '仓库id',
    `area_id`         bigint                                                        NULL DEFAULT NULL COMMENT '库区id',
    `location_id`     bigint                                                        NULL DEFAULT NULL COMMENT '库位id',
    `receipt_list_id` bigint                                                        NULL DEFAULT NULL COMMENT '入库明细id',
    `group_id`        bigint                                                        NULL DEFAULT NULL COMMENT '分组id',
    `num`             int                                                           NULL DEFAULT NULL COMMENT '数量',
    `status`          int                                                           NULL DEFAULT NULL COMMENT '状态 0 推荐 1使用',
    `remark`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`     datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '库位方案'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_location_plan
-- ----------------------------
INSERT INTO `wms_location_plan`
VALUES (1, 1, 3, 10, 1, 1760230572514471938, 200, NULL, NULL, 'admin', 'admin', '2024-02-21 17:10:47',
        '2024-02-21 17:10:47');
INSERT INTO `wms_location_plan`
VALUES (2, 1, 3, 11, 2, 1760230607838900225, 200, NULL, NULL, 'admin', 'admin', '2024-02-21 17:10:55',
        '2024-02-21 17:10:55');
INSERT INTO `wms_location_plan`
VALUES (3, 1, 3, 12, 3, 1760230651245752321, 200, NULL, NULL, 'admin', 'admin', '2024-02-21 17:11:05',
        '2024-02-21 17:11:05');
INSERT INTO `wms_location_plan`
VALUES (13, 1, 3, 10, 4, 1760233029596475393, 150, NULL, NULL, 'admin', 'admin', '2024-02-21 17:21:11',
        '2024-02-21 17:21:11');
INSERT INTO `wms_location_plan`
VALUES (14, 1, 3, 10, 5, 1760479979671474178, 10, NULL, NULL, 'admin', 'admin', '2024-02-22 09:41:50',
        '2024-02-22 09:41:50');

-- ----------------------------
-- Table structure for wms_outbound
-- ----------------------------
DROP TABLE IF EXISTS `wms_outbound`;
CREATE TABLE `wms_outbound`
(
    `id`            bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `code`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '出库单号',
    `bill_code`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '运单编号',
    `type`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '出库类型',
    `owner_id`      bigint                                                        NULL DEFAULT NULL COMMENT '货主id',
    `warehouse_id`  bigint                                                        NULL DEFAULT NULL COMMENT '仓库id',
    `area_id`       bigint                                                        NULL DEFAULT NULL COMMENT '库区id',
    `plan_out_time` datetime                                                      NULL DEFAULT NULL COMMENT '计划出库时间',
    `goods_num`     int                                                           NULL DEFAULT NULL COMMENT '货品数量',
    `wave_status`   int                                                           NULL DEFAULT NULL COMMENT '波次执行状态 0失败，1成功',
    `carrier_id`    bigint                                                        NULL DEFAULT NULL COMMENT '承运商id',
    `license`       varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '车牌号',
    `driver_name`   varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '司机姓名',
    `driver_phone`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '司机电话',
    `receiver_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '收货人姓名',
    `status`        int                                                           NULL DEFAULT NULL COMMENT '出库单状态 1新建、2拣货中、3已取消、4拣货完成、5交接中、6交接完成',
    `remark`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`   datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '出库单'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_outbound
-- ----------------------------
INSERT INTO `wms_outbound`
VALUES (1, 'HP10019', 'SF202412121234', '0', 1, 1, 3, '2024-02-22 00:00:00', 300, NULL, 1, 'ioio', '小张司机',
        '19900000000', 'uu', 6, 'pp', 'admin', 'admin', '2024-02-21 17:17:14', '2024-02-21 17:18:26');
INSERT INTO `wms_outbound`
VALUES (2, 'HP10021', '324324', '0', 1, 1, 3, '2024-02-23 00:00:00', 10, NULL, 1, '3434', '234', '18834343434', '3443',
        6, '', 'admin', 'admin', '2024-02-22 09:46:28', '2024-02-22 09:53:05');

-- ----------------------------
-- Table structure for wms_outbound_list
-- ----------------------------
DROP TABLE IF EXISTS `wms_outbound_list`;
CREATE TABLE `wms_outbound_list`
(
    `id`             bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主记录id',
    `master_id`      bigint                                                        NULL DEFAULT NULL COMMENT '主记录id',
    `stock_id`       bigint                                                        NULL DEFAULT NULL COMMENT '库存',
    `outbound_num`   int                                                           NULL DEFAULT NULL COMMENT '发货数量',
    `picking_num`    int                                                           NULL DEFAULT NULL COMMENT '实拣数量',
    `difference_num` int                                                           NULL DEFAULT NULL COMMENT '数量差异',
    `status`         int                                                           NULL DEFAULT NULL COMMENT '状态',
    `remark`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`    datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`    datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '出库清单'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_outbound_list
-- ----------------------------
INSERT INTO `wms_outbound_list`
VALUES (1, 1, 1, 100, 100, 0, 1, NULL, 'admin', 'admin', '2024-02-21 17:17:37', '2024-02-21 17:18:06');
INSERT INTO `wms_outbound_list`
VALUES (2, 1, 2, 100, 100, 0, 1, NULL, 'admin', 'admin', '2024-02-21 17:17:37', '2024-02-21 17:18:09');
INSERT INTO `wms_outbound_list`
VALUES (3, 1, 3, 100, 100, 0, 1, NULL, 'admin', 'admin', '2024-02-21 17:17:37', '2024-02-21 17:18:12');
INSERT INTO `wms_outbound_list`
VALUES (4, 2, 1, 10, 10, 0, 1, NULL, 'admin', 'admin', '2024-02-22 09:46:48', '2024-02-22 09:52:15');

-- ----------------------------
-- Table structure for wms_owner
-- ----------------------------
DROP TABLE IF EXISTS `wms_owner`;
CREATE TABLE `wms_owner`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `code`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '编码',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '名称',
    `person_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '联系人姓名',
    `phone`       varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '联系电话',
    `email`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '联系邮箱',
    `location`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省市区描述',
    `province`    varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '省',
    `city`        varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '市',
    `area`        varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '区',
    `address`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
    `status`      int                                                           NULL DEFAULT NULL COMMENT '状态',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time` datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '货主表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_owner
-- ----------------------------
INSERT INTO `wms_owner`
VALUES (1, 'HZ000001', '晓雨', '刘晓雨', '18899999999', 'liuxyu@itcast.com', '山西省/长治市/潞城区', '140000', '140400', '140406',
        '长江1号别墅', 1, 'VIP客户', 'admin', 'admin', '2024-02-03 11:48:15', '2024-02-20 15:20:04');
INSERT INTO `wms_owner`
VALUES (2, 'HZ000002', '王姐', '王丽莉', '18800000000', 'wangjie@itcast.com', '河北省/秦皇岛市/山海关区', '130000', '130300', '130303',
        '海边5号别墅', 1, 'VIP客户', 'admin', 'admin', '2024-02-03 11:48:15', '2024-02-20 15:22:11');
INSERT INTO `wms_owner`
VALUES (3, 'HZ000003', '婉婉', '李婉婉', '18900000000', 'wangwang@itcast.com', '山西省/长治市/上党区', '140000', '140400', '140404',
        '和平小区3栋', 1, 'VIP客户', 'admin', 'admin', '2024-02-03 11:48:15', '2024-02-20 15:21:54');

-- ----------------------------
-- Table structure for wms_owner_location
-- ----------------------------
DROP TABLE IF EXISTS `wms_owner_location`;
CREATE TABLE `wms_owner_location`
(
    `id`           bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `owner_id`     bigint                                                        NULL DEFAULT NULL COMMENT '货主id',
    `warehouse_id` bigint                                                        NULL DEFAULT NULL COMMENT '仓库id',
    `area_id`      bigint                                                        NULL DEFAULT NULL COMMENT '库区id',
    `location_id`  bigint                                                        NULL DEFAULT NULL COMMENT '库位id',
    `status`       int                                                           NULL DEFAULT NULL COMMENT '状态',
    `remark`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`  datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`  datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 41
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '货主-库位关联表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_owner_location
-- ----------------------------
INSERT INTO `wms_owner_location`
VALUES (21, 1, 1, 1, 1, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:16', '2024-02-20 15:20:16');
INSERT INTO `wms_owner_location`
VALUES (22, 1, 1, 1, 2, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:16', '2024-02-20 15:20:16');
INSERT INTO `wms_owner_location`
VALUES (23, 1, 1, 1, 3, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:16', '2024-02-20 15:20:16');
INSERT INTO `wms_owner_location`
VALUES (24, 1, 1, 1, 4, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:16', '2024-02-20 15:20:16');
INSERT INTO `wms_owner_location`
VALUES (25, 1, 1, 1, 5, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:16', '2024-02-20 15:20:16');
INSERT INTO `wms_owner_location`
VALUES (26, 1, 1, 2, 6, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:16', '2024-02-20 15:20:16');
INSERT INTO `wms_owner_location`
VALUES (27, 1, 1, 2, 7, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:16', '2024-02-20 15:20:16');
INSERT INTO `wms_owner_location`
VALUES (28, 1, 1, 2, 8, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:16', '2024-02-20 15:20:16');
INSERT INTO `wms_owner_location`
VALUES (29, 1, 1, 2, 9, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:16', '2024-02-20 15:20:16');
INSERT INTO `wms_owner_location`
VALUES (30, 1, 1, 3, 10, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:16', '2024-02-20 15:20:16');
INSERT INTO `wms_owner_location`
VALUES (31, 1, 1, 3, 11, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:16', '2024-02-20 15:20:16');
INSERT INTO `wms_owner_location`
VALUES (32, 1, 1, 3, 12, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:16', '2024-02-20 15:20:16');
INSERT INTO `wms_owner_location`
VALUES (33, 1, 1, 4, 13, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:16', '2024-02-20 15:20:16');
INSERT INTO `wms_owner_location`
VALUES (34, 1, 1, 4, 14, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:16', '2024-02-20 15:20:16');
INSERT INTO `wms_owner_location`
VALUES (35, 1, 1, 5, 15, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:16', '2024-02-20 15:20:16');
INSERT INTO `wms_owner_location`
VALUES (36, 2, 2, 6, 16, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:54', '2024-02-20 15:20:54');
INSERT INTO `wms_owner_location`
VALUES (37, 2, 2, 6, 17, NULL, NULL, 'admin', 'admin', '2024-02-20 15:20:54', '2024-02-20 15:20:54');
INSERT INTO `wms_owner_location`
VALUES (38, 3, 3, 7, 18, NULL, NULL, 'admin', 'admin', '2024-02-20 15:21:17', '2024-02-20 15:21:17');
INSERT INTO `wms_owner_location`
VALUES (39, 3, 4, 8, 19, NULL, NULL, 'admin', 'admin', '2024-02-20 15:22:05', '2024-02-20 15:22:05');
INSERT INTO `wms_owner_location`
VALUES (40, 3, 4, 8, 20, NULL, NULL, 'admin', 'admin', '2024-02-20 15:22:05', '2024-02-20 15:22:05');

-- ----------------------------
-- Table structure for wms_owner_strategy
-- ----------------------------
DROP TABLE IF EXISTS `wms_owner_strategy`;
CREATE TABLE `wms_owner_strategy`
(
    `id`                 bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `owner_id`           bigint                                                        NULL DEFAULT NULL COMMENT '货主id',
    `warehouse_id`       bigint                                                        NULL DEFAULT NULL COMMENT '仓库id',
    `area_id`            bigint                                                        NULL DEFAULT NULL COMMENT '库区id',
    `launch_strategy`    bigint                                                        NULL DEFAULT NULL COMMENT '上架策略',
    `picking_strategy`   bigint                                                        NULL DEFAULT NULL COMMENT '拣货策略',
    `inventory_strategy` bigint                                                        NULL DEFAULT NULL COMMENT '盘点策略',
    `status`             int                                                           NULL DEFAULT NULL COMMENT '状态',
    `remark`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`        datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`        datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '货主-策略关联表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_owner_strategy
-- ----------------------------

-- ----------------------------
-- Table structure for wms_picking
-- ----------------------------
DROP TABLE IF EXISTS `wms_picking`;
CREATE TABLE `wms_picking`
(
    `id`              bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `master_id`       bigint                                                        NULL DEFAULT NULL COMMENT '货主id',
    `code`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '拣货任务单号',
    `outbound_code`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '出库单号',
    `owner_id`        bigint                                                        NULL DEFAULT NULL COMMENT '货主id',
    `warehouse_id`    bigint                                                        NULL DEFAULT NULL COMMENT '仓库id',
    `area_id`         bigint                                                        NULL DEFAULT NULL COMMENT '库区id',
    `picking_num`     int                                                           NULL DEFAULT NULL COMMENT '拣货数量',
    `person_name`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '负责人姓名',
    `real_num`        int                                                           NULL DEFAULT NULL COMMENT '实拣数量',
    `difference_num`  int                                                           NULL DEFAULT NULL COMMENT '数量差异',
    `completion_time` datetime                                                      NULL DEFAULT NULL COMMENT '完成时间',
    `status`          int                                                           NULL DEFAULT NULL COMMENT '拣货状态  1待分配、2拣货中、3拣货完成',
    `remark`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`     datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '拣货任务表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_picking
-- ----------------------------
INSERT INTO `wms_picking`
VALUES (1, 1, 'JHRW10004', 'HP10019', 1, 1, 3, 300, '张文礼', 300, 0, '2024-02-21 17:18:15', 3, 'pp', 'admin', 'admin',
        '2024-02-21 17:17:51', '2024-02-21 17:18:15');
INSERT INTO `wms_picking`
VALUES (2, 2, 'JHRW10005', 'HP10021', 1, 1, 3, 10, '张文礼', 10, 0, '2024-02-22 09:52:40', 3, '', 'admin', 'admin',
        '2024-02-22 09:50:48', '2024-02-22 09:52:40');

-- ----------------------------
-- Table structure for wms_receipt
-- ----------------------------
DROP TABLE IF EXISTS `wms_receipt`;
CREATE TABLE `wms_receipt`
(
    `id`                bigint                                                        NOT NULL AUTO_INCREMENT,
    `code`              varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL DEFAULT NULL COMMENT '入库单号',
    `bill_code`         varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL DEFAULT NULL COMMENT '运单编号',
    `plan_arrival_time` datetime                                                      NULL DEFAULT NULL COMMENT '计划到达时间',
    `owner_id`          bigint                                                        NULL DEFAULT NULL COMMENT '货主id',
    `warehouse_id`      bigint                                                        NULL DEFAULT NULL COMMENT '仓库id',
    `area_id`           bigint                                                        NULL DEFAULT NULL COMMENT '库区id',
    `license`           varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL DEFAULT NULL COMMENT '车牌号',
    `shipper_name`      varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL DEFAULT NULL COMMENT '发货人姓名',
    `delivery_name`     varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL DEFAULT NULL COMMENT '送货人姓名',
    `delivery_phone`    varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL DEFAULT NULL COMMENT '送货人电话',
    `plan_num`          int                                                           NULL DEFAULT NULL COMMENT '预计到货数',
    `status`            int                                                           NULL DEFAULT NULL COMMENT '入库单状态 1新建、2收货中、3已取消、4收货完成、5上架中、6上架完成',
    `remark`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`       varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`       varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`       datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci COMMENT = '入库单'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_receipt
-- ----------------------------
INSERT INTO `wms_receipt`
VALUES (1, 'RK10028', 'SF202312123456', '2024-02-21 17:08:35', 1, 1, NULL, 'e', 'zx', 'xx', '19900000000', 600, 6, 'e',
        'admin', 'admin', '2024-02-21 17:09:09', '2024-02-21 17:12:28');
INSERT INTO `wms_receipt`
VALUES (2, 'RK10029', 'SF202312123423', '2024-02-21 17:19:26', 1, 1, NULL, 'ewrewr', 'sds', 'fdsf', '18800000000', 150,
        6, 'erewr', 'admin', 'admin', '2024-02-21 17:19:43', '2024-02-21 17:21:37');
INSERT INTO `wms_receipt`
VALUES (3, 'RK10031', 'ere', '2024-02-22 09:32:19', 1, 1, NULL, 'retert', 'weee', 'wewewe', '19900000000', 10, 6,
        'retret', 'admin', 'admin', '2024-02-22 09:32:37', '2024-02-22 09:41:56');
INSERT INTO `wms_receipt`
VALUES (4, 'RK10032', 'sa\'d\'s', '2024-02-22 09:48:52', 1, 1, NULL, 'reterte', 'ds', 'sdsd', '19900000000', NULL, 1,
        'rterte', 'admin', 'admin', '2024-02-22 09:49:03', '2024-02-22 09:50:00');
INSERT INTO `wms_receipt`
VALUES (5, 'RK10033', 'rtrt', '2024-02-22 10:06:16', 1, 1, NULL, '89879', 'we', 'rer', '19900000000', 10, 2, '87978',
        'admin', 'admin', '2024-02-22 10:06:28', '2024-02-22 10:08:24');

-- ----------------------------
-- Table structure for wms_receipt_list
-- ----------------------------
DROP TABLE IF EXISTS `wms_receipt_list`;
CREATE TABLE `wms_receipt_list`
(
    `id`                         bigint                                                        NOT NULL AUTO_INCREMENT COMMENT 'id',
    `master_id`                  bigint                                                        NULL DEFAULT NULL COMMENT '主记录id',
    `goods_id`                   bigint                                                        NULL DEFAULT NULL COMMENT '货品id',
    `owner_id`                   bigint                                                        NULL DEFAULT NULL COMMENT '货主id',
    `warehouse_id`               bigint                                                        NULL DEFAULT NULL COMMENT '仓库id',
    `recommend_area_id`          bigint                                                        NULL DEFAULT NULL COMMENT '推荐库区id',
    `recommend_location_plan_id` bigint                                                        NULL DEFAULT NULL COMMENT '推荐库位id',
    `area_id`                    bigint                                                        NULL DEFAULT NULL COMMENT '库区id',
    `location_plan_id`           bigint                                                        NULL DEFAULT NULL COMMENT '库位方案id',
    `plan_num`                   int                                                           NULL DEFAULT NULL COMMENT '预计到货数',
    `real_num`                   int                                                           NULL DEFAULT NULL COMMENT '实收货数',
    `real_difference_num`        int                                                           NULL DEFAULT NULL COMMENT '实收数量差异',
    `grounding_num`              int                                                           NULL DEFAULT NULL COMMENT '上架数量',
    `grounding_difference_num`   int                                                           NULL DEFAULT NULL COMMENT '上架数量差异',
    `status`                     int                                                           NULL DEFAULT NULL COMMENT '状态',
    `remark`                     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`                datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`                datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '入库清单'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_receipt_list
-- ----------------------------
INSERT INTO `wms_receipt_list`
VALUES (1, 1, 1, 1, 1, NULL, 1760230495393804290, NULL, 1760230572514471938, 200, 200, 0, 200, 0, NULL, NULL, 'admin',
        'admin', '2024-02-21 17:09:15', '2024-02-21 17:10:47');
INSERT INTO `wms_receipt_list`
VALUES (2, 1, 2, 1, 1, NULL, 1760230495456718850, NULL, 1760230607838900225, 200, 200, 0, 200, 0, NULL, NULL, 'admin',
        'admin', '2024-02-21 17:09:15', '2024-02-21 17:10:55');
INSERT INTO `wms_receipt_list`
VALUES (3, 1, 3, 1, 1, NULL, 1760230495456718851, NULL, 1760230651245752321, 200, 200, 0, 200, 0, NULL, NULL, 'admin',
        'admin', '2024-02-21 17:09:15', '2024-02-21 17:11:05');
INSERT INTO `wms_receipt_list`
VALUES (4, 2, 1, 1, 1, NULL, 1760232961963323394, NULL, 1760233029596475393, 150, 150, 0, 150, 0, NULL, NULL, 'admin',
        'admin', '2024-02-21 17:19:50', '2024-02-21 17:21:11');
INSERT INTO `wms_receipt_list`
VALUES (5, 3, 1, 1, 1, NULL, 1760479591245369345, NULL, 1760479979671474178, 10, 10, 0, 10, 0, NULL, NULL, 'admin',
        'admin', '2024-02-22 09:33:11', '2024-02-22 09:41:50');
INSERT INTO `wms_receipt_list`
VALUES (6, 5, 1, 1, 1, NULL, NULL, NULL, NULL, 10, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', 'admin',
        '2024-02-22 10:06:33', '2024-02-22 10:06:41');

-- ----------------------------
-- Table structure for wms_receiving
-- ----------------------------
DROP TABLE IF EXISTS `wms_receiving`;
CREATE TABLE `wms_receiving`
(
    `id`              bigint                                                        NOT NULL AUTO_INCREMENT,
    `master_id`       bigint                                                        NULL DEFAULT NULL COMMENT '主记录id',
    `code`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '收货任务单号',
    `receipt_code`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '入库单号',
    `owner_id`        bigint                                                        NULL DEFAULT NULL COMMENT '货主id',
    `warehouse_id`    bigint                                                        NULL DEFAULT NULL COMMENT '仓库id',
    `area_id`         bigint                                                        NULL DEFAULT NULL COMMENT '库区id',
    `plan_num`        int                                                           NULL DEFAULT NULL COMMENT '预计到货数',
    `receiver_name`   varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '收货人姓名',
    `real_num`        int                                                           NULL DEFAULT NULL COMMENT '实收货数',
    `difference_num`  int                                                           NULL DEFAULT NULL COMMENT '数量差异',
    `completion_time` datetime                                                      NULL DEFAULT NULL COMMENT '完成时间',
    `status`          int                                                           NULL DEFAULT NULL COMMENT '收货状态 1待分配、2收货中、3收货完成、4已取消',
    `remark`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`     datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '收货任务表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_receiving
-- ----------------------------
INSERT INTO `wms_receiving`
VALUES (1, 1, 'SHRW10010', 'RK10028', 1, 1, NULL, 600, '张文礼', 600, 0, '2024-02-21 17:10:28', 3, 'e', 'admin', 'admin',
        '2024-02-21 17:10:08', '2024-02-21 17:10:28');
INSERT INTO `wms_receiving`
VALUES (2, 2, 'SHRW10011', 'RK10029', 1, 1, NULL, 150, '张文礼', 150, 0, '2024-02-21 17:20:16', 3, 'erewr', 'admin',
        'admin', '2024-02-21 17:20:02', '2024-02-21 17:20:16');
INSERT INTO `wms_receiving`
VALUES (3, 3, 'SHRW10012', 'RK10031', 1, 1, NULL, 10, '张文礼', 10, 0, '2024-02-22 09:40:17', 3, 'retret', 'admin',
        'admin', '2024-02-22 09:38:25', '2024-02-22 09:40:17');
INSERT INTO `wms_receiving`
VALUES (4, 5, 'SHRW10013', 'RK10033', 1, 1, NULL, 10, NULL, NULL, NULL, NULL, 1, '87978', 'admin', 'admin',
        '2024-02-22 10:08:24', '2024-02-22 10:08:24');

-- ----------------------------
-- Table structure for wms_status_record
-- ----------------------------
DROP TABLE IF EXISTS `wms_status_record`;
CREATE TABLE `wms_status_record`
(
    `id`                    bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `business_id`           bigint                                                        NULL DEFAULT NULL COMMENT '业务id',
    `business_type`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '业务类型',
    `business_model`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '业务模型',
    `business_status`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '业务状态',
    `business_status_label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '业务状态标签',
    `happen_time`           datetime                                                      NULL DEFAULT NULL COMMENT '发生时间',
    `status`                int                                                           NULL DEFAULT NULL COMMENT '状态',
    `remark`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`           datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`           datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 46
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '状态记录表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_status_record
-- ----------------------------
INSERT INTO `wms_status_record`
VALUES (1, 1, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '1', NULL, '2024-02-21 17:09:09', NULL,
        NULL, 'admin', 'admin', '2024-02-21 17:09:09', '2024-02-21 17:09:09');
INSERT INTO `wms_status_record`
VALUES (2, 1, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '2', NULL, '2024-02-21 17:10:08', NULL,
        NULL, 'admin', 'admin', '2024-02-21 17:10:08', '2024-02-21 17:10:08');
INSERT INTO `wms_status_record`
VALUES (3, 1, 'ReceivingEntity', 'com.itheima.wms.model.entity.biz.Receiving', '1', NULL, '2024-02-21 17:10:08',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:10:08', '2024-02-21 17:10:08');
INSERT INTO `wms_status_record`
VALUES (4, 1, 'ReceivingEntity', 'com.itheima.wms.model.entity.biz.Receiving', '2', NULL, '2024-02-21 17:10:14',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:10:14', '2024-02-21 17:10:14');
INSERT INTO `wms_status_record`
VALUES (5, 1, 'ReceivingEntity', 'com.itheima.wms.model.entity.biz.Receiving', '3', NULL, '2024-02-21 17:10:28',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:10:28', '2024-02-21 17:10:28');
INSERT INTO `wms_status_record`
VALUES (6, 1, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '4', NULL, '2024-02-21 17:10:28', NULL,
        NULL, 'admin', 'admin', '2024-02-21 17:10:28', '2024-02-21 17:10:28');
INSERT INTO `wms_status_record`
VALUES (7, 1, 'GroundingEntity', 'com.itheima.wms.model.entity.biz.Grounding', '1', NULL, '2024-02-21 17:10:28',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:10:28', '2024-02-21 17:10:28');
INSERT INTO `wms_status_record`
VALUES (8, 1, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '5', NULL, '2024-02-21 17:10:28', NULL,
        NULL, 'admin', 'admin', '2024-02-21 17:10:28', '2024-02-21 17:10:28');
INSERT INTO `wms_status_record`
VALUES (9, 1, 'GroundingEntity', 'com.itheima.wms.model.entity.biz.Grounding', '2', NULL, '2024-02-21 17:10:33',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:10:33', '2024-02-21 17:10:33');
INSERT INTO `wms_status_record`
VALUES (10, 1, 'GroundingEntity', 'com.itheima.wms.model.entity.biz.Grounding', '3', NULL, '2024-02-21 17:12:28',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:12:28', '2024-02-21 17:12:28');
INSERT INTO `wms_status_record`
VALUES (11, 1, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '6', NULL, '2024-02-21 17:12:28',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:12:28', '2024-02-21 17:12:28');
INSERT INTO `wms_status_record`
VALUES (12, 1, 'OutboundEntity', 'com.itheima.wms.model.entity.biz.Outbound', '1', NULL, '2024-02-21 17:17:14',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:17:14', '2024-02-21 17:17:14');
INSERT INTO `wms_status_record`
VALUES (13, 1, 'OutboundEntity', 'com.itheima.wms.model.entity.biz.Outbound', '2', NULL, '2024-02-21 17:17:51',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:17:51', '2024-02-21 17:17:51');
INSERT INTO `wms_status_record`
VALUES (14, 1, 'PickingEntity', 'com.itheima.wms.model.entity.biz.Picking', '1', NULL, '2024-02-21 17:17:51',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:17:51', '2024-02-21 17:17:51');
INSERT INTO `wms_status_record`
VALUES (15, 1, 'PickingEntity', 'com.itheima.wms.model.entity.biz.Picking', '2', NULL, '2024-02-21 17:17:58',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:17:58', '2024-02-21 17:17:58');
INSERT INTO `wms_status_record`
VALUES (16, 1, 'PickingEntity', 'com.itheima.wms.model.entity.biz.Picking', '3', NULL, '2024-02-21 17:18:15',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:18:15', '2024-02-21 17:18:15');
INSERT INTO `wms_status_record`
VALUES (17, 1, 'OutboundEntity', 'com.itheima.wms.model.entity.biz.Outbound', '4', NULL, '2024-02-21 17:18:15',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:18:15', '2024-02-21 17:18:15');
INSERT INTO `wms_status_record`
VALUES (18, 1, 'HandoverEntity', 'com.itheima.wms.model.entity.biz.Handover', '1', NULL, '2024-02-21 17:18:15',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:18:15', '2024-02-21 17:18:15');
INSERT INTO `wms_status_record`
VALUES (19, 1, 'OutboundEntity', 'com.itheima.wms.model.entity.biz.Outbound', '5', NULL, '2024-02-21 17:18:15',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:18:15', '2024-02-21 17:18:15');
INSERT INTO `wms_status_record`
VALUES (20, 1, 'HandoverEntity', 'com.itheima.wms.model.entity.biz.Handover', '2', NULL, '2024-02-21 17:18:21',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:18:21', '2024-02-21 17:18:21');
INSERT INTO `wms_status_record`
VALUES (21, 1, 'HandoverEntity', 'com.itheima.wms.model.entity.biz.Handover', '3', NULL, '2024-02-21 17:18:26',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:18:26', '2024-02-21 17:18:26');
INSERT INTO `wms_status_record`
VALUES (22, 1, 'OutboundEntity', 'com.itheima.wms.model.entity.biz.Outbound', '6', NULL, '2024-02-21 17:18:26',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:18:26', '2024-02-21 17:18:26');
INSERT INTO `wms_status_record`
VALUES (23, 2, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '1', NULL, '2024-02-21 17:19:43',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:19:43', '2024-02-21 17:19:43');
INSERT INTO `wms_status_record`
VALUES (24, 2, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '2', NULL, '2024-02-21 17:20:02',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:20:02', '2024-02-21 17:20:02');
INSERT INTO `wms_status_record`
VALUES (25, 2, 'ReceivingEntity', 'com.itheima.wms.model.entity.biz.Receiving', '1', NULL, '2024-02-21 17:20:02',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:20:02', '2024-02-21 17:20:02');
INSERT INTO `wms_status_record`
VALUES (26, 2, 'ReceivingEntity', 'com.itheima.wms.model.entity.biz.Receiving', '2', NULL, '2024-02-21 17:20:06',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:20:06', '2024-02-21 17:20:06');
INSERT INTO `wms_status_record`
VALUES (27, 2, 'ReceivingEntity', 'com.itheima.wms.model.entity.biz.Receiving', '3', NULL, '2024-02-21 17:20:16',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:20:16', '2024-02-21 17:20:16');
INSERT INTO `wms_status_record`
VALUES (28, 2, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '4', NULL, '2024-02-21 17:20:16',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:20:16', '2024-02-21 17:20:16');
INSERT INTO `wms_status_record`
VALUES (29, 2, 'GroundingEntity', 'com.itheima.wms.model.entity.biz.Grounding', '1', NULL, '2024-02-21 17:20:16',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:20:16', '2024-02-21 17:20:16');
INSERT INTO `wms_status_record`
VALUES (30, 2, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '5', NULL, '2024-02-21 17:20:16',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:20:16', '2024-02-21 17:20:16');
INSERT INTO `wms_status_record`
VALUES (31, 2, 'GroundingEntity', 'com.itheima.wms.model.entity.biz.Grounding', '2', NULL, '2024-02-21 17:20:21',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:20:21', '2024-02-21 17:20:21');
INSERT INTO `wms_status_record`
VALUES (32, 2, 'GroundingEntity', 'com.itheima.wms.model.entity.biz.Grounding', '3', NULL, '2024-02-21 17:21:37',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:21:37', '2024-02-21 17:21:37');
INSERT INTO `wms_status_record`
VALUES (33, 2, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '6', NULL, '2024-02-21 17:21:37',
        NULL, NULL, 'admin', 'admin', '2024-02-21 17:21:37', '2024-02-21 17:21:37');
INSERT INTO `wms_status_record`
VALUES (34, 3, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '1', NULL, '2024-02-22 09:32:37',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:32:37', '2024-02-22 09:32:37');
INSERT INTO `wms_status_record`
VALUES (35, 3, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '2', NULL, '2024-02-22 09:38:25',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:38:25', '2024-02-22 09:38:25');
INSERT INTO `wms_status_record`
VALUES (36, 3, 'ReceivingEntity', 'com.itheima.wms.model.entity.biz.Receiving', '1', NULL, '2024-02-22 09:38:25',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:38:25', '2024-02-22 09:38:25');
INSERT INTO `wms_status_record`
VALUES (37, 3, 'ReceivingEntity', 'com.itheima.wms.model.entity.biz.Receiving', '2', NULL, '2024-02-22 09:39:17',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:39:17', '2024-02-22 09:39:17');
INSERT INTO `wms_status_record`
VALUES (38, 3, 'ReceivingEntity', 'com.itheima.wms.model.entity.biz.Receiving', '3', NULL, '2024-02-22 09:40:17',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:40:17', '2024-02-22 09:40:17');
INSERT INTO `wms_status_record`
VALUES (39, 3, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '4', NULL, '2024-02-22 09:40:17',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:40:17', '2024-02-22 09:40:17');
INSERT INTO `wms_status_record`
VALUES (40, 3, 'GroundingEntity', 'com.itheima.wms.model.entity.biz.Grounding', '1', NULL, '2024-02-22 09:40:17',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:40:17', '2024-02-22 09:40:17');
INSERT INTO `wms_status_record`
VALUES (41, 3, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '5', NULL, '2024-02-22 09:40:17',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:40:17', '2024-02-22 09:40:17');
INSERT INTO `wms_status_record`
VALUES (42, 3, 'GroundingEntity', 'com.itheima.wms.model.entity.biz.Grounding', '2', NULL, '2024-02-22 09:40:54',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:40:54', '2024-02-22 09:40:54');
INSERT INTO `wms_status_record`
VALUES (43, 3, 'GroundingEntity', 'com.itheima.wms.model.entity.biz.Grounding', '3', NULL, '2024-02-22 09:41:56',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:41:56', '2024-02-22 09:41:56');
INSERT INTO `wms_status_record`
VALUES (44, 3, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '6', NULL, '2024-02-22 09:41:56',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:41:56', '2024-02-22 09:41:56');
INSERT INTO `wms_status_record`
VALUES (45, 2, 'OutboundEntity', 'com.itheima.wms.model.entity.biz.Outbound', '1', NULL, '2024-02-22 09:46:28',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:46:28', '2024-02-22 09:46:28');
INSERT INTO `wms_status_record`
VALUES (46, 4, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '1', NULL, '2024-02-22 09:49:03',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:49:03', '2024-02-22 09:49:03');
INSERT INTO `wms_status_record`
VALUES (47, 2, 'OutboundEntity', 'com.itheima.wms.model.entity.biz.Outbound', '2', NULL, '2024-02-22 09:50:48',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:50:48', '2024-02-22 09:50:48');
INSERT INTO `wms_status_record`
VALUES (48, 2, 'PickingEntity', 'com.itheima.wms.model.entity.biz.Picking', '1', NULL, '2024-02-22 09:50:48',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:50:48', '2024-02-22 09:50:48');
INSERT INTO `wms_status_record`
VALUES (49, 2, 'PickingEntity', 'com.itheima.wms.model.entity.biz.Picking', '2', NULL, '2024-02-22 09:51:44',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:51:44', '2024-02-22 09:51:44');
INSERT INTO `wms_status_record`
VALUES (50, 2, 'PickingEntity', 'com.itheima.wms.model.entity.biz.Picking', '3', NULL, '2024-02-22 09:52:40',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:52:40', '2024-02-22 09:52:40');
INSERT INTO `wms_status_record`
VALUES (51, 2, 'OutboundEntity', 'com.itheima.wms.model.entity.biz.Outbound', '4', NULL, '2024-02-22 09:52:40',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:52:40', '2024-02-22 09:52:40');
INSERT INTO `wms_status_record`
VALUES (52, 2, 'HandoverEntity', 'com.itheima.wms.model.entity.biz.Handover', '1', NULL, '2024-02-22 09:52:40',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:52:40', '2024-02-22 09:52:40');
INSERT INTO `wms_status_record`
VALUES (53, 2, 'OutboundEntity', 'com.itheima.wms.model.entity.biz.Outbound', '5', NULL, '2024-02-22 09:52:40',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:52:40', '2024-02-22 09:52:40');
INSERT INTO `wms_status_record`
VALUES (54, 2, 'HandoverEntity', 'com.itheima.wms.model.entity.biz.Handover', '2', NULL, '2024-02-22 09:52:57',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:52:57', '2024-02-22 09:52:57');
INSERT INTO `wms_status_record`
VALUES (55, 2, 'HandoverEntity', 'com.itheima.wms.model.entity.biz.Handover', '3', NULL, '2024-02-22 09:53:05',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:53:05', '2024-02-22 09:53:05');
INSERT INTO `wms_status_record`
VALUES (56, 2, 'OutboundEntity', 'com.itheima.wms.model.entity.biz.Outbound', '6', NULL, '2024-02-22 09:53:05',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:53:05', '2024-02-22 09:53:05');
INSERT INTO `wms_status_record`
VALUES (57, 1, 'CheckEntity', 'com.itheima.wms.model.entity.biz.Check', '1', NULL, '2024-02-22 09:56:35', NULL,
        NULL, 'admin', 'admin', '2024-02-22 09:56:35', '2024-02-22 09:56:35');
INSERT INTO `wms_status_record`
VALUES (58, 1, 'CheckEntity', 'com.itheima.wms.model.entity.biz.Check', '2', NULL, '2024-02-22 09:57:28', NULL,
        NULL, 'admin', 'admin', '2024-02-22 09:57:28', '2024-02-22 09:57:28');
INSERT INTO `wms_status_record`
VALUES (59, 1, 'CheckTaskEntity', 'com.itheima.wms.model.entity.biz.CheckTask', '1', NULL, '2024-02-22 09:57:28',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:57:28', '2024-02-22 09:57:28');
INSERT INTO `wms_status_record`
VALUES (60, 1, 'CheckTaskEntity', 'com.itheima.wms.model.entity.biz.CheckTask', '2', NULL, '2024-02-22 09:57:53',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:57:53', '2024-02-22 09:57:53');
INSERT INTO `wms_status_record`
VALUES (61, 1, 'CheckTaskEntity', 'com.itheima.wms.model.entity.biz.CheckTask', '3', NULL, '2024-02-22 09:58:49',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:58:49', '2024-02-22 09:58:49');
INSERT INTO `wms_status_record`
VALUES (62, 1, 'CheckEntity', 'com.itheima.wms.model.entity.biz.Check', '3', NULL, '2024-02-22 09:58:49', NULL,
        NULL, 'admin', 'admin', '2024-02-22 09:58:49', '2024-02-22 09:58:49');
INSERT INTO `wms_status_record`
VALUES (63, 1, 'CheckTaskEntity', 'com.itheima.wms.model.entity.biz.CheckTask', '5', NULL, '2024-02-22 09:59:18',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:59:18', '2024-02-22 09:59:18');
INSERT INTO `wms_status_record`
VALUES (64, 1, 'CheckEntity', 'com.itheima.wms.model.entity.biz.Check', '4', NULL, '2024-02-22 09:59:18', NULL,
        NULL, 'admin', 'admin', '2024-02-22 09:59:18', '2024-02-22 09:59:18');
INSERT INTO `wms_status_record`
VALUES (65, 2, 'CheckTaskEntity', 'com.itheima.wms.model.entity.biz.CheckTask', '1', NULL, '2024-02-22 09:59:18',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:59:18', '2024-02-22 09:59:18');
INSERT INTO `wms_status_record`
VALUES (66, 2, 'CheckTaskEntity', 'com.itheima.wms.model.entity.biz.CheckTask', '2', NULL, '2024-02-22 09:59:41',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:59:41', '2024-02-22 09:59:41');
INSERT INTO `wms_status_record`
VALUES (67, 2, 'CheckTaskEntity', 'com.itheima.wms.model.entity.biz.CheckTask', '3', NULL, '2024-02-22 09:59:57',
        NULL, NULL, 'admin', 'admin', '2024-02-22 09:59:57', '2024-02-22 09:59:57');
INSERT INTO `wms_status_record`
VALUES (68, 1, 'CheckEntity', 'com.itheima.wms.model.entity.biz.Check', '5', NULL, '2024-02-22 09:59:57', NULL,
        NULL, 'admin', 'admin', '2024-02-22 09:59:57', '2024-02-22 09:59:57');
INSERT INTO `wms_status_record`
VALUES (69, 2, 'CheckEntity', 'com.itheima.wms.model.entity.biz.Check', '1', NULL, '2024-02-22 10:00:37', NULL,
        NULL, 'admin', 'admin', '2024-02-22 10:00:37', '2024-02-22 10:00:37');
INSERT INTO `wms_status_record`
VALUES (70, 2, 'CheckEntity', 'com.itheima.wms.model.entity.biz.Check', '2', NULL, '2024-02-22 10:00:46', NULL,
        NULL, 'admin', 'admin', '2024-02-22 10:00:46', '2024-02-22 10:00:46');
INSERT INTO `wms_status_record`
VALUES (71, 3, 'CheckTaskEntity', 'com.itheima.wms.model.entity.biz.CheckTask', '1', NULL, '2024-02-22 10:00:46',
        NULL, NULL, 'admin', 'admin', '2024-02-22 10:00:46', '2024-02-22 10:00:46');
INSERT INTO `wms_status_record`
VALUES (72, 3, 'CheckTaskEntity', 'com.itheima.wms.model.entity.biz.CheckTask', '2', NULL, '2024-02-22 10:00:50',
        NULL, NULL, 'admin', 'admin', '2024-02-22 10:00:50', '2024-02-22 10:00:50');
INSERT INTO `wms_status_record`
VALUES (73, 3, 'CheckTaskEntity', 'com.itheima.wms.model.entity.biz.CheckTask', '3', NULL, '2024-02-22 10:01:00',
        NULL, NULL, 'admin', 'admin', '2024-02-22 10:01:00', '2024-02-22 10:01:00');
INSERT INTO `wms_status_record`
VALUES (74, 2, 'CheckEntity', 'com.itheima.wms.model.entity.biz.Check', '3', NULL, '2024-02-22 10:01:00', NULL,
        NULL, 'admin', 'admin', '2024-02-22 10:01:00', '2024-02-22 10:01:00');
INSERT INTO `wms_status_record`
VALUES (75, 3, 'CheckTaskEntity', 'com.itheima.wms.model.entity.biz.CheckTask', '4', NULL, '2024-02-22 10:01:01',
        NULL, NULL, 'admin', 'admin', '2024-02-22 10:01:01', '2024-02-22 10:01:01');
INSERT INTO `wms_status_record`
VALUES (76, 5, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '1', NULL, '2024-02-22 10:06:28',
        NULL, NULL, 'admin', 'admin', '2024-02-22 10:06:28', '2024-02-22 10:06:28');
INSERT INTO `wms_status_record`
VALUES (77, 5, 'ReceiptEntity', 'com.itheima.wms.model.entity.biz.Receipt', '2', NULL, '2024-02-22 10:08:24',
        NULL, NULL, 'admin', 'admin', '2024-02-22 10:08:24', '2024-02-22 10:08:24');
INSERT INTO `wms_status_record`
VALUES (78, 4, 'ReceivingEntity', 'com.itheima.wms.model.entity.biz.Receiving', '1', NULL, '2024-02-22 10:08:24',
        NULL, NULL, 'admin', 'admin', '2024-02-22 10:08:24', '2024-02-22 10:08:24');

-- ----------------------------
-- Table structure for wms_stock
-- ----------------------------
DROP TABLE IF EXISTS `wms_stock`;
CREATE TABLE `wms_stock`
(
    `id`            bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `warehouse_id`  bigint                                                        NULL DEFAULT NULL COMMENT '仓库id',
    `area_id`       bigint                                                        NULL DEFAULT NULL COMMENT '库区id',
    `location_id`   bigint                                                        NULL DEFAULT NULL COMMENT '库位id',
    `owner_id`      bigint                                                        NULL DEFAULT NULL COMMENT '货主id',
    `goods_type_id` bigint                                                        NULL DEFAULT NULL COMMENT '货品类型id 若一直未使用则删除',
    `goods_id`      bigint                                                        NULL DEFAULT NULL COMMENT '货品id',
    `total`         int                                                           NULL DEFAULT NULL COMMENT '总库存',
    `free`          int                                                           NULL DEFAULT NULL COMMENT '可用库存',
    `frozen`        int                                                           NULL DEFAULT NULL COMMENT '冻结库存',
    `status`        int                                                           NULL DEFAULT NULL COMMENT '状态 0 未满 1 已满',
    `remark`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`   datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '库存(实时)表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_stock
-- ----------------------------
INSERT INTO `wms_stock`
VALUES (1, 1, 3, 10, 1, NULL, 1, 250, 250, 0, 0, NULL, 'admin', 'admin', '2024-02-21 17:10:47', '2024-02-22 09:59:57');
INSERT INTO `wms_stock`
VALUES (2, 1, 3, 11, 1, NULL, 2, 100, 100, 0, 0, NULL, 'admin', 'admin', '2024-02-21 17:10:55', '2024-02-22 10:01:00');
INSERT INTO `wms_stock`
VALUES (3, 1, 3, 12, 1, NULL, 3, 100, 100, 0, 0, NULL, 'admin', 'admin', '2024-02-21 17:11:05', '2024-02-21 17:18:15');

-- ----------------------------
-- Table structure for wms_stock_record
-- ----------------------------
DROP TABLE IF EXISTS `wms_stock_record`;
CREATE TABLE `wms_stock_record`
(
    `id`            bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `source_id`     bigint                                                        NULL DEFAULT NULL COMMENT '来源id',
    `location_id`   bigint                                                        NULL DEFAULT NULL COMMENT '库位id',
    `type`          varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '任务类型',
    `original`      int                                                           NULL DEFAULT NULL COMMENT '原始总库存',
    `original_free` int                                                           NULL DEFAULT NULL COMMENT '原始可用库存',
    `way`           varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '变动方式 + -',
    `alteration`    int                                                           NULL DEFAULT NULL COMMENT '操作库存',
    `result`        int                                                           NULL DEFAULT NULL COMMENT '操作后总库存',
    `result_free`   int                                                           NULL DEFAULT NULL COMMENT '操作后可用库存',
    `status`        int                                                           NULL DEFAULT NULL COMMENT '状态',
    `remark`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `create_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`   datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '库存记录表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_stock_record
-- ----------------------------
INSERT INTO `wms_stock_record`
VALUES (1, 1, 10, 'RK', 0, 0, 'frozen', 200, 200, 0, NULL, NULL, 'admin', 'admin', '2024-02-21 17:10:47',
        '2024-02-21 17:10:47');
INSERT INTO `wms_stock_record`
VALUES (2, 2, 11, 'RK', 0, 0, 'frozen', 200, 200, 0, NULL, NULL, 'admin', 'admin', '2024-02-21 17:10:55',
        '2024-02-21 17:10:55');
INSERT INTO `wms_stock_record`
VALUES (3, 3, 12, 'RK', 0, 0, 'frozen', 200, 200, 0, NULL, NULL, 'admin', 'admin', '2024-02-21 17:11:05',
        '2024-02-21 17:11:05');
INSERT INTO `wms_stock_record`
VALUES (4, 1, 10, 'RK', 200, 0, '+', 200, 200, 200, NULL, NULL, 'admin', 'admin', '2024-02-21 17:12:28',
        '2024-02-21 17:12:28');
INSERT INTO `wms_stock_record`
VALUES (5, 2, 11, 'RK', 200, 0, '+', 200, 200, 200, NULL, NULL, 'admin', 'admin', '2024-02-21 17:12:28',
        '2024-02-21 17:12:28');
INSERT INTO `wms_stock_record`
VALUES (6, 3, 12, 'RK', 200, 0, '+', 200, 200, 200, NULL, NULL, 'admin', 'admin', '2024-02-21 17:12:28',
        '2024-02-21 17:12:28');
INSERT INTO `wms_stock_record`
VALUES (7, 1, 10, 'CK', 200, 100, 'frozen', 100, 200, 0, NULL, NULL, 'admin', 'admin', '2024-02-21 17:17:43',
        '2024-02-21 17:18:06');
INSERT INTO `wms_stock_record`
VALUES (8, 2, 11, 'CK', 200, 100, 'frozen', 100, 200, 0, NULL, NULL, 'admin', 'admin', '2024-02-21 17:17:46',
        '2024-02-21 17:18:09');
INSERT INTO `wms_stock_record`
VALUES (9, 3, 12, 'CK', 200, 100, 'frozen', 100, 200, 0, NULL, NULL, 'admin', 'admin', '2024-02-21 17:17:48',
        '2024-02-21 17:18:12');
INSERT INTO `wms_stock_record`
VALUES (10, 1, 10, 'JH', 200, 100, '-', 100, 100, 100, NULL, NULL, 'admin', 'admin', '2024-02-21 17:18:15',
        '2024-02-21 17:18:15');
INSERT INTO `wms_stock_record`
VALUES (11, 2, 11, 'JH', 200, 100, '-', 100, 100, 100, NULL, NULL, 'admin', 'admin', '2024-02-21 17:18:15',
        '2024-02-21 17:18:15');
INSERT INTO `wms_stock_record`
VALUES (12, 3, 12, 'JH', 200, 100, '-', 100, 100, 100, NULL, NULL, 'admin', 'admin', '2024-02-21 17:18:15',
        '2024-02-21 17:18:15');
INSERT INTO `wms_stock_record`
VALUES (13, 4, 10, 'RK', 100, 100, 'frozen', 150, 250, 100, NULL, NULL, 'admin', 'admin', '2024-02-21 17:20:32',
        '2024-02-21 17:20:32');
INSERT INTO `wms_stock_record`
VALUES (14, 4, 10, 'RK', 250, 100, '+', 150, 250, 250, NULL, NULL, 'admin', 'admin', '2024-02-21 17:21:37',
        '2024-02-21 17:21:37');
INSERT INTO `wms_stock_record`
VALUES (15, 5, 10, 'RK', 250, 250, 'frozen', 10, 260, 250, NULL, NULL, 'admin', 'admin', '2024-02-22 09:41:50',
        '2024-02-22 09:41:50');
INSERT INTO `wms_stock_record`
VALUES (16, 5, 10, 'RK', 260, 250, '+', 10, 260, 260, NULL, NULL, 'admin', 'admin', '2024-02-22 09:41:56',
        '2024-02-22 09:41:56');
INSERT INTO `wms_stock_record`
VALUES (17, 4, 10, 'CK', 260, 250, 'frozen', 10, 260, 240, NULL, NULL, 'admin', 'admin', '2024-02-22 09:48:06',
        '2024-02-22 09:52:15');
INSERT INTO `wms_stock_record`
VALUES (18, 4, 10, 'JH', 260, 250, '-', 10, 250, 250, NULL, NULL, 'admin', 'admin', '2024-02-22 09:52:40',
        '2024-02-22 09:52:40');
INSERT INTO `wms_stock_record`
VALUES (19, 1, 10, 'PD', 250, 250, 'frozen', 250, 250, 0, NULL, NULL, 'admin', 'admin', '2024-02-22 09:56:56',
        '2024-02-22 09:56:56');
INSERT INTO `wms_stock_record`
VALUES (20, 1, 10, 'PD', 250, 0, 'unfreeze', 250, 250, 250, NULL, NULL, 'admin', 'admin', '2024-02-22 09:58:49',
        '2024-02-22 09:58:49');
INSERT INTO `wms_stock_record`
VALUES (21, 2, 10, 'PD', 250, 250, 'frozen', 250, 250, 0, NULL, NULL, 'admin', 'admin', '2024-02-22 09:59:18',
        '2024-02-22 09:59:18');
INSERT INTO `wms_stock_record`
VALUES (22, 2, 10, 'PD', 250, 0, 'unfreeze', 250, 250, 250, NULL, NULL, 'admin', 'admin', '2024-02-22 09:59:57',
        '2024-02-22 09:59:57');
INSERT INTO `wms_stock_record`
VALUES (23, 3, 11, 'PD', 100, 100, 'frozen', 100, 100, 0, NULL, NULL, 'admin', 'admin', '2024-02-22 10:00:40',
        '2024-02-22 10:00:40');
INSERT INTO `wms_stock_record`
VALUES (24, 3, 11, 'PD', 100, 0, 'unfreeze', 100, 100, 100, NULL, NULL, 'admin', 'admin', '2024-02-22 10:01:00',
        '2024-02-22 10:01:00');

-- ----------------------------
-- Table structure for wms_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `wms_warehouse`;
CREATE TABLE `wms_warehouse`
(
    `id`           bigint                                                 NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `code`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL COMMENT '编码',
    `name`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL COMMENT '名称',
    `type`         varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL COMMENT '类型 中转仓:ZZ、加工仓:JG、储备仓:CB、冷藏仓:LC',
    `location`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '省市区描述',
    `province`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL COMMENT '省',
    `city`         varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL COMMENT '市',
    `area`         varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL COMMENT '区',
    `address`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '详细地址',
    `surface`      decimal(10, 2)                                         NULL DEFAULT NULL COMMENT '面积',
    `person_id`    bigint                                                 NULL DEFAULT NULL COMMENT '负责人id',
    `person_name`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL COMMENT '负责人姓名',
    `phone`        varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL COMMENT '联系电话',
    `included_num` int                                                    NULL DEFAULT 0 COMMENT '库区数量',
    `status`       tinyint(1)                                             NULL DEFAULT 0 COMMENT '状态 0 停用 1启用',
    `remark`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '备注',
    `create_name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL COMMENT '创建人',
    `update_name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL COMMENT '更新人',
    `create_time`  datetime                                               NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`  datetime                                               NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `warehouse_code` (`code`) USING BTREE,
    UNIQUE INDEX `warehouse_name` (`name`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '仓库表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_warehouse
-- ----------------------------
INSERT INTO `wms_warehouse`
VALUES (1, 'CK000001', '1号仓库', 'ZZ', '天津市/市辖区/河东区', '120000', '129900', '120102', '太平洋银行写字楼1层', 10000.00, NULL, '万宝宝',
        '18032363332', 2, 1, '1号仓库', 'admin', 'admin', '2024-02-03 10:27:49', '2024-02-20 11:24:51');
INSERT INTO `wms_warehouse`
VALUES (2, 'CK000002', '2号仓库', 'JG', '河北省/秦皇岛市/北戴河区', '130000', '130300', '130304', '西湖大街1号院', 10000.00, NULL, '李欢欢',
        '18021253369', 2, 1, '2号仓库', 'admin', 'admin', '2024-02-03 10:27:49', '2024-02-03 10:27:49');
INSERT INTO `wms_warehouse`
VALUES (3, 'CK000003', '3号仓库', 'CB', '山西省/长治市/潞城区', '140000', '140400', '140406', '迎泽大街万福小区', 10000.00, NULL, '王丽丽',
        '18022114421', 3, 1, '3号仓库', 'admin', 'admin', '2024-02-03 10:27:49', '2024-02-19 15:53:30');
INSERT INTO `wms_warehouse`
VALUES (4, 'CK000004', '4号仓库', 'LC', '吉林省/四平市/梨树县', '220000', '220300', '220322', '金燕龙写字楼1层', 10000.00, NULL, '万宝宝',
        '18032363332', 3, 1, '4号仓库', 'admin', 'admin', '2024-02-03 10:27:49', '2024-02-03 10:27:49');

SET
    FOREIGN_KEY_CHECKS = 1;
