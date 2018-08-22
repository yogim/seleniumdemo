package com.ymhase.seleniumdemo;

import org.openqa.selenium.WebDriver;

public class ProjectManager {

	private static ProjectManager sharedManager = null;

	private WebDriver webDriver;

	private String basePath;

	private ProjectManager() {
		initWebDriver();
	}

	private void initWebDriver() {
		try {
			PropertiesManager properties = PropertiesManager.sharedManager();

			String driver = properties.getProperty("driver");

			String driverClass = "org.openqa.selenium.firefox.FirefoxDriver";

			if (driver.equals("Chrome")) {
				driverClass = "org.openqa.selenium.chrome.ChromeDriver";
			}

			if (driverClass.equals("org.openqa.selenium.chrome.ChromeDriver")) {
				System.setProperty("webdriver.chrome.driver", "drivers/chrome/chromedriver");
			}

			Class<?> c = Class.forName(driverClass);
			webDriver = (WebDriver) c.newInstance();

			basePath = properties.getProperty("basePath");
			webDriver.get(basePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ProjectManager sharedManager() {
		if (sharedManager == null) {
			sharedManager = new ProjectManager();
		}

		return sharedManager;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

}