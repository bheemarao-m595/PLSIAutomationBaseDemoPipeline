package com.pom;

import com.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public  void fecilityClick(){

        Facility.click();
    }

    public  void clickClient(WebElement el){

        el.click();
    }

    public  void clickNewAppointment(){

        newAppointment.click();
    }
}
