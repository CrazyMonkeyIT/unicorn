
DROP TABLE IF EXISTS `t_b_room_content`;
CREATE TABLE `t_b_room_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomId` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '记录产生的小程序用户id',
  `type` varchar(36) DEFAULT NULL COMMENT '消息类型：text 文本消息，voice 语音消息，image 图片消息',
  `duration` int(3) DEFAULT NULL,
  `url` varchar(2000) DEFAULT NULL COMMENT '语音消息或图片消息的url',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `active` int(11) DEFAULT '1' COMMENT '是否有效：0、失效，1、有效',
  `content` text COMMENT '聊天内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
BEGIN;
INSERT INTO `t_b_room_content` VALUES ('2', '123', '9', 'voice', '1', null, '2018-03-24 16:57:50', '1', null), ('3', '123', '9', 'voice', '5', null, '2018-03-24 16:58:16', '1', null), ('4', '123', '9', 'voice', '14', null, '2018-03-25 13:23:15', '1', null), ('5', '123', '9', 'voice', '3', null, '2018-03-25 14:47:01', '1', null), ('6', '123', '9', 'voice', '3', null, '2018-03-25 14:52:21', '1', null), ('7', '123', '9', 'voice', '2', 'https://dujiaoshouzhiku.com/minifile/123/wx99b01206c5360b49(1521961088564).o6zAJswoRcHhTELimgTqFQpQi3rs.275c3473c3ba0d42b572b8438f61b943.silk', '2018-03-25 14:58:08', '1', null), ('8', '123', '9', 'voice', '1', null, '2018-03-25 15:34:05', '1', null), ('9', '123', '9', 'voice', '3', null, '2018-03-31 14:44:22', '1', null), ('10', '123', '9', 'voice', '75', null, '2018-03-31 14:45:33', '1', null);
COMMIT;


DROP TABLE IF EXISTS `t_b_room`;
CREATE TABLE `t_b_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `name` varchar(255) DEFAULT NULL COMMENT '房间名称',
  `subject_id` int(11) DEFAULT NULL COMMENT '专题ID',
  `lecturer_id` int(11) NOT NULL COMMENT '讲师ID',
  `room_desc` varchar(500) DEFAULT NULL COMMENT '房间描述',
  `room_poster_path` varchar(255) NOT NULL COMMENT '房间海报地址',
  `type` int(11) DEFAULT NULL COMMENT '房间內型 0：VIP 1：路演',
  `count` int(11) DEFAULT NULL COMMENT '房间总人数',
  `members` text COMMENT '房间人员ID',
  `status` int(11) DEFAULT NULL COMMENT '房间状态 0：直播中 1：直播结束 -1：直播未开始',
  `room_price` decimal(10,0) DEFAULT NULL COMMENT '房间价格',
  `courseware_id` bigint(255) DEFAULT NULL COMMENT '课件文件id',
  `prepare_live_begin_time` datetime DEFAULT NULL COMMENT '预计直播开始时间',
  `prepare_live_end_time` datetime DEFAULT NULL COMMENT '预计直播结束时间',
  `actual_live_begin_time` datetime DEFAULT NULL COMMENT '实际直播开始时间',
  `actual_live_end_time` datetime DEFAULT NULL COMMENT '实际直播结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  PRIMARY KEY (`id`),
  KEY `courseware_id` (`courseware_id`),
  CONSTRAINT `outindex_courseware_id` FOREIGN KEY (`courseware_id`)
  REFERENCES `t_b_up_file_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;) ENGINE=InnoDB DEFAULT CHARSET=utf8;



