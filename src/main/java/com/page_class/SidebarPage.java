package com.page_class;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SidebarPage {

	@FindBy(xpath = "//*[text()='My Info']")
	public WebElement Myinfo;
	
	@FindBy(xpath = "//*[text()='PIM']")
	public WebElement pim;
	
	

	public SidebarPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

}
