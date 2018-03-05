/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : djs

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2018-03-05 22:25:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_b_lecturer_income
-- ----------------------------
DROP TABLE IF EXISTS `t_b_lecturer_income`;
CREATE TABLE `t_b_lecturer_income` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lecturer_id` int(11) DEFAULT NULL COMMENT '讲师ID',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '金额',
  `income_type` varchar(50) DEFAULT NULL COMMENT '收益类型 （live：直播）',
  `income_srouce` varchar(50) DEFAULT NULL COMMENT '收益来源',
  `payment_ratio` int(11) DEFAULT NULL COMMENT '分成比例',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_b_lecturer_income
-- ----------------------------
