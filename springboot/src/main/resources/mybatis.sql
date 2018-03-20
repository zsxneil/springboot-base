/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-20 20:57:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '城市编号',
  `province_id` int(10) unsigned NOT NULL COMMENT '省份编号',
  `city_name` varchar(25) DEFAULT NULL COMMENT '城市名称',
  `description` varchar(25) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '1', '深圳', 'that is it');
INSERT INTO `city` VALUES ('2', '5', '上海', 'poster test');

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemsname` varchar(32) NOT NULL COMMENT '商品名称',
  `price` float(10,1) NOT NULL COMMENT '商品定价',
  `detail` text COMMENT '商品描述',
  `pic` varchar(64) DEFAULT NULL COMMENT '商品图片',
  `createtime` datetime NOT NULL COMMENT '生产日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of items
-- ----------------------------
INSERT INTO `items` VALUES ('1', '台式机', '3000.0', '该电脑质量非常好！', 'pic1', '2015-07-07 13:28:53');
INSERT INTO `items` VALUES ('2', '笔记本', '6000.0', '笔记本性能好，质量好！', 'pic2', '2015-07-08 13:22:57');
INSERT INTO `items` VALUES ('3', '背包', '200.0', '名牌背包，容量大质量好！', 'pic3', '2015-07-10 13:25:02');
INSERT INTO `items` VALUES ('4', 'mybatis', '3330.5', 'mybtais test', 'path', '2018-03-20 18:59:07');
INSERT INTO `items` VALUES ('5', 'mybatis', '3330.5', 'mybtais test', 'path', '2018-03-20 19:06:36');
INSERT INTO `items` VALUES ('6', 'mybatis', '3330.5', 'mybtais test', 'path', '2018-03-20 19:06:42');

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orders_id` int(11) NOT NULL COMMENT '订单id',
  `items_id` int(11) NOT NULL COMMENT '商品id',
  `items_num` int(11) DEFAULT NULL COMMENT '商品购买数量',
  PRIMARY KEY (`id`),
  KEY `FK_orderdetail_1` (`orders_id`),
  KEY `FK_orderdetail_2` (`items_id`),
  CONSTRAINT `FK_orderdetail_1` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_orderdetail_2` FOREIGN KEY (`items_id`) REFERENCES `items` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------
INSERT INTO `orderdetail` VALUES ('0', '1', '1', '1');
INSERT INTO `orderdetail` VALUES ('1', '1', '2', '3');
INSERT INTO `orderdetail` VALUES ('2', '2', '3', '4');
INSERT INTO `orderdetail` VALUES ('3', '3', '2', '3');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '下单用户id',
  `number` varchar(30) NOT NULL COMMENT '订单号',
  `createtime` datetime NOT NULL COMMENT '创建订单时间',
  `note` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `FK_orders_1` (`user_id`),
  CONSTRAINT `FK_orders_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('0', '1', '1000010', '2015-06-04 13:22:35', null);
INSERT INTO `orders` VALUES ('1', '1', '1000011', '2015-07-08 13:22:41', null);
INSERT INTO `orders` VALUES ('2', '2', '1000012', '2015-07-17 14:13:23', null);
INSERT INTO `orders` VALUES ('3', '3', '1000012', '2015-07-16 18:13:23', null);
INSERT INTO `orders` VALUES ('4', '4', '1000012', '2015-07-15 19:13:23', null);
INSERT INTO `orders` VALUES ('5', '5', '1000012', '2015-07-14 17:13:23', null);
INSERT INTO `orders` VALUES ('6', '6', '1000012', '2015-07-13 16:13:23', null);

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of province
-- ----------------------------
INSERT INTO `province` VALUES ('26c6c0b82c3311e8b706484d7eb70be2', '广东', '493');
INSERT INTO `province` VALUES ('956642d42c3311e8b706484d7eb70be2', '广东', '895');
INSERT INTO `province` VALUES ('eae81c402c3211e8b706484d7eb70be2', '广东', '493');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL COMMENT '用户名称',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0', '王五', null, '2', null);
INSERT INTO `user` VALUES ('1', '张三', '2014-07-10', '1', '北京市');
INSERT INTO `user` VALUES ('2', '张小明', null, '1', '河南郑州');
INSERT INTO `user` VALUES ('3', '陈小明', null, '1', '河南郑州');
INSERT INTO `user` VALUES ('4', '张三丰', null, '1', '河南郑州');
INSERT INTO `user` VALUES ('5', '陈小明', null, '1', '河南郑州');
INSERT INTO `user` VALUES ('6', '王五', null, null, null);
INSERT INTO `user` VALUES ('7', '小A', '2015-06-27', '2', '北京');
INSERT INTO `user` VALUES ('8', '小B', '2015-06-27', '2', '北京');
INSERT INTO `user` VALUES ('9', '小C', '2015-06-27', '1', '北京');
INSERT INTO `user` VALUES ('10', '小D', '2015-06-27', '2', '北京');
INSERT INTO `user` VALUES ('35', '测试', '2017-04-01', '1', '深圳市');
INSERT INTO `user` VALUES ('36', '测试员', '2017-04-06', '1', '南山区');
INSERT INTO `user` VALUES ('37', '测试员', '2017-04-06', '1', '南山区');
INSERT INTO `user` VALUES ('38', 'neil_zhang', '2017-04-06', '0', '南山区');
INSERT INTO `user` VALUES ('39', 'zsxneil@qq.com', '2017-04-06', '1', '南山区');
INSERT INTO `user` VALUES ('46', 'Neil', '2018-03-17', '2', '深圳市');
INSERT INTO `user` VALUES ('47', 'Neil', '2018-03-17', '2', '深圳市');
INSERT INTO `user` VALUES ('48', 'Neil', '2018-03-17', '2', '深圳市');
INSERT INTO `user` VALUES ('51', 'neilUpdate', '2018-03-17', '2', '深圳市');
INSERT INTO `user` VALUES ('52', 'Neil', '2018-03-17', '2', '深圳市');
INSERT INTO `user` VALUES ('66', 'Neil', '2018-03-17', '2', '深圳市');
INSERT INTO `user` VALUES ('67', 'Neil', '2018-03-17', '2', '深圳市');
INSERT INTO `user` VALUES ('70', 'txtest', '2018-03-17', '2', '深圳市');
INSERT INTO `user` VALUES ('75', 'Neil', '2018-03-17', '2', '深圳市');
