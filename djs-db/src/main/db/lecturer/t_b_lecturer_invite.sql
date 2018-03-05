/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : djs

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2018-03-05 22:25:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_b_lecturer_invite
-- ----------------------------
DROP TABLE IF EXISTS `t_b_lecturer_invite`;
CREATE TABLE `t_b_lecturer_invite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lecturer_name` varchar(50) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `grade_id` int(11) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `invite_code` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL COMMENT '状态 （notallow/allow）',
  `create_user_id` varchar(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_b_lecturer_invite
-- ----------------------------
