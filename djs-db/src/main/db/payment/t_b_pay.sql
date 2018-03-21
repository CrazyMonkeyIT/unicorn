/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : djs

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2018-03-21 21:24:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_b_pay_result
-- ----------------------------
DROP TABLE IF EXISTS `t_b_pay_result`;
CREATE TABLE `t_b_pay_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unified_info_id` int(11) DEFAULT NULL,
  `nonce_str` varchar(32) DEFAULT NULL,
  `sign` varchar(32) DEFAULT NULL,
  `result_code` varchar(16) DEFAULT NULL,
  `err_code` varchar(32) DEFAULT NULL,
  `err_code_des` varchar(128) DEFAULT NULL,
  `openid` varchar(128) DEFAULT NULL,
  `trade_type` varchar(16) DEFAULT NULL,
  `bank_type` varchar(16) DEFAULT NULL,
  `total_fee` varchar(20) DEFAULT NULL,
  `cash_fee` varchar(20) DEFAULT NULL,
  `transaction_id` varchar(32) DEFAULT NULL,
  `out_trade_no` varchar(32) DEFAULT NULL,
  `time_end` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for t_b_unified_order
-- ----------------------------
DROP TABLE IF EXISTS `t_b_unified_order`;
CREATE TABLE `t_b_unified_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appid` varchar(32) DEFAULT NULL,
  `mch_id` varchar(32) DEFAULT NULL,
  `nonce_str` varchar(32) DEFAULT NULL,
  `sign` varchar(32) DEFAULT NULL,
  `body` varchar(128) DEFAULT NULL,
  `out_trade_no` varchar(32) DEFAULT NULL,
  `total_fee` int(20) DEFAULT NULL,
  `spbill_create_ip` varchar(16) DEFAULT NULL,
  `notify_url` varchar(256) DEFAULT NULL,
  `trade_type` varchar(16) DEFAULT NULL,
  `openid` varchar(128) DEFAULT NULL,
  `return_code` varchar(16) DEFAULT NULL,
  `return_msg` varchar(128) DEFAULT NULL,
  `result_code` varchar(16) DEFAULT NULL,
  `err_code` varchar(32) DEFAULT NULL,
  `err_code_des` varchar(128) DEFAULT NULL,
  `prepay_id` varchar(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for t_b_enterprise_pay
-- ----------------------------
DROP TABLE IF EXISTS `t_b_enterprise_pay`;
CREATE TABLE `t_b_enterprise_pay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mch_appid` varchar(64) DEFAULT NULL,
  `mchid` varchar(32) DEFAULT NULL,
  `nonce_str` varchar(32) DEFAULT NULL,
  `sign` varchar(32) DEFAULT NULL,
  `partner_trade_no` varchar(32) DEFAULT NULL,
  `openid` varchar(128) DEFAULT NULL,
  `check_name` varchar(10) DEFAULT NULL,
  `re_user_name` varchar(20) DEFAULT NULL,
  `amount` int(20) DEFAULT NULL,
  `desc` varchar(128) DEFAULT NULL,
  `spbill_create_ip` varchar(32) DEFAULT NULL,
  `result_code` varchar(16) DEFAULT NULL,
  `err_code` varchar(32) DEFAULT NULL,
  `err_code_des` varchar(128) DEFAULT NULL,
  `payment_no` varchar(64) DEFAULT NULL,
  `payment_time` varchar(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;