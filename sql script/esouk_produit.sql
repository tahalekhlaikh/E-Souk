-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: esouk
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `produit`
--

DROP TABLE IF EXISTS `produit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `designation` varchar(150) DEFAULT NULL,
  `prix` double DEFAULT NULL,
  `photo` varchar(45) DEFAULT NULL,
  `categorie` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `categorie_idx` (`categorie`),
  CONSTRAINT `categorie` FOREIGN KEY (`categorie`) REFERENCES `categorie` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produit`
--

LOCK TABLES `produit` WRITE;
/*!40000 ALTER TABLE `produit` DISABLE KEYS */;
INSERT INTO `produit` VALUES (1,'Pomme',5,'pomme',1),(2,'Banane',7,'banane',1),(3,'Fraise',11,'fraise',1),(4,'Kaki',15,'kaki',1),(5,'patateDouce',8,'patate_douce',2),(6,'Ananas',12,'ananas',1),(7,'Abricot',15,'abricot',1),(8,'ail',19,'ail',2),(9,'amande',15,'amande',1),(10,'aubergine',7,'aubergine',2),(11,'avocat',12,'avoca',1),(12,'brocoli',15,'brocoli',2),(13,'carotte',19,'carotte',2),(14,'cerise',14,'cerise',1),(15,'champignons',19,'champignons',2),(16,'chataigne',22,'chataigne',1),(17,'chou-fleur',16,'chou-fleur',2),(18,'chou-pomme',19,'chou-pomme',2),(19,'citron',5,'citron',1),(20,'mandarine',6,'clementine',1),(21,'concombre',8,'concombre',2),(22,'courgette',10,'courgette',2),(23,'datte',11,'datte',1),(24,'oignon',3,'oignon',2),(25,'grenade',12,'grenade',1),(26,'kiwi',15,'kiwi',1),(27,'mangue',18,'mangue',1),(28,'noix',22,'Noix',1),(29,'Noix de Coco',20,'noix-de-coco',1);
/*!40000 ALTER TABLE `produit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-26 18:56:48
