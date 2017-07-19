CREATE SCHEMA `database_patcher`;

CREATE TABLE `database_patcher`.`availability_zone` (
  `id` int(11) NOT NULL,
  `zone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `database_patcher`.`data_types` (
  `id` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `database_patcher`.`db` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `region_id` int(11) DEFAULT NULL,
  `hostname` varchar(45) DEFAULT NULL,
  `port` varchar(5) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `last_patch_timestamp` timestamp NULL DEFAULT NULL,
  `last_status` tinyint(4) DEFAULT NULL,
  `current_version` varchar(25) DEFAULT NULL,
  `next_patch_timestamp` timestamp NULL DEFAULT NULL,
  `target_version` varchar(25) DEFAULT NULL,
  `data_type_id` int(11) DEFAULT NULL,
  `availability_zone_id` int(11) DEFAULT NULL,
  `patch_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `database_patcher`.`db_patch_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `db_id` int(11) NOT NULL,
  `patch_id` int(11) NOT NULL,
  `patch_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `database_patcher`.`patch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patch_description` varchar(1000) NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `patch_script` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `database_patcher`.`region` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

INSERT INTO `database_patcher`.`availability_zone` (`id`,`zone`) VALUES (0,'All');
INSERT INTO `database_patcher`.`availability_zone` (`id`,`zone`) VALUES (1,'NHS');
INSERT INTO `database_patcher`.`availability_zone` (`id`,`zone`) VALUES (2,'Internet');
INSERT INTO `database_patcher`.`availability_zone` (`id`,`zone`) VALUES (3,'Development');
INSERT INTO `database_patcher`.`availability_zone` (`id`,`zone`) VALUES (4,'Testing');

INSERT INTO `database_patcher`.`data_types` (`id`,`type`) VALUES (0,'All');
INSERT INTO `database_patcher`.`data_types` (`id`,`type`) VALUES (1,'EHR');
INSERT INTO `database_patcher`.`data_types` (`id`,`type`) VALUES (2,'Configuration');
INSERT INTO `database_patcher`.`data_types` (`id`,`type`) VALUES (3,'Keycloak');
INSERT INTO `database_patcher`.`data_types` (`id`,`type`) VALUES (4,'Audit');
INSERT INTO `database_patcher`.`data_types` (`id`,`type`) VALUES (5,'Transaction Log');
INSERT INTO `database_patcher`.`data_types` (`id`,`type`) VALUES (6,'Information Model');
INSERT INTO `database_patcher`.`data_types` (`id`,`type`) VALUES (7,'Enterprise');

INSERT INTO `database_patcher`.`region` (`id`,`name`) VALUES (0,'All');
INSERT INTO `database_patcher`.`region` (`id`,`name`) VALUES (1,'East London Discovery');
INSERT INTO `database_patcher`.`region` (`id`,`name`) VALUES (2,'Development System');


