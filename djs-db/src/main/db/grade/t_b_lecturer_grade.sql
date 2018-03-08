/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : djs

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-03-08 22:27:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_b_lecturer_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_b_lecturer_grade`;
CREATE TABLE `t_b_lecturer_grade` (
  `lecturer_grade_id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(255) DEFAULT NULL COMMENT '等级名称',
  `payment_ratio` int(11) DEFAULT NULL COMMENT '等级分成比例',
  `is_valid` int(11) DEFAULT NULL COMMENT '是否有效 0:无效 1:有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`lecturer_grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
