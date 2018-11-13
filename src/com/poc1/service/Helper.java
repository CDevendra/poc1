package com.poc1.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Helper {
	public static String getPropertyValue(String key) throws FileNotFoundException, IOException {
		Properties property = new Properties();
		InputStream stream = Helper.class.getClassLoader().getResourceAsStream("poc1.properties");
		property.load(stream);
		//logger.info(property.getProperty(key));
		return property.getProperty(key);
	}
	
	public static String getFileDirectoryByFileType() throws FileNotFoundException, IOException {
		return getPropertyValue("poc1.file.upload.path");
	}
	
	public static String getDbProperties(String string) throws FileNotFoundException, IOException {
		//return getPropertyValue("poc1.mysql.driver");		
		if(string != null) {
			switch(string.toLowerCase()) {
			case "driver" : return getPropertyValue("poc1.mysql.driver");
			case "url" : return getPropertyValue("poc1.mysql.url");
			case "user" : return getPropertyValue("poc1.mysql.username");
			case "password" : return getPropertyValue("poc1.mysql.password");
			}
		}
		return null;
	}
}
