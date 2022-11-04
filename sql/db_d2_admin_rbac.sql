/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : db_d2_admin_rbac

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 04/11/2022 23:46:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for am_admin
-- ----------------------------
DROP TABLE IF EXISTS `am_admin`;
CREATE TABLE `am_admin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `contact_telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `locked` tinyint(1) NULL DEFAULT 0 COMMENT '是否锁定',
  `data_status` int(11) NULL DEFAULT NULL COMMENT '数据状态（0锁定，1正常）',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间（时间戳）',
  `last_update_time` bigint(20) NULL DEFAULT NULL COMMENT '最后一次修改时间（时间戳）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_sys_admin_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1588160325000712194 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of am_admin
-- ----------------------------
INSERT INTO `am_admin` VALUES (1588160325000712193, 'Eric', '156758700756', 'linzhihao00@gmail.com', 'eric', '$2a$10$RvKmkvN9yyIUfckBxZPs5u5jkuDGFq5Y4f4HVvwb5HotNME/zQLAe', NULL, 0, 1, 1667481902731, 1667485777165);

-- ----------------------------
-- Table structure for am_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `am_admin_role`;
CREATE TABLE `am_admin_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色标识',
  `admin_id` bigint(20) NULL DEFAULT NULL COMMENT '后台用户标识',
  `data_status` tinyint(4) NULL DEFAULT NULL COMMENT '数据状态',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` bigint(20) NULL DEFAULT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of am_admin_role
-- ----------------------------
INSERT INTO `am_admin_role` VALUES (2, 1588090105438945281, 1588160325000712193, 1, 1667482838094, NULL);

-- ----------------------------
-- Table structure for am_permission
-- ----------------------------
DROP TABLE IF EXISTS `am_permission`;
CREATE TABLE `am_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `type` tinyint(1) NOT NULL COMMENT '资源类型(1.菜单 2.按钮或文本块)',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父编号',
  `parent_ids` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '父级',
  `permission` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限字符串',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由路径',
  `component_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件本地路径',
  `component_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件名称',
  `redirect_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转URL',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '是否有效',
  `config` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '权限配置',
  `data_status` int(11) NULL DEFAULT NULL COMMENT '数据状态（0锁定，1正常）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sys_admin_permission_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1588071719065665539 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of am_permission
-- ----------------------------
INSERT INTO `am_permission` VALUES (1580948537234432002, '权限管理', 1, 0, '0/', 'authorization', '/authorization', NULL, 'authorization', '/authorization/permission', 'bank', 1, 1, '{}', 1);
INSERT INTO `am_permission` VALUES (1580948959512764417, '菜单管理', 1, 1580948537234432002, '0/1580948537234432002/', 'authorization:permission', '/authorization/permission/index', 'authorization/permission/index', 'authorization-permission-index', NULL, 'sitemap', 1, 1, '{auth:true,cache:true}', 1);
INSERT INTO `am_permission` VALUES (1580955779446894593, '查询按钮', 0, 1580948959512764417, '0/1580948537234432002/1580948959512764417/', 'authorization:permission:select', NULL, NULL, NULL, NULL, NULL, 1, 1, '{}', 1);
INSERT INTO `am_permission` VALUES (1581572089876934657, '系统设置', 1, 0, '0/', 'setting', '/setting/index', NULL, NULL, NULL, 'gear', 1, 1, '{}', 1);
INSERT INTO `am_permission` VALUES (1588070022079627266, '角色管理', 1, 1580948537234432002, '0/1580948537234432002/', 'role', '/authorization/role/index', 'authorization/role/index', 'authorization-role-index', NULL, 'users', 1, 1, '{}', 1);
INSERT INTO `am_permission` VALUES (1588071719065665538, '后台用户管理', 1, 1580948537234432002, '0/1580948537234432002/', 'admin', '/authorization/admin/index', 'authorization/admin/index', 'authorization-admin-index', NULL, 'address-book', 1, 1, '{}', 1);

-- ----------------------------
-- Table structure for am_role
-- ----------------------------
DROP TABLE IF EXISTS `am_role`;
CREATE TABLE `am_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一标识',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态 1.正常 0.禁用',
  `data_status` int(11) NULL DEFAULT NULL COMMENT '数据状态（0锁定，1正常）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1588090105438945282 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of am_role
-- ----------------------------
INSERT INTO `am_role` VALUES (1588090105438945281, 'super-admin', '超级管理员', NULL, 1, 1);

-- ----------------------------
-- Table structure for am_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `am_role_permission`;
CREATE TABLE `am_role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色标识',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限标识',
  `data_status` tinyint(4) NULL DEFAULT NULL COMMENT '数据状态',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` bigint(20) NULL DEFAULT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of am_role_permission
-- ----------------------------
INSERT INTO `am_role_permission` VALUES (38, 1588090105438945281, 1580948537234432002, 1, 1667468876375, NULL);
INSERT INTO `am_role_permission` VALUES (39, 1588090105438945281, 1580948959512764417, 1, 1667468876375, NULL);
INSERT INTO `am_role_permission` VALUES (40, 1588090105438945281, 1588070022079627266, 1, 1667468876375, NULL);
INSERT INTO `am_role_permission` VALUES (41, 1588090105438945281, 1588071719065665538, 1, 1667468876375, NULL);
INSERT INTO `am_role_permission` VALUES (42, 1588090105438945281, 1580955779446894593, 1, 1667468876375, NULL);

SET FOREIGN_KEY_CHECKS = 1;
