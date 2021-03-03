/*
 Navicat Premium Data Transfer

 Source Server         : (1
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 8.136.23.192:11106
 Source Schema         : monolith

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 25/02/2021 21:20:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sender_address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'mail-from',
  `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'mail title',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'mail content',
  `del` tinyint(1) NULL DEFAULT 0 COMMENT 'is del? 0 no 1 yes; default 0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_message_recipient
-- ----------------------------
DROP TABLE IF EXISTS `t_message_recipient`;
CREATE TABLE `t_message_recipient`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `message_id` bigint NULL DEFAULT NULL,
  `recipient_id` bigint NULL DEFAULT NULL,
  `type` tinyint NULL DEFAULT NULL COMMENT '10=to,20=cc,30=bcc',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_recipient
-- ----------------------------
DROP TABLE IF EXISTS `t_recipient`;
CREATE TABLE `t_recipient`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `recipient_address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'recipient\'s name',
  `del` tinyint(1) NULL DEFAULT 0 COMMENT 'is del? 0 no 1 yes; default 0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
