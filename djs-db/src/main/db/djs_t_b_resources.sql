
DROP TABLE IF EXISTS `t_b_resources`;
CREATE TABLE `t_b_resources` (
  `resource_id` bigint(20) NOT NULL,
  `resource_name` varchar(255) NOT NULL,
  `resource_code` varchar(255) NOT NULL,
  `resource_url` varchar(255) NOT NULL,
  `resource_desc` varchar(255) DEFAULT NULL,
  `css_cls` varchar(255) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `resource_type` int(11) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_b_resources` WRITE;
INSERT INTO `t_b_resources` VALUES (1,'系统管理','system','#','system_manager',NULL,NULL,0,0,1,'2018-02-08 10:15:30','2018-02-08 10:15:30'),(2,'资源管理','resource','/system/resource/list','system_resource',NULL,NULL,1,0,1,'2018-02-08 10:14:32','2018-02-08 10:14:35');
UNLOCK TABLES;
