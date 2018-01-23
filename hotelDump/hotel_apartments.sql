CREATE DATABASE  IF NOT EXISTS `hotel` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hotel`;
-- MySQL dump 10.13  Distrib 5.7.18-ndb-7.6.3, for Linux (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	5.7.18-ndb-7.6.3

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
-- Table structure for table `apartments`
--

DROP TABLE IF EXISTS `apartments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apartments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numberOfRooms` enum('1','2','3','4','5+') NOT NULL,
  `price` int(11) NOT NULL,
  `type` enum('standart','business','deluxe','president') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartments`
--

LOCK TABLES `apartments` WRITE;
/*!40000 ALTER TABLE `apartments` DISABLE KEYS */;
INSERT INTO `apartments` VALUES (1,'3',500,'standart'),(2,'1',200,'standart'),(3,'2',700,'deluxe'),(4,'1',250,'standart'),(5,'4',650,'deluxe'),(6,'4',1000,'deluxe'),(8,'5+',3000,'deluxe'),(9,'5+',5000,'president'),(10,'2',650,'standart'),(11,'3',400,'standart'),(12,'4',300,'standart'),(13,'5+',700,'standart'),(14,'3',400,'standart'),(15,'3',500,'standart'),(16,'5+',600,'standart'),(17,'4',500,'standart'),(18,'1',400,'deluxe'),(19,'1',450,'deluxe'),(20,'2',500,'deluxe'),(21,'2',550,'deluxe'),(22,'3',600,'deluxe'),(23,'3',650,'deluxe'),(24,'4',700,'deluxe'),(25,'4',700,'deluxe'),(26,'5+',700,'deluxe'),(27,'5+',800,'deluxe'),(28,'1',300,'business'),(29,'1',300,'business'),(30,'2',400,'business'),(31,'2',400,'business'),(32,'3',500,'business'),(33,'3',700,'business'),(34,'4',500,'business'),(35,'4',300,'business'),(36,'5+',500,'business'),(37,'5+',900,'business'),(38,'4',3000,'president'),(39,'4',4000,'president'),(40,'5+',8000,'president');
/*!40000 ALTER TABLE `apartments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-23 23:44:31
