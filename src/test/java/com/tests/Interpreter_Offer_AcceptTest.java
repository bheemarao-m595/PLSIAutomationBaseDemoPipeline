package com.tests;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.pom.DashBoardPage;
import com.pom.NewAppointmentPage;
import com.utils.CommonUtils;
import com.utils.DashBoardHeaders;
import com.utils.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.pom.LoginPage;
import com.pom.InterpreterPage;


@Listeners({com.listeners.ListenerTest.class})
public class Interpreter_Offer_AcceptTest extends BaseClass {

    @Test(alwaysRun = true,dependsOnMethods = "makeAnOfferToInterpreter")
    public void acceptOfferByInterpreter() throws Throwable
        {
                driver.get("http://qa.ims.client.sstech.us/login");
                logger = extent.createTest(BaseClass.getMethodName() + "method started");
                LoginPage lo = new LoginPage(driver);
                String intEmail = CommonUtils.readPropertiesFileValues("ExecutionData.properties","makeAnOfferToInterpreter");
                datasheet.put("UserName",intEmail);
                lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
                logger.log(Status.PASS, "Login as Interpreter");
                InterpreterPage InP = new InterpreterPage(driver);
                InP.interpreterAccept();
                logger.log(Status.PASS, "Interpreter Accepted");
                logger.addScreenCaptureFromPath(takeScreenshotForStep("Appointment clicked"));
                lo.click_logOut();

        }


    @Test(priority = 1)
    public void makeAnOfferToInterpreter() throws Throwable{
        {
                driver.get("http://qa.ims.client.sstech.us/login");
                logger = extent.createTest(BaseClass.getMethodName() + "method started");
                LoginPage lo = new LoginPage(driver);
                lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
                logger.log(Status.PASS, "Login as Scheduler");
                InterpreterPage interpreterPage = new InterpreterPage(driver);
                DashBoardPage dbp = new DashBoardPage(driver);

                ExcelUtils exl = new ExcelUtils();
                XSSFWorkbook wb = exl.getWorkbook(BaseClass.getFilePathOfTestDataFile());
                Map<String,String> appointmentData =   exl.getMapDataForRowName(wb,"New appointment","scheduleAppointmentMedicalTest");
                List<WebElement> rows = dbp.getAllAppointmentIdsWithStatus("New");
                if(rows.size() ==0) {
                    logger.log(Status.INFO, "There are no rows with status New");
                    NewAppointmentPage nap = new NewAppointmentPage(driver);
                    appointmentData.put("First Name","Automation_SB");
                    String lastNameOfPatient =  nap.addScheduleAppointment(appointmentData);
                    logger.log(Status.PASS, lastNameOfPatient);
                    DashBoardPage db = new DashBoardPage(driver);
                    String patientFName = appointmentData.get("First Name");
                    Thread.sleep(4000);
                    WebElement appid = null;
                    if(!lastNameOfPatient.equals("NC")) {
                        db.search(lastNameOfPatient);
                        appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER, patientFName + " " + lastNameOfPatient);
                        String appIdText = appid.getText();
                        CommonUtils.writeToPropertiesFile("ExecutionData.properties","makeAnOfferToInterpreter-AppId",appIdText);
                        appid.click();
                    }
                    else {

                        logger.log(Status.FAIL, "Appointment not created");
                        Assert.fail("Appointment not created");
                    }
                    Thread.sleep(2000);
                    logger.log(Status.PASS, "Clicked on Appointment");

                }else{
                    WebElement appid = null;
                    appid =  dbp.getWebElementOfHeaderAndCellValue(DashBoardHeaders.STATUS,"New");
                    Assert.assertNotNull(appid,"Appointment Id not found");
                    dbp.search(appid.getText());
                    JavascriptExecutor js = (JavascriptExecutor)driver;
                    js.executeScript("arguments[0].scrollIntoView(true);",appid);
                    appid.click();
                    Thread.sleep(2000);
                }

                String intEmail =   interpreterPage.makeAnOfferforAny();
                CommonUtils.writeToPropertiesFile("ExecutionData.properties","makeAnOfferToInterpreter",intEmail);

                logger.log(Status.PASS, "Interpreter offered");
                lo.click_logOut();

        }
    }

    @Test(priority = 2)
    public void rescindOfferedToInterpreter() throws Throwable{
        {
                driver.get("http://qa.ims.client.sstech.us/login");
                logger = extent.createTest(BaseClass.getMethodName() + "method started");
                LoginPage lo = new LoginPage(driver);
                lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
                logger.log(Status.PASS, "Login as Scheduler");
                InterpreterPage InP = new InterpreterPage(driver);

                ExcelUtils exl = new ExcelUtils();
                XSSFWorkbook wb = exl.getWorkbook(BaseClass.getFilePathOfTestDataFile());
                Map<String,String> appointmentData =   exl.getMapDataForRowName(wb,"New appointment","scheduleAppointmentMedicalTest");

                NewAppointmentPage nap = new NewAppointmentPage(driver);
                appointmentData.put("First Name","Automation_SB");
                String lastNameOfPatient =  nap.addScheduleAppointment(appointmentData);
                logger.log(Status.PASS, "Appointment Created");
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

                Thread.sleep(2000);
                logger.log(Status.PASS, "Clicked on Appointment ID");
                String email =  InP.makeAnOfferforAny();
                logger.log(Status.INFO," Offer made to " + email);
                logger.addScreenCaptureFromPath(takeScreenshotForStep("Made an Offer"));
                InP.interpreterRescind();
                logger.log(Status.PASS, "Interpreter Rescind");
                logger.addScreenCaptureFromPath(takeScreenshotForStep("Rescind Offer"));
                lo.click_logOut();

        }
    }

    @Test(alwaysRun = true,dependsOnMethods = "makeAnOfferToInterpreter")
    public void editPreference() throws Throwable
    {
        logger = extent.createTest(BaseClass.getMethodName() + "method started");

            driver.get("http://qa.ims.client.sstech.us/login");
            LoginPage lo = new LoginPage(driver);
            lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
            Thread.sleep(4000);
            logger.addScreenCaptureFromPath(takeScreenshotForStep("UpdatePatient"));
            logger.log(Status.PASS, "Login Clicked");
            InterpreterPage InP = new InterpreterPage(driver);
            DashBoardPage dbp = new DashBoardPage(driver);
            dbp.clickUrgent();
            Thread.sleep(5000);
            String appIdText = CommonUtils.readPropertiesFileValues("ExecutionData.properties","makeAnOfferToInterpreter-AppId");
            logger.log(Status.PASS, "Clicked on Interpreter");
            Thread.sleep(3000);
            dbp.search(appIdText);
//            String fullName = dbp.getPatientNameFromAppId(appIdText);
            dbp.updatePatientNotes(appIdText);
            logger.log(Status.PASS, "preference updated");
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

