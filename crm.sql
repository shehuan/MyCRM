/*
Navicat MySQL Data Transfer

Source Server         : crm
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-02-12 11:08:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sn` varchar(50) DEFAULT NULL COMMENT '部门编号',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `manager_id` bigint DEFAULT NULL COMMENT '部门经理id',
  `parent_id` bigint DEFAULT NULL COMMENT '上级部门id',
  `state` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', 'boss', '总经办', null, null, null);
INSERT INTO `department` VALUES ('2', 'it', '技术部', null, null, null);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '账号',
  `realname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓名',
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '电话',
  `admin` tinyint(1) DEFAULT NULL COMMENT '是否是超级管理员，1：是，0：否',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态，0：离职，1：正常',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `department_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_department` (`department_id`),
  CONSTRAINT `fk_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'admin', 'zhangsan', '123456', 'xyz@163.com', '18710840955', '1', '1', '2020-02-08 21:37:49', '2020-02-11 16:23:44', '1');
INSERT INTO `employee` VALUES ('5', 'abc', 'wangwu', '123456', '123@163.com', '110', '0', '0', '2020-02-09 00:00:00', '2020-02-09 23:37:44', '2');
INSERT INTO `employee` VALUES ('6', 'nba', 'zhaoliu', '123456', 'nba@163.com', '119', '0', '1', '2020-02-06 00:00:00', '2020-02-10 14:12:47', '2');
INSERT INTO `employee` VALUES ('7', 'kfc', '肯德基', '123456', 'kfc@163.com', '112', '0', '1', '2020-02-10 00:00:00', '2020-02-11 16:24:53', '2');
INSERT INTO `employee` VALUES ('8', 'xiaomi', '小米', '123456', 'mi@163.com', '114', '0', '1', '2020-02-11 00:00:00', '2020-02-11 16:23:24', '2');

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `employee_id` bigint DEFAULT NULL COMMENT '员工id',
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `fk_employee` (`employee_id`),
  KEY `fk_role` (`role_id`),
  CONSTRAINT `fk_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of employee_role
-- ----------------------------
INSERT INTO `employee_role` VALUES ('7', '8', '7');
INSERT INTO `employee_role` VALUES ('8', '1', '3');
INSERT INTO `employee_role` VALUES ('9', '7', '5');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户ip',
  `time` datetime DEFAULT NULL COMMENT '操作时间',
  `function_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '执行的方法',
  `params_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '方法的参数',
  PRIMARY KEY (`id`),
  KEY `fk_user` (`user_id`),
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `text` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单名称',
  `icon_cls` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单图标',
  `checked` tinyint(1) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `attributes` varchar(100) DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `resource` varchar(100) DEFAULT NULL COMMENT '菜单的权限路径',
  PRIMARY KEY (`id`),
  KEY `fk_parent` (`parent_id`),
  CONSTRAINT `fk_parent` FOREIGN KEY (`parent_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '系统管理', null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('2', '员工管理', null, null, null, '{ \"url\": \"/employee/page\"}', '1', null);
INSERT INTO `menu` VALUES ('3', '角色管理', null, null, null, '{ \"url\": \"/role/page\"}', '1', null);
INSERT INTO `menu` VALUES ('4', '营销管理', null, null, null, null, null, 'menu:marketing');
INSERT INTO `menu` VALUES ('5', '潜在客户管理', null, null, null, null, '4', null);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '权限名',
  `resource` varchar(100) DEFAULT NULL COMMENT '权限资源路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '员工新增', 'com.shh.crm.controller.EmployeeController:save');
INSERT INTO `permission` VALUES ('2', '员工修改', 'com.shh.crm.controller.EmployeeController:edit');
INSERT INTO `permission` VALUES ('3', '员工删除', 'com.shh.crm.controller.EmployeeController:delete');
INSERT INTO `permission` VALUES ('4', '员工查看', 'com.shh.crm.controller.EmployeeController:list');
INSERT INTO `permission` VALUES ('5', '员工所有操作权限', 'com.shh.crm.controller.EmployeeController:all');
INSERT INTO `permission` VALUES ('6', '营销管理菜单', 'menu:marketing');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sn` varchar(50) DEFAULT NULL COMMENT '角色编号',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('3', 'admin', '管理员');
INSERT INTO `role` VALUES ('5', 'HR', '人事专员');
INSERT INTO `role` VALUES ('7', 'develope', '开发');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `fk_role_id` (`role_id`),
  KEY `fk_permission_id` (`permission_id`),
  CONSTRAINT `fk_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('19', '5', '1');
INSERT INTO `role_permission` VALUES ('20', '5', '2');
INSERT INTO `role_permission` VALUES ('21', '5', '3');
INSERT INTO `role_permission` VALUES ('22', '5', '4');
INSERT INTO `role_permission` VALUES ('23', '7', '4');
INSERT INTO `role_permission` VALUES ('25', '3', '5');
INSERT INTO `role_permission` VALUES ('26', '3', '6');
