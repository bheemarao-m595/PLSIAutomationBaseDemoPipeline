package com.pom;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.utils.CommonUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.Map;

import static com.base.BaseClass.*;

public class NewAppointmentPage {


    WebDriver wd;
    BaseClass b = new BaseClass();
    public  NewAppointmentPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }


    @FindBy(id = "btn_appointments_cancel")
    private WebElement appCancel;

    @FindBy(css= "input[placeholder='Search...']")
    private WebElement Search;

    @FindBy(css= "/html/body/div/div[3]/div/div/div[2]/table/tbody/tr/td[1]")
    private WebElement selectView;

    @FindBy(css= "//img[@class='MuiBox-root css-1vwda0p']")
    private WebElement editAppo;

    @FindBy(css= "//input[@name='AppointmentstartTime']")
    private WebElement appointmentstarttime1;

    @FindBy(xpath= "//input[@name='AppointmentEndTime']")
    private WebElement appointmentendtime1;

    @FindBy(xpath= "//input[@id='react-select-17-input']")
    private WebElement Client1;

    @FindBy(css= "#react-select-22-input")
    private WebElement Requestedlanguage1;

    @FindBy(xpath= "//button[@type='submit']")
    private WebElement savebutton;

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

    @FindBy(xpath= "//button[@id='btn_appointments_setappointment']")
    private WebElement setAppointmentButton;

    @FindBy(id = "rad_nonmedical_appt")
    private WebElement Non_Medical;

    @FindBy(xpath = "//tbody[@class='MuiTableBody-root css-1xnox0e']//tr[1]//td[1]")
    private WebElement click_View;

    @FindBy(id = "btn_viewAppointment_editappt")
    private WebElement clickeditApp;

    @FindBy(id= "form_editgeneralInfo_starttime")
    private WebElement updateAppStartTime;

    @FindBy(id= "form_editgeneralInfo_endtime")
    private WebElement updateAppEndTime;

    @FindBy(id = "form_editcaredetails_physicianname")
    private WebElement care_Physician;

    @FindBy(id = "tbn_viewAppointment_update")
    private WebElement saveupdate;

    @FindBy(xpath = "(//input[@id='form_apptdae'])[2]")
    private WebElement appointmentDate1;

    @FindBy(xpath = "(//input[@id='form_apptdae'])[3]")
    private WebElement appointmentDate2;


    @FindBy(xpath = "(//input[@id='form_appt_starttime'])[2]")
    private WebElement appointmentStartTime1;

    @FindBy(xpath = "(//input[@id='form_appt_starttime'])[3]")
    private WebElement appointmentStartTime2;

    @FindBy(xpath= "(//input[@id='form_appt_endtime'])[2]")
    private WebElement appointmentEndTime1;

    @FindBy(xpath= "(//input[@id='form_appt_endtime'])[3]")
    private WebElement appointmentEndTime2;

    @FindBy (id = "cb_recurringappts")
    private WebElement recurringAppointment;

    @FindBy (id = "btn_addmoredates")
    private WebElement addMoreDates;

    @FindBy(xpath = "//div[@class='MuiBox-root css-1ixrlsg']")
    private WebElement toastMessage;



    public  void clickNewAppointment(){

        try {
            setAppointmentButton.click();
        }catch (Exception e){
            logger.log(Status.INFO,"Got error ");
            throw  e;
        }

    }
    public String scheduleAppointment(String appInfo) throws InterruptedException, IOException {

        Thread.sleep(3000);
        newAppointment.click();
        Thread.sleep(3000);
        if(!appInfo.equalsIgnoreCase("Medical"))
            Non_Medical.click();

        logger.log(Status.PASS,"Appointment Table");
        Thread.sleep(3000);
        appointmentDate.click();
        appointmentDate.sendKeys(CommonUtils.getCurrentSystemDate());

        Thread.sleep(2000);
        appointmentStartTime.click();
        appointmentStartTime.sendKeys(CommonUtils.addMinutesToCurrentTime(5));

        Thread.sleep(2000);
        appointmentEndTime.click();
        appointmentEndTime.sendKeys(CommonUtils.addMinutesToCurrentTime(10));
        logger.log(Status.PASS,"Time entered");

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

        Thread.sleep(2000);
        patient_Mrn.click();
        patient_Mrn.sendKeys(CommonUtils.getRandomNumberOfLength(4));

        Thread.sleep(2000);
        patient_FName.click();
        patient_FName.sendKeys(datasheet.get("First Name"));

        String randomName = "NC";
        randomName = CommonUtils.getRandomStringOfLength(3);
        randomName = datasheet.get("Last Name")+  "_" + randomName;
        patient_LName.click();
        patient_LName.sendKeys(randomName);

        Thread.sleep(2000);
        patient_Dob.click();
        patient_Dob.sendKeys(datasheet.get("DOB"));


        Thread.sleep(2000);
        requestedLanguage.click();
        requestedLanguage.sendKeys(datasheet.get("Requested Language"));
        requestedLanguage.sendKeys(Keys.TAB);
        Thread.sleep(1000);

        setAppointmentButton.click();
        logger.log(Status.PASS,"Set Appointment clicked");
        Thread.sleep(4000);
        logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Appointment created"));

        Thread.sleep(4000);
        if(BaseClass.isElementPresent(setAppointmentButton))
            return "NC";
        else
            return randomName;

    }


    public String addScheduleAppointment(Map<String,String> creationData) throws InterruptedException, IOException {

        Thread.sleep(2000);
        newAppointment.click();

        Thread.sleep(2000);
        if(!isElementPresent(appointmentDate)){
             return  "NC";
        }
        appointmentDate.click();
        appointmentDate.sendKeys(CommonUtils.getCurrentSystemDate());

        Thread.sleep(1000);
        appointmentStartTime.click();
        appointmentStartTime.sendKeys(CommonUtils.addMinutesToCurrentTime(5));

        Thread.sleep(1000);
        appointmentEndTime.click();
        appointmentEndTime.sendKeys(CommonUtils.addMinutesToCurrentTime(10));

        Thread.sleep(1000);
        Tbd_Checkbox.click();

        Thread.sleep(1000);
        client.click();
        client.sendKeys(creationData.get("Client"));

        client.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        Facility.click();
        Facility.sendKeys(creationData.get("Facility"));

        Facility.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        appointmentType.click();
        appointmentType.sendKeys(creationData.get("App Type"));
        appointmentType.sendKeys(Keys.TAB);

        Thread.sleep(1000);
        building.click();
        building.sendKeys(creationData.get("Building"));
        building.sendKeys(Keys.TAB);

        Thread.sleep(1000);
        department.click();
        department.sendKeys(creationData.get("Department"));
        Thread.sleep(1000);
        department.sendKeys(Keys.TAB);

        // building.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        patient_Mrn.click();
        patient_Mrn.sendKeys(CommonUtils.getRandomNumberOfLength(4));

        Thread.sleep(1000);
        patient_FName.click();
        patient_FName.sendKeys(creationData.get("First Name"));

        Thread.sleep(1000);
        patient_LName.click();
        String randomName = "NNCY";
        randomName = CommonUtils.getRandomStringOfLength(3);
        randomName = creationData.get("Last Name")+  "_" + randomName;
        patient_LName.sendKeys(randomName);

        Thread.sleep(1000);
        patient_Dob.click();
        patient_Dob.sendKeys(creationData.get("DOB"));


        Thread.sleep(1000);
        requestedLanguage.click();
        requestedLanguage.sendKeys(creationData.get("Requested Language"));
        requestedLanguage.sendKeys(Keys.TAB);
        Thread.sleep(1000);

        Thread.sleep(1000);
        BaseClass.goToElementVisibleArea(setAppointmentButton);
        setAppointmentButton.click();
        logger.log(Status.PASS,"Set Appointment clicked");
        logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Set Appointment clicked"));
        Thread.sleep(1000);
       if(BaseClass.isElementPresent(setAppointmentButton))
        return "NC";
       else
           return randomName;

    }

    public void clickEdit(){
        editAppo.click();

    }

    public  void editAppointment() throws InterruptedException, IOException {

        Thread.sleep(3000);
        WebDriver d = BaseClass.driver;
        JavascriptExecutor js = (JavascriptExecutor)d;
        js.executeScript("arguments[0].scrollIntoView(true);",clickeditApp);

        Thread.sleep(2000);
        clickeditApp.click();

        Thread.sleep(3000);
        WebDriver d1 = BaseClass.driver;
        JavascriptExecutor js1 = (JavascriptExecutor)d1;
        Thread.sleep(3000);
        js1.executeScript("arguments[0].scrollIntoView(true);",updateAppStartTime);


        Thread.sleep(1000);
        updateAppStartTime.click();
        updateAppStartTime.sendKeys(datasheet.get("App Start time"));

        Thread.sleep(1000);
        updateAppEndTime.click();
        updateAppEndTime.sendKeys(datasheet.get("App End Time"));

        Thread.sleep(1000);
        care_Physician.click();
        care_Physician.clear();
        care_Physician.sendKeys(datasheet.get("Physician Name") + "_Edited");

        Thread.sleep(1000);
        saveupdate.click();
        Thread.sleep(1000);
        logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Edited"));


    }

    public void cancelAppointment() throws InterruptedException, IOException {

        Thread.sleep(3000);
        newAppointment.click();

        Thread.sleep(2000);
        appointmentDate.click();
        appointmentDate.sendKeys(CommonUtils.getCurrentSystemDate());

        Thread.sleep(2000);
        appointmentStartTime.click();
        appointmentStartTime.sendKeys(CommonUtils.addMinutesToCurrentTime(4));

        Thread.sleep(2000);
        appointmentEndTime.click();
        appointmentEndTime.sendKeys(CommonUtils.addMinutesToCurrentTime(10));

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

        Thread.sleep(1000);
        building.click();
        building.sendKeys(datasheet.get("Building"));
        building.sendKeys(Keys.TAB);

        Thread.sleep(1000);
        department.click();
        department.sendKeys(datasheet.get("Department"));
        Thread.sleep(1000);
        department.sendKeys(Keys.TAB);

        // building.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        patient_Mrn.click();
        patient_Mrn.sendKeys(CommonUtils.getRandomNumberOfLength(4));

        Thread.sleep(1000);
        patient_FName.click();
        patient_FName.sendKeys(datasheet.get("First Name"));

        Thread.sleep(1000);
        patient_LName.click();
        patient_LName.sendKeys(datasheet.get("Last Name")+ CommonUtils.getRandomStringOfLength(3));

        Thread.sleep(1000);
        patient_Dob.click();
        patient_Dob.sendKeys(datasheet.get("DOB"));


        Thread.sleep(1000);
        requestedLanguage.click();
        requestedLanguage.sendKeys(datasheet.get("Requested Language"));
        requestedLanguage.sendKeys(Keys.TAB);

        logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Filled to Cancel"));

        appCancel.click();
        Thread.sleep(2000);

    }


    public String recurringAppointment(Map<String,String> creationData) throws InterruptedException, IOException {

        BaseClass b = new BaseClass();
        Thread.sleep(3000);
        newAppointment.click();

        Thread.sleep(3000);
        appointmentDate.click();
        appointmentDate.sendKeys(CommonUtils.getCurrentSystemDate());

        Thread.sleep(2000);
        appointmentStartTime.click();
        appointmentStartTime.sendKeys(CommonUtils.addMinutesToCurrentTime(5));

        Thread.sleep(1000);
        appointmentEndTime.click();
        appointmentEndTime.sendKeys(CommonUtils.addMinutesToCurrentTime(10));

        Thread.sleep(1000);
        recurringAppointment.click();

        Thread.sleep(1000);
        addMoreDates.click();

        Thread.sleep(3000);
        appointmentDate1.click();
        appointmentDate1.sendKeys(CommonUtils.getCurrentSystemDate());

        Thread.sleep(1000);
        appointmentStartTime1.click();
        Thread.sleep(1000);
        appointmentStartTime1.sendKeys(CommonUtils.addMinutesToCurrentTime(12));
        Thread.sleep(500);
        appointmentStartTime1.sendKeys(Keys.TAB);


        Thread.sleep(1000);
        appointmentEndTime1.click();
        Thread.sleep(2000);
        appointmentEndTime1.sendKeys(CommonUtils.addMinutesToCurrentTime(15));
        Thread.sleep(1000);
        appointmentEndTime1.sendKeys(Keys.TAB);
        logger.addScreenCaptureFromPath(BaseClass.takeScreenshotForStep("Recurring times entered"));
        logger.log(Status.PASS,"Recurring time entered");

        Thread.sleep(1000);
        addMoreDates.click();

        Thread.sleep(3000);
        appointmentDate2.click();
        Thread.sleep(1000);
        appointmentDate2.sendKeys(CommonUtils.getCurrentSystemDate());

        Thread.sleep(2000);
        appointmentStartTime2.click();
        Thread.sleep(1000);
        appointmentStartTime2.sendKeys(CommonUtils.addMinutesToCurrentTime(15));
        Thread.sleep(500);
        appointmentStartTime2.sendKeys(Keys.TAB);

        Thread.sleep(1000);
        appointmentEndTime2.click();
        Thread.sleep(1000);
        appointmentEndTime2.sendKeys(CommonUtils.addMinutesToCurrentTime(20));
        Thread.sleep(1000);
        appointmentEndTime2.sendKeys(Keys.TAB);

        Thread.sleep(1000);
        client.click();
        client.sendKeys(creationData.get("Client"));

        client.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        Facility.click();
        Facility.sendKeys(creationData.get("Facility"));

        Facility.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        appointmentType.click();
        appointmentType.sendKeys(creationData.get("App Type"));
        appointmentType.sendKeys(Keys.TAB);

        Thread.sleep(1000);
        building.click();
        building.sendKeys(creationData.get("Building"));
        building.sendKeys(Keys.TAB);

        Thread.sleep(1000);
        department.click();
        department.sendKeys(creationData.get("Department"));
        Thread.sleep(1000);
        department.sendKeys(Keys.TAB);

        // building.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        patient_Mrn.click();
        patient_Mrn.sendKeys(CommonUtils.getRandomNumberOfLength(4));

        Thread.sleep(1000);
        patient_FName.click();
        patient_FName.sendKeys(creationData.get("First Name"));

        Thread.sleep(1000);
        patient_LName.click();
        String randomName = "NNCY";
        randomName = CommonUtils.getRandomStringOfLength(3);
        randomName = creationData.get("Last Name")+  "_" + randomName;
        patient_LName.sendKeys(randomName);

        Thread.sleep(1000);
        patient_Dob.click();
        patient_Dob.sendKeys(creationData.get("DOB"));


        Thread.sleep(1000);
        requestedLanguage.click();
        requestedLanguage.sendKeys(creationData.get("Requested Language"));
        requestedLanguage.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        logger.addScreenCaptureFromPath(BaseClass.takeScreenshotForStep("Edited Requested Language"));

        Thread.sleep(1000);
        BaseClass.goToElementVisibleArea(setAppointmentButton);
        setAppointmentButton.click();

        Thread.sleep(1000);
        if(BaseClass.isElementPresent(setAppointmentButton))
        {
            logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Recurring Appointment not created"));
            return "NC";
        }
        else {
            logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Recurring Appointment created"));
            return randomName;
        }

    }



}
