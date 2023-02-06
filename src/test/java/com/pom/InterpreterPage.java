package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InterpreterPage {

    WebDriver wd;

    public InterpreterPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }

    @FindBy(xpath= "//span[text()='Interpreters']")
    private WebElement InterpretersTab;

    @FindBy(xpath= "/html/body/div/div[1]/div/ul/div/div/div/ul/a/li/div/div/span")
    private WebElement Interpretersubtab;

    @FindBy(xpath= "//button[contains(text(),' + add Interpreter')]")
    private WebElement AddInterpreter;

    @FindBy(xpath= "//input[@placeholder='Enter First Name']")
    private WebElement FirstName;

    @FindBy(css= "input[placeholder='Enter Last Name']")
    private WebElement LastName;

    @FindBy(xpath= "//input[@id='react-select-4-input']")
    private WebElement gender;

    @FindBy(xpath= "input[placeholder='Enter Street 1']")
    private WebElement Streetname;

    @FindBy(xpath= "input[placeholder='Enter SMS-Capable Phone Number']")
    private WebElement SMSPhoneNo;

    @FindBy(xpath= "input[placeholder='Enter Landline']")
    private WebElement LandLine;

    @FindBy(xpath= "input[placeholder='Enter Landline']")
    private WebElement City;

}
