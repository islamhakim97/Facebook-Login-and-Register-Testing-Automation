package com.facebook.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.FBase.com.testBase;

public class RegisterPage extends testBase{
	public RegisterPage() throws IOException {
		PageFactory.initElements(driver,this);//initialize our elements
	}
	@FindBy(linkText="Create New Account")
	WebElement signUp;
	@FindBy(name="firstname")
	WebElement firstName;
	@FindBy(name="lastname")
	WebElement lastName;
	@FindBy(name="reg_email__")
	WebElement emailOrMobNum;
	@FindBy(name="reg_email_confirmation__")
	WebElement confirmEmail;
	@FindBy(name="reg_passwd__")
	WebElement passswordTextBox;
	@FindBy(xpath="//input[@type='radio']")
	List<WebElement> genderList;
	@FindBy(id="day")
	WebElement day;
	@FindBy(id="month")
	WebElement month;
	@FindBy(id="year")
	WebElement year;
	@FindBy(name="websubmit")
	WebElement Submit;
	
	
	//methods
	//when we declare method that redirects us to anew page it [we should return the [new page object].
	public HomePage performRegister() throws IOException
	{
		js.executeScript("arguments[0].style.border='3px solid gold'", signUp);
		signUp.click();
		firstName.sendKeys("islam");
		lastName.sendKeys("hakim");
		emailOrMobNum.sendKeys("islam@mail.com");
		confirmEmail.sendKeys("islam@mail.eg");
		passswordTextBox.sendKeys("Islam@123456!");
		//Date of birth --> Static Select
				//1- Day Of birth
				Select dayValue= new Select(day);
				dayValue.selectByValue("24");
		        //2- Month Of Birth
				Select monthValue= new Select(month);
				monthValue.selectByValue("4");
				//3- Year Of Birth
				Select yearValue= new Select(year);
				yearValue.selectByValue("1997");

	   //Gender Radio Button
	   genderList.get(1).click();//  Male 
	   Submit.click();
	   return new HomePage();
	    
	}

}
