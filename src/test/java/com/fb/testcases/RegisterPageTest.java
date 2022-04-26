package com.fb.testcases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.FBase.com.testBase;
import com.facebook.pages.HomePage;
import com.facebook.pages.RegisterPage;
import com.fb.util.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class RegisterPageTest extends testBase {

	public RegisterPageTest() throws IOException {
		super();
	}
	
	RegisterPage registerPage;
	//@Parameters({"browser"})
	@BeforeMethod
	public void setUp(Method method) throws IOException, ATUTestRecorderException//String browser

	{
		TestUtils.TakeVideo(method.getName());
		TestUtils.Recorder.start();
		initialization("chrome");
		registerPage=new RegisterPage();
		logger=extent.startTest(method.getName());
	}
	@AfterMethod
	public void tearDown(Method method,ITestResult result) throws IOException, ATUTestRecorderException
	{
		TestUtils.TakePicture(method.getName());
		//For adding Test Status To the Extent Report
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.log(LogStatus.PASS, "Test Pass");
			//Add Snapshots to the Report in case of success
			logger.log(LogStatus.PASS, "<a href='"+result.getName()+".png"+"'><span class='label info'>Download Snapshot</span></a>");
			//Add Video to the Report
		  logger.log(LogStatus.PASS, "<a href='"+result.getName()+".mov"+"'><span class='label info'>Download Video</span></a>");
			
		}
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(LogStatus.FAIL, result.getThrowable());
			//Add Snapshots to the Report in case of failure
			logger.log(LogStatus.FAIL, "<a href='"+result.getName()+".png"+"'><span class='label info'>Download Snapshot</span></a>");
			//Add Video to the Report
			  logger.log(LogStatus.PASS, "<a href='"+result.getName()+".mov"+"'><span class='label info'>Download Video</span></a>");
				
		}
		else
		{
			logger.log(LogStatus.SKIP, "Test Skipped");
		}
		
		driver.quit();
		//driver.close();
		TestUtils.Recorder.stop();
	}
	@Test(priority=1)
	public void testRegister() throws IOException
	{
		HomePage homePage =registerPage.performRegister();
		boolean actualResult = homePage.profileImgisDisplayed();
		Assert.assertTrue(actualResult,"SignUp Fail,Unreal DateOfBirth");
		
	}

}
