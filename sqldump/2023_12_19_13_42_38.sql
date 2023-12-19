-- MariaDB dump 10.19-11.2.2-MariaDB, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: PBO
-- ------------------------------------------------------
-- Server version	10.4.28-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admins` (
  `admin_id` int(11) NOT NULL,
  `admin_name` varchar(25) DEFAULT NULL,
  `admin_alamat` varchar(30) DEFAULT NULL,
  `admin_email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  CONSTRAINT `admins_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `credentials` (`credentials_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES
(1,'anon','Jl.ABC','anon@...');
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credentials`
--

DROP TABLE IF EXISTS `credentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credentials` (
  `credentials_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`credentials_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credentials`
--

LOCK TABLES `credentials` WRITE;
/*!40000 ALTER TABLE `credentials` DISABLE KEYS */;
INSERT INTO `credentials` VALUES
(1,'admin','admin'),
(2,'dokter1','dokter1'),
(3,'dokter2','dokter2(Changed)'),
(8,'pasien','pasien'),
(9,'dokter3','dokter3'),
(10,'dokter4','dokter4');
/*!40000 ALTER TABLE `credentials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dokter`
--

DROP TABLE IF EXISTS `dokter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dokter` (
  `dokter_id` int(11) NOT NULL,
  `dokter_nama` varchar(30) DEFAULT NULL,
  `dokter_alamat` varchar(30) DEFAULT NULL,
  `dokter_email` varchar(30) DEFAULT NULL,
  `dokter_telp` varchar(30) DEFAULT NULL,
  `info` int(11) DEFAULT NULL,
  PRIMARY KEY (`dokter_id`),
  KEY `info` (`info`),
  CONSTRAINT `dokter_ibfk_1` FOREIGN KEY (`dokter_id`) REFERENCES `credentials` (`credentials_id`),
  CONSTRAINT `dokter_ibfk_2` FOREIGN KEY (`info`) REFERENCES `dokter_info` (`dokter_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dokter`
--

LOCK TABLES `dokter` WRITE;
/*!40000 ALTER TABLE `dokter` DISABLE KEYS */;
INSERT INTO `dokter` VALUES
(2,'Anon','Jl.ABC','anon@yahoo.com','08123456',1),
(3,'Anon 2 (Changed)','Jl.DEF','anon2@yahoo.com','08123455',2),
(9,'Anon 3','Jl.HIJ','anon3@yahoo.com','08123454',1),
(10,'Anon 4','Jl.KLM','anon4@yahoo.com','08123453',2);
/*!40000 ALTER TABLE `dokter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dokter_info`
--

DROP TABLE IF EXISTS `dokter_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dokter_info` (
  `dokter_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(100) DEFAULT NULL,
  `specialist` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`dokter_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dokter_info`
--

LOCK TABLES `dokter_info` WRITE;
/*!40000 ALTER TABLE `dokter_info` DISABLE KEYS */;
INSERT INTO `dokter_info` VALUES
(1,'Neurology','Neurologist'),
(2,'Dermatology','Dermatologist');
/*!40000 ALTER TABLE `dokter_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `janji_temu`
--

DROP TABLE IF EXISTS `janji_temu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `janji_temu` (
  `janji_temu_id` int(11) NOT NULL AUTO_INCREMENT,
  `tanggal` date DEFAULT NULL,
  `waktu` time DEFAULT NULL,
  `desc` varchar(511) DEFAULT NULL,
  `dokter_id` int(11) DEFAULT NULL,
  `pasien_id` int(11) DEFAULT NULL,
  `status` enum('Pending','Approved','Rejected') DEFAULT 'Pending',
  PRIMARY KEY (`janji_temu_id`),
  KEY `dokter_id` (`dokter_id`),
  KEY `pasien_id` (`pasien_id`),
  CONSTRAINT `janji_temu_ibfk_1` FOREIGN KEY (`dokter_id`) REFERENCES `dokter` (`dokter_id`),
  CONSTRAINT `janji_temu_ibfk_2` FOREIGN KEY (`pasien_id`) REFERENCES `pasien` (`pasien_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `janji_temu`
--

LOCK TABLES `janji_temu` WRITE;
/*!40000 ALTER TABLE `janji_temu` DISABLE KEYS */;
INSERT INTO `janji_temu` VALUES
(1,'2002-12-21','07:30:00','pengen ke gensokyo',2,8,'Approved'),
(2,'2023-01-02','13:00:00','lorem isum',3,8,NULL),
(4,'2022-12-21','14:00:00','ackchually...',2,8,'Rejected'),
(5,'2023-12-12','16:00:00','ackchualyy...',3,8,'Pending');
/*!40000 ALTER TABLE `janji_temu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasien`
--

DROP TABLE IF EXISTS `pasien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pasien` (
  `pasien_id` int(11) NOT NULL,
  `pasien_nama` varchar(30) DEFAULT NULL,
  `pasien_alamat` varchar(30) DEFAULT NULL,
  `pasien_email` varchar(30) DEFAULT NULL,
  `pasien_telp` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`pasien_id`),
  CONSTRAINT `pasien_ibfk_1` FOREIGN KEY (`pasien_id`) REFERENCES `credentials` (`credentials_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasien`
--

LOCK TABLES `pasien` WRITE;
/*!40000 ALTER TABLE `pasien` DISABLE KEYS */;
INSERT INTO `pasien` VALUES
(8,'pasien(Changed)','Jl.ABC','pasien@yahoo.com','0812345671');
/*!40000 ALTER TABLE `pasien` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-19 13:42:38
