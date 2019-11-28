package com.phptravels.testscript;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.phptravels.constants.FilePath;
import com.phptravels.constants.TimeConstant;
import com.phptravels.helper.CommonUtility;
import com.phptravels.helper.PropertyFileLoader;
import com.phptravels.logreport.LogReport;
import com.phptravels.pages.PhpTravelsPages;
import com.phptravels.testbase.TestBase;
import com.phptravels.util.NullCellValueException;
import com.phptravels.util.ReadExcelFile;
import com.phptravels.validation.VerificationManager;
import com.phptravels.waits.Waits;

/**
 * In this class i'm maintaining all my testscripts
 * @author Mohit.Jaiswal
 *
 */
public class TestPhpTravelsSript extends TestBase {
	CommonUtility utility = new CommonUtility();
	PropertyFileLoader properties = new PropertyFileLoader();
	VerificationManager validate = new VerificationManager();
	ReadExcelFile excelReader = new ReadExcelFile();
	PhpTravelsPages travels = new PhpTravelsPages();
	LogReport log = new LogReport();  
	Waits time = new Waits();
	Logger logger = Logger.getLogger(TestPhpTravelsSript.class);

	@Test(priority = 1)
	public void dashBoard() throws Exception, NullCellValueException {

		String userId = excelReader.getCellData(FilePath.PHPTRAVELS_DASHBOARD, "dashBoardData", "UserId", "testID1");
		log.logger.info("enter the userid");
		utility.clickAndSendText(driver, properties.phpTravellLocator("loc.input.id"), TimeConstant.WAIT, userId);

		String userPassward = excelReader.getCellData(FilePath.PHPTRAVELS_DASHBOARD, "dashBoardData", "UserPassward",
				"testID1");
		log.logger.info("enter the userPassward");
		utility.clickAndSendText(driver, properties.phpTravellLocator("loc.input.passward"), TimeConstant.WAIT,
				userPassward);
		log.logger.info("click on login button");
		utility.clickElement(driver, properties.phpTravellLocator("loc.login.btn"));
		log.logger.info("validation 'hi' message is comming or not");
		travels.accountPageValidation(driver);
		log.logger.info("validated message is displayed");
		travels.dateTimeValidation(driver);
		log.logger.info("validated date and time");

		log.logger.info("click on the gotIt! button");
		
		log.logger.info("click on the invoice button");
		utility.clickElement(driver, properties.phpTravellLocator("loc.btn.invoice"));

		String mainWindowHandle = driver.getWindowHandle();

		for (String childWindowHandle : driver.getWindowHandles()) {

			// If window handle is not main window handle then close it
			if (!childWindowHandle.equals(mainWindowHandle)) {
				driver.switchTo().window(childWindowHandle);
				// Close child windows
				log.logger.info("validate url is matching with the booking number");
				String invoicePageUrl = driver.getCurrentUrl();
				log.logger.info("click on the gotIt button");

				String actualBookingId = utility.getText(driver, properties.phpTravellLocator("loc.txt.bookingNumber"));
				validate.verifyContent(invoicePageUrl, actualBookingId, "book id not present");
				log.logger.info("validated that the bookingId matched with the url");

			}
		}

		// switch back to main window
		log.logger.info("switch to main window");
		driver.switchTo().window(mainWindowHandle);
		 ((JavascriptExecutor) driver).executeScript("window.focus();");

	}

	@Test(priority = 2)
	public void verifyRandomBooking() throws Exception {
		log.logger.info("select random invoice and validate");
	
		travels.selectRandomBookingDetails(driver);
		driver.close();
		

	
	
		
	}
	
	
	
}
