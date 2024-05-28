package com.test_scripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import com.base.Base;
import com.page_class.DashboardPage;
import com.page_class.LoginPage;
import com.page_class.MyInfoPage;
import com.page_class.SidebarPage;
import com.utility.Utility;

public class Test {
	LoginPage login;
	DashboardPage dashboard;
	SidebarPage sidebar;
	MyInfoPage myinfo;

	@org.testng.annotations.Test
	public void test1() throws IOException, InterruptedException {

//		login = new LoginPage(driver);
//		dashboard = new DashboardPage(driver);
//		sidebar = new SidebarPage(driver);
//		myinfo = new MyInfoPage(driver);
//
//		Utility.maximizeWindow(driver);
//		driver.get(url);
//		Utility.setImplicitWait(driver, 30);
//		login.enterCred("shubh810", "Shubham@123");
//		login.clickOnLoginBtn();
//
//		String expectedResult = "Dashboard";
//		String actualResult = dashboard.DashboardText.getText();
//
//		Assert.assertEquals(expectedResult, actualResult);
//
//		sidebar.Myinfo.click();
//		myinfo.changeName("SHUBHAM", "SHEDGE");
//		myinfo.changeNationality("india");
//		myinfo.changeMaritalStatus("Single");
//		myinfo.changeMaritalStatus("male");
//		myinfo.save1();
//
//		Thread.sleep(15000);

//		FileInputStream file = new FileInputStream(
//				"C:\\Users\\admin\\Desktop\\Framework_selenium_custom_TestNG\\Framework_CustomTestNG\\test_data\\excelsheet\\testdata.xlsx");
//
//		Workbook workbook = new XSSFWorkbook(file);
//
//		Sheet sheet = workbook.getSheet("Sheet1");
//
//		System.out.println(sheet.getRow(0).getCell(0));
//		System.out.println(sheet.getRow(0).getCell(1));
//		System.out.println(sheet.getRow(1).getCell(0));
//		System.out.println(sheet.getRow(1).getCell(1));
		
		String path="C:\\Users\\admin\\Desktop\\Framework_selenium_custom_TestNG\\Framework_CustomTestNG\\test_data\\excelsheet\\testdata.xlsx";
		
		String data=Utility.readExcelData(path, "addEmployeeData", 2, 1);
		System.out.println(data);

	}

}
