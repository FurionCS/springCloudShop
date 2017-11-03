/*
Navicat MySQL Data Transfer

Source Server         : cs
Source Server Version : 50634
Source Host           : 123.207.231.196:3306
Source Database       : springcloud-integral

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-11-03 16:52:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_integral_change
-- ----------------------------
DROP TABLE IF EXISTS `t_integral_change`;
CREATE TABLE `t_integral_change` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '积分变化表id',
  `change_name` varchar(255) DEFAULT NULL COMMENT '变化名称',
  `change_dep` varchar(255) DEFAULT NULL COMMENT '变化描述',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `math` varchar(3000) DEFAULT NULL COMMENT '数学公式',
  `change_type` int(11) DEFAULT NULL COMMENT '变化类型',
  `code` varchar(20) DEFAULT NULL COMMENT '变化编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='积分变化类型表';

-- ----------------------------
-- Table structure for t_user_integral
-- ----------------------------
DROP TABLE IF EXISTS `t_user_integral`;
CREATE TABLE `t_user_integral` (
  `id` varchar(255) NOT NULL COMMENT '用户积分id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `now_source` bigint(20) DEFAULT NULL COMMENT '当前积分',
  `his_source` bigint(20) DEFAULT NULL COMMENT '历史总积分',
  `used_source` bigint(20) DEFAULT NULL COMMENT '使用的积分',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_integral_user_id_uindex` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户积分详情';

-- ----------------------------
-- Table structure for t_user_integral_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_user_integral_detail`;
CREATE TABLE `t_user_integral_detail` (
  `id` varchar(255) NOT NULL COMMENT '用户积分详情id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `change_source` int(11) DEFAULT NULL COMMENT '改变积分',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `change_id` int(11) DEFAULT NULL COMMENT '改变类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户积分变化详情表';
