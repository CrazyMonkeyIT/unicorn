/*
Navicat MySQL Data Transfer

Source Server         : mysql-local
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : djs

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-03-07 17:54:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_b_advertisement
-- ----------------------------
DROP TABLE IF EXISTS `t_b_advertisement`;
CREATE TABLE `t_b_advertisement` (
  `advertisement_id` int(11) NOT NULL AUTO_INCREMENT,
  `advertisement_type_id` int(11) DEFAULT NULL,
  `advertisement_url` varchar(255) DEFAULT NULL COMMENT '超链接URL',
  `advertisement_img` blob COMMENT '广告图片',
  `advertisement_title` varchar(255) DEFAULT NULL COMMENT '广告标题',
  `advertisement_desc` varchar(255) DEFAULT NULL COMMENT '广告描述',
  `status` int(255) DEFAULT NULL COMMENT '状态 -2:人工暂停 1:正常 -1:到期',
  `invalid_date` datetime DEFAULT NULL COMMENT '失效时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`advertisement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
