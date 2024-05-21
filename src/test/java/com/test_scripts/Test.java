package com.test_scripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.base.Base;
import com.utility.Utility;

public class Test extends Base {

	@org.testng.annotations.Test
	public void test1() throws IOException {
		
		Utility.maximizeWindow(driver);
		driver.get(url);
		Utility.setImplicitWait(driver, 30);
		
		

	}

}
