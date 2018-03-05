/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : djs

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2018-03-05 22:25:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_b_lecturer_account
-- ----------------------------
DROP TABLE IF EXISTS `t_b_lecturer_account`;
CREATE TABLE `t_b_lecturer_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lecturer_id` int(11) DEFAULT NULL COMMENT '讲师ID',
  `payment_ratio` int(11) DEFAULT NULL COMMENT '分成比例',
  `total_income` decimal(11,2) DEFAULT NULL COMMENT '总收益',
  `withdraw_cash` decimal(11,2) DEFAULT NULL COMMENT '可提现金额',
  `withdraw_switch` varchar(10) DEFAULT NULL COMMENT '提现开关 （ON/OFF）',
  `withdraw_off_desc` varchar(200) DEFAULT NULL COMMENT '禁止提现原因',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_b_lecturer_account
-- ----------------------------
