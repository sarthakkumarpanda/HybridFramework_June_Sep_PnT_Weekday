package com.qa.tutorialsninja.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.tutorialsninja.TestBase.TestBase;
import com.qa.tutorialsninja.Utilities.Utilities;

public class RegisterTest extends TestBase{
	
	public RegisterTest() throws Exception {
		super();	
	}

	public WebDriver driver;
	
	@BeforeMethod
	public void registerSetup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
	@Test(priority=1)
	public void verifyRegisterAccountWithMandatoryDetails() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstname"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastname"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithDateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name = 'agree']")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text() = 'Congratulations! Your new account has been successfully created!']")).isDisplayed());
			
	}
	
	@Test(priority=2)
	public void verifyRegisterAccountWithAllDetails() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstname"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastname"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithDateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name = 'newsletter' and @value = '1']")).click();
		driver.findElement(By.xpath("//input[@name = 'agree']")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text() = 'Congratulations! Your new account has been successfully created!']")).isDisplayed());	
	}
	
	@Test(priority=3)
	public void verifyRegisterAccountWithNoDetails() {
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String acutalPrivacyPolicyWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedPrivacyPolicyWarningMessage = dataprop.getProperty("policyWarningMessage");
		Assert.assertTrue(acutalPrivacyPolicyWarningMessage.contains(expectedPrivacyPolicyWarningMessage));
		
		String actualFirstNameWarningMessage = driver.findElement(By.xpath("//div[text() = 'First Name must be between 1 and 32 characters!']")).getText();
		String expectedFirstNameWarningMessage = dataprop.getProperty("firstNameWarningMessage");
		Assert.assertTrue(actualFirstNameWarningMessage.contains(expectedFirstNameWarningMessage));
		
		String actualLastNameWarningMessage = driver.findElement(By.xpath("//div[text() = 'Last Name must be between 1 and 32 characters!']")).getText();
		String expectedLastNameWarningMessage = dataprop.getProperty("lastNameWarningMessage");
		Assert.assertTrue(actualLastNameWarningMessage.contains(expectedLastNameWarningMessage));
		
		String actualEmailWarningMessage = driver.findElement(By.xpath("//div[text() = 'E-Mail Address does not appear to be valid!']")).getText();
		String expectedEmailWarningMessage = dataprop.getProperty("emailWarningMessage");
		Assert.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));
		
		String actualTelephoneWarningMessage = driver.findElement(By.xpath("//div[text() = 'Telephone must be between 3 and 32 characters!']")).getText();
		String expectedTelephoneWarningMessage = dataprop.getProperty("telephoneWarningMessage");
		Assert.assertTrue(actualTelephoneWarningMessage.contains(expectedTelephoneWarningMessage));
		
		String actualPasswordWarningMessage = driver.findElement(By.xpath("//div[text() = 'Password must be between 4 and 20 characters!']")).getText();
		String expectedPasswordWarningMessage = dataprop.getProperty("passwordWarningMessage");
		Assert.assertTrue(actualPasswordWarningMessage.contains(expectedPasswordWarningMessage));
			
	}
	
	@Test(priority=4)
	
	public void verifyRegisterAccountWithDuplicateEmail() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstname"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastname"));
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name = 'newsletter' and @value = '1']")).click();
		driver.findElement(By.xpath("//input[@name = 'agree']")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualDuplicateEmailWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedDuplicateEmailWarningMessage = dataprop.getProperty("emailExistWarningMessage");
		Assert.assertTrue(actualDuplicateEmailWarningMessage.contains(expectedDuplicateEmailWarningMessage));
		
	}
	
	
	
@AfterMethod
public void tearDown() {
	driver.quit();
}
	
	
	
	
	
	
	

}
