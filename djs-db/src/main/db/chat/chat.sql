DROP TABLE IF EXISTS `t_b_content`;
CREATE TABLE `t_b_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_id` bigint(20) DEFAULT NULL COMMENT '房间ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `content` text COMMENT '内容',
  `attachment` blob COMMENT '附件',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_b_room`;
CREATE TABLE `t_b_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `name` varchar(255) DEFAULT NULL COMMENT '房间名称',
  `type` int(11) DEFAULT NULL COMMENT '房间內型',
  `count` int(11) DEFAULT NULL COMMENT '房间总人数',
  `members` varchar(255) DEFAULT NULL COMMENT '房间人员ID',
  `status` int(11) DEFAULT NULL COMMENT '房间状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



