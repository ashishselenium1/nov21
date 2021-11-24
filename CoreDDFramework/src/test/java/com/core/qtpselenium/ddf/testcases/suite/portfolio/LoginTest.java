package com.core.qtpselenium.ddf.testcases.suite.portfolio;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.core.qtpselenium.ddf.keywords.ApplicationKeywords;
import com.core.qtpselenium.ddf.reports.ExtentManager;
import com.core.qtpselenium.ddf.testcases.base.TestBase;
import com.core.qtpselenium.ddf.util.DataUtil;
import com.core.qtpselenium.ddf.util.Xls_Reader;

public class LoginTest extends TestBase{
	
	@Test(dataProvider = "getData")
	public void doLogin(Hashtable<String,String> dataTable) {
		test.log(Status.INFO, dataTable.toString());
		if(dataTable.get("Runmode").equals("N")) {
			// skip in extent reports
			test.log(Status.SKIP, "Skipping the iteration as runmode is N");
			// skip in testng
			throw new SkipException("Skipping the iteration as runmode is N");
		}
		app = new ApplicationKeywords(test);// init the prop file & softAssert & test report
		app.log("Starting Login Test");
		app.openBrowser("Chrome");// init the driver
		app.navigate("app_url");
		app.validateTitle("login_page_title",false);
		app.log("Logging In");
		app.type("username_login_id", dataTable.get("Username"));
		//app.validateElementPresent("password_login_css", true);
		app.type("password_login_css",  dataTable.get("Password"));
		//app.hitEnter("password_login");
		app.click("login_submit_xpath");
		app.assertAll();
		app.log("Test Passed");
	}
	
	@DataProvider
	public Object[][] getData(){
		Xls_Reader  xls  = new Xls_Reader(System.getProperty("user.dir")+"//data//Test Data.xlsx");
		return new DataUtil().getTestData(xls, "PortfolioSuite","Login Test");
	//	Object data[][] = new DataUtil().getTestDataFromJSON("portfolio_suite.json", "Login Test");
	//	return  data;

	}

}
