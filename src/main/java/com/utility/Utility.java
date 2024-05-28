package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.file.Paths;

import org.testng.annotations.Test;

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
	
	 
	public static String readExcelData(String filePath, String sheetName, int rowNum, int colNum) {
		String cellValue = null;
		try {
			FileInputStream fis = new FileInputStream(new File(filePath));
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet(sheetName);
			Row row = sheet.getRow(rowNum - 1); // Excel rows are 0-based, so subtract 1
			Cell cell = row.getCell(colNum - 1); // Excel columns are 0-based, so subtract 1

			if (cell != null) {
				if (cell.getCellType() == CellType.STRING) {
					cellValue = cell.getStringCellValue();
				} else if (cell.getCellType() == CellType.NUMERIC) {
					cellValue = String.valueOf(cell.getNumericCellValue());
				}
			}

			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cellValue;
	}
	
	
	public static String captureScreenshotOnDesktop(WebDriver driver, String testName) {
		try {
			if (driver instanceof TakesScreenshot) {
				TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
				File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
				String timestamp = dateFormat.format(new Date());

				String screenshotDirectory = Paths.get(System.getProperty("user.home"), "Desktop", "screenshots")
						.toString() + "\\";
				File directory = new File(screenshotDirectory);
				if (!directory.exists()) {
					directory.mkdirs();
				}

				String screenshotFilePath = screenshotDirectory + testName + "_" + timestamp + ".png";
				File finalScreenshot = new File(screenshotFilePath);
				FileUtils.copyFile(screenshot, finalScreenshot);

				System.out.println("Screenshot saved as: " + screenshotFilePath);

				return screenshotFilePath;
			} else {
				System.out.println("Driver does not support taking screenshots.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null; // Return null if unable to capture screenshot
	}
	
	
	
	
	
	

}
