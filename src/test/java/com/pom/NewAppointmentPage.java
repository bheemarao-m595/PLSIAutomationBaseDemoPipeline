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

    @FindBy(name= "appointments.0.SurgeryAppointment")
    private WebElement Tbd_Checkbox;

    @FindBy(name= "appointments.0.aptDate")
    private WebElement appointmentDate;

    @FindBy(name= "appointments.0.aptStartTime")
    private WebElement appointmentStartTime;

    @FindBy(name= "appointments.0.aptEndTime")
    private WebElement appointmentEndTime;


    @FindBy(id= "react-select-3-input")
    private WebElement client;


    @FindBy(id= "react-select-4-input")
    private WebElement Facility;


    @FindBy(id= "react-select-5-input")
    private WebElement appointmentType;


    @FindBy(id= "react-select-6-input")
    private WebElement building;

    @FindBy(id= "react-select-7-input")
    private WebElement department;



    @FindBy(id= "btn_new_appt")
    private WebElement newAppointment;

    public  void clickClient(){

        client.click();
    }

    public  void facilityClick(){

        Facility.click();
    }

    public  void clickClient(WebElement el){

        el.click();
    }

    public  void clickNewAppointment(){

        try {
            newAppointment.click();
        }catch (Exception e){
            logger.log(Status.INFO,"Got error ");
            throw  e;
        }

    }
    public void addScheduleAppointment() throws InterruptedException {

        appointmentDate.click();
        appointmentDate.sendKeys(BaseClass.datasheet.get("Appointment Date"));
        Thread.sleep(2000);
        appointmentStartTime.click();
        appointmentStartTime.sendKeys(BaseClass.datasheet.get("Appointment Start Time"));
        Thread.sleep(2000);
        appointmentEndTime.click();
        appointmentEndTime.sendKeys(BaseClass.datasheet.get("Appointment End Time"));
        Thread.sleep(2000);
        Tbd_Checkbox.click();
        Thread.sleep(2000);
        client.click();
        Thread.sleep(2000);
        client.sendKeys(datasheet.get("Client"));

        client.sendKeys(Keys.TAB);
        Thread.sleep(2000);
        Facility.click();
        Facility.sendKeys(datasheet.get("Facility"));
        client.sendKeys(Keys.TAB);
        Thread.sleep(2000);

    }
}
