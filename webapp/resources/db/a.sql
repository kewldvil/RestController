/*
Navicat PGSQL Data Transfer

Source Server         : Spring
Source Server Version : 90303
Source Host           : localhost:5432
Source Database       : homework_db
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90303
File Encoding         : 65001

Date: 2015-11-05 17:06:38
*/


-- ----------------------------
-- Sequence structure for tbuser_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "tbuser_id_seq";
CREATE SEQUENCE "tbuser_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;
SELECT setval('"public"."tbuser_id_seq"', 3, true);

-- ----------------------------
-- Table structure for tbuser
-- ----------------------------
DROP TABLE IF EXISTS "tbuser";
CREATE TABLE "tbuser" (
"id" int4 DEFAULT nextval('tbuser_id_seq'::regclass) NOT NULL,
"username" varchar(100) COLLATE "default",
"email" varchar(100) COLLATE "default",
"password" varchar(100) COLLATE "default",
"birthdate" timestamp(6),
"registerdate" timestamp(6),
"image" varchar COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tbuser
-- ----------------------------
BEGIN;
INSERT INTO "tbuser" VALUES ('1', 'vuthea', 'vuteh@gmail.com', '123456', '2015-11-05 13:44:56.581', '2015-11-05 13:44:56.581', 'default.jpg');
INSERT INTO "tbuser" VALUES ('3', 'fsdfsd', 'fsdfds', 'fsdfsd', '2015-11-18 14:15:55', '2015-11-17 14:15:46', null);
COMMIT;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "tbuser_id_seq" OWNED BY "tbuser"."id";
