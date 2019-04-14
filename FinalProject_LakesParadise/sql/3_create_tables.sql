
USE `lakes_paradise_db`;

CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NOT NULL,
  `surname` VARCHAR(30) NOT NULL,
  `phone` VARCHAR(13) NOT NULL,
  `town` VARCHAR(20) NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `profiles` (
  `id` INT NOT NULL,
  `login` VARCHAR(30) NOT NULL,
  `password` VARCHAR(25) NOT NULL,
  `orders` INT(2) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  CONSTRAINT `id`
  FOREIGN KEY (`id`)
  REFERENCES `lakes_paradise_db`.`users` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `homestead` ( `id` int(11) NOT NULL AUTO_INCREMENT,
 `title` varchar(45) NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `description` mediumtext NOT NULL,
  `rating` int(11) DEFAULT NULL,
  `id_user` varchar(45) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lakes_paradise_db`.`orders` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `id_user` INT NOT NULL,
 `id_home` INT NOT NULL,
 `date_start` DATE NOT NULL,
 `date_end` DATE NOT NULL,
 `status_pay` TINYINT NULL,
 `number_people` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_user_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `id_user`
  FOREIGN KEY (`id_user`)
  REFERENCES `lakes_paradise_db`.`users` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (`id_home`)
  REFERENCES `lakes_paradise_db`.homesteads (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `images` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `image` blob NOT NULL,
   `id_home` int(11) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `id_home_idx` (`id_home`),
    CONSTRAINT `home_id` FOREIGN KEY (`id_home`) REFERENCES homesteads (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `stuff` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL,
  `password` VARCHAR(30) NULL,
   /*
	 * 0 - администратор (Role.ADMINISTRATOR)
	 * 1 - архтивариус (Role.REGISTRAR)
	 * 2 - библиотекарь (Role.LIBRARIAN)
	 */
  `role` TINYINT NOT NULL CHECK (`role` IN (0, 1, 2)),
  PRIMARY KEY (`id`),
UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `owner` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(15) NOT NULL,
  `surname` VARCHAR(30) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `id_homestead`
  FOREIGN KEY (`id`)
  REFERENCES `lakes_paradise_db`.homesteads (`id`)
ON DELETE CASCADE
ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
