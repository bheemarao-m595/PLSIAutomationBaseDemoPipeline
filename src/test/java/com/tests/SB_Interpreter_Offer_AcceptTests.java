package com.tests;
import java.io.IOException;
import java.util.Map;

import com.pom.DashBoardPage;
import com.pom.NewAppointmentPage;
import com.utils.CommonUtils;
import com.utils.DashBoardHeaders;
import com.utils.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.pom.LoginPage;
import com.pom.InterpreterPage;


@Listeners({com.listeners.ListenerTest.class})
public class SB_Interpreter_Offer_AcceptTests extends BaseClass {

    @Test(priority = 3)
    public void acceptOfferByInterpreter() throws Throwable
        {

            try {
                driver.get("http://uat.ims.client.sstech.us/login");
                logger = extent.createTest(BaseClass.getMethodName() + "method started");
                LoginPage lo = new LoginPage(driver);
                lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
                logger.log(Status.PASS, "Login as Interpreter");
                InterpreterPage InP = new InterpreterPage(driver);
                InP.interpreterAccept();
                logger.log(Status.PASS, "Interpreter Accepted");
                logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointment clicked"));
                lo.click_logOut();

            } catch (Exception e) {

                e.printStackTrace();
                Assert.assertFalse(true, "got exception at acceptInterpreter ");
            }
        }




    @Test(priority = 1)
    public void makeAnOfferToInterpreter() throws Throwable{
        {

            try {
                driver.get("http://uat.ims.client.sstech.us/login");
                logger = extent.createTest(BaseClass.getMethodName() + "method started");
                LoginPage lo = new LoginPage(driver);
                lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
                logger.log(Status.PASS, "Logined as Scheduler");
                InterpreterPage InP = new InterpreterPage(driver);

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
                    String appIdText = appid.getText();
                    CommonUtils.writeToPropertiesFile("ExecutionData.properties","makeAnOfferToInterpreter",appIdText);
                    appid.click();
                }
                else {

                  String appIdText  =  CommonUtils.readPropertiesFileValues("ExecutionData.properties","scheduleAppointmentMedicalTest");
                  db.clickUrgent();
                  db.search(appIdText);
                  WebElement appIdAlt = db.getAppIDWebElementwithText(appIdText);
                  appIdAlt.click();
                    /*logger.log(Status.FAIL, "Appointment not created");
                    Assert.assertTrue(false,"Appointment not created");*/
                }
              //  Assert.assertNotNull(appid,"Appointment ID not returned properly");

                logger.log(Status.PASS, "Clicked on Appointment");
                InP.makeAnOfferClick();
                logger.log(Status.PASS, "Interpreter offered");
                lo.click_logOut();
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
                driver.get("http://uat.ims.client.sstech.us/login");
                logger = extent.createTest(BaseClass.getMethodName() + "method started");
                LoginPage lo = new LoginPage(driver);
                lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
                logger.log(Status.PASS, "Login as Scheduler");
                InterpreterPage InP = new InterpreterPage(driver);

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


                logger.log(Status.PASS, "Clicked on Appointment ID");
                InP.makeAnOfferClick();
                logger.addScreenCaptureFromPath(takeScreenshotForStep("Made an Offer"));
                InP.interpreterRescind();
                logger.log(Status.PASS, "Interpreter Rescind");
                logger.addScreenCaptureFromPath(takeScreenshotForStep("Rescind Offer"));
                lo.click_logOut();
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
            driver.get("http://uat.ims.client.sstech.us/login");
            LoginPage lo = new LoginPage(driver);
            lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
            logger.addScreenCaptureFromPath(takeScreenshotForStep("UpdatePatient"));
            logger.log(Status.PASS, "Login Clicked");
            InterpreterPage InP = new InterpreterPage(driver);
            DashBoardPage dbp = new DashBoardPage(driver);
            dbp.clickUrgent();
            logger.log(Status.PASS, "Clicked on Interpreter");
            dbp.search("Testerymy");
            String fullName =  "Automation_SV Testerymy";
            dbp.updatePatientNotes(fullName);
            logger.log(Status.PASS, "preference updated");
            lo.click_logOut();

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


}

