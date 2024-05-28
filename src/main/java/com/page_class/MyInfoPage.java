package com.page_class;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyInfoPage {

	WebDriver driver;

	@FindBy(name = "firstName")
	WebElement firstName;

	@FindBy(name = "middleName")
	WebElement middleName;

	@FindBy(name = "lastName")
	WebElement lastName;

	@FindBy(xpath = "//label[text()='Nickname']//parent::div//following-sibling::div//input")
	WebElement nickName;

	@FindBy(xpath = "/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[2]/div[2]/div/div[2]/div/div/input")
	WebElement LicenseExpiryDate;

	@FindBy(xpath = "//*[text()='Nationality']//parent::div//following-sibling::div//div[@class='oxd-select-text-input']")
	WebElement Nationality;

	@FindBy(xpath = "//*[text()='Marital Status']//parent::div//following-sibling::div//div[@class='oxd-select-text-input']")
	WebElement MaritalStatus;

	@FindBy(xpath = "//input[@type='radio' and @value='1']//following-sibling::span")
	WebElement male;

	@FindBy(xpath = "//input[@type='radio' and @value='2']//following-sibling::span")
	WebElement female;

	@FindBy(xpath = "(//div[@class='oxd-form-actions'])[1]")
	WebElement saveBtn1;

	public MyInfoPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void changeNationality(String text) {

		((JavascriptExecutor) driver).executeScript("arguments[0].innerText = arguments[1];", Nationality, text);

	}

	public void changeMaritalStatus(String text) {

		((JavascriptExecutor) driver).executeScript("arguments[0].innerText = arguments[1];", MaritalStatus, text);

	}

	public void changeGender(String input) {

		if (input.equalsIgnoreCase("male")) {

			male.click();

		} else if (input.equalsIgnoreCase("female")) {
			female.click();

		} else {
			throw new RuntimeException("invalid input");
		}

	}

	public void changeName(String first, String last) {

		firstName.clear();
		firstName.sendKeys(first);

		lastName.clear();
		lastName.sendKeys(last);

	}

	public void save1() {
		saveBtn1.click();
	}

}
