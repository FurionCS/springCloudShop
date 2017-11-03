/*
Navicat MySQL Data Transfer

Source Server         : cs
Source Server Version : 50634
Source Host           : 123.207.231.196:3306
Source Database       : springcloud-order

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-11-03 16:51:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `delete_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `user_id` int(20) unsigned NOT NULL COMMENT '下单用户ID',
  `product_id` int(20) unsigned NOT NULL COMMENT '产品ID',
  `price` int(10) unsigned NOT NULL COMMENT '实际支付金额',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '订单状态, 0为支付中, 1为交易完成, 2为全部资源已被撤销, 3为资源确认冲突',
  PRIMARY KEY (`id`),
  KEY `idx_order_user_id_ct` (`user_id`,`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_order_participant
-- ----------------------------
DROP TABLE IF EXISTS `t_order_participant`;
CREATE TABLE `t_order_participant` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `expire_time` datetime NOT NULL COMMENT '预留资源过期时间',
  `uri` varchar(255) NOT NULL COMMENT '预留资源确认URI',
  `order_id` bigint(19) unsigned NOT NULL COMMENT '订单ID',
  PRIMARY KEY (`id`),
  KEY `idx_order_participant_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
