package com.ymhase.seleniumdemo;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

	private static PropertiesManager sharedManager = null;

	private String propertiesFile = "project.properties";

	private Properties props = null;

	private PropertiesManager() {

		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFile);

			if (inputStream != null) {
				props = new Properties();
				props.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propertiesFile + "' not found in the classpath");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PropertiesManager sharedManager() {

		if (sharedManager == null) {
			sharedManager = new PropertiesManager();
		}

		return sharedManager;
	}
	
	public String getProperty(String key) {
		
		if(props == null) {
			throw new RuntimeException(key);
		}
		
		return props.getProperty(key);
	}

}
