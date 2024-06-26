package com.page_class;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	@FindBy(name = "username")
	public WebElement username;

	@FindBy(name = "password")
	public WebElement password;

	@FindBy(xpath = "//button[text()=' Login ']")
	public WebElement loginBtn;

	@FindBy(xpath = "//p[text()='Invalid credentials']")
	public WebElement invalidCred;

	public LoginPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

	public void enterCred(String user, String pass) {

		username.sendKeys(user);
		password.sendKeys(pass);

	}

	public void clickOnLoginBtn() {

		loginBtn.click();

	}

}
