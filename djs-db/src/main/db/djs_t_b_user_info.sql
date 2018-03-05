
DROP TABLE IF EXISTS `t_b_user_info`;
CREATE TABLE `t_b_user_info` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL,
  `login_name` varchar(100) DEFAULT NULL,
  `login_pwd` varchar(100) DEFAULT NULL,
  `user_status` int(11) DEFAULT NULL,
  `parent_id` bigint(11) DEFAULT NULL,
  `login_invallid` timestamp NULL,
  `last_login_time` timestamp NULL,
  `update_time` timestamp NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

LOCK TABLES `t_b_user_info` WRITE;
INSERT INTO `t_b_user_info` VALUES (1,'chenbiao','bill','a02f6f0009b898916d0794508cd508652df76d3e1093e9198b2e2b84802a4b92',1,NULL,'2018-02-08 09:30:13','2018-02-08 10:20:56','2018-02-08 10:20:56');
UNLOCK TABLES;

--函数
DROP FUNCTION IF EXISTS `getChildList`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getChildList`(rootId int) RETURNS varchar(2000) CHARSET utf8
BEGIN
DECLARE str varchar(2000);
DECLARE cid varchar(100);
SET str = '';
SET cid = rootId;
WHILE cid is not null DO
    SET str = concat(str, ',', cid);
    SELECT group_concat(user_id) INTO cid FROM t_b_login_user where FIND_IN_SET(parent_id, cid) > 0;
END WHILE;
RETURN substring(str,4);
END
;;