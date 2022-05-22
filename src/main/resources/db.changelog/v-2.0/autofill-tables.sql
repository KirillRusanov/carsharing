INSERT INTO `specialist` (id, email, name, phone_number) values (1, "tester@gmail.com", "Petya", "+37529231222");
GO
INSERT INTO `specialist` (id, email, name, phone_number) values (2, "marina@gmail.com", "Marina", "+3752967722");
GO
INSERT INTO `specialist` (id, email, name, phone_number) values (3, "pavel@gmail.com", "Pavel", "+37529241232");
GO


INSERT INTO `customer` (`id`, `name`, `surname`, `phone_number`,
`email`, `password`, `passport_number`,
 `license_number`, `is_verified`, `specialist_id`, `balance`)
values (1, "Max", "Pupkin", "+375295401522",
 "pup@mail.ru", "$2a$10$9/qrbYNjt/YZ7h.KVCoZMuX6ikbTEXF0SXZptbfaCs5MtBVyCvHlG", "E1ARRRRRBB",
 "DDD222", 1, 1, 30.2);
GO
INSERT INTO `customer` (`id`, `name`, `surname`, `phone_number`,
`email`, `password`, `passport_number`,
 `license_number`, `is_verified`, `specialist_id`, `balance`)
values (2, "Kirill", "Rusanov", "+375295841020",
 "kr.rusanov@mail.ru", "$2a$12$QEKL7iq06.yEr4YuA9mBLeovs2zwNloc8WzwMchA9LLlZNwXTwjbu", "EBB32122",
 "DDR2112", 1, 3, 1003.2);
GO
INSERT INTO `customer` (`id`, `name`, `surname`, `phone_number`,
`email`, `password`, `passport_number`,
 `license_number`, `is_verified`, `specialist_id`, `balance`)
values (3, "Nikita", "Vaskin", "+3752912322219",
 "vaskin@mail.ru", "$2a$10$9/qrbYNjt/YZ7h.KVCoZMuX6ikbTEXF0SXZptbfaCs5MtBVyCvHlG", "E1PBBF123",
 "RBB322", 1, 1, 1321.20);
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

INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (1, "SPORT", "BUSY", "Hyundai Tucson", 120, 2, 2, 53.65064900, 23.80531400);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (2, "BASIC", "AVAILABLE", "Volkswagen Lavida", 125, 2, 2, 53.67532200, 23.86127600);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (3, "BASIC", "AVAILABLE", "Nissan Sylphy", 39, 3, 2, 53.69937000, 23.81836000);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (4, "BASIC", "BUSY", "Toyota Camry", 176, 1, 2, 53.67634200, 23.80428400);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (5, "BASIC", "AVAILABLE", "Toyota Camry", 10, 1, 2, 53.69056800, 23.83346600);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (6, "BASIC", "AVAILABLE", "Toyota Camry", 32, 1, 2, 53.65020000, 23.81424000);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (7, "BASIC", "AVAILABLE", "Volkswagen Tiguan", 11, 1, 2, 53.67528200, 23.85337900);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (8, "BASIC", "AVAILABLE", "Honda Civic", 62, 1, 2, 53.66610700, 23.78402800);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (9, "BASIC", "AVAILABLE", "Toyota RAV4", 67, 1, 2, 53.66508800, 23.88668100);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (10, "BASIC", "AVAILABLE", "Volkswagen Polo", 24, 1, 2, 53.69077100, 23.81458400);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (11, "SPORT", "AVAILABLE", "Audi R8", 24, 1, 2, 53.673175, 23.826115);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (12, "SPORT", "AVAILABLE", "Audi R8", 24, 1, 2, 53.613175, 23.886115);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (13, "SPORT", "AVAILABLE", "Bugatti Veyron", 24, 1, 2, 53.66970700, 23.82058400);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (14, "SPORT", "AVAILABLE", "Bugatti Veyron", 24, 1, 2, 53.66890700, 23.81658400);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (15, "SPORT", "AVAILABLE", "Bugatti Veyron", 24, 1, 2, 53.77610700, 23.81458400);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (16, "SPORT", "AVAILABLE", "Dodge Viper", 24, 1, 2, 53.670175, 23.829215);
GO
INSERT INTO `car` (`id`, `car_type`, `car_status`, `brand`, `fuel`, `rate_id`, `customer_id`, `posX`, `posY`)
values (17, "SPORT", "AVAILABLE", "Dodge Viper", 24, 1, 2, 53.678975, 23.827515);
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

INSERT INTO `deal` (`id`, `status`, `start_date`, `description`, `customer_id`, `car_id`)
values (1, "ACTIVE", CURRENT_DATE(), "gonna to rent 2 days", 1, 1);
GO
INSERT INTO `deal` (`id`, `status`, `start_date`, `description`, `customer_id`, `car_id`)
values (2, "ACTIVE", CURRENT_DATE(), "gonna to rent 1 month", 3, 4);
GO
INSERT INTO `deal` (`id`, `status`, `start_date`, `description`, `customer_id`, `car_id`)
values (3, "FINISHED", CURRENT_DATE(), "gonna to rent 1day", 3, 3);
GO
INSERT INTO `deal` (`id`, `status`, `start_date`, `description`, `customer_id`, `car_id`)
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