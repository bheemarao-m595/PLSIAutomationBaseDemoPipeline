package com.tests;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import com.base.BaseClass;
import com.pom.LoginPage;
import com.pom.InterpreterPage;


public class SB_Interpreter_Offer_AcceptTests extends BaseClass {
    WebDriver driver = null;
    ExtentTest logger = null;
    XSSFSheet sheet = null;

    @Test(description = "This TC will perform valid login and verified that appointment can ablle to edit the calculation TimeFrame")
    public void acceptOfferByInterpreter() throws Throwable
        {

            try {
                logger = extent.createTest("Login as an PLSI scheduler");
                driver = openBrowser();
                LoginPage lo = new LoginPage(driver);
                lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
                logger.log(Status.PASS, "Login as Interpreter");
                InterpreterPage InP = new InterpreterPage(driver);
                InP.interpreterAccept();
                logger.log(Status.PASS, "Interpreter Accepted");

            } catch (Exception e) {

                e.printStackTrace();
                Assert.assertFalse(true, "got exception at acceptInterpreter ");
            }
        }



    @Test(description = "This TC will perform valid login and verified that appointment can ablle to edit the calculation TimeFrame")
    public void makeAnOfferToInterpreter() throws Throwable{
        {

            try {
                logger = extent.createTest("Login as an PLSI scheduler");
                driver = openBrowser();
                driver.manage().window().maximize();
                LoginPage lo = new LoginPage(driver);
                lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
                logger.log(Status.PASS, "Logined as Scheduler");
                InterpreterPage InP = new InterpreterPage(driver);

                InP.clickUrgent();
                logger.log(Status.PASS, "Urgent Tab clicked");
                InP.clickAppointmentId();
                logger.log(Status.PASS, "Clicked on Appointment");
                InP.makeAnOfferClick();
                logger.log(Status.PASS, "Inetrpreter offered");
            }
            catch (Exception e){

                e.printStackTrace();
                Assert.assertFalse(true, "got exception at offerInterpreter ");
            }
        }
    }


    @Test(description = "This TC will perform valid login and verified that appointment can ablle to edit the calculation TimeFrame")
    public void rescindOfferedToInterpreter() throws Throwable{
        {

            try {
                logger = extent.createTest("Login as an PLSI scheduler");
                driver = openBrowser();
                driver.manage().window().maximize();
                LoginPage lo = new LoginPage(driver);
                lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
                logger.log(Status.PASS, "Login as Scheduler");
                InterpreterPage InP = new InterpreterPage(driver);

                InP.clickUrgent();
                logger.log(Status.PASS, "Urgent Tab clicked");
                logger.addScreenCaptureFromPath(takeScreenshotForStep("Urgent clicked"));

                InP.clickAppointmentId();
                logger.log(Status.PASS, "Clicked on Appointment");
                logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointment clicked"));

                InP.interpreterRescind();
                logger.log(Status.PASS, "Interpreter Rescind");
            }
            catch (Exception e){

                e.printStackTrace();
                Assert.assertFalse(true, "got exception at offerInterpreter ");
            }
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

