package com.core.qtpselenium.ddf.keywords;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class ApplicationKeywords extends ValidationKeywords{
	
	public ApplicationKeywords(ExtentTest test) {
		// init the prop file
		try {
			prop = new Properties();
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//project.properties");
			prop.load(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// init the softAssert
		softAssert  = new SoftAssert();
		
		// init test report object
		this.test=test;
	}
	
	
	public void defaultLogin() {
		log("Logging in with default credentials");
		navigate("app_url");
		type("username_login_id", getProperty("defaultUserId"));
		type("password_login_css",  getProperty("defaultPassword"));
		click("login_submit_xpath");
	}
	
	
	public void selectPortfolio() {
		
	}

}
