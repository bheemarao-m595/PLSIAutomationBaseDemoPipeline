package com.tests;

import java.io.IOException;

import com.aventstack.extentreports.Status;
import com.pom.DashBoardPage;
import com.utils.DashBoardHeaders;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.base.BaseClass;
import com.pom.LoginPage;
import com.pom.NewAppointmentPage;

public class SV_SetAppointmentTest extends BaseClass
{
	@BeforeTest
	@Parameters({"Module"})
	public void readModule(String moduleName) throws IOException {

		BaseClass.setModuleName(moduleName);

	}
	@Test(priority = 1)
	public void addScheduleAppointment() throws Throwable
	{

		try {
			driver=openBrowser();
			driver.manage().window().maximize();
			logger = extent.createTest("Login as an PLSI scheduler");

			LoginPage lo = new LoginPage(driver);
			lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
			logger.log(Status.PASS,"Login Success");

			DashBoardPage db = new DashBoardPage(driver);
			NewAppointmentPage nap = new NewAppointmentPage(driver);
			nap.addScheduleAppointment("Medical");
		WebElement newAppointment =	db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.STATUS,"New");
		System.out.println(newAppointment.getText());
			logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointment created"));
		}catch (Exception e){

			e.printStackTrace();
			Assert.assertFalse(true, "got exception at ");
		}


	}


	@Test
	public void quickEditStatus() throws Throwable
	{
	        driver=openBrowser();
			driver.manage().window().maximize();

		logger = extent.createTest(BaseClass.getMethodName() + "method started");

		LoginPage lo = new LoginPage(driver);
		lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
		logger.log(Status.PASS,"Login Success");

		DashBoardPage dbp = new DashBoardPage(driver);

		logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointments table"));
		dbp.updateQuickStatus();
		logger.log(Status.PASS,"Update Success");

		logger.addScreenCaptureFromPath(takeScreenshotForStep("Save quick edit status"));

	}


	@Test
	public void updateAppointmentDetails() throws Throwable
	{

		driver=openBrowser();
		driver.manage().window().maximize();
		logger = extent.createTest("Login as an PLSI scheduler");
		logger = extent.createTest(BaseClass.getMethodName() + "method started");

		LoginPage lo = new LoginPage(driver);
		lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));

		NewAppointmentPage nap = new NewAppointmentPage(driver);

		logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointments table"));
		nap.editAppointment();

		logger.addScreenCaptureFromPath(takeScreenshotForStep("Save the updated appointment details"));
	}


	@Test
	public void createNonMedicalAppointment()
	{

		try {

			driver=openBrowser();
			driver.manage().window().maximize();

			logger = extent.createTest(BaseClass.getMethodName() + "method started");

			LoginPage lo = new LoginPage(driver);
			lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));

			DashBoardPage db = new DashBoardPage(driver);
			NewAppointmentPage nap = new NewAppointmentPage(driver);
			logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointments table"));
			nap.addScheduleAppointment("Non Medical");
			WebElement newAppointment =	db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.STATUS,"New");
			System.out.println(newAppointment.getText());
			logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointment created"));
		}catch (Exception e){

			e.printStackTrace();
			Assert.assertFalse(true, "got exception at ");
		}

	}



	@AfterMethod
	public void captureResult(ITestResult result) throws IOException {

		String methodName = BaseClass.getMethodName();
		logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));
	}

	@AfterTest
	public void closingTheBrowser(){

		driver.close();
	}


}
