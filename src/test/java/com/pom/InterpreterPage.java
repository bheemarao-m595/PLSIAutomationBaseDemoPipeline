package com.pom;

import com.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InterpreterPage {

    WebDriver wd;
    @FindBy(xpath = "//span[contains(text(),'URGENT')]")
    private WebElement urgentTab;


    @FindBy(xpath= "//tbody[@class='MuiTableBody-root css-1xnox0e']//tr[1]//td[1]/div/div")
    private WebElement appointment;

        @FindBy(xpath= "//span[text()='INTERPRETER MATCHING']/..")
    private WebElement InterpreterMatchingTab;

    @FindBy(xpath= "//img[@class='MuiBox-root css-1iulk6e']/..")
    private WebElement findInterpreter;

    @FindBy(xpath= "//span[text()='make an offer']")
    private WebElement makeAnOffer;

    @FindBy(xpath= "//*[local-name()='svg' and @data-testid='ReplayIcon']/*[local-name()='path']")
    private WebElement rescindOfferbutton;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr[1]/td[7]")
    private WebElement PatientConsumer;

    @FindBy(xpath= "//div[@class='MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-multiline css-116j8tg' ]//*[@placeholder='Enter relevant preferences']")
    private WebElement preference;

    @FindBy(xpath= "//div[@class='MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-multiline css-116j8tg' ]//*[@placeholder='Enter relevant comments']")
    private WebElement requester;

    @FindBy(xpath= "//div[@class='MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-multiline css-116j8tg' ]//*[@placeholder='Enter relevant notes']")
    private WebElement scheduler_notes;

    @FindBy(xpath= "//button[text()=' Save']")
    private WebElement Save;

    @FindBy(xpath= "//li[@class='MuiListItem-root MuiListItem-padding css-1u6s8c4'][6]")
    private WebElement Home_Intrepreter1;

    @FindBy(xpath= "//li[@class='MuiListItem-root MuiListItem-padding css-9bln5v']")
    private WebElement Intrepreter;

    @FindBy(xpath= "//button[@class='MuiButtonBase-root MuiTab-root MuiTab-textColorPrimary css-1auuvg7'][1]")
    private WebElement Offered;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr[1]/td[count(//table[@class='MuiTable-root css-jiyur0']/thead/tr/th/div[text()='View'])]")
    private WebElement Appointment;

    @FindBy(xpath= "//button[@id='Accept']")
    private WebElement Accept;

    @FindBy(xpath= "//a[@href='/interpreter/interpreter-home']")
    private WebElement IntrepreterTab;
    @FindBy(xpath= "//button[@class='MuiButtonBase-root MuiTab-root MuiTab-textColorPrimary css-1auuvg7'][1]")
    private WebElement OfferedTab;
    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr[1]/td[count(//table[@class='MuiTable-root css-jiyur0']/thead/tr/th/div[text()='View'])]")
    private WebElement AppointmentOffered;
    @FindBy(xpath= "//button[@id='Accept']")
    private WebElement AcceptTab;




    public WebElement Save(WebDriver driver) {
        return driver.findElement(By.xpath("//button[text()=' Save']"));
    }

    public InterpreterPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }
    public  void clickUrgent() throws InterruptedException {
        urgentTab.click();
        Thread.sleep(4000);
    }
    public void clickAppointmentId()
    {

        System.out.println("hi");
            appointment.click();
            System.out.println("hi");
    }
    public void makeAnOfferClick() throws InterruptedException {

        if(BaseClass.isElementPresent(InterpreterMatchingTab))
            InterpreterMatchingTab.click();
        Thread.sleep(3000);
        findInterpreter.click();
        makeAnOffer.click();
    }
    public void interpreterRescind() throws InterruptedException {


             BaseClass.waitforElementToMakeClickable(InterpreterMatchingTab);
             BaseClass.clickWithJavaScript(InterpreterMatchingTab);
           Thread.sleep(3000);
           findInterpreter.click();
          Thread.sleep(3000);
           if(BaseClass.isElementPresent(rescindOfferbutton))
            rescindOfferbutton.click();

           Thread.sleep(3000);
    }
    public  void interpreterAccept()  {
        IntrepreterTab.click();
        OfferedTab.click();
        AppointmentOffered.click();

        BaseClass.goToElementVisibleArea(AcceptTab);
        AcceptTab.click();
    }
    public  void updatePatientName()  {
        PatientConsumer.click();
        preference.sendKeys("Test Data");
        requester.sendKeys("Test Data");
        scheduler_notes.sendKeys("Test Data");
        Save.click();

    }

}


