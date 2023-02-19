package com.pom;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

import static com.base.BaseClass.logger;

public class FinancePage {
    WebDriver wd;
    public FinancePage(WebDriver d){
        wd = d;
        PageFactory.initElements(d,this);

    }
    @FindBy(xpath= "//button[text()='Financial']")
    private WebElement FinanceTab;
    @FindBy(xpath= "//div[@class='MuiGrid-root MuiGrid-item css-1wxaqej'][1]")
    private WebElement EditAppointmentFields;
    @FindBy(xpath= "//*[text()='Cancellation Time Frame']/../following-sibling::div//input")
    private WebElement calculationDropdown;
    @FindBy(xpath= "//input[@name='NoShow']")
    private WebElement NoShow;
    @FindBy(xpath= "//button[text()='Save']")
    private WebElement SaveCalculation;
    @FindBy(xpath= "//button[@id='btn_viewEditActualInterpreterPayout_editfields']")
    private WebElement editActualpayout;
    @FindBy(xpath= "//input[@placeholder='Enter Interpreter Check In']")
    private WebElement docheckin;
    @FindBy(xpath= "//input[@id='form_actualInterpreterPayOut_actualcheckouttime']")
    private WebElement docheckout;
    @FindBy(xpath= "(//label[text()='Hours Payment Type'])[3]/following-sibling::h6")
    private WebElement paymentType;
    @FindBy(xpath= "//input[@placeholder='Search...']")
    private WebElement search;
    @FindBy(xpath= "//tbody[@class='MuiTableBody-root css-1xnox0e']//tr[2]//td[1]/div/div")
    private WebElement appointmentId;


    public  void editfinance()  {
        FinanceTab.click();
        EditAppointmentFields.click();
    }
    public  void calculationtimeframe(String option) throws InterruptedException {
        calculationDropdown.click();
        calculationDropdown.sendKeys(option);
        Thread.sleep(3500);
        calculationDropdown.sendKeys(Keys.TAB);
    }
    public void noshowbutton(){

        NoShow.click();
    }
    public void calculationsave(){

        SaveCalculation.click();
    }

    public void editFinancePayout() throws IOException {
        BaseClass b = new BaseClass();
        FinanceTab.click();
        editActualpayout.click();
        logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Editing finance"));
    }
    public void editActualPayout(String checkin, String checkout) {
        docheckin.sendKeys(checkin);

        docheckout.sendKeys(checkout);
    }
    public void editcalculation() throws InterruptedException, IOException {

        String[] dropDown = {"Less Than 2 Hours in Advance", "Between 2 and 24 Hours in Advance", "More Than 24 Hours in Advance",
                "Surgery - More Than 2 Days in Advance","Surgery - Less Than 2 Days and Before Surgery Scheduled","Surgery - After Surgery Scheduled"};
        Thread.sleep(2000);
        search.click();
        Thread.sleep(1000);
        search.sendKeys("Completed");
        Thread.sleep(2000);
        appointmentId.click();
        Thread.sleep(1500);
        FinanceTab.click();
        Thread.sleep(1000);

        for (int i=0;i<dropDown.length;i++) {
            Thread.sleep(2000);
            EditAppointmentFields.click();
            Thread.sleep(2000);
            calculationDropdown.click();

            Thread.sleep(2000);
            calculationDropdown.sendKeys(dropDown[i]);
            calculationDropdown.sendKeys(Keys.TAB);
            Thread.sleep(1000);
            SaveCalculation.click();
            Thread.sleep(1000);
            logger.log(Status.PASS,dropDown[i] +"selected and Saved");
            BaseClass b = new BaseClass();
            logger.addScreenCaptureFromPath(b.takeScreenshotForStep(dropDown[i] +"selected and Saved"));


        }

    }
    public String paymenttype(){
        paymentType.getText();
        return null;
    }
    public void save(){
        SaveCalculation.click();

    }


}
