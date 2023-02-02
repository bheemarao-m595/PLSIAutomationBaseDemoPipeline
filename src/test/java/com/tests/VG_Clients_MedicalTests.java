package com.tests;

import java.io.IOException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.LoginPage;
import com.pom.RequestAppointmentPage;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BaseClass;

public class VG_Clients_MedicalTests extends BaseClass {

	WebDriver driver = null;
	ExtentTest logger = null;


	XSSFSheet sheet = null;

	//	@Parameters({ "browser", "URL" })
	/*@BeforeTest
	public void init(String browser, String URL) throws IOException {
		driver = openBrowser(browser);
		UI = new SeleniumUIUtils(driver);
		driver.get(URL);
		driver.manage().window().maximize();
		sheet = readSheet("ClientMedical");
	}
*/
	@BeforeMethod
	public void Setup() {
		System.out.println("Before test");
		// data.readExcelDataToArray(sheet);

	}

	@Test(description = "This TC will perform valid login and verified that all appointments tab page is showing only todays appointments")
	public void ClientsMedical() throws InterruptedException, IOException {

		driver = openBrowser("chrome");

		driver.manage().window().maximize();
		LoginPage lo = new LoginPage(driver);

//		CommonUtils.getRandomStringOfLength(5);

		System.out.println("starting");
		logger = extent.createTest(BaseClass.getMethodName() + "method started");

		lo.enterUserName(datasheet.get("UserName"));
		lo.enterUserName(datasheet.get("Password"));
		Thread.sleep(5000);
		lo.clickLogin();
		logger.addScreenCaptureFromPath(takeScreenshotForStep("medical"));
		logger.log(Status.PASS, "Login CLicked");
		Thread.sleep(5000);
		RequestAppointmentPage vi = new RequestAppointmentPage(driver);
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
}