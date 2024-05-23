package com.page_class;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

	@FindBy(xpath = "//h6[text()='Dashboard']")
	public WebElement DashboardText;
	
	

	public DashboardPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

}
