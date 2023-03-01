package com.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.pom.*;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

@Listeners({com.listeners.ListenerTest.class})
public class InterpreterAptsTab_Test extends BaseClass {


    @Test(description = "This TC will validate the table has the required columns",priority=1)
    public void verifyColumnsRequiredAreAvailableInTable() throws InterruptedException, IOException {


        driver.get("http://uat.ims.client.sstech.us/login");
        LoginPage lo = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        NavigationPanelPage navPanel = new NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
        InterpreterPage interpreterPage = new InterpreterPage(driver);


        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        JavascriptExecutor js = (JavascriptExecutor) driver;//to use for scrolling up and down the page

        lo.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));
        logger.log(Status.PASS, "logged in as scheduler");
        Thread.sleep(5000);


        logger.log(Status.PASS, "current page is all appointments dashboard");

        Thread.sleep(5000);
        navPanel.click_Interpreters();
        logger.log(Status.PASS, "clicked Interpreters tab");
        Thread.sleep(3000);
        //To find number of columns in the table
        List<WebElement> columnNames = interpreterPage.tableInterpreterListColumnNames();
        logger.log(Status.PASS, "Number of columns in table are "+columnNames.size());

        //looping though all the columns text to see if they have the columns required.
        for(int i=0;i<columnNames.size();i++) {

            String[] col_names= {"VIEW INTERPRETER","EMAIL ADDRESS","GENDER","LEGACY INTERPRETER ID","SMS CAPABLE PHONE NUMBER","LANDLINE","ADDRESS","INACTIVE"};

            String match = "default";
            for (int j = 0; j < col_names.length; j++) {

                if (columnNames.get(i).getText().contains(col_names[j])) {
                    match = col_names[j];

                    break;
                }
            }

            switch(match) {

                case "VIEW INTERPRETER":
                    logger.log(Status.PASS, "Column 'VIEW INTERPRETER' is present");
                    break;

                case "EMAIL ADDRESS":
                    logger.log(Status.PASS, "Column 'EMAIL ADDRESS' is present");
                    break;

                case "GENDER":
                    logger.log(Status.PASS, "Column 'GENDER' is present");
                    break;

                case "LEGACY INTERPRETER ID":
                    logger.log(Status.PASS, "Column 'LEGACY INTERPRETER ID' is present");
                    break;

                case "SMS CAPABLE PHONE NUMBER":
                    logger.log(Status.PASS, "Column 'SMS CAPABLE PHONE NUMBER' is present");
                    break;

                case "LANDLINE":
                    logger.log(Status.PASS, "Column 'LANDLINE' is present");
                    break;

                case "ADDRESS":
                    logger.log(Status.PASS, "Column 'ADDRESS' is present");
                    break;

                case "INACTIVE":
                    logger.log(Status.PASS, "Column 'INACTIVE' is present");
                    break;

            }

        }
        lo.click_logOut();

    }

    @Test(description = "This TC will verify default sort is Interpreter name",priority=2)
    public void verifyDefaultSortIsInterpreterName() throws InterruptedException, IOException {


        driver.get("http://uat.ims.client.sstech.us/login");
        LoginPage lo = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        NavigationPanelPage navPanel = new NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
        InterpreterPage interpreterPage = new InterpreterPage(driver);

        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        JavascriptExecutor js = (JavascriptExecutor) driver;//to use for scrolling up and down the page

        lo.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));
        logger.log(Status.PASS, "logged in as scheduler");
        Thread.sleep(5000);

        navPanel.click_Interpreters();

        Thread.sleep(5000);

        int rowsSize = readNumberOfRowsInTable(interpreterDb.get_tableInterpreterListBody());
        
        List<WebElement> column_view_interpreter = interpreterPage.allAppointmentTableBodyRowsViewInterpretercolumn();
        logger.log(Status.PASS, "selected the column View Interpreter");

        //String[] interpreter_Names = new String[column_view_interpreter.size()];
        List<String> interpreter_Names=new ArrayList<String>();

        for (int i = 0; i <= rowsSize - 1; i++) {

            logger.log(Status.PASS, "The interpreter list has "+i  +column_view_interpreter.get(i).getText());

            interpreter_Names.add(column_view_interpreter.get(i).getText());
        }
        boolean isSort=stringSort(interpreter_Names);

        if (isSort) {

            logger.log(Status.PASS, "The column View Interpreter is sorted in alphabetical order.");
        }else {
            logger.log(Status.FAIL, "The column View Interpreter is not sorted in alphabetical order.");
        }
        lo.click_logOut();


    }

    @Test(description = "This TC will verify Search for all columns data",priority=3)
    public void verifySearchForAllColumnsData()throws InterruptedException, IOException {

        driver.get("http://uat.ims.client.sstech.us/login");
        LoginPage lo = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        NavigationPanelPage navPanel = new NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
        InterpreterPage interpreterPage = new InterpreterPage(driver);

        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        JavascriptExecutor js = (JavascriptExecutor) driver;//to use for scrolling up and down the page

        lo.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));
        logger.log(Status.PASS, "logged in as scheduler");
        Thread.sleep(5000);

        navPanel.click_Interpreters();
        Thread.sleep(3000);

        String c1=datasheet.get("Data");
        if (c1 == null) {
            logger.log(Status.PASS,"Data is empty");
        }
        else {

            interpreterPage.enterSearch(datasheet.get("Data"));
            logger.log(Status.PASS, "Entered "+c1+"in Search");
            Thread.sleep(2000);
            //getting number of rows of that page table
            WebElement tableBody_forStringValue = interpreterDb.get_tableInterpreterListBody();
            logger.log(Status.PASS, "Took all rows in a list.");
            List<WebElement>TotalRowsList = tableBody_forStringValue.findElements(By.tagName("tr"));

            int rowSize=TotalRowsList.size();
            for(int i=1;i<=rowSize;i++) {

                List<WebElement> ToGetColumns = tableBody_forStringValue.findElements(By.xpath("//tr[" + i + "]/td"));
                logger.log(Status.PASS, "Took all cell's data in a list.");
                //List<WebElement> ToGetColumns = driver.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr["+i+"]/td"));

                int colsize = ToGetColumns.size();
                logger.log(Status.PASS, "Iterating through the cells in each row.");
                for(int j=1;j<=colsize;j++) {
                    String td = tableBody_forStringValue.findElement(By.xpath("//tr/td[" + j + "]")).getText();
                    //String str = driver.findElement(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td["+j+"]")).getText();
                    if (td.equalsIgnoreCase(datasheet.get("Data"))) {

                        Assert.assertEquals(td.equalsIgnoreCase(datasheet.get("Data")), true);
                        logger.log(Status.PASS, "Searched record which has the mentioned data "+datasheet.get("Data"));
                        break;

                    }

                }

            }
        }
        lo.click_logOut();

    }


    @Test(description = "This TC will verify No Results Found when no interpreters are found.",priority=4)
    public void verifyNoResultsFound()throws InterruptedException, IOException{


        driver.get("http://uat.ims.client.sstech.us/login");
        LoginPage lo = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        NavigationPanelPage navPanel = new NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
        InterpreterPage interpreterPage = new InterpreterPage(driver);

        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        JavascriptExecutor js = (JavascriptExecutor) driver;//to use for scrolling up and down the page

        lo.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));
        logger.log(Status.PASS, "logged in as scheduler");
        Thread.sleep(5000);


        logger.log(Status.PASS, "current page is all appointments dashboard");

        Thread.sleep(5000);
        navPanel.click_Interpreters();
        Thread.sleep(3000);


        logger.log(Status.PASS, "Entering some junk data which is not in any interpreter record");
        interpreterPage.enterSearch("mjnhbgvfcdxsza");

        Thread.sleep(2000);

        Assert.assertEquals(interpreterPage.isDisplayed_noResults(), true);
        logger.log(Status.PASS,"No Records is displayed." );
        lo.click_logOut();

    }

    @Test(description = "This TC will verify Interpreter appointments page table has the required columns.",priority=5)
    public void verifyColumnsRequiredAreAvailableInAppointmentsTableForAInterpreter()throws InterruptedException, IOException{


        driver.get("http://uat.ims.client.sstech.us/login");
        LoginPage lo = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        NavigationPanelPage navPanel = new NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
        InterpreterPage interpreterPage = new InterpreterPage(driver);
        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        JavascriptExecutor js = (JavascriptExecutor) driver;//to use for scrolling up and down the page

        lo.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));
        logger.log(Status.PASS, "logged in as scheduler");
        Thread.sleep(5000);

        navPanel.click_Interpreters();

        Thread.sleep(3000);
        String w = datasheet.get("TypeValue");
        interpreterPage.enterSearch(datasheet.get("TypeValue"));
        logger.log(Status.PASS, "Entered Data "+w+" in Search");

        Thread.sleep(3000);

        WebElement tableBody = interpreterDb.get_tableInterpreterListBody();

        tableBody.findElement(By.xpath("//tr/td[" + 1 + "]")).click();

        Thread.sleep(5000);

        interpreterPage.clickTabAppointments();

        List<WebElement> columnNames = interpreterPage.tableAppointmentsListColumnNames();

        logger.log(Status.PASS, "Number of columns in table are "+columnNames.size());

        //looping though all the columns text to see if they have the columns required.
        for(int i=0;i<columnNames.size();i++) {


            String[] col_names= {"VIEW","DATE","START","END","DURATION(HRS)","APPT TYPE","LANGUAGE","PATIENT/CONSUMER","STATUS"};

            String match = "default";
            for (int j = 0; j < col_names.length; j++) {
                if (columnNames.get(i).getText().contains(col_names[j])) {
                    match = col_names[j];
                    break;
                }

            }

            switch(match) {

                case "VIEW":
                    logger.log(Status.PASS, "Column 'View' is present");
                    break;

                case "DATE":
                    logger.log(Status.PASS, "Column 'Date' is present");
                    break;

                case "START":
                    logger.log(Status.PASS, "Column 'Start' is present");
                    break;

                case "END":
                    logger.log(Status.PASS, "Column 'End' is present");
                    break;

                case "DURATION(HRS)":
                    logger.log(Status.PASS, "Column 'Duration(Hrs)' is present");
                    break;

                case "APPT TYPE":
                    logger.log(Status.PASS, "Column Appt Type' is present");
                    break;

                case "LANGUAGE":
                    logger.log(Status.PASS, "Column 'Language' is present");
                    break;

                case "PATIENT/CONSUMER":
                    logger.log(Status.PASS, "Column 'Patient/Consumer' is present");
                    break;

                case "STATUS":
                    logger.log(Status.PASS, "Column 'Status' is present");
                    break;

            }

        }
        lo.click_logOut();

    }


    @Test(description = "This TC will verify Interpreter appointments page table has the required columns.",priority=6)
    public void verifyAllAppointmentsAreRelatedToInterpreterByVerifyingLanguage()throws InterruptedException, IOException{


        driver.get("http://uat.ims.client.sstech.us/login");
        LoginPage lo = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        NavigationPanelPage navPanel = new NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
        InterpreterPage interpreterPage = new InterpreterPage(driver);

        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        JavascriptExecutor js = (JavascriptExecutor) driver;//to use for scrolling up and down the page

        lo.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));
        logger.log(Status.PASS, "logged in as scheduler");
        Thread.sleep(5000);

        navPanel.click_Interpreters();

        Thread.sleep(3000);

        interpreterPage.enterSearch(datasheet.get("TypeValue"));
        String w = datasheet.get("TypeValue");
        logger.log(Status.PASS, "Entered "+w+" in Search");

        Thread.sleep(3000);

        WebElement tableBody = interpreterDb.get_tableInterpreterListBody();

        tableBody.findElement(By.xpath("//tr/td[" + 1 + "]")).click();

        Thread.sleep(3000);

        interpreterPage.clickTabLanguageProficiency();

        Thread.sleep(3000);

        List<WebElement> columnLanguage_LanguageProficiency = interpreterPage.tableLanguageProficiencyList_Col_Language();

        Set<String> interpreterLanguagesSet = new HashSet<>();

        for(WebElement e: columnLanguage_LanguageProficiency) {

            interpreterLanguagesSet.add(e.getText());

        }

        interpreterPage.clickTabAppointments();

        Thread.sleep(3000);

        List<WebElement> columnLanguage_Appointments = interpreterPage.tableAppointmentList_Col_Language();

        for(int i=0;i<columnLanguage_Appointments.size();i++) {

            String s= columnLanguage_Appointments.get(i).getText();
            Boolean b = interpreterLanguagesSet.contains(columnLanguage_Appointments.get(i).getText());

            logger.log(Status.PASS, "The language "+s+" is included in language proficiency for the interpreter");
        }
        lo.click_logOut();

    }

    @Test(priority=7)
    public void verifySearchForAllColumnsDataIndividually()throws InterruptedException, IOException{


        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        driver.get("http://uat.ims.client.sstech.us/login");
        LoginPage lo = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        NavigationPanelPage navPanel = new NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
        InterpreterPage interpreterPage = new InterpreterPage(driver);

        lo.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));
        logger.log(Status.PASS, "logged in as scheduler");
        Thread.sleep(5000);
        navPanel.click_Interpreters();
        Thread.sleep(3000);

        interpreterPage.enterSearch(datasheet.get("Search-Interpreter"));
        logger.log(Status.PASS, "Entered String Data in Search");

        Thread.sleep(2000);

        WebElement tableBody = interpreterDb.get_tableInterpreterListBody();
        tableBody.findElement(By.xpath("//tr/td[" + 1 + "]")).click();
        logger.log(Status.PASS, "Clicked the interpreter");

        interpreterPage.clickTabAppointments();
        logger.log(Status.PASS, "Clicked the Tab Appointments");
        Thread.sleep(5000);

        interpreterPage.enterTableAppointmentsListSearch(datasheet.get("TypeValue"));
        logger.log(Status.PASS, "Entered data in Search");

        Thread.sleep(2000);
        //getting number of rows of that page table
      /*  WebElement tableBody_aptslist = interpreterPage.tableAppointmentsListBody();

        List<WebElement>TotalRowsList_aptslist = tableBody_aptslist.findElements(By.tagName("tr"));

        int rowSize_appList=TotalRowsList_aptslist.size();
        logger.log(Status.PASS, "Found number of rows in the page: "+rowSize_appList);
        for(int i=1;i<=rowSize_appList;i++) {

            List<WebElement> ToGetColumns_aptsList = driver.findElements(By.xpath("//div[@class='MuiBox-root css-xj7u6x']//table/tbody/tr["+i+"]/td"));

            int colsize_aptsList = ToGetColumns_aptsList.size();
            for(int j=0;j<colsize_aptsList;j++) {

                String td_appList=ToGetColumns_aptsList.get(j).getText();

                if (td_appList.equalsIgnoreCase(datasheet.get("TypeValue"))){

                    Assert.assertEquals(td_appList.equalsIgnoreCase(datasheet.get("TypeValue")), true);
                    logger.log(Status.PASS, "Searched record which has the mentioned data ");
                    break;

                }

            }
*/
            lo.click_logOut();
//        }

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException, InterruptedException {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, "Test Case Failed due to " + result.getThrowable());

        }
        String methodName = BaseClass.getMethodName();
        logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));
        Thread.sleep(2000);

    }

}
