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
DROP TABLE IF EXISTS `t_b_advertisement`;
CREATE TABLE `t_b_advertisement` (
  `advertisement_id` int(11) NOT NULL AUTO_INCREMENT,
  `advertisement_type_id` int(11) DEFAULT NULL,
  `advertisement_url` varchar(255) DEFAULT NULL COMMENT '超链接URL',
  `room_id` bigint(20) DEFAULT NULL COMMENT '关联的房间ID',
  `lecturer_id` int(11) DEFAULT NULL COMMENT '关联的讲师ID',
  `advertisement_img_path` varchar(255) DEFAULT NULL COMMENT '广告图片地址',
  `advertisement_title` varchar(255) DEFAULT NULL COMMENT '广告标题',
  `advertisement_desc` varchar(255) DEFAULT NULL COMMENT '广告描述',
  `status` int(255) DEFAULT NULL COMMENT '状态 -2:人工暂停 1:正常 -1:到期 0:删除',
  `invalid_date` datetime DEFAULT NULL COMMENT '失效时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`advertisement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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
INSERT INTO `t_b_advertisement_type` VALUES ('1', '专题广告');
INSERT INTO `t_b_advertisement_type` VALUES ('2', '讲师广告');
INSERT INTO `t_b_advertisement_type` VALUES ('3', '指定路演广告');
INSERT INTO `t_b_advertisement_type` VALUES ('4', 'VIP广告');



-- ----------------------------
--  Table structure for `t_b_subject`
-- ----------------------------
DROP TABLE IF EXISTS `t_b_subject`;
CREATE TABLE `t_b_subject` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) DEFAULT NULL,
  `subject_poster` varchar(255) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
