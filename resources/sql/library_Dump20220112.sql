CREATE DATABASE  IF NOT EXISTS `testdb001` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `testdb001`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: testdb001
-- ------------------------------------------------------
-- Server version	5.7.35-log

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
-- Table structure for table `l_book`
--

DROP TABLE IF EXISTS `l_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `l_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(40) NOT NULL,
  `author` varchar(50) DEFAULT NULL,
  `publish` varchar(50) DEFAULT NULL,
  `book_count` int(11) DEFAULT NULL,
  `price` float(10,2) DEFAULT NULL,
  `rent` float(5,2) DEFAULT NULL,
  `status` int(1) DEFAULT '1',
  `usable` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_book`
--

LOCK TABLES `l_book` WRITE;
/*!40000 ALTER TABLE `l_book` DISABLE KEYS */;
INSERT INTO `l_book` VALUES (1,'JAVA教程','刘畅','外国出版社',0,999.00,998.00,0,0),(2,'C++教程','罗比特','新华出版社',1,96.00,98.00,1,1),(3,'Python教程','赛斯','陕西出版社',9,66.00,100.00,1,1),(4,'JS教程','王能行','渭南出版社',10,58.00,12.00,1,1),(5,'JavaWeb基础','孙红康','尚硅谷',10,83.00,10.00,1,1),(6,'Hadoop从入门到不可能','孙红康','尚硅谷',10,10.00,1.00,1,1),(7,'爱情心理学','蓓邰','宝鸡大学出版社',12,999.00,999.00,1,1),(8,'JAVA教程','张三','码歌教育出版社',94,46.00,35.00,1,1),(9,'伟大的书籍','刘畅','外国出版社',0,999.00,998.00,1,1),(10,'C++教程','罗比特','新华出版社',10,96.00,98.00,1,1),(11,'Python教程','赛斯','陕西出版社',10,66.00,100.00,1,1),(12,'JS教程','王能行','渭南出版社',10,58.00,12.00,1,1),(13,'JavaWeb基础','孙红康','尚硅谷',10,83.00,10.00,1,1),(14,'伟大的书籍','孙红康','尚硅谷',11,10.00,1.00,1,1),(15,'爱情心理学','蓓邰','宝鸡大学出版社',12,999.00,999.00,1,1),(17,'致富之路','王能行','富婆出版社',1,999.00,NULL,1,0);
/*!40000 ALTER TABLE `l_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_record`
--

DROP TABLE IF EXISTS `l_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `l_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `username` varchar(40) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `book_name` varchar(40) DEFAULT NULL,
  `lend_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_record`
--

LOCK TABLES `l_record` WRITE;
/*!40000 ALTER TABLE `l_record` DISABLE KEYS */;
INSERT INTO `l_record` VALUES (1,1,'gg',7,'爱情心理学','2021-12-14','2021-12-15'),(2,4,'lucy',2,'C++教程','2021-12-14','2021-12-15'),(3,3,'yeye',3,'Python教程','2021-12-14','2021-12-15'),(4,2,'mm',5,'JavaWeb基础','2021-12-14','2021-12-15'),(5,1,'gg',6,'Hadoop从入门到不可能','2021-12-14','2021-12-15'),(6,1,'ALiggang',6,'Hadoop从入门到不可能','2021-12-14','2021-12-15'),(7,5,'oken',1,'JAVA教程','2021-12-14','2021-12-15'),(8,2,NULL,2,NULL,'2022-01-11','2022-01-11'),(9,1,NULL,2,NULL,'2022-01-11','2022-01-11'),(10,2,NULL,2,NULL,'2022-01-11','2022-01-11'),(11,2,NULL,2,NULL,'2022-01-11','2022-01-11'),(12,2,NULL,2,NULL,'2022-01-11','2022-01-11'),(13,2,NULL,2,NULL,'2022-01-11','2022-01-11'),(14,9,NULL,2,NULL,'2022-01-11',NULL),(15,8,NULL,2,NULL,'2022-01-11','2022-01-11'),(16,8,NULL,6,NULL,'2022-01-11','2022-01-11');
/*!40000 ALTER TABLE `l_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_shop`
--

DROP TABLE IF EXISTS `l_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `l_shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `username` varchar(40) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `book_name` varchar(40) DEFAULT NULL,
  `buy_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_shop`
--

LOCK TABLES `l_shop` WRITE;
/*!40000 ALTER TABLE `l_shop` DISABLE KEYS */;
INSERT INTO `l_shop` VALUES (1,1,'gg',7,'Python教程','2021-12-14'),(2,4,'lucy',2,'C++教程','2021-12-14'),(3,3,'yeye',3,'Python教程','2021-12-14'),(4,2,'mm',5,'JavaWeb基础','2021-12-14'),(5,1,'gg',6,'JS教程','2021-12-14'),(6,1,'ALiang',6,'爱情心理学','2021-12-14'),(7,5,'oken',1,'JAVA教程','2021-12-14'),(8,2,NULL,2,NULL,'2022-01-11'),(9,2,NULL,2,NULL,'2022-01-11'),(10,2,NULL,2,NULL,'2022-01-11'),(11,2,NULL,2,NULL,'2022-01-11'),(12,2,NULL,2,NULL,'2022-01-11'),(13,2,NULL,2,NULL,'2022-01-11'),(14,2,NULL,2,NULL,'2022-01-11'),(15,2,NULL,2,NULL,'2022-01-11'),(16,2,NULL,8,NULL,'2022-01-11'),(17,2,NULL,8,NULL,'2022-01-11'),(18,2,NULL,8,NULL,'2022-01-11'),(19,2,NULL,8,NULL,'2022-01-11'),(20,2,NULL,8,NULL,'2022-01-11'),(21,2,NULL,8,NULL,'2022-01-11'),(22,2,NULL,2,NULL,'2022-01-11'),(23,2,NULL,2,NULL,'2022-01-11'),(24,2,NULL,2,NULL,'2022-01-11'),(25,2,NULL,3,NULL,'2022-01-11'),(26,9,NULL,2,NULL,'2022-01-11'),(27,8,NULL,2,NULL,'2022-01-11'),(28,8,NULL,2,NULL,'2022-01-11'),(29,8,NULL,2,NULL,'2022-01-11'),(30,8,NULL,2,NULL,'2022-01-11'),(31,8,NULL,2,NULL,'2022-01-11'),(32,8,NULL,2,NULL,'2022-01-11'),(33,8,NULL,2,NULL,'2022-01-11'),(34,8,NULL,6,NULL,'2022-01-11');
/*!40000 ALTER TABLE `l_shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_user`
--

DROP TABLE IF EXISTS `l_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `l_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(20) NOT NULL,
  `sex` int(1) DEFAULT '0',
  `tel` varchar(11) DEFAULT NULL,
  `balance` float(10,2) DEFAULT '100.00',
  `account` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `l_user_username_uk` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_user`
--

LOCK TABLES `l_user` WRITE;
/*!40000 ALTER TABLE `l_user` DISABLE KEYS */;
INSERT INTO `l_user` VALUES (1,'1','1',1,'183666',100.00,1),(2,'mm','123',0,'183667',100.00,1),(3,'yeye','123456',0,'188111',100.00,0),(4,'lucy','123456',0,'188112',100.00,0),(5,'oken','123456',1,'188113',100.00,1),(6,'godone','123456',1,'188114',100.00,0),(7,'lulaotie','lulaotie',1,'18378594514',NULL,0),(8,'user111','123456',1,'666',798.00,0),(10,'aaaa','123',0,'18578451365',NULL,0);
/*!40000 ALTER TABLE `l_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_brand`
--

DROP TABLE IF EXISTS `t_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_brand_name_uk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_brand`
--

LOCK TABLES `t_brand` WRITE;
/*!40000 ALTER TABLE `t_brand` DISABLE KEYS */;
INSERT INTO `t_brand` VALUES (2,'大众'),(4,'奔驰'),(3,'奥迪'),(5,'宝马'),(1,'标致');
/*!40000 ALTER TABLE `t_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_car`
--

DROP TABLE IF EXISTS `t_car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_number` varchar(10) DEFAULT NULL,
  `brand_id` int(9) DEFAULT NULL,
  `model` varchar(40) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `category_id` int(9) DEFAULT NULL,
  `t_comments` varchar(100) DEFAULT NULL,
  `price` float(11,2) DEFAULT NULL,
  `rent` float(9,2) DEFAULT NULL,
  `status` int(1) DEFAULT '0',
  `usable` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_car_number_uk` (`car_number`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_car`
--

LOCK TABLES `t_car` WRITE;
/*!40000 ALTER TABLE `t_car` DISABLE KEYS */;
INSERT INTO `t_car` VALUES (1,'桂S666666',1,'郎逸','black',1,'自动1.6L',0.00,72.00,0,1),(2,'777',1,'途观','7',1,'自动1.8T',0.00,200.00,1,1),(3,'沪A1686',3,'A4L','红色',4,'自动2.0T',400000.00,359.00,0,1),(4,'沪D11C32',1,'308','黑色',1,'手动1.6L',130000.00,56.00,1,0),(5,'沪E11C99',1,'308S','蓝色',2,'自动1.2T',160000.00,70.00,0,0),(6,'沪F11C21',2,'高尔夫','红色',1,'自动1.4T',200000.00,69.00,1,0),(7,'沪F1324',5,'320Li','白色',4,'自动2.0T',380000.00,500.00,1,0),(8,'沪F6666',4,'B200','黑色',4,'自动1.6T',320000.00,300.00,1,0),(10,'1111111',1,'1','蓝色',1,'1111',11.00,1.00,1,0),(11,'66',1,'222','66',1,'22',0.00,2.00,0,0),(12,'77777',1,'777777','77',1,'777',77.00,7.00,0,0),(13,'111111kk',1,'1','黑色',1,'阿萨德',666681.00,66.00,0,0),(16,'桂S777',1,'奔驰','黑色',1,'kool',99.90,9.90,0,0),(17,'G·6666667',NULL,'1','蓝色',NULL,'遇见未来',55.00,2.00,0,0),(18,'G·66666565',NULL,'2','555',NULL,'遇见未来',55.00,5.00,0,0);
/*!40000 ALTER TABLE `t_car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_category`
--

DROP TABLE IF EXISTS `t_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_category_name_uk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_category`
--

LOCK TABLES `t_category` WRITE;
/*!40000 ALTER TABLE `t_category` DISABLE KEYS */;
INSERT INTO `t_category` VALUES (3,'SUV'),(4,'精英型'),(1,'紧凑型'),(2,'舒适型');
/*!40000 ALTER TABLE `t_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_record`
--

DROP TABLE IF EXISTS `t_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `car_id` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `payment` float(9,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_record`
--

LOCK TABLES `t_record` WRITE;
/*!40000 ALTER TABLE `t_record` DISABLE KEYS */;
INSERT INTO `t_record` VALUES (1,2,2,'2017-01-24','2017-01-26',400.00),(2,3,4,'2017-02-20','2017-02-28',448.00),(3,4,5,'2017-03-01','2017-03-03',210.00),(4,5,4,'2021-12-01','2021-12-02',666.00),(51,7,1,'2021-12-01',NULL,NULL),(52,7,1,'2021-12-01',NULL,NULL);
/*!40000 ALTER TABLE `t_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_stock`
--

DROP TABLE IF EXISTS `t_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_stock` (
  `id` int(11) DEFAULT NULL,
  `product_name` varchar(256) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_stock`
--

LOCK TABLES `t_stock` WRITE;
/*!40000 ALTER TABLE `t_stock` DISABLE KEYS */;
INSERT INTO `t_stock` VALUES (1,'apple',90),(10,'product_10',300),(11,'product_11',300),(12,'product_12',300),(13,'product_13',300),(14,'product_14',300),(15,'product_15',300),(16,'product_16',300),(17,'product_17',300),(18,'product_18',300),(19,'product_19',300);
/*!40000 ALTER TABLE `t_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(20) NOT NULL,
  `sex` int(1) DEFAULT '0',
  `id_number` varchar(18) DEFAULT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `addr` varchar(100) DEFAULT NULL,
  `type` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_username_uk` (`username`),
  UNIQUE KEY `t_user_id_number_uk` (`id_number`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'xiaoming','111111',0,'15647641312345','1515445577','江苏',1),(2,'tom','111111',0,'15647641312346','1515445555','日本',1),(3,'lucy','111111',0,'15647641312347','1515445556','上海',1),(4,'sam','111111',0,'15647641312348','1515445557','北京',1),(5,'leo','111111',0,'15647641312349','1515445558','深圳',1),(6,'marry','111111',0,'15647641312340','1515445559','南京',1),(7,'admin','admin',0,NULL,NULL,NULL,1),(8,'Fe','111111',1,'454254','412','666',1),(9,'fee','123456',1,'12365461111','412','42',1),(10,'666','666',0,'666','666','666',0),(11,'ALiangSev','123456',1,'451','5636','55',1),(12,'777','77',1,'7777','77777','77',0),(13,'kkk','kkk',1,'44444444888','888','888',0),(14,'wo','123456',1,'123654688','6667','42',1),(15,'GG','GG',1,'1124','66664','6666',1);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-12 14:23:31
