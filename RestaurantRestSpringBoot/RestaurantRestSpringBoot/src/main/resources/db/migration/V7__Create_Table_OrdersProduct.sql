CREATE TABLE `order_products` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `order_id` INT(10) NOT NULL,
  `product_id` INT(10) NOT NULL,
  `quantity` INT(10) NOT NULL,
  KEY `fk_order_products_order` (`id`),
  CONSTRAINT `fk_order_products` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `fk_order_products_products` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)    
) ENGINE=InnoDB DEFAULT CHARSET=latin1;