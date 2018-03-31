




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
  `type` int(11) DEFAULT NULL COMMENT '房间內型',
  `count` int(11) DEFAULT NULL COMMENT '房间总人数',
  `members` text DEFAULT NULL COMMENT '房间人员ID',
  `status` int(11) DEFAULT NULL COMMENT '房间状态',
  `room_price` decimal DEFAULT null comment '房间价格',
  `prepare_live_begin_time` datetime DEFAULT NULL COMMENT '预计直播开始时间',
  `prepare_live_end_time` datetime DEFAULT NULL COMMENT '预计直播结束时间',
  `actual_live_begin_time` datetime default null comment '实际直播开始时间',
  `actual_live_end_time` datetime default null comment '实际直播结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



