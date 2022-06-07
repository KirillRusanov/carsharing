INSERT INTO `specialist` (id, email, name, phone_number) values (1, "tester@gmail.com", "Petya", "+37529231222");
GO
INSERT INTO `specialist` (id, email, name, phone_number) values (2, "marina@gmail.com", "Marina", "+3752967722");
GO
INSERT INTO `specialist` (id, email, name, phone_number) values (3, "pavel@gmail.com", "Pavel", "+37529241232");
GO


INSERT INTO `customer` (`id`, `name`, `surname`, `phone_number`,
`email`, `password`, `passport_number`,
 `license_number`, `is_verified`, `specialist_id`)
values (1, "Max", "Pupkin", "+375295401522",
 "pup@mail.ru", "$2a$10$9/qrbYNjt/YZ7h.KVCoZMuX6ikbTEXF0SXZptbfaCs5MtBVyCvHlG", "E1ARRRRRBB",
 "DDD222", 1, 1);
GO
INSERT INTO `customer` (`id`, `name`, `surname`, `phone_number`,
`email`, `password`, `passport_number`,
 `license_number`, `is_verified`, `specialist_id`)
values (2, "Kirill", "Rusanov", "+375295841020",
 "kr.rusanov@mail.ru", "$2a$12$zmTgyL8wO8wDoC74sJG84uZzEE6tkd11mU/vSO8ErZ7OhAi6ihcWi", "EBB32122",
 "DDR2112", 1, 3);
GO
INSERT INTO `customer` (`id`, `name`, `surname`, `phone_number`,
`email`, `password`, `passport_number`,
 `license_number`, `is_verified`, `specialist_id`)
values (3, "Nikita", "Vaskin", "+3752912322219",
 "vaskin@mail.ru", "$2a$10$9/qrbYNjt/YZ7h.KVCoZMuX6ikbTEXF0SXZptbfaCs5MtBVyCvHlG", "E1PBBF123",
 "RBB322", 1, 1);
GO


INSERT INTO `rate` (`id`, `name`, `type`, `cost`) values (1, "BASIC", "DAILY", 10);
GO
INSERT INTO `rate` (`id`, `name`, `type`, `cost`) values (2, "VIP", "DAILY", 25);
GO
INSERT INTO `rate` (`id`, `name`, `type`, `cost`) values (3, "STANDART", "HOURLY", 2);
GO


INSERT INTO `role` (`id`, `name`) values (1, "ROLE_CUSTOMER");
GO
INSERT INTO `role` (`id`, `name`) values (2, "ROLE_SPECIALIST");
GO
INSERT INTO `role` (`id`, `name`) values (3, "ROLE_ADMIN");
GO

INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`)
values (1, "SPORT", "BUSY", "BMW i4", 120, 2, 2);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`)
values (2, "BASIC", "AVAILABLE", "BMW i1", 125, 2, 2);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`)
values (3, "BASIC", "AVAILABLE", "BMW i2", 70, 3, 2);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`)
values (4, "BASIC", "BUSY", "BMW i3", 90, 1, 2);
GO

INSERT INTO `card` (`id`, `number`, `code`, `term`, `customer_id`)
values (1, "3212-2412-4212-3122", "178", CURRENT_DATE(), 1);
GO
INSERT INTO `card` (`id`, `number`, `code`, `term`, `customer_id`)
values (2, "3212-5223-4212-3122", "128", CURRENT_DATE(), 2);
GO
INSERT INTO `card` (`id`, `number`, `code`, `term`, `customer_id`)
values (3, "3212-1112-4212-3122", "198", CURRENT_DATE(), 3);
GO
INSERT INTO `card` (`id`, `number`, `code`, `term`, `customer_id`)
values (4, "3212-2412-9763-3122", "102", CURRENT_DATE(), 1);
GO

INSERT INTO `deal` (`id`, `status`, `date`, `description`, `customer_id`, `car_id`)
values (1, "ACTIVE", CURRENT_DATE(), "gonna to rent 2 days", 1, 1);
GO
INSERT INTO `deal` (`id`, `status`, `date`, `description`, `customer_id`, `car_id`)
values (2, "ACTIVE", CURRENT_DATE(), "gonna to rent 1 month", 3, 4);
GO
INSERT INTO `deal` (`id`, `status`, `date`, `description`, `customer_id`, `car_id`)
values (3, "FINISHED", CURRENT_DATE(), "gonna to rent 1day", 3, 3);
GO
INSERT INTO `deal` (`id`, `status`, `date`, `description`, `customer_id`, `car_id`)
values (4, "FINISHED", CURRENT_DATE(), "gonna to rent 3 hour", 3, 3);
GO

INSERT INTO `customer_role` (`customer_id`, `role_id`)
values (2, 3);
GO
INSERT INTO `customer_role` (`customer_id`, `role_id`)
values (2, 1);
GO
INSERT INTO `customer_role` (`customer_id`, `role_id`)
values (1, 1);
GO
INSERT INTO `customer_role` (`customer_id`, `role_id`)
values (3, 1);