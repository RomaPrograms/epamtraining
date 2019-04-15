USE `lakes_paradise_db`;

CREATE TABLE `profiles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `orders` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `town` varchar(20) DEFAULT NULL,
  CONSTRAINT `PK_users` PRIMARY KEY (`id`),
  CONSTRAINT `FK_profiles` FOREIGN KEY (`id`) REFERENCES `profiles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `stuff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` tinyint(4) NOT NULL,
  CONSTRAINT `PK_stuff` PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `owners` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `phone` varchar(15) NOT NULL,
  CONSTRAINT `PK_owners` PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `homesteads` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `description` mediumtext NOT NULL,
  `people_number` int(11) NOT NULL,
  `rating` double DEFAULT NULL,
  `id_owner` int(11) NOT NULL,
  CONSTRAINT `PK_homesteads` PRIMARY KEY (`id`),
  KEY `ID_owner` (`id_owner`),
  CONSTRAINT `FK_owner` FOREIGN KEY (`id_owner`) REFERENCES `owners`
  (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` blob NOT NULL,
  `id_home` int(11) NOT NULL,
  CONSTRAINT `PK_images` PRIMARY KEY (`id`),
  KEY `id_home_idx` (`id_home`),
  CONSTRAINT `FK_homestead` FOREIGN KEY (`id_home`) REFERENCES `homesteads`
  (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_profile` int(11) NOT NULL,
  `id_home` int(11) NOT NULL,
  `date_start` date NOT NULL,
  `date_end` date NOT NULL,
  `status_pay` tinyint(4) DEFAULT NULL,
  CONSTRAINT `PK_orders` PRIMARY KEY (`id`),
  KEY `ID_user` (`id_profile`),
  KEY `ID_home` (`id_home`),
  CONSTRAINT `FK_user` FOREIGN KEY (`id_profile`) REFERENCES `users`
  (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_orders` FOREIGN KEY (`id_home`) REFERENCES `homesteads` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;