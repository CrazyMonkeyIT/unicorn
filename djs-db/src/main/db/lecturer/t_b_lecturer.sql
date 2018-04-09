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
  `open_id` varchar(100) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `grade_id` int(11) DEFAULT NULL COMMENT '讲师等级',
  `head_photo_file` varchar(100) DEFAULT NULL COMMENT '讲师头像',
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
-- Table structure for t_b_lecturer_account
-- ----------------------------
DROP TABLE IF EXISTS `t_b_lecturer_account`;
CREATE TABLE `t_b_lecturer_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lecturer_id` int(11) DEFAULT NULL COMMENT '讲师ID',
  `payment_ratio` int(11) DEFAULT NULL COMMENT '分成比例',
  `total_income` decimal(11,2) DEFAULT NULL COMMENT '总收益',
  `withdraw_cash` decimal(11,2) DEFAULT NULL COMMENT '可提现金额',
  `withdraw_switch` varchar(10) DEFAULT NULL COMMENT '提现开关 （ON/OFF）',
  `withdraw_off_desc` varchar(200) DEFAULT NULL COMMENT '禁止提现原因',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for t_b_lecturer_income
-- ----------------------------
DROP TABLE IF EXISTS `t_b_lecturer_income`;
CREATE TABLE `t_b_lecturer_income` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lecturer_id` int(11) DEFAULT NULL COMMENT '讲师ID',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '金额',
  `income_type` varchar(50) DEFAULT NULL COMMENT '收益类型 （live：直播）',
  `income_srouce` varchar(50) DEFAULT NULL COMMENT '收益来源',
  `payment_ratio` int(11) DEFAULT NULL COMMENT '分成比例',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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
  `create_time` timestamp NULL ,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_b_withdraw_examine
-- ----------------------------
DROP TABLE IF EXISTS `t_b_withdraw_examine`;
CREATE TABLE `t_b_withdraw_examine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lecturer_id` int(11) DEFAULT NULL COMMENT '讲师ID',
  `withdraw_money` decimal(11,2) DEFAULT NULL COMMENT '提现金额',
  `status` varchar(10) DEFAULT NULL COMMENT '状态 （wait 等待审核/already 已通过/refuse 已拒绝）',
  `create_time` timestamp NULL ,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for t_b_lecturer_register
-- ----------------------------
DROP TABLE IF EXISTS `t_b_lecturer_register`;
CREATE TABLE `t_b_lecturer_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lecturer_name` varchar(50) DEFAULT NULL,
  `open_id` varchar(100) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `grade_id` int(11) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL COMMENT '状态（WAIT/SUCCESS/FAILURE）',
  `head_photo_file` varchar(100) DEFAULT NULL COMMENT '头像',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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