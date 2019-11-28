package com.phptravels.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.phptravels.constants.FilePath;
import com.phptravels.helper.CommonUtility;
import com.phptravels.helper.PropertyFileLoader;
import com.phptravels.logreport.LogReport;
import com.phptravels.util.ExcelDataReader;
import com.phptravels.util.NullCellValueException;
import com.phptravels.util.ReadExcelFile;
import com.phptravels.util.WriteExcel;
import com.phptravels.validation.VerificationManager;

/**
 * Class validates the different functionality of phptravels website
 * 
 * @author Mohit.Jaiswal
 *
 */
public class PhpTravelsPages {

	// public UpdatedExcelFileReader excelReader;
	LogReport log = new LogReport();
	public ExcelDataReader excel;
	CommonUtility utility = new CommonUtility();
	PropertyFileLoader properties = new PropertyFileLoader();
	VerificationManager validate = new VerificationManager();
	ReadExcelFile excelReader = new ReadExcelFile();
	Logger logger = Logger.getLogger(PhpTravelsPages.class);

	public void accountPageValidation(WebDriver driver) throws Exception, NullCellValueException {

		String actualDisplayedMessage = utility.getText(driver,
				properties.phpTravellLocator("loc.text.messageDisplayed"));

		String expectedDisplayedMessage = excelReader.getCellData(FilePath.PHPTRAVELS_DASHBOARD, "dashBoardData",
				"DisplayedMessage", "testID1");

		log.logger.info("verifying  the displayedMessage");

		validate.verify(actualDisplayedMessage, expectedDisplayedMessage, "message not matched");
		log.logger.info("verified the displayedMessaage");
		String color = driver.findElement(By.cssSelector(properties.phpTravellLocator("loc.txt.color.bookingTab")))
				.getAttribute("class");

		String bookingTab = excelReader.getCellData(FilePath.PHPTRAVELS_DASHBOARD, "dashBoardData", "BookingTab",
				"testID1");

		log.logger.info(" verifying booking is tab is selected");
		validate.verifyContent(color, bookingTab, "booking Tab is not selected");
		log.logger.info("verified booking tab is selected");

	}

	public static String getDateTimeFormate(String dateFormate) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormate);
		Date date = new Date();
		String currentDate = dateFormat.format(date).toString();

		return currentDate;
	}

	public void dateTimeValidation(WebDriver driver) {

		String actualTime = utility.getText(driver, properties.phpTravellLocator("loc.text.time"));

		String actualTimming = actualTime.substring(0, 5);
		System.out.println(actualTimming);
		String time = getDateTimeFormate("HH:mm");
		String date = getDateTimeFormate("dd-MMMM-YYY");

		String actualDate = utility.getText(driver, properties.phpTravellLocator("loc.text.date"));
		validate.verify(actualTimming, time, "time not matched");
		validate.verify(actualDate, date, "date not matched");

	}

	public static void main(String[] args) {
		System.out.println(getDateTimeFormate("HH:mm"));
		System.out.println(getDateTimeFormate("dd-MM-YY"));
		System.out.println(getDateTimeFormate("dd-MMM-YYY"));
		System.out.println(getDateTimeFormate("dd-MMMM-YYY"));

	}

	public void verifyBokingId(WebDriver driver) {
		String invoicePageUrl = driver.getCurrentUrl();
		System.out.println(invoicePageUrl);
	}

	int randomInteger;
	String bookingId[];
	String bookingDetailNumber[];
	String bookingDetailDueDate[];
	String actualBookingDetails;

	public void selectRandomBookingDetails(WebDriver driver) throws Exception

	{

		WriteExcel write = new WriteExcel(FilePath.PHPTRAVELS_DASHBOARD);

		List<WebElement> totalInvoice = utility.getElementsList(properties.phpTravellLocator("loc.btn.allInvoice"),
				driver);
		System.out.println(totalInvoice.size());
		for (int index = 1; index <= totalInvoice.size(); index++) {
			actualBookingDetails = utility.getText(driver,
					properties.phpTravellLocator("loc.txt.bookingdetails").replace("***", index + ""));

			String actualStatus = utility.getText(driver,
					properties.phpTravellLocator("loc.txt.bookingStatus").replace("***", index + ""));
			String splitBooking[] = actualBookingDetails.split("\n", 3);
			bookingId = splitBooking[0].split(" ", 3);

			bookingDetailNumber = splitBooking[1].split(" ", 3);
			bookingDetailDueDate = splitBooking[2].split(" ", 3);

			log.logger.info("storing booking detail");
			write.setCellData("bookingDetail", "BookingId", index + 1, bookingId[2]);
			log.logger.info("storing booking id");
			write.setCellData("bookingDetail", "Booking Number", index + 1, bookingDetailNumber[2]);
			log.logger.info("storing booking number");
			write.setCellData("bookingDetail", "DueDate", index + 1, bookingDetailDueDate[2]);
			log.logger.info("storing booking status ");
			write.setCellData("bookingDetail", "Status", index + 1, actualStatus);
			log.logger.info("stored booking id ,number ,due date,status ");

		}

		excel = new ExcelDataReader(FilePath.PHPTRAVELS_DASHBOARD);
		Random random = new Random();
		randomInteger = random.nextInt(totalInvoice.size());
		log.logger.info("generating the random number for booking random booking detail " + totalInvoice.size());
		System.out.println(randomInteger + 1);
		String locator = properties.phpTravellLocator("loc.btn.randomInvoice").replace("***", randomInteger + 1 + "");
		utility.scrollToDown(driver, locator);
		actualBookingDetails = utility.getText(driver,
				properties.phpTravellLocator("loc.txt.bookingdetails").replace("***", randomInteger + 1 + ""));
		log.logger.info("getting  of random booking details");
		String actualStatus = utility.getText(driver,
				properties.phpTravellLocator("loc.txt.bookingStatus").replace("***", randomInteger + 1 + ""));
		log.logger.info("getting the booking status");
		String bookId = excel.getCellData("bookingDetail", "BookingId", randomInteger + 1);
		log.logger.info("reading the bookingid from excel");
		String bookNum = excel.getCellData("bookingDetail", "Booking Number", randomInteger + 1);
		log.logger.info("reading the booking number from excel");
		String dueDate = excel.getCellData("bookingDetail", "DueDate", randomInteger + 1);
		log.logger.info("reading the booking due date");
		String status = excel.getCellData("bookingDetail", "Status", randomInteger + 1);
		log.logger.info("reading the booking status ");

		validate.verify(bookingId[2], bookId, "booking id does not match");
		log.logger.info("validated the bookingId ");
		validate.verify(bookingDetailNumber[2], bookNum, "booking number does not match");
		log.logger.info("validated the booking number");
		validate.verify(bookingDetailDueDate[2], dueDate, "booking duedate not match");
		log.logger.info("validated the booking due date");
		validate.verifyContent(actualStatus, status, "status does not match");
		log.logger.info("validated booking status");

		utility.clickElement(driver, locator);
		log.logger.info("click on the random invoice");

	}
}
