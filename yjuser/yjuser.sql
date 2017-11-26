/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 100208
Source Host           : localhost:3306
Source Database       : yjuser

Target Server Type    : MYSQL
Target Server Version : 100208
File Encoding         : 65001

Date: 2017-11-21 19:23:19
*/

DROP DATABASE IF EXISTS `yjuser`;
CREATE DATABASE IF NOT EXISTS `yjuser` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `yjuser`;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `mail` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` int(11) DEFAULT NULL COMMENT '手机',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `id_card` int(18) DEFAULT NULL COMMENT '身份证号',
  `person_name` varchar(255) DEFAULT NULL COMMENT '身份证姓名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `icon` varchar(255) DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
