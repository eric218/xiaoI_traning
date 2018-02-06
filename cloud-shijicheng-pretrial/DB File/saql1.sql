CREATE DATABASE  IF NOT EXISTS `ticketsystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `ticketsystem`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: ticketsystem
-- ------------------------------------------------------
-- Server version	5.6.27-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `PID` int(11) DEFAULT NULL COMMENT '父id',
  `AREA_NAME` varchar(50) DEFAULT NULL COMMENT '名称',
  `AREA_ORDER` int(11) DEFAULT NULL COMMENT '序列',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8mb4 COMMENT='省市区';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_name` varchar(45) NOT NULL COMMENT '文件名称',
  `file_addr` varchar(500) NOT NULL COMMENT '文件FTP地址',
  `seq` int(11) NOT NULL COMMENT '文件顺序',
  `notify_form_id` varchar(32) DEFAULT NULL COMMENT '申请告知表表单号',
  `mode_code` varchar(20) DEFAULT NULL COMMENT '供民意调查模块使用字段(文件属于哪个类型)',
  `mode_id` int(11) DEFAULT NULL COMMENT '供民意调查模块使用字段(来源表的主键)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bk_fn`
--

DROP TABLE IF EXISTS `bk_fn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bk_fn` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bk_role`
--

DROP TABLE IF EXISTS `bk_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bk_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) NOT NULL,
  `permission` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bk_user`
--

DROP TABLE IF EXISTS `bk_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bk_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `status` int(11) NOT NULL,
  `salt` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bk_user_role`
--

DROP TABLE IF EXISTS `bk_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bk_user_role` (
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ci_menus`
--

DROP TABLE IF EXISTS `ci_menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ci_menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `m_name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父id',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `url` varchar(255) DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ci_user`
--

DROP TABLE IF EXISTS `ci_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ci_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `province` int(11) DEFAULT NULL COMMENT '省',
  `city` int(11) DEFAULT NULL COMMENT '市',
  `area` int(11) DEFAULT NULL COMMENT '区',
  `community` int(11) DEFAULT NULL COMMENT '社区/街道',
  `rolename` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `phone` varchar(13) DEFAULT NULL COMMENT '电话号码',
  `pwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限规则',
  `user_status` int(11) DEFAULT NULL COMMENT '用户状态',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `salt` varchar(255) DEFAULT NULL COMMENT '标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ct_log`
--

DROP TABLE IF EXISTS `ct_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ct_log` (
  `id` varchar(30) NOT NULL,
  `luckyNumber` varchar(10) DEFAULT NULL,
  `dateline` varchar(30) DEFAULT NULL,
  `game100` varchar(20) DEFAULT NULL,
  `game300` varchar(20) DEFAULT NULL,
  `result300` varchar(20) DEFAULT NULL,
  `special` varchar(20) DEFAULT NULL,
  `groupNum` varchar(10) DEFAULT NULL,
  `catchTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `data_dictionary`
--

DROP TABLE IF EXISTS `data_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dic_name` varchar(255) DEFAULT NULL COMMENT '翻译后的名称',
  `dic_code` int(11) DEFAULT NULL COMMENT '对应标记位 如：0，1 ',
  `sign_code` varchar(255) DEFAULT NULL COMMENT '唯一标识编码',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8mb4 COMMENT='数据字典';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gc_lottery`
--

DROP TABLE IF EXISTS `gc_lottery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gc_lottery` (
  `id` varchar(50) NOT NULL,
  `money` double NOT NULL,
  `number` int(11) NOT NULL,
  `sender` int(11) NOT NULL,
  `roomId` varchar(50) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `status` varchar(2) NOT NULL,
  `type` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gc_lottery_detail`
--

DROP TABLE IF EXISTS `gc_lottery_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gc_lottery_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lotteryid` varchar(50) NOT NULL,
  `uid` int(11) NOT NULL,
  `coin` double NOT NULL,
  `createDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gc_room`
--

DROP TABLE IF EXISTS `gc_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gc_room` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `catalog` varchar(10) NOT NULL,
  `type` varchar(10) NOT NULL,
  `owner` int(11) NOT NULL DEFAULT '0',
  `limitNum` int(11) NOT NULL,
  `hot` int(11) NOT NULL DEFAULT '0',
  `roomimg` varchar(100) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `psw` varchar(30) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `feeAdd` double DEFAULT NULL,
  `shareRate` double DEFAULT NULL,
  `poolAdd` double DEFAULT NULL,
  `sumFee` double DEFAULT NULL,
  `sumPool` double DEFAULT NULL,
  `rule` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gc_room_props`
--

DROP TABLE IF EXISTS `gc_room_props`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gc_room_props` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `roomId` varchar(30) NOT NULL,
  `configKey` varchar(50) DEFAULT NULL,
  `alias` varchar(200) DEFAULT NULL,
  `configValue` varchar(50) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `job_company`
--

DROP TABLE IF EXISTS `job_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_name` varchar(45) DEFAULT NULL COMMENT '招聘单位',
  `company_addr` varchar(255) DEFAULT NULL COMMENT '公司地址',
  `contact_name` varchar(32) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `recruit_start_time` date DEFAULT NULL COMMENT '招聘开始时间',
  `recruit_end_time` date DEFAULT NULL COMMENT '招聘结束时间',
  `job_detail` longtext COMMENT '招聘岗位及要求',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(45) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='公司招聘';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `job_person`
--

DROP TABLE IF EXISTS `job_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '个人姓名',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `nation` varchar(45) DEFAULT NULL COMMENT '民族',
  `degree` varchar(45) DEFAULT NULL COMMENT '学历',
  `birth` varchar(45) DEFAULT NULL COMMENT '生日',
  `graduate_school` varchar(255) DEFAULT NULL COMMENT '毕业学校',
  `intention` varchar(255) DEFAULT NULL COMMENT '求职意愿',
  `phone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `salary` varchar(45) DEFAULT NULL,
  `home_addr` varchar(45) DEFAULT NULL COMMENT '家庭住址',
  `work_experience` longtext COMMENT '工作经历',
  `create_time` datetime DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='个人求职';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `job_publish`
--

DROP TABLE IF EXISTS `job_publish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_publish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_title` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `content` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `log_user_operate`
--

DROP TABLE IF EXISTS `log_user_operate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_user_operate` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `operate_time` datetime NOT NULL,
  `operate_id` varchar(32) NOT NULL,
  `tk_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `login_log`
--

DROP TABLE IF EXISTS `login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_log` (
  `login_id` varchar(32) NOT NULL,
  `login_userid` varchar(32) NOT NULL,
  `login_time` time NOT NULL,
  `login_date` date NOT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `migrant_married_info`
--

DROP TABLE IF EXISTS `migrant_married_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `migrant_married_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `female_name` varchar(32) DEFAULT NULL COMMENT '女方姓名',
  `female_id` varchar(32) DEFAULT NULL COMMENT '女方身份证号码',
  `female_marital_status` int(11) DEFAULT NULL COMMENT '女方婚姻状况(1:已婚/2:离异/3:丧偶)',
  `male_name` varchar(32) DEFAULT NULL COMMENT '男方姓名',
  `male_id` varchar(32) DEFAULT NULL COMMENT '男方身份证号码',
  `male_marital_status` int(11) DEFAULT NULL COMMENT '男方婚姻状况(1:已婚/2:离异/3:丧偶)',
  `marriage_certificate` varchar(11) DEFAULT NULL COMMENT '结婚证编号',
  `children_number` varchar(11) DEFAULT NULL COMMENT '现有子女数',
  `son_number` varchar(11) DEFAULT NULL COMMENT '子女男性数量',
  `daughter_number` varchar(11) DEFAULT NULL COMMENT '子女女性数量',
  `departure` tinyint(1) DEFAULT NULL COMMENT '夫妇是否外出(1：是0：否)',
  `departure_time` date DEFAULT NULL COMMENT '离开时间',
  `registered_province` int(11) DEFAULT NULL COMMENT '户籍地址(省)',
  `registered_city` int(11) DEFAULT NULL COMMENT '户籍地址(市)',
  `registered_area` int(11) DEFAULT NULL COMMENT '户籍地址(区)',
  `registered_community` int(11) DEFAULT NULL COMMENT '户籍地址（社区/乡）',
  `registered_address` varchar(32) DEFAULT NULL COMMENT '户籍地址(详细街道)',
  `domicile_province` int(11) DEFAULT NULL COMMENT '居住地址(省)',
  `domicile_city` int(11) DEFAULT NULL COMMENT '居住地址(市)',
  `domicile_area` int(11) DEFAULT NULL COMMENT '居住地址(区)',
  `domicile_community` int(11) DEFAULT NULL COMMENT '居住地址（社区/乡）',
  `domicile_address` varchar(32) DEFAULT NULL COMMENT '居住地址(详细街道)',
  `birth_control` tinyint(1) DEFAULT NULL COMMENT '是否进行节育(1：是0：否)',
  `contraceptive_mode` int(1) DEFAULT NULL COMMENT '节育方式(1：皮埋  2：上环3：男扎4：女扎 -1：其他)',
  `gynecological_examination` tinyint(1) DEFAULT NULL COMMENT '是否参加妇检(0:否，1:是)',
  `notify_form_id` varchar(32) DEFAULT NULL COMMENT '申请告知表表单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COMMENT='已婚个人信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `migrant_unmarried_info`
--

DROP TABLE IF EXISTS `migrant_unmarried_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `migrant_unmarried_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `application_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `application_id` varchar(32) DEFAULT NULL COMMENT '身份证号码',
  `expiry_date` varchar(32) DEFAULT NULL COMMENT '身份证有效期限',
  `registered_province` int(11) DEFAULT NULL COMMENT '户籍地址(省)',
  `registered_city` int(11) DEFAULT NULL COMMENT '户籍地址(市)',
  `registered_area` int(11) DEFAULT NULL COMMENT '户籍地址(区)',
  `Registered_community` int(11) DEFAULT NULL COMMENT '户籍地址（社区/乡）',
  `registered_address` varchar(32) DEFAULT NULL COMMENT '户籍地址(详细街道)',
  `domicile_province` int(11) DEFAULT NULL COMMENT '居住地址(省)',
  `domicile_city` int(11) DEFAULT NULL COMMENT '居住地址(市)',
  `domicile_area` int(11) DEFAULT NULL COMMENT '居住地址(区)',
  `domicile_community` int(11) DEFAULT NULL COMMENT '居住地址（社区/乡）',
  `domicile_address` varchar(32) DEFAULT NULL COMMENT '居住地址(详细街道)',
  `notify_form_id` varchar(32) DEFAULT NULL COMMENT '申请告知表表单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='未婚个人信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notify`
--

DROP TABLE IF EXISTS `notify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notify` (
  `form_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '提交表单号',
  `form_type` int(11) NOT NULL COMMENT '表单分类',
  `area` int(11) DEFAULT NULL COMMENT '区/县',
  `community` int(11) DEFAULT NULL COMMENT '乡镇/社区',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  `approval_status` int(1) NOT NULL DEFAULT '0' COMMENT '审核状态(待审:0/已审通过：1/已审未通过：2)',
  `approval_person` varchar(255) DEFAULT NULL COMMENT '审核人',
  `approval_result` varchar(255) DEFAULT NULL COMMENT '审核结果描述',
  `approval_time` datetime DEFAULT NULL COMMENT '审核时间',
  `proposer_name` varchar(32) NOT NULL COMMENT '申请人姓名',
  `propose_phone` varchar(32) NOT NULL COMMENT '申请人联系电话',
  `propose_marital_status` int(1) NOT NULL DEFAULT '0' COMMENT '申请人婚姻状况(1:已婚/2:未婚)',
  PRIMARY KEY (`form_no`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `poll_options`
--

DROP TABLE IF EXISTS `poll_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poll_options` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `topic_id` int(11) DEFAULT NULL COMMENT '问题ID',
  `content` varchar(255) DEFAULT NULL COMMENT '选项内容',
  `seq` int(11) DEFAULT NULL COMMENT '顺序',
  `is_answer` int(11) DEFAULT NULL COMMENT '是否正确答案   1：是    0：否',
  `opt_type` int(11) DEFAULT NULL COMMENT '选项类型  0：单选  1：多选  2：填空',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1281 DEFAULT CHARSET=utf8mb4 COMMENT='题目选项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `poll_reply`
--

DROP TABLE IF EXISTS `poll_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poll_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `survey_id` int(11) DEFAULT NULL COMMENT '问卷调查ID',
  `survey_title` varchar(45) DEFAULT NULL COMMENT '问卷调查名称',
  `topic_id` int(11) DEFAULT NULL COMMENT '问题ID',
  `topic_title` varchar(45) DEFAULT NULL COMMENT '问卷调查题目名称',
  `topic_seq` int(11) DEFAULT NULL COMMENT '问卷调查题目顺序',
  `topic_type` int(11) DEFAULT NULL COMMENT '问题类型  0：单选  1：多选  2：填空',
  `option_id` int(11) DEFAULT NULL COMMENT '选项ID',
  `option_content` varchar(255) DEFAULT NULL COMMENT '题目选项内容',
  `option_seq` int(11) DEFAULT NULL COMMENT '题目选项顺序',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户ID(指的是手机用户ID)',
  `content` varchar(255) DEFAULT NULL COMMENT '回复内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=235 DEFAULT CHARSET=utf8mb4 COMMENT='回复表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `poll_survey`
--

DROP TABLE IF EXISTS `poll_survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poll_survey` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `description` varchar(255) DEFAULT NULL COMMENT '问卷描述',
  `start_time` datetime DEFAULT NULL COMMENT '调查开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '调查结束时间',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态 0：未发布  1：发布  2：取消发布',
  `counts` decimal(19,2) DEFAULT NULL,
  `surveyTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8mb4 COMMENT='民调';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `poll_topic`
--

DROP TABLE IF EXISTS `poll_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poll_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `survey_id` int(11) DEFAULT NULL COMMENT '问卷ID',
  `title` varchar(255) DEFAULT NULL COMMENT '题目名称',
  `seq` int(11) DEFAULT NULL COMMENT '题目顺序',
  `type` int(11) DEFAULT NULL COMMENT '选项类型  0：单选  1：多选  2：填空',
  `creator` varchar(45) DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(45) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=408 DEFAULT CHARSET=utf8mb4 COMMENT='问题';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pub_bank`
--

DROP TABLE IF EXISTS `pub_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pub_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `bankName` varchar(100) DEFAULT NULL,
  `branch` varchar(100) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `account` varchar(30) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `userIdText` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pub_loginlog`
--

DROP TABLE IF EXISTS `pub_loginlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pub_loginlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `realIp` varchar(15) DEFAULT NULL,
  `loginTime` datetime DEFAULT NULL,
  `userName` varchar(30) DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `province` varchar(30) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `area` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pub_manualmoney`
--

DROP TABLE IF EXISTS `pub_manualmoney`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pub_manualmoney` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `userIdText` varchar(32) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `operator` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pub_recharge`
--

DROP TABLE IF EXISTS `pub_recharge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pub_recharge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `tradeno` varchar(50) NOT NULL,
  `fee` double NOT NULL,
  `realfee` double DEFAULT NULL,
  `tradetime` datetime NOT NULL,
  `finishtime` datetime DEFAULT NULL,
  `payno` varchar(50) DEFAULT NULL,
  `goodsname` varchar(50) DEFAULT NULL,
  `descpt` varchar(200) DEFAULT NULL,
  `status` char(1) NOT NULL DEFAULT '1',
  `gift` double DEFAULT NULL,
  `operator` double DEFAULT NULL,
  `userIdText` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_tradeno` (`tradeno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pub_user`
--

DROP TABLE IF EXISTS `pub_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pub_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(32) NOT NULL,
  `nickName` varchar(30) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `pwd` varchar(100) NOT NULL,
  `moneyCode` varchar(100) NOT NULL,
  `headImg` varchar(200) DEFAULT NULL,
  `signture` varchar(200) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `exp` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `QQ` varchar(20) DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  `parentTree` text,
  `point` double DEFAULT NULL,
  `subPoint` double DEFAULT NULL,
  `registIP` varchar(20) DEFAULT NULL,
  `registDate` datetime DEFAULT NULL,
  `lastLoginIP` varchar(20) DEFAULT NULL,
  `lastLoginDate` datetime DEFAULT NULL,
  `userType` char(1) DEFAULT NULL,
  `wxOpenId` varchar(50) DEFAULT NULL,
  `wbOpenId` varchar(50) DEFAULT NULL,
  `qqOpenId` varchar(50) DEFAULT NULL,
  `alipay` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `water` double DEFAULT NULL,
  `chargeAmount` double DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `onlineStatus` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pub_withdraw`
--

DROP TABLE IF EXISTS `pub_withdraw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pub_withdraw` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `tradeno` varchar(50) DEFAULT NULL,
  `fee` double NOT NULL,
  `tradetime` datetime NOT NULL,
  `finishtime` datetime DEFAULT NULL,
  `payno` varchar(50) DEFAULT NULL,
  `goodsname` varchar(50) DEFAULT NULL,
  `descpt` varchar(200) DEFAULT NULL,
  `status` char(1) NOT NULL DEFAULT '1',
  `accountInfo` varchar(200) DEFAULT NULL,
  `bankName` varchar(100) DEFAULT NULL,
  `account` varchar(100) DEFAULT NULL,
  `branch` varchar(100) DEFAULT NULL,
  `ownerName` varchar(30) DEFAULT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `userIdText` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_tradeno` (`tradeno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reproductive_civics_info`
--

DROP TABLE IF EXISTS `reproductive_civics_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reproductive_civics_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `female_name` varchar(45) DEFAULT NULL COMMENT '女方姓名',
  `female_id` varchar(32) DEFAULT NULL COMMENT '女方身份证号码',
  `female_age` int(11) DEFAULT NULL COMMENT '女方年龄',
  `female_marital_status` int(11) DEFAULT '25' COMMENT '男方婚姻状况(25:已婚/26:离异/27:丧偶)默认为25,从数据字典中取',
  `female_registered_province` int(11) DEFAULT NULL COMMENT '女方户籍地址(省)',
  `female_registered_city` int(11) DEFAULT NULL COMMENT '女方户籍地址(市)',
  `female_registered_area` int(11) DEFAULT NULL COMMENT '女方户籍地址(区)',
  `female_registered_community` int(11) DEFAULT NULL COMMENT '女方户籍地址（社区/乡）',
  `female_registered_address` varchar(32) DEFAULT NULL COMMENT '女方户籍地址(详细街道)',
  `female_domicile_province` int(11) DEFAULT NULL COMMENT '女方居住地址(省)',
  `female_domicile_city` int(11) DEFAULT NULL COMMENT '女方居住地址(市)',
  `female_domicile_area` int(11) DEFAULT NULL COMMENT '女方居住地址(区)',
  `female_domicile_community` int(11) DEFAULT NULL COMMENT '女方居住地址（社区/乡）',
  `female_domicile_address` varchar(32) DEFAULT NULL COMMENT '女方居住地址(详细街道)',
  `male_name` varchar(32) DEFAULT NULL COMMENT '男方姓名',
  `male_id` varchar(32) DEFAULT NULL COMMENT '男方身份证号码',
  `male_age` int(11) DEFAULT NULL COMMENT '男方年龄',
  `male_marital_status` int(11) DEFAULT '1' COMMENT '男方婚姻状况(1:已婚/2:离异/3:丧偶),默认为1',
  `male_registered_province` int(11) DEFAULT NULL COMMENT '男方户籍地址(省)',
  `male_registered_city` int(11) DEFAULT NULL COMMENT '男方户籍地址(市)',
  `male_registered_area` int(11) DEFAULT NULL COMMENT '男方户籍地址(区)',
  `male_registered_community` int(11) DEFAULT NULL,
  `male_registered_address` varchar(32) DEFAULT NULL COMMENT '男方户籍地址(详细街道)',
  `male_domicile_province` int(11) DEFAULT NULL COMMENT '男方居住地址(省)',
  `male_domicile_city` int(11) DEFAULT NULL COMMENT '男方居住地址(市)',
  `male_domicile_area` int(11) DEFAULT NULL COMMENT '男方居住地址(区)',
  `male_domicile_community` int(11) DEFAULT NULL,
  `male_domicile_address` varchar(32) DEFAULT NULL COMMENT '男方居住地址(详细街道)',
  `marriage_certificate_number` varchar(32) DEFAULT NULL COMMENT '结婚登记证号',
  `marriage_certificate_date` date DEFAULT NULL COMMENT '结婚登记日期',
  `marriage_certificate_organ` varchar(32) DEFAULT NULL COMMENT '结婚登记机关',
  `children_number` varchar(11) DEFAULT NULL COMMENT '现有子女数',
  `son_number` varchar(11) DEFAULT NULL COMMENT '子女男性数量',
  `daughter_number` varchar(11) DEFAULT NULL COMMENT '子女女性数量',
  `pregnancy` int(11) DEFAULT NULL COMMENT '当前孕育情况（0：未怀孕 1：已怀孕）',
  `pregnancy_weeks` varchar(11) DEFAULT NULL COMMENT '怀孕周数(如：24周03天)',
  `notify_form_id` varchar(32) DEFAULT NULL COMMENT '申请告知表表单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='生殖保健服务 用户基础信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rural_subsistence_allowances_info`
--

DROP TABLE IF EXISTS `rural_subsistence_allowances_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rural_subsistence_allowances_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `notify_form_id` varchar(32) DEFAULT NULL COMMENT '告知表关联id',
  `householdername` varchar(50) DEFAULT NULL COMMENT '户主姓名',
  `householderid` varchar(32) DEFAULT NULL COMMENT '户主身份证号',
  `birthday` date DEFAULT NULL COMMENT '出生年月',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` int(11) DEFAULT NULL COMMENT '性别 1：男 2：女',
  `totalpopulation` varchar(32) DEFAULT NULL COMMENT '家庭共同生活总人口数 1：1 ， 2:2， 3：3 ，4:4， 5:5， 6:6人及以上',
  `ruralpopulation` varchar(11) DEFAULT NULL COMMENT '农村户口人数 1：1 ， 2:2， 3：3 ，4:4， 5:5， 6:6人及以上',
  `urbanpopulation` varchar(11) DEFAULT NULL COMMENT '城市户口人数 1：1 ， 2:2， 3：3 ，4:4， 5:5， 6:6人及以上',
  `registeredprovince` int(11) DEFAULT NULL COMMENT '户籍地址（省）',
  `registeredcity` int(11) DEFAULT NULL COMMENT '户籍地址（市）',
  `registeredarea` int(11) DEFAULT NULL COMMENT '户籍地址（区）',
  `registeredcommunity` int(11) DEFAULT NULL COMMENT '户籍地址（社区/乡）',
  `registeredaddress` varchar(255) DEFAULT NULL COMMENT '户籍地址（详细街道）',
  `domicileprovince` int(11) DEFAULT NULL COMMENT '居住地址（省）',
  `domicilecity` int(11) DEFAULT NULL COMMENT '居住地址（市）',
  `domicilearea` int(11) DEFAULT NULL COMMENT '居住地址（区）',
  `domicilecommunity` int(11) DEFAULT NULL COMMENT '居住地址（社区/乡）',
  `domicileaddress` varchar(255) DEFAULT NULL COMMENT '居住地址（详细街道）',
  `housingnature` int(11) DEFAULT NULL COMMENT '现住房性质 1：购买 2：自建 3：租用    -1：其他',
  `housingnaturedescribe` varchar(500) DEFAULT NULL COMMENT '房屋性质描述',
  `buildingstructure` int(11) DEFAULT NULL COMMENT '房屋结构 1：简易棚坯；2：土木结构；3：砖混结构；4：钢筋混凝土结构；-1：其他',
  `housingarea` int(11) DEFAULT NULL COMMENT '面积 1:0-50平方 ；2:51-100平方；3:101-200平方；4:201平方以上',
  `responsibilityfield` int(11) DEFAULT NULL COMMENT '承包责任田 1:0-1亩；2:2-5亩；3:6亩及以上',
  `responsibilityland` int(11) DEFAULT NULL COMMENT '承包责任土  1:0-1亩；2:2-5亩；3:6亩及以上',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='农村低保申请';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_params`
--

DROP TABLE IF EXISTS `sys_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_params` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `paramname` varchar(50) NOT NULL,
  `paramvalue` varchar(50) NOT NULL,
  `paramalias` varchar(50) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_paramname` (`paramname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `phonenumb` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `avatar` varchar(50) DEFAULT NULL,
  `registertime` datetime DEFAULT NULL,
  `lastsignintime` datetime DEFAULT NULL,
  `lastsigninip` varchar(50) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_phonenumb` (`phonenumb`) USING BTREE,
  UNIQUE KEY `idx_email` (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_userroletoken`
--

DROP TABLE IF EXISTS `sys_userroletoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_userroletoken` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` varchar(50) NOT NULL,
  `roleid` varchar(50) NOT NULL,
  `organid` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fk_sysuid` (`userid`) USING BTREE,
  CONSTRAINT `sys_userroletoken_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_info`
--

DROP TABLE IF EXISTS `tk_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_info` (
  `tk_id` varchar(32) NOT NULL,
  `tk_name` varchar(32) NOT NULL,
  `tk_tel` varchar(32) NOT NULL,
  `tk_content` varchar(2000) DEFAULT NULL,
  `tk_status` int(11) NOT NULL,
  `tk_date` date NOT NULL,
  `tk_time` time NOT NULL,
  `tk_fn_id` int(11) NOT NULL,
  PRIMARY KEY (`tk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `urban_expand`
--

DROP TABLE IF EXISTS `urban_expand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `urban_expand` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `urban_id` int(11) DEFAULT NULL COMMENT '城市低保申请表id',
  `familyname` varchar(50) DEFAULT NULL COMMENT '家庭成员姓名',
  `familybirthday` date DEFAULT NULL COMMENT '出生年月',
  `familymaritalstatus` int(11) DEFAULT NULL COMMENT '婚姻状况 1：已婚2：未婚3：离异4：丧偶',
  `familyhealthcondition` int(11) DEFAULT NULL COMMENT '身体状况 1：健康2：患病3：残疾',
  `familyemploymentstatus` int(11) DEFAULT NULL COMMENT '就业情况 1：已就业2：失业3：无业4：待业',
  `familyidentitycategory` int(11) DEFAULT NULL COMMENT '身份类别 1:工人 2:农民 3:牧民 4:渔民 5:医生 6:药剂师 7:护士 8:机长 9:飞行员 10:船长 11:乘务人员 12:服务员 13:公务员 14:律师15:商人16:宗教职业者17:退休人员18:家庭主妇19:无业人员20:学生21:儿童22:职员23:法律工作者24:事业单位工作者-1：其他',
  `familyworkunit` varchar(255) DEFAULT NULL COMMENT '工作单位',
  `registeredrelationship` int(11) DEFAULT NULL COMMENT '是否同户籍  1：是 2：否',
  `liverelationship` int(11) DEFAULT NULL COMMENT '是否同居住  1：是   2：否',
  `familyrelationship` int(11) DEFAULT NULL COMMENT '与户主关系  1：父女  2：父子  3：母女  4：母子  5：夫妻  6：祖孙  7：兄妹  8：姐弟  9：姐妹  10：兄弟  11：其他',
  `familyincome` int(11) DEFAULT NULL COMMENT '个人月收入  1：0-500元  2：501-1000元  3：1001-2000元  4：2001-3000元  5：3000元以上',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='城市低保申请(拓展)-家庭成员信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `urban_rural_expand`
--

DROP TABLE IF EXISTS `urban_rural_expand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `urban_rural_expand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ur_id` int(11) DEFAULT NULL COMMENT '城乡医疗救助id',
  `householdername` varchar(50) DEFAULT NULL COMMENT '户主姓名',
  `familyname` varchar(50) DEFAULT NULL COMMENT '家庭成员姓名',
  `householderid` varchar(30) DEFAULT NULL COMMENT '户主身份证号',
  `familyid` varchar(30) DEFAULT NULL COMMENT '家庭成员身份证号码',
  `sex` int(11) DEFAULT NULL COMMENT '性别1：男 2：女',
  `familyrelationship` int(11) DEFAULT NULL COMMENT '与户主关系 1：本人2：父女3：父子4：母女5：母子6：夫妻7：祖孙8：兄妹9：姐弟10：姐妹11：兄弟12：其他',
  `familyworkunit` varchar(500) DEFAULT NULL COMMENT '单位全称',
  `unitnature` int(11) DEFAULT NULL COMMENT '单位性质 1：机关2：科研设计单位3：高等教育单位4：:中初教育单位5：医疗卫生单位6：其他事业单位7：国有企业8：三资企业9：其他企业10：部队11：其他',
  `personaproperty` varchar(500) DEFAULT NULL COMMENT '名下财产',
  `wage` varchar(500) DEFAULT NULL COMMENT '工资收入',
  `operatingincome` varchar(500) DEFAULT NULL COMMENT '家庭经营收入',
  `otherincome` varchar(500) DEFAULT NULL COMMENT '其他收入',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='城乡医疗救助(拓展)-家庭成员信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `urban_rural_medical_assistance_info`
--

DROP TABLE IF EXISTS `urban_rural_medical_assistance_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `urban_rural_medical_assistance_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `notify_form_id` varchar(32) DEFAULT NULL COMMENT '告知表关联id',
  `patientname` varchar(50) DEFAULT NULL COMMENT '患者姓名',
  `patientid` varchar(30) DEFAULT NULL COMMENT '身份证号码',
  `patientbirthday` date DEFAULT NULL COMMENT '出生年月',
  `patientphone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `patientage` int(11) DEFAULT NULL COMMENT '年龄',
  `registeredprovince` int(11) DEFAULT NULL COMMENT '户籍地址（省）',
  `registeredcity` int(11) DEFAULT NULL COMMENT '户籍地址（市）',
  `registeredarea` int(11) DEFAULT NULL COMMENT '户籍地址（区）',
  `registeredcommunity` int(11) DEFAULT NULL COMMENT '户籍地址（社区/乡）',
  `registeredaddress` varchar(255) DEFAULT NULL COMMENT '户籍地址（详细街道）',
  `domicileprovince` int(11) DEFAULT NULL COMMENT '居住地址（省）',
  `domicilecity` int(11) DEFAULT NULL COMMENT '居住地址（市）',
  `domicilearea` int(11) DEFAULT NULL COMMENT '居住地址（区）',
  `domicilecommunity` int(11) DEFAULT NULL COMMENT '居住地址（社区/乡）',
  `domicileaddress` varchar(255) DEFAULT NULL COMMENT '居住地址（详细街道）',
  `hospital` varchar(255) DEFAULT NULL COMMENT '就诊医院',
  `medicalexpenses` varchar(500) DEFAULT NULL COMMENT '自付合规医疗费用',
  `patiencategory` int(11) DEFAULT NULL COMMENT '患者类别 1：特困供养人员2:二十世纪六十年代精简退职老职工3:家庭经济困难的精神障碍患者、肇事肇祸的精神障碍患者4:城乡低保对象5:精准扶贫建档立卡贫困人口中的大病患者6:艾滋病人和艾滋病机会性感染者7:享受抚恤补助的优抚对象（不含1-6级残疾军人）8:低收入家庭人员患病9:因病致贫家庭人员患病10:其他特殊困难群众',
  `diseasename` varchar(500) DEFAULT NULL COMMENT '所患疾病名称',
  `medicalinsurance` int(11) DEFAULT NULL COMMENT '参加医疗保险情况 1:城镇职工基本医疗保险2:城镇居民基本医疗保险3:商业保险4:新型农村合作医疗5:医疗保险 6:其他',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='城乡医疗救助';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `urban_subsistence_allowances_info`
--

DROP TABLE IF EXISTS `urban_subsistence_allowances_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `urban_subsistence_allowances_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `notify_form_id` varchar(32) DEFAULT NULL COMMENT '告知表关联id',
  `householdername` varchar(50) DEFAULT NULL COMMENT '户主姓名',
  `householderid` varchar(30) DEFAULT NULL COMMENT '户主身份证号',
  `birthday` date DEFAULT NULL COMMENT '出生年月',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `householdermaritalstatus` int(11) DEFAULT NULL COMMENT '婚姻状况 1：已婚 2：未婚 3：离异 4：丧偶',
  `householderhealthcondition` int(11) DEFAULT NULL COMMENT '身体状况1：健康 2：患病 3：残疾 ',
  `householderemploymentstatus` int(11) DEFAULT NULL COMMENT '就业情况 1：已就业 2：失业 3：无业 4：待业',
  `householderidentitycategory` int(11) DEFAULT NULL COMMENT '身份类别 1:工人 2:农民 3:牧民 4:渔民 5:医生 6:药剂师 7:护士 8:机长 9:飞行员 10:船长 11:乘务人员 12:服务员 13:公务员 14:律师15:商人16:宗教职业者17:退休人员18:家庭主妇19:无业人员20:学生21:儿童22:职员23:法律工作者24:事业单位工作者-1：其他',
  `householderworkunit` varchar(255) DEFAULT NULL COMMENT '工作单位',
  `householderpersonalincome` int(11) DEFAULT NULL COMMENT '个人月收入 1：0-500元 2：501-1000元 3：1001-2000元 4：2001-3000元 5：3000元以上',
  `familypopulation` int(11) DEFAULT NULL COMMENT '家庭共同生活总人口数 1：1  2:2   3：3    4:4    5:5      6:6人及以上',
  `samehousehold` int(11) DEFAULT NULL COMMENT '同户籍人数 1：1  2:2   3：3    4:4    5:5      6:6人及以上',
  `livetogether` int(11) DEFAULT NULL COMMENT '同居住人数 1：1  2:2   3：3    4:4    5:5      6:6人及以上',
  `registeredprovince` int(11) DEFAULT NULL COMMENT '户籍地址（省）',
  `registeredcity` int(11) DEFAULT NULL COMMENT '户籍地址（市）',
  `registeredarea` int(11) DEFAULT NULL COMMENT '户籍地址（区）',
  `registeredcommunity` int(11) DEFAULT NULL COMMENT '户籍地址（社区/乡）',
  `registeredaddress` varchar(255) DEFAULT NULL COMMENT '户籍地址（详细街道）',
  `domicileprovince` int(11) DEFAULT NULL COMMENT '居住地址（省）',
  `domicilecity` int(11) DEFAULT NULL COMMENT '居住地址（市）',
  `domicilearea` int(11) DEFAULT NULL COMMENT '居住地址（区）',
  `domicilecommunity` int(11) DEFAULT NULL COMMENT '居住地址（社区/乡）',
  `domicileaddress` varchar(255) DEFAULT NULL COMMENT '居住地址（详细街道）',
  `residencepermityears` int(11) DEFAULT NULL COMMENT '持贵阳市居住证年限 1：不满1年；2.1年及以上',
  `housingnature` int(11) DEFAULT NULL COMMENT '现住房性质 1：购买 2：自建 3：租用 -1：其他',
  `housingnaturedescribe` varchar(500) DEFAULT NULL COMMENT '房屋性质描述',
  `buildingstructure` int(11) DEFAULT NULL COMMENT '房屋结构 1：简易棚坯；2：土木结构；3：砖混结构；4：钢筋混凝土结构；-1：其他',
  `housingarea` int(11) DEFAULT NULL COMMENT '面积 1:0-50平方 ；2:51-100平方；3:101-200平方；4:201平方以上',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='城市低保申请';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `work_flow`
--

DROP TABLE IF EXISTS `work_flow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work_flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `people` varchar(100) DEFAULT NULL COMMENT '办理对象',
  `department` varchar(100) DEFAULT NULL COMMENT '办理部门',
  `address` varchar(100) DEFAULT NULL COMMENT '办理地址',
  `tel` varchar(100) DEFAULT NULL COMMENT '咨询电话',
  `work_time` varchar(100) DEFAULT NULL COMMENT '接待时间',
  `time_limit` varchar(100) DEFAULT NULL COMMENT '办理时限',
  `province` varchar(100) DEFAULT NULL COMMENT '省',
  `city` varchar(100) DEFAULT NULL COMMENT '市',
  `town` varchar(100) DEFAULT NULL COMMENT '乡镇',
  `community` varchar(100) DEFAULT NULL COMMENT '社区',
  `category` int(11) DEFAULT NULL COMMENT '类别：1流动人口婚育证明、2生殖保健服务、3城乡医疗救助申请',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-06 11:35:26
