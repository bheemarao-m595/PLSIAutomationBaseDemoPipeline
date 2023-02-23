package com.tests;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.pom.LoginPage;
import com.pom.SV_Interpreter_DetailsPage;
import com.pom.VG_Interpreter_ADDInterpreterpage;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

@Listeners({com.listeners.ListenerTest.class})
public class    SV_Interpreter_DetailsTest extends BaseClass
{
    @Test(priority = 1)
    public void create_Interpreter_Availability() throws Throwable {

        driver.get("http://uat.ims.client.sstech.us/login");
        logger = extent.createTest(BaseClass.getMethodName() + "method started");

        LoginPage lo = new LoginPage(driver);
        lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));

        VG_Interpreter_ADDInterpreterpage mInt = new VG_Interpreter_ADDInterpreterpage(driver);
        mInt.clickInterpreters();
        logger.addScreenCaptureFromPath(takeScreenshotForStep("Create Availability Interpreter table"));

        SV_Interpreter_DetailsPage intAvail = new SV_Interpreter_DetailsPage(driver);
        intAvail.openInterpreterDetailsWindow(datasheet.get("InterpreterName"));
        intAvail.create_Interpreter_Availability();
        lo.click_logOut();

    }

    @Test(dependsOnMethods= "create_Interpreter_Availability")
    public void update_Interpreter_Availability() throws Throwable
    {

        driver.get("http://uat.ims.client.sstech.us/login");
        logger = extent.createTest(BaseClass.getMethodName() + "method started");

        LoginPage lo = new LoginPage(driver);
        lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));

        VG_Interpreter_ADDInterpreterpage mInt = new VG_Interpreter_ADDInterpreterpage(driver);
        mInt.clickInterpreters();

        logger.addScreenCaptureFromPath(takeScreenshotForStep("Interpreters table"));

        SV_Interpreter_DetailsPage intAvail = new SV_Interpreter_DetailsPage(driver);
        intAvail.openInterpreterDetailsWindow(datasheet.get("InterpreterName"));
        intAvail.edit_Interpreter_Availablity();
        logger.log(Status.PASS, "Save the updated availability");
        logger.addScreenCaptureFromPath(takeScreenshotForStep("Save the updated availability"));
        lo.click_logOut();

    }

    @Test(dependsOnMethods = "update_Interpreter_Availability")
    public void deleteInt_Avail() throws Throwable {

        driver.get("http://uat.ims.client.sstech.us/login");
        logger = extent.createTest(BaseClass.getMethodName() + "method started");

        LoginPage lo = new LoginPage(driver);
        lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));

        VG_Interpreter_ADDInterpreterpage mInt = new VG_Interpreter_ADDInterpreterpage(driver);
        mInt.clickInterpreters();

        logger.addScreenCaptureFromPath(takeScreenshotForStep("Interpreters table"));
        logger.log(Status.PASS, "Delete Availability");

        SV_Interpreter_DetailsPage intAvail = new SV_Interpreter_DetailsPage(driver);
        intAvail.openInterpreterDetailsWindow(datasheet.get("InterpreterName"));
        intAvail.delete_Interpreter_Availability();
        logger.log(Status.PASS, " After Delete Availability");
        logger.addScreenCaptureFromPath(takeScreenshotForStep("Delete the updated availability"));
        lo.click_logOut();
    }

    @Test
    public void create_Proficiency() throws Throwable
    {

        driver.get("http://uat.ims.client.sstech.us/login");
        logger = extent.createTest( BaseClass.getMethodName() + "method started");

        LoginPage lo = new LoginPage(driver);
        lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));

        VG_Interpreter_ADDInterpreterpage mInt = new VG_Interpreter_ADDInterpreterpage(driver);
        mInt.clickInterpreters();

        logger.addScreenCaptureFromPath(takeScreenshotForStep("Interpreters table"));

        SV_Interpreter_DetailsPage intAvail = new SV_Interpreter_DetailsPage(driver);
        intAvail.openInterpreterDetailsWindow(datasheet.get("InterpreterName"));
        intAvail.add_Proficiency();
        logger.log(Status.PASS, "Add Proficiency");
        logger.addScreenCaptureFromPath(takeScreenshotForStep("Created language proficiency from any interpreter"));
        lo.click_logOut();

    }

    @Test(dependsOnMethods = "create_Proficiency")
    public void remove_LangProficiency() throws Throwable
    {
        driver.get("http://uat.ims.client.sstech.us/login");
        logger = extent.createTest(BaseClass.getMethodName() + "method started");

        LoginPage lo = new LoginPage(driver);
        lo.doLogin(datasheet.get("UserName"), datasheet.get("Password"));

        VG_Interpreter_ADDInterpreterpage mInt = new VG_Interpreter_ADDInterpreterpage(driver);
        mInt.clickInterpreters();

        logger.addScreenCaptureFromPath(takeScreenshotForStep("Interpreters table"));

        SV_Interpreter_DetailsPage intAvail = new SV_Interpreter_DetailsPage(driver);
        intAvail.openInterpreterDetailsWindow(datasheet.get("InterpreterName"));
        intAvail.delete_Proficiency(datasheet.get("Language"));
        logger.log(Status.PASS, "Delete any selected language");
        logger.addScreenCaptureFromPath(takeScreenshotForStep("Delete any selected language proficiency from any interpreter"));
        lo.click_logOut();
    }


    @AfterMethod
    public void captureResult(ITestResult result) throws IOException {

        String methodName = BaseClass.getMethodName();
        logger.log(Status.PASS, "Method completed");
        logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));


    }



}
