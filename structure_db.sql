/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table caregiver
# ------------------------------------------------------------

DROP TABLE IF EXISTS `caregiver`;

CREATE TABLE `caregiver` (
  `licence_number` varchar(255) DEFAULT NULL,
  `caregiver_id` int(11) NOT NULL,
  PRIMARY KEY (`caregiver_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table consumable
# ------------------------------------------------------------

DROP TABLE IF EXISTS `consumable`;

CREATE TABLE `consumable` (
  `id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `threshold` int(11) DEFAULT NULL,
  `consumable_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqoj1peb5pmpfs6v8168x9bfjg` (`consumable_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table consumable_type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `consumable_type`;

CREATE TABLE `consumable_type` (
  `id` int(11) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table employee
# ------------------------------------------------------------

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `employment_date` datetime DEFAULT NULL,
  `salary` float NOT NULL,
  `work_schedule` varchar(255) DEFAULT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table equipment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `equipment`;

CREATE TABLE `equipment` (
  `id` int(11) NOT NULL,
  `installation_date` datetime DEFAULT NULL,
  `equipment_type_id` int(11) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKog1e3ls88y65004cs34gtncgs` (`equipment_type_id`),
  KEY `FKrlxmj3jqo3mnp532alg49ievg` (`room_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table equipment_type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `equipment_type`;

CREATE TABLE `equipment_type` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table health_file
# ------------------------------------------------------------

DROP TABLE IF EXISTS `health_file`;

CREATE TABLE `health_file` (
  `id` int(11) NOT NULL,
  `chronic_conditions` varchar(255) DEFAULT NULL,
  `emergency_contact` varchar(255) DEFAULT NULL,
  `medications` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table hibernate_sequence
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table patient
# ------------------------------------------------------------

DROP TABLE IF EXISTS `patient`;

CREATE TABLE `patient` (
  `patient_id` int(11) NOT NULL,
  `healthfile_id` int(11) DEFAULT NULL,
  `primary_doctor_caregiver_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`patient_id`),
  KEY `FKphibxjbc32bf7bp35eexvcog0` (`healthfile_id`),
  KEY `FK772m09qprd692almw4kcphy3f` (`primary_doctor_caregiver_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table room
# ------------------------------------------------------------

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table secretary
# ------------------------------------------------------------

DROP TABLE IF EXISTS `secretary`;

CREATE TABLE `secretary` (
  `secretary_id` int(11) NOT NULL,
  PRIMARY KEY (`secretary_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table ticket
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ticket`;

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `request_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table ticket_consumables
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ticket_consumables`;

CREATE TABLE `ticket_consumables` (
  `ticket_id` int(11) NOT NULL,
  `consumable_id` int(11) NOT NULL,
  PRIMARY KEY (`ticket_id`,`consumable_id`),
  KEY `FK4mh898wnxr7r5ult43nmk0n0y` (`consumable_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` longtext,
  `phone_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table user_rights
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_rights`;

CREATE TABLE `user_rights` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table user_roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKcp0a32yof5itawn9l2vn3yjbm` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
