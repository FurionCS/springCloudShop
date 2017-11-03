/*
Navicat MySQL Data Transfer

Source Server         : cs
Source Server Version : 50634
Source Host           : 123.207.231.196:3306
Source Database       : springcloud-user

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-11-03 16:51:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_resources
-- ----------------------------
DROP TABLE IF EXISTS `t_resources`;
CREATE TABLE `t_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源表id',
  `url` varchar(255) DEFAULT NULL COMMENT '资源url',
  `description` varchar(255) DEFAULT NULL COMMENT '资源描述',
  `status` int(11) DEFAULT NULL COMMENT '状态0：停用1：正常',
  `create_user` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色表id',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_user` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resources`;
CREATE TABLE `t_role_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色资源表id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `resource_id` int(11) DEFAULT NULL COMMENT '资源id',
  `status` int(255) DEFAULT NULL COMMENT '状态 0:停用1:正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(255) DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(255) DEFAULT NULL COMMENT '账号密码',
  `idCard` varchar(18) DEFAULT NULL COMMENT '身份证',
  `balance` decimal(10,0) DEFAULT NULL COMMENT '用户余额',
  `createTime` datetime DEFAULT NULL,
  `leastTime` datetime DEFAULT NULL COMMENT '上次登入时间',
  `status` int(11) DEFAULT '1' COMMENT '停用(0),正常(1),黑名单(1)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username` (`user_name`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_addr
-- ----------------------------
DROP TABLE IF EXISTS `t_user_addr`;
CREATE TABLE `t_user_addr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `receiver_name` varchar(50) DEFAULT NULL COMMENT '收件人姓名',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '收件人手机号',
  `receiver_city` varchar(20) DEFAULT NULL COMMENT '收件人城市',
  `receiver_district` varchar(20) DEFAULT NULL COMMENT '收件人省',
  `receiver_address` varchar(100) DEFAULT NULL COMMENT '收件人地址',
  `receiver_code` varchar(6) DEFAULT NULL COMMENT '邮编',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `ind_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户地址表';

-- ----------------------------
-- Table structure for t_user_balance_tcc
-- ----------------------------
DROP TABLE IF EXISTS `t_user_balance_tcc`;
CREATE TABLE `t_user_balance_tcc` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户余额tcc表id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `amount` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色表',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `status` int(11) DEFAULT '1' COMMENT '状态：0:停用，1:正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
