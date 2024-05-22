package com.test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.Base;
import com.page_class.DashboardPage;
import com.page_class.LoginPage;
import com.utility.Utility;

public class TC_002 extends Base {

	LoginPage login;
	DashboardPage dashboard;

	@Test(description = "Verify login with valid ESS username and in-valid password")
	public void tc_002() {

		login = new LoginPage(driver);
		dashboard = new DashboardPage(driver);

		Utility.maximizeWindow(driver);
		driver.get(url);
		Utility.setImplicitWait(driver, 30);
		login.enterCred("shubh810", "123456");
		login.clickOnLoginBtn();

		String expectedResult = "Invalid credentials";
		String actualResult = login.invalidCred.getText();

		Assert.assertEquals(expectedResult, actualResult);

	}

}
