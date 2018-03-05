/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : djs

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2018-03-05 22:25:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_b_lecturer
-- ----------------------------
DROP TABLE IF EXISTS `t_b_lecturer`;
CREATE TABLE `t_b_lecturer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lecturer_name` varchar(50) DEFAULT NULL COMMENT '讲师姓名',
  `phone` varchar(11) DEFAULT NULL,
  `grade_id` int(11) DEFAULT NULL COMMENT '讲师等级',
  `is_chief` varchar(10) DEFAULT NULL COMMENT '是否首席 (yes/no)',
  `status` int(2) DEFAULT NULL COMMENT '状态 （0：正常 1：直播中 2：封停）',
  `status_desc` varchar(200) DEFAULT NULL COMMENT '状态描述',
  `company` varchar(100) DEFAULT NULL COMMENT '公司',
  `position` varchar(100) DEFAULT NULL COMMENT '职位',
  `live_number` varchar(10) DEFAULT NULL COMMENT '直播场次',
  `live_hours` varchar(10) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_b_lecturer
-- ----------------------------
