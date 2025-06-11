package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

public class excelsheet_utility {
	
	XSSFWorkbook wb;
	FileInputStream fs;
	XSSFSheet sh;
	XSSFRow rw;
	XSSFCell cl;
	
	String path;
	
	

	public excelsheet_utility(String path) {
		
		this.path = path;
	}
	
	public int getrowcount(String sheetname) throws IOException {
		
		fs = new FileInputStream(path);
		
		wb = new XSSFWorkbook(fs);
		
		sh = wb.getSheet(sheetname);
		
		int row_count = sh.getLastRowNum();
		
		wb.close();
		
		fs.close();
		
		return row_count;
	}
	
	
	public int getcellcount(String sheetname, int rownumber) throws IOException {
		
		fs = new FileInputStream(path);
		
		wb = new XSSFWorkbook(fs);
		
		sh = wb.getSheet(sheetname);
		
		rw = sh.getRow(rownumber);
		
		int cell_count = rw.getLastCellNum();
		
		wb.close();
		
		fs.close();
		
		return cell_count;
	}
	
	public String getcelldata(String sheet_name, int rownumber, int cellnumber) throws IOException {
		
		fs = new FileInputStream(path);
		wb = new XSSFWorkbook(fs);
		sh = wb.getSheet(sheet_name);
		rw = sh.getRow(rownumber);
		cl = rw.getCell(cellnumber);
		
		DataFormatter datafor = new DataFormatter(); // will format the of anytype to string.
		
		String data;
		try {
			data = datafor.formatCellValue(cl); 
		}
		catch(Exception e){
			data = "";
		}
		
		return data;
		
	}
	
	public void setcelldata(String sheetname, int rownumber, int cellnumber, String data) throws IOException {
		File xlfile = new File(path);
		if (!xlfile.exists()) {
			FileOutputStream fo = new FileOutputStream(path);
			wb = new XSSFWorkbook();
			wb.write(fo);	
		}
		
		fs = new FileInputStream(path);
		wb = new XSSFWorkbook(fs);
		if(wb.getSheetIndex(sheetname)==-1) {
			wb.createSheet(sheetname);
		}
		sh = wb.getSheet(sheetname);
		
		if(sh.getRow(rownumber)==null) {
			sh.createRow(rownumber);
		}
		rw = sh.getRow(rownumber);
		
		cl = rw.createCell(cellnumber);
		cl.setCellValue(data);
		FileOutputStream fo = new FileOutputStream(path);
		wb = new XSSFWorkbook();
		wb.write(fo);
		wb.close();
		fs.close();
		fo.close();
	}
	
	public void setcolourgreen(String sheetname, int rownumber, int cellnumber) throws IOException {
		
		fs = new FileInputStream(path);
		wb = new XSSFWorkbook(fs);
		sh = wb.getSheet(sheetname);
		rw = sh.getRow(rownumber);
		cl = rw.getCell(cellnumber);
		
		XSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cl.setCellStyle(style);
		FileOutputStream fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fs.close();
		fo.close();
		
	}
public void setredgreen(String sheetname, int rownumber, int cellnumber) throws IOException {
		
		fs = new FileInputStream(path);
		wb = new XSSFWorkbook(fs);
		sh = wb.getSheet(sheetname);
		rw = sh.getRow(rownumber);
		cl = rw.getCell(cellnumber);
		
		XSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cl.setCellStyle(style);
		FileOutputStream fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fs.close();
		fo.close();
		
	}

}
