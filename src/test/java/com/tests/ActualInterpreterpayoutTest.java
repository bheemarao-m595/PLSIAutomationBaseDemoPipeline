package com.tests;

import org.testng.ITestResult;
import org.testng.annotations.*;
import com.base.BaseClass;
import com.pom.*;
import com.aventstack.extentreports.Status;

import java.io.IOException;

@Listeners({com.listeners.ListenerTest.class})
public class ActualInterpreterpayoutTest extends BaseClass{


    @Test(description = "This TC will perform valid login and update the Actual Interpreter Payout")
    public void editHybridInterpreterpayout() throws Throwable {

            driver.get("http://uat.ims.client.sstech.us/login");
            logger = extent.createTest(BaseClass.getMethodName() + "" + "method started");
            LoginPage lo = new LoginPage(driver);
            InterpreterPage InO = new InterpreterPage(driver);
            FinancePage Fp = new FinancePage(driver);
            lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
            logger.addScreenCaptureFromPath(takeScreenshotForStep("Login"));
            logger.log(Status.PASS, "Login Clicked");
            InO.clickUrgent();
            logger.log(Status.PASS, "Urgent tab Clicked");
            Thread.sleep(3500);
            InO.searchApps("Completed");
            Thread.sleep(2000);
            InO.clickAppointmentId();
            logger.log(Status.PASS, "Selected Appointment");
            Thread.sleep(3500);
            Fp.editFinancePayout();

            Thread.sleep(3500);
            Fp.editActualPayout(datasheet.get("Check-in"), datasheet.get("Check-out"));
            Thread.sleep(3500);
            Fp.save();
            String Type = Fp.paymenttype();
            logger.log(Status.PASS, "Selected Interpreter Payout for" +Type);
            String methodName = BaseClass.getMethodName();
            logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));
            logger.log(Status.PASS, "Hybrid Interpreter details saved");
            lo.click_logOut();


    }
    @Test
    public void editActualAfterInterpreter() throws Throwable {

            driver.get("http://uat.ims.client.sstech.us/login");
            logger = extent.createTest(BaseClass.getMethodName() + "" + "method started");
            LoginPage lo = new LoginPage(driver);
            InterpreterPage InO = new InterpreterPage(driver);
            FinancePage Fp = new FinancePage(driver);
            lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
            logger.addScreenCaptureFromPath(takeScreenshotForStep("Login"));
            logger.log(Status.PASS, "Login Clicked");
            Thread.sleep(5000);
            InO.clickUrgent();
            logger.log(Status.PASS, "Urgent tab Clicked");
            InO.searchApps("Completed");
            Thread.sleep(3500);
            InO.clickAppointmentId();
            logger.log(Status.PASS, "Selected Appointment");
            Thread.sleep(3500);
            Fp.editFinancePayout();
            logger.log(Status.PASS, "Selected Finance and EditActualpaayout module");
            Thread.sleep(3500);
            Fp.editActualPayout(datasheet.get("Check-in"), datasheet.get("Check-out"));
            Thread.sleep(3500);
            Fp.save();
            String Type = Fp.paymenttype();
            logger.log(Status.PASS, "Selected Interpreter Payout for" +Type);
            String methodName = BaseClass.getMethodName();
            logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));

            logger.log(Status.PASS, "Selected Interpreter Payout for" + Type);
            lo.click_logOut();


    }
    @Test
    public void editStandardInterpreter() throws Throwable {

            driver.get("http://uat.ims.client.sstech.us/login");
            logger = extent.createTest(BaseClass.getMethodName() + "method started");
            LoginPage lo = new LoginPage(driver);
            InterpreterPage InO = new InterpreterPage(driver);
            FinancePage Fp = new FinancePage(driver);
            lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
            logger.addScreenCaptureFromPath(takeScreenshotForStep("Login"));
            logger.log(Status.PASS, "Login Clicked");
            Thread.sleep(5000);
            InO.clickUrgent();
            logger.log(Status.PASS, "Urgent tab Clicked");
            Thread.sleep(3500);
            InO.searchApps("Completed");
            Thread.sleep(3500);
            InO.clickAppointmentId();
            logger.log(Status.PASS, "Selected Appointment");
            Thread.sleep(3500);
            Fp.editFinancePayout();
            logger.log(Status.PASS, "Selected Finance and Edit Actual paayout module");

            Thread.sleep(3500);
            Fp.editActualPayout(datasheet.get("Check-in"), datasheet.get("Check-out"));
            Thread.sleep(3500);
            Fp.save();
            String Type = Fp.paymenttype();
            logger.log(Status.PASS, "Selected Interpreter Payout for" +Type);
            String methodName = BaseClass.getMethodName();
            logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));

            logger.log(Status.PASS, "Selected Interpreter Payout for" + Type);
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
