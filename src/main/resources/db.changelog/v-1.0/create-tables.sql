CREATE SCHEMA IF NOT EXISTS `carsharing` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
GO
USE `carsharing`;
GO
CREATE TABLE IF NOT EXISTS `carsharing`.`rate` (
  `id` BIGINT(255) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `type` VARCHAR(255) NOT NULL,
  `cost` BIGINT(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
GO
CREATE TABLE IF NOT EXISTS `carsharing`.`specialist` (
  `id` BIGINT(255) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `phoneNumber_UNIQUE` (`phone_number` ASC) VISIBLE)
ENGINE = InnoDB;
GO
CREATE TABLE IF NOT EXISTS `carsharing`.`customer` (
  `id` BIGINT(255) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `passport_number` VARCHAR(255) NULL,
  `license_number` VARCHAR(255) NULL,
  `is_verified` BIT(1) NULL DEFAULT 0,
  `specialist_id` BIGINT(255) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `passportNumber_UNIQUE` (`passport_number` ASC) VISIBLE,
  UNIQUE INDEX `phoneNumber_UNIQUE` (`phone_number` ASC) VISIBLE,
  INDEX `fk_customer_specialist1_idx` (`specialist_id` ASC) VISIBLE,
  UNIQUE INDEX `license_number_UNIQUE` (`license_number` ASC) VISIBLE,
  CONSTRAINT `fk_customer_specialist1`
    FOREIGN KEY (`specialist_id`)
    REFERENCES `carsharing`.`specialist` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
GO
CREATE TABLE IF NOT EXISTS `carsharing`.`car` (
  `id` BIGINT(255) NOT NULL,
  `car_type` VARCHAR(255) NOT NULL,
  `car_status` VARCHAR(255) NOT NULL,
  `brand` VARCHAR(255) NOT NULL,
  `fuel` INT NOT NULL,
  `rate_id` BIGINT(255) NOT NULL,
  `customer_id` BIGINT(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_car_rate1_idx` (`rate_id` ASC) VISIBLE,
  INDEX `fk_car_customer1_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_car_rate1`
    FOREIGN KEY (`rate_id`)
    REFERENCES `carsharing`.`rate` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_car_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `carsharing`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
GO
CREATE TABLE IF NOT EXISTS `carsharing`.`deal` (
  `id` BIGINT(255) NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(255) NOT NULL,
  `date` DATE NOT NULL,
  `description` VARCHAR(300) NOT NULL,
  `customer_id` BIGINT(255) NOT NULL,
  `car_id` BIGINT(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_deal_customer1_idx` (`customer_id` ASC) VISIBLE,
  INDEX `fk_deal_car1_idx` (`car_id` ASC) VISIBLE,
  CONSTRAINT `fk_deal_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `carsharing`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_deal_car1`
    FOREIGN KEY (`car_id`)
    REFERENCES `carsharing`.`car` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
GO
CREATE TABLE IF NOT EXISTS `carsharing`.`card` (
  `id` BIGINT(255) NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(255) NOT NULL,
  `code` VARCHAR(255) NOT NULL,
  `term` DATE NOT NULL,
  `customer_id` BIGINT(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE,
  INDEX `fk_card_customer1_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_card_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `carsharing`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
GO
CREATE TABLE IF NOT EXISTS `carsharing`.`role` (
  `id` BIGINT(255) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;
GO
CREATE TABLE IF NOT EXISTS `carsharing`.`customer_role` (
  `customer_id` BIGINT(255) NOT NULL,
  `role_id` BIGINT(255) NOT NULL,
  PRIMARY KEY (`customer_id`, `role_id`),
  INDEX `fk_customer_role_role1_idx` (`role_id` ASC) VISIBLE,
  INDEX `fk_customer_role_customer1_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_customer_role_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `carsharing`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `carsharing`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
