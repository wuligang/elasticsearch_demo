/*
Navicat MySQL Data Transfer

Source Server         : docker
Source Server Version : 50728
Source Host           : 192.168.52.128:3308
Source Database       : article

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2019-11-22 17:29:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `titleId` int(10) NOT NULL DEFAULT '0',
  `title` varchar(225) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `author` char(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `content` text CHARACTER SET utf8,
  `publishDate` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '今天天气不错', 'zfeig', '测试帖哦', '1448272110');
INSERT INTO `article` VALUES ('2', '昨天立冬，天气依旧炎热', 'admin', '昨天立冬，天气依旧炎热，立冬日，广州依旧高温，入冬多次失败！', '1448272218');
INSERT INTO `article` VALUES ('3', '如何在linux下部署node.js', 'zfeig', '如何在linux下部署node.js，docker应用越来越广泛，本章讨论如何在linux下搭建docker环境，安装node运行环境', '1448272447');
INSERT INTO `article` VALUES ('5', '生命在于运动，锻炼小结', 'admin', '生命在于运动，锻炼小结，健康的身体离不开日常的锻炼', '1448272557');
INSERT INTO `article` VALUES ('6', '如何提高学习效率', '王大崔', '如何提高学习效率，论提高学习效率的重要性', '1448272652');
INSERT INTO `article` VALUES ('7', '大圣归来，国产动漫复出之作', '孙大圣', '大圣归来，国产动漫复出之作，大圣归来票房热卖，标志着国产电影与欧美制作水平之间的距离逐步缩小，追赶指路越来越紧！', '1448272898');
INSERT INTO `article` VALUES ('8', '关于住房公积金的管理', 'admin', '关于住房公积金的管理，由于新的政策发布，住房公积金政策继续调整，这将会日益影响一些异地工作打算回家置业的人员，这里为您详细介绍下公积金相关知识', '1448333293');
INSERT INTO `article` VALUES ('9', '网页爬虫的反向代理研究', 'zfeig', '网页爬虫的反向代理研究，网页爬虫，如何皮面被限制ip，提高反爬能力', '1448347207');
INSERT INTO `article` VALUES ('10', '土耳其袭击俄罗斯\"战机\"', 'admin', '土耳其袭击俄罗斯\"战机\"，距最新消息，俄罗斯一家苏25轰炸机在利比亚执行空袭任务时，被土耳其空军空空导弹袭击不幸坠毁。', '1448503767');
INSERT INTO `article` VALUES ('11', '春运火车票即将开售', 'zfeig', '春运火车票即将开售，春运将至，春运火车票即将开始，预计新一轮的客流高峰将到来，希望大家做好准备！', '1448504226');
INSERT INTO `article` VALUES ('12', '年底大优惠，一大波特品大卖', 'lv\'s loveer', '年底大优惠，一大波特品大卖，又到年关，各种压力即将来临，如何“化解”又该如何准备？本次大会展帮您排忧解难！', '1448504562');
INSERT INTO `article` VALUES ('13', '谁便写写', 'zfeig', '谁便写写，有时候会很无聊，只是喜欢发牢骚，随便写写会更好！！', '1448504855');
INSERT INTO `article` VALUES ('14', '最新理财资讯大放送', 'admin', '最新理财资讯大放送！随着互联网金融的日益火爆！互联网理财也越来越火爆，小编为您推荐靠谱p2p理财机构！', '1448505149');
