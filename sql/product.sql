CREATE DATABASE IF NOT EXISTS `dentaldb`;

USE `dentaldb`; 

CREATE TABLE IF NOT EXISTS `product` (
  `productId` int(11) NOT NULL AUTO_INCREMENT,
  `productName` varchar(45) NOT NULL,
  `productPrice` varchar(45) NOT NULL,
  `productDesc` varchar(45) NOT NULL,
  `productImagePath` varchar(100) NOT NULL,
  PRIMARY KEY (`productId`)
);
