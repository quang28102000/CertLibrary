-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: certlibrary
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_tittle` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `platform` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category` varchar(256) NOT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `course_id_UNIQUE` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'The Complete SQL Bootcamp 2022: Go from Zero to Hero','Udemy','SQL'),(2,'Web Design: Wireframes to Prototypes','Coursera','Web Design'),(3,'Explore All Data Science','CodeCademy','Data'),(4,'CS50\'s Introduction to Game Development','EdX','Game programming'),(5,'Basic Angular 4x Programming','Edumall','Angular'),(6,'The MERN Fullstack Guide','Udemy','Fullstack'),(7,'Node with React: Fullstack Web Development','Udemy','Fullstack'),(8,'Full Stack Web Development with Angular','Coursera','Fullstack'),(9,'Connecting Front-End to Back-End','CodeCademy','Fullstack'),(10,'Spring Boot Tutorial | Spring Boot Full Stack with React.js','Udemy','Fullstack'),(11,'The Complete 2022 Web Development Bootcamp','Udemy','Fullstack'),(12,'Khóa học thứ 12','Udemy','Fullstack'),(13,'Testing Coruse ','Udemy','SQL');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_detail`
--

DROP TABLE IF EXISTS `course_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_detail` (
  `course_detail_id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(500) DEFAULT NULL,
  `course_id` int NOT NULL,
  `course_length` float DEFAULT NULL,
  PRIMARY KEY (`course_detail_id`),
  UNIQUE KEY `course_detail_id_UNIQUE` (`course_detail_id`),
  UNIQUE KEY `course_id_UNIQUE` (`course_id`),
  CONSTRAINT `couse_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_detail`
--

LOCK TABLES `course_detail` WRITE;
/*!40000 ALTER TABLE `course_detail` DISABLE KEYS */;
INSERT INTO `course_detail` VALUES (1,'https://coursemarks.com/wp-content/uploads/2020/11/762616_7693_3.jpg.webp                                                                                                                                                                                       ',1,8.51),(2,'https://s3.amazonaws.com/coursera_assets/meta_images/generated/XDP/XDP~COURSE!~web-design-wireframes-prototypes/XDP~COURSE!~web-design-wireframes-prototypes.jpeg                                                                                               ',2,41),(3,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnKSKdmkM60RHg2l6ngoMsHbEUvmOsLR3fmaNgXPYgeWuKjWLUG3xW2TTh7WiW9rFNiqc&usqp=CAU                                                                                                                            ',3,20),(4,'https://aseanop.com/wp-content/uploads/2020/05/CS50s-Introduction-to-Game-Development.jpg                                                                                                                                                                       ',4,25),(5,'https://www.zendvn.com/img/upload/courses/desktop_97dc1df2d48219e2f70a285b66307ca9fbf7bf6c.jpeg                                                                                                                                                                 ',5,21),(6,'https://www.filepicker.io/api/file/TMGv8UTlyDnfF5jKec0w                                                                                                                                                                                                         ',6,23),(7,'https://www.gangboard.com/wp-content/uploads/2020/02/node-with-react-fullstack-web-development-course.jpg                                                                                                                                                       ',7,30),(8,'https://i.ytimg.com/vi/eRZHzi3l-4Q/mqdefault.jpg                                                                                                                                                                                                                ',8,28),(9,'https://connect.vn/wp-content/uploads/2019/12/front-end-back-end-l%C3%A0-g%C3%AC.png                                                                                                                                                                            ',9,29),(10,'https://miro.medium.com/max/1200/1*NqhTGwmPUYzCwBcYq5fDSQ.jpeg                                                                                                                                                                                                  ',10,27),(11,'https://i0.wp.com/tutorialsplanet.net/wp-content/uploads/2020/06/455553363.jpg?fit=750%2C422&ssl=1                                                                                                                                                              ',11,33),(12,'\"Hình ảnh khóa học 12\"',12,10),(13,'Hình ảnh đã chỉnh sửa',13,10);
/*!40000 ALTER TABLE `course_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_employee`
--

DROP TABLE IF EXISTS `course_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_employee` (
  `course_id` int NOT NULL,
  `employee_id` int NOT NULL,
  `status` int NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `cert_link` varchar(256) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`course_id`,`employee_id`),
  KEY `employee_id_idx` (`employee_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `employee_id` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_employee`
--

LOCK TABLES `course_employee` WRITE;
/*!40000 ALTER TABLE `course_employee` DISABLE KEYS */;
INSERT INTO `course_employee` VALUES (1,4,4,'2022-03-08 00:00:00','2021-10-08 00:00:00','https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ',1),(1,19,1,'2022-02-26 00:00:00',NULL,NULL,1),(1,20,2,'2022-03-17 07:00:00','2022-03-23 07:00:00','Đường dẫn khóa web của Minh Quang',1),(2,3,1,'2022-02-28 00:00:00',NULL,NULL,1),(2,11,2,'2022-01-02 00:00:00','2022-01-19 00:00:00','https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ',1),(2,15,1,'2022-02-22 00:00:00',NULL,NULL,1),(2,19,2,'2022-02-10 00:00:00','2021-12-27 00:00:00','https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ',1),(3,4,2,'2022-02-17 00:00:00','2019-09-12 00:00:00',NULL,0),(3,7,1,'2022-02-28 00:00:00',NULL,NULL,0),(3,15,1,'2022-02-25 00:00:00','2022-01-20 00:00:00',NULL,0),(3,20,2,'2022-03-18 00:00:00','2022-03-26 00:00:00','Chứng chỉ của khóa mới Data Science',0),(4,2,2,'2022-02-17 00:00:00','2021-01-15 00:00:00','https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ',0),(4,11,4,'2022-02-16 00:00:00',NULL,NULL,0),(5,1,2,'2022-01-30 00:00:00',NULL,NULL,0),(5,5,3,'2022-01-01 00:00:00','2011-01-30 00:00:00',NULL,0),(5,19,3,'2022-03-11 00:00:00','2022-02-10 00:00:00',NULL,0),(6,2,4,'2022-02-12 00:00:00','2021-11-02 00:00:00',NULL,0),(6,10,1,'2022-02-24 00:00:00','2022-01-17 00:00:00','https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ',0),(6,15,2,'2022-03-04 00:00:00','2021-02-23 00:00:00','https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ',0),(7,1,2,'2022-02-11 00:00:00','2021-12-28 00:00:00',NULL,0),(7,10,2,'2022-01-12 00:00:00','2022-01-28 00:00:00',NULL,0),(7,19,4,'2022-01-01 00:00:00','2022-02-02 00:00:00',NULL,0),(8,6,2,'2022-02-13 00:00:00',NULL,NULL,0),(8,15,2,'2022-02-16 00:00:00','2020-10-22 00:00:00',NULL,0),(9,7,1,'2022-03-14 00:00:00',NULL,NULL,0),(9,15,3,'2022-03-13 00:00:00','2022-01-30 00:00:00','https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ',0),(11,7,4,'2022-01-17 00:00:00','2022-01-30 00:00:00','https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ',0),(11,19,1,'2022-03-15 07:00:00','2022-03-14 07:00:00','Đường dẫn chứng chỉ mới',0),(11,20,3,'2022-03-15 00:00:00',NULL,'update lần 1',0);
/*!40000 ALTER TABLE `course_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_skills`
--

DROP TABLE IF EXISTS `course_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_skills` (
  `skill_id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  PRIMARY KEY (`skill_id`,`course_id`),
  KEY `course_id_idx` (`course_id`),
  CONSTRAINT `course_skills_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `course_skills_ibfk_2` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`skill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_skills`
--

LOCK TABLES `course_skills` WRITE;
/*!40000 ALTER TABLE `course_skills` DISABLE KEYS */;
INSERT INTO `course_skills` VALUES (1,1),(2,2),(3,3),(4,3),(5,4),(6,5),(7,6),(8,6),(9,6),(10,6),(9,7),(10,7),(11,8),(12,8),(13,8),(14,8),(11,9),(12,9),(14,9),(15,10),(16,10),(11,11),(14,11),(15,11),(16,11),(17,12),(18,12),(19,12),(3,13),(9,13),(19,13),(20,13);
/*!40000 ALTER TABLE `course_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'David','Adams','david@gmail.com'),(2,'John','Doe','john@gmail.com'),(3,'Ajay','Rao','ajay@gmail.com'),(4,'Mary','Public','mary@gmail.com'),(5,'Maxwell','Dixon','max@gmail.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `profile_image` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `employee_id_UNIQUE` (`employee_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `profile_image_UNIQUE` (`profile_image`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Nguyễn Văn A','ANV1                                         ','Ahuygw435                                    ','ANV1@fsoft.com.vn                            ','https://camerabox.vn/uploads/news/2018_06/den-gan-chu-the.jpg                                                                                                                                                                                                   '),(2,'Nguyễn Việt Á','ANV2                                         ','Gsgrwe345                                    ','ANV2@fsoft.com.vn                            ','https://photocross.net/wp-content/uploads/2020/03/anh-chan-dung.jpg                                                                                                                                                                                             '),(3,'Nguyễn Thị Bé','BeNT                                         ','VG13HHhh                                     ','BeNT@fsoft.com.vn                            ','https://aphoto.vn/wp-content/uploads/2019/07/anh-chan-dung-nghe-thuat-top-aphoto5.jpg                                                                                                                                                                           '),(4,'Nguyễn Thị Cam','CamNT                                        ','dsh243uh                                     ','CamNT@fsoft.com.vn                           ','https://www.paratime.vn/wp-content/uploads/2019/09/timestudio.vn-headshot-eye-glasses-02.jpg                                                                                                                                                                    '),(5,'Lê Hữu Định','DinhLH                                       ','dsfnhu3728                                   ','DinhLH@fsoft.com.vn                          ','https://zstudio.vn/wp-content/uploads/2018/05/chup-anh-chan-dung-nam-5.jpg                                                                                                                                                                                      '),(6,'Phạm Luôn Đúng','DungPL                                       ','hui3ih34u2                                   ','DungPL@fsoft.com.vn                          ','https://chupanhvn.s3.ap-southeast-1.amazonaws.com/wp-content/uploads/2020/10/25114511/chan-dung-nam2.jpg                                                                                                                                                        '),(7,'Lưu Thị Dung','DungLT                                       ','huhui8927                                    ','DungLT@fsoft.com.vn                          ','https://chupanhvn.s3.ap-southeast-1.amazonaws.com/wp-content/uploads/2017/04/23050109/chup-anh-chan-dung-930x620.jpg                                                                                                                                            '),(8,'Trần Ngọc Dung','DungTN                                       ','euhwfw2786                                   ','DungTN@fsoft.com.vn                          ','https://digitalphoto.com.vn/wp-content/uploads/2018/08/40710181061_e790d61992_o-1-683x1024.jpg                                                                                                                                                                  '),(9,'Nguyễn Thị Nam Em','EmNTN                                        ','IUHhuiiu387                                  ','EmNTN@fsoft.com.vn                           ','https://vcdn-giaitri.vnecdn.net/2019/01/20/Nam-Em-2-3142-1548001119.jpg                                                                                                                                                                                         '),(10,'Hoàng Văn Hoàng','HoangHV                                      ','jhwqiu278                                    ','HoangHV@fsoft.com.vn                         ','https://photo-cms-baophapluat.zadn.vn/w800/Uploaded/2022/bpivpvoi/2020_11_03/1_8_zvmk.jpg                                                                                                                                                                       '),(11,'Kim Soo Huyn','HuynKS                                       ','hhyiu7823                                    ','HuynKS@.fsoft.com.vn                         ','https://file.vfo.vn/hinh/2018/02/nhung-hinh-chan-dung-dep-nhat-cach-chup-tao-dang-hinh-chup-chan-dung-29.jpg                                                                                                                                                    '),(12,'Lê Văn Luyện','LuyenLV                                      ','HJyguygtiu78                                 ','LuyenLV@fsoft.com.vn                         ','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnCOL1fWyrEEzXPP2Uql6WbI04fXrtrwrL4w&usqp=CAU                                                                                                                                                             '),(13,'Nguyễn Thị Ngọc Liên','LienNTN                                      ','Lyiuh728                                     ','LienNTN@fsoft.com.vn                         ','https://img.websosanh.vn/v10/users/review/images/xp5exn8diygzs/1551160483586_5370080.jpg?compress=85                                                                                                                                                            '),(14,'Nguyễn Văn Minh','MinhNV                                       ','jiuh787                                      ','MinhNV@fsoft.com.vn                          ','https://www.chapter3d.com/wp-content/uploads/2020/06/doi-mat-biet-noi-1.jpg                                                                                                                                                                                     '),(15,'Đinh Hoàng Oanh','OanhDH                                       ','hiu727                                       ','OanhDH@fsoft.com.vn                          ','https://www.chapter3d.com/wp-content/uploads/2020/06/doi-mat-biet-noi-1.jpg                                                                                                                                                                                     https://www.chapter3d.com/wp-content/uploads/2020/06/doi-mat-biet-noi-1.jpg                                                                                                                                                                         '),(16,'Lê Hồng Phúc','PhucLH                                       ','jiuh782                                      ','PhucLH@fsoft.com.vn                          ','https://i.pinimg.com/236x/eb/d6/8c/ebd68cc71d8db7b0fe1052994fa2e608.jpg                                                                                                                                                                                         '),(17,'Nguyễn Thị Lệ Quyên','QuyenNTL                                     ','uyguy86                                      ','QuyenNTL@fsoft.com.vn                        ','https://vcdn-vnexpress.vnecdn.net/2022/02/09/le-quyen-3428-1629359203-2723-1644389467.jpg                                                                                                                                                                       '),(18,'Nguyễn Sai','SaiN                                         ','Huyguy88                                     ','SaiN@fsoft.com.vn                            ','https://static.wikia.nocookie.net/naruto/images/e/ee/.................png/revision/latest?cb=20170823110922&path-prefix=vi                                                                                                                                      '),(19,'Hoàng Thượng','ThuongH                                      ','huuyg786                                     ','ThuongH@fsoft.com.vn                         ','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQoEUXyRXz7Y_F0CmxiGkB3Z-rfoX7l5oCyRA&usqp=CAU                                                                                                                                                             '),(20,'Võ Đình Minh Quang','QuangVDM','1234','Quang@gmail.com',NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_skills`
--

DROP TABLE IF EXISTS `employee_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_skills` (
  `e_skill_id` int NOT NULL AUTO_INCREMENT,
  `skill_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_id` int NOT NULL,
  PRIMARY KEY (`e_skill_id`),
  UNIQUE KEY `e_skill_id_UNIQUE` (`e_skill_id`),
  KEY `employee_id_idx` (`employee_id`),
  CONSTRAINT `employee_skills_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_skills`
--

LOCK TABLES `employee_skills` WRITE;
/*!40000 ALTER TABLE `employee_skills` DISABLE KEYS */;
INSERT INTO `employee_skills` VALUES (1,'SQL',1),(2,'CSS',1),(3,'HTML',1),(4,'Spring boot',1),(5,'Unity',2),(6,'.Net',2),(7,'Web Design',3),(8,'Logo Design',3),(9,'Game Design',4),(10,'Servlet',5),(11,'HTML',5),(12,'Game Design',7),(13,'Logo Design',7),(14,'HTML',10),(15,'CSS',10),(16,'Bootstrap',10),(17,'JSP',11),(18,'Servlet',11),(19,'Web Design',11),(20,'Unity',12),(21,'Game Design',13),(22,'Data',14),(23,'SQL',17),(24,'Spring boot',18),(25,'CSS',18),(26,'Unity',19),(27,'SQL',19),(28,'Spring Data JPA',15);
/*!40000 ALTER TABLE `employee_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skills` (
  `skill_id` int NOT NULL AUTO_INCREMENT,
  `skill_name` varchar(45) NOT NULL,
  PRIMARY KEY (`skill_id`),
  UNIQUE KEY `skill_id_UNIQUE` (`skill_id`),
  UNIQUE KEY `skill_name_UNIQUE` (`skill_name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` VALUES (6,'Angular'),(18,'Angular Material'),(16,'Bootstrap'),(12,'CSS'),(3,'Data'),(8,'Express JS'),(11,'HTML'),(13,'JSP'),(19,'Kỹ  năng mới'),(20,'Kỹ năng mới version2'),(4,'Machine learning'),(7,'Mongo DB'),(10,'Node JS'),(9,'React JS'),(17,'RxJS'),(14,'Servlet'),(15,'Spring boot'),(1,'SQL'),(5,'Unity'),(2,'Web Design');
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-04 14:07:19
