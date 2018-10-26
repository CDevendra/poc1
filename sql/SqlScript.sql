CREATE DATABASE IF NOT EXISTS fileDb;

USE fileDb;
 
CREATE TABLE IF NOT EXISTS files_upload (
  file_id INT(100) NOT NULL AUTO_INCREMENT,
  file_name VARCHAR(200) DEFAULT NULL,
  file_description VARCHAR(300) DEFAULT NULL,
  file_data longblob,
  PRIMARY KEY (file_id)
);
