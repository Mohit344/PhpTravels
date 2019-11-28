package com.phptravels.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.phptravels.constants.FilePath;

public class InsurancePolicyDate {

	
	ReadExcelFile excelReader = new ReadExcelFile();
	LocalDate date = LocalDate.now();
	WriteExcelOperation writeOperation = new WriteExcelOperation();
	public void currentDate() throws Exception {

	

		String TodaysDate = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(date);
		System.out.println("current date:" + TodaysDate);
	
		writeOperation.setCellDataUnique(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "effectiveDateChange", "Test_ID1", TodaysDate);
	}

	public void oneMonthLater() throws Exception, NullCellValueException {
		

		String afterAMonth = excelReader.getCellData(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "range",
				"Test_ID2");

		int monthLater = Integer.parseInt(afterAMonth);
		LocalDate afterOneMonth = date.plusMonths(monthLater);
		String dateAfterOneMonth = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(afterOneMonth);
		System.out.println("date after one month:" + dateAfterOneMonth);

		writeOperation.setCellDataUnique(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "effectiveDateChange", "Test_ID2", dateAfterOneMonth);
	}

	public void tenDaysLater() throws Exception, NullCellValueException {
	
		String afterTenDay = excelReader.getCellData(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "range",
				"Test_ID3");

		int exactTenDay = Integer.parseInt(afterTenDay);
		LocalDate afterTenDays = date.plusDays(exactTenDay);
		String dateAfterTenDays = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(afterTenDays);

		System.out.println("date after ten days:" + dateAfterTenDays);
		
		writeOperation.setCellDataUnique(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "effectiveDateChange", "Test_ID3", dateAfterTenDays);
	}

	public void oneMonthEarlier() throws Exception, NullCellValueException {
		
		String beforeAMonth = excelReader.getCellData(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "range",
				"Test_ID4");
		int beforeMonth = Integer.parseInt(beforeAMonth);
		LocalDate beforeOneMonth = date.minusMonths(beforeMonth);

		String dateBeforeOneMonth = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(beforeOneMonth);

		System.out.println("date before one month:" + dateBeforeOneMonth);
		writeOperation.setCellDataUnique(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "effectiveDateChange", "Test_ID4", dateBeforeOneMonth);
	}

	public static void main(String[] args) throws Exception, NullCellValueException {

		InsurancePolicyDate checkDate = new InsurancePolicyDate();
		checkDate.currentDate();
		checkDate.oneMonthLater();
		checkDate.tenDaysLater();
		checkDate.oneMonthEarlier();

	}

}
