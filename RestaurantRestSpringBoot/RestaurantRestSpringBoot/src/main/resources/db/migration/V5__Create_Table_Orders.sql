CREATE TABLE `orders` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `client_id` INT(10) NOT NULL,
  `order_date` datetime(6) NOT NULL,
  `progress` bit(1) DEFAULT NULL,
  `observation` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;