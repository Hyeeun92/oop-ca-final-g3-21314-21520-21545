-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: oop_final
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `administrator_id` varchar(10) NOT NULL,
  `administrator_password` varchar(45) DEFAULT NULL,
  `administrator_name` varchar(45) DEFAULT NULL,
  `administrator_Lname` varchar(45) DEFAULT NULL,
  `administrator_email` varchar(45) DEFAULT NULL,
  `administrator_address` varchar(60) DEFAULT NULL,
  `administrator_gender` varchar(10) DEFAULT NULL,
  `branch_Bno` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`administrator_id`),
  KEY `branch_Bno` (`branch_Bno`),
  CONSTRAINT `administrator_ibfk_1` FOREIGN KEY (`branch_Bno`) REFERENCES `branch` (`branch_Bno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES ('Admin1','Admin1','Hyeeun','Lee','21545@student.dorset-college.ie','Rathmines','F','VGC-1'),('Admin2','Admin2','Liubov','Eremenko','21314@student.dorset-college.ie','Phibsborough','F','VGC-2'),('Admin3','Admin3','Nathaile','Flores','21520@student.dorset-college.ie','Sandymount','F','VGC-3');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `class_id` varchar(30) NOT NULL,
  `student_id` varchar(30) NOT NULL,
  `date` varchar(30) DEFAULT NULL,
  `statement` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`class_id`,`student_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `schedule` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `attendance_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `branch_Bno` varchar(20) NOT NULL,
  `branch_street` varchar(40) DEFAULT NULL,
  `branch_area` varchar(20) DEFAULT NULL,
  `branch_city` varchar(20) DEFAULT NULL,
  `branch_pcode` varchar(9) NOT NULL,
  `branch_tel_no` varchar(12) DEFAULT NULL,
  `branch_fax_no` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`branch_Bno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES ('VGC-1','8 Belvedere Pl','Mountjoy','Dublin','D01 EV27','0171-8861212','0171-8861214'),('VGC-2','14 Georges Quay','Ballintemple','Cork','T12 EY24','01224-67125','01224-67111'),('VGC-3','Siobhan McKenna Rd','Newcastle','Galway','H91 E9D9','0141-3392178','0141-3394439');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `course_id` varchar(30) NOT NULL,
  `course_name` varchar(50) DEFAULT NULL,
  `course_price` double DEFAULT NULL,
  `course_comments` varchar(30) DEFAULT NULL,
  `branch_Bno` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `branch_Bno` (`branch_Bno`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`branch_Bno`) REFERENCES `branch` (`branch_Bno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('BSC-CS1','Bachelor in Sciences of Computer Year1',3500,NULL,'VGC-1'),('BSC-CS2','Bachelor in Sciences of Computer Year2',4700,NULL,'VGC-1'),('BSC-CS3','Bachelor in Sciences of Computer Year3',5000,'No Class','VGC-1'),('courseId','samplecourse',100,'No lecturer defined','VGC-1');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grade` (
  `course_id` varchar(30) NOT NULL,
  `class_id` varchar(30) NOT NULL,
  `student_id` varchar(30) NOT NULL,
  `grade` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`course_id`,`class_id`,`student_id`),
  KEY `grade_ibfk_1` (`class_id`),
  KEY `grade_ibfk_2` (`student_id`),
  CONSTRAINT `grade_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `schedule` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `grade_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `grade_ibfk_3` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture`
--

DROP TABLE IF EXISTS `lecture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture` (
  `lecture_id` varchar(15) NOT NULL,
  `lecture_password` varchar(45) DEFAULT NULL,
  `lecture_name` varchar(45) DEFAULT NULL,
  `lecture_Lname` varchar(45) DEFAULT NULL,
  `lecture_email` varchar(45) DEFAULT NULL,
  `lecture_address` varchar(60) DEFAULT NULL,
  `lecture_gender` varchar(10) DEFAULT NULL,
  `branch_Bno` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`lecture_id`),
  KEY `branch_Bno` (`branch_Bno`),
  CONSTRAINT `lecture_ibfk_1` FOREIGN KEY (`branch_Bno`) REFERENCES `branch` (`branch_Bno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture`
--

LOCK TABLES `lecture` WRITE;
/*!40000 ALTER TABLE `lecture` DISABLE KEYS */;
INSERT INTO `lecture` VALUES ('lect-D9','df-9-46','David','Ford','dfglass@gmail.com','35 Leopardstown Drive, Leopardstown, Co. Dublin','M','VGC-1'),('lect-M8','mh-8-138','Mary','Howe','mhflowers@gmail.com','38 Merrion Grove, Bootestown, Co. Dublin','F','VGC-1'),('lecture','lecture','lecture','lecture','lecture','lecture','F',NULL);
/*!40000 ALTER TABLE `lecture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `course_id` varchar(30) DEFAULT NULL,
  `student_id` varchar(30) DEFAULT NULL,
  `type_of_pay` varchar(45) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  KEY `course_id` (`course_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES ('BSC-CS1','st21-M9','full',3500),('BSC-CS2','st21-E11','instalment',2000);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `class_id` varchar(30) NOT NULL,
  `finish_date` varchar(45) NOT NULL,
  `lecture_id` varchar(15) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `course_id` varchar(30) DEFAULT NULL,
  `information` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`class_id`,`finish_date`),
  KEY `lecture_id` (`lecture_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `schedule_ibfk_2` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`lecture_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `schedule_ibfk_3` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES ('C21-Fro','12/05/2021','lect-M8','exam','BSC-CS1',NULL),('C21-Mat','12/05/2021','lect-D9','exam','BSC-CS2','Morning Test'),('C21-Mat','26/5/2021','lect-D9','Exam','BSC-CS1','final exam'),('C21-Mat','28/5/2021','lect-D9','CA','BSC-CS1','final CA');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` varchar(30) NOT NULL,
  `student_password` varchar(20) DEFAULT NULL,
  `student_Fname` varchar(45) DEFAULT NULL,
  `student_Lname` varchar(45) DEFAULT NULL,
  `student_email` varchar(45) DEFAULT NULL,
  `student_address` varchar(60) DEFAULT NULL,
  `student_gender` varchar(10) DEFAULT NULL,
  `branch_Bno` varchar(20) DEFAULT NULL,
  `course_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `course_id` (`course_id`),
  KEY `branch_Bno` (`branch_Bno`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`branch_Bno`) REFERENCES `branch` (`branch_Bno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('st21-E11','et-11-520','Eoin','Thierny','etmoon@gmail.com','40 Seafield Cresent, Bootestown, Co. Dublin','M','VGC-1','BSC-CS2'),('st21-M9','1111','Mark','Kenny','mwsunny@gmail.com','31 Oakley Park, Blackrock, Co. Dublin','M','VGC-1','BSC-CS1');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timetable`
--

DROP TABLE IF EXISTS `timetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timetable` (
  `course_id` varchar(30) NOT NULL,
  `class_id` varchar(30) NOT NULL,
  `class_name` varchar(45) DEFAULT NULL,
  `lecture_id` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`class_id`,`course_id`),
  KEY `lecture_id` (`lecture_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `timetable_ibfk_1` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`lecture_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `timetable_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timetable`
--

LOCK TABLES `timetable` WRITE;
/*!40000 ALTER TABLE `timetable` DISABLE KEYS */;
INSERT INTO `timetable` VALUES ('BSC-CS3','C21-Dev','Dev Ops','lect-M8'),('BSC-CS1','C21-Fro','Frontend Web Development','lect-M8'),('BSC-CS1','C21-Mat','Mathematics for IT 1','lect-D9'),('BSC-CS2','C21-Mat','Mathematics for IT 2','lect-D9'),('BSC-CS3','C21-Obj','Object Oriented Programming Advanced','lect-D9'),('BSC-CS2','C21-Ope','Operating Systems and Administration','lect-M8');
/*!40000 ALTER TABLE `timetable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-04 15:08:31
