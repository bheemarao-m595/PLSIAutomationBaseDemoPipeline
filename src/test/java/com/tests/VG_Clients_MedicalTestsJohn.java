package com.tests;

import java.io.IOException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.LoginPage;
import com.pom.RequestAppointmentPage_John;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.base.BaseClass;

public class VG_Clients_MedicalTestsJohn extends BaseClass {

	WebDriver driver = null;
	ExtentTest logger = null;


	XSSFSheet sheet = null;


	@Test(description = "This TC will perform valid login and verified that all appointments tab page is showing only todays appointments")
	public void ClientsMedical() throws InterruptedException, IOException {

		driver = openBrowser();

		driver.manage().window().maximize();
		LoginPage lo = new LoginPage(driver);

		System.out.println("starting");
		logger = extent.createTest(BaseClass.getMethodName() + "method started");

		//lo.enterUserName(datasheet.get("UserName"));
		//lo.enterUserName(datasheet.get("Password"));

		lo.doLogin(datasheet.get("UserName"),datasheet.get("Password"));
		Thread.sleep(5000);

		logger.addScreenCaptureFromPath(takeScreenshotForStep("medical"));
		logger.log(Status.PASS, "Login CLicked");
		Thread.sleep(5000);
		RequestAppointmentPage_John vi = new RequestAppointmentPage_John(driver);
		System.out.println("hi");
		logger.addScreenCaptureFromPath(takeScreenshotForStep("medical"));
		vi.createAppointmentFromClient();

		logger.addScreenCaptureFromPath(takeScreenshotForStep("new Appointment booked"));

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, "Test Case Failed due to " + result.getThrowable());


		}
		String methodName = BaseClass.getMethodName();
		logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));


	}

	@BeforeTest
	@Parameters({"Module"})
	public void readModule(String moduleName){

		BaseClass.setModuleName(moduleName);

	}
}