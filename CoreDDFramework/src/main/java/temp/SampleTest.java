package temp;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.core.qtpselenium.ddf.util.Xls_Reader;

public class SampleTest {
	
	// username,password,phone,email
	@Test(dataProvider = "getData")
	public void  register(Hashtable<String,String> table) {
		System.out.println(table.get("Runmode") +" --- "+ table.get("Username")+" --- "+ table.get("Password") +" --- "+ table.get("Result"));
		
	}
	
	@DataProvider
	public Object[][] getData(){
		Xls_Reader  xls  = new Xls_Reader(System.getProperty("user.dir")+"//data//Test Data.xlsx");
		String testCase = "Login Test";
		
		// Line number on which test lies
		// total rows in test
		// total cols in test
		
		
		// Line number on which test lies
		int testStartRowNum=1;
		while(!xls.getCellData("PortfolioSuite", 0, testStartRowNum).equals(testCase)) {
			testStartRowNum++;
		}
		
		System.out.println("Test case starts from row "+testStartRowNum);
		
		// total rows in test
		int colRowNum=testStartRowNum+1;
		int dataRowNum=testStartRowNum+2;
		int rows=0;
		
		while(!xls.getCellData("PortfolioSuite", 0, dataRowNum+rows).equals("")) {
			rows++;
		}
		
		System.out.println("Total Rows "+rows);
		
		// total cols in test
		int cols=0;
		while(!xls.getCellData("PortfolioSuite", cols, colRowNum).equals("")) {
			cols++;	
		}
		
		System.out.println("Total Cols "+cols);
		Object[][] data =new Object[rows][1];
		int index=0;
		// Read the data
		for(int rNum=dataRowNum;rNum<dataRowNum+rows;rNum++) {
			Hashtable<String,String> table = new Hashtable<String,String>();
			
			for(int cNum=0;cNum<cols;cNum++) {
				String key = xls.getCellData("PortfolioSuite", cNum, colRowNum);
				String value = xls.getCellData("PortfolioSuite", cNum, rNum);
				table.put(key, value);
				//data[index][cNum]=value;
				// index   cNum
				//  0        0      0,0
				//  0        1      0,1
				//  0        2      0,2  
				//  1        0
				System.out.println(value);
			}
			data[index][0]=table;
			index++;
			System.out.println("----------------------");
			
		}
		
		return data;

	}

}
