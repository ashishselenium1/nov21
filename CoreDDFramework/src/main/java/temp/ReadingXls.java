package temp;

import com.core.qtpselenium.ddf.util.Xls_Reader;

public class ReadingXls {

	public static void main(String[] args) {
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
		
		// Read the data
		for(int rNum=dataRowNum;rNum<dataRowNum+rows;rNum++) {
			
			for(int cNum=0;cNum<cols;cNum++) {
				String d = xls.getCellData("PortfolioSuite", cNum, rNum);
				System.out.println(d);
			}
			System.out.println("----------------------");
			
		}
		
		
		
		
		
		
		
		

	}

}
