DROP TABLE IF EXISTS `order_items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `products`(
`id` INT NOT NULL AUTO_INCREMENT,
`product_name` VARCHAR(100) NOT NULL,
`price` double NOT NULL,
PRIMARY KEY(`id`));


CREATE TABLE IF NOT EXISTS `orders`(
`id` INT NOT NULL AUTO_INCREMENT,
`customer_id` integer NOT NULL,

PRIMARY KEY(`id`),
FOREIGN KEY (`customer_id`)
	REFERENCES `customers`(`id`));
    
CREATE TABLE IF NOT EXISTS `order_items`(
	`id` INT NOT NULL AUTO_INCREMENT,
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
        