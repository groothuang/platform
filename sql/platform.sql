/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : platform

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 22/04/2019 17:36:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info`  (
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '角色（1：普通用户，2：管理员，3超级管理员）',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `licence` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idCard` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enable_flag` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `car_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `car_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `car_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `car_price` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `total_price` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `return_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_return_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `return_sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `self_driving` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tools_info
-- ----------------------------
DROP TABLE IF EXISTS `tools_info`;
CREATE TABLE `tools_info`  (
  `car_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `car_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `car_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `car_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `car_brand` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `car_source` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `car_price` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `car_site` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `car_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `validity_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `car_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enable_flag` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `seat_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `return_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_return_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`car_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tools_info
-- ----------------------------
INSERT INTO `tools_info` VALUES ('C095334', '大巴', '095326.png', '大巴', '大众', '系统', '12', '2019-04-22 09:53:34', '珠海火车站', '哈哈哈', '12', '空闲', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tools_info` VALUES ('C105225', '', 'blank.jpg', '汽车', '', '系统', '', '2019-04-22 10:52:25', '', '', '', '空闲', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tools_info` VALUES ('C140715', '丰田皇冠2018款 2.0T', '140713.png', '汽车', '大众', '用户', '12', '2019-04-22 14:07:15', '珠江新城地铁站A2出口', '丰田皇冠2018款 2.0T 先锋版 厂商指导价：25.08-37.48万元', '12', '空闲', 'true', NULL, NULL, NULL, NULL);
INSERT INTO `tools_info` VALUES ('C141252', '12', '141913.png', '大巴', '', '用户', '', '2019-04-22 14:12:52', '', '', '', '空闲', 'false', NULL, NULL, NULL, NULL);
INSERT INTO `tools_info` VALUES ('C150341', '', '150328.png', '汽车', '', '用户', '', '2019-04-22 15:03:41', '', '', '', '空闲', 'false', NULL, NULL, NULL, NULL);
INSERT INTO `tools_info` VALUES ('C150721', '', '150707.png', '汽车', '', '用户', '', '2019-04-22 15:07:21', '', '', '', '空闲', 'false', NULL, NULL, NULL, NULL);
INSERT INTO `tools_info` VALUES ('C165749', '阿萨', '172828.png', '汽车', '大', '用户', '13', '2019-04-22 10:51:25', 'yyy', '斤斤计较军军军军军军', '1', NULL, 'true', NULL, NULL, NULL, NULL);
INSERT INTO `tools_info` VALUES ('C170247', '1', '104734.png', '自行车', '', '系统', '', '2019-04-19 17:02:47', '', '', '', '空闲', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tools_info` VALUES ('C173047', '2', '104724.png', '汽车', '', '系统', '', '2019-04-19 17:30:47', '', '', '', '空闲', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tools_info` VALUES ('C173053', '哈哈哈哈', '104630.png', '汽车', 'bmw', '系统', '10', '2019-04-19 17:30:53', '珠江新城', '珠江新城', '12', '空闲', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色（1：普通用户，2：管理员，3超级管理员）',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `licence` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id_card` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enable_flag` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('A18090154', 'admin1', '123123', '1', '', NULL, '', '', NULL, NULL, '345', '2019-04-18 09:01:54', 'true');
INSERT INTO `user_info` VALUES ('A18090221', 'admin2', '123123', '1', '', NULL, '', '', NULL, NULL, '1234', '2019-04-18 09:02:21', 'true');
INSERT INTO `user_info` VALUES ('A18090231', 'admin3', '123123', '1', '', NULL, '', '', NULL, NULL, '1234', '2019-04-18 09:02:31', 'true');
INSERT INTO `user_info` VALUES ('A18095113', 'admin4', '123123', '1', '', NULL, '', '', NULL, NULL, '12366', '2019-04-18 09:51:13', 'false');
INSERT INTO `user_info` VALUES ('S19001', 'super', '123123', '0', '', NULL, NULL, NULL, NULL, NULL, NULL, '2019-04-04 19:02:11', NULL);
INSERT INTO `user_info` VALUES ('U190417131853', 'user2', '123', '2', '小李', '女', 'yyyyyy', '34534', '5345', '45345', NULL, '2019-04-17 13:18:53', NULL);
INSERT INTO `user_info` VALUES ('U190417132257', 'user3', '123', '2', '黄', '女', '13750010000', '464101668@qq.com', '430101750000', '440500199702000000', '', '2019-04-17 13:22:57', NULL);
INSERT INTO `user_info` VALUES ('U190418110900', '123', '123', '2', '', '男', '', '', '', '', '123', '2019-04-18 11:09:00', NULL);

SET FOREIGN_KEY_CHECKS = 1;
