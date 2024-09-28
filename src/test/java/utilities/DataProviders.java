package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1
	
	@DataProvider(name="LoginData")             //name should be different for different data providers
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx";//taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility and mention the path bec constructor will invoke under ExcelUtilities
		
		int totalrows=xlutil.getRowCount("Sheet1");	
		int totalcols=xlutil.getCellCount("Sheet1",1);
				
		String logindata[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password as the excel file and data provider should contain same no of rows and columns thats why we capture earlier
		
		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col   //i=1 bec in row zero represent header part that we should ignore 
			{
				logindata[i-1][j]= xlutil.getCellData("Sheet1",i, j);  //1,0  //from the excel getting the data i.e row no and col no and store that into two dimensional array. i and j should be same but here i-1 because array index that are storing is starts from zero.
			}                                                          //so this particular for loop will read all the data form excel sheet rows and columns and put that in a two dimensional array
		}
	return logindata;           //returning two dimension array
				
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
}

