package com.facebook.pages;

import java.io.IOException;
import java.lang.reflect.Array;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.FBase.com.testBase;

public class LoginPage extends testBase {
	public LoginPage() throws IOException {
		super();
		//this means all elements [initElements(FbImg,IntroHeaderforgotPassLink, ... etc )] instaed of that we use [this] keyword.
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//img[@alt='Facebook']")
	WebElement FbImg;
	@FindBy(xpath="//h2[@class='_8eso']")
	WebElement IntroHeader;
	@FindBy(xpath="//a[text()='Forgotten password?']")
	WebElement forgotPassLink;
	@FindBy(xpath="//h2[text()='Find Your Account']")
	WebElement FindYourAccounForgotPassAR;
	@FindBy(xpath="//a[@class='_8esh']")
	WebElement CreatePage;
	@FindBy(xpath="//span[text()='Business or brand']")
	WebElement businessText;
	@FindBy(xpath="//span[text()='Community or public figure']")
	WebElement figureText;
	@FindBy(linkText="Create New Account")
	WebElement signUp;
	@FindBy(name="firstname")
	WebElement newAccountLinkfirstNameAR;
	@FindBy(name="email")
	WebElement usernameTextBox;
	@FindBy(name="pass")
	WebElement passwordTextBox;
	@FindBy(name="login")
	WebElement login;
	@FindBy(xpath="//*[text()='Welcome']")
	WebElement welcomeBtn;
	@FindBy(xpath="//div[@aria-label='Allow all cookies']")
	WebElement AllowCookies;//Once Only one Time :AllowC ookies.click(); -- For the first time only.
	
	public String getURL()
	{
		String URL=driver.getCurrentUrl();
		return URL;
	}
	public boolean imgIsDisplayed()
	{
		js.executeScript("arguments[0].style.border='3px solid blue'", FbImg);
		boolean AR= FbImg.isDisplayed();
		return AR;
	}
	public String getIntroHeaderText()
	{
		js.executeScript("arguments[0].style.border='3px solid red'", IntroHeader);
		String IntroText=IntroHeader.getText();
		return IntroText;
	}
	public boolean ForgotPasswordIsClickable()
	{
		js.executeScript("arguments[0].style.border='3px solid blue'", forgotPassLink);
		forgotPassLink.click();
		boolean AR=FindYourAccounForgotPassAR.isDisplayed();
		return AR;
	}
	public boolean[] createPageLinkIsClickable()
	{
		js.executeScript("arguments[0].style.border='3px solid blue'", CreatePage);
		CreatePage.click();
		js.executeScript("arguments[0].style.border='3px solid orange'",businessText);
		boolean AR1=businessText.isDisplayed();
		js.executeScript("arguments[0].style.border='3px solid orange'",figureText);
		boolean AR2=figureText.isDisplayed();
		boolean[] AResults= {AR1,AR2};
		
		return AResults;
	}
	public boolean signUpIsClickble()
	{
		js.executeScript("arguments[0].style.border='3px solid blue'", signUp);
		signUp.click();
		boolean AR=newAccountLinkfirstNameAR.isDisplayed();
		return AR;
	}
	public boolean testperformLogin(String user,String pass)
	{
		js.executeScript("arguments[0].style.border='3px solid purple'", usernameTextBox);
		usernameTextBox.sendKeys(user);//prop.getProperty("username")
		js.executeScript("arguments[0].style.border='3px solid purple'", passwordTextBox);
		passwordTextBox.sendKeys(pass);//prop.getProperty("password")
		login.click();
//AllowCookies.click(); -- For the first time only.
		js.executeScript("arguments[0].style.border='3px solid gold'", welcomeBtn);
		boolean AR=welcomeBtn.isDisplayed();
		return AR;
	}
	//investigate login method that redirect you to another page
	public HomePage performLogin() throws IOException
	{
		js.executeScript("arguments[0].style.border='3px solid purple'", usernameTextBox);
		usernameTextBox.sendKeys(prop.getProperty("username"));//
		js.executeScript("arguments[0].style.border='3px solid purple'", passwordTextBox);
		passwordTextBox.sendKeys(prop.getProperty("password"));//
		login.click();
	    HomePage homePage=new HomePage();
	    
	    return homePage;

		
	}
	public void getFbLoginPage()
	{
		driver.get(prop.getProperty("URL")) ;
	}
	public void DeleteAllCookies()
	{
		driver.manage().deleteAllCookies();
	}
	

	

}
