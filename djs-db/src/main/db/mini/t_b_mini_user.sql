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
  `create_time` timestamp NOT NULL DEFAULT  COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `active` int(11) DEFAULT '1' COMMENT '用户状态(1:正常，0：失效)',
  `is_vip` int(1) DEFAULT NULL COMMENT '是否VIP (0 ：否  1：是)',
  `vip_invalid_time` timestamp NULL DEFAULT NULL COMMENT 'vip过期时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_b_mini_user_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `t_b_mini_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_b_mini_user` VALUES ('9', '毛先生', '1', 'China', 'Shanghai', '', null, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLgZK43CgcILte4AfCBOicPTgYb7oIXq9CUPoYSDOgZyZZt000sR5eVib1UW70kW2OWNLeUF1vNu9xg/0', 'oIIsb5FO1VVlMLLZo5LS_gkTAnKg', null, '2018-03-11 17:26:49', '2018-03-11 17:26:49', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
