package com.tests;

import java.io.IOException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.DashBoardPage;
import com.pom.LoginPage;
import com.pom.VG_RequestAppointmentPage;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.base.BaseClass;

@Listeners({com.listeners.ListenerTest.class})
public class VG_Clients_MedicalTests extends BaseClass {

	WebDriver driver = null;
	ExtentTest logger = null;


	XSSFSheet sheet = null;


	@Test(description = "This TC will perform valid login and verified that all appointments tab page is create medical  appointments")
	public void ClientsMedical() throws InterruptedException, IOException {

		driver = openBrowser("chrome");

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
		VG_RequestAppointmentPage vi = new VG_RequestAppointmentPage(driver);
		System.out.println("hi");
		logger.addScreenCaptureFromPath(takeScreenshotForStep("medical"));
		vi.createAppointmentFromClient("Medical");


		logger.addScreenCaptureFromPath(takeScreenshotForStep("new Appointment booked"));

	}
	@Test(description = "This TC will perform valid login and verified that all NonMedical appoinmets will create and serach")
	public void ClientsnonMedical() throws InterruptedException, IOException {

		driver = openBrowser("chrome");

		driver.manage().window().maximize();
		LoginPage lo = new LoginPage(driver);

		System.out.println("starting");
		logger = extent.createTest(BaseClass.getMethodName() + "method started");


		lo.doLogin(datasheet.get("UserName"),datasheet.get("Password"));
		Thread.sleep(5000);


		DashBoardPage db = new DashBoardPage(driver);



		WebElement appId = db.getWebElementOfHeader("STATUS","New");

		appId.click();

		logger.addScreenCaptureFromPath(takeScreenshotForStep("medical"));
		logger.log(Status.PASS, "Login CLicked");
		Thread.sleep(5000);
		VG_RequestAppointmentPage vi = new VG_RequestAppointmentPage(driver);
		System.out.println("hi");
		logger.addScreenCaptureFromPath(takeScreenshotForStep("medical"));
		vi.createAppointmentFromClient("nonmedical");


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
	public void readModule(String moduleName) {

		BaseClass.setModuleName(moduleName);
	}
}