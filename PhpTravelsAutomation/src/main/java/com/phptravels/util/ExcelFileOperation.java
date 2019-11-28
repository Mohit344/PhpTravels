package com.phptravels.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * In this Class i'm reading excel file
 */
public class ExcelFileOperation {
	static FileInputStream fis = null;
	static XSSFWorkbook workbook = null;
	static XSSFSheet worksheet = null;

	public ExcelFileOperation(String filepath) {
		try {
			fis = new FileInputStream(new File(filepath));
		} catch (FileNotFoundException e) {
			System.out.println("Sorry! File not Found.");
			e.printStackTrace();
		}
		// Class used to read excel file and read the data
		try {
			workbook = new XSSFWorkbook(fis);

		} catch (IOException e) {
			System.out.println("File path not found");
			e.printStackTrace();
		}
		worksheet = workbook.getSheetAt(0);
	}

	/*
	 * In this method i'm reading excel file using dependency of apache-poi
	 */

	public Iterator<Row> getData() throws IOException {

		// iterating through rows and getting row number
		Iterator<Row> rows = worksheet.iterator();
		return rows;
	}

	public int getNoOfRows() {
		int rowIndex = worksheet.getLastRowNum();

		return rowIndex + 1;
	}

	public int getNoOfColumns() {
		Iterator<Row> rowIterator = worksheet.rowIterator();
		int columnIndex = 0;
		/**
		 * Escape the header row *
		 */
		if (rowIterator.hasNext()) {
			Row headerRow = rowIterator.next();
			// get the number of cells in the header row
			columnIndex = headerRow.getPhysicalNumberOfCells();
		}
		return columnIndex;
	}
}
