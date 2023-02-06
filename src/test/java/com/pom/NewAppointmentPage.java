package com.pom;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.base.BaseClass.datasheet;
import static com.base.BaseClass.logger;

public class NewAppointmentPage {


    WebDriver wd;
    public  NewAppointmentPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }

    @FindBy(id = "btn_new_appt")
    private WebElement newAppointment;

    @FindBy(name= "appointments.0.SurgeryAppointment")
    private WebElement Tbd_Checkbox;

    @FindBy(name= "appointments.0.aptDate")
    private WebElement appointmentDate;

    @FindBy(name= "appointments.0.aptStartTime")
    private WebElement appointmentStartTime;

    @FindBy(name= "appointments.0.aptEndTime")
    private WebElement appointmentEndTime;


    @FindBy(xpath= "//label[@id='typo_apptform_client']/../following-sibling::div//input")
    private WebElement client;


    @FindBy(xpath= "//label[@id='typo_apptform_facility']/../following-sibling::div//input")
    private WebElement Facility;


    @FindBy(xpath= "//label[@id='typo_apptform_appttype']/../following-sibling::div//input")
    private WebElement appointmentType;


    @FindBy(xpath= "//label[@id='typo_apptform_building']/../following-sibling::div//input")
    private WebElement building;

    @FindBy(xpath= "//label[@id='typo_apptform_dept_clinic']/../following-sibling::div//input")
    private WebElement department;

    @FindBy(id = "form_grid_patientinfo_patientMRN")
    private WebElement patient_Mrn;

    @FindBy(id = "form_patientinfo_firstname")
    private WebElement patient_FName;

    @FindBy(id = "form_patientinfo_lastname")
    private WebElement patient_LName;

    @FindBy(id = "form_patientinfo_DOB")
    private WebElement patient_Dob;

    @FindBy(xpath = "//label[@id='typo_patientinfo_requestedlanguage']/../following-sibling::div//input")
    private WebElement requestedLanguage;

    @FindBy(id= "btn_appointments_setappointment")
    private WebElement setAppointment;



    public  void clickNewAppointment(){

        try {
            setAppointment.click();
        }catch (Exception e){
            logger.log(Status.INFO,"Got error ");
            throw  e;
        }

    }
    public void addScheduleAppointment() throws InterruptedException {

        Thread.sleep(3000);
        newAppointment.click();


        Thread.sleep(3000);
        appointmentDate.click();
        appointmentDate.sendKeys(BaseClass.datasheet.get("Appointment Date"));

        Thread.sleep(2000);
        appointmentStartTime.click();
        appointmentStartTime.sendKeys(BaseClass.datasheet.get("App Start time"));

        Thread.sleep(2000);
        appointmentEndTime.click();
        appointmentEndTime.sendKeys(BaseClass.datasheet.get("App End Time"));

        Thread.sleep(2000);
        Tbd_Checkbox.click();

        Thread.sleep(2000);
        client.click();
        client.sendKeys(datasheet.get("Client"));

        client.sendKeys(Keys.TAB);
        Thread.sleep(2000);
        Facility.click();
        Facility.sendKeys(datasheet.get("Facility"));

        Facility.sendKeys(Keys.TAB);
        Thread.sleep(2000);
        appointmentType.click();
        appointmentType.sendKeys(datasheet.get("App Type"));
        appointmentType.sendKeys(Keys.TAB);

        Thread.sleep(2000);
        building.click();
        building.sendKeys(datasheet.get("Building"));
        building.sendKeys(Keys.TAB);

        Thread.sleep(2000);
        department.click();
        department.sendKeys(datasheet.get("Department"));
        Thread.sleep(2000);
        department.sendKeys(Keys.TAB);

        // building.sendKeys(Keys.TAB);
        Thread.sleep(2000);
        patient_Mrn.click();
        patient_Mrn.sendKeys(BaseClass.datasheet.get("Patient MRN"));

        Thread.sleep(2000);
        patient_FName.click();
        patient_FName.sendKeys(BaseClass.datasheet.get("First Name"));

        Thread.sleep(2000);
        patient_LName.click();
        patient_LName.sendKeys(BaseClass.datasheet.get("Last Name"));

        Thread.sleep(2000);
        patient_Dob.click();
        patient_Dob.sendKeys(BaseClass.datasheet.get("DOB"));


        Thread.sleep(3000);
        requestedLanguage.click();
        requestedLanguage.sendKeys(datasheet.get("Requested Language"));
        requestedLanguage.sendKeys(Keys.TAB);
        Thread.sleep(3000);

        Thread.sleep(2000);
        setAppointment.click();
        System.out.println("Create the appointment");



    }
}
