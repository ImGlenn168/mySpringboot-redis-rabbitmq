/*
 Navicat MySQL Data Transfer

 Source Server         : localHost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : ssm

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 27/03/2023 13:13:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_management
-- ----------------------------
DROP TABLE IF EXISTS `user_management`;
CREATE TABLE `user_management`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户电话',
  `dept` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户部门',
  `status` smallint(10) NULL DEFAULT NULL COMMENT '用户身份(1管理员/2员工)',
  `hire_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入职时间',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `state` smallint(10) NULL DEFAULT NULL COMMENT '状态(1使用中/0已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_management
-- ----------------------------
INSERT INTO `user_management` VALUES (1, 'admin', '123456', '88888888', '管理部', 1, '2023-03-21', '2023-03-21 11:01:32', '2023-03-21 11:01:36', -1);
INSERT INTO `user_management` VALUES (2, 'user01', '112233', '11111111', '生产部', 2, '2023-03-21', '2023-03-21 11:02:40', '2023-03-21 11:02:42', 1);
INSERT INTO `user_management` VALUES (3, 'user02', '112233', '11111111', '应用部', 2, '2023-03-21', '2023-03-21 11:08:56', '2023-03-21 11:08:56', 1);
INSERT INTO `user_management` VALUES (4, 'test01', '111', '111', '111', 1, '111', '2023-03-21 14:34:32', '2023-03-21 14:34:32', 1);
INSERT INTO `user_management` VALUES (5, 'test01', '111', '111', '111', 1, '111', '2023-03-21 14:39:10', '2023-03-21 14:39:10', 1);
INSERT INTO `user_management` VALUES (6, 'test01', '111', '111', '111', 1, '111', '2023-03-21 16:02:19', '2023-03-21 16:02:21', 1);
INSERT INTO `user_management` VALUES (7, 'aaaaa', '112233', '8888888888', '事业部', 2, '2023-03-21', '2023-03-21 16:01:53', '2023-03-21 16:01:56', 1);
INSERT INTO `user_management` VALUES (8, 'user03', '112233', '8888888888', '事业部', 2, '2023-03-21', '2023-03-21 15:48:00', '2023-03-21 15:48:00', -1);

SET FOREIGN_KEY_CHECKS = 1;
