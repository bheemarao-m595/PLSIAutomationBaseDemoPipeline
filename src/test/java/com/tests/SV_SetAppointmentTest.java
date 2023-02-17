package com.tests;

import java.io.IOException;
import java.util.Map;

import com.aventstack.extentreports.Status;
import com.pom.DashBoardPage;
import com.utils.DashBoardHeaders;
import com.utils.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
			driver.get("http://uat.ims.client.sstech.us/login");
			logger = extent.createTest(BaseClass.getMethodName() + "method started");

			LoginPage lo = new LoginPage(driver);
			lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
			logger.log(Status.PASS,"Login Success");

			DashBoardPage db = new DashBoardPage(driver);
			NewAppointmentPage nap = new NewAppointmentPage(driver);
			WebElement appid = null;
			String patientFName = datasheet.get("First Name");

		    String lastNameOfPatient =	nap.scheduleAppointment("Medical");
			if(!lastNameOfPatient.equals("NC")) {
				db.search(lastNameOfPatient);
				Thread.sleep(1000);
				appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER, patientFName + " " + lastNameOfPatient);
				logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointment created"));
			}
			else {
				logger.log(Status.FAIL, "Appointment not created");
				Assert.assertTrue(false,"Appointment not created");
			}

			Assert.assertNotNull(appid,"Appointment ID not returned properly");
			logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointment created"));



			if(appid !=null)
				logger.log(Status.PASS,"Appointment Created as " + appid.getText());
			else
				logger.log(Status.FAIL,"Appointment could not created");

			lo.click_logOut();

		}catch (Exception e){

			e.printStackTrace();
			Assert.assertFalse(true, "got exception at ");
		}



	}


	@Test(dependsOnMethods = "scheduleAppointmentMedicalTest")
	public void quickEditStatus() throws Throwable
	{

		driver.get("http://uat.ims.client.sstech.us/login");

		logger = extent.createTest(BaseClass.getMethodName() + "method started");

		LoginPage lo = new LoginPage(driver);
		lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
		logger.log(Status.PASS,"Login Success");

		DashBoardPage dbp = new DashBoardPage(driver);

		logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointments table"));
		dbp.search("Tester_xnm");
		String patientFullName = "Automation_SV Tester_xnm";
		dbp.updateQuickStatus(patientFullName);
		logger.log(Status.PASS,"Update Success");

		logger.addScreenCaptureFromPath(takeScreenshotForStep("Save quick edit status"));
		lo.click_logOut();

	}


	@Test
	public void updateAppointmentDetails() throws Throwable
	{

		 driver.get("http://uat.ims.client.sstech.us/login");
		logger = extent.createTest(BaseClass.getMethodName() + "method started");

		LoginPage lo = new LoginPage(driver);
		lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));

		NewAppointmentPage nap = new NewAppointmentPage(driver);
        DashBoardPage dbp = new DashBoardPage(driver);
		logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointments table"));
		String patientFullName = "Automation_SV Tester_xnm";
		dbp.search("Tester_xnm");
		WebElement appIdLink = dbp.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER,patientFullName);
		appIdLink.click();
		Thread.sleep(2000);
		nap.editAppointment("Tester_vsc");
		logger.log(Status.PASS, "Save the updated appointment");
		logger.addScreenCaptureFromPath(takeScreenshotForStep("Save the updated appointment details"));
		lo.click_logOut();
	}


	@Test
	public void createNonMedicalAppointmentTest()
	{
		try {

		//	driver=openBrowser();
		//	driver.manage().window().maximize();
			driver.get("http://uat.ims.client.sstech.us/login");
			logger = extent.createTest(BaseClass.getMethodName() + "method started");

			LoginPage lo = new LoginPage(driver);
			lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));

			DashBoardPage db = new DashBoardPage(driver);
			NewAppointmentPage nap = new NewAppointmentPage(driver);

			logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointments table"));
		    String lastNameOfPatient =	nap.scheduleAppointment("Non Medical");
			String patientFName = datasheet.get("First Name");
			WebElement appid = null;

			if(!lastNameOfPatient.equals("NC")) {
				db.search(lastNameOfPatient);
				Thread.sleep(1000);
				appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER, patientFName + " " + lastNameOfPatient);
			}
				else {
				logger.log(Status.FAIL, "Appointment not created");
				Assert.assertTrue(false,"Appointment not created");
			}
			Assert.assertNotNull(appid,"Appointment ID not returned properly");
			logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointment created"));
			if(appid !=null)
				logger.log(Status.PASS,"Appointment Created as " + appid.getText());
			else
				logger.log(Status.FAIL,"Appointment could not created");
			lo.click_logOut();

		}catch (Exception e){

			e.printStackTrace();
			Assert.assertFalse(true, "got exception at ");
		}


	}

	@Test
	public void cancelNewAppointment() throws Throwable
	{
		//driver=openBrowser();
		//driver.manage().window().maximize();
		driver.get("http://uat.ims.client.sstech.us/login");
		logger = extent.createTest(BaseClass.getMethodName() + "method started");

		LoginPage lo = new LoginPage(driver);
		lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));

		NewAppointmentPage nap = new NewAppointmentPage(driver);

		logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointments table"));
		nap.cancelAppointment();
		logger.addScreenCaptureFromPath(takeScreenshotForStep("Canceled the appointment"));
		lo.click_logOut();
	}

	@Test()
	public void createRecurringAppointment() throws InterruptedException, IOException {

		//driver=openBrowser();
		//driver.manage().window().maximize();
		driver.get("http://uat.ims.client.sstech.us/login");
		logger = extent.createTest(BaseClass.getMethodName() + "method started");
		LoginPage lo = new LoginPage(driver);
		NewAppointmentPage recurringAppointment = new NewAppointmentPage(driver);

		try{
		lo.doLogin(datasheet.get("UserName"),datasheet.get("Password"));
		logger.addScreenCaptureFromPath(takeScreenshotForStep("Home Page"));
		logger.log(Status.PASS, "Login Clicked");
		Thread.sleep(2000);
		ExcelUtils excelUtils = new ExcelUtils();
		XSSFWorkbook w = excelUtils.getWorkbook(getFilePathOfTestDataFile());
		Map<String, String> recAppData = excelUtils.getMapDataForRowName(w, "New Appointment", "createRecurringAppointment");

		   String lastNameOfPatient =	recurringAppointment.scheduleAppointment("Medical");
			String	patientFName = datasheet.get("First Name");
		DashBoardPage db = new DashBoardPage(driver);
		WebElement appid = null;
		if(!lastNameOfPatient.equals("NC")) {
			db.search(lastNameOfPatient);
			Thread.sleep(1000);
			appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER, patientFName + " " + lastNameOfPatient);
			logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointment created"));
		}
		else {
			logger.log(Status.FAIL, "Appointment not created");
			Assert.assertTrue(false,"Appointment not created");
		}

		Assert.assertNotNull(appid,"Appointment ID not returned properly");
		logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointment created"));

		if(appid !=null)
			logger.log(Status.PASS,"Appointment Created as " + appid.getText());
		else
			logger.log(Status.FAIL,"Appointment could not created");

		lo.click_logOut();

	}catch (Exception e){
        e.printStackTrace();
        }
	}


	@AfterMethod
	public void captureResult(ITestResult result) throws IOException {

		String methodName = BaseClass.getMethodName();
		logger.log(Status.PASS, "Method completed");
		logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));
		try{
			Thread.sleep(3000);
		//	driver.quit();

		}catch (Exception e){
			e.printStackTrace();
		}
	}



}
