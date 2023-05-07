package com.pom;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.utils.CommonUtils;
import com.utils.DashBoardHeaders;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.base.BaseClass.*;

public class DashBoardPage {

    WebDriver wd;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']")
    private WebElement appointmentsTableinDashBoard;

    @FindBy(css= ".MuiBox-root.css-w2sxf")
    private WebElement Menu;

    @FindBy(xpath= "//input[@class='MuiInputBase-input css-1bqqmdo']")
    private WebElement search;


    @FindBy(css= ".css-1txeit4")
    private WebElement newAppointment;

    public boolean newAppointmentIsDisplayed(){
        return newAppointment.isDisplayed();
    }

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']")
    private WebElement allAppointmentTable;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']/tbody")
    private WebElement allAppointmentTableBody;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']/tbody/tr")
    private WebElement allAppointmentTableBodyRows;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']/tbody/tr/td[2]")
    private WebElement allAppointmentTableBodyRowsDatecolumn;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']/tbody/tr/td[9]")
    private WebElement allAppointmentTableBodyRowsStatusColumn;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']/tbody/tr/td[1]")
    private WebElement allAppointmentTableBodyRowsViewColumn;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']/tbody/tr/td[7]")
    private WebElement allAppointmentTableBodyRowsPatientColumn;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']/tbody/tr/td[8]")
    private WebElement allAppointmentTableBodyRowsLanguageColumn;


    @FindBy(css= ".MuiBox-root.css-lhz7xj")
    private WebElement appointmentCreatedSuccessMsg;

    @FindBy(xpath = "//img[@class='MuiBox-root css-8kdvm0'][2]")
    private WebElement filter;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']/tbody/tr[1]/td[7]")
    private WebElement PatientConsumer;

    @FindBy(xpath= "//div[@class='MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-multiline css-9q87mf' ]//*[@placeholder='Enter relevant preferences']")
    private WebElement preference;

    @FindBy(xpath= "//div[@class='MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-multiline css-9q87mf' ]//*[@placeholder='Enter relevant comments']")
    private WebElement requester;

    @FindBy(xpath= "//div[@class='MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-multiline css-9q87mf' ]//*[@placeholder='Enter relevant notes']")
    private WebElement scheduler_notes;

    @FindBy(xpath= "//button[text()=' Save']")
    private WebElement Save;

    @FindBy(xpath = "//tbody[@class='MuiTableBody-root css-1xnox0e']//tr[2]//td[9]")
    private WebElement click_Status;

    @FindBy(xpath = "//label[@id='typo_Statusform_apptstatus']/../following-sibling::div//input")
    private WebElement appointmentStatus;

    @FindBy(id = "btn_Statusform_save")
    private WebElement save_Status;

    @FindBy(xpath = "//span[text()='TO BE PROCESSED']/..")
    private WebElement toBeProcessedTab;

    @FindBy(xpath = "//span[text()='PENDING ACCEPTANCE']/..")
    private WebElement pendingAcceptanceTab;

    @FindBy(xpath = "//span[contains(text(),'URGENT')]")
    private WebElement urgentTab;




    public DashBoardPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }

    public void updateQuickStatus(String appId) throws InterruptedException {

        WebElement statusLink   =  driver.findElement(By.xpath("//*[text()='"+ appId +"']/../../following-sibling::td[8]//span/span"));

        statusLink.click();
        Thread.sleep(2000);
        appointmentStatus.click();
        appointmentStatus.sendKeys(datasheet.get("Appointment Status"));
        appointmentStatus.sendKeys(Keys.TAB);

        Thread.sleep(2000);
        save_Status.click();
        Thread.sleep(2000);

    }

    public  void updatePatientNotes(String appIdText) throws InterruptedException, IOException {

        WebElement patientLink =  wd.findElement(By.xpath("//table[@class='MuiTable-root css-v6tz28']/tbody/tr//td//div[text()='"+ appIdText  +"']/../../following-sibling::td[6]//span/span"));

        patientLink.click();
        Thread.sleep(2000);

        scheduler_notes.clear();
        preference.sendKeys("Test Preferenes:" + CommonUtils.getCurrentSystemDate());
        Thread.sleep(2000);

        requester.clear();
        requester.sendKeys("Test requester");
        Thread.sleep(2000);

        scheduler_notes.clear();
        scheduler_notes.sendKeys("Test Notes");
        Thread.sleep(2000);
        Save.click();
        Thread.sleep(2000);
        logger.addScreenCaptureFromPath(new BaseClass().takeScreenshotForStep("Preference Edited"));

    }

    public  void clickSearchInHomePage(String keyword){


        search.sendKeys(keyword);
    }

    public  WebElement getWebElementOfHeaderAndCellValue(DashBoardHeaders dbh, String cellValue){

        WebElement  appId = null;
        DashBoardHeaders searchString = dbh;
        String  actualHeader =CommonUtils.getActualHeaderStringFromDashBoardTable(searchString);

        int i = 1;
        List<WebElement> rows = wd.findElements(By.xpath("//table[@class='MuiTable-root css-v6tz28']//th/div[1]"));
        Map<String, Integer> headIndex = new LinkedHashMap<>();


        for (WebElement r : rows) {

            String val = r.getText();
            String firtword = val.split("\\R")[0];
            headIndex.put(firtword, i);
            i++;
        }

        int headerIndex = headIndex.get(actualHeader);

        int recordsCount = wd.findElements(By.xpath("//table[@class='MuiTable-root css-v6tz28']//tbody//tr")).size();
        logger.log(Status.INFO,"Table is empty");
        for (int rowNumber = 1; rowNumber <= recordsCount; rowNumber++) {

            String part1 = "//table[@class='MuiTable-root css-v6tz28']//tbody//tr[";
            String part2 = "]//td//*[text()='" + cellValue + "']";
            String part3 = "//td//*[text()='" + cellValue + "']/ancestor::td/preceding-sibling::td[";

            BaseClass b = new BaseClass();
            boolean recordTEextMatching = b.isElementByXpath( part1 + rowNumber + part2);
            if (recordTEextMatching) {

                appId = b.getElementByXpath(wd, (part3 + (headerIndex - 1) + "]"));
                break;

            }
        }
       logger.log(Status.INFO,"Appointment ID found is " + appId.getText());
        return appId;


    }

    public  WebElement get_allAppointmentTable(){

        return allAppointmentTable;
    }

    public  List<WebElement> get_allAppointmentTableBodyRowsStatusColumn(){

        return  wd.findElements(By.xpath("//table[@class='MuiTable-root css-v6tz28']/tbody/tr/td[9]"));

    }

    public  List<WebElement> get_allAppointmentTableBodyRowsLanguageColumn() {
        return wd.findElements(By.xpath("//table[@class='MuiTable-root css-v6tz28']/tbody/tr/td[8]"));
    }

    public List<WebElement> get_allAppointmentTableBodyRowsViewColumn() {
        return wd.findElements(By.xpath("//table[@class='MuiTable-root css-v6tz28']/tbody/tr/td[1]"));
    }


    public  void search(String searchFor) throws InterruptedException {
        search.sendKeys(searchFor);
        Thread.sleep(3000);
        search.sendKeys(Keys.TAB);
    }

    public  WebElement getAppIDWebElementwithText(String appidText){

        WebElement appId =   wd.findElement(By.xpath("//div[@class='custom-badge' and text()='"+ appidText + "']"));

        return  appId;

    }

    public  void clickTobeProcessedTab(){

        toBeProcessedTab.click();
    }

    public  void clickAcceptancePendingTab(){

        pendingAcceptanceTab.click();
    }

    public  void clickUrgent() throws InterruptedException {
        urgentTab.click();
        Thread.sleep(4000);
    }

     public  String getPatientNameFromAppId(String appID){

         BaseClass b = new BaseClass();
         try {
             WebElement name = b.getElementByXpath(wd, "//div[text()='" + appID + "']/../../following-sibling::td[6]//span/span");
             String nameString = name.getText();
             return nameString;
         }catch (Exception e){
             e.printStackTrace();
             return  "Not Found";
         }
    }

    public List<WebElement> getAllAppointmentIdsWithStatus(String status){

        List<WebElement> li = wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[9]"));
         List<WebElement> requiredList = new ArrayList<>();
         for(int i=0; i<li.size();i++){

             if(li.get(i).getText().equalsIgnoreCase(status)){

                 requiredList.add(li.get(i));
             }
         }
         return requiredList;

    }




}
