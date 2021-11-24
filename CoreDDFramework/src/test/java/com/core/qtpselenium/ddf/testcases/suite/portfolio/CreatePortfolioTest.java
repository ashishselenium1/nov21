package com.core.qtpselenium.ddf.testcases.suite.portfolio;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.core.qtpselenium.ddf.keywords.ApplicationKeywords;
import com.core.qtpselenium.ddf.reports.ExtentManager;
import com.core.qtpselenium.ddf.testcases.base.TestBase;
import com.core.qtpselenium.ddf.util.DataUtil;
import com.core.qtpselenium.ddf.util.Xls_Reader;

public class CreatePortfolioTest extends TestBase{
	
	@Test(dataProvider = "getData")
	public void createPortfolio(Hashtable<String,String> dataTable) {
		test.log(Status.INFO, dataTable.toString());
		if(dataTable.get("Runmode").equals("N")) {
			// skip in extent reports
			test.log(Status.SKIP, "Skipping the iteration as runmode is N");
			// skip in testng
			throw new SkipException("Skipping the iteration as runmode is N");
		}
		app = new ApplicationKeywords(test);// init the prop file & softAssert & test report
		app.log("Starting Create portfolio Test");
		app.openBrowser("Chrome");// init the driver
		// login
		app.defaultLogin();
		// create the portfolio
		app.click("createPortfolio_id");
		app.clear("portfolio_name_xpath");
		app.type("portfolio_name_xpath", dataTable.get("Portfolio Name"));
		app.click("create_port_button_xpath");
		app.wait(5);
		app.isOptionPresentInDropDown("portfolio_dropdown_id",dataTable.get("Portfolio Name"),true);
	}
	
	@DataProvider
	public Object[][] getData(){
		Xls_Reader  xls  = new Xls_Reader(System.getProperty("user.dir")+"//data//Test Data.xlsx");
		return new DataUtil().getTestData(xls, "PortfolioSuite","Create Portfolio");

	}
}
