USE `carsharing`;
GO
DELETE FROM `deal`
GO
DELETE FROM `card`
GO
DELETE FROM `customer_role`
GO
DELETE FROM `car`
GO
DELETE FROM `customer`
GO
DELETE FROM `specialist`
GO
DELETE FROM `role`
GO
DELETE FROM `rate`
GO
ALTER TABLE `deal` DROP COLUMN `date`;
GO
ALTER TABLE `deal` ADD `start_date` DATE NOT NULL
GO
ALTER TABLE `deal` ADD `end_date` DATE NULL
