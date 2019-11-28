package com.phptravels.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.phptravels.constants.FilePath;
import com.phptravels.util.WriteExcelOperation;

public class EffectiveDateHelper {

	LocalDate date = LocalDate.now();
	WriteExcelOperation writeOperation = new WriteExcelOperation();
	
	
	
	public void  effectiveDate(int month ,int numOfDays)
	{      
		LocalDate laterMonth  = date.plusMonths(month);
		  LocalDate earlierMonth=date.plusMonths(-month);
		LocalDate Days=date.plusDays(numOfDays);
		  
		String TodaysDate = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(date);
		String afterMonth = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(laterMonth);
       String beforeMonth=   DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(earlierMonth);
       String dateAfterTenDays = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(Days);

       System.out.println(TodaysDate);
       System.out.println(afterMonth);
       System.out.println(beforeMonth);
       System.out.println(dateAfterTenDays);
       
		writeOperation.setCellDataUnique(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "effectiveDateChange", "Test_ID1", TodaysDate);
		writeOperation.setCellDataUnique(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "effectiveDateChange", "Test_ID2", afterMonth);
		writeOperation.setCellDataUnique(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "effectiveDateChange", "Test_ID3", dateAfterTenDays);
		writeOperation.setCellDataUnique(FilePath.EFFECTIVE_DATE_CHANGE, "effectivedate", "effectiveDateChange", "Test_ID4", beforeMonth);
		
}
	


public static void main(String[] args) {
	
	EffectiveDateHelper helper= new EffectiveDateHelper();
	helper.effectiveDate(1, 2);
	
	
	
	
}


}





