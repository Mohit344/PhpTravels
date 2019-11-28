package com.phptravels.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * this class is for write the data in the excel.
 * @author Mohit.Jaiswal
 *
 */
public class WriteExcel {

	
	
	
	
	  public FileInputStream fis = null;
	    public FileOutputStream fos = null;
	    public XSSFWorkbook workbook = null;
	    public XSSFSheet sheet = null;
	    public XSSFRow row = null;
	    public XSSFCell cell = null;
	    String xlFilePath;
	 
	    public WriteExcel(String xlFilePath) throws Exception
	    {
	        this.xlFilePath = xlFilePath;
	        fis = new FileInputStream(xlFilePath);
	        workbook = new XSSFWorkbook(fis);
	        fis.close();
	    }
	 
	    public boolean setCellData(String sheetName, String colName, int rowNum, String value)
	    {
	        try
	        {
	            int col_Num = -1;
	            sheet = workbook.getSheet(sheetName);
	 
	            row = sheet.getRow(0);
	            for (int i = 0; i < row.getLastCellNum(); i++) {
	                if (row.getCell(i).getStringCellValue().trim().equals(colName))
	                {
	                    col_Num = i;
	                }
	            }
	 
	            sheet.autoSizeColumn(col_Num);
	            row = sheet.getRow(rowNum - 1);
	            if(row==null)
	                row = sheet.createRow(rowNum - 1);
	 
	            cell = row.getCell(col_Num);
	            if(cell == null)
	                cell = row.createCell(col_Num);
	 
	            cell.setCellValue(value);
	 
	            fos = new FileOutputStream(xlFilePath);
	            workbook.write(fos);
	            fos.close();
	        }
	        catch (Exception ex)
	        {
	            ex.printStackTrace();
	            return  false;
	        }
	        return true;
	    }
	
public static void main(String[] args) throws Exception {
	WriteExcel write = new WriteExcel("./src/test/resources/testdata/writeexcel.xlsx");
	
	write.setCellData("sheet1", "username", 2, "mohit");
	
}

}


