package com.fb.testcases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.FBase.com.testBase;
import com.facebook.pages.LoginPage;
import com.fb.util.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

import atu.testrecorder.exceptions.ATUTestRecorderException;


public class LoginPageTest extends testBase {

	public LoginPageTest() throws IOException {
		super();
		
	}
	LoginPage loginPage;
	
	 //@Parameters({"browser"})   //{} means defined
	     @BeforeTest
	    public void initializeBroswer() throws IOException
	    
	     {
		    initialization("chrome");
		    loginPage=new LoginPage();// after you initialize your driver
	     }
	    
		@BeforeMethod
		public void setup(Method method) throws IOException , ATUTestRecorderException                            //Method method,String browser

		{
			TestUtils.TakeVideo(method.getName());
			TestUtils.Recorder.start();
			loginPage.DeleteAllCookies();
			loginPage.getFbLoginPage();
			
			logger=extent.startTest(method.getName());
		}

		@AfterMethod
		public void tearDown(Method method,ITestResult result) throws IOException, ATUTestRecorderException //ITestResult is TestNG listener to log test status[pass|fail|skipped]

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
			TestUtils.Recorder.stop();
				
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
			
			//driver.quit();
			//driver.close();
			
		}
		@AfterTest
		public void endbrowser()
		{
			driver.quit();
		}
		
		@Test(priority=1)
		public void checkURLTest(Method method) throws IOException
		{
			String AR=loginPage.getURL();
			String ER="https://www.facebook.com/";
			Assert.assertEquals(AR,ER,"Fail//Facebook URL Not Correct");

		}
		@Test(priority=2)
		public void checkFBImgTest(Method method) throws IOException
		{
			
			boolean AR=loginPage.imgIsDisplayed();
			Assert.assertEquals(AR,true,"Fail//Facebook Img not found");
			
		}
		@Test(priority=3)
		public void checkFbintroMsgTest(Method method) throws IOException
		{
			String AR=loginPage.getIntroHeaderText();
			Assert.assertEquals(AR,"Facebook helps you connect and share with the people in your life.","Fail//Facebook introHeader not Correct");
			
		}
		@Test(priority=4)
		public void ForgotPasswordLinkTest(Method method) throws IOException
		{
			boolean AR=loginPage.ForgotPasswordIsClickable();	
			Assert.assertEquals(AR,true,"Fail//Forgotten password?Link not Working");
			

		}
		@Test(priority=5)
		public void CreatePageLinkTest(Method method) throws IOException
		{
			SoftAssert softAssert=new SoftAssert();
			boolean AR1,AR2;
			boolean []actualResults = loginPage.createPageLinkIsClickable();
			 AR1=actualResults[0];
			 AR2=actualResults[1];
			softAssert.assertEquals(AR1, true);
			System.out.println("FrogotPasswordLink is clickable and 'Business or brand' Exists");
			softAssert.assertEquals(AR2, true);
			System.out.println("Community or public figure Exists");
			softAssert.assertEquals(AR1, AR2);// try false to fail
			System.out.println("AR1=AR2");
		 	softAssert.assertAll("Tc Fails due to AR1!=AR2");//we have to add it to show if Test case Pass Or Fail , if we not add [assertAll], TestNg will not report if TestCase Fail , it will report all pass Despite there is a Tc fails..
			

		}
		
		
		@Test(priority=6)
		public void signUpLinkTest(Method method) throws IOException
		{
			boolean AR=loginPage.signUpIsClickble();
			Assert.assertTrue(AR,"SignUp Link is not clickable");
			System.out.println("SignUp Link is clickable");
		}

		//Login TCs
		
		@Test(priority=7,dataProvider="testLoginData")//,dataProvider="testLoginData"
		public void loginTest(String user,String pass) throws IOException//String fname,String lname
		{
		    boolean ActualResult = loginPage.testperformLogin(user,pass);
			Assert.assertEquals(ActualResult, true,"Login Fail");
			
		}
		@DataProvider
		public Object[][] testLoginData() throws IOException
		{
			// if you ** change the file name **[don't forget to change the File Path ]in the getDataFromExcel Method 
			//AND [pass the correct sheet name to getDataFrom Excel Method]].
			String ExcelSheetname="Sheet1";//"FbLoginData";//"RegisterDataSheet";
			Object[][]data=TestUtils.getDataFromExcel(ExcelSheetname);
			return data;
		}

		

}
