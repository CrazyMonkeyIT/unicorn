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

 Date: 03/31/2018 16:01:09 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_b_room_content`
-- ----------------------------
DROP TABLE IF EXISTS `t_b_room_content`;
CREATE TABLE `t_b_room_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomId` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '记录产生的小程序用户id',
  `type` varchar(36) DEFAULT NULL COMMENT '消息类型：text 文本消息，voice 语音消息，image 图片消息',
  `duration` int(3) DEFAULT NULL,
  `url` varchar(2000) DEFAULT NULL COMMENT '语音消息或图片消息的url',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `active` int(11) DEFAULT '1' COMMENT '是否有效：0、失效，1、有效',
  `content` text COMMENT '聊天内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_b_room_content`
-- ----------------------------
BEGIN;
INSERT INTO `t_b_room_content` VALUES ('2', '123', '9', 'voice', '1', null, '2018-03-24 16:57:50', '1', null), ('3', '123', '9', 'voice', '5', null, '2018-03-24 16:58:16', '1', null), ('4', '123', '9', 'voice', '14', null, '2018-03-25 13:23:15', '1', null), ('5', '123', '9', 'voice', '3', null, '2018-03-25 14:47:01', '1', null), ('6', '123', '9', 'voice', '3', null, '2018-03-25 14:52:21', '1', null), ('7', '123', '9', 'voice', '2', 'https://dujiaoshouzhiku.com/minifile/123/wx99b01206c5360b49(1521961088564).o6zAJswoRcHhTELimgTqFQpQi3rs.275c3473c3ba0d42b572b8438f61b943.silk', '2018-03-25 14:58:08', '1', null), ('8', '123', '9', 'voice', '1', null, '2018-03-25 15:34:05', '1', null), ('9', '123', '9', 'voice', '3', null, '2018-03-31 14:44:22', '1', null), ('10', '123', '9', 'voice', '75', null, '2018-03-31 14:45:33', '1', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
