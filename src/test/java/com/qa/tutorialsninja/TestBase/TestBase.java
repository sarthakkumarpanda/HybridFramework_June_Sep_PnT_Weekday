package com.qa.tutorialsninja.TestBase;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.tutorialsninja.Utilities.Utilities;

public class TestBase {
	
	public WebDriver driver;
	public ChromeOptions options;
	public Properties prop;
	public FileInputStream ip;
	public FileInputStream ip1;
	public Properties dataprop;
	
	public TestBase() throws Exception {
		prop = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\tutorialsninja\\config\\config.properties");
		prop.load(ip);
		
		dataprop = new Properties();
		ip1 = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\tutorialsninja\\TestData\\testData.properties");
	    dataprop.load(ip1);
	}
	
	
	public WebDriver initializeBrowserAndOpenApplication(String browserName) {
		
		if(browserName.equals("chrome")) {
			options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			options.addArguments("--start-maximized");
			options.addArguments("--incognito");
			options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation", "disable-infobars"));
			driver = new ChromeDriver(options);
		}else if(browserName.equals("firefox")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}else if(browserName.equals("edge")) {
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Utilities.SCRIPT_TIME));
		driver.get(prop.getProperty("url"));
		return driver;
	}

}
