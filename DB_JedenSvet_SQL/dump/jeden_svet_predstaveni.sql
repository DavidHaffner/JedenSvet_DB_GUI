-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: jeden_svet
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `predstaveni`
--

DROP TABLE IF EXISTS `predstaveni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `predstaveni` (
  `idpredstaveni` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `datum` datetime NOT NULL,
  `idkino` int(10) unsigned NOT NULL,
  `idfilm` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idpredstaveni`),
  KEY `idkino_idx` (`idkino`),
  KEY `idfilm_idx` (`idfilm`),
  CONSTRAINT `idfilm` FOREIGN KEY (`idfilm`) REFERENCES `film` (`idfilm`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idkino` FOREIGN KEY (`idkino`) REFERENCES `kino` (`idkino`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `predstaveni`
--

LOCK TABLES `predstaveni` WRITE;
/*!40000 ALTER TABLE `predstaveni` DISABLE KEYS */;
INSERT INTO `predstaveni` VALUES (1,'2018-04-01 00:00:00',1,1),(2,'2018-04-02 00:00:00',1,2),(3,'2018-04-03 00:00:00',1,3),(4,'2018-04-04 00:00:00',2,1),(5,'2018-04-05 00:00:00',2,2),(6,'2018-04-06 00:00:00',2,3);
/*!40000 ALTER TABLE `predstaveni` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-18 19:32:55
