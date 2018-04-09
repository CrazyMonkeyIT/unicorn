/*
 Navicat Premium Data Transfer

 Source Server         : unicorn
 Source Server Type    : MySQL
 Source Server Version : 50636
 Source Host           : localhost
 Source Database       : unicorn

 Target Server Type    : MySQL
 Target Server Version : 50636
 File Encoding         : utf-8

 Date: 03/11/2018 17:44:50 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_b_mini_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_b_mini_user`;
CREATE TABLE `t_b_mini_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '小程序客户id',
  `nick_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'nick_name',
  `gender` int(11) DEFAULT NULL COMMENT '性别(1:男，0：女)',
  `country` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '国家',
  `province` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '省份',
  `city` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '城市',
  `language` varchar(56) COLLATE utf8_bin DEFAULT NULL COMMENT '语言',
  `avatar_url` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像地址',
  `open_id` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'openid',
  `union_id` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'unionId',
  `create_time` timestamp COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `active` int(11) DEFAULT '1' COMMENT '用户状态(1:正常，0：失效)',
  `is_vip` int(1) DEFAULT NULL COMMENT '是否VIP (0 ：否  1：是)',
  `vip_invalid_time` timestamp NULL DEFAULT NULL COMMENT 'vip过期时间',
  `total_pay_amount` int(11) DEFAULT 0 COMMENT '总支付金额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_b_mini_user_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `t_b_mini_user`
-- ----------------------------

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