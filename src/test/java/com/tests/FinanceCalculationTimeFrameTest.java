package com.tests;

import org.testng.ITestResult;
import org.testng.annotations.*;
import com.base.BaseClass;
import com.pom.*;
import com.aventstack.extentreports.Status;

import java.io.IOException;

@Listeners({com.listeners.ListenerTest.class})
public class FinanceCalculationTimeFrameTest extends BaseClass{

    @Test(description = "This TC will perform valid login and verified that appointment can ablle to edit the calculation TimeFrame")
    public void editfinanace() throws InterruptedException, IOException {

        driver.get("http://uat.ims.client.sstech.us/login");
        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        LoginPage lo = new LoginPage(driver);
        InterpreterPage InO = new InterpreterPage(driver);
        FinancePage financePage = new FinancePage(driver);
        lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
        logger.addScreenCaptureFromPath(takeScreenshotForStep("medical"));
        logger.log(Status.PASS, "Login Clicked");
        Thread.sleep(5000);

        InO.clickUrgent();
        logger.log(Status.PASS, "Urgent tab Clicked");
        Thread.sleep(3500);
        financePage.editcalculation();
        lo.click_logOut();

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
