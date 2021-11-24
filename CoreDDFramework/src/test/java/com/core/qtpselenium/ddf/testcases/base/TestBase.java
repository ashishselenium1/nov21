package com.core.qtpselenium.ddf.testcases.base;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.core.qtpselenium.ddf.keywords.ApplicationKeywords;
import com.core.qtpselenium.ddf.reports.ExtentManager;

public class TestBase {
	
	public String testName;

	public ExtentReports rep;
	public ExtentTest test;
	public ApplicationKeywords app ;
	
	@BeforeMethod
	public void init(ITestContext context) {
		System.out.println("Name - "+context.getCurrentXmlTest().getName());
		testName = context.getCurrentXmlTest().getName();
		rep =  ExtentManager.getReport(System.getProperty("user.dir")+"//reports//");
		test = rep.createTest(testName);
	}
	
	@AfterMethod
	public void quit() {
		if(app!=null)
			app.quit();
		rep.flush();
	}
	


}
