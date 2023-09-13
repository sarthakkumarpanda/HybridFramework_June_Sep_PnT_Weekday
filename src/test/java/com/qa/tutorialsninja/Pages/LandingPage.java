package com.qa.tutorialsninja.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	public WebDriver driver;
	
	//I will define all my Objects here
	@FindBy(linkText = "My Account")
	private WebElement MyAccountDropDown;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	
	//I will initialize the Objects
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		//PageFactory.initElements(driver, LandingPage.class);
		PageFactory.initElements(driver, this);
	}
	
	
	//Action on those Objects
	public void clickOnMyAccountDropdown() {
		MyAccountDropDown.click();
	}
	
	public void selectLoginOption() {
		loginOption.click();	
	}

}
