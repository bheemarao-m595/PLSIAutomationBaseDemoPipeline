package com.tests;

import java.io.IOException;

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

			DashBoardPage db = new DashBoardPage(driver);
			NewAppointmentPage nap = new NewAppointmentPage(driver);
			logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointments table"));
			nap.addScheduleAppointment();
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
