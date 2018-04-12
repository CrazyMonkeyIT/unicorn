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

 Date: 04/12/2018 23:21:39 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_b_room_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_b_room_user`;
CREATE TABLE `t_b_room_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) DEFAULT NULL COMMENT '房间id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `pay_type` int(11) DEFAULT NULL COMMENT '消耗类型(0:邀请码；1:微信支付)',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `active` int(11) DEFAULT '1' COMMENT '状态(0:失效；1:有效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
