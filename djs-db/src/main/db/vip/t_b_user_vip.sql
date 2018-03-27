/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : djs

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2018-03-25 23:01:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_b_user_vip
-- ----------------------------
DROP TABLE IF EXISTS `t_b_user_vip`;
CREATE TABLE `t_b_user_vip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vip_name` varchar(50) DEFAULT NULL,
  `valid_day` int(11) DEFAULT NULL,
  `open_money` double(11,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_b_user_vip
-- ----------------------------
INSERT INTO `t_b_user_vip` VALUES ('1', '一年', '365', '1000.00');
INSERT INTO `t_b_user_vip` VALUES ('2', '6个月', '180', '500.00');
INSERT INTO `t_b_user_vip` VALUES ('3', '3个月', '90', '300.00');



-- ----------------------------
-- Table structure for t_b_user_vip_record
-- ----------------------------
DROP TABLE IF EXISTS `t_b_user_vip_record`;
CREATE TABLE `t_b_user_vip_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mini_user_id` int(11) DEFAULT NULL,
  `user_vip_id` int(11) DEFAULT NULL,
  `open_money` varchar(50) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_b_user_vip_record
-- ----------------------------
