package com.utility;

/** 
 * @Author: Shubham Shedge
 * @Version: 1.0
 * @Description: This class contains all Utility functions.
 */

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
		Properties properties = new Properties();
		String path = System.getProperty("user.dir") + "\\test_data\\properties\\sit_env.properties";
		try {
			FileInputStream file = new FileInputStream(path);
			properties.load(file);
			return properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null; // Handle the exception according to your needs
		}
	}

	public static String getLocator(String key) {
		Properties properties = new Properties();
		try (FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\locators\\locators.properties")) {
			properties.load(file);
			return properties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			return null; // Handle the exception according to your needs
		}
	}

	public static WebElement findElement(String locator, WebDriver driver) {
		String[] locatorInfo = locator.split("=", 2);
		String locatorType = locatorInfo[0];
		String locatorValue = locatorInfo[1];

		switch (locatorType.toLowerCase()) {
		case "id":
			return driver.findElement(By.id(locatorValue));
		case "xpath":
			return driver.findElement(By.xpath(locatorValue));
		case "css":
		case "cssselector":
			return driver.findElement(By.cssSelector(locatorValue));
		case "name":
			return driver.findElement(By.name(locatorValue));
		case "classname":
			return driver.findElement(By.className(locatorValue));
		case "link":
		case "linktext":
			return driver.findElement(By.linkText(locatorValue));
		case "partiallink":
		case "partiallinktext":
			return driver.findElement(By.partialLinkText(locatorValue));
		// Add more cases for other locator types if needed
		default:
			throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
		}
	}

	public static WebElement getElement(String key, WebDriver driver) {
		String locator = getLocator(key);
		if (locator != null) {
			return findElement(locator, driver);
		} else {
			throw new RuntimeException("Locator not found for key: " + key);
		}
	}

	public static void setImplicitWait(WebDriver driver, int timeoutInSeconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutInSeconds));
	}

	public static void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public static void minimizeWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}

	public static void clickElement(WebDriver driver, By locator, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public static void clickElement(WebDriver driver, WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public static void clickElement(WebDriver driver, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public static void sendKeys(WebDriver driver, By locator, String text, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
		element.sendKeys(text);
	}

	public static void sendKeys(WebDriver driver, WebElement element, String text, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
		element.sendKeys(text);
	}

	public static void assertTextEqualsByElementText(WebDriver driver, WebElement element, String expectedText,
			int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));

		String actualText = element.getText();

		Assert.assertEquals(actualText, expectedText, "Text mismatch: ");
	}

	public static void assertTextEqualsByElementValue(WebDriver driver, WebElement element, String expectedText,
			int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, expectedText));

		String actualText = element.getAttribute("value");

		Assert.assertEquals(actualText, expectedText, "Text mismatch: ");
	}

	public static void assertElementPresent(WebDriver driver, By by, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			Assert.assertTrue(element.isDisplayed(), "Element is not displayed on the page.");
		} catch (org.openqa.selenium.TimeoutException e) {
			Assert.fail("Element is not present on the page within the specified timeout.");
		}
	}

	public static void assertElementPresent(WebDriver driver, WebElement element, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			wait.until(ExpectedConditions.visibilityOf(element));
			Assert.assertTrue(element.isDisplayed(), "Element is not displayed on the page.");
		} catch (org.openqa.selenium.TimeoutException e) {
			Assert.fail("Element is not present on the page within the specified timeout.");
		}
	}

	public static void assertPageTitleEquals(WebDriver driver, String expectedTitle, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.titleContains(expectedTitle));

		String actualTitle = driver.getTitle();

		Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch: ");
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

	public static String[] readRowWithFlagY(String filePath, String sheetName, String flagVal) {
		try (FileInputStream fis = new FileInputStream(new File(filePath)); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);

			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Row row = sheet.getRow(rowNum);
				if (row != null) {
					Cell flagCell = row.getCell(0); // Assuming the flag is in the first column (0-based index)

					if (flagCell != null && flagCell.getCellType() == CellType.STRING) {
						String flag = flagCell.getStringCellValue();

						if (flagVal.equalsIgnoreCase(flag)) {
							int lastColNum = row.getLastCellNum();
							String[] rowData = new String[lastColNum - 1]; // Excluding the flag column
							int rowDataIndex = 0;

							for (int colNum = 1; colNum < lastColNum; colNum++) {
								Cell dataCell = row.getCell(colNum);

								if (dataCell != null) {
									if (dataCell.getCellType() == CellType.STRING) {
										rowData[rowDataIndex] = dataCell.getStringCellValue();
									} else if (dataCell.getCellType() == CellType.NUMERIC) {
										rowData[rowDataIndex] = String.valueOf(dataCell.getNumericCellValue());
									}
									rowDataIndex++;
								}
							}

							return rowData;
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null; // Return null if no row with the flag "Y" is found
	}

	public static String getTestMethodDescription(Class<?> testClass, String methodName) {
		try {
			// Get the test method using reflection
			Method testMethod = testClass.getMethod(methodName);

			// Get the Test annotation
			Annotation annotation = testMethod.getAnnotation(Test.class);

			// Check if the Test annotation is present
			if (annotation instanceof Test) {
				// Cast the annotation to Test and get the description
				return ((Test) annotation).description();
			} else {
				return "Test annotation not found";
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return "Method not found: " + methodName;
		}
	}

	public static void sendKeysAction(WebDriver driver, Keys key) {
		Actions actions = new Actions(driver);
		actions.sendKeys(key).perform();
	}

}