package com.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.pom.InterpreterPage;
import com.pom.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

@Listeners({com.listeners.ListenerTest.class})
public class SB_UpdatePatientnameTests extends BaseClass {
    WebDriver driver = null;
    ExtentTest logger = null;

    @Test(description = "This TC will perform valid login and update the patient preferences")
    public void editPreference() throws Throwable
        {
            logger = extent.createTest("Login as an PLSI scheduler");
            try {

                driver  = openBrowser();
                driver.manage().window().maximize();
                LoginPage lo = new LoginPage(driver);
                lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
                logger.addScreenCaptureFromPath(takeScreenshotForStep("UpdatePatient"));
                logger.log(Status.PASS, "Login CLicked");
                InterpreterPage InP = new InterpreterPage(driver);
                InP.clickUrgent();
                logger.log(Status.PASS, "Clicked on Interpreter");
                InP.updatePatientName();
                logger.log(Status.PASS, "preference updated");

            }
            catch (Exception e) {

                e.printStackTrace();
                Assert.assertFalse(true, "got exception at preference ");
            }
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
