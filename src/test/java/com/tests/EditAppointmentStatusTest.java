package com.tests;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.pom.FinancialAdminDashboardPage;
import com.pom.LoginPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Listeners({com.listeners.ListenerTest.class})
public class EditAppointmentStatusTest extends BaseClass{


    @Test()
    public void updateFinanceAppointmentStatus() throws InterruptedException, IOException {

        driver.get("http://uat.ims.client.sstech.us/login");
        LoginPage lo = new LoginPage(driver);
        FinancialAdminDashboardPage FA=new FinancialAdminDashboardPage(driver);
        FinancialAdminDashboardPage fadmin = new FinancialAdminDashboardPage(driver);

        logger = extent.createTest(BaseClass.getMethodName() + "method started");

        lo.doLogin(datasheet.get("UserName"),datasheet.get("Password"));
        logger.addScreenCaptureFromPath(takeScreenshotForStep("Home Page"));
        logger.log(Status.PASS, "Login Clicked");
        Thread.sleep(2000);

        boolean isSelected =  FA.navigateFinancialArchivePage();
        if(!isSelected)
        {
            WebElement el = FA.getArchivetab();
            String timeStamp = new SimpleDateFormat("dd-MM-YYYY_HH-mm-ss").format(new Date());
            logger.addScreenCaptureFromPath(takeScreenshotForStep("taking screenshot"+timeStamp+".png",el));
            Assert.assertTrue(false,"Tab not highlighted");

        }

           fadmin.updateAppointmentStatus("Requested Start Time");
           logger.addScreenCaptureFromPath(takeScreenshotForStep("Updated Appointment Status"));
           logger.log(Status.PASS,"Updating Appointment Status");
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
