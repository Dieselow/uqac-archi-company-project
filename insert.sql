# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.35)
# Database: archi
# Generation Time: 2021-09-30 23:00:34 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table user_rights
# ------------------------------------------------------------



LOCK TABLES `user_rights` WRITE;
/*!40000 ALTER TABLE `user_rights` DISABLE KEYS */;

INSERT INTO `user_rights` (`id`, `name`)
VALUES
	(0,'ADMIN'),
	(1,'USER'),
	(2,'SECRETARY'),
	(3,'CAREGIVER');

/*!40000 ALTER TABLE `user_rights` ENABLE KEYS */;
UNLOCK TABLES;


 -- INSERT MEDECIN
INSERT INTO `user`  VALUES(1, 'DDupont', '123456', 'David', 'Dupont', '2000-06-02', 'david.dupont@gmail.com', '7 rue des Corneliens 60200 Compiegne', '+33770471615', 5000, 'Mercredi 10-20H' ,'2019-08-01', 'AB544885', 3, 2);
INSERT INTO `user`  VALUES(2, 'JDoe', '123456', 'John', 'Doe', '2000-08-09', 'john.doe@gmail.com', '8 rue des Chicoutiméens', '+1 418 514 7788', 80000, 'Mercredi 10-20H' ,'2011-08-01', 'AB544886', 3, 2);

 -- INSERT SECRETARY
INSERT INTO `user`  VALUES(3, 'JDoe', '123456', 'John', 'Secretary', '2000-08-09', 'john.doe@gmail.com', '8 rue des Chicoutiméens', '+1 418 514 7788', 80000, 'Mercredi 10-20H' ,'2011-08-01', 'AB544886', 2, 1);
INSERT INTO `user`  VALUES(4, 'JDoe', '123456', 'Paul', 'Secretary', '2000-08-09', 'john.doe@gmail.com', '8 rue des Chicoutiméens', '+1 418 514 7788', 80000, 'Mercredi 10-20H' ,'2011-08-01', 'AB544886', 2, 1);

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
