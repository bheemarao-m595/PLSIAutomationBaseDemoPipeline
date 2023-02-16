package com.tests;
import java.io.IOException;
import java.util.Map;

import com.pom.DashBoardPage;
import com.pom.NewAppointmentPage;
import com.utils.DashBoardHeaders;
import com.utils.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.pom.LoginPage;
import com.pom.InterpreterPage;


public class SB_Interpreter_Offer_AcceptTests extends BaseClass {
    WebDriver driver = null;

    @Test(priority = 3)
    public void acceptOfferByInterpreter() throws Throwable
        {

            try {
                logger = extent.createTest(BaseClass.getMethodName() + "method started");
                driver = openBrowser();
                driver.manage().window().maximize();
                LoginPage lo = new LoginPage(driver);
                lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
                logger.log(Status.PASS, "Login as Interpreter");
                InterpreterPage InP = new InterpreterPage(driver);
                InP.interpreterAccept();
                logger.log(Status.PASS, "Interpreter Accepted");
                logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointment clicked"));

            } catch (Exception e) {

                e.printStackTrace();
                Assert.assertFalse(true, "got exception at acceptInterpreter ");
            }
        }




    @Test(priority = 1)
    public void makeAnOfferToInterpreter() throws Throwable{
        {

            try {
                logger = extent.createTest(BaseClass.getMethodName() + "method started");
                driver = openBrowser();
                driver.manage().window().maximize();
                LoginPage lo = new LoginPage(driver);
                lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
                logger.log(Status.PASS, "Logined as Scheduler");
                InterpreterPage InP = new InterpreterPage(driver);
////////////////////////////

                ExcelUtils exl = new ExcelUtils();
                XSSFWorkbook wb = exl.getWorkbook(BaseClass.getFilePathOfTestDataFile());
                Map<String,String> appointmentData =   exl.getMapDataForRowName(wb,"New appointment","scheduleAppointmentMedicalTest");

                NewAppointmentPage nap = new NewAppointmentPage(driver);
                String lastNameOfPatient =  nap.addScheduleAppointment(appointmentData);
                DashBoardPage db = new DashBoardPage(driver);
                String patientFName = appointmentData.get("First Name");
                Thread.sleep(4000);
                db.search(lastNameOfPatient);
                WebElement appid = null;
                if(!lastNameOfPatient.equals("NC")) {
                    db.search(lastNameOfPatient);
                    appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER, patientFName + " " + lastNameOfPatient);
                }
                else {
                    logger.log(Status.FAIL, "Appointment not created");
                    Assert.assertTrue(false,"Appointment not created");
                }
                Assert.assertNotNull(appid,"Appointment ID not returned properly");
                appid.click();

                ////////////////////////////

//                InP.clickUrgent();
//                InP.searchApps("Automation_SV Testerymy");
                logger.log(Status.PASS, "Urgent Tab clicked");
                //InP.clickAppointmentId();
//                DashBoardPage db  = new DashBoardPage(driver);
//                WebElement appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER,"Automation_SV Testerymy");
//                appid.click();
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


    @Test(priority = 2)
    public void rescindOfferedToInterpreter() throws Throwable{
        {

            try {
                logger = extent.createTest(BaseClass.getMethodName() + "method started");
                driver = openBrowser();
                driver.manage().window().maximize();
                LoginPage lo = new LoginPage(driver);
                lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
                logger.log(Status.PASS, "Login as Scheduler");
                InterpreterPage InP = new InterpreterPage(driver);
///////////////////////

                ExcelUtils exl = new ExcelUtils();
                XSSFWorkbook wb = exl.getWorkbook(BaseClass.getFilePathOfTestDataFile());
                Map<String,String> appointmentData =   exl.getMapDataForRowName(wb,"New appointment","scheduleAppointmentMedicalTest");

                NewAppointmentPage nap = new NewAppointmentPage(driver);
                String lastNameOfPatient =  nap.addScheduleAppointment(appointmentData);
                DashBoardPage db = new DashBoardPage(driver);
                String patientFName = appointmentData.get("First Name");
                Thread.sleep(4000);

                WebElement appid = null;
                if(!lastNameOfPatient.equals("NC")) {
                    db.search(lastNameOfPatient);
                    appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER, patientFName + " " + lastNameOfPatient);
                }
                    else {
                    logger.log(Status.FAIL, "Appointment not created");
                    Assert.assertTrue(false,"Appointment not created");
                }
                Assert.assertNotNull(appid,"Appointment ID not returned properly");
                appid.click();

                ////////////////////////////

                logger.log(Status.PASS, "Clicked on Appointment ID");
                InP.makeAnOfferClick();
                logger.addScreenCaptureFromPath(takeScreenshotForStep("Made an Offer"));
                InP.interpreterRescind();
                logger.log(Status.PASS, "Interpreter Rescind");
                logger.addScreenCaptureFromPath(takeScreenshotForStep("Rescined Offer"));
            }
            catch (Exception e){

                e.printStackTrace();
                Assert.assertFalse(true, "got exception at offerInterpreter ");
            }
        }
    }

    @Test(description = "This TC will perform valid login and update the patient preferences")
    public void editPreference() throws Throwable
    {
        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        try {

            driver  = openBrowser();
            driver.manage().window().maximize();
            LoginPage lo = new LoginPage(driver);
            lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
            logger.addScreenCaptureFromPath(takeScreenshotForStep("UpdatePatient"));
            logger.log(Status.PASS, "Login Clicked");
            InterpreterPage InP = new InterpreterPage(driver);
            DashBoardPage dbp = new DashBoardPage(driver);
            InP.clickUrgent();
            logger.log(Status.PASS, "Clicked on Interpreter");
            dbp.search("Testerymy");
            dbp.updatePatientNotes("Testerymy");
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
        try{
            Thread.sleep(3000);
            driver.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @AfterTest
    public void closingTheBrowser(){

        //driver.close();
    }


}

