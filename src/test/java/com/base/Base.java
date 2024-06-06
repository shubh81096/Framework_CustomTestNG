package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.utility.Utility;

public class Base {

    public static WebDriver driver;

    public String browser = Utility.getPropertyDirectly("browser");
    public String url = Utility.getPropertyDirectly("url");
    public boolean headless = Boolean.parseBoolean(Utility.getPropertyDirectly("headless"));

    @BeforeClass
    public void browserSetup() throws Exception {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (headless) {
                options.addArguments("--headless");
            }
            driver = new FirefoxDriver(options);

        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            if (headless) {
                options.addArguments("headless");
                options.addArguments("disable-gpu");
            }
            driver = new EdgeDriver(options);

        } else {
            throw new Exception("Invalid Browser Value");
        }

        driver.get(url);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        if (driver != null) {
            driver.quit();
        }
    }
}
