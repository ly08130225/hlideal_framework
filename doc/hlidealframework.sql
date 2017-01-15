/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50134
Source Host           : localhost:3306
Source Database       : xmkjframework

Target Server Type    : MYSQL
Target Server Version : 50134
File Encoding         : 65001

Date: 2016-12-05 13:00:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sysapplication`
-- ----------------------------
DROP TABLE IF EXISTS `sysapplication`;
CREATE TABLE `sysapplication` (
  `id` varchar(64) NOT NULL DEFAULT '',
  `appcode` varchar(64) DEFAULT NULL,
  `appname` varchar(50) DEFAULT NULL,
  `apphost` varchar(100) DEFAULT NULL,
  `appport` varchar(50) DEFAULT NULL,
  `appcontext` varchar(100) DEFAULT NULL,
  `appctx` varchar(100) DEFAULT NULL,
  `appurl` varchar(200) DEFAULT NULL,
  `appstatus` varchar(50) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `deleteflag` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `createid` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createname` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatename` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  `var01` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var02` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var03` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var04` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var05` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysapplication
-- ----------------------------
INSERT INTO `sysapplication` VALUES ('1', '0001', '**系统', null, null, null, null, null, null, null, '0', '1', 'admin', '2016-11-11 19:42:47', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `sysdept`
-- ----------------------------
DROP TABLE IF EXISTS `sysdept`;
CREATE TABLE `sysdept` (
  `id` varchar(64) NOT NULL,
  `parentid` varchar(64) DEFAULT NULL,
  `depttype` varchar(50) DEFAULT NULL COMMENT '岗位类型',
  `deptno` varchar(50) DEFAULT NULL,
  `deptname` varchar(50) DEFAULT NULL,
  `managerid` varchar(64) DEFAULT NULL COMMENT '直属领导',
  `tempmanagerids` varchar(500) DEFAULT NULL,
  `supermanagerid` varchar(64) DEFAULT NULL COMMENT '上级领导',
  `remark` varchar(200) DEFAULT NULL,
  `deleteflag` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `createid` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createname` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatename` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  `var01` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var02` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var03` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var04` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var05` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysdept
-- ----------------------------

-- ----------------------------
-- Table structure for `sysdic`
-- ----------------------------
DROP TABLE IF EXISTS `sysdic`;
CREATE TABLE `sysdic` (
  `id` varchar(64) NOT NULL DEFAULT '',
  `cateid` varchar(64) DEFAULT NULL,
  `catekey` varchar(64) DEFAULT NULL,
  `parentid` varchar(64) DEFAULT NULL,
  `checktree` char(1) DEFAULT NULL,
  `dickey` varchar(50) DEFAULT NULL,
  `dicvalue` varchar(50) DEFAULT NULL,
  `minvalue` varchar(50) DEFAULT NULL,
  `maxvalue` varchar(50) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `deleteflag` char(1) DEFAULT NULL,
  `createid` varchar(50) DEFAULT NULL,
  `createname` varchar(50) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatename` varchar(50) DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  `var01` varchar(50) DEFAULT NULL,
  `var02` varchar(50) DEFAULT NULL,
  `var03` varchar(50) DEFAULT NULL,
  `var04` varchar(50) DEFAULT NULL,
  `var05` varchar(50) DEFAULT NULL,
  `var06` varchar(50) DEFAULT NULL,
  `var07` varchar(50) DEFAULT NULL,
  `var08` varchar(50) DEFAULT NULL,
  `var09` varchar(50) DEFAULT NULL,
  `var10` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysdic
-- ----------------------------
INSERT INTO `sysdic` VALUES ('3564468efd63409d9754ed97cd0b1490', null, 'PROCESSNODE', null, null, '1', '设计组接收', null, null, null, '0', '1', 'admin', '2016-11-17 20:40:35', null, '2016-11-17 20:40:35', '4', null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('ee7fffbc079a4f8cb8b8c6170fbf22d7', null, 'PROCESSNODE', null, null, '2', '设计', null, null, null, '0', '1', 'admin', '2016-11-17 20:43:40', null, '2016-11-17 20:45:10', '4', null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('e84f110448024e9bb11fdfbf8b279d57', null, 'PROCESSNODE', null, null, '3', '打印', null, null, null, '0', '1', 'admin', '2016-11-17 20:45:22', null, '2016-11-17 20:45:22', '4', null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('f2c13ff07d4c4f46b7851fbd28f56ded', null, 'PROCESSNODE', null, null, '4', '仓库发货', null, null, null, '0', '1', 'admin', '2016-11-17 20:45:32', null, '2016-11-17 20:45:32', '4', null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('8f2a707001044345a2af14147c893c25', null, 'PROCESSNODE', null, null, '5', '热升华', null, null, null, '0', '1', 'admin', '2016-11-17 20:45:43', null, '2016-11-17 20:45:43', '4', null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('fa25e5ae10454327bb75b6a118fb16df', null, 'PROCESSNODE', null, null, '6', '单成品审核', null, null, null, '0', '1', 'admin', '2016-11-17 20:45:54', null, '2016-11-17 20:45:54', '4', null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('2bf0c167effa4aaba0401a79ec18cd5a', null, 'PROCESSNODE', null, null, '7', '生产', null, null, null, '0', '1', 'admin', '2016-11-17 20:46:05', null, '2016-11-17 20:46:05', '4', null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('1c7c44c1512445faad034c4b5a38c08b', null, 'PROCESSNODE', null, null, '8', '质检', null, null, null, '0', '1', 'admin', '2016-11-17 20:46:13', null, '2016-11-17 20:46:13', '4', null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('38cad4302de8488f98c50cd29e6fecb1', null, 'PROCESSNODE', null, null, '9', '发货', null, null, null, '0', '1', 'admin', '2016-11-17 20:46:25', null, '2016-11-17 20:48:53', '4', null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('30bdbf0eb6d7464da69b5351b9082a68', null, 'ORDERSOURCE', null, null, '1', '自建平台', null, null, null, '0', '1', 'admin', '2016-11-17 20:52:25', null, '2016-11-17 20:52:25', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('74c2a5aad0274ab5828a5caca725ee7b', null, 'ORDERSOURCE', null, null, '2', '代发货', null, null, null, '0', '1', 'admin', '2016-11-17 20:52:33', null, '2016-11-17 20:52:33', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('b9a447d804de483e838e35cbacf9a522', null, 'ORDERSOURCE', null, null, '3', '第三方平台', null, null, null, '0', '1', 'admin', '2016-11-17 20:52:41', null, '2016-11-17 20:52:41', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('e8ad8f0b648548b98048d66d02382548', null, 'ORDERSOURCE', null, null, '4', '其它', null, null, null, '0', '1', 'admin', '2016-11-17 20:52:48', null, '2016-11-17 20:52:48', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('fd1c74e320f4495385d911b24fbf9f19', null, 'CLASSTYPE', null, null, 'A', '包', null, null, null, '0', '1', 'admin', '2016-11-17 20:54:22', null, '2016-11-17 20:54:22', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('3b8f631837894264b3b0370e844ea2cf', null, 'CLASSTYPE', null, null, 'B', '鞋', null, null, null, '0', '1', 'admin', '2016-11-17 20:54:29', null, '2016-11-17 20:54:29', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('4d840142881b433b9a95d2cb9b8eeceb', null, 'CLASSTYPE', null, null, 'C', '杂类', null, null, null, '0', '1', 'admin', '2016-11-17 20:54:38', null, '2016-11-17 20:54:38', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('b70a33e9441d442dbfbeb3b41fadc1a6', null, 'CLASSTYPE', null, null, 'D', '服装', null, null, null, '0', '1', 'admin', '2016-11-17 20:54:49', null, '2016-11-17 20:59:42', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('2f57ee42b10347349c7b5a7f08ef51f2', null, 'AGENTTYPE', null, null, 'bm', '北门', null, null, null, '0', '1', 'admin', '2016-11-17 20:57:52', null, '2016-11-17 22:06:07', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('867cc4caa58d4249bc2c3c7f62342195', null, 'AGENTTYPE', null, null, 'zgr', '曾广荣', null, null, null, '0', '1', 'admin', '2016-11-17 20:58:04', null, '2016-11-17 20:58:04', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('b12efa5d55c84db68a5b4571415fa544', null, 'AGENTTYPE', null, null, 'CC', 'XX', null, null, null, '0', '1', 'admin', '2016-11-17 20:58:12', null, '2016-11-17 20:58:12', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('098f97dcb4dc44d2a2d4e66579e26ee9', null, 'STRUCTURETYPE', null, null, 'A', '17寸毛毡双肩包', null, null, null, '0', '1', 'admin', '2016-11-17 21:17:32', null, '2016-11-17 22:04:18', '包', 'A', '100', null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('22bbc9e23a5d4bdc9c6427d4fa4f02d6', null, 'STRUCTURETYPE', null, null, 'B', '12寸毛毡包', null, null, null, '0', '1', 'admin', '2016-11-17 22:04:49', null, '2016-11-17 22:04:49', '包', 'A', '100', null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('e5583ccb7e5a4c0f863275a67d4ac8f5', null, 'STRUCTURETYPE', null, null, 'C', '运动鞋', null, null, null, '0', '1', 'admin', '2016-11-17 22:05:04', null, '2016-11-18 11:12:53', '鞋', 'B', '100', null, null, null, null, null, null, null);
INSERT INTO `sysdic` VALUES ('41b05e15a34d4ca4901035c495f0a0d1', null, 'STRUCTURETYPE', null, null, 'D', '童鞋', null, null, null, '0', '1', 'admin', '2016-11-17 22:05:18', null, '2016-11-17 22:05:37', '鞋', 'B', '1000', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `sysdiccate`
-- ----------------------------
DROP TABLE IF EXISTS `sysdiccate`;
CREATE TABLE `sysdiccate` (
  `id` varchar(64) NOT NULL,
  `catekey` varchar(50) DEFAULT NULL,
  `catename` varchar(50) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `deleteflag` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `createid` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createname` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatename` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  `var01` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var02` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var03` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var04` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var05` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysdiccate
-- ----------------------------
INSERT INTO `sysdiccate` VALUES ('1', 'PROCESSNODE', '进度节点', '进度节点', '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysdiccate` VALUES ('2', 'ORDERSOURCE', '订单来源', '订单来源', '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysdiccate` VALUES ('3', 'CLASSTYPE', '类别', '类别', '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysdiccate` VALUES ('4', 'STRUCTURETYPE', '结构档案', '结构档案', '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysdiccate` VALUES ('5', 'AGENTTYPE', '代理商', '代理商', '0', '1', 'admin', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `sysfunction`
-- ----------------------------
DROP TABLE IF EXISTS `sysfunction`;
CREATE TABLE `sysfunction` (
  `id` varchar(64) NOT NULL DEFAULT '',
  `appid` varchar(64) DEFAULT NULL,
  `parentid` varchar(64) DEFAULT NULL,
  `funtionkey` varchar(200) DEFAULT NULL,
  `functionname` varchar(50) DEFAULT NULL,
  `orderno` int(11) DEFAULT NULL,
  `linkurl` varchar(500) DEFAULT NULL,
  `iconlink` varchar(200) DEFAULT NULL,
  `globalcheck` char(1) DEFAULT NULL COMMENT '默认系统管理功能',
  `checkshow` char(1) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `deleteflag` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `createid` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createname` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatename` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  `var01` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var02` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var03` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var04` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var05` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysfunction
-- ----------------------------
INSERT INTO `sysfunction` VALUES ('1', '1', '0', null, '基础数据', '1', '', '', null, '1', '基础数据', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('2', '1', '0', null, '订单管理', '2', '', '', null, '1', '订单管理', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('3', '1', '0', null, '计划管理', '3', '', '', null, '1', '计划管理', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('4', '1', '0', null, '半成品仓管理', '4', '', '', null, '1', '半成品仓管理', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('5', '1', '0', null, '系统管理', '5', '', '', null, '1', '系统管理', '0', null, null, null, null, '2015-09-01 10:58:43', 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('6', '1', '1', null, '进度节点设置', '101', '/admin/backstage/sys/dic/dicmanage01.htm', '', null, '1', '进度节点设置', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('7', '1', '1', null, '订单来源分类', '102', '/admin/backstage/sys/dic/dicmanage02.htm', '', null, '1', '订单来源分类', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('8', '1', '1', null, '类别设置', '103', '/admin/backstage/sys/dic/dicmanage03.htm', '', null, '1', '类别设置', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('9', '1', '1', null, '结构档案', '104', '/admin/backstage/sys/dic/dicmanage04.htm', '', null, '1', '结构档案', '0', null, null, null, null, '2015-09-01 11:03:18', 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('10', '1', '1', null, '代理商管理', '105', '/admin/backstage/sys/dic/dicmanage05.htm', '', null, '1', '产品档案', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('11', '1', '2', null, '订单接口与导入', '201', '', '', null, '1', '订单接口与导入', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('12', '1', '2', null, '销售订单', '202', '', '', null, '1', '销售订单', '0', null, null, null, null, '2015-09-01 11:03:11', 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('13', '1', '3', null, '完工进度汇报', '301', '', '', null, '1', '完工进度汇报', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('14', '1', '3', null, '生产进度查询', '302', '', '', null, '1', '生产进度查询', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('15', '1', '3', null, '交期达成率分析', '303', '', '', null, '1', '交期达成率分析', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('16', '1', '3', null, '订单量统计', '304', '', '', null, '1', '订单量统计', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('17', '1', '4', null, '采购入库', '401', '', '', null, '1', '采购入库', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('18', '1', '4', null, '库存查询', '402', '', '', null, '1', '库存查询', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('19', '1', '5', null, '用户管理', '501', '/admin/backstage/sys/usermanage.htm', '', null, '1', '用户管理', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('20', '1', '5', null, '角色管理', '502', '/admin/backstage/sys/rolemanage.htm', '', null, '1', '角色管理', '0', null, null, null, null, null, 'desktop', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('21', '1', '0', null, '手机端', '6', '', '', null, '1', '手机端', '0', null, null, null, null, null, 'app', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('22', '1', '21', null, '生产进度查看', '601', '', '', null, '1', '生产进度查看', '0', null, null, null, null, null, 'app', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('23', '1', '21', null, '订单交期预警', '602', '', '', null, '1', '订单交期预警', '0', null, null, null, null, null, 'app', null, null, null, null);
INSERT INTO `sysfunction` VALUES ('24', '1', '21', null, '订单量分析', '603', null, null, null, '1', '订单量分析', '0', null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `sysparam`
-- ----------------------------
DROP TABLE IF EXISTS `sysparam`;
CREATE TABLE `sysparam` (
  `id` varchar(64) NOT NULL,
  `paramkey` varchar(50) DEFAULT NULL,
  `paramvalue` varchar(500) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `deleteflag` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `createid` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createname` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatename` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  `var01` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var02` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var03` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var04` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var05` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysparam
-- ----------------------------

-- ----------------------------
-- Table structure for `syspost`
-- ----------------------------
DROP TABLE IF EXISTS `syspost`;
CREATE TABLE `syspost` (
  `id` varchar(64) NOT NULL,
  `posttype` varchar(50) DEFAULT NULL COMMENT '岗位类型',
  `postno` varchar(50) DEFAULT NULL,
  `postname` varchar(50) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `deleteflag` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `createid` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createname` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatename` varchar(50) CHARACTER SET latin1 DEFAULT '',
  `updatedate` datetime DEFAULT NULL,
  `var01` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var02` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var03` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var04` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var05` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syspost
-- ----------------------------

-- ----------------------------
-- Table structure for `syspoststaff`
-- ----------------------------
DROP TABLE IF EXISTS `syspoststaff`;
CREATE TABLE `syspoststaff` (
  `id` varchar(64) NOT NULL DEFAULT '',
  `postid` varchar(64) DEFAULT NULL,
  `staffid` varchar(64) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `deleteflag` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `createid` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createname` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatename` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  `var01` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var02` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var03` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var04` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var05` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syspoststaff
-- ----------------------------

-- ----------------------------
-- Table structure for `sysrole`
-- ----------------------------
DROP TABLE IF EXISTS `sysrole`;
CREATE TABLE `sysrole` (
  `id` varchar(64) NOT NULL DEFAULT '',
  `roleno` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `deleteflag` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `createid` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createname` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatename` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  `var01` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var02` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var03` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var04` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var05` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysrole
-- ----------------------------
INSERT INTO `sysrole` VALUES ('1', '001', '管理员', '管理员', '0', '1', 'admin', '2015-08-28 15:12:41', 'admin', '2016-11-16 20:32:06', null, null, null, null, null);
INSERT INTO `sysrole` VALUES ('a0e924c75f8c4b1bbc0735cb5a7d4ab1', null, '测试01', '测试01', '0', '1', 'admin', '2016-11-16 20:32:16', null, '2016-11-16 20:49:38', null, null, null, null, null);
INSERT INTO `sysrole` VALUES ('63c470f86b824ca09323378c9d0681dd', null, '测试02', '测试02', '0', '1', 'admin', '2016-11-16 20:46:00', null, '2016-11-16 20:48:22', null, null, null, null, null);
INSERT INTO `sysrole` VALUES ('50eb4871dfb0422298c16de0ad8fae6e', null, '测试03', '测试03', '0', '1', 'admin', '2016-11-16 20:48:45', null, '2016-11-16 20:48:54', null, null, null, null, null);
INSERT INTO `sysrole` VALUES ('3358830672454264a735a0bdecb80f07', null, '111', '111', '0', '1', 'admin', '2016-11-16 20:50:32', null, '2016-11-16 20:50:32', null, null, null, null, null);
INSERT INTO `sysrole` VALUES ('6f1a2ed2fedc4e9f84cf9a6ce433a72f', null, '22', '22', '0', '1', 'admin', '2016-11-16 20:50:35', null, '2016-11-16 20:50:35', null, null, null, null, null);
INSERT INTO `sysrole` VALUES ('64eed7edae284460a947c020f6b3d838', null, '33', '33', '0', '1', 'admin', '2016-11-16 20:50:40', null, '2016-11-16 20:50:40', null, null, null, null, null);
INSERT INTO `sysrole` VALUES ('d3d15526f3aa4890af9f706bd8fe1843', null, '88', '88', '0', '1', 'admin', '2016-11-16 20:51:16', null, '2016-11-16 20:51:16', null, null, null, null, null);
INSERT INTO `sysrole` VALUES ('f9b7e86fb9034d1c85e9b6e814b505ee', null, '99', '99', '0', '1', 'admin', '2016-11-16 20:51:20', null, '2016-11-16 20:51:20', null, null, null, null, null);
INSERT INTO `sysrole` VALUES ('d5c85cfc42dd422f81f30e03fd16b2ad', null, '11111', '11111', '0', '1', 'admin', '2016-11-16 20:51:24', null, '2016-11-16 20:51:24', null, null, null, null, null);
INSERT INTO `sysrole` VALUES ('7b9f4780c17847ddb9f199f40d5df7d2', null, '33333', '444444', '0', '1', 'admin', '2016-11-16 20:51:30', null, '2016-11-16 20:51:30', null, null, null, null, null);

-- ----------------------------
-- Table structure for `sysrolefunction`
-- ----------------------------
DROP TABLE IF EXISTS `sysrolefunction`;
CREATE TABLE `sysrolefunction` (
  `id` varchar(64) NOT NULL,
  `roleid` varchar(64) DEFAULT '',
  `functionid` varchar(64) DEFAULT '',
  `appid` varchar(64) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `deleteflag` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `createid` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createname` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatename` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  `var01` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var02` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var03` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var04` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var05` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sysrolefunction
-- ----------------------------
INSERT INTO `sysrolefunction` VALUES ('1', '1', '1', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('2', '1', '2', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('3', '1', '3', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('4', '1', '4', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('5', '1', '5', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('6', '1', '6', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('7', '1', '7', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('8', '1', '8', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('9', '1', '9', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('10', '1', '10', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('11', '1', '11', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('12', '1', '12', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('13', '1', '13', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('14', '1', '14', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('15', '1', '15', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('16', '1', '16', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('17', '1', '17', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('18', '1', '18', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('19', '1', '19', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('20', '1', '20', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('21', '1', '21', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('22', '1', '22', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);
INSERT INTO `sysrolefunction` VALUES ('23', '1', '23', '1', null, '0', '1', 'admin', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `sysstaff`
-- ----------------------------
DROP TABLE IF EXISTS `sysstaff`;
CREATE TABLE `sysstaff` (
  `id` varchar(64) NOT NULL,
  `loginname` varchar(50) NOT NULL,
  `loginpwd` varchar(100) DEFAULT NULL,
  `staffno` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `deptid` varchar(64) DEFAULT NULL,
  `deptname` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `hostip` varchar(50) DEFAULT NULL,
  `mobilephone` varchar(50) DEFAULT NULL,
  `connectphone` varchar(50) DEFAULT NULL,
  `adminstatus` char(1) DEFAULT NULL,
  `loginip` varchar(50) DEFAULT NULL,
  `logindate` datetime DEFAULT NULL,
  `loginflag` char(1) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `deleteflag` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `createid` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createname` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatename` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  `var01` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var02` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var03` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var04` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `var05` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysstaff
-- ----------------------------
INSERT INTO `sysstaff` VALUES ('1', 'admin', '96e79218965eb72c92a549dd5a330112', '001', '系统管理员', '1', '管理部', '0', null, 'younger@163.com', null, '13845147414', '023-73321451', '1', '127.0.0.1', '2016-05-17 16:37:46', '1', null, '0', '1', 'admin', '2015-08-28 15:11:04', 'admin', '2015-08-28 15:11:07', null, null, null, null, null);

-- ----------------------------
-- Table structure for `sysstaffdic`
-- ----------------------------
DROP TABLE IF EXISTS `sysstaffdic`;
CREATE TABLE `sysstaffdic` (
  `id` varchar(64) NOT NULL,
  `staffid` varchar(64) DEFAULT NULL COMMENT '岗位类型',
  `dictype` varchar(50) DEFAULT NULL,
  `dicvalue` varchar(2000) DEFAULT NULL,
  `dictext` varchar(2000) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `deleteflag` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `createid` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createname` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatename` varchar(50) CHARACTER SET latin1 DEFAULT '',
  `updatedate` datetime DEFAULT NULL,
  `var01` varchar(50) DEFAULT NULL,
  `var02` varchar(50) CHARACTER SET ujis DEFAULT NULL,
  `var03` varchar(50) DEFAULT NULL,
  `var04` varchar(50) DEFAULT NULL,
  `var05` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysstaffdic
-- ----------------------------
INSERT INTO `sysstaffdic` VALUES ('bdd59aeff101409a8de7430787bb71d5', '9f647461f5b94ffab4062421cb4940f0', 'PROCESSNODE', '1,2,3', '设计组接收,设计,打印', null, '0', '1', 'admin', '2016-11-20 21:13:59', null, '2016-11-20 21:14:08', null, null, null, null, null);
INSERT INTO `sysstaffdic` VALUES ('7d37f6d260ec452d938c8e39bdc4ca4d', '9f647461f5b94ffab4062421cb4940f0', 'CLASSTYPE', 'A,C', '包,杂类', null, '0', '1', 'admin', '2016-11-20 21:18:48', null, '2016-11-20 21:18:48', null, null, null, null, null);
INSERT INTO `sysstaffdic` VALUES ('94cfdaa13a3b4743baf803c39ba33ced', '9f647461f5b94ffab4062421cb4940f0', 'ORDERSOURCE', '1,3', '自建平台,第三方平台', null, '0', '1', 'admin', '2016-11-20 21:18:52', null, '2016-11-20 21:18:52', null, null, null, null, null);
INSERT INTO `sysstaffdic` VALUES ('ccb3d86791fd47b68dd7ddbd6ba53585', '9f647461f5b94ffab4062421cb4940f0', 'AGENTTYPE', 'zgr', '曾广荣', null, '0', '1', 'admin', '2016-11-20 21:18:54', null, '2016-11-20 21:18:54', null, null, null, null, null);
INSERT INTO `sysstaffdic` VALUES ('a458424743a646b1a0ed188f40c72cf1', '1', 'CLASSTYPE', 'A,B,C', '包,鞋,杂类', null, '0', '1', 'admin', '2016-11-20 21:19:58', null, '2016-11-20 21:20:19', null, null, null, null, null);

-- ----------------------------
-- Table structure for `sysstaffrole`
-- ----------------------------
DROP TABLE IF EXISTS `sysstaffrole`;
CREATE TABLE `sysstaffrole` (
  `staffid` varchar(64) NOT NULL DEFAULT '',
  `roleid` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`staffid`,`roleid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysstaffrole
-- ----------------------------
INSERT INTO `sysstaffrole` VALUES ('1', '1');
