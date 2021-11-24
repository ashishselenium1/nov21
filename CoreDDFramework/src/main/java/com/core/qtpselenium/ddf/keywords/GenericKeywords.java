package com.core.qtpselenium.ddf.keywords;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

public class GenericKeywords extends UtilityKeywords{
	
	public void openBrowser(String browserName) {
		log("Opening browser "+ browserName);
		
		
		if(getProperty("grid_run").equals("Y")) {
			// RemoteWebDriver
			DesiredCapabilities cap=null;
			if(browserName.equals("Mozilla")){
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}else if(browserName.equals("Chrome")){
				 cap = DesiredCapabilities.chrome();
				 cap.setBrowserName("chrome");
				 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			
			if(browserName.equals("Chrome")) {
				//System.setProperty("webdriver.chrome.driver","D:\\Ashish\\driverexes\\chromedriver.exe");
			    driver = new ChromeDriver();
			}else if(browserName.equals("Mozilla")) {
				//System.setProperty("webdriver.chrome.driver","D:\\Ashish\\driverexes\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
		}
		
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	public void navigate(String urlKey) {
		log("Navigating to"+ getProperty(urlKey));
		driver.get(getProperty(urlKey));
	}
	
	// hit enter button of keyboard
	public void hitEnter(String locatorKey) {
		getElement(locatorKey).sendKeys(Keys.ENTER);
	}
	
	// click on element on web page
	public void click(String locatorKey) {
		log("Clicking on "+locatorKey);
		getElement(locatorKey).click();
	}
	
	// type in a text field
	public void type(String locatorKey, String data) {
		log("Typing in "+ locatorKey+" data "+data);
		getElement(locatorKey).sendKeys(data);
	}
	
	// clear the value of textfield
	public void clear(String locatorKey) {
		log("Clearing "+ locatorKey);
		getElement(locatorKey).clear();
	}
	
	public void wait(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// select value from dropdown
	public void select(String locatorKey, String option) {
		log("Selecting "+option+" in dropdown "+ locatorKey);
		Select s = new Select(getElement(locatorKey));
		s.selectByVisibleText(option);
	}
	
	public void quit() {
		if(driver!=null)
			driver.quit();
	}
    // Generic keywords - Applicable to all web pages
    // validation keywords
    // application keywords
	
	
	
	
	
	
	
	
	
	
	
	
}
