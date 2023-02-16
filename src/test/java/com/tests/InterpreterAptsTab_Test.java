package com.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.pom.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;


public class InterpreterAptsTab_Test extends BaseClass {

    WebDriver driver = null;
    ExtentTest logger = null;
    XSSFSheet sheet = null;

    @Test(description = "This TC will validate the table has the required columns",priority=1)

    public void verifyColumnsRequiredAreAvailableInTable() throws InterruptedException, IOException {

        driver = openBrowser();
        driver.manage().window().maximize();

        LoginPage lo = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        NavigationPanelPage navPanel = new NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
        InterpreterPage interpreterPage = new InterpreterPage(driver);


        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        JavascriptExecutor js = (JavascriptExecutor) driver;//to use for scrolling up and down the page

        lo.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));
        logger.log(Status.INFO, "logged in as scheduler");
        Thread.sleep(5000);

        //UI.waitForElementVisibility(dashboard.newAppointment());

        logger.log(Status.INFO, "current page is all appointments dashboard");

        // UI.waitForElementVisibility(navPanel.Interpreters());
        Thread.sleep(5000);
        navPanel.click_Interpreters();
        logger.log(Status.INFO, "clicked Interpreters tab");
        // UI.waitForElementVisibility(interpreterPage.search());
        Thread.sleep(3000);
        logger.log(Status.INFO, "current page is Interpreters dashboard");
        //To find number of columns in the table
        List<WebElement> columnNames = interpreterPage.tableInterpreterListColumnNames();
        logger.log(Status.INFO, "Number of columns in table are "+columnNames.size());

        //looping though all the columns text to see if they have the columns required.
        for(int i=0;i<columnNames.size();i++) {

            System.out.println(columnNames.get(i).getText());

            //As the getText() is giving column name and sorting arrows text we are using below code to see if the required column names are displayed.

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

    }

    @Test(description = "This TC will verify default sort is Interpreter name",priority=2)

    public void verifyDefaultSortIsInterpreterName() throws InterruptedException, IOException {

        driver = openBrowser();
        driver.manage().window().maximize();

        LoginPage lo = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        NavigationPanelPage navPanel = new NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
        InterpreterPage interpreterPage = new InterpreterPage(driver);

        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        JavascriptExecutor js = (JavascriptExecutor) driver;//to use for scrolling up and down the page

        lo.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));
        logger.log(Status.INFO, "logged in as scheduler");
        Thread.sleep(5000);

        //UI.waitForElementVisibility(dashboard.newAppointment());

        logger.log(Status.INFO, "current page is all appointments dashboard");

        // UI.waitForElementVisibility(navPanel.Interpreters());
        navPanel.click_Interpreters();
        //UI.click(navPanel.SubInterpreter());

        // UI.waitForElementVisibility(interpreterPage.search());

        Thread.sleep(5000);

        int rowsSize = readNumberOfRowsInTable(interpreterPage.get_tableInterpreterListBody());

        logger.log(Status.INFO, "Found number of rows in the page: " + rowsSize);

        List<WebElement> column_view_interpreter = interpreterPage.allAppointmentTableBodyRowsViewInterpretercolumn();
        logger.log(Status.INFO, "selected the column View Interpreter");

        //String[] interpreter_Names = new String[column_view_interpreter.size()];
        List<String> interpreter_Names=new ArrayList<String>();

        for (int i = 0; i <= rowsSize - 1; i++) {

            System.out.println(column_view_interpreter.get(i).getText());
            logger.log(Status.INFO, "The interpreter list has "+i  +column_view_interpreter.get(i).getText());

            interpreter_Names.add(column_view_interpreter.get(i).getText());
        }
        boolean isSort=stringSort(interpreter_Names);

        if (isSort) {

            logger.log(Status.PASS, "The column View Interpreter is sorted in alphabetical order.");
        }else {
            logger.log(Status.FAIL, "The column View Interpreter is not sorted in alphabetical order.");
        }


    }

    @Test(description = "This TC will verify Search for all columns data",priority=3)
    public void verifySearchForAllColumnsData()throws InterruptedException, IOException {

        driver = openBrowser();
        driver.manage().window().maximize();

        LoginPage lo = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        NavigationPanelPage navPanel = new NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
        InterpreterPage interpreterPage = new InterpreterPage(driver);

        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        JavascriptExecutor js = (JavascriptExecutor) driver;//to use for scrolling up and down the page

        lo.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));
        logger.log(Status.INFO, "logged in as scheduler");
        Thread.sleep(5000);

        //UI.waitForElementVisibility(dashboard.newAppointment());

        logger.log(Status.INFO, "current page is all appointments dashboard");

        // UI.waitForElementVisibility(navPanel.Interpreters());
        Thread.sleep(5000);
        navPanel.click_Interpreters();

        Thread.sleep(3000);

        // UI.waitForElementVisibility(interpreterPage.search());

        String c1=datasheet.get("Data");

            /* if (c1 == null || c1.getCellType() == Cell.CELL_TYPE_BLANK) {

                System.out.println("String data is empty");
                // This cell is empty
            }*/
        if (c1 == null) {
            System.out.println("Data is empty");
        }
        else {

            interpreterPage.enterSearch(datasheet.get("Data"));
            logger.log(Status.INFO, "Entered "+c1+"in Search");
            Thread.sleep(2000);
            //getting number of rows of that page table
            WebElement tableBody_forStringValue = interpreterPage. get_tableInterpreterListBody();
            logger.log(Status.INFO, "Took all rows in a list.");
            List<WebElement>TotalRowsList = tableBody_forStringValue.findElements(By.tagName("tr"));
            System.out.println("Total number of Rows in the table are : "+ TotalRowsList.size());
            int rowSize=TotalRowsList.size();
            logger.log(Status.INFO, "Found number of rows in the page: "+rowSize);
            logger.log(Status.INFO, "Iterating through the rows");
            for(int i=1;i<=rowSize;i++) {

                List<WebElement> ToGetColumns = tableBody_forStringValue.findElements(By.xpath("//tr[" + i + "]/td"));
                logger.log(Status.INFO, "Took all cell's data in a list.");
                //List<WebElement> ToGetColumns = driver.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr["+i+"]/td"));

                int colsize = ToGetColumns.size();
                System.out.println(colsize);
                logger.log(Status.INFO, "Iterating through the cells in each row.");
                for(int j=1;j<=colsize;j++) {
                    String td = tableBody_forStringValue.findElement(By.xpath("//tr/td[" + j + "]")).getText();
                    //String str = driver.findElement(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td["+j+"]")).getText();
                    System.out.println(td);
                    if (td.equalsIgnoreCase(datasheet.get("Data"))) {

                        Assert.assertEquals(td.equalsIgnoreCase(datasheet.get("Data")), true);
                        logger.log(Status.PASS, "Searched record which has the mentioned data "+datasheet.get("Data"));
                        break;

                    }

                }

            }
        }
          /*  Cell c1=datasheet.getCellValue(sheet_Search,1,0);
            String c2=datasheet.get("Integer Data");

            if (c2 == null || c2.getCellType() == Cell.CELL_TYPE_BLANK) {

                System.out.println("Integer data is empty");
                // This cell is empty
            }else {

                logger.log(Status.INFO, "Entered a phone number in Search text box");

                String intvalue=datasheet.get("Integer Data");

                System.out.println(intvalue);

                System.out.println(intvalue.length());

                int trimIndex = intvalue.length()-1;

                String trimmed_intvalue= intvalue.substring(1,trimIndex);

                System.out.println(trimmed_intvalue);

                UI.sendKeys(interpreterPage.search(),trimmed_intvalue);

                logger.log(Status.INFO, "The phone number is "+trimmed_intvalue);

                //as 10 digit phone numbers are displayed in USA phone number format, we have to convert the number so we can compare the data.
                if(trimmed_intvalue.length()==10) {

                    String firstBracket = "(";
                    String endingBracket=") ";
                    String countryCode=trimmed_intvalue.substring(0,3);
                    countryCode=firstBracket.concat(countryCode);
                    countryCode=countryCode.concat(endingBracket);
                    System.out.println(countryCode);
                    String phoneNumber=trimmed_intvalue.substring(3, 6);
                    System.out.println(phoneNumber);
                    phoneNumber=phoneNumber.concat("-");
                    System.out.println(phoneNumber);
                    String usPhoneNumber=countryCode.concat(phoneNumber).concat(trimmed_intvalue.substring(6,10));
                    System.out.println(usPhoneNumber);
                    trimmed_intvalue=usPhoneNumber;
                    System.out.println(trimmed_intvalue);

                    logger.log(Status.INFO, "Converted the phone number to USA format to compare "+trimmed_intvalue);

                }

                Thread.sleep(2000);
                WebElement tableBody_forIntValue = UI.getElement(interpreterPage.tableInterpreterListBody());
                List<WebElement>toGetRows_forIntvalue = tableBody_forIntValue.findElements(By.tagName("tr"));
                logger.log(Status.INFO, "Got all rows of table in a list");
                System.out.println("Total number of Rows in the table are : "+ toGetRows_forIntvalue.size());
                int rowSize_forIntvalue=toGetRows_forIntvalue.size();
                logger.log(Status.INFO, "Found number of rows in the page: "+rowSize_forIntvalue);
                logger.log(Status.INFO, "Iterating through all rows");
                for(int i=1;i<=rowSize_forIntvalue;i++) {

                    List<WebElement> ToGetColumns = tableBody_forIntValue.findElements(By.xpath("//tr[" + i + "]/td"));

                    int colsize = ToGetColumns.size();
                    System.out.println(colsize);
                    logger.log(Status.INFO, "Iterating through all td's in each row");
                    for(int j=1;j<=colsize;j++) {

                        String td = tableBody_forIntValue.findElement(By.xpath("//tr/td[" + j + "]")).getText();

                        System.out.println(td);

                        if (td.equalsIgnoreCase(trimmed_intvalue)) {

                            Assert.assertEquals(td.equalsIgnoreCase(trimmed_intvalue), true);
                            logger.log(Status.PASS, "Searched record which has the mentioned data "+data.getDataAsString(sheet_Search, "Integer Data", 1));
                            break;

                        }

                    }

                }
            }*/
    }


    @Test(description = "This TC will verify No Results Found when no interpreters are found.",priority=4)
    public void verifyNoResultsFound()throws InterruptedException, IOException{

        driver = openBrowser();
        driver.manage().window().maximize();

        LoginPage lo = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        NavigationPanelPage navPanel = new NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
        InterpreterPage interpreterPage = new InterpreterPage(driver);

        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        JavascriptExecutor js = (JavascriptExecutor) driver;//to use for scrolling up and down the page

        lo.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));
        logger.log(Status.INFO, "logged in as scheduler");
        Thread.sleep(5000);

        //UI.waitForElementVisibility(dashboard.newAppointment());

        logger.log(Status.INFO, "current page is all appointments dashboard");

        // UI.waitForElementVisibility(navPanel.Interpreters());
        Thread.sleep(5000);
        navPanel.click_Interpreters();
        Thread.sleep(3000);

        //  UI.waitForElementVisibility(interpreterPage.search());

        logger.log(Status.INFO, "Entering some junk data which is not in any interpreter record");
        interpreterPage.enterSearch("mjnhbgvfcdxsza");

        Thread.sleep(2000);

        Assert.assertEquals(interpreterPage.isDisplayed_noResults(), true);
        logger.log(Status.PASS,"No Records is displayed." );

    }

    @Test(description = "This TC will verify Interpreter appointments page table has the required columns.",priority=5)
    public void verifyColumnsRequiredAreAvailableInAppointmentsTableForAInterpreter()throws InterruptedException, IOException{

        driver = openBrowser();
        driver.manage().window().maximize();

        LoginPage lo = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        NavigationPanelPage navPanel = new NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
        InterpreterPage interpreterPage = new InterpreterPage(driver);
        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        JavascriptExecutor js = (JavascriptExecutor) driver;//to use for scrolling up and down the page

        lo.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));
        logger.log(Status.INFO, "logged in as scheduler");
        Thread.sleep(5000);

        //UI.waitForElementVisibility(dashboard.newAppointment());

        logger.log(Status.INFO, "current page is all appointments dashboard");

        // UI.waitForElementVisibility(navPanel.Interpreters());
        Thread.sleep(5000);
        navPanel.click_Interpreters();

        // UI.waitForElementVisibility(interpreterPage.search());
        Thread.sleep(3000);
        String w = datasheet.get("TypeValue");
        interpreterPage.enterSearch(datasheet.get("TypeValue"));
        logger.log(Status.INFO, "Entered Data "+w+" in Search");

        Thread.sleep(5000);

        WebElement tableBody = interpreterPage.get_tableInterpreterListBody();

        tableBody.findElement(By.xpath("//tr/td[" + 1 + "]")).click();

        Thread.sleep(5000);

        //UI.waitForElementVisibility(interpreterPage.tabAppointments());

        interpreterPage.clickTabAppointments();

        //WebElement appointmentsTable=UI.getElement(interpreterPage.tableAppointmentsList());

        List<WebElement> columnNames = interpreterPage.tableAppointmentsListColumnNames();

        logger.log(Status.INFO, "Number of columns in table are "+columnNames.size());
        System.out.println(columnNames.size());

        //looping though all the columns text to see if they have the columns required.
        for(int i=0;i<columnNames.size();i++) {

            System.out.println(columnNames.get(i).getText());

            //As the getText() is giving column name and sorting arrows text we are using below code to see if the required column names are displayed.

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

    }


    @Test(description = "This TC will verify Interpreter appointments page table has the required columns.",priority=6)
    public void verifyAllAppointmentsAreRelatedToInterpreterByVerifyingLanguage()throws InterruptedException, IOException{

        driver = openBrowser();
        driver.manage().window().maximize();

        LoginPage lo = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        NavigationPanelPage navPanel = new NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
        InterpreterPage interpreterPage = new InterpreterPage(driver);

        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        JavascriptExecutor js = (JavascriptExecutor) driver;//to use for scrolling up and down the page

        lo.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));
        logger.log(Status.INFO, "logged in as scheduler");
        Thread.sleep(5000);

        //UI.waitForElementVisibility(dashboard.newAppointment());

        logger.log(Status.INFO, "current page is all appointments dashboard");

        // UI.waitForElementVisibility(navPanel.Interpreters());
        Thread.sleep(5000);
        navPanel.click_Interpreters();

        // UI.waitForElementVisibility(interpreterPage.search());
        Thread.sleep(3000);

        interpreterPage.enterSearch(datasheet.get("TypeValue"));
        String w = datasheet.get("TypeValue");
        logger.log(Status.INFO, "Entered "+w+" in Search");

        Thread.sleep(5000);

        WebElement tableBody = interpreterPage.get_tableInterpreterListBody();

        tableBody.findElement(By.xpath("//tr/td[" + 1 + "]")).click();

        Thread.sleep(3000);

        // UI.waitForElementVisibility(interpreterPage.tabLanguageProficiency());

        interpreterPage.clickTabLanguageProficiency();

        Thread.sleep(3000);

        List<WebElement> columnLanguage_LanguageProficiency = interpreterPage.tableLanguageProficiencyList_Col_Language();

        String[] language_proficeiency_language_array = new String[columnLanguage_LanguageProficiency.size()];

        for(int i=0;i<columnLanguage_LanguageProficiency.size();i++) {

            System.out.println(columnLanguage_LanguageProficiency.get(i).getText());

            language_proficeiency_language_array[i] =columnLanguage_LanguageProficiency.get(i).getText();

        }

        interpreterPage.clickTabAppointments();

        Thread.sleep(3000);

        List<WebElement> columnLanguage_Appointments = interpreterPage.tableAppointmentList_Col_Language();

        for(int i=0;i<columnLanguage_Appointments.size();i++) {

            System.out.println(columnLanguage_Appointments.get(i).getText());
            String s= columnLanguage_Appointments.get(i).getText();
            Boolean b = ArrayUtils.contains(language_proficeiency_language_array, columnLanguage_Appointments.get(i).getText());

            Assert.assertEquals(true, b);

            logger.log(Status.PASS, "The language "+s+" is included in language proficiency for the interpreter");

        }

    }


    @Test(priority=7)
    public void verifySearchForAllColumnsDataIndividually()throws InterruptedException, IOException{

        driver = openBrowser();
        driver.manage().window().maximize();

        LoginPage lo = new LoginPage(driver);
        DashBoardPage dashboard = new DashBoardPage(driver);
        AppointmentDetailsPage appDetails = new AppointmentDetailsPage(driver);
        NavigationPanelPage navPanel = new NavigationPanelPage(driver);
        InterpreterDashboardPage interpreterDb = new InterpreterDashboardPage(driver);
        InterpreterPage interpreterPage = new InterpreterPage(driver);

        logger = extent.createTest(BaseClass.getMethodName() + "method started");
        JavascriptExecutor js = (JavascriptExecutor) driver;//to use for scrolling up and down the page

        lo.doLogin(datasheet.get("Scheduler Username"),datasheet.get("Scheduler Password"));
        logger.log(Status.INFO, "logged in as scheduler");
        Thread.sleep(5000);

        //UI.waitForElementVisibility(dashboard.newAppointment());

        logger.log(Status.INFO, "current page is all appointments dashboard");

        // UI.waitForElementVisibility(navPanel.Interpreters());
        Thread.sleep(5000);
        navPanel.click_Interpreters();

        // UI.waitForElementVisibility(interpreterPage.search());
        Thread.sleep(3000);

        // UI.waitForElementVisibility(interpreterPage.search());

        interpreterPage.enterSearch(datasheet.get("Search-Interpreter"));
        logger.log(Status.INFO, "Entered String Data in Search");

        Thread.sleep(2000);

        WebElement tableBody = interpreterPage.get_tableInterpreterListBody();
        tableBody.findElement(By.xpath("//tr/td[" + 1 + "]")).click();
        logger.log(Status.INFO, "Clicked the interpreter");

        //UI.waitForElementVisibility(interpreterPage.tabAppointments());
        interpreterPage.clickTabAppointments();
        logger.log(Status.INFO, "Clicked the Tab Appointments");
        Thread.sleep(5000);

        interpreterPage.enterTableAppointmentsListSearch(datasheet.get("TypeValue"));
        logger.log(Status.INFO, "Entered data in Search");

        Thread.sleep(2000);
        //getting number of rows of that page table
        WebElement tableBody_aptslist = interpreterPage.tableAppointmentsListBody();
        logger.log(Status.INFO, "Took all rows in a list.");

        List<WebElement>TotalRowsList_aptslist = tableBody_aptslist.findElements(By.tagName("tr"));

        System.out.println("Total number of Rows in the table are : "+ TotalRowsList_aptslist.size());

        System.out.println(tableBody_aptslist.getText());

        System.out.println("-----------------------");

        int rowSize_appList=TotalRowsList_aptslist.size();
        logger.log(Status.INFO, "Found number of rows in the page: "+rowSize_appList);
        logger.log(Status.INFO, "Iterating through the rows");
        for(int i=1;i<=rowSize_appList;i++) {


            WebElement ToGetSpecicficRow_aptsList = driver.findElement(By.xpath("//div[@class='MuiBox-root css-xj7u6x']//table/tbody/tr["+i+"]"));
            //WebElement ToGetColumns_aptsList = tableBody_aptslist.findElement(By.xpath("//tr[" + i + "]"));
            System.out.println(ToGetSpecicficRow_aptsList.getText());
            System.out.println("*********************************");

            logger.log(Status.INFO, "Took all cell's data in a list.");
            List<WebElement> ToGetColumns_aptsList = driver.findElements(By.xpath("//div[@class='MuiBox-root css-xj7u6x']//table/tbody/tr["+i+"]/td"));

            int colsize_aptsList = ToGetColumns_aptsList.size();
            System.out.println("Total number of cells in each row : "+colsize_aptsList);
            logger.log(Status.INFO, "Iterating through the "+colsize_aptsList+" cells in each row.");
            for(int j=0;j<colsize_aptsList;j++) {

                String td_appList=ToGetColumns_aptsList.get(j).getText();

                System.out.println(td_appList);
                //if (td.equalsIgnoreCase(data.getDataAsString(sheet_Search, "String Data", 1))) {
                if (td_appList.equalsIgnoreCase(datasheet.get("TypeValue"))){

                    Assert.assertEquals(td_appList.equalsIgnoreCase(datasheet.get("TypeValue")), true);
                    logger.log(Status.PASS, "Searched record which has the mentioned data ");
                    break;

                }

            }

        }
    }


    @AfterMethod
    public void tearDown(ITestResult result) throws IOException, InterruptedException {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, "Test Case Failed due to " + result.getThrowable());

        }
        String methodName = BaseClass.getMethodName();
        logger.addScreenCaptureFromPath(takeScreenshotForStep("End of " + methodName));
        Thread.sleep(2000);

        try{
            Thread.sleep(3000);
            driver.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
