package com.utility;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class Utility {

	public static String getPropertyDirectly(String key) {

		String path = "C:\\Users\\admin\\Desktop\\Framework_selenium_custom_TestNG\\Framework_CustomTestNG\\test_data\\properties\\sit_env.properties";

		try {
			Properties prop = new Properties();
			FileInputStream file = new FileInputStream(path);
			prop.load(file);

			return prop.getProperty(key);
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	public static void setImplicitWait(WebDriver driver, int time) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));

	}

	public static void maximizeWindow(WebDriver driver) {

		driver.manage().window().maximize();

	}

	public static void minimizeWindow(WebDriver driver) {

		driver.manage().window().minimize();

	}
	
	
	
	
	

}
