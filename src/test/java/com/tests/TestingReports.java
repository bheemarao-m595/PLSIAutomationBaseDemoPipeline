package com.tests;

import com.base.BaseClass;
import com.pom.LoginPage;
import com.pom.NewAppointmentPage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import java.io.IOException;
import java.util.Map;

import com.utils.ExcelUtils;

@Listeners({com.listeners.ListenerTest.class})
public class TestingReports  extends BaseClass {

//    ExtentTest logger = null;


    @Test(enabled = true)
    public  void testreport() throws IOException {


        logger = extent.createTest(getMethodName() + " started");

        logger.log(Status.PASS, "step1");
        logger.log(Status.PASS, "step2");
        logger.log(Status.PASS, "step3");
        logger.log(Status.PASS, "step4");
        logger.log(Status.PASS, "step5");



        ExcelUtils eu = new ExcelUtils();
        String path = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\Project.xlsx";
        XSSFWorkbook workbook = eu.getWorkbook(path);
       Map<String,String> data = eu.getMapDataForRowName(workbook,"Login","Login1");

       for(Map.Entry<String,String> e: data.entrySet()){

           logger.log(Status.INFO,e.getKey()+"-->"+e.getValue());
       }

        eu.getMapDataForALlrows(workbook,"Login");
        extent.flush();



        }


     @Test
        public void testLogin() throws InterruptedException, IOException {

        driver = openBrowser("chrome");

         System.out.println("starting");
         logger = extent.createTest(getMethodName() + " started");

         driver.manage().window().maximize();

         LoginPage lp = new LoginPage(driver);
         lp.enterUserName(datasheet.get("UserName"));
         lp.enterPassword(datasheet.get("Password"));
         Thread.sleep(5000);
         lp.clickLogin();
         logger.log(Status.PASS, "Login Done");
         logger.addScreenCaptureFromPath(takeScreenshotForStep("login"));

         Thread.sleep(4000);
         NewAppointmentPage na = new NewAppointmentPage(driver);
         na.clickNewAppointment();
         logger.log(Status.PASS, "New Appointment clicked");
         logger.addScreenCaptureFromPath(takeScreenshotForStep("new"));

         Assert.assertEquals(1,3);

        }

        @AfterMethod
    public void tearDownMethod() throws IOException {

            String methodName = BaseClass.getMethodName();
            logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));
        }





}
