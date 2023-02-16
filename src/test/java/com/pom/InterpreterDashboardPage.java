
package com.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InterpreterDashboardPage {

    WebDriver wd;

    public InterpreterDashboardPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }

    @FindBy(xpath= "//span[text()='OFFERED']")
    private WebElement offeredTab;

    public  void clickOfferedTab(){
        offeredTab.click();
    }

    @FindBy(xpath= "//span[text()='OFFERED']/div")
    private WebElement offeredTabCount;

    public  String getTextOfferedTab(){
        return offeredTabCount.getText();
    }

    @FindBy(xpath= "//span[text()='ACCEPTED']")
    private WebElement acceptedTab;

    public  void clickAcceptedTab(){
        acceptedTab.click();
    }

    @FindBy(xpath= "//span[text()='AVAILABLE']")
    private WebElement AvailableTab;

    @FindBy(xpath= "//input[@class='MuiInputBase-input css-1bqqmdo']")
    private WebElement search;

    public  void enterSearch(String searchFor){
        search.sendKeys(searchFor);
    }

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody")
    private WebElement InterpreterDashboardAppointmentTable;

    public  WebElement get_InterpreterDashboardAppointmentTable(){
        return InterpreterDashboardAppointmentTable;
    }


    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']//tr/td[1]")
    private WebElement InterpreterDashboardAppointmentTableColView;

    public List<WebElement> get_InterpreterDashboardAppointmentTableColView(){
//        return  wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']//tr/td[1]"));
        return  wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']//tr/td[1]/div/div"));

    }

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']//tr[1]/td[12]")
    private WebElement InterpreterDashboardAppointmentTableColStatus;

    public List<WebElement> get_InterpreterDashboardAppointmentTableColStatus(){
        return  wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']//tr[1]/td[12]"));

    }

    @FindBy(xpath= "//p[@class='MuiTypography-root MuiTypography-body1 css-16o4yi7']")
    private WebElement InterpreterDashboardAppointmentClickTitle;

    public  String getTextInterpreterDashboardAppointmentClickTitle(){
        return InterpreterDashboardAppointmentClickTitle.getText();
    }

    @FindBy(xpath= "//button[text()='Decline Appointment']")
    private WebElement DeclineButton;

    public  void clickDeclineButton(){
        DeclineButton.click();
    }

    @FindBy(xpath= "//button[text()='Accept Appointment']")
    private WebElement AcceptButton;

    public  void clickAcceptButton(){
        AcceptButton.click();
    }

    @FindBy(xpath= "//button[text()='Return Appointment']")
    private WebElement returnAppointmentButton;

    public  void hoverReturnAppointmentButton(){

        Actions actions = new Actions(wd);

        actions.moveToElement(returnAppointmentButton).perform();

    }


    public  void clickReturnAppointmentButton(){

               returnAppointmentButton.click();
    }


    @FindBy(xpath= "//div[@class='MuiTooltip-tooltip MuiTooltip-tooltipArrow MuiTooltip-tooltipPlacementTop css-1p9alne']")
    private WebElement HoverTextOnReturnAppointmentButton;

    public boolean isDisplayed_HoverTextOnReturnAppointmentButton(){

        return HoverTextOnReturnAppointmentButton.isDisplayed();

    }
    public  String get_HoverTextOnReturnAppointmentButton(){

     /*   WebElement ele = d.findElement(By.xpath("//button[text()='Return Appointment']"));

        JavascriptExecutor js = (JavascriptExecutor)d;
        js.executeScript("arguments[0].scrollIntoView(true);",ele);

        Actions actions = new Actions(d);

        actions.moveToElement(ele).perform();



        String s = HoverTextOnReturnAppointmentButton.getText();

        System.out.println(s);*/
        return HoverTextOnReturnAppointmentButton.getText();
    }


    @FindBy(xpath= "//button[text()='Check In']")
    private WebElement checkInButton;

    public  void clickCheckInButton(){

        checkInButton.click();
    }

    @FindBy(xpath= "//button[text()='Check Out']")
    private WebElement checkOutButton;

    public  void clickCheckOutButton(){

        checkOutButton.click();
    }

    @FindBy(xpath= "//p[text()='Finalize Appointment']")
    private WebElement FinalizeAppointmentPageTitle;

    @FindBy(xpath= "//div[@class='MuiBox-root css-1esysrx']/div/div[2]/button")
    private WebElement finalizeAppointmentButton;

    public  void clickFinalizeAppointmentButton(){

        finalizeAppointmentButton.click();
    }

    @FindBy(xpath= "//p[text()='View Financial Breakdown - undefined']/button")
    private WebElement finalizeAppointmentPageCrossButton;

    public  void clickFinalizeAppointmentPageCrossButton(){

        finalizeAppointmentPageCrossButton.click();
    }

    @FindBy(xpath= "//p[text()='View Financial Breakdown - undefined']")
    private WebElement FinancialBreakDownPageTitle;

    @FindBy(xpath= "//button[text()='View Financial Breakdown']")
    private WebElement financialBreakDownButton;

    public  void clickFinancialBreakDownButton(){

        financialBreakDownButton.click();
    }

    @FindBy(xpath= "//p[text()='View Financial Breakdown - undefined']/button")
    private WebElement financialBreakDownPageCrossButton;

    public  void clickFinancialBreakDownPageCrossButton(){

        financialBreakDownPageCrossButton.click();
    }

    @FindBy(xpath= "//p[ contains (text(),'Appointment -')]")
    private WebElement AppointmentPageTitle;

    @FindBy(xpath= "//p[ contains (text(),'Appointment -')]/button")
    private WebElement appointmentCrossButton;
    public  void clickAppointmentCrossButton(){

         appointmentCrossButton.click();
    }

    @FindBy(xpath= "//button[text()='EDIT INTERPRETER']")
    private WebElement EditInterpreter;

    @FindBy(xpath= "//span[text()='Can Self-Book for Appointments']")
    private WebElement CanSelfBookAppointment;

    @FindBy(xpath= "//button[text()='Cancel']")
    private WebElement Cancel;

    @FindBy(xpath= "//button[text()='close']")
    private WebElement Close;


    @FindBy(xpath= "//input[contains(@id,'react-select')]")
    private WebElement finalizeAppointmentDropdownsList;
    public List<WebElement> get_FinalizeAppointmentDropdownsList(){
        return  wd.findElements(By.xpath("//input[contains(@id,'react-select')]"));

    }


    }



