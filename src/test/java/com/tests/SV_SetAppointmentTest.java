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

	@Test(priority = 1)
	public void scheduleAppointmentMedicalTest() throws Throwable
	{

		try {
			driver=openBrowser();
			driver.manage().window().maximize();
			logger = extent.createTest(BaseClass.getMethodName() + "method started");

			LoginPage lo = new LoginPage(driver);
			lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
			logger.log(Status.PASS,"Login Success");

			DashBoardPage db = new DashBoardPage(driver);
			NewAppointmentPage nap = new NewAppointmentPage(driver);
			WebElement appid = null;
			String patientFName = datasheet.get("First Name");

		    String lastNameOfPatient =	nap.scheduleAppointment("Medical");
			if(!lastNameOfPatient.equals("NC"))
				appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER, patientFName + " " + lastNameOfPatient);
			else {
				logger.log(Status.FAIL, "Appointment not created");
				Assert.assertTrue(false,"Appointment not created");
			}

			Assert.assertNotNull(appid,"Appointment ID not returned properly");
			db.search(lastNameOfPatient);
			Thread.sleep(4000);
			appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER, patientFName + " " + lastNameOfPatient);

			logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointment created"));
			if(appid !=null)
				logger.log(Status.PASS,"Appointment Created as " + appid.getText());
			else
				logger.log(Status.FAIL,"Appointment could not created");

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
		logger = extent.createTest(BaseClass.getMethodName() + "method started");

		LoginPage lo = new LoginPage(driver);
		lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));

		NewAppointmentPage nap = new NewAppointmentPage(driver);

		logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointments table"));
		nap.editAppointment();
		logger.log(Status.PASS, "Save the updated appointment");
		logger.addScreenCaptureFromPath(takeScreenshotForStep("Save the updated appointment details"));
	}


	@Test
	public void createNonMedicalAppointmentTest()
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
		    String lastNameOfPatient =	nap.scheduleAppointment("Non Medical");
			String patientFName = datasheet.get("First Name");
			WebElement appid = null;

			if(!lastNameOfPatient.equals("NC"))
				appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER, patientFName + " " + lastNameOfPatient);
			else {
				logger.log(Status.FAIL, "Appointment not created");
				Assert.assertTrue(false,"Appointment not created");
			}
			db.search(lastNameOfPatient);
			Assert.assertNotNull(appid,"Appointment ID not returned properly");
			Thread.sleep(4000);
			logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointment created"));
			if(appid !=null)
				logger.log(Status.PASS,"Appointment Created as " + appid.getText());
			else
				logger.log(Status.FAIL,"Appointment could not created");


		}catch (Exception e){

			e.printStackTrace();
			Assert.assertFalse(true, "got exception at ");
		}

	}



	@AfterMethod
	public void captureResult(ITestResult result) throws IOException {

		String methodName = BaseClass.getMethodName();
		logger.log(Status.PASS, "Method completed");
		logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));
	}

	@AfterTest
	public void closingTheBrowser(){

	//	driver.close();
	}

}
