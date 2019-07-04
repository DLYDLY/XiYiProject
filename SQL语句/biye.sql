/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.38 : Database - biye_lxk
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`biye_lxk` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `biye_lxk`;

/*Table structure for table `biye_admin` */

DROP TABLE IF EXISTS `biye_admin`;

CREATE TABLE `biye_admin` (
  `adminId` varchar(20) NOT NULL COMMENT '管理员编号',
  `adminName` varchar(20) NOT NULL,
  `adminPassword` varchar(20) NOT NULL COMMENT '管理员密码',
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `biye_admin` */

/*Table structure for table `biye_admin_role` */

DROP TABLE IF EXISTS `biye_admin_role`;

CREATE TABLE `biye_admin_role` (
  `aRId` varchar(20) NOT NULL COMMENT '管理员_权限编号',
  `adminId` varchar(20) NOT NULL COMMENT '管理员id',
  `roleId` varchar(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`aRId`),
  KEY `fk_admin` (`adminId`),
  KEY `fk_roles` (`roleId`),
  CONSTRAINT `fk_admin` FOREIGN KEY (`adminId`) REFERENCES `biye_admin` (`adminId`),
  CONSTRAINT `fk_roles` FOREIGN KEY (`roleId`) REFERENCES `biye_roles` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `biye_admin_role` */

/*Table structure for table `biye_comment` */

DROP TABLE IF EXISTS `biye_comment`;

CREATE TABLE `biye_comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `commentDesc` varchar(100) NOT NULL COMMENT '评论的内容',
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `biye_comment` */

/*Table structure for table `biye_grace` */

DROP TABLE IF EXISTS `biye_grace`;

CREATE TABLE `biye_grace` (
  `gId` int(11) NOT NULL AUTO_INCREMENT,
  `graceName` varchar(20) NOT NULL COMMENT '用户等级',
  PRIMARY KEY (`gId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `biye_grace` */

/*Table structure for table `biye_news` */

DROP TABLE IF EXISTS `biye_news`;

CREATE TABLE `biye_news` (
  `newsID` varchar(50) NOT NULL COMMENT '新闻编号',
  `newsTitle` varchar(50) NOT NULL COMMENT '新闻标题',
  `newsContent` text NOT NULL COMMENT '新闻内容',
  `newsDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发布时间',
  `newsTypeId` varchar(50) DEFAULT NULL COMMENT '新闻类别编号',
  `newslsTop` int(11) DEFAULT '0' COMMENT '0为不置顶，1位置顶',
  `state` int(11) DEFAULT '0' COMMENT '0为存在的数据，1位删除的数据',
  `userId` int(11) NOT NULL COMMENT '新闻所属user的id',
  PRIMARY KEY (`newsID`),
  KEY `fk_news_type` (`newsTypeId`),
  KEY `fk_news_user` (`userId`),
  CONSTRAINT `fk_news_type` FOREIGN KEY (`newsTypeId`) REFERENCES `biye_news_type` (`typeId`),
  CONSTRAINT `fk_news_user` FOREIGN KEY (`userId`) REFERENCES `biye_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `biye_news` */

/*Table structure for table `biye_news_image` */

DROP TABLE IF EXISTS `biye_news_image`;

CREATE TABLE `biye_news_image` (
  `imageId` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片编号',
  `imagePath` varchar(100) NOT NULL COMMENT '图片地址',
  `newsId` varchar(50) DEFAULT NULL COMMENT '所属的新闻id',
  PRIMARY KEY (`imageId`),
  KEY `fk_image_news` (`newsId`),
  CONSTRAINT `fk_image_news` FOREIGN KEY (`newsId`) REFERENCES `biye_news` (`newsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `biye_news_image` */

/*Table structure for table `biye_news_type` */

DROP TABLE IF EXISTS `biye_news_type`;

CREATE TABLE `biye_news_type` (
  `typeId` varchar(50) NOT NULL DEFAULT '',
  `typeName` varchar(50) NOT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `biye_news_type` */

/*Table structure for table `biye_roles` */

DROP TABLE IF EXISTS `biye_roles`;

CREATE TABLE `biye_roles` (
  `roleId` varchar(20) NOT NULL DEFAULT '',
  `roleName` varchar(20) NOT NULL COMMENT '权限名称',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `biye_roles` */

/*Table structure for table `biye_user` */

DROP TABLE IF EXISTS `biye_user`;

CREATE TABLE `biye_user` (
  `userId` int(11) NOT NULL DEFAULT '0' COMMENT '用户表id',
  `username` varchar(50) NOT NULL COMMENT '用户名称',
  `password` varchar(32) NOT NULL COMMENT '用户密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '用户手机号码',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `created` datetime NOT NULL COMMENT '创建用户的时间',
  `updated` datetime NOT NULL COMMENT '修改用户的时间',
  `graceId` int(11) NOT NULL DEFAULT '1' COMMENT '1为用户，2为记者',
  PRIMARY KEY (`userId`),
  KEY `fk_user_grace` (`graceId`),
  CONSTRAINT `fk_user_grace` FOREIGN KEY (`graceId`) REFERENCES `biye_grace` (`gId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `biye_user` */

/*Table structure for table `biye_user_comment` */

DROP TABLE IF EXISTS `biye_user_comment`;

CREATE TABLE `biye_user_comment` (
  `uCId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户_评论表的id',
  `userId` int(11) NOT NULL COMMENT '用户的id',
  `commentId` int(11) NOT NULL COMMENT '评论id',
  PRIMARY KEY (`uCId`),
  KEY `fk_user_comment` (`userId`),
  KEY `fk_comment_user` (`commentId`),
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`commentId`) REFERENCES `biye_comment` (`commentId`),
  CONSTRAINT `fk_user_comment` FOREIGN KEY (`userId`) REFERENCES `biye_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `biye_user_comment` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
