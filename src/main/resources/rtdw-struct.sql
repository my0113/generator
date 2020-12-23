/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.61 : Database - rtdw
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rtdw` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rtdw`;

/*Table structure for table `tbl_dict` */

DROP TABLE IF EXISTS `tbl_dict`;

CREATE TABLE `tbl_dict` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `dictCode` varchar(64) NOT NULL COMMENT '编码',
  `dictDesc` varchar(64) NOT NULL COMMENT '名称',
  `categoryCode` varchar(64) NOT NULL COMMENT '分类编码',
  `categoryDesc` varchar(64) DEFAULT NULL COMMENT '分类说明',
  `sortNo` int(8) unsigned NOT NULL DEFAULT '9999' COMMENT '排序编号',
  `dataType` varchar(64) NOT NULL DEFAULT 'STRING' COMMENT '数据类型',
  `remark` varchar(128) DEFAULT NULL COMMENT '附加说明',
  `locateCode` varchar(64) DEFAULT NULL COMMENT '检索标识',
  `cid` bigint(20) unsigned DEFAULT '0' COMMENT '创建人ID',
  `uid` bigint(20) unsigned DEFAULT '0' COMMENT '修改人ID',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `utime` datetime NOT NULL COMMENT '更新时间',
  `version` int(8) NOT NULL DEFAULT '0' COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_code_category_code` (`dictCode`,`categoryCode`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_discount` */

DROP TABLE IF EXISTS `tbl_discount`;

CREATE TABLE `tbl_discount` (
  `id` int(11) DEFAULT NULL COMMENT '优惠活动类型:1-新用户立减；2-满减；3-抵价券；4-套餐赠送；5-满赠；6-超时赔付；7-特价菜；8-首单返优惠券；9-使用红包；11-提前下单减；12-满返优惠券；16-满免配送费；17-折扣商品；18-美团专送再减；19-点评券；20-第二份半价；21-会员免配送费；22-门店新客立减；23-买赠；24-平台新用户立减；25-满减配送费；27-指定商品满减；28-新客满减；30-阶梯满减配送费；36-平台新客减配送费；40-加价购；41-新客折扣菜；45-外卖拼团；46-外卖加价购；48-拼团减配送费；53-新客专享减包装费54-新客专享减配送费；100-满返商家代金券；101-使用商家代金券；103-进店领券；117-商品券；118-商品折扣券；305-津贴联盟；306-立减金；307-套餐推荐津贴',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(100) DEFAULT NULL COMMENT '优惠说明'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_food` */

DROP TABLE IF EXISTS `tbl_food`;

CREATE TABLE `tbl_food` (
  `app_food_code` varchar(50) NOT NULL COMMENT '菜品id',
  `food_name` varchar(100) DEFAULT NULL COMMENT '菜品名',
  `price` float DEFAULT NULL COMMENT '菜品价格',
  `sku_id` varchar(50) DEFAULT NULL COMMENT '菜品sku',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位(份)',
  PRIMARY KEY (`app_food_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_order` */

DROP TABLE IF EXISTS `tbl_order`;

CREATE TABLE `tbl_order` (
  `ctime` datetime DEFAULT NULL COMMENT '订单创建时间',
  `caution` varchar(100) DEFAULT NULL COMMENT '备注',
  `cityId` bigint(20) DEFAULT NULL COMMENT '城市Id',
  `deliveryTime` datetime DEFAULT NULL COMMENT '用户预计送达时间，“立即送达”时为0',
  `ePoiId` varchar(50) DEFAULT NULL COMMENT '三方系统中的门店Id',
  `hasInvoiced` int(11) DEFAULT NULL COMMENT '是否需要发票:1-需要发票；0-不需要',
  `invoiceTitle` varchar(100) DEFAULT NULL COMMENT '发票抬头',
  `taxpayerId` varchar(50) DEFAULT NULL COMMENT '发票税号',
  `isThirdShipping` int(11) DEFAULT NULL COMMENT '是否第三方配送 0：否；1：是  （目前基本上不支持第三方配送）',
  `latitude` double DEFAULT NULL COMMENT '实际送餐地址纬度（高德系坐标）',
  `longitude` double DEFAULT NULL COMMENT '实际送餐地址经度（高德系坐标）',
  `logisticsCancelTime` DATETIME DEFAULT NULL COMMENT '取消配送时间',
  `logisticsCode` varchar(50) DEFAULT NULL COMMENT '配送类型码',
  `logisticsCompletedTime` datetime DEFAULT NULL COMMENT '配送完成时间',
  `logisticsConfirmTime` datetime DEFAULT NULL COMMENT '配送单确认时间，骑手接单时间',
  `logisticsDispatcherMobile` varchar(50) DEFAULT NULL COMMENT '骑手电话',
  `logisticsDispatcherName` varchar(50) DEFAULT NULL COMMENT '骑手姓名',
  `logisticsFetchTime` datetime DEFAULT NULL COMMENT '骑手取单时间',
  `logisticsId` int(11) DEFAULT NULL COMMENT '配送方ID',
  `logisticsName` varchar(50) DEFAULT NULL COMMENT '配送方名称',
  `logisticsSendTime` datetime DEFAULT NULL COMMENT '配送单下单时间',
  `logisticsStatus` int(11) DEFAULT NULL COMMENT '配送订单状态',
  `orderCompletedTime` datetime DEFAULT NULL COMMENT '订单完成时间',
  `orderConfirmTime` datetime DEFAULT NULL COMMENT '商家确认时间',
  `orderCancelTime` datetime DEFAULT NULL COMMENT '订单取消时间',
  `orderId` bigint(20) NOT NULL DEFAULT '0' COMMENT '订单ID',
  `orderIdView` bigint(20) DEFAULT NULL COMMENT '订单展示Id',
  `orderSendTime` datetime DEFAULT NULL COMMENT '用户下单时间',
  `originalPrice` float DEFAULT NULL COMMENT '订单原价',
  `payType` int(11) DEFAULT NULL COMMENT '订单支付类型（1：货到付款；2：在线支付）',
  `poiAddress` varchar(200) DEFAULT NULL COMMENT '门店地址',
  `poiId` bigint(20) DEFAULT NULL COMMENT '门店Id',
  `poiName` varchar(100) DEFAULT NULL COMMENT '门店名称',
  `poiPhone` varchar(50) DEFAULT NULL COMMENT '门店服务电话',
  `recipientAddress` varchar(200) DEFAULT NULL COMMENT '收货人地址',
  `recipientName` varchar(50) DEFAULT NULL COMMENT '收货人名称',
  `recipientPhone` varchar(50) DEFAULT NULL COMMENT '收货人电话',
  `backupRecipientPhone` varchar(100) DEFAULT NULL COMMENT '备份隐私号',
  `shippERPhone` varchar(50) DEFAULT NULL COMMENT '骑手电话',
  `shippingFee` float DEFAULT NULL COMMENT '配送费用',
  `status` int(11) DEFAULT NULL COMMENT '订单状态',
  `total` float DEFAULT NULL COMMENT '总价',
  `uTime` datetime DEFAULT NULL COMMENT '订单更新时间',
  `daySeq` int(11) DEFAULT NULL COMMENT '门店当天的订单流水号（每天流水号从1开始）',
  `dinnersNumber` int(11) DEFAULT NULL COMMENT '就餐人数',
  `pickType` int(11) DEFAULT NULL COMMENT '取餐类型',
  `isFavorites` tinyint(1) DEFAULT NULL COMMENT '用户是否收藏此门店',
  `isPoiFirstOrder` tinyint(1) DEFAULT NULL COMMENT '用户是否第一次在此门店点餐',
  `orderTagList` varchar(50) DEFAULT NULL COMMENT '订单业务打标枚举，即业务身份（json字符串格式的数组，例："[16]"）',
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_order_discount_map` */

DROP TABLE IF EXISTS `tbl_order_discount_map`;

CREATE TABLE `tbl_order_discount_map` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(20) DEFAULT NULL COMMENT '订单ID',
  `discountId` bigint(20) DEFAULT NULL COMMENT '优惠ID',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_order_food_map` */

DROP TABLE IF EXISTS `tbl_order_food_map`;

CREATE TABLE `tbl_order_food_map` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(20) DEFAULT NULL COMMENT '订单ID',
  `foodId` varchar(50) DEFAULT NULL COMMENT '餐品ID',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_poi` */

DROP TABLE IF EXISTS `tbl_poi`;

CREATE TABLE `tbl_poi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '门店Id',
  `epoiId` varchar(50) DEFAULT NULL COMMENT 'ERP系统的门店ID',
  `poiAddress` varchar(300) DEFAULT NULL COMMENT '门店地址',
  `poiName` varchar(100) DEFAULT NULL COMMENT '门店名称',
  `poiPhone` varchar(50) DEFAULT NULL COMMENT '门店服务电话',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_poi_discount_map` */

DROP TABLE IF EXISTS `tbl_poi_discount_map`;

CREATE TABLE `tbl_poi_discount_map` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `poiId` bigint(20) NOT NULL COMMENT '门店Id',
  `discountId` bigint(20) DEFAULT NULL COMMENT '优惠ID',
  `reduceFee` float DEFAULT NULL COMMENT '活动优惠金额，在原价基础上减免的金额',
  `mtCharge` float DEFAULT NULL COMMENT '该活动中美团承担的费用',
  `poiCharge` float DEFAULT NULL COMMENT '该活动中商家承担的费用',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

/*Table structure for table `tbl_poi_food_map` */

DROP TABLE IF EXISTS `tbl_poi_food_map`;

CREATE TABLE `tbl_poi_food_map` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `poiId` bigint(20) NOT NULL COMMENT '门店Id',
  `foodId` varchar(50) DEFAULT NULL COMMENT '餐品ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
