package com.qa.tutorialsninja.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public WebDriver driver;
	
	@FindBy(id = "input-email")
	private WebElement emailAddressField;
	
	
	@FindBy(id = "input-password")
	private WebElement passwordAddressField;
	
	@FindBy(css = "input.btn.btn-primary")
	private WebElement loginButton;
	
	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarningMessage;
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterPassword(String passwordText) {
		passwordAddressField.sendKeys(passwordText);
	}
	
	public void clickOnLoginButton() {
		loginButton.click();
	}
	
	public String retrieveEmailPasswordNotMatchingWarningMessageText() {
		String warningText = emailPasswordNotMatchingWarningMessage.getText();
		return warningText;
	}

}
