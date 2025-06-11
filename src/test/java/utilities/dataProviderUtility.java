package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class dataProviderUtility {
	
	@DataProvider(name="excel_data")
	public String [] [] data() throws IOException{
		
		excelsheet_utility xlutil = new excelsheet_utility(".//testdata//excel_data.xlsx");
		
		int rowcount = xlutil.getrowcount("Sheet_1");
		int cellcount = xlutil.getcellcount("Sheet_1",1);
		
		String datatype[] [] = new String[rowcount][cellcount]; // you have to assign the value other wise it will throw
		//null point exceptio, because it doesnt allocated any value to it yet.
		
		for (int i=1;i<=rowcount;i++) { //for loop for rows
			
			for (int j=0;j<cellcount;j++) {
				
				datatype[i-1][j] = xlutil.getcelldata("Sheet_1", i, j);
			}
		}
		return datatype;
	}

}
