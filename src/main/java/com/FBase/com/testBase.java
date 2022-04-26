package com.FBase.com;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.fb.util.WebListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class testBase {
	public static WebDriver driver; //Glopal driver
	public static JavascriptExecutor js;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebListener weblistener;
	public static ExtentReports extent;
	public static ExtentTest logger;// to log each test in the Report
	public testBase() throws IOException
	{
		prop=new Properties();
		FileInputStream fis= new FileInputStream("/Users/islamabdelhakim/eclipse-workspace/FacebookApplication/src/com/facebook/config/config.properties");
		prop.load(fis);
		
	}
	
	public void initialization(String browser)
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", "/Volumes/IslamHakim/AlgorizaTestAutomationInternship/AutomationTesting/SelenuimWebDrivers/chromedriver");
	    driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "/Volumes/IslamHakim/AlgorizaTestAutomationInternship/AutomationTesting/SelenuimWebDrivers/geckodriver");
		    driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("safari"))
		{
			/*Configure safari driver Manually first in your MacbookAir : 
			(Open terminal :
			[run 'cd usr/bin , safaridriver --enble',
			enable remote automation on safari-Deceloper ,
			close any safari browser before start testing as safari not permit to instantiate more than safari browser in the same time]
			 )
			*/
			 driver=new SafariDriver();
		}
	    //For web Driver listener
	    e_driver=new EventFiringWebDriver(driver);
	    weblistener=new WebListener();
	    e_driver.register(weblistener);
	    driver=e_driver;
	    
		//driver.get(prop.getProperty("URL")) ;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		js = ((JavascriptExecutor) driver);
	}
	
    
}

