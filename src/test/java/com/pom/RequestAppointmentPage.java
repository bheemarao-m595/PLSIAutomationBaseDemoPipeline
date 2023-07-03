package com.pom;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.utils.CommonUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.base.BaseClass.*;

public class RequestAppointmentPage {


    WebDriver wd;

    @FindBy(xpath= "//*[text()='Home - Client']/../..//..")
    private WebElement clients;

    @FindBy(xpath= "//li[@class='MuiListItem-root MuiListItem-padding css-9bln5v']")
    private WebElement Subclient;

    @FindBy(xpath= "//button[text()='REQUEST APPOINTMENT']")
    private WebElement requestAppointment;

    @FindBy(xpath= "//input[@id='radio_medical_appt']")
    private WebElement medical;

    @FindBy(xpath= "//input[@id='rad_nonmedical_appt']")
    private WebElement nonmedical;

    @FindBy(xpath= "//input[@name='appointments.0.aptDate']")
    private WebElement Appointmentdate;

    @FindBy(xpath= "//input[@name='appointments.0.aptStartTime']")
    private WebElement startTime;

    @FindBy(xpath= "//input[@name='appointments.0.aptEndTime']")
    private WebElement endTime;


    @FindBy(xpath= "//label[@id='typo_apptform_client']/../following-sibling::div//input")
    private WebElement client;

    @FindBy(xpath= "//label[@id='typo_apptform_facility']/../following-sibling::div//input")
    private WebElement facility;

    @FindBy(xpath= "//label[@id='typo_apptform_appttype']/../following-sibling::div//input")
    private WebElement appointmenttype;

    @FindBy(xpath= "//label[@id='typo_apptform_building']/../following-sibling::div//input")
    private WebElement building;

    @FindBy(xpath= "//label[@id='typo_apptform_dept_clinic']/../following-sibling::div//input")
    private WebElement department;

    @FindBy(xpath= "//input[@name='patientMrn']")
    private WebElement patientMRN;

    @FindBy(xpath= "//input[@name='pFirstName']")
    private WebElement firstName;

    @FindBy(xpath= "//input[@id='form_patientinfo_lastname']")
    private WebElement lastName;

    @FindBy(xpath= "//input[@name='pDob']")
    private WebElement DOB;

    @FindBy(xpath= "//input[@id='react-select-8-input']")
    private WebElement Gender;

    @FindBy(xpath= "//input[@id='react-select-9-input']")
    private WebElement patientGender;

    @FindBy(xpath= "//label[text()='Requested Language ']/../following-sibling::div//input")
    private WebElement requestedlanguage;

    @FindBy(xpath= "//label[text()='Alternate Language']/../following-sibling::div//input")
    private WebElement alternatelanguage;

    @FindBy(xpath= "//input[@name='pPreferences']")
    private WebElement preferences;

    @FindBy(xpath= "//button[@id='btn_clients_components_Addreq_addappt']")
    private WebElement addAppointment;

    @FindBy(xpath= "//input[@placeholder='Search...']")
    private WebElement searchbar;

    @FindBy(xpath= "//tbody[@class='MuiTableBody-root css-1xnox0e']//tr[@class='MuiTableRow-root css-1pe2zvv'][1]//div[@class='MuiBox-root css-1nxrbxh']")
    private WebElement viewAppointment;

    @FindBy(xpath= "//button[@id='Request Change']")
    private WebElement requestChange;

    @FindBy(xpath= "//textarea[@name='ChangeReason']")
    private WebElement textarea;

    @FindBy(xpath= "//button[text()='send request']")
    private WebElement sendRequest;

    @FindBy(xpath= "//button[@id='Cancellation']")
    private WebElement cancellation;

    @FindBy(xpath= "//button[text()='send request']")
    private WebElement sendCancellation;

    @FindBy(xpath= "//span[text()='MODIFICATION REQUESTS']")
    private WebElement modificationRequest;


    public RequestAppointmentPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }

    public static Map<String,String> fieldXpath = new HashMap<>();

    public String getWebElementOfField(String label){

     return  "";
    }

    public void  createAppointmentFromClient(String type) throws InterruptedException, IOException {

        BaseClass.waitforElementToMakeClickable(clients);
        clients.click();
        Thread.sleep(4000);
        requestAppointment.click();
        Thread.sleep(4000);

        if(type.equals("Medical"))
        medical.click();
        else
          nonmedical.click();

        logger.addScreenCaptureFromPath(takeScreenshotForStep("Before entering details"));
        Appointmentdate.clear();
        Appointmentdate.sendKeys(CommonUtils.getCurrentSystemDate());
        startTime.sendKeys(CommonUtils.addMinutesToCurrentTime(5));
        endTime.sendKeys(CommonUtils.addMinutesToCurrentTime(10));
        client.sendKeys(datasheet.get("Client"));
        Thread.sleep(3000);
        client.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        facility.sendKeys(datasheet.get("Facility"));
        facility.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        appointmenttype.click();
        Thread.sleep(2000);
        appointmenttype.sendKeys(datasheet.get("Appointment Type"));
        appointmenttype.sendKeys(Keys.TAB);
        Thread.sleep(2000);
        building.click();
        building.sendKeys(datasheet.get("Building"));
        building.sendKeys(Keys.TAB);

        Thread.sleep(2000);
        department.click();
        department.sendKeys(datasheet.get("Department"));
        Thread.sleep(2000);
        department.sendKeys(Keys.TAB);
        
        Thread.sleep(3000);
        patientMRN.sendKeys(datasheet.get("Patient MRN"));
        patientMRN.sendKeys(CommonUtils.getRandomNumberOfLength(3));
        patientMRN.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        firstName.sendKeys(datasheet.get("First Name"));
        firstName.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        String ln = datasheet.get("Last Name");
        lastName.sendKeys(ln);
        lastName.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        DOB.click();
        DOB.sendKeys(datasheet.get("DOB"));
        DOB.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        patientGender.sendKeys(datasheet.get("Select Patient Gender"));
        patientGender.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        requestedlanguage.sendKeys(datasheet.get("Requested Language"));
        requestedlanguage.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        alternatelanguage.sendKeys(datasheet.get("Alternate Language"));
        alternatelanguage.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        preferences.sendKeys(datasheet.get("Preferences"));
        preferences.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        WebDriver d = BaseClass.driver;
        JavascriptExecutor js = (JavascriptExecutor)d;
        js.executeScript("arguments[0].scrollIntoView(true);", addAppointment);
        Thread.sleep(3000);
        logger.addScreenCaptureFromPath(takeScreenshotForStep("Before Add"));
        addAppointment.submit();

        Thread.sleep(10000);
        logger.addScreenCaptureFromPath(takeScreenshotForStep("Add Clicked"));
        if(isElementPresent(addAppointment)){
            logger.log(Status.FAIL,"Appointment not created");
            Assert.fail("Appointment not created");
        }
        Thread.sleep(3000);


    }






}


