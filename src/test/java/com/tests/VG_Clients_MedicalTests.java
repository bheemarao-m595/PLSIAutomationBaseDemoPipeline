package com.tests;

import java.io.IOException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.LoginPageFactory;
import com.pom.RequestAppointmentPage;
import com.utils.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pom.LoginPage;

public class VG_Clients_MedicalTests extends BaseClass {

	WebDriver driver = null;
	ExtentTest logger = null;

	LoginPage loginPage = new LoginPage();
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
		//driver.get("http://uat.ims.client.sstech.us/login");



		driver.manage().window().maximize();
		LoginPageFactory lo = new LoginPageFactory(driver);

		System.out.println("starting");
		logger = extent.createTest("starting home page");

		driver.get("http://uat.ims.client.sstech.us/login");
		logger.log(Status.PASS, "URL accessed");


		lo.enterUserName("mayuri.chigope@sstech.us");
		lo.enterPassword("Welcome@1");


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
	@AfterTest
	public void closeTest() throws IOException {
		String methodName = BaseClass.getMethodName();
		logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));
		extent.close();
		extent.flush();
	}

}
