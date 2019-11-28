package com.phptravels.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.phptravels.constants.FilePath;
import com.phptravels.util.NullCellValueException;
import com.phptravels.util.ReadExcelFile;
import com.phptravels.util.WriteExcel;
/**
 * this class contain the methods of dates like currentdate,onemonth later,one month earlier
 * @author Mohit.Jaiswal
 *
 */
public class DateManager {

	// public static ExcelDataReader excelReader;
	ReadExcelFile excelReader = new ReadExcelFile();
	LocalDate date = LocalDate.now();

	public void currentDate() throws Exception {

		WriteExcel write = new WriteExcel(FilePath.EFFECTIVE_DATE_CHANGE);

		String presentDate = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(date);
		System.out.println("current date:" + presentDate);
		write.setCellData("effectivedate", "effectiveDateChange", 2, presentDate);
	}

	public void oneMonthLater() throws Exception, NullCellValueException {
		WriteExcel write = new WriteExcel(FilePath.EFFECTIVE_DATE_CHANGE);

		String afterAMonth = excelReader.getCellData(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "range",
				"Test_ID2");

		int monthLater = Integer.parseInt(afterAMonth);
		LocalDate afterOneMonth = date.plusMonths(monthLater);
		String dateAfterOneMonth = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(afterOneMonth);
		System.out.println("date after one month:" + dateAfterOneMonth);
		write.setCellData("effectivedate", "effectiveDateChange", 3, dateAfterOneMonth);
	}

	public void tenDaysLater() throws Exception, NullCellValueException {
		WriteExcel write = new WriteExcel(FilePath.EFFECTIVE_DATE_CHANGE);
		String afterTenDay = excelReader.getCellData(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "range",
				"Test_ID3");

		int exactTenDay = Integer.parseInt(afterTenDay);
		LocalDate afterTenDays = date.plusDays(exactTenDay);
		String dateAfterTenDays = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(afterTenDays);

		System.out.println("date after ten days:" + dateAfterTenDays);
		write.setCellData("effectivedate", "effectiveDateChange", 4, dateAfterTenDays);
	}

	public void oneMonthEarlier() throws Exception, NullCellValueException {
		WriteExcel write = new WriteExcel(FilePath.EFFECTIVE_DATE_CHANGE);

		String beforeAMonth = excelReader.getCellData(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "range",
				"Test_ID4");
		int beforeMonth = Integer.parseInt(beforeAMonth);
		LocalDate beforeOneMonth = date.minusMonths(beforeMonth);

		String dateBeforeOneMonth = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(beforeOneMonth);

		System.out.println("date before one month:" + dateBeforeOneMonth);
		write.setCellData("effectivedate", "effectiveDateChange", 5, dateBeforeOneMonth);
	}

	public static void main(String[] args) throws Exception, NullCellValueException {

		DateManager checkDate = new DateManager();
		checkDate.currentDate();
		checkDate.oneMonthLater();
		checkDate.tenDaysLater();
		checkDate.oneMonthEarlier();

	}

}
