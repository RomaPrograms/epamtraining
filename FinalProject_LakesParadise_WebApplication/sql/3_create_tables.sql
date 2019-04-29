CREATE TABLE `profiles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_profiles_login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(10) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `phone` bigint(12) NOT NULL,
  `image` mediumblob,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_users_profiles_id` FOREIGN KEY (`id`) REFERENCES `profiles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `homesteads` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `price` decimal(10,3) NOT NULL,
  `description` mediumtext NOT NULL,
  `people_number` tinyint(6) NOT NULL,
  `rating` double DEFAULT NULL,
  `number_of_voted_users` bigint(11) DEFAULT NULL,
  `owner_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_homesteads_owner_id` (`owner_id`),
  KEY `IDX_homesteads_title` (`title`),
  KEY `IDX_homesteads_price` (`price`),
  KEY `IDX_homesteads_people_number` (`people_number`),
  CONSTRAINT `FK_homesteads_users_id` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` mediumtext NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `date_of_comment` date NOT NULL,
  `home_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_reviews_home_id` (`home_id`),
  CONSTRAINT `FK_reviews_homesteads_id` FOREIGN KEY (`home_id`) REFERENCES `homesteads` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

REATE TABLE `images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` mediumblob NOT NULL,
  `home_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_images_home_id` (`home_id`),
  CONSTRAINT `FK_images_homesteads_id` FOREIGN KEY (`home_id`) REFERENCES `homesteads` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `home_id` int(11) NOT NULL,
  `date_start` date NOT NULL,
  `date_end` date NOT NULL,
  `status_pay` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_orders_home_id` (`home_id`),
  KEY `IDX_orders_user_id` (`user_id`),
  CONSTRAINT `FK_orders_homesteads_id` FOREIGN KEY (`home_id`) REFERENCES `homesteads` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_orders_users_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;