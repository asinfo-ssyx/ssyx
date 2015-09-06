/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : ssyx

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2014-11-23 02:42:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `active_info`
-- ----------------------------
DROP TABLE IF EXISTS `active_info`;
CREATE TABLE `active_info` (
  `seq_no` int(11) NOT NULL AUTO_INCREMENT,
  `active_name` varchar(100) DEFAULT NULL,
  `active_code` varchar(50) DEFAULT NULL,
  `b_active_code` varchar(50) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `begin_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `end_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `create_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `user_type` varchar(50) DEFAULT NULL,
  `active_ms_type` varchar(50) DEFAULT NULL COMMENT '1产品推荐2内容推荐',
  `active_type` varchar(50) DEFAULT NULL COMMENT '1实时2非实时',
  `active_title` varchar(1000) DEFAULT NULL,
  `acitve_scene` int(2) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `use_id` varchar(20) DEFAULT NULL,
  `city_id` varchar(20) DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`seq_no`)
) ENGINE=InnoDB AUTO_INCREMENT=350 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of active_info
-- ----------------------------
INSERT INTO `active_info` VALUES ('276', '123', 'active_201408281129401', null, '0', '2014-08-20 11:29:34', '2014-08-31 11:29:37', '2014-08-28 11:29:40', '数据部', null, '2', '123			', '5', null, null, null, '2', null);
INSERT INTO `active_info` VALUES ('277', '123', 'active_201408281137351', null, '0', '2014-08-14 11:37:31', '2014-08-31 11:37:32', '2014-08-28 11:37:35', '数据部', null, '2', '	123		', '5', null, null, null, '2', null);
INSERT INTO `active_info` VALUES ('278', '123', 'active_201408281139321', null, '0', '2014-08-01 11:39:27', '2014-08-31 11:39:29', '2014-08-28 11:39:32', '数据部', null, '2', '123			', '4', null, null, null, '2', null);
INSERT INTO `active_info` VALUES ('279', '123', 'active_201408281153041', null, '9', '2014-08-08 11:53:00', '2014-08-31 11:53:02', '2014-08-28 11:53:04', '数据部', null, '2', '		123	', '5', null, null, null, '3', null);
INSERT INTO `active_info` VALUES ('280', '123', 'active_201408281153472', null, '9', '2014-08-02 11:53:43', '2014-08-31 11:53:45', '2014-08-28 11:53:47', '数据部', null, '1', '123			', '2', null, null, null, '3', null);
INSERT INTO `active_info` VALUES ('281', '123', 'active_201408281154213', null, '2', '2014-08-15 11:54:16', '2014-08-31 11:54:18', '2014-08-28 11:54:21', '数据部', null, '2', '23			', '4', null, null, null, '4', null);
INSERT INTO `active_info` VALUES ('282', '11', 'active_201408281452294', '', '9', '2014-08-08 14:52:23', '2014-08-31 14:52:25', '2014-08-28 14:52:29', '市场部', '', '2', '1			', '6', null, '', null, '4', null);
INSERT INTO `active_info` VALUES ('283', '12', 'active_201409011129321', null, '0', '2014-09-01 11:29:26', '2014-09-29 11:29:29', '2014-09-01 11:29:32', '数据部', null, '1', '12			', '1', null, null, null, '11', null);
INSERT INTO `active_info` VALUES ('284', '123', 'active_201409011437131', null, '0', '2014-09-01 14:37:05', '2014-09-30 14:37:08', '2014-09-01 14:37:13', '数据部', null, '1', '123			', '1', null, null, null, '11', null);
INSERT INTO `active_info` VALUES ('285', '111', 'active_201409011443422', null, '0', '2014-09-01 14:43:35', '2014-09-30 14:43:38', '2014-09-01 14:43:42', '数据部', null, '1', '111			', '1', null, null, null, '11', null);
INSERT INTO `active_info` VALUES ('286', '123', 'active_201409111604211', null, '0', '2014-09-04 16:04:16', '2014-09-14 16:04:18', '2014-09-11 16:04:21', '数据部', null, '2', '123			', '5', null, null, '11', '5', null);
INSERT INTO `active_info` VALUES ('287', '123', 'active_201409111616351', null, '0', '2014-09-04 16:16:30', '2014-09-29 16:16:32', '2014-09-11 16:16:35', '数据部', null, '2', '12			', '5', null, null, '11', '6', null);
INSERT INTO `active_info` VALUES ('288', '123', 'active_201409111619422', null, '0', '2014-09-03 16:19:35', '2014-09-29 16:19:37', '2014-09-11 16:19:42', '数据部', null, '2', '123			', '5', null, null, '1', '7', null);
INSERT INTO `active_info` VALUES ('289', '123', 'active_201409111622231', '', '9', '2014-09-04 16:22:13', '2014-09-22 16:22:15', '2014-09-11 16:22:23', '数据部', '', '2', '123			', '5', null, '', '1', '5', null);
INSERT INTO `active_info` VALUES ('290', 'XXX', 'active_201409130451341', null, '0', '2014-09-04 04:51:11', '2014-09-18 04:51:13', '2014-09-13 04:51:34', '数据部', null, '1', 'XXX			', '1', null, null, '1', '6', null);
INSERT INTO `active_info` VALUES ('291', '1', 'active_201409151128281', null, '0', '2014-09-01 11:28:19', '2014-09-30 11:28:25', '2014-09-15 11:28:28', '数据部', null, '2', '1			', '11', null, null, '11', '7', null);
INSERT INTO `active_info` VALUES ('292', '1', 'active_201409151153262', null, '0', '2014-09-04 11:53:21', '2014-09-22 11:53:23', '2014-09-15 11:53:26', '数据部', null, '1', '1			', '1', null, null, '1', '8', null);
INSERT INTO `active_info` VALUES ('293', '123', 'active_201409151438143', null, '0', '2014-09-11 14:38:09', '2014-09-29 14:38:10', '2014-09-15 14:38:14', '数据部', null, '2', '123			', '12', null, null, '1', '8', null);
INSERT INTO `active_info` VALUES ('294', '12', 'active_201409151439291', null, '0', '2014-09-10 14:39:23', '2014-09-23 14:39:25', '2014-09-15 14:39:29', '数据部', null, '2', '1			', '11', null, null, '1', '8', null);
INSERT INTO `active_info` VALUES ('295', '1', 'active_201409151442451', null, '0', '2014-09-11 14:42:40', '2014-09-22 14:42:42', '2014-09-15 14:42:45', '数据部', null, '2', '1			', '12', null, null, '1', '9', null);
INSERT INTO `active_info` VALUES ('296', '123', 'active_201409151444322', null, '0', '2014-09-05 14:44:28', '2014-09-22 14:44:29', '2014-09-15 14:44:32', '数据部', null, '2', '123			', '11', null, null, '1', '9', null);
INSERT INTO `active_info` VALUES ('297', '123', 'active_201409151503133', null, '0', '2014-09-05 15:03:08', '2014-09-22 15:03:10', '2014-09-15 15:03:13', '数据部', null, '2', '123			', '11', null, null, '1', '9', null);
INSERT INTO `active_info` VALUES ('298', '123', 'active_201409151638211', null, '0', '2014-09-04 16:38:17', '2014-09-30 16:38:19', '2014-09-15 16:38:21', '数据部', null, '2', '123			', '11', null, null, '1', '9', null);
INSERT INTO `active_info` VALUES ('299', '123', 'active_201409161659201', null, '0', '2014-09-04 16:59:16', '2014-09-22 16:59:18', '2014-09-16 16:59:20', '数据部', null, '2', '123			', '12', null, null, '1', '10', null);
INSERT INTO `active_info` VALUES ('300', '123', 'active_201409171553411', null, '0', '2014-09-03 15:53:33', '2014-09-24 15:53:35', '2014-09-17 15:53:41', '数据部', null, '1', '123			', '1', null, null, '1', '12', null);
INSERT INTO `active_info` VALUES ('301', 'sdsa', 'active_201409171553592', null, '0', '2014-09-03 15:53:33', '2014-09-24 15:53:35', '2014-09-17 15:53:59', '数据部', null, '2', '11			', '11', null, null, '1', '12', null);
INSERT INTO `active_info` VALUES ('302', '123', 'active_201409171710561', null, '0', '2014-09-11 17:10:50', '2014-09-23 17:10:52', '2014-09-17 17:10:56', '数据部', null, '1', '123			', '2', null, null, '1', '13', null);
INSERT INTO `active_info` VALUES ('303', '123', 'active_201409171711132', null, '0', '2014-09-11 17:11:08', '2014-09-22 17:11:10', '2014-09-17 17:11:13', '数据部', null, '2', '123			', '12', null, null, '1', '14', null);
INSERT INTO `active_info` VALUES ('304', '123', 'active_201409181438282', null, '0', '2014-09-04 14:36:58', '2014-09-23 14:37:00', '2014-09-18 14:38:28', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '14', 'yangsy');
INSERT INTO `active_info` VALUES ('305', '123', 'active_201409221010001', null, '0', '2014-09-05 10:09:55', '2014-09-23 10:09:57', '2014-09-22 10:10:00', '数据部', null, '2', '123			', '11', null, null, 'yangsy', '15', 'yangsy');
INSERT INTO `active_info` VALUES ('306', '123', 'active_201409221126211', null, '0', '2014-09-03 11:26:15', '2014-09-29 11:26:17', '2014-09-22 11:26:21', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '15', 'yangsy');
INSERT INTO `active_info` VALUES ('307', '12', 'active_201409221427171', null, '0', '2014-09-05 14:27:13', '2014-09-30 14:27:15', '2014-09-22 14:27:17', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '16', 'yangsy');
INSERT INTO `active_info` VALUES ('308', '11', 'active_201409221456491', null, '0', '2014-09-05 14:56:44', '2014-09-29 14:56:45', '2014-09-22 14:56:49', '数据部', null, '1', '11			', '2', null, null, 'yangsy', '16', 'yangsy');
INSERT INTO `active_info` VALUES ('309', '123', 'active_201409231108581', null, '0', '2014-09-11 11:08:52', '2014-09-30 11:08:55', '2014-09-23 11:08:58', '数据部', null, '1', '11			', '1', null, null, 'yangsy', '16', 'yangsy');
INSERT INTO `active_info` VALUES ('310', '123', 'active_201409231112211', null, '0', '2014-09-11 11:12:16', '2014-09-30 11:12:18', '2014-09-23 11:12:21', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '16', 'yangsy');
INSERT INTO `active_info` VALUES ('311', '123', 'active_201409231116541', null, '0', '2014-09-10 11:16:49', '2014-09-30 11:16:51', '2014-09-23 11:16:54', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '17', 'yangsy');
INSERT INTO `active_info` VALUES ('312', 'q', 'active_201409231119151', null, '0', '2014-09-16 11:19:10', '2014-09-30 11:19:12', '2014-09-23 11:19:15', '数据部', null, '1', 'q			', '1', null, null, 'yangsy', '17', 'yangsy');
INSERT INTO `active_info` VALUES ('313', '1', 'active_201409231508012', null, '0', '2014-09-11 15:07:57', '2014-09-30 15:07:58', '2014-09-23 15:08:01', '数据部', null, '1', '1			', '1', null, null, 'yangsy', '17', 'yangsy');
INSERT INTO `active_info` VALUES ('314', '1', 'active_201409231523023', null, '0', '2014-09-02 15:22:58', '2014-09-30 15:22:59', '2014-09-23 15:23:02', '数据部', null, '1', '1			', '1', null, null, '11', '18', '11');
INSERT INTO `active_info` VALUES ('315', '123', 'active_201409231539411', null, '0', '2014-09-10 15:39:37', '2014-09-30 15:39:38', '2014-09-23 15:39:41', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '18', 'yangsy');
INSERT INTO `active_info` VALUES ('316', '123', 'active_201409231620301', null, '0', '2014-09-10 16:20:24', '2014-09-30 16:20:26', '2014-09-23 16:20:30', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '17', 'yangsy');
INSERT INTO `active_info` VALUES ('317', '123', 'active_201409231714431', null, '0', '2014-09-17 17:14:37', '2014-09-30 17:14:39', '2014-09-23 17:14:43', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '18', 'yangsy');
INSERT INTO `active_info` VALUES ('318', '123', 'active_201409231723232', null, '0', '2014-09-17 17:23:17', '2014-09-30 17:23:19', '2014-09-23 17:23:23', '数据部', null, '2', '123			', '11', null, null, 'yangsy', '20', 'yangsy');
INSERT INTO `active_info` VALUES ('319', '12321', 'active_201409231730433', null, '0', '2014-09-17 17:30:37', '2014-09-29 17:30:39', '2014-09-23 17:30:43', '数据部', null, '2', '123123			', '11', null, null, 'yangsy', '20', 'yangsy');
INSERT INTO `active_info` VALUES ('320', '123', 'active_201409231736411', null, '0', '2014-09-17 17:36:35', '2014-09-29 17:36:37', '2014-09-23 17:36:41', '数据部', null, '2', '123			', '12', null, null, 'yangsy', '20', 'yangsy');
INSERT INTO `active_info` VALUES ('321', '123', 'active_201409231752522', null, '0', '2014-09-17 17:52:47', '2014-09-30 17:52:49', '2014-09-23 17:52:52', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '5', 'yangsy');
INSERT INTO `active_info` VALUES ('322', '123', 'active_201409231801281', null, '0', '2014-09-17 18:01:20', '2014-09-30 18:01:22', '2014-09-23 18:01:28', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '5', 'yangsy');
INSERT INTO `active_info` VALUES ('323', '123', 'active_201409231808392', null, '0', '2014-09-03 18:08:34', '2014-09-30 18:08:36', '2014-09-23 18:08:39', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '6', 'yangsy');
INSERT INTO `active_info` VALUES ('324', '测试', 'active_201409231840521', null, '0', '2014-09-18 18:40:45', '2014-09-30 18:40:46', '2014-09-23 18:40:52', '数据部', null, '2', '123			', '11', null, null, 'yangsy', '6', 'yangsy');
INSERT INTO `active_info` VALUES ('325', '123', 'active_201409231841412', null, '0', '2014-09-10 18:41:33', '2014-09-30 18:41:35', '2014-09-23 18:41:41', '数据部', null, '1', '123		', '2', null, null, 'yangsy', '6', 'yangsy');
INSERT INTO `active_info` VALUES ('326', '123', 'active_201409231842153', null, '0', '2014-09-11 18:42:11', '2014-09-30 18:42:12', '2014-09-23 18:42:15', '数据部', null, '2', '123			', '11', null, null, 'yangsy', '7', 'yangsy');
INSERT INTO `active_info` VALUES ('327', '132', 'active_201409240955461', null, '0', '2014-09-11 09:55:41', '2014-09-30 09:55:43', '2014-09-24 09:55:46', '数据部', null, '1', '123			', '1', null, null, '11', '7', '11');
INSERT INTO `active_info` VALUES ('328', '11', 'active_201409240957061', null, '0', '2014-09-17 09:57:00', '2014-09-29 09:57:02', '2014-09-24 09:57:06', '数据部', null, '1', '1			', '1', null, null, '11', '7', '11');
INSERT INTO `active_info` VALUES ('329', '123', 'active_201409241012151', null, '0', '2014-09-17 10:12:09', '2014-09-30 10:12:11', '2014-09-24 10:12:15', '数据部', null, '2', '123			', '11', null, null, '11', '7', '11');
INSERT INTO `active_info` VALUES ('330', '13', 'active_201409242022421', null, '0', '2014-09-09 20:22:38', '2014-09-30 20:22:40', '2014-09-24 20:22:42', '数据部', null, '1', '123			', '1', null, null, '11', '7', '11');
INSERT INTO `active_info` VALUES ('331', '123', 'active_201409291138121', null, '0', '2014-09-04 11:38:08', '2014-09-30 11:38:10', '2014-09-29 11:38:12', '数据部', null, '2', '123			', '4', null, null, 'yangsy', '3', 'yangsy');
INSERT INTO `active_info` VALUES ('332', '测试活动', 'active_201410200958161', '', '2', '2014-10-17 09:58:10', '2014-10-29 09:58:12', '2014-10-20 09:58:16', '数据部', '', '1', '123123			', '1', null, '', 'yangsy', '3', 'yangsy');
INSERT INTO `active_info` VALUES ('333', '123', 'active_201410221714071', null, '0', '2014-10-07 17:14:02', '2014-10-29 17:14:03', '2014-10-22 17:14:07', '数据部', null, '2', '123			', '5', null, null, 'yangsy', '3', 'yangsy');
INSERT INTO `active_info` VALUES ('334', '123', 'active_201410231054101', '', '5', '2014-10-10 10:54:04', '2014-10-29 10:54:07', '2014-10-23 10:54:10', '数据部', '', '1', '123			', '1', null, '', 'yangsy', '3', 'yangsy');
INSERT INTO `active_info` VALUES ('335', '123', 'active_201410291043221', null, '0', '2014-10-23 10:43:17', '2014-10-30 10:43:19', '2014-10-29 10:43:22', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '4', 'yangsy');
INSERT INTO `active_info` VALUES ('336', '123', 'active_201410291050291', null, '0', '2014-10-30 10:50:24', '2014-10-31 10:50:26', '2014-10-29 10:50:29', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '4', 'yangsy');
INSERT INTO `active_info` VALUES ('337', '123', 'active_201410291123512', null, '0', '2014-10-31 11:23:46', '2014-10-31 11:23:48', '2014-10-29 11:23:51', '数据部', null, '2', '123			', '4', null, null, 'yangsy', '4', 'yangsy');
INSERT INTO `active_info` VALUES ('338', '123', 'active_201410291127313', null, '0', '2014-10-25 11:27:27', '2014-10-31 11:27:28', '2014-10-29 11:27:31', '数据部', null, '1', '123			', '2', null, null, 'yangsy', '2', 'yangsy');
INSERT INTO `active_info` VALUES ('339', '123', 'active_201410291153454', null, '0', '2014-10-16 11:53:41', '2014-10-31 11:53:43', '2014-10-29 11:53:45', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '2', 'yangsy');
INSERT INTO `active_info` VALUES ('340', '111', 'active_201410291448105', null, '0', '2014-10-30 14:48:05', '2014-10-31 14:48:07', '2014-10-29 14:48:10', '数据部', null, '1', '11			', '1', null, null, 'yangsy', '2', 'yangsy');
INSERT INTO `active_info` VALUES ('341', '123', 'active_201410291456446', null, '0', '2014-10-15 14:56:39', '2014-10-30 14:56:41', '2014-10-29 14:56:44', '数据部', null, '1', '	123		', '1', null, null, 'yangsy', '2', 'yangsy');
INSERT INTO `active_info` VALUES ('342', '123', 'active_201410301549461', null, '0', '2014-10-31 15:49:41', '2014-10-31 15:49:43', '2014-10-30 15:49:46', '数据部', null, '1', '		123	', '2', null, null, 'yangsy', '2', 'yangsy');
INSERT INTO `active_info` VALUES ('343', '123', 'active_201411041129501', null, '0', '2014-11-06 11:29:44', '2014-11-27 11:29:46', '2014-11-04 11:29:50', '数据部', null, '1', '213			', '1', null, null, 'yangsy', '2', 'yangsy');
INSERT INTO `active_info` VALUES ('344', '123', 'active_201411041131472', null, '0', '2014-11-06 11:31:40', '2014-11-26 11:31:42', '2014-11-04 11:31:47', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '2', 'yangsy');
INSERT INTO `active_info` VALUES ('345', '123', 'active_201411221647221', null, '0', '2014-11-23 16:47:16', '2014-11-23 16:47:19', '2014-11-22 16:47:22', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '999', 'yangsy');
INSERT INTO `active_info` VALUES ('346', '123', 'active_201411221709402', null, '0', '2014-11-06 17:09:32', '2014-11-29 17:09:34', '2014-11-22 17:09:40', '数据部', null, '1', '		123	', '1', null, null, 'yangsy', '999', 'yangsy');
INSERT INTO `active_info` VALUES ('347', '123', 'active_201411221753241', null, '0', '2014-11-07 17:53:20', '2014-11-28 17:53:21', '2014-11-22 17:53:24', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '999', 'yangsy');
INSERT INTO `active_info` VALUES ('348', '123', 'active_201411221835322', null, '0', '2014-11-15 18:35:28', '2014-11-27 18:35:30', '2014-11-22 18:35:32', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '999', 'yangsy');
INSERT INTO `active_info` VALUES ('349', '123', 'active_201411221850501', null, '0', '2014-11-07 18:50:43', '2014-11-28 18:50:44', '2014-11-22 18:50:50', '数据部', null, '1', '123			', '1', null, null, 'yangsy', '999', 'yangsy');
