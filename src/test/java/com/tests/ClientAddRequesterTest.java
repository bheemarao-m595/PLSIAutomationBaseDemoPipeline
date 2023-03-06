package com.tests;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.pom.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.listeners.ListenerTest.class})
public class ClientAddRequesterTest extends BaseClass {

    @Test()
    public void addRequesterforClient() throws Throwable {

        driver.get("http://uat.ims.client.sstech.us/login");
        logger = extent.createTest(BaseClass.getMethodName() + "" + "method started");
        LoginPage lo = new LoginPage(driver);
        InterpreterPage InO = new InterpreterPage(driver);
        lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));
        logger.addScreenCaptureFromPath(takeScreenshotForStep("Login"));
        logger.log(Status.PASS, "Login Clicked");
        Thread.sleep(5000);
        NavigationPanelPage navp = new NavigationPanelPage(driver);
        navp.clickClientsLinkinLeftPanel();
        ClientDashBoardPage clientDB = new ClientDashBoardPage(driver);
        clientDB.searchClient(datasheet.get("client"));
        logger.log(Status.PASS, "Client Search");
        logger.addScreenCaptureFromPath(takeScreenshotForStep("Created and clicked appt id"));

        clientDB.clickClientElementWithName(datasheet.get("client"));
        boolean bool = clientDB.isClientDetailsWindowDisplayedfor(datasheet.get("client"));
        Assert.assertTrue(bool);
        ClientDetailsPage cdp = new ClientDetailsPage(driver);
        cdp.clickRequestersTab();
        logger.log(Status.PASS, "Requester Tab");
        Thread.sleep(1000);
        logger.addScreenCaptureFromPath(takeScreenshotForStep("Created and clicked appt id"));

        String createdrequesterName = cdp.addRequester();
        logger.log(Status.PASS, "Client Added:" + createdrequesterName);
        logger.addScreenCaptureFromPath(takeScreenshotForStep("Created and clicked appt id"));
       boolean isCreated =  BaseClass.isDataPresentinTable("(//table[@class='MuiTable-root css-jiyur0'])[2]",createdrequesterName, true);
       Assert.assertTrue(isCreated,"Requester Not created");
    }

}
