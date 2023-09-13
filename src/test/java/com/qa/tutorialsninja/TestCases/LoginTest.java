package com.qa.tutorialsninja.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.tutorialsninja.Pages.AccountPage;
import com.qa.tutorialsninja.Pages.LandingPage;
import com.qa.tutorialsninja.Pages.LoginPage;
import com.qa.tutorialsninja.TestBase.TestBase;
import com.qa.tutorialsninja.Utilities.Utilities;

public class LoginTest extends TestBase{
	
	public LoginTest() throws Exception {
		super();	
	}

	public WebDriver driver;
	
	@BeforeMethod
	public void loginSetup() {
		
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		LandingPage landingpage = new LandingPage(driver);
		landingpage.clickOnMyAccountDropdown();
		//driver.findElement(By.linkText("My Account")).click();
		landingpage.selectLoginOption();
		//driver.findElement(By.linkText("Login")).click();	
	}
	
	
	@Test(priority=1)
	public void verifyLoginWithValidCredentials() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(prop.getProperty("validEmail"));
		//driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		loginpage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		AccountPage accountpage = new AccountPage(driver);
		Assert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInfoLink());
		
	}
	
	@Test(priority=2)
	public void verifyLoginWithValidEmailInvalidPassword() {
		    LoginPage loginpage = new LoginPage(driver);
		    loginpage.enterEmailAddress(prop.getProperty("validEmail"));
		    loginpage.enterPassword(dataprop.getProperty("invalidPassword"));
		    loginpage.clickOnLoginButton();
			String actualWarningMessage = loginpage.retrieveEmailPasswordNotMatchingWarningMessageText();
			String expectedWarningMessage = dataprop.getProperty("loginWarningMessage");
			Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailValidPassword() {
		LoginPage loginpage = new LoginPage(driver);
	    loginpage.enterEmailAddress(Utilities.generateEmailWithDateTimeStamp());
	    loginpage.enterPassword(prop.getProperty("validPassword"));
	    loginpage.clickOnLoginButton();
	    String actualWarningMessage = loginpage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = dataprop.getProperty("loginWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	
	}
	
	@Test(priority=4)
	public void verifyLoginWithInvalidCredentials() {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithDateTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = dataprop.getProperty("loginWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));	
	}
	
	@Test(priority=5)
	public void verifyLoginWithNoCredentials() {
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = dataprop.getProperty("loginWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
