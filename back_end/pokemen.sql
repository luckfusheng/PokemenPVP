
DROP TABLE IF EXISTS `attributes`;
CREATE TABLE `attributes`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '属性id',
  `name` varchar(255)  DEFAULT NULL COMMENT '属性名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB ;

-- ----------------------------
-- Records of attributes
-- ----------------------------
INSERT INTO `attributes` VALUES (1, '草');
INSERT INTO `attributes` VALUES (2, '毒');
INSERT INTO `attributes` VALUES (3, '火');
INSERT INTO `attributes` VALUES (4, '水');
INSERT INTO `attributes` VALUES (5, '虫');

-- ----------------------------
-- Table structure for panda
-- ----------------------------
DROP TABLE IF EXISTS `panda`;
CREATE TABLE `panda`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(0) DEFAULT NULL COMMENT '宝可梦id',
  `aid` int(0) DEFAULT NULL COMMENT '属性id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB ;

-- ----------------------------
-- Table structure for pokemen
-- ----------------------------
DROP TABLE IF EXISTS `pokemen`;
CREATE TABLE `pokemen`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `number` varchar(255)   DEFAULT NULL COMMENT '编号',
  `name` varchar(255)   DEFAULT NULL COMMENT '姓名',
  `ss` int(0) DEFAULT NULL COMMENT '战斗力',
  `url` varchar(255)   DEFAULT NULL COMMENT '图片地址',
  `stature` varchar(255)   DEFAULT NULL COMMENT '身高',
  `weight` varchar(255)   DEFAULT NULL COMMENT '体重',
  `characteristic` varchar(255)   DEFAULT NULL COMMENT '特征',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB ;

-- ----------------------------
-- Table structure for restrained
-- ----------------------------
DROP TABLE IF EXISTS `restrained`;
CREATE TABLE `restrained`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `advantage` int(0) DEFAULT NULL COMMENT '优势属性id',
  `inferiorposition` int(0) DEFAULT NULL COMMENT '劣势属性id',
  `offset` int(0) DEFAULT NULL COMMENT '战斗力偏移量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB ;

-- ----------------------------
-- Records of restrained
-- ----------------------------
INSERT INTO `restrained` VALUES (1, 1, 4, 10);
INSERT INTO `restrained` VALUES (2, 3, 1, 10);
INSERT INTO `restrained` VALUES (3, 3, 5, 10);
INSERT INTO `restrained` VALUES (4, 4, 3, 10);
INSERT INTO `restrained` VALUES (5, 5, 1, 10);

 
