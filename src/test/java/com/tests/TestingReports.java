package com.tests;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;
import com.pom.LoginPageFactory;
import com.pom.NewAppointmentPage;
import com.utils.CommonUtils;
import com.utils.SeleniumUIUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

import com.utils.ExcelUtils;

@Listeners({com.listeners.ListenerTest.class})
public class TestingReports  extends BaseClass {

    ExtentTest logger = null;



    @Test(enabled = true)
    public  void testreport() throws IOException {


        System.out.println("starting");
        logger = extent.createTest(getMethodName() + " started");

        logger.log(Status.PASS, "step1");
        logger.log(Status.FAIL, "step2");
        logger.log(Status.PASS, "step3");
        logger.log(Status.FAIL, "step4");
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
         logger = extent.createTest("starting home page");

         driver.get("http://uat.ims.client.sstech.us/login");
         logger.log(Status.PASS, "URL accessed");

         driver.manage().window().maximize();

         LoginPageFactory  lp = new LoginPageFactory(driver);
       //  LoginPageFactory lp =   PageFactory.initElements(driver,LoginPageFactory.class);
         lp.enterUserName("ravi.thota@sstech.us");
         lp.enterPassword("Welcome@1");
         Thread.sleep(5000);
         lp.clickLogin();
         logger.log(Status.PASS, "Login Done");
         logger.addScreenCaptureFromPath(takeScreenshotForStep("hi"));

         Thread.sleep(4000);
         NewAppointmentPage na = new NewAppointmentPage(driver);
         na.clickNewAppointment();
         logger.log(Status.PASS, "New Appointment clicked");
         logger.addScreenCaptureFromPath(takeScreenshotForStep("ji"));


         Thread.sleep(3000);



         boolean b = new BaseClass().isElementByXpath(driver,"//*[@id='react-select-3-input']");
         WebElement client;
         if(b)

         {
             client = new BaseClass().getElementByXpath(driver,"//*[@id='react-select-3-input']");
             clickElement(client);
             logger.log(Status.PASS, "Client CLicked");
         }

         na.fecilityClick();
         logger.log(Status.FAIL, "Facility Clicked");

        logger.log(Status.PASS,"booked");

        }

        @AfterMethod
    public void tearDown()  {


         //   logger.addScreenCaptureFromPath(new CommonUtils().takeScreenshotForStep("taking screen"));

       // driver.close();
        }





}
