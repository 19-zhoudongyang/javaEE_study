/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : mybatis

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 14/09/2022 13:01:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp`  (
  `eid` int(0) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `did` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_emp
-- ----------------------------
INSERT INTO `t_emp` VALUES (1, 'admin', 22, '男', '19099@qq.com', 3);
INSERT INTO `t_emp` VALUES (2, '李四', 23, '女', '213213@qq.com', 3);
INSERT INTO `t_emp` VALUES (3, '王五', 34, '男', '213213@qq.com', 2);
INSERT INTO `t_emp` VALUES (4, '黄六', 44, '女', '213213@qq.com', 2);
INSERT INTO `t_emp` VALUES (11, 'a1', 23, '男', '123124@qq.com', NULL);
INSERT INTO `t_emp` VALUES (12, 'a2', 23, '男', '123124@qq.com', NULL);
INSERT INTO `t_emp` VALUES (13, 'a3', 23, '男', '123124@qq.com', NULL);

SET FOREIGN_KEY_CHECKS = 1;
