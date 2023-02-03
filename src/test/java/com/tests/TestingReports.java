package com.tests;

import com.base.BaseClass;
import com.pom.LoginPage;
import com.pom.NewAppointmentPage;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.Status;

import java.io.IOException;
import java.util.Map;

import com.utils.ExcelUtils;

@Listeners({com.listeners.ListenerTest.class})
public class TestingReports  extends BaseClass {

//    ExtentTest logger = null;

//    static Logger log = Logger.getLogger(BaseClass.class.getName());

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


        }


     @Test
        public void testLogin() throws InterruptedException, IOException {

        driver = openBrowser("chrome");

         System.out.println("starting");
         logger = extent.createTest(  " started");

         driver.manage().window().maximize();

         LoginPage lp = new LoginPage(driver);

         lp.doLogin(datasheet.get("UserName"),datasheet.get("Password"));
         Thread.sleep(5000);
         logger.log(Status.PASS, "Login Done");
         logger.addScreenCaptureFromPath(takeScreenshotForStep("login"));

         Thread.sleep(4000);
         NewAppointmentPage na = new NewAppointmentPage(driver);
         na.clickNewAppointment();
         na.addScheduleAppointment();

         logger.log(Status.PASS, "New Appointment clicked");
         logger.addScreenCaptureFromPath(takeScreenshotForStep("new"));

 extent.flush();
        }

        @AfterMethod
    public void tearDownMethod() throws IOException {

            String methodName = BaseClass.getMethodName();
            logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));
        }

    @BeforeTest
    @Parameters({"Module"})
    public void readModule(String moduleName){

        BaseClass.setModuleName(moduleName);


    }

}