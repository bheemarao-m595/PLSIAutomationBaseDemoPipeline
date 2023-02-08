package com.pom;

import com.base.BaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

import static com.base.BaseClass.datasheet;

public class VG_RequestAppointmentPage {


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
    private WebElement EndTime;


    @FindBy(xpath= "//label[@id='typo_apptform_client']/../following-sibling::div//input")
    private WebElement client;

    @FindBy(xpath= "//label[@id='typo_apptform_facility']/../following-sibling::div//input")
    private WebElement Facility;

    @FindBy(xpath= "//label[@id='typo_apptform_appttype']/../following-sibling::div//input")
    private WebElement AppointmentType;

    @FindBy(xpath= "//label[@id='typo_apptform_building']/../following-sibling::div//input")
    private WebElement Building;

    @FindBy(xpath= "//label[@id='typo_apptform_dept_clinic']/../following-sibling::div//input")
    private WebElement Department;

    @FindBy(xpath= "//input[@name='patientMrn']")
    private WebElement PatientMRN;

    @FindBy(xpath= "//input[@name='pFirstName']")
    private WebElement FirstName;

    @FindBy(xpath= "//input[@id='form_patientinfo_lastname']")
    private WebElement LastName;

    @FindBy(xpath= "//input[@name='pDob']")
    private WebElement DOB;

    @FindBy(xpath= "//input[@id='react-select-8-input']")
    private WebElement Gender;

    @FindBy(xpath= "//input[@id='react-select-9-input']")
    private WebElement PatientGender;

    @FindBy(xpath= "//input[@id='react-select-10-input']")
    private WebElement RequestedLanguage;

    @FindBy(xpath= "//input[@id='react-select-11-input']")
    private WebElement AlternateLanguage;

    @FindBy(xpath= "//input[@name='pPreferences']")
    private WebElement Prefrences;

    @FindBy(xpath= "//button[@id='btn_clients_components_Addreq_addappt']")
    private WebElement AddAppointment;

    @FindBy(xpath= "//input[@placeholder='Search...']")
    private WebElement searchbar;

    @FindBy(xpath= "//tbody[@class='MuiTableBody-root css-1xnox0e']//tr[@class='MuiTableRow-root css-1pe2zvv'][1]//div[@class='MuiBox-root css-1nxrbxh']")
    private WebElement ViewAppointmnet;

    @FindBy(xpath= "//button[@id='Request Change']")
    private WebElement Requestchange1;

    @FindBy(xpath= "//textarea[@name='ChangeReason']")
    private WebElement Textarea;

    @FindBy(xpath= "//button[text()='send request']")
    private WebElement SendRequest;

    @FindBy(xpath= "//button[@id='Cancellation']")
    private WebElement Cancellation;

    @FindBy(xpath= "//button[text()='send request']")
    private WebElement SendCancellation;

    @FindBy(xpath= "//span[text()='MODIFICATION REQUESTS']")
    private WebElement ModificationRequest;


    public VG_RequestAppointmentPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }

    public static Map<String,String> fieldXpath = new HashMap<>();

    public String getWebElementOfField(String label){

     return  "";
    }

    public void     createAppointmentFromClient(String type) throws InterruptedException {

        BaseClass.waitforElementToMakeClickable(clients);
        clients.click();
        Thread.sleep(4000);
        requestAppointment.click();
        Thread.sleep(4000);

        if(type.equals("Medical"))
        medical.click();
        else
          nonmedical.click();
        Appointmentdate.clear();
        Appointmentdate.sendKeys(datasheet.get("Appointment Date"));
        startTime.sendKeys(datasheet.get("Appointment Start Time"));
        EndTime.sendKeys(datasheet.get("Appointment End Time"));
//        client.click();
        client.sendKeys(datasheet.get("Client"));
        Thread.sleep(3000);
        client.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        Facility.sendKeys(datasheet.get("Facility"));
        Facility.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        AppointmentType.click();
        Thread.sleep(2000);
        AppointmentType.sendKeys(datasheet.get("Appointment Type"));
        AppointmentType.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        Building.sendKeys(datasheet.get("Building"));
        Building.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        Department.sendKeys(datasheet.get("Department"));
        Department.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        PatientMRN.sendKeys(datasheet.get("Patient MRN"));
        PatientMRN.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        FirstName.sendKeys(datasheet.get("First Name"));
        FirstName.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        String ln = datasheet.get("Last Name");
        LastName.sendKeys(ln);
        LastName.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        DOB.sendKeys(datasheet.get("DOB"));
        DOB.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        PatientGender.sendKeys(datasheet.get("Select Patient Gender"));
        PatientGender.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        RequestedLanguage.sendKeys(datasheet.get("Requested Language"));
        RequestedLanguage.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        AlternateLanguage.sendKeys(datasheet.get("Alternate Language"));
        AlternateLanguage.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        Prefrences.sendKeys(datasheet.get("Preferences"));
        Prefrences.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        WebDriver d = BaseClass.driver;
        JavascriptExecutor js = (JavascriptExecutor)d;
        js.executeScript("arguments[0].scrollIntoView(true);",AddAppointment);
        Thread.sleep(1000);
        AddAppointment.click();
        searchbar.sendKeys(datasheet.get("First Name"));
        searchbar.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        ViewAppointmnet.click();
        js.executeScript("arguments[0].scrollIntoView(true);",Requestchange1);
        Thread.sleep(3000);
        Requestchange1.click();



//        FirstName.sendKeys(CommonUtils.getRandomStringOfLength(5));
//        System.out.println("hi");


    }






}


