package com.pom;

import com.base.BaseClass;
import com.utils.CommonUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.validation.Validator;
import java.util.HashMap;
import java.util.Map;

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

    @FindBy(xpath= "//input[@name='pLastName']")
    private WebElement LastName;

    @FindBy(xpath= "//input[@name='pDob']")
    private WebElement DOB;

    @FindBy(xpath= "//input[@id='react-select-7-input']")
    private WebElement Gender;

    @FindBy(xpath= "//input[@id='react-select-8-input']")
    private WebElement RequestedGender;

    @FindBy(xpath= "//input[@id='react-select-9-input']")
    private WebElement RequestedLanguage;

    @FindBy(xpath= "//input[@id='react-select-10-input']")
    private WebElement AlternateLanguage;

    @FindBy(xpath= "//input[@name='pPreferences']")
    private WebElement Prefrences;

    @FindBy(xpath= "//button[@id='btn_clients_components_Addreq_addappt']")
    private WebElement AddAppointment;

    @FindBy(xpath= "//input[@placeholder='Search...']")
    private WebElement searchbar;

    @FindBy(xpath= "//tbody[@class='MuiTableBody-root css-1xnox0e']//tr[@class='MuiTableRow-root css-1pe2zvv'][1]//div[@class='MuiBox-root css-1nxrbxh']")
    private WebElement ViewAppointmnet;

    @FindBy(xpath= "//button[@id='client_component_viewreqappt_Change']")
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


    public RequestAppointmentPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }

    public static Map<String,String> fieldXpath = new HashMap<>();

    public String getWebElementOfField(String label){


  return  "";
    }

    public void createAppointmentFromClient() throws InterruptedException {

        Thread.sleep(6000);
        clients.click();
        Thread.sleep(4000);
        requestAppointment.click();
        Thread.sleep(4000);

        medical.click();
        Appointmentdate.clear();
        Appointmentdate.sendKeys(BaseClass.datasheet.get("Appointment Date"));
        startTime.sendKeys(BaseClass.datasheet.get("Appointment Start Time"));
        EndTime.sendKeys(BaseClass.datasheet.get("Appointment End Time"));
//        client.click();
        client.sendKeys(BaseClass.datasheet.get("Client"));
        Thread.sleep(3000);
        client.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        Facility.sendKeys(BaseClass.datasheet.get("Facility"));
        Facility.sendKeys(Keys.TAB);
        System.out.println("k");
        Thread.sleep(3000);
        AppointmentType.click();
        Thread.sleep(2000);
        AppointmentType.sendKeys(BaseClass.datasheet.get("Appointment Type"));
        AppointmentType.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        FirstName.sendKeys(CommonUtils.getRandomStringOfLength(5));
        System.out.println("hi");


    }





}


