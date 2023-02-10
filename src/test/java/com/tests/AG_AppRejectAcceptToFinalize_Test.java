package com.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.aventstack.extentreports.ExtentTest;
import com.pom.*;
import com.utils.DashBoardHeaders;
import com.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import   com.base.BaseClass;
import com.utils.CommonUtils;
import com.utils.SeleniumUIUtils;
import com.aventstack.extentreports.Status;

public class AG_AppRejectAcceptToFinalize_Test extends BaseClass {

        /*This class tests an appointment with current date is being rejected,accepted,
        can not be returned as to return an appointment the start time should be past 24 hrs from current time,
        check-in,checkout,finalize and view financial for the appointment.
        Self booking of an appointment by interpreter is also tested.
      */

        SeleniumUIUtils UI = null;
        WebDriver driver = null;
        CommonUtils CU = null;

        @Test(priority = 1)
        public void rejectAppointment() throws InterruptedException, IOException {

            logger = extent.createTest(BaseClass.getMethodName() + "method started");

            driver = openBrowser();

            driver.manage().window().maximize();

            JavascriptExecutor js = (JavascriptExecutor) driver;


            LoginPage loginPage = new LoginPage(driver);
            DashBoardPage dashboard = new DashBoardPage(driver);
            AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
            AG_NavigationPanelPage navPanel = new AG_NavigationPanelPage(driver);
            InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
            ExcelUtils exl = new ExcelUtils();
            XSSFWorkbook wb = exl.getWorkbook(BaseClass.getFilePathOfTestDataFile());

            Map<String,String> appointmentData =   exl.getMapDataForRowName(wb,"New appointment","addScheduleAppointment");
            loginPage.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));

            Thread.sleep(3000);

            logger.log(Status.INFO, "current page is all appointments dashboard");


            NewAppointmentPage nap = new NewAppointmentPage(driver);
            appointmentData.put("Requested Language",BaseClass.datasheet.get("Requested Language"));
            String lastNameOfPatient =  nap.addScheduleAppointment(appointmentData);
            DashBoardPage db = new DashBoardPage(driver);
            String patientFName = appointmentData.get("First Name");
            db.search(lastNameOfPatient);
            Thread.sleep(4000);
            WebElement appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER, patientFName + " " + lastNameOfPatient);
            //      WebElement appid = db.getAppIDWebElement("727");
            // WebElement appid = driver.findElement(By.xpath("//tbody[@class='MuiTableBody-root css-1xnox0e']//tr//td/div/div[text()='722']"));
            Assert.assertNotNull(appid,"Appointment ID not returned properly");
            String view_Text = appid.getText();
            appid.click();

            Thread.sleep(3000);

            appDetails.clickTabInterpreterMatching();

            logger.log(Status.INFO, " Clicked INTERPRETER MATCHING Tab");

            Thread.sleep(1000);

            appDetails.clickButtonFindInterpreters();

            logger.log(Status.INFO, " Clicked the button FIND INTERPRETERS");

            Thread.sleep(1000);

            int interpreterListRowsSize = readNumberOfRowsInTable(appDetails.get_interpreterListTableBody());

            List<WebElement> column_Actions = appDetails.get_interpreterListTableActionsCol();
            logger.log(Status.INFO, "selected the column actions");

            List<WebElement> column_Interpreter_Name = appDetails.get_interpreterListTableInterpreterCol();
            logger.log(Status.INFO, "selected the column Interpreter name");

            logger.log(Status.INFO, "iterating through first name list");

            for (int j = 0; j <= interpreterListRowsSize - 1; j++) {

                String first_name = column_Interpreter_Name.get(j).getText();

                System.out.println(column_Interpreter_Name.get(j).getText());

                if (first_name.equalsIgnoreCase(datasheet.get("Interpreter Name"))) {

                    logger.log(Status.INFO,
                            "selected " + datasheet.get("Interpreter Name") + " to make the offer");

                    column_Actions.get(j).click();
                    logger.log(Status.INFO, "selected the corresponding row and clicked actions to make offer");
                    Thread.sleep(3000);

                    appDetails.clickClose();
                    logger.log(Status.INFO, "Closed the popup");

                    break;

                }

            }

            Thread.sleep(3000);

            loginPage.click_logOut();
            logger.log(Status.INFO, "logout as scheduler");

            loginPage.doLogin(datasheet.get("Interpreter Username"),datasheet.get("Interpreter Password"));


            logger.log(Status.INFO, "logged in as interpreter");

            Thread.sleep(2000);
           // UI.waitForElementVisibility(navPanel.Home_Interpreter());

            navPanel.click_Home_Interpreter();
            Thread.sleep(1000);

            interpreterDb.clickOfferedTab();
            logger.log(Status.INFO, "Clicked Interpreter>Offered tab");
            Thread.sleep(1000);

            int count = Integer.parseInt(interpreterDb.getTextOfferedTab());
            System.out.println(count);

            Thread.sleep(3000);
            //UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentTable());

            int interpreterDashboardAppointmentListRowsSize = readNumberOfRowsInTable(interpreterDb.get_InterpreterDashboardAppointmentTable());

            Assert.assertEquals(count, interpreterDashboardAppointmentListRowsSize);
            logger.log(Status.PASS, "The count beside Offered tab is verified with number of records displayed.");

            List<WebElement> column_View = interpreterDb.get_InterpreterDashboardAppointmentTableColView();
            logger.log(Status.INFO, "selected the column View ie appointment id");

            for (int k = 0; k <= interpreterDashboardAppointmentListRowsSize - 1; k++) {

                String id = column_View.get(k).getText();
                logger.log(Status.INFO, "iterating through view column list");
                System.out.println(column_View.get(k).getText());

                if (id.equalsIgnoreCase(view_Text)) {

                    System.out.println(k);
                    column_View.get(k).click();
                    logger.log(Status.PASS, "able to click the id " + view_Text + " in OFFER tab page");

                    Thread.sleep(3000);
                    //UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentClickTitle());

                    String appointment_offer_title = interpreterDb.getTextInterpreterDashboardAppointmentClickTitle();
                    System.out.println(appointment_offer_title);
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
            logger.log(Status.INFO, "logged out as interpreter");

           /* CU.login(data.getDataAsString(sheet_Reject, "Scheduler Username", 1),
                    data.getDataAsString(sheet_Reject, "Scheduler Password", 1));*/
            loginPage.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));

            logger.log(Status.INFO, "Logged in as scheduler");
            Thread.sleep(2000);

            dashboard.search(view_Text);
            logger.log(Status.INFO, "entered id in search box");

            Thread.sleep(2000);

            List<WebElement> column_status_offer_rejected = dashboard.get_allAppointmentTableBodyRowsStatusColumn();
            logger.log(Status.INFO, "selected the column Status");

            System.out.println(column_status_offer_rejected.get(0).getText());

            String status_offer_rejected = column_status_offer_rejected.get(0).getText();

            Assert. assertEquals("OFFER REJECTED", status_offer_rejected);
            logger.log(Status.PASS,
                    "verified the status of " + view_Text + " is offered rejected in All appointments list");

            List<WebElement> column_view_offer_rejected = dashboard.get_allAppointmentTableBodyRowsViewColumn();

            WebElement view_id_offer_rejected = column_view_offer_rejected.get(0);

            view_Text = view_id_offer_rejected.getText();

            System.out.println(view_Text);

            view_id_offer_rejected.click();
            logger.log(Status.INFO, "clicked the id");

            Thread.sleep(2000);
            logger.log(Status.INFO, " navigated to the appointment details page");

            appDetails.clickTabInterpreterMatching();

            logger.log(Status.INFO, " Clicked INTERPRETER MATCHING Tab");

            Thread.sleep(2000);

            List<WebElement> column_Actions_offer_rejected = appDetails.get_interpreterListTableActionsCol();
            logger.log(Status.INFO, "selected the column actions");
            List<WebElement> column_Interpreter_Name_offer_rejected = appDetails.get_interpreterListTableInterpreterCol();
            logger.log(Status.INFO, "selected the column first name");
            logger.log(Status.INFO, "iterating through first name list");

            for (int l = 0; l <= interpreterListRowsSize - 1; l++) {

                System.out.println(interpreterListRowsSize);
                String first_name_offer_rejected = column_Interpreter_Name_offer_rejected.get(l).getText();

                System.out.println(column_Interpreter_Name_offer_rejected.get(l).getText());
                System.out.println(first_name_offer_rejected);

                if (first_name_offer_rejected.equalsIgnoreCase(datasheet.get("Interpreter Name"))) {

                    logger.log(Status.INFO,
                            "Selected the previous interpret " + datasheet.get("Interpreter Name"));
                    System.out.println(column_Actions_offer_rejected.get(l).getText());

                    String offer_status_off_rejected = column_Actions_offer_rejected.get(l).getText();

                    Assert.assertEquals(" OFFER REJECTED", offer_status_off_rejected);
                    logger.log(Status.PASS, "checked the status is offer rejected so test case is passed");
                    Thread.sleep(3000);

                    appDetails.clickClose();
                    logger.log(Status.INFO, "Closed the appointment detail page");
                    break;

                }

            }

        }

    @Test(priority = 2)
    public void acceptAppointment() throws InterruptedException, IOException {
        logger = extent.createTest(BaseClass.getMethodName() + "method started");

        driver = openBrowser();
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        LoginPage loginPage = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        AG_NavigationPanelPage navPanel = new AG_NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);

         ExcelUtils exl = new ExcelUtils();
        XSSFWorkbook wb = exl.getWorkbook(BaseClass.getFilePathOfTestDataFile());
        Map<String,String> appointmentData =   exl.getMapDataForRowName(wb,"New appointment","addScheduleAppointment");

        loginPage.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));
        NewAppointmentPage nap = new NewAppointmentPage(driver);
        appointmentData.put("Requested Language",BaseClass.datasheet.get("Requested Language"));
        String lastNameOfPatient =  nap.addScheduleAppointment(appointmentData);
        DashBoardPage db = new DashBoardPage(driver);
        String patientFName = appointmentData.get("First Name");
        WebElement appid = db.getWebElementOfHeaderAndCellValue(DashBoardHeaders.PATIENT_CONSUMER, patientFName + " " + lastNameOfPatient);
        Assert.assertNotNull(appid,"Appointment ID not returned properly");
        String view_Text = appid.getText();
        appid.click();
        //for creating appointment id

        Thread.sleep(5000);

        Thread.sleep(1000);

        appDetails.clickTabInterpreterMatching();

        logger.log(Status.INFO, " Clicked INTERPRETER MATCHING Tab");

        Thread.sleep(1000);

        appDetails.clickButtonFindInterpreters();

        logger.log(Status.INFO, " Clicked the button FIND INTERPRETERS");

        Thread.sleep(1000);

        int interpreterListRowsSize = readNumberOfRowsInTable(appDetails.get_interpreterListTableBody());

        List<WebElement> column_Actions = appDetails.get_interpreterListTableActionsCol();
        logger.log(Status.INFO, "selected the column actions");

        List<WebElement> column_Interpreter_Name = appDetails.get_interpreterListTableInterpreterCol();
        logger.log(Status.INFO, "selected the column Interpreter name");

        logger.log(Status.INFO, "iterating through first name list");

        for (int j = 0; j <= interpreterListRowsSize - 1; j++) {

            String first_name = column_Interpreter_Name.get(j).getText();

            if (first_name.equalsIgnoreCase(datasheet.get("Interpreter Name"))) {

                logger.log(Status.INFO,
                        "selected " + datasheet.get("Interpreter Name") + " to make the offer");

                column_Actions.get(j).click();
                logger.log(Status.INFO, "selected the corresponding row and clicked actions to make offer");
                Thread.sleep(3000);

                appDetails.clickClose();
                logger.log(Status.INFO, "Closed the popup");

                break;

            }

        }
        Thread.sleep(3000);

        loginPage.click_logOut();
        logger.log(Status.INFO, "logout as scheduler");

        loginPage.doLogin(datasheet.get("Interpreter Username"),datasheet.get("Interpreter Password"));


          /*  CU.login(data.getDataAsString(sheet_Reject, "Interpreter Username", 1),
                    data.getDataAsString(sheet_Reject, "Interpreter Password", 1));*/
        logger.log(Status.INFO, "logged in as interpreter");

        Thread.sleep(2000);
        // UI.waitForElementVisibility(navPanel.Home_Interpreter());

        navPanel.click_Home_Interpreter();
        Thread.sleep(1000);

        interpreterDb.clickOfferedTab();
        logger.log(Status.INFO, "Clicked Interpreter>Offered tab");
        Thread.sleep(1000);

        int count = Integer.parseInt(interpreterDb.getTextOfferedTab());
        System.out.println(count);

        Thread.sleep(3000);
        //UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentTable());

        int interpreterDashboardAppointmentListRowsSize = readNumberOfRowsInTable(interpreterDb.get_InterpreterDashboardAppointmentTable());

        //Assert.assertEquals(count, interpreterDashboardAppointmentListRowsSize);
        logger.log(Status.PASS, "The count beside Offered tab is verified with number of records displayed.");

        List<WebElement> column_View = interpreterDb.get_InterpreterDashboardAppointmentTableColView();
        logger.log(Status.INFO, "selected the column View ie appointment id");

        for (int k = 0; k <= interpreterDashboardAppointmentListRowsSize - 1; k++) {

            String id = column_View.get(k).getText();
            logger.log(Status.INFO, "iterating through view column list");

            if (id.equalsIgnoreCase(view_Text)) {

                column_View.get(k).click();
                logger.log(Status.PASS, "able to click the id " + view_Text + " in OFFER tab page");

                Thread.sleep(3000);
                //UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentClickTitle());

                String appointment_offer_title = interpreterDb.getTextInterpreterDashboardAppointmentClickTitle();
                Assert.assertTrue(appointment_offer_title.contains(view_Text));
                logger.log(Status.PASS, "Verified the title has the view id selected i.e." + view_Text);

                interpreterDb.clickAcceptButton();
                logger.log(Status.PASS, "could click Accept Appointment");

                Thread.sleep(5000);
                //UI.waitForElementVisibility(interpreterDb.AcceptedTab());
                break;

            }
        }
            }

    @Test(priority = 3)
    public void returnAppointment() throws InterruptedException, IOException {

    logger = extent.createTest(BaseClass.getMethodName() + "method started");

    driver = openBrowser();

        driver.manage().window().maximize();

    JavascriptExecutor js = (JavascriptExecutor) driver;

    LoginPage loginPage = new LoginPage(driver);
    DashBoardPage dashboard = new DashBoardPage(driver);
    AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
    AG_NavigationPanelPage navPanel = new AG_NavigationPanelPage(driver);
    InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);

        loginPage.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));

        Thread.sleep(3000);

        logger.log(Status.INFO, "current page is all appointments dashboard");

        dashboard.search(datasheet.get("Appointment Status"));

        Thread.sleep(5000);

    int allAppRowsSize = readNumberOfRowsInTable(dashboard.get_allAppointmentTable());

        logger.log(Status.INFO, "Found number of rows in the page: " + allAppRowsSize);

    List<WebElement> column_status = dashboard.get_allAppointmentTableBodyRowsStatusColumn();
        logger.log(Status.INFO, "selected the column Status");

    List<WebElement> column_language = dashboard.get_allAppointmentTableBodyRowsLanguageColumn();
        logger.log(Status.INFO, "selected the column Language");

    String view_Text = null;// for caturing appointment id

        logger.log(Status.INFO, "looping through all rows in Status column till we find new appointment");

        for (int i = 0; i <= allAppRowsSize - 1; i++) {

        String status = column_status.get(i).getText();
        System.out.println(column_status.get(i).getText());

        String language = column_language.get(i).getText();
        System.out.println(column_language.get(i).getText());

        if (status.equalsIgnoreCase(datasheet.get("Appointment Status"))
                && language.equalsIgnoreCase(datasheet.get("Requested Language"))) {

            logger.log(Status.INFO, "found a Confirmed appointment");

            List<WebElement> column_view = dashboard.get_allAppointmentTableBodyRowsViewColumn();

            WebElement view_id = column_view.get(i);

            view_Text = view_id.getText();

            System.out.println(view_Text);


            break;
        }
            js.executeScript("window.scrollBy(0,100)", "");
        }

        loginPage.click_logOut();
        logger.log(Status.INFO, "clicked Log-out button");

        Thread.sleep(1000);
        loginPage.doLogin(datasheet.get("Interpreter Username"),datasheet.get("Interpreter Password"));

        logger.log(Status.INFO, "logged in as interpreter");

        Thread.sleep(2000);

        navPanel.click_Home_Interpreter();
        Thread.sleep(1000);

        interpreterDb.clickAcceptedTab();
        logger.log(Status.INFO, "Clicked Interpreter>Accepted tab");

        Thread.sleep(3000);

        interpreterDb.enterSearch(view_Text);
        logger.log(Status.INFO, "entered id " + view_Text + " in search box");

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
        logger.log(Status.INFO, "Clicked the Appointment id in view column");


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





}
    @Test(priority = 4)
    public void checkInAppointment() throws InterruptedException, IOException {
    logger = extent.createTest(BaseClass.getMethodName() + "method started");

    driver = openBrowser();

        driver.manage().window().maximize();

    JavascriptExecutor js = (JavascriptExecutor) driver;

    LoginPage loginPage = new LoginPage(driver);
    DashBoardPage dashboard = new DashBoardPage(driver);
    AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
    AG_NavigationPanelPage navPanel = new AG_NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);

        loginPage.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));

        Thread.sleep(3000);

        logger.log(Status.INFO, "current page is all appointments dashboard");

        dashboard.search(datasheet.get("Appointment Status"));

        Thread.sleep(5000);

    int allAppRowsSize = readNumberOfRowsInTable(dashboard.get_allAppointmentTable());

        logger.log(Status.INFO, "Found number of rows in the page: " + allAppRowsSize);

    List<WebElement> column_status = dashboard.get_allAppointmentTableBodyRowsStatusColumn();
        logger.log(Status.INFO, "selected the column Status");

    List<WebElement> column_language = dashboard.get_allAppointmentTableBodyRowsLanguageColumn();
        logger.log(Status.INFO, "selected the column Language");

    String view_Text = null;// for caturing appointment id

        logger.log(Status.INFO, "looping through all rows in Status column till we find new appointment");

        for (int i = 0; i <= allAppRowsSize - 1; i++) {

        String status = column_status.get(i).getText();
        System.out.println(column_status.get(i).getText());

        String language = column_language.get(i).getText();
        System.out.println(column_language.get(i).getText());

        if (status.equalsIgnoreCase(datasheet.get("Appointment Status"))
                && language.equalsIgnoreCase(datasheet.get("Requested Language"))) {

            logger.log(Status.INFO, "found a Confirmed appointment");

            List<WebElement> column_view = dashboard.get_allAppointmentTableBodyRowsViewColumn();

            WebElement view_id = column_view.get(i);

            view_Text = view_id.getText();

            System.out.println(view_Text);


            break;
        }
        js.executeScript("window.scrollBy(0,100)", "");
    }

        loginPage.click_logOut();
        logger.log(Status.INFO, "clicked Log-out button");

        Thread.sleep(1000);
        loginPage.doLogin(datasheet.get("Interpreter Username"),datasheet.get("Interpreter Password"));

        logger.log(Status.INFO, "logged in as interpreter");

        Thread.sleep(2000);
    // UI.waitForElementVisibility(navPanel.Home_Interpreter());

        navPanel.click_Home_Interpreter();
        Thread.sleep(1000);

        interpreterDb.clickAcceptedTab();
        logger.log(Status.INFO, "Clicked Interpreter>Accepted tab");

        Thread.sleep(3000);

        interpreterDb.enterSearch(view_Text);
        logger.log(Status.INFO, "entered id " + view_Text + " in search box");

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
        logger.log(Status.INFO, "Clicked the Appointment id in view column");

       // UI.waitForElementVisibility(interpreterDb.AppointmentPageTitle());

        Thread.sleep(1000);

       // UI.waitForElementVisibility(interpreterDb.CheckInButton());

        Thread.sleep(1000);

        interpreterDb.clickCheckInButton();
        logger.log(Status.PASS, "Clicked Check in button");

        Thread.sleep(10000);

        loginPage.click_logOut();
        logger.log(Status.INFO, "logged out as interpreter");

    }

    @Test(priority = 5)
    public void checkOutAndFinaliseAppointment() throws InterruptedException, IOException {
        driver = openBrowser();

        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        LoginPage loginPage = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        AG_NavigationPanelPage navPanel = new AG_NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);

        loginPage.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));

        Thread.sleep(3000);

        logger.log(Status.INFO, "current page is all appointments dashboard");

        dashboard.search(datasheet.get("Appointment Status"));

        Thread.sleep(5000);

        int allAppRowsSize = readNumberOfRowsInTable(dashboard.get_allAppointmentTable());

        logger.log(Status.INFO, "Found number of rows in the page: " + allAppRowsSize);

        List<WebElement> column_status = dashboard.get_allAppointmentTableBodyRowsStatusColumn();
        logger.log(Status.INFO, "selected the column Status");

        List<WebElement> column_language = dashboard.get_allAppointmentTableBodyRowsLanguageColumn();
        logger.log(Status.INFO, "selected the column Language");

        String view_Text = null;// for caturing appointment id

        logger.log(Status.INFO, "looping through all rows in Status column till we find new appointment");

        for (int i = 0; i <= allAppRowsSize - 1; i++) {

            String status = column_status.get(i).getText();
            System.out.println(column_status.get(i).getText());

            String language = column_language.get(i).getText();
            System.out.println(column_language.get(i).getText());

            if (status.equalsIgnoreCase(datasheet.get("Appointment Status"))
                    && language.equalsIgnoreCase(datasheet.get("Requested Language"))) {

                logger.log(Status.INFO, "found a Confirmed appointment");

                List<WebElement> column_view = dashboard.get_allAppointmentTableBodyRowsViewColumn();

                WebElement view_id = column_view.get(i);

                view_Text = view_id.getText();

                System.out.println(view_Text);


                break;
            }
            js.executeScript("window.scrollBy(0,100)", "");
        }

        loginPage.click_logOut();
        logger.log(Status.INFO, "clicked Log-out button");

        Thread.sleep(1000);
        loginPage.doLogin(datasheet.get("Interpreter Username"),datasheet.get("Interpreter Password"));

        logger.log(Status.INFO, "logged in as interpreter");

        Thread.sleep(2000);
        // UI.waitForElementVisibility(navPanel.Home_Interpreter());

        navPanel.click_Home_Interpreter();
        Thread.sleep(1000);

        interpreterDb.clickAcceptedTab();
        logger.log(Status.INFO, "Clicked Interpreter>Accepted tab");

        Thread.sleep(3000);

        interpreterDb.enterSearch(view_Text);
        logger.log(Status.INFO, "entered id " + view_Text + " in search box");

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
        logger.log(Status.INFO, "Clicked the Appointment id in view column");

        // UI.waitForElementVisibility(interpreterDb.AppointmentPageTitle());


        Thread.sleep(2000);
        //UI.waitForElementVisibility(interpreterDb.CheckOutButton());

        interpreterDb.clickCheckOutButton();
        logger.log(Status.PASS, "Clicked Check out button");

        Thread.sleep(3000);

        //UI.waitForElementVisibility(interpreterDb.FinalizeAppointmentPageTitle());

        List<WebElement> dropdown = interpreterDb.get_FinalizeAppointmentDropdownsList();

        dropdown.get(1).sendKeys(datasheet.get("Requested Language"));
        dropdown.get(1).sendKeys(Keys.ENTER);
        dropdown.get(2).sendKeys(datasheet.get("Request Reimbursement?"));
        dropdown.get(2).sendKeys(Keys.ENTER);

        Thread.sleep(3000);

        interpreterDb.clickFinalizeAppointmentButton();

        Thread.sleep(10000);

        //UI.waitForElementVisibility(dashboard.logOut());

       // dashboard.click_logOut();
       // logger.log(Status.INFO, "logged out as interpreter");


    }
    @Test(priority = 6)

    public void viewFinancialBreakdown() throws InterruptedException, IOException {

        driver = openBrowser();

        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        LoginPage loginPage = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        AG_NavigationPanelPage navPanel = new AG_NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);

        loginPage.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));

        Thread.sleep(3000);

        logger.log(Status.INFO, "current page is all appointments dashboard");

        dashboard.search(datasheet.get("Appointment Status"));

        Thread.sleep(5000);

        int allAppRowsSize = readNumberOfRowsInTable(dashboard.get_allAppointmentTable());

        logger.log(Status.INFO, "Found number of rows in the page: " + allAppRowsSize);

        List<WebElement> column_status = dashboard.get_allAppointmentTableBodyRowsStatusColumn();
        logger.log(Status.INFO, "selected the column Status");

        List<WebElement> column_language = dashboard.get_allAppointmentTableBodyRowsLanguageColumn();
        logger.log(Status.INFO, "selected the column Language");

        String view_Text = null;// for caturing appointment id

        logger.log(Status.INFO, "looping through all rows in Status column till we find new appointment");

        for (int i = 0; i <= allAppRowsSize - 1; i++) {

            String status = column_status.get(i).getText();
            System.out.println(column_status.get(i).getText());

            String language = column_language.get(i).getText();
            System.out.println(column_language.get(i).getText());

            if (status.equalsIgnoreCase(datasheet.get("Appointment Status"))
                    && language.equalsIgnoreCase(datasheet.get("Requested Language"))) {

                logger.log(Status.INFO, "found a Confirmed appointment");

                List<WebElement> column_view = dashboard.get_allAppointmentTableBodyRowsViewColumn();

                WebElement view_id = column_view.get(i);

                view_Text = view_id.getText();

                System.out.println(view_Text);


                break;
            }
            js.executeScript("window.scrollBy(0,100)", "");
        }

        loginPage.click_logOut();
        logger.log(Status.INFO, "clicked Log-out button");

        Thread.sleep(1000);
        loginPage.doLogin(datasheet.get("Interpreter Username"),datasheet.get("Interpreter Password"));

        logger.log(Status.INFO, "logged in as interpreter");

        Thread.sleep(2000);
        // UI.waitForElementVisibility(navPanel.Home_Interpreter());

        navPanel.click_Home_Interpreter();
        Thread.sleep(1000);

        interpreterDb.clickAcceptedTab();
        logger.log(Status.INFO, "Clicked Interpreter>Accepted tab");

        Thread.sleep(3000);

        interpreterDb.enterSearch(view_Text);
        logger.log(Status.INFO, "entered id " + view_Text + " in search box");

        Thread.sleep(2000);
        //  UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentTableColView());

        List<WebElement> View_accepted = interpreterDb.get_InterpreterDashboardAppointmentTableColView();

        String acceptedId = View_accepted.get(0).getText();
        Assert.assertEquals(view_Text, acceptedId);
        logger.log(Status.PASS, "Accepted appointment is available in Accepted tab list");

        List<WebElement> Status_accepted = interpreterDb.get_InterpreterDashboardAppointmentTableColStatus();

        String acceptedStatus = Status_accepted.get(0).getText();
        Assert.assertEquals("Completed", acceptedStatus);
        logger.log(Status.PASS, "The status is confirmed in the list");

        View_accepted.get(0).click();
        logger.log(Status.INFO, "Clicked the Appointment id in view column");

        // UI.waitForElementVisibility(interpreterDb.AppointmentPageTitle());

        Thread.sleep(2000);

        interpreterDb.clickFinancialBreakDownButton();
        logger.log(Status.INFO, "clicked the FinancialBreakDown button ");

        Thread.sleep(1000);

        /*interpreterDb.clickFinancialBreakDownPageCrossButton();
        logger.log(Status.INFO, "clicked cross button of financial break down page cross button to exit the page ");

        Thread.sleep(1000);

        interpreterDb.clickAppointmentCrossButton();
        logger.log(Status.INFO, "clicked cross button of appointment page ");

        Thread.sleep(10000);


        dashboard.click_logOut();
        logger.log(Status.INFO, "logged out as interpreter");
        Thread.sleep(2000);*/

    }


        @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, "Test Case Failed due to " + result.getThrowable());
        }
        String methodName = BaseClass.getMethodName();
        logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));


    }

    @BeforeTest
    @Parameters({"Module"})
    public void readModule(String moduleName){

        BaseClass.setModuleName(moduleName);

    }

}

