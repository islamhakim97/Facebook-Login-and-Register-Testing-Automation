package com.facebook.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.FBase.com.testBase;

public class HomePage extends testBase{

	public HomePage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	//Put Elements Of The HomePage with PageFactory Object Technique
	
    @FindBy(xpath="//image[@style='height:28px;width:28px']")
    List<WebElement >elements;
    WebElement img=elements.get(1);
	
	
	//Put Methods Of The HomePage with PageFactory Object Technique
	public boolean profileImgisDisplayed()
	{
		return img.isDisplayed();
	}

}
