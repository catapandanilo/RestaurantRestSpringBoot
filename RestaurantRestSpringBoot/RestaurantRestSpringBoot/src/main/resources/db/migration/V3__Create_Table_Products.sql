CREATE TABLE `products` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `description` varchar(180) NOT NULL,
  `price` decimal(65,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;