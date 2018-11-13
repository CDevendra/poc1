CREATE DATABASE IF NOT EXISTS `dentaldb`;

USE `dentaldb`; 

DROP TABLE IF EXISTS 'cart';
CREATE TABLE `cart` (
  `cartId` int(11) NOT NULL AUTO_INCREMENT,
  `cartName` varchar(45) NOT NULL,
  `customerId` varchar(45) NOT NULL,
  `productIdList` varchar(45) NOT NULL,
  `totalProductQuantity` int(11) NOT NULL,
  `totalProductcost` int(11) NOT NULL,
  PRIMARY KEY (`cartId`)
);

#TRUNCATE TABLE RC_RULE_RESULTS;

LOAD DATA LOCAL INFILE '/user/hive/warehouse/poc1/data/cart.csv' IGNORE INTO TABLE cart FIELDS TERMINATED BY ',' ENCLOSED BY '"' ESCAPED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES;
