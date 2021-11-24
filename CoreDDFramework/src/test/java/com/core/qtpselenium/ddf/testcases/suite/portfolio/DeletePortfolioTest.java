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

public class DeletePortfolioTest extends TestBase {
// read data from json
// grid
// jenkins	
	
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
		app.log("Starting Delete portfolio Test");
		app.openBrowser("Chrome");// init the driver
		// login
		app.defaultLogin();
		// delete the portfolio
		System.out.println(dataTable.toString());
		System.out.println("Portfolio Name "+ dataTable.get("Portfolio Name"));
		app.select("portfolio_dropdown_id", dataTable.get("Portfolio Name"));
		// delete the option
		// verify if its deleted
		
		
	}
	
	@DataProvider
	public Object[][] getData(){
		Xls_Reader  xls  = new Xls_Reader(System.getProperty("user.dir")+"//data//Test Data.xlsx");
		return new DataUtil().getTestData(xls, "PortfolioSuite","Delete Portfolio");

	}
}
