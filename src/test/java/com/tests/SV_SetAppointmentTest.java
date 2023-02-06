package com.tests;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;
import com.pom.LoginPage;
import com.pom.NewAppointmentPage;
import com.utils.SeleniumUIUtils;

public class SV_SetAppointmentTest extends BaseClass
{
	@BeforeTest
	@Parameters({"Module"})
	public void readModule(String moduleName) throws IOException {
		driver=openBrowser("chrome");
		driver.manage().window().maximize();

		BaseClass.setModuleName(moduleName);


	}
	@Test(priority = 1)
	public void addScheduleAppointment() throws Throwable
	{

		try {
			logger = extent.createTest("Login as an PLSI scheduler");

			LoginPage lo = new LoginPage(driver);
			lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));


			NewAppointmentPage nap = new NewAppointmentPage(driver);
			nap.addScheduleAppointment();
			// String methodName = BaseClass.getMethodName();
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


}
