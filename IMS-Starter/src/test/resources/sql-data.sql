        
INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `orders` (`customer_id`) VALUES (1);
INSERT INTO `products` (`product_name`, `price`) VALUES ('jordan', 23.32);
INSERT INTO `order_items` (`customer_id`, `orders_id`, `products_id`,`amount`) VALUES (1,1,1,123);