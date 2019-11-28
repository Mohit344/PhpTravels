package com.phptravels.testscript;

import org.testng.annotations.Test;

import com.phptravels.constants.FilePath;
import com.phptravels.pages.EffectiveDateHelper;
import com.phptravels.util.NullCellValueException;
import com.phptravels.util.ReadExcelFile;

public class TestEffectiveDate {
	ReadExcelFile excelReader = new ReadExcelFile();
	EffectiveDateHelper helper = new EffectiveDateHelper();

	@Test
	public void getDate() throws NullCellValueException {

		String month = excelReader.getCellData(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "range", "Test_ID2");
		String noOfDay = excelReader.getCellData(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "range", "Test_ID3");

		int numOfmonth = Integer.parseInt(month);
		int numOfDays = Integer.parseInt(noOfDay);

		helper.effectiveDate(numOfmonth, numOfDays);
	}

}
