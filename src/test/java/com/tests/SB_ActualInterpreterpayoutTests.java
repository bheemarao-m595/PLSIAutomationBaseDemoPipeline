package com.tests;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.base.BaseClass;
import com.pom.*;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.io.IOException;


public class SB_ActualInterpreterpayoutTests extends BaseClass{
    WebDriver driver = null;
    XSSFSheet sheet = null;
    @BeforeMethod
    public void Setup() {
        System.out.println("Before test");
    }
    @Test(description = "This TC will perform valid login and update the Actual Interpreter Payout")
    public void editHybridInterpreterpayout() throws Throwable {
        try {
            logger = extent.createTest(BaseClass.getMethodName() + "" + "method started");
            driver = openBrowser();
            driver.manage().window().maximize();
            LoginPage lo = new LoginPage(driver);
            InterpreterPage InO = new InterpreterPage(driver);
            FinancePage Fp = new FinancePage(driver);
            lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
            logger.addScreenCaptureFromPath(takeScreenshotForStep("Login"));
            logger.log(Status.PASS, "Login CLicked");
            Thread.sleep(5000);
            InO.clickUrgent();
            logger.log(Status.PASS, "Urgent tab CLicked");
            Thread.sleep(3500);
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

        }
        catch (Exception e){

            e.printStackTrace();
            Assert.assertFalse(false, "got exception at offerInterpreter ");
            String methodName = BaseClass.getMethodName();
            logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));

        }


    }
    @Test
    public void editActualAfterInterpreter() throws Throwable {
        try {
            logger = extent.createTest(BaseClass.getMethodName() + "" + "method started");
            driver = openBrowser();
            driver.manage().window().maximize();
            LoginPage lo = new LoginPage(driver);
            InterpreterPage InO = new InterpreterPage(driver);
            FinancePage Fp = new FinancePage(driver);
            lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
            logger.addScreenCaptureFromPath(takeScreenshotForStep("Login"));
            logger.log(Status.PASS, "Login CLicked");
            Thread.sleep(5000);
          //  InO.clickUrgent(); john
            logger.log(Status.PASS, "Urgent tab CLicked");
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
        }
        catch (Exception e){

            e.printStackTrace();
            Assert.assertFalse(false, "got exception at offerInterpreter ");
            String methodName = BaseClass.getMethodName();
            logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));

        }


    }
    @Test
    public void editStandardInterpreter() throws Throwable {
        try {
            logger = extent.createTest(BaseClass.getMethodName() + "method started");
            driver = openBrowser();
            driver.manage().window().maximize();
            LoginPage lo = new LoginPage(driver);
            InterpreterPage InO = new InterpreterPage(driver);
            FinancePage Fp = new FinancePage(driver);
            lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
            logger.addScreenCaptureFromPath(takeScreenshotForStep("Login"));
            logger.log(Status.PASS, "Login CLicked");
            Thread.sleep(5000);
            InO.clickUrgent();
            logger.log(Status.PASS, "Urgent tab CLicked");
            Thread.sleep(3500);
         //   InO.searchApps("Completed");John
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
        }
        catch (Exception e){

            e.printStackTrace();
            Assert.assertFalse(false, "got exception at offerInterpreter ");
            String methodName = BaseClass.getMethodName();
            logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));

        }

    }
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, "Test Case Failed due to " + result.getThrowable());


        }
        String methodName = BaseClass.getMethodName();
        logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));

        try{
            Thread.sleep(3000);
            driver.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
