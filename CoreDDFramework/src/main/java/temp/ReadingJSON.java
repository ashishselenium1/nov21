package temp;

import java.io.FileReader;
import java.io.Reader;
import java.util.Hashtable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonObject;

public class ReadingJSON {

	public static void main(String[] args) {
		// JSONArray  []
		// JSONObject {}
		try {
			String testName = "Create Portfolio";
			Reader reader = new FileReader(System.getProperty("user.dir")+"//data//portfolio_suite.json");
	        JSONParser parser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) parser.parse(reader);
	        //System.out.println(jsonObject.toJSONString());
	        
	        String company = (String)jsonObject.get("company");
	        JSONArray testData = (JSONArray)jsonObject.get("testdata");
	       // System.out.println(company);
	        //System.out.println(testData.toString());
	        Object[][] data = null;
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

	}

}
