/*
Navicat MySQL Data Transfer

Source Server         : mysql-local
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : djs

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-03-15 15:59:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_b_live_type
-- ----------------------------
DROP TABLE IF EXISTS `t_b_live_type`;
CREATE TABLE `t_b_live_type` (
  `live_id` int(11) DEFAULT NULL,
  `live_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_b_live_type
-- ----------------------------
INSERT INTO `t_b_live_type` VALUES ('1', '普通');
INSERT INTO `t_b_live_type` VALUES ('2', '专场');
INSERT INTO `t_b_live_type` VALUES ('3', 'VIP');
