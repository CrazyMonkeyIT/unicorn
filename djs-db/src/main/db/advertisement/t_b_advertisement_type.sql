/*
Navicat MySQL Data Transfer

Source Server         : mysql-local
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : djs

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-03-07 17:53:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_b_advertisement_type
-- ----------------------------
DROP TABLE IF EXISTS `t_b_advertisement_type`;
CREATE TABLE `t_b_advertisement_type` (
  `advertisement_type_id` int(11) NOT NULL,
  `advertisement_type_desc` varchar(255) DEFAULT NULL COMMENT '广告类型描述',
  PRIMARY KEY (`advertisement_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_b_advertisement_type
-- ----------------------------
INSERT INTO `t_b_advertisement_type` VALUES ('1', '超链接广告');
INSERT INTO `t_b_advertisement_type` VALUES ('2', '路演主题广告');
INSERT INTO `t_b_advertisement_type` VALUES ('3', '专题广告');
INSERT INTO `t_b_advertisement_type` VALUES ('4', '讲师广告');
INSERT INTO `t_b_advertisement_type` VALUES ('5', '指定路演广告');
INSERT INTO `t_b_advertisement_type` VALUES ('6', 'VIP广告');
