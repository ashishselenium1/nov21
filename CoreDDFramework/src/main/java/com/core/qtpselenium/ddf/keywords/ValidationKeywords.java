package com.core.qtpselenium.ddf.keywords;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ValidationKeywords extends GenericKeywords{
	
	public void validateTitle(String expectedTitleKey,boolean stopFlag) {
		String expectedTitle = getProperty(expectedTitleKey);
		String actualTitle = driver.getTitle();
		System.out.println(expectedTitle);
		System.out.println(actualTitle);
		if(!expectedTitle.equals(actualTitle)) {
			reportFailure("Titles did not match for login page", stopFlag);
		}
	}
	
	public void validateText(String expectedTextKey,boolean stopFlag) {
		
	}
	
	public void validateElementPresent(String locatorKey, boolean stopFlag) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getLocator(locatorKey)));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getLocator(locatorKey)));
		}catch(Exception e) {
			reportFailure("Element not found "+ locatorKey, stopFlag);
		}
	}
	
	// validate if option is present in a dropdown
	public void isOptionPresentInDropDown(String locatorKey,String option, boolean stopFlag) {
		log("Validating option "+option+" in dropdown "+ locatorKey);
		Select s = new Select(getElement(locatorKey));
		List<WebElement> options = s.getOptions();
		boolean found=false;
		for(int i=0;i<options.size();i++) {
			if(options.get(i).getText().equals(option)) {
				found=true;
				break;
			}
		}
		if(!found)
			reportFailure("Option not found in dropdown "+option, stopFlag);
		else
			log("Validation successful");
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
