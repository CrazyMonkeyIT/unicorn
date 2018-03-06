/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : djs

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2018-03-05 22:26:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_b_withdraw_examine
-- ----------------------------
DROP TABLE IF EXISTS `t_b_withdraw_examine`;
CREATE TABLE `t_b_withdraw_examine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lecturer_id` int(11) DEFAULT NULL COMMENT '讲师ID',
  `withdraw_money` decimal(11,2) DEFAULT NULL COMMENT '提现金额',
  `status` varchar(10) DEFAULT NULL COMMENT '状态 （wait 等待审核/already 已通过/refuse 已拒绝）',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_b_withdraw_examine
-- ----------------------------
