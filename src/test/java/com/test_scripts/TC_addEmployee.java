package com.test_scripts;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.framework.qual.DefaultQualifier.List;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import com.base.Base;
import com.page_class.DashboardPage;
import com.page_class.LoginPage;
import com.page_class.PimPage;
import com.page_class.SidebarPage;
import com.reports.ExtentReportListener;
import com.utility.Utility;

@Listeners(ExtentReportListener.class)
public class TC_addEmployee extends Base {

	LoginPage login;
	DashboardPage dashboard;
	PimPage pim;
	SidebarPage side;

	@org.testng.annotations.Test
	public void TC_addEmployee() throws IOException, InterruptedException {

		String path = "C:\\Users\\admin\\Desktop\\Framework_selenium_custom_TestNG\\Framework_CustomTestNG\\test_data\\excelsheet\\testdata.xlsx";

		login = new LoginPage(driver);
		dashboard = new DashboardPage(driver);
		side = new SidebarPage(driver);
		pim = new PimPage(driver);

		Utility.maximizeWindow(driver);
		driver.get(url);
		Utility.setImplicitWait(driver, 30);
		login.enterCred("Admin", "admin1234");
//		Utility.captureScreenshotOnDesktop(driver, "TC_addEmployee");
		login.clickOnLoginBtn();
		String expectedResult = "Dashboard";
		String actualResult = dashboard.DashboardText.getText();
		Assert.assertEquals(expectedResult, actualResult);
//		Utility.captureScreenshotOnDesktop(driver, "TC_addEmployee");
		side.pim.click();
		pim.addEmployee.click();
		pim.employeeFullName(Utility.readExcelData(path, "addEmployeeData", 2, 1),
				Utility.readExcelData(path, "addEmployeeData", 2, 2),
				Utility.readExcelData(path, "addEmployeeData", 2, 3));
		pim.clickOnCreateLoginDetails();
//		Utility.captureScreenshotOnDesktop(driver, "TC_addEmployee");
		Thread.sleep(4000);
		pim.employeeCred(Utility.readExcelData(path, "addEmployeeData", 2, 4),
				Utility.readExcelData(path, "addEmployeeData", 2, 5),
				Utility.readExcelData(path, "addEmployeeData", 2, 6));
		pim.clickOnSaveBtn();
//		Utility.captureScreenshotOnDesktop(driver, "TC_addEmployee");

	}

}
