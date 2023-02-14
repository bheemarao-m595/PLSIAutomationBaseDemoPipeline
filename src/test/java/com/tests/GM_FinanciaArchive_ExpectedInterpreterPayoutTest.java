package com.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.pom.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GM_FinanciaArchive_ExpectedInterpreterPayoutTest extends BaseClass{

    WebDriver driver = null;
    @BeforeMethod
    public void Setup() throws IOException {
        driver = openBrowser();
        driver.manage().window().maximize();

    }

    @Test(description = "This TC will perform valid login, navigated to financial tab in financial archive page and edit Expected Payout fields")
    public void editingExpectedPayoutFields() throws InterruptedException, IOException {

        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        LoginPage lo = new LoginPage(driver);
        GM_FinancialAdminDashboardPage FA=new GM_FinancialAdminDashboardPage(driver);
        GM_FinancialAdmin_AppointmentDetails_FinancialPage aptDetails = new GM_FinancialAdmin_AppointmentDetails_FinancialPage(driver);
        DashBoardPage db = new DashBoardPage(driver);

        lo.doLogin(datasheet.get("UserName"),datasheet.get("Password"));
        logger.addScreenCaptureFromPath(takeScreenshotForStep("Home Page"));
        logger.log(Status.PASS, "Login CLicked");
        Thread.sleep(2000);

        boolean isSelected =  FA.navigateFinancialArchivePage();
        if(!isSelected)
        {
            WebElement el = FA.getArchivetab();
            System.out.println(el.isDisplayed());
            String timeStamp = new SimpleDateFormat("dd-MM-YYYY_HH-mm-ss").format(new Date());
            logger.addScreenCaptureFromPath(takeScreenshotForStep("taking screenshot"+timeStamp+".png",el));
            Assert.assertTrue(false,"Tab not highlighted");

        }
        try {
            //Thread.sleep(5000);
            //db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.STATUS,"CONFIRMED");
            //clickElement(db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.STATUS, "CONFIRMED"));
            //logger.log(Status.INFO,"Clicked Status");
            //logger.addScreenCaptureFromPath(takeScreenshotForStep("Status to be clicked"));

            Thread.sleep(2000);
            aptDetails.editExpectedPayout();
            logger.log(Status.INFO,"Editing Expected Payout Fields");
            logger.addScreenCaptureFromPath(takeScreenshotForStep("Expected payout"));
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    @Test(description = "This TC will perform valid login, navigated to financial tab in financial archive page and edit Actual Payout fields")
    public void editingActualPayoutFields() throws InterruptedException, IOException {


        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        LoginPage lo = new LoginPage(driver);
        GM_FinancialAdminDashboardPage FA=new GM_FinancialAdminDashboardPage(driver);
        GM_FinancialAdmin_AppointmentDetails_FinancialPage aptDetails = new GM_FinancialAdmin_AppointmentDetails_FinancialPage(driver);

        lo.doLogin(datasheet.get("UserName"),datasheet.get("Password"));
        logger.addScreenCaptureFromPath(takeScreenshotForStep("Home Page"));
        logger.log(Status.PASS, "Login CLicked");
        Thread.sleep(2000);

        boolean isSelected =  FA.navigateFinancialArchivePage();
        if(!isSelected)
        {
            WebElement el = FA.getArchivetab();
            System.out.println(el.isDisplayed());
            String timeStamp = new SimpleDateFormat("dd-MM-YYYY_HH-mm-ss").format(new Date());
            logger.addScreenCaptureFromPath(takeScreenshotForStep("taking screenshot"+timeStamp+".png",el));
            Assert.assertTrue(false,"Tab not highlighted");

        }
        try {

            Thread.sleep(2000);
            aptDetails.editActualPayout();
            logger.log(Status.INFO,"Editing Expected Payout Fields");
            logger.addScreenCaptureFromPath(takeScreenshotForStep("Actual payout"));
        }
        catch (Exception e){
            e.printStackTrace();
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
