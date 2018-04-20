
DROP TABLE IF EXISTS `t_b_resources`;
CREATE TABLE `t_b_resources` (
  `resource_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(255) NOT NULL,
  `resource_code` varchar(255) NOT NULL,
  `resource_url` varchar(255) NOT NULL,
  `resource_desc` varchar(255) DEFAULT NULL,
  `css_cls` varchar(255) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `resource_type` int(11) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `create_time` timestamp NULL,
  `update_time` timestamp NULL,
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_b_resources` WRITE;
INSERT INTO `t_b_resources` VALUES ('1','系统管理','system','#','system_manager',NULL,NULL,0,0,1,'2018-02-08 10:15:30','2018-02-08 10:15:30');
INSERT INTO `t_b_resources` VALUES ('2', '资源管理', 'resource', '/system/resource/list', 'system_resource', null, null, '1', '0', '1', '2018-02-08 10:14:32', '2018-02-08 10:14:35');
INSERT INTO `t_b_resources` VALUES ('5', '用户管理', '', '/system/user/list', '', '', '3', '1', '0', '1', '2018-03-05 22:33:06', null);
INSERT INTO `t_b_resources` VALUES ('6', '讲师管理', '#', '#', '', '', '1', '0', '0', '1', '2018-03-11 16:45:56', null);
INSERT INTO `t_b_resources` VALUES ('7', '讲师列表', '#', '/lecturer/list', '', '', '1', '4', '0', '1', '2018-03-11 16:46:23', null);
INSERT INTO `t_b_resources` VALUES ('8', '邀请讲师', '#', '/lecturer/invite/list', '', '', '2', '4', '0', '1', '2018-03-17 15:09:42', null);
INSERT INTO `t_b_resources` VALUES ('9', '讲师等级', '#', '/lecturer/grade/list', '', '', '3', '4', '0', '1', '2018-03-17 15:11:04', null);
INSERT INTO `t_b_resources` VALUES ('10', '提现审核', '#', '/lecturer/withdraw/list', '', '', '5', '4', '0', '1', '2018-03-17 18:27:57', null);
INSERT INTO `t_b_resources` VALUES ('11', '讲师审核', '#', '/lecturer/register/list', '', '', '4', '4', '0', '1', '2018-03-17 18:28:37', null);
INSERT INTO `t_b_resources` VALUES ('7', '直播间管理', 'room_manager', '#', '', '', '2', '0', '0', '1', '2018-04-20 17:13:39', '2018-04-20 17:14:16'),
INSERT INTO `t_b_resources` VALUES ('9', '直播间列表', 'room_list', '/room/get/info', '', '', '1', '7', '0', '1', '2018-04-20 17:15:41', null);
UNLOCK TABLES;



DROP TABLE IF EXISTS `t_b_user_info`;
CREATE TABLE `t_b_user_info` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL,
  `login_name` varchar(100) DEFAULT NULL,
  `login_pwd` varchar(100) DEFAULT NULL,
  `user_status` int(11) DEFAULT NULL COMMENT '用户状态(0:初始化，1：正常，2：冻结，3：过期 4:删除)',
  `parent_id` bigint(11) DEFAULT NULL,
  `login_invallid` timestamp NULL DEFAULT NULL,
  `last_login_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

LOCK TABLES `t_b_user_info` WRITE;
INSERT INTO `t_b_user_info` VALUES (1,'chenbiao','bill','a02f6f0009b898916d0794508cd508652df76d3e1093e9198b2e2b84802a4b92',1,NULL,'2019-02-08 09:30:13','2018-02-08 10:20:56','2018-02-08 10:20:56','2018-02-08 10:20:56');
UNLOCK TABLES;



DROP TABLE IF EXISTS `t_b_user_resource`;
CREATE TABLE `t_b_user_resource` (
  `user_resource_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  `active` int(11) NOT NULL,
  `create_time` timestamp NULL,
  `update_time` timestamp NULL,
  PRIMARY KEY (`user_resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
LOCK TABLES `t_b_user_resource` WRITE;
INSERT INTO `t_b_user_resource` VALUES
(1,1,1,1,'2018-02-08 10:12:02','2018-02-08 10:12:05'),(2,1,2,1,'2018-02-08 10:15:42','2018-02-08 10:15:45');
UNLOCK TABLES;


DROP TABLE IF EXISTS `t_b_up_file_record`;
CREATE TABLE `t_b_up_file_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文件记录主键',
  `actual_file_path` varchar(255) DEFAULT NULL COMMENT '实际文件地址',
  `http_file_path` varchar(255) DEFAULT NULL,
  `split_files` text COMMENT 'http直接访问的地址，例如[{filepath:http://..../.jpg,isForeshow:true},{....}]',
  `creator_id` int(255) DEFAULT NULL COMMENT '上传作者',
  `create_time` datetime DEFAULT NULL COMMENT '上传时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
