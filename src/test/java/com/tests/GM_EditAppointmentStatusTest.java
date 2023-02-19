package com.tests;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.pom.DashBoardPage;
import com.pom.GM_FinancialAdminDashboardPage;
import com.pom.LoginPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Listeners({com.listeners.ListenerTest.class})
public class GM_EditAppointmentStatusTest extends BaseClass{


    @Test(description = "This TC will perform valid login, navigated to financial tab in financial archive page and edit Expected Payout fields")
    public void updateFinanceAppointmentStatus() throws InterruptedException, IOException {

        driver.get("http://uat.ims.client.sstech.us/login");
        LoginPage lo = new LoginPage(driver);
        GM_FinancialAdminDashboardPage FA=new GM_FinancialAdminDashboardPage(driver);
        GM_FinancialAdminDashboardPage fadmin = new GM_FinancialAdminDashboardPage(driver);

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
        try {
           fadmin.updateAppointmentStatus("Requested Start Time");
           logger.addScreenCaptureFromPath(takeScreenshotForStep("Updated Appointment Status"));
            logger.log(Status.INFO,"Updating Appointment Status");
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
