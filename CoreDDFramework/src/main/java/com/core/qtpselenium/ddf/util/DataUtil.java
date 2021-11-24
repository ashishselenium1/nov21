package com.core.qtpselenium.ddf.util;

import java.io.FileReader;
import java.io.Reader;
import java.util.Hashtable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataUtil {
	
	public Object[][] getTestData(Xls_Reader xls,String sheetName , String testCase){
		// Line number on which test lies
		// total rows in test
		// total cols in test
		
		
		// Line number on which test lies
		System.out.println("Finding test "+testCase);
		int testStartRowNum=1;
		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(testCase)) {
			testStartRowNum++;
		}
		
		System.out.println("Test case starts from row "+testStartRowNum);
		
		// total rows in test
		int colRowNum=testStartRowNum+1;
		int dataRowNum=testStartRowNum+2;
		int rows=0;
		
		while(!xls.getCellData(sheetName, 0, dataRowNum+rows).equals("")) {
			rows++;
		}
		
		System.out.println("Total Rows "+rows);
		
		// total cols in test
		int cols=0;
		while(!xls.getCellData(sheetName, cols, colRowNum).equals("")) {
			cols++;	
		}
		
		System.out.println("Total Cols "+cols);
		Object[][] data =new Object[rows][1];
		int index=0;
		// Read the data
		for(int rNum=dataRowNum;rNum<dataRowNum+rows;rNum++) {
			Hashtable<String,String> table = new Hashtable<String,String>();
			
			for(int cNum=0;cNum<cols;cNum++) {
				String key = xls.getCellData(sheetName, cNum, colRowNum);
				String value = xls.getCellData(sheetName, cNum, rNum);
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
	
	public Object[][] getTestDataFromJSON(String fileName, String testName){
		Object[][] data = null;
		
		try {
			//String testName = "Create Portfolio";
			Reader reader = new FileReader(System.getProperty("user.dir")+"//data//"+fileName);
	        JSONParser parser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) parser.parse(reader);
	        //System.out.println(jsonObject.toJSONString());
	        
	        String company = (String)jsonObject.get("company");
	        JSONArray testData = (JSONArray)jsonObject.get("testdata");
	       // System.out.println(company);
	        //System.out.println(testData.toString());
	        
	        for(int i=0;i<testData.size();i++) {
	        	JSONObject testDataSet = (JSONObject)testData.get(i);
	        	String testNameJSON = (String)testDataSet.get("testname");
	        	if(testNameJSON.equals(testName)) {
	        	JSONArray testCaseData = (JSONArray)testDataSet.get("testcasedata");
	        	//System.out.println(testName);
	        	//System.out.println(testCaseData);
	        	 data = new Object[testCaseData.size()][1];
	        	for(int j=0;j<testCaseData.size();j++) {
	        		Hashtable<String , String> table = new Hashtable<String,String>();
	        		System.out.println("**");
	        		JSONObject dataSetDetails=(JSONObject) testCaseData.get(j);
	        		//System.out.println(dataSetDetails);
	        		JSONArray parameters = (JSONArray) dataSetDetails.get("parameters");
	        		//System.out.println(parameters);
	        		for(int k=0;k<parameters.size();k++) {
	        			JSONObject parameter =  (JSONObject) parameters.get(k);
	        			//System.out.println(parameter);
	        			String parameterName = (String)parameter.get("parametername");
	        			String parameterValue = (String)parameter.get("parametervalue");
	        			System.out.println(parameterName+" ---- "+parameterValue);
	        			table.put(parameterName, parameterValue);
	        		}
	        		System.out.println(table);
	        		data[j][0]=table;
	        	}
	        	System.out.println("------------------------------------");
	        }
	        }
	        
		}catch(Exception e) {
			
		}

		return data;
	}

}
