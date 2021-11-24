package com.core.qtpselenium.ddf.keywords;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.core.qtpselenium.ddf.reports.ExtentManager;

public class UtilityKeywords {
	WebDriver driver;
	Properties prop;
	SoftAssert softAssert;
	ExtentTest test;

	// Central function to extract the element
	public WebElement getElement(String locatorKey) {
		WebElement e = null;
		try {
			// read the locator from prop file
			By locator = getLocator(locatorKey);
			//String locator=getProperty(locatorKey);
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			// presence check
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			// visibility check
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			
			e = driver.findElement(locator);
			//By loc = By.id("xxxxxxxx");
			//driver.findElement(loc).click();
			
			
		}catch(Exception ex) {
			// element not found - report this in reports
			reportFailure("Element not found "+ locatorKey, true);
		}
		return e;
	}
	
	public String getProperty(String key) {
			return prop.getProperty(key);
	}
	
	public By getLocator(String locatorKey) {
		By locator = null;
		if(locatorKey.endsWith("_xpath"))
			locator = By.xpath(getProperty(locatorKey));
		else if(locatorKey.endsWith("_id"))
			locator = By.id(getProperty(locatorKey));
		else if(locatorKey.endsWith("_name"))
			locator = By.name(getProperty(locatorKey));
		else if(locatorKey.endsWith("_css"))
			locator = By.cssSelector(getProperty(locatorKey));
		
		return locator;
	}
	
	public void reportFailure(String failureMsg, boolean stopFlag) {
		test.log(Status.FAIL, failureMsg);// fail in extent reports
		softAssert.fail(failureMsg);// failing in testng
		takeScreenShot();// take the screenshot and put in reports
		if(stopFlag)
			assertAll();
	}
	
	public void assertAll() {
		softAssert.assertAll();
	}
	
	/******************REPORTING IN EXTENT REPORTS*********************/
	// generic log function
	public void log(String msg) {
		System.out.println(msg);
		test.log(Status.INFO, msg);
	}
	public void takeScreenShot() {
		// fileName of the screenshot
			Date d=new Date();
			String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
				// take screenshot
				File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				try {
					// store screenshot in screenshots folder
					FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenshotFile));
					//put screenshot file in reports
				    test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	

}
