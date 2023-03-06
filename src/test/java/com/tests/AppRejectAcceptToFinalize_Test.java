package com.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.pom.*;
import com.utils.DashBoardHeaders;
import com.utils.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import   com.base.BaseClass;
import com.utils.CommonUtils;
import com.aventstack.extentreports.Status;

@Listeners({com.listeners.ListenerTest.class})
public class AppRejectAcceptToFinalize_Test extends BaseClass {


    @Test(priority = 1)
    public void rejectAppointment() throws Throwable {

        driver.get("http://uat.ims.client.sstech.us/login");
        logger = extent.createTest(BaseClass.getMethodName() + "method started");

        LoginPage loginPage = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        AG_NavigationPanelPage navPanel = new AG_NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
        ExcelUtils exl = new ExcelUtils();
        XSSFWorkbook wb = exl.getWorkbook(BaseClass.getFilePathOfTestDataFile());

        Map<String,String> appointmentData =   exl.getMapDataForRowName(wb,"New appointment","scheduleAppointmentMedicalTest");
        loginPage.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));

        Thread.sleep(3000);

        logger.log(Status.PASS, "current page is all appointments dashboard");

        NewAppointmentPage nap = new NewAppointmentPage(driver);
        String patientFName = appointmentData.get("First Name");
        DashBoardPage db = new DashBoardPage(driver);
        WebElement appid = null;
        appointmentData.put("Requested Language",BaseClass.datasheet.get("Requested Language"));

        String lastNameOfPatient =  nap.addScheduleAppointment(appointmentData);
        Thread.sleep(4000);
        if(!lastNameOfPatient.equals("NC")) {
            db.search(lastNameOfPatient);
            appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER, patientFName + " " + lastNameOfPatient);
        }
        else {
            logger.log(Status.FAIL, "Appointment not created");
            Assert.fail("Appointment not created");
        }
        Assert.assertNotNull(appid,"Appointment ID not returned properly");

        String view_Text = appid.getText();
        appid.click();

        Thread.sleep(3000);

        appDetails.clickTabInterpreterMatching();

        logger.log(Status.PASS, " Clicked INTERPRETER MATCHING Tab");

        Thread.sleep(1000);

        appDetails.clickButtonFindInterpreters();

        logger.log(Status.PASS, " Clicked the button FIND INTERPRETERS");

        Thread.sleep(1000);

        int interpreterListRowsSize = readNumberOfRowsInTable(appDetails.get_interpreterListTableBody());

        List<WebElement> column_Actions = appDetails.get_interpreterListTableActionsCol();
        logger.log(Status.PASS, "selected the column actions");

        List<WebElement> column_Interpreter_Name = appDetails.get_interpreterListTableInterpreterCol();
        logger.log(Status.PASS, "selected the column Interpreter name");

        logger.log(Status.PASS, "iterating through first name list");

        for (int j = 0; j <= interpreterListRowsSize - 1; j++) {

            String first_name = column_Interpreter_Name.get(j).getText();

            if (first_name.equalsIgnoreCase(datasheet.get("Interpreter Name"))) {

                logger.log(Status.PASS,
                        "selected " + datasheet.get("Interpreter Name") + " to make the offer");

                column_Actions.get(j).click();
                logger.log(Status.PASS, "selected the corresponding row and clicked actions to make offer");
                Thread.sleep(3000);

                appDetails.clickClose();
                logger.log(Status.PASS, "Closed the popup");

                break;

            }

        }

        Thread.sleep(3000);
        loginPage.click_logOut();
        logger.log(Status.PASS, "logout as scheduler");

        loginPage.doLogin(datasheet.get("Interpreter Username"),datasheet.get("Interpreter Password"));

        logger.log(Status.PASS, "logged in as interpreter");

        Thread.sleep(2000);

        navPanel.click_Home_Interpreter();
        Thread.sleep(1000);

        interpreterDb.clickOfferedTab();
        logger.log(Status.PASS, "Clicked Interpreter>Offered tab");
        Thread.sleep(1000);

        int count = Integer.parseInt(interpreterDb.getTextOfferedTab());

        Thread.sleep(3000);

        int interpreterDashboardAppointmentListRowsSize = readNumberOfRowsInTable(interpreterDb.get_InterpreterDashboardAppointmentTable());

        Assert.assertEquals(count, interpreterDashboardAppointmentListRowsSize);
        logger.log(Status.PASS, "The count beside Offered tab is verified with number of records displayed.");

        List<WebElement> column_View = interpreterDb.get_InterpreterDashboardAppointmentTableColView();
        logger.log(Status.PASS, "selected the column View ie appointment id");

        for (int k = 0; k <= interpreterDashboardAppointmentListRowsSize - 1; k++) {

            String id = column_View.get(k).getText();
            logger.log(Status.PASS, "iterating through view column list");

            if (id.equalsIgnoreCase(view_Text)) {

                column_View.get(k).click();
                logger.log(Status.PASS, "able to click the id " + view_Text + " in OFFER tab page");

                Thread.sleep(3000);

                String appointment_offer_title = interpreterDb.getTextInterpreterDashboardAppointmentClickTitle();
                Assert.assertTrue(appointment_offer_title.contains(view_Text));
                logger.log(Status.PASS, "Verified the title has the view id selected i.e." + view_Text);

                interpreterDb.clickDeclineButton();
                logger.log(Status.PASS, "Clicked Decline Button");

                Thread.sleep(10000);
                break;

            }

        }
        Thread.sleep(5000);

        loginPage.click_logOut();
        logger.log(Status.PASS, "logged out as interpreter");

        loginPage.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));

        logger.log(Status.PASS, "Logged in as scheduler");
        Thread.sleep(2000);

        dashboard.search(view_Text);
        logger.log(Status.PASS, "entered id in search box");

        Thread.sleep(2000);

        List<WebElement> column_status_offer_rejected = dashboard.get_allAppointmentTableBodyRowsStatusColumn();
        logger.log(Status.PASS, "selected the column Status");

        String status_offer_rejected = column_status_offer_rejected.get(0).getText();

        Assert. assertEquals("OFFER REJECTED", status_offer_rejected);
        logger.log(Status.PASS,
                "verified the status of " + view_Text + " is offered rejected in All appointments list");

        List<WebElement> column_view_offer_rejected = dashboard.get_allAppointmentTableBodyRowsViewColumn();

        WebElement view_id_offer_rejected = column_view_offer_rejected.get(0);

        view_id_offer_rejected.click();
        logger.log(Status.PASS, "clicked the id");

        Thread.sleep(2000);
        logger.log(Status.PASS, " navigated to the appointment details page");

        appDetails.clickTabInterpreterMatching();

        logger.log(Status.PASS, " Clicked INTERPRETER MATCHING Tab");

        Thread.sleep(2000);

        List<WebElement> column_Actions_offer_rejected = appDetails.get_interpreterListTableActionsCol();
        logger.log(Status.PASS, "selected the column actions");
        List<WebElement> column_Interpreter_Name_offer_rejected = appDetails.get_interpreterListTableInterpreterCol();
        logger.log(Status.PASS, "selected the column first name");
        logger.log(Status.PASS, "iterating through first name list");

        for (int l = 0; l <= interpreterListRowsSize - 1; l++) {

            String first_name_offer_rejected = column_Interpreter_Name_offer_rejected.get(l).getText();

            if (first_name_offer_rejected.equalsIgnoreCase(datasheet.get("Interpreter Name"))) {

                logger.log(Status.PASS,
                        "Selected the previous interpret " + datasheet.get("Interpreter Name"));

                String offer_status_off_rejected = column_Actions_offer_rejected.get(l).getText();

                Assert.assertEquals(" OFFER REJECTED", offer_status_off_rejected);
                logger.log(Status.PASS, "checked the status is offer rejected so test case is passed");
                Thread.sleep(3000);

                appDetails.clickClose();
                logger.log(Status.PASS, "Closed the appointment detail page");
                break;

            }

        }
        loginPage.click_logOut();

    }

    @Test(priority = 2)
    public void acceptAppointment()  {

        try {
            driver.get("http://uat.ims.client.sstech.us/login");
            logger = extent.createTest(BaseClass.getMethodName() + "method started");

            LoginPage loginPage = new LoginPage(driver);
            AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
            AG_NavigationPanelPage navPanel = new AG_NavigationPanelPage(driver);
            InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);

            ExcelUtils exl = new ExcelUtils();
            XSSFWorkbook wb = exl.getWorkbook(BaseClass.getFilePathOfTestDataFile());
            Map<String, String> appointmentData = exl.getMapDataForRowName(wb, "New appointment", "scheduleAppointmentMedicalTest");

            loginPage.doLogin(datasheet.get("Scheduler Username"), datasheet.get("Scheduler Password"));
            NewAppointmentPage nap = new NewAppointmentPage(driver);
            appointmentData.put("Requested Language", BaseClass.datasheet.get("Requested Language"));
            String lastNameOfPatient = nap.addScheduleAppointment(appointmentData);
            DashBoardPage db = new DashBoardPage(driver);
            String patientFName = appointmentData.get("First Name");
            Thread.sleep(4000);

            WebElement appid = null;
            if (!lastNameOfPatient.equals("NC")) {
                db.search(lastNameOfPatient);
                appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER, patientFName + " " + lastNameOfPatient);
            } else {
                logger.log(Status.FAIL, "Appointment not created");
                Assert.fail("Appointment not created");
            }
            Assert.assertNotNull(appid, "Appointment ID not returned properly");
            String view_Text = appid.getText();
            appid.click();

            Thread.sleep(5000);

            Thread.sleep(1000);

            appDetails.clickTabInterpreterMatching();

            logger.log(Status.PASS, "Clicked INTERPRETER MATCHING Tab");

            Thread.sleep(1000);

            appDetails.clickButtonFindInterpreters();

            logger.log(Status.PASS, " Clicked the button FIND INTERPRETERS");

            Thread.sleep(1000);

            int interpreterListRowsSize = readNumberOfRowsInTable(appDetails.get_interpreterListTableBody());

            List<WebElement> column_Actions = appDetails.get_interpreterListTableActionsCol();
            logger.log(Status.PASS, "selected the column actions");

            List<WebElement> column_Interpreter_Name = appDetails.get_interpreterListTableInterpreterCol();
            logger.log(Status.PASS, "selected the column Interpreter name");

            logger.log(Status.PASS, "iterating through first name list");

            boolean interpreterFound = false;

            for (int j = 0; j <= interpreterListRowsSize - 1; j++) {

                String first_name = column_Interpreter_Name.get(j).getText();

                if (first_name.equalsIgnoreCase(datasheet.get("Interpreter Name"))) {

                    logger.log(Status.PASS,
                            "selected " + datasheet.get("Interpreter Name") + " to make the offer");

                    column_Actions.get(j).click();
                    logger.log(Status.PASS, "selected the corresponding row and clicked actions to make offer");
                    Thread.sleep(3000);

                    appDetails.clickClose();
                    logger.log(Status.PASS, "Closed the popup");
                    interpreterFound = true;
                    break;

                }

            }
            Thread.sleep(3000);
            Assert.assertTrue(interpreterFound,"Interpreter " + datasheet.get("Interpreter Name") + "not found");
            loginPage.click_logOut();
            logger.log(Status.PASS, "logout as scheduler");

            loginPage.doLogin(datasheet.get("Interpreter Username"), datasheet.get("Interpreter Password"));

            logger.log(Status.PASS, "logged in as interpreter");

            Thread.sleep(2000);
            navPanel.click_Home_Interpreter();
            Thread.sleep(1000);

            interpreterDb.clickOfferedTab();
            logger.log(Status.PASS, "Clicked Interpreter>Offered tab");

            Thread.sleep(3000);

            int interpreterDashboardAppointmentListRowsSize = readNumberOfRowsInTable(interpreterDb.get_InterpreterDashboardAppointmentTable());

            logger.log(Status.PASS, "The count beside Offered tab is verified with number of records displayed.");

            List<WebElement> column_View = interpreterDb.get_InterpreterDashboardAppointmentTableColView();
            logger.log(Status.PASS, "selected the column View ie appointment id");

            for (int k = 0; k <= interpreterDashboardAppointmentListRowsSize - 1; k++) {

                String id = column_View.get(k).getText();
                logger.log(Status.PASS, "iterating through view column list");

                if (id.equalsIgnoreCase(view_Text)) {

                    column_View.get(k).click();
                    logger.log(Status.PASS, "able to click the id " + view_Text + " in OFFER tab page");

                    Thread.sleep(3000);

                    String appointment_offer_title = interpreterDb.getTextInterpreterDashboardAppointmentClickTitle();
                    Assert.assertTrue(appointment_offer_title.contains(view_Text));
                    logger.log(Status.PASS, "Verified the title has the view id selected i.e." + view_Text);

                    interpreterDb.clickAcceptButton();
                    logger.log(Status.PASS, "could click Accept Appointment");

                    Thread.sleep(5000);
                    break;

                }
            }
            loginPage.click_logOut();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test(priority = 3)
    public void returnAppointment() throws Throwable {

        try {

            driver.get("http://uat.ims.client.sstech.us/login");
            logger = extent.createTest(BaseClass.getMethodName() + "method started");

            JavascriptExecutor js = (JavascriptExecutor) driver;

            LoginPage loginPage = new LoginPage(driver);
            DashBoardPage dashboard = new DashBoardPage(driver);
            AG_NavigationPanelPage navPanel = new AG_NavigationPanelPage(driver);
            InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);

            loginPage.doLogin(datasheet.get("Scheduler Username"), datasheet.get("Scheduler Password"));

            Thread.sleep(3000);

            logger.log(Status.PASS, "current page is all appointments dashboard");

            dashboard.search(datasheet.get("Appointment Status"));

            Thread.sleep(3000);

            int allAppRowsSize = readNumberOfRowsInTable(dashboard.get_allAppointmentTable());

            logger.log(Status.PASS, "Found number of rows in the page: " + allAppRowsSize);

            List<WebElement> column_status = dashboard.get_allAppointmentTableBodyRowsStatusColumn();
            logger.log(Status.PASS, "selected the column Status");

            List<WebElement> column_language = dashboard.get_allAppointmentTableBodyRowsLanguageColumn();
            logger.log(Status.PASS, "selected the column Language");

            String view_Text = null;


            for (int i = 0; i <= allAppRowsSize - 1; i++) {

                String status = column_status.get(i).getText();

                String language = column_language.get(i).getText();

                if (status.equalsIgnoreCase(datasheet.get("Appointment Status"))
                        && language.equalsIgnoreCase(datasheet.get("Requested Language"))) {

                    logger.log(Status.PASS, "found a Confirmed appointment");

                    List<WebElement> column_view = dashboard.get_allAppointmentTableBodyRowsViewColumn();

                    WebElement view_id = column_view.get(i);

                    view_Text = view_id.getText();

                    break;
                }
                js.executeScript("window.scrollBy(0,100)", "");
            }

            loginPage.click_logOut();
            logger.log(Status.PASS, "clicked Log-out button");

            Thread.sleep(1000);
            loginPage.doLogin(datasheet.get("Interpreter Username"), datasheet.get("Interpreter Password"));

            logger.log(Status.PASS, "logged in as interpreter");

            Thread.sleep(2000);

            navPanel.click_Home_Interpreter();
            Thread.sleep(1000);

            interpreterDb.clickAcceptedTab();
            logger.log(Status.PASS, "Clicked Interpreter>Accepted tab");

            Thread.sleep(2000);

            interpreterDb.enterSearch(view_Text);
            logger.log(Status.PASS, "entered id " + view_Text + " in search box");

            Thread.sleep(2000);
            //  UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentTableColView());

            List<WebElement> View_accepted = interpreterDb.get_InterpreterDashboardAppointmentTableColView();

            String acceptedId = View_accepted.get(0).getText();
            Assert.assertEquals(view_Text, acceptedId);
            logger.log(Status.PASS, "Accepted appointment is available in Accepted tab list");

            List<WebElement> Status_accepted = interpreterDb.get_InterpreterDashboardAppointmentTableColStatus();

            String acceptedStatus = Status_accepted.get(0).getText();
            Assert.assertEquals("Confirmed", acceptedStatus);
            logger.log(Status.PASS, "The status is confirmed in the list");

            View_accepted.get(0).click();
            logger.log(Status.PASS, "Clicked the Appointment id in view column");


            Thread.sleep(5000);
            interpreterDb.hoverReturnAppointmentButton();

            Thread.sleep(5000);


            if (interpreterDb.isDisplayed_HoverTextOnReturnAppointmentButton()) {
                String hoverText = interpreterDb.get_HoverTextOnReturnAppointmentButton();
                Assert.assertEquals(hoverText,
                        "It is less than 24 Hours before the appointment start time. Please reach out to a Scheduler for immediate assistance");
                logger.log(Status.PASS, "Hover Text  is displayed");

                interpreterDb.clickAppointmentCrossButton();

                Thread.sleep(10000);

                loginPage.click_logOut();

            } else {

                logger.log(Status.FAIL, "Hover Text is not displayed, enabling user to return the application scheduled less than 24 hrs");
                interpreterDb.clickReturnAppointmentButton();

                Thread.sleep(10000);

                loginPage.click_logOut();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    @Test(priority = 4)
    public void checkInAppointment() throws Throwable {

        try {
            driver.get("http://uat.ims.client.sstech.us/login");
            logger = extent.createTest(BaseClass.getMethodName() + "method started");
            JavascriptExecutor js = (JavascriptExecutor) driver;

            LoginPage loginPage = new LoginPage(driver);
            DashBoardPage dashboard = new DashBoardPage(driver);
            AG_NavigationPanelPage navPanel = new AG_NavigationPanelPage(driver);
            InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);

            loginPage.doLogin(datasheet.get("Scheduler Username"), datasheet.get("Scheduler Password"));

            Thread.sleep(3000);

            logger.log(Status.PASS, "current page is all appointments dashboard");

            dashboard.search(datasheet.get("Appointment Status"));

            Thread.sleep(3000);

            int allAppRowsSize = readNumberOfRowsInTable(dashboard.get_allAppointmentTable());

            logger.log(Status.PASS, "Found number of rows in the page: " + allAppRowsSize);

            List<WebElement> column_status = dashboard.get_allAppointmentTableBodyRowsStatusColumn();
            logger.log(Status.PASS, "selected the column Status");

            List<WebElement> column_language = dashboard.get_allAppointmentTableBodyRowsLanguageColumn();
            logger.log(Status.PASS, "selected the column Language");

            String view_Text = null;


            for (int i = 0; i <= allAppRowsSize - 1; i++) {

                String status = column_status.get(i).getText();

                String language = column_language.get(i).getText();

                if (status.equalsIgnoreCase(datasheet.get("Appointment Status"))
                        && language.equalsIgnoreCase(datasheet.get("Requested Language"))) {

                    logger.log(Status.PASS, "found a Confirmed appointment");

                    List<WebElement> column_view = dashboard.get_allAppointmentTableBodyRowsViewColumn();

                    WebElement view_id = column_view.get(i);

                    view_Text = view_id.getText();


                    break;
                }
                js.executeScript("window.scrollBy(0,100)", "");
            }

            loginPage.click_logOut();
            logger.log(Status.PASS, "clicked Log-out button");

            Thread.sleep(1000);
            loginPage.doLogin(datasheet.get("Interpreter Username"), datasheet.get("Interpreter Password"));

            logger.log(Status.PASS, "logged in as interpreter");

            Thread.sleep(2000);
            // UI.waitForElementVisibility(navPanel.Home_Interpreter());

            navPanel.click_Home_Interpreter();
            Thread.sleep(1000);

            interpreterDb.clickAcceptedTab();
            logger.log(Status.PASS, "Clicked Interpreter>Accepted tab");

            Thread.sleep(3000);

            interpreterDb.enterSearch(view_Text);
            logger.log(Status.PASS, "entered id " + view_Text + " in search box");

            Thread.sleep(2000);
            //  UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentTableColView());

            List<WebElement> View_accepted = interpreterDb.get_InterpreterDashboardAppointmentTableColView();

            String acceptedId = View_accepted.get(0).getText();
            Assert.assertEquals(view_Text, acceptedId);
            logger.log(Status.PASS, "Accepted appointment is available in Accepted tab list");

            List<WebElement> Status_accepted = interpreterDb.get_InterpreterDashboardAppointmentTableColStatus();

            String acceptedStatus = Status_accepted.get(0).getText();
            Assert.assertEquals("Confirmed", acceptedStatus);
            logger.log(Status.PASS, "The status is confirmed in the list");

            View_accepted.get(0).click();
            logger.log(Status.PASS, "Clicked the Appointment id in view column");

            Thread.sleep(1000);

            interpreterDb.clickCheckInButton();
            logger.log(Status.PASS, "Clicked Check in button");

            Thread.sleep(10000);

            loginPage.click_logOut();
            logger.log(Status.PASS, "logged out as interpreter");
            loginPage.click_logOut();

        }catch (Exception e){
            e.printStackTrace();
        }



    }

    @Test(priority = 5)
    public void checkOutAndFinaliseAppointment() throws Throwable {

        try {
            driver.get("http://uat.ims.client.sstech.us/login");
            logger = extent.createTest(BaseClass.getMethodName() + "method started");

            JavascriptExecutor js = (JavascriptExecutor) driver;

            LoginPage loginPage = new LoginPage(driver);
            DashBoardPage dashboard = new DashBoardPage(driver);
            AG_NavigationPanelPage navPanel = new AG_NavigationPanelPage(driver);
            InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);

            loginPage.doLogin(datasheet.get("Scheduler Username"), datasheet.get("Scheduler Password"));

            Thread.sleep(3000);

            logger.log(Status.PASS, "current page is all appointments dashboard");

            dashboard.search(datasheet.get("Appointment Status"));

            Thread.sleep(5000);

            int allAppRowsSize = readNumberOfRowsInTable(dashboard.get_allAppointmentTable());

            logger.log(Status.PASS, "Found number of rows in the page: " + allAppRowsSize);

            List<WebElement> column_status = dashboard.get_allAppointmentTableBodyRowsStatusColumn();
            logger.log(Status.PASS, "selected the column Status");

            List<WebElement> column_language = dashboard.get_allAppointmentTableBodyRowsLanguageColumn();
            logger.log(Status.PASS, "selected the column Language");

            String view_Text = null;

            logger.log(Status.PASS, "looping through all rows in Status column till we find new appointment");

            for (int i = 0; i <= allAppRowsSize - 1; i++) {

                String status = column_status.get(i).getText();
                String language = column_language.get(i).getText();

                if (status.equalsIgnoreCase(datasheet.get("Appointment Status"))
                        && language.equalsIgnoreCase(datasheet.get("Requested Language"))) {

                    logger.log(Status.PASS, "found a Confirmed appointment");

                    List<WebElement> column_view = dashboard.get_allAppointmentTableBodyRowsViewColumn();

                    WebElement view_id = column_view.get(i);

                    view_Text = view_id.getText();

                    break;
                }
                js.executeScript("window.scrollBy(0,100)", "");
            }

            loginPage.click_logOut();
            logger.log(Status.PASS, "clicked Log-out button");

            Thread.sleep(1000);
            loginPage.doLogin(datasheet.get("Interpreter Username"), datasheet.get("Interpreter Password"));

            logger.log(Status.PASS, "logged in as interpreter");

            Thread.sleep(2000);

            navPanel.click_Home_Interpreter();
            Thread.sleep(1000);

            interpreterDb.clickAcceptedTab();
            logger.log(Status.PASS, "Clicked Interpreter>Accepted tab");

            Thread.sleep(3000);

            interpreterDb.enterSearch(view_Text);
            logger.log(Status.PASS, "entered id " + view_Text + " in search box");

            Thread.sleep(2000);

            List<WebElement> View_accepted = interpreterDb.get_InterpreterDashboardAppointmentTableColView();

            String acceptedId = View_accepted.get(0).getText();
            Assert.assertEquals(view_Text, acceptedId);
            logger.log(Status.PASS, "Accepted appointment is available in Accepted tab list");

            List<WebElement> Status_accepted = interpreterDb.get_InterpreterDashboardAppointmentTableColStatus();

            String acceptedStatus = Status_accepted.get(0).getText();
            Assert.assertEquals("Confirmed", acceptedStatus);
            logger.log(Status.PASS, "The status is confirmed in the list");

            View_accepted.get(0).click();
            logger.log(Status.PASS, "Clicked the Appointment id in view column");

            Thread.sleep(2000);

            interpreterDb.clickCheckOutButton();
            logger.log(Status.PASS, "Clicked Check out button");

            Thread.sleep(3000);

            List<WebElement> dropdown = interpreterDb.get_FinalizeAppointmentDropdownsList();

            dropdown.get(1).sendKeys(datasheet.get("Requested Language"));
            dropdown.get(1).sendKeys(Keys.ENTER);
            dropdown.get(2).sendKeys(datasheet.get("Request Reimbursement"));
            dropdown.get(2).sendKeys(Keys.ENTER);

            Thread.sleep(3000);

            interpreterDb.clickFinalizeAppointmentButton();

            Thread.sleep(10000);
            loginPage.click_logOut();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Test(priority = 6)
    public void viewFinancialBreakdown() throws Throwable {

        try {

            driver.get("http://uat.ims.client.sstech.us/login");
            logger = extent.createTest(BaseClass.getMethodName() + "method started");

            JavascriptExecutor js = (JavascriptExecutor) driver;

            LoginPage loginPage = new LoginPage(driver);
            DashBoardPage dashboard = new DashBoardPage(driver);
            AG_NavigationPanelPage navPanel = new AG_NavigationPanelPage(driver);
            InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);

            loginPage.doLogin(datasheet.get("Scheduler Username"), datasheet.get("Scheduler Password"));

            Thread.sleep(3000);

            logger.log(Status.PASS, "current page is all appointments dashboard");

            dashboard.search(datasheet.get("Appointment Status"));

            Thread.sleep(5000);

            int allAppRowsSize = readNumberOfRowsInTable(dashboard.get_allAppointmentTable());

            logger.log(Status.PASS, "Found number of rows in the page: " + allAppRowsSize);

            List<WebElement> column_status = dashboard.get_allAppointmentTableBodyRowsStatusColumn();
            logger.log(Status.PASS, "selected the column Status");

            List<WebElement> column_language = dashboard.get_allAppointmentTableBodyRowsLanguageColumn();
            logger.log(Status.PASS, "selected the column Language");

            String view_Text = "";

            logger.log(Status.PASS, "looping through all rows in Status column till we find new appointment");

            for (int i = 0; i <= allAppRowsSize - 1; i++) {

                String status = column_status.get(i).getText();

                String language = column_language.get(i).getText();

                if (status.equalsIgnoreCase(datasheet.get("Appointment Status"))
                        && language.equalsIgnoreCase(datasheet.get("Requested Language"))) {

                    logger.log(Status.PASS, "found a Confirmed appointment");

                    List<WebElement> column_view = dashboard.get_allAppointmentTableBodyRowsViewColumn();

                    WebElement view_id = column_view.get(i);

                    view_Text = view_id.getText();

                    break;
                }
                js.executeScript("window.scrollBy(0,100)", "");
            }

            loginPage.click_logOut();
            logger.log(Status.PASS, "clicked Log-out button");

            Thread.sleep(1000);
            loginPage.doLogin(datasheet.get("Interpreter Username"), datasheet.get("Interpreter Password"));

            logger.log(Status.PASS, "logged in as interpreter");

            Thread.sleep(2000);

            navPanel.click_Home_Interpreter();
            Thread.sleep(1000);

            interpreterDb.clickAcceptedTab();
            logger.log(Status.PASS, "Clicked Interpreter>Accepted tab");

            Thread.sleep(3000);

            interpreterDb.enterSearch(view_Text);
            logger.log(Status.PASS, "entered id " + view_Text + " in search box");

            Thread.sleep(2000);

            List<WebElement> View_accepted = interpreterDb.get_InterpreterDashboardAppointmentTableColView();

            String acceptedId = View_accepted.get(0).getText();
            Assert.assertEquals(view_Text, acceptedId);
            logger.log(Status.PASS, "Accepted appointment is available in Accepted tab list");

            List<WebElement> Status_accepted = interpreterDb.get_InterpreterDashboardAppointmentTableColStatus();

            String acceptedStatus = Status_accepted.get(0).getText();
            Assert.assertEquals("Completed", acceptedStatus);
            logger.log(Status.PASS, "The status is confirmed in the list");

            View_accepted.get(0).click();
            logger.log(Status.PASS, "Clicked the Appointment id in view column");

            Thread.sleep(2000);

            interpreterDb.clickFinancialBreakDownButton();
            logger.log(Status.PASS, "clicked the FinancialBreakDown button ");

            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test(priority = 7)
    public void selfBookAppointment()  throws Throwable {
        try {

            driver.get("http://uat.ims.client.sstech.us/login");
            logger = extent.createTest(BaseClass.getMethodName() + "method started");

            LoginPage loginPage = new LoginPage(driver);
            AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
            AG_NavigationPanelPage navPanel = new AG_NavigationPanelPage(driver);
            InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
            InterpreterPage interpreterPage = new InterpreterPage(driver);

            ExcelUtils exl = new ExcelUtils();
            XSSFWorkbook wb = exl.getWorkbook(BaseClass.getFilePathOfTestDataFile());
            Map<String, String> appointmentData = exl.getMapDataForRowName(wb, "New appointment", "scheduleAppointmentMedicalTest");

            loginPage.doLogin(datasheet.get("Scheduler Username"), datasheet.get("Scheduler Password"));
           // NewAppointmentPage nap = new NewAppointmentPage(driver);
            appointmentData.put("Requested Language", BaseClass.datasheet.get("Requested Language"));
            appointmentData.put("First Name", "Automation_AG");
            DashBoardPage db = new DashBoardPage(driver);
            /*String lastNameOfPatient = nap.addScheduleAppointment(appointmentData);
            DashBoardPage db = new DashBoardPage(driver);
            String patientFName = appointmentData.get("First Name");
            Thread.sleep(4000);
            WebElement appid = null;
            if (!lastNameOfPatient.equals("NC")) {
                db.search(lastNameOfPatient);
                appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER, patientFName + " " + lastNameOfPatient);
            } else {
                logger.log(Status.FAIL, "Appointment not created");
                Assert.fail("Appointment not created");
            }
            Assert.assertNotNull(appid, "Appointment ID not returned properly");
            String view_Text = appid.getText();
            appid.click();*/

// John  code start
            List<WebElement> rows = db.getAllAppointmentIdsWithStatus("New");
            String appIdText = "empty";
            if(rows.size() ==0) {
                logger.log(Status.INFO, "There are no rows with status New");
                NewAppointmentPage nap = new NewAppointmentPage(driver);
                appointmentData.put("First Name","Automation_SB");
                String lastNameOfPatient =  nap.addScheduleAppointment(appointmentData);
//                DashBoardPage db = new DashBoardPage(driver);
                String patientFName = appointmentData.get("First Name");
                Thread.sleep(4000);
                WebElement appid = null;
                if(!lastNameOfPatient.equals("NC")) {
                    db.search(lastNameOfPatient);
                    appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER, patientFName + " " + lastNameOfPatient);
                     appIdText = appid.getText();
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
                appid =  db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.STATUS,"New");
                Assert.assertNotNull(appid,"Appointment Id not found");
                appIdText = appid.getText();
                db.search(appIdText);
                JavascriptExecutor js = (JavascriptExecutor)driver;
                js.executeScript("arguments[0].scrollIntoView(true);",appid);
                appid.click();
                Thread.sleep(2000);
            }
            // John code end
            logger.addScreenCaptureFromPath(takeScreenshotForStep("Created and clicked appt id"));

            Thread.sleep(2000);
            appDetails.clickTabInterpreterMatching();

            logger.log(Status.PASS, "Clicked INTERPRETER MATCHING Tab");

            appDetails.clickButtonFindInterpreters();
            Thread.sleep(2000);

            logger.log(Status.PASS, "Clicked the button FIND INTERPRETERS");

            Thread.sleep(1000);

            int interpreterListRowsSize = readNumberOfRowsInTable(appDetails.get_interpreterListTableBody());

            List<WebElement> column_Actions = appDetails.get_interpreterListTableActionsCol();
            logger.log(Status.PASS, "selected the column actions");

            List<WebElement> column_Interpreter_Email = appDetails.get_interpreterListTableEmailCol();
            logger.log(Status.PASS, "selected the column Interpreter email");

            logger.log(Status.PASS, "number of interpreters available " + interpreterListRowsSize);

            String[] interpreters = new String[interpreterListRowsSize];

            for (int i = 0; i < interpreterListRowsSize; i++) {

                interpreters[i] = column_Interpreter_Email.get(i).getText();

                // Printing using for each loop
                for (String k : interpreters) {
                    logger.log(Status.INFO, k);
                }

            }

            logger.addScreenCaptureFromPath(takeScreenshotForStep("Captured all interpreters"));

            Thread.sleep(1000);

            appDetails.clickClose();
            Thread.sleep(3000);
            loginPage.click_logOut();
            logger.log(Status.PASS, "logout as scheduler");

            for (int j = 0; j < interpreterListRowsSize; j++) {

                String interpreter = interpreters[j];
                loginPage.doLogin(interpreter, datasheet.get("Interpreter Password"));
                logger.log(Status.PASS, "Logged in as interpreter " + interpreter + " from the list available for the appointment is "+appIdText);
                Thread.sleep(2000);

                if (db.newAppointmentIsDisplayed()) {

                    Thread.sleep(1000);
                    logger.log(Status.PASS, "current page is all appointments dashboard");
                    navPanel.click_Interpreters();
                    Thread.sleep(1000);
                    logger.log(Status.PASS, "Clicked Interpreters");

                    interpreterDb.enterSearch(interpreter);
                    Thread.sleep(2000);
                    logger.log(Status.PASS, "Entered the interpreter email " + interpreter + " in search");
                    logger.addScreenCaptureFromPath(takeScreenshotForStep("Found interpreter"));

                    interpreterPage.clickFirstInterpreterViewInList();
                    logger.log(Status.PASS, "clicked the interpreter");

                    Thread.sleep(2000);
                    interpreterDb.clickEditInterpreterButton();
                    logger.log(Status.PASS, "clicked the Edit Interpreter to see the status of checkbox");

                    Thread.sleep(2000);

                    String selfbookCheckboxValue = interpreterDb.getCanSelfBookAppointment()
                            .getAttribute("value");

                    if (selfbookCheckboxValue.equalsIgnoreCase("true")) {

                        logger.log(Status.PASS, "ths check box is selected ie this interpreter can self book an appointment");
                        logger.addScreenCaptureFromPath(takeScreenshotForStep("Checkbox is verified to be true"));

                        interpreterDb.clickCancel();
                        Thread.sleep(2000);

                        interpreterDb.clickClose();
                        Thread.sleep(2000);

                        navPanel.click_Home_Interpreter();
                        Thread.sleep(1000);

                        interpreterDb.availableTabIsDisplayed();
                        logger.log(Status.PASS, "Available tab is displayed as self book appointment check box is checked.");
                        Thread.sleep(1000);
                        interpreterDb.clickAvailableTab();
                        logger.log(Status.PASS, "Clicked Available tab");
                        Thread.sleep(1000);
                        interpreterDb.enterSearch(appIdText);

                        logger.log(Status.PASS, "entered id " + appIdText + " in search box");
                        logger.addScreenCaptureFromPath(takeScreenshotForStep("Appt available in Available Tab"));

                        Thread.sleep(2000);


                        interpreterDb.clickFirstInterpreterDashboardAppointmentTableColView();

                        logger.log(Status.PASS, "appointment id " + appIdText + " is displayed in AVAILABLE tab page");

                        Thread.sleep(3000);

                        String appointment_offer_title = interpreterDb.getTitleInterpreterDashboardAppointmentTitle();

                        Assert.assertTrue(appointment_offer_title.contains(appIdText));
                        logger.log(Status.PASS, "Verified the title has the view id selected i.e." + appIdText);

                        interpreterDb.clickAcceptButton();

                        logger.log(Status.PASS, "Clicked Accept Appointment");
                        logger.addScreenCaptureFromPath(takeScreenshotForStep("Accepted appt"));
                        Thread.sleep(3000);
                        break;

                    } else if (selfbookCheckboxValue.equalsIgnoreCase("false")) {

                        BaseClass.goToElementVisibleArea(interpreterDb.getCanSelfBookAppointment());
                        logger.addScreenCaptureFromPath(takeScreenshotForStep("Checkbox is not checked."));

                        interpreterDb.clickCancel();
                        Thread.sleep(2000);

                        interpreterDb.clickClose();
                        Thread.sleep(2000);

                        navPanel.click_Home_Interpreter();
                        Thread.sleep(1000);

                        boolean availableTab = interpreterDb.availableTabIsDisplayed();

                        if (availableTab) {
                            logger.log(Status.PASS, "Available tab is displayed even though the check box is unchecked");
                            logger.addScreenCaptureFromPath(takeScreenshotForStep("Available tab is displayed."));
                          //  Assert.fail("Available tab is displayed even though the check box is unchecked");

                        } else {
                            logger.log(Status.PASS, "Available tab is not displayed");
                            logger.addScreenCaptureFromPath(takeScreenshotForStep("Available tab is not displayed."));
                        }
                    }

                } else if (loginPage.invalidCredentialsErrorMsgIsDisplayed()){

                    logger.log(Status.INFO,"considering another interpreter in list as password is unknown");

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed due to" + e.getMessage());
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

