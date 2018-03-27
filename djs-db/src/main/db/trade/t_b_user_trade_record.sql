/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : djs

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2018-03-27 22:59:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_b_user_trade_record
-- ----------------------------
DROP TABLE IF EXISTS `t_b_user_trade_record`;
CREATE TABLE `t_b_user_trade_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(128) DEFAULT NULL,
  `trade_amount` decimal(20,2) DEFAULT NULL COMMENT '交易金额',
  `trade_type` int(1) DEFAULT NULL COMMENT '交易类型 0：收入  1：支出',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
