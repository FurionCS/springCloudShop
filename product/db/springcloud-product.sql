/*
Navicat MySQL Data Transfer

Source Server         : cs
Source Server Version : 50634
Source Host           : 123.207.231.196:3306
Source Database       : springcloud-product

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-11-03 16:51:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `delete_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `name` varchar(45) NOT NULL COMMENT '商品名',
  `stock` int(10) unsigned NOT NULL DEFAULT '10000000' COMMENT '库存',
  `price` decimal(10,0) unsigned NOT NULL DEFAULT '0' COMMENT '售价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_product_category
-- ----------------------------
DROP TABLE IF EXISTS `t_product_category`;
CREATE TABLE `t_product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品分类表id',
  `name` varchar(255) NOT NULL COMMENT '产品名字',
  `sort_order` int(2) DEFAULT NULL COMMENT '排序权重',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='产品分类表';

-- ----------------------------
-- Table structure for t_product_stock_tcc
-- ----------------------------
DROP TABLE IF EXISTS `t_product_stock_tcc`;
CREATE TABLE `t_product_stock_tcc` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `expire_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `stock` int(10) unsigned NOT NULL COMMENT '预留资源数量',
  `status` int(3) unsigned NOT NULL COMMENT '0为try, 1为confirm完成',
  `product_id` int(19) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_product_tcc_status_exptime` (`status`,`expire_time`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
