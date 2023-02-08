package com.tests;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.base.BaseClass;
import com.pom.*;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.io.IOException;

@Listeners({com.listeners.ListenerTest.class})
public class SB_FinanceCalculationTimeFrameTests extends BaseClass{
    WebDriver driver = null;
    ExtentTest logger = null;

    @Test(description = "This TC will perform valid login and verified that appointment can ablle to edit the calculation TimeFrame")
    public void editfinanace() throws InterruptedException, IOException {

        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        driver = openBrowser();
        driver.manage().window().maximize();
        LoginPage lo = new LoginPage(driver);
        InterpreterPage InO = new InterpreterPage(driver);
        FinancePage Fp = new FinancePage(driver);
        lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
        logger.addScreenCaptureFromPath(takeScreenshotForStep("medical"));
        logger.log(Status.PASS, "Login CLicked");
        Thread.sleep(5000);

        InO.clickUrgent();
        logger.log(Status.PASS, "Urgent tab CLicked");
        Thread.sleep(3500);
        InO.clickAppointmentId();
        logger.log(Status.PASS, "Selected Appointment");
        Thread.sleep(3500);

        Fp.editfinance();
        logger.log(Status.PASS, "Edit Appointment fields clicked");
        Thread.sleep(3500);

        Fp.calculationtimeframe(datasheet.get("TimeFrame"));
        Fp.calculationsave();

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
