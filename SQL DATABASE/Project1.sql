drop schema IF EXISTS ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims`;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`products`(
`id` int NOT NULL AUTO_INCREMENT,
`product_name` varchar(100) NOT NULL,
`price` double NOT NULL,
PRIMARY KEY(`id`));


CREATE TABLE IF NOT EXISTS `ims`.`orders`(
`id` int NOT NULL AUTO_INCREMENT,
`customer_id` integer NOT NULL,

PRIMARY KEY(`id`),
FOREIGN KEY (`customer_id`)
	REFERENCES `customers`(`id`));
    
CREATE TABLE IF NOT EXISTS `ims`.`order_items`(
`id` int NOT NULL AUTO_INCREMENT,
`customer_ID` integer NOT NULL,
`orders_id` integer NOT NULL,
`products_id` integer NOT NULL,
`amount` integer NOT NULL,

PRIMARY KEY (`id`),

FOREIGN KEY (`customer_id`)
	REFERENCES `customers`(`id`),

FOREIGN KEY (`orders_id`)
	REFERENCES `orders`(`id`),

FOREIGN KEY (`products_id`)
	REFERENCES `products`(`id`));
    
