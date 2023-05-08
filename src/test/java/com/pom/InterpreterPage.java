package com.pom;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.utils.CommonUtils;
import com.utils.DashBoardHeaders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import  static com.base.BaseClass.logger;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class InterpreterPage {

    WebDriver wd;

    public InterpreterPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }

    public  void searchApps(String key) throws InterruptedException {
        search.click();
        Thread.sleep(1000);
        search.sendKeys(key);
    }

    @FindBy(xpath= "//input[@placeholder='Search...']")
    private WebElement search;

    @FindBy(xpath= "//div[@class='MuiBox-root css-1ygsm17']//table")
    private WebElement tableLanguageProficiencyList;

    @FindBy(xpath= "//div[@class='MuiBox-root css-1ygsm17']//table/tbody/tr/td[2]")
    private WebElement tableLanguageProficiencyList_Col_Language;

    @FindBy(xpath = "//span[contains(text(),'URGENT')]")
    private WebElement urgentTab;

    @FindBy(xpath= "//tbody[@class='MuiTableBody-root css-1xnox0e']//tr[2]//td[1]/div/div")
    private WebElement appointment;

    @FindBy(xpath= "//button[@id='Accept']")
    private WebElement Accept;

    @FindBy(xpath= "//a[@href='/interpreter/interpreter-home']")
    private WebElement IntrepreterTab;
    @FindBy(xpath= "//button[@class='MuiButtonBase-root MuiTab-root MuiTab-textColorPrimary css-pqhchs'][1]")
    private WebElement OfferedTab;
    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']/tbody/tr[1]/td[1]/div/div")
    private WebElement AppointmentOffered;
    @FindBy(xpath= "//button[@id='Accept']")
    private WebElement AcceptTab;

    @FindBy(xpath= "//img[@class='MuiBox-root css-1iulk6e']/..")
    private WebElement findInterpreter;

    @FindBy(xpath= "//span[text()='make an offer']")
    private WebElement makeAnOffer;

    @FindBy(xpath= "//*[local-name()='svg' and @data-testid='ReplayIcon']/*[local-name()='path']")
    private WebElement rescindOfferbutton;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']/tbody/tr[1]/td[7]")
    private WebElement PatientConsumer;

    @FindBy(xpath= "//span[text()='INTERPRETER MATCHING']/..")
    private WebElement InterpreterMatchingTab;


    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']")
    private WebElement tableInterpreterList;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']//thead/tr/th/div")
    private WebElement tableInterpreterListColumnNames;

    public List<WebElement> tableInterpreterListColumnNames() {
        return wd.findElements(By.xpath("//table[@class='MuiTable-root css-v6tz28']//thead/tr/th/div"));
    }

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']/tbody")
    private WebElement tableInterpreterListBody;


    @FindBy(xpath= "//span[text()='APPOINTMENTS']")
    private WebElement tabAppointments;


    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']/tbody/tr/td[1]")
    private WebElement allAppointmentTableBodyRowsViewInterpretercolumn;

    @FindBy(xpath="//div[@class='MuiBox-root css-g53dgy'][1]")
    private WebElement firstInterpreterViewInList;

    @FindBy(xpath= "//div[@class='MuiBox-root css-xj7u6x']//table/tbody/tr/td[7]")
    private WebElement tableAppointmentList_Col_Language;

    @FindBy(xpath= "//span[text()='LANGUAGE PROFICIENCY']")
    private WebElement tabLanguageProficiency;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']/tbody/tr/td")
    private WebElement allAppointmentTableBody_td;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']")
    private WebElement allAppointmentTableBody_tr;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']")
    private WebElement noResults;

    @FindBy(xpath= "//div[@class='MuiBox-root css-xj7u6x']//table")
    private WebElement tableAppointmentsList;

    @FindBy(xpath= "//div[@class='MuiBox-root css-xj7u6x']//input[@placeholder='Search...']")
    private WebElement tableAppointmentsListSearch;

    @FindBy(xpath= "//div[@class='MuiBox-root css-xj7u6x']//table/thead/tr/th/div")
    private WebElement tableAppointmentsListColumnNames;

    @FindBy(xpath= "//div[@class='MuiBox-root css-xj7u6x']//table/tbody")
    private WebElement tableAppointmentsListBody;


    public void clickFirstInterpreterViewInList(){
       boolean b = BaseClass.isElementPresent(firstInterpreterViewInList);
       if(b)
        firstInterpreterViewInList.click();
       else
           Assert.fail("interpreter record not found");
    }

    public List<WebElement> allAppointmentTableBodyRowsViewInterpretercolumn() {
        return wd.findElements(By.xpath("//table[@class='MuiTable-root css-v6tz28']/tbody/tr/td[1]"));
    }

    public By allAppointmentTableBody_tr() {
        return By.xpath("//table[@class='MuiTable-root css-v6tz28']/tbody/tr");
    }

    public boolean isDisplayed_noResults(){

        return noResults.isDisplayed();

    }

    public void clickTabAppointments() throws InterruptedException {
        Thread.sleep(2000);
        tabAppointments.click();
    }


    public void clickTabLanguageProficiency() {

        tabLanguageProficiency.click();
    }


    public void enterTableAppointmentsListSearch(String val) {

        tableAppointmentsListSearch.sendKeys(val);
    }

    public List<WebElement> tableAppointmentsListColumnNames() {

        return wd.findElements(By.xpath("//div[@class='MuiBox-root css-xj7u6x']//table/thead/tr/th/div"));

    }

    public WebElement tableAppointmentsListBody() {

        return tableAppointmentsListBody;

    }

    public List<WebElement> tableAppointmentList_Col_Language() {

        return wd.findElements(By.xpath("//div[@class='MuiBox-root css-xj7u6x']//table/tbody/tr/td[7]"));
    }

    public List<WebElement> tableLanguageProficiencyList_Col_Language() {
        return wd.findElements(By.xpath("//div[@class='MuiBox-root css-1ygsm17']//table/tbody/tr/td[2]"));
    }

    public  void clickUrgent() throws InterruptedException {
        urgentTab.click();
        Thread.sleep(4000);
    }

    public void clickAppointmentId() throws InterruptedException {

        boolean isTablePresent = BaseClass.isElementPresent(appointment);
        Assert.assertTrue(isTablePresent,"Empty table");
        appointment.click();
        Thread.sleep(3000);
    }

    public   void enterSearch(String val){

        search.sendKeys(val);
    }

    public  void interpreterAccept() throws InterruptedException, IOException {

        IntrepreterTab.click();
        Thread.sleep(2000);
        OfferedTab.click();
        Thread.sleep(2000);
        logger.addScreenCaptureFromPath(new BaseClass().takeScreenshotForStep("Offer Tab"));
        Thread.sleep(2000);
        AppointmentOffered.click();
        Thread.sleep(2000);
        AcceptTab.click();
        logger.addScreenCaptureFromPath(new BaseClass().takeScreenshotForStep("Offer Accepted"));
        Thread.sleep(2000);
    }

    public void interpreterRescind() throws InterruptedException, IOException {

        BaseClass.waitforElementToMakeClickable(InterpreterMatchingTab);
        BaseClass.clickWithJavaScript(InterpreterMatchingTab);
        Thread.sleep(2000);
        findInterpreter.click();
        Thread.sleep(3000);
        if(BaseClass.isElementPresent(rescindOfferbutton)) {
            BaseClass.goToElementVisibleArea(rescindOfferbutton);
            rescindOfferbutton.click();
        }
       logger.addScreenCaptureFromPath(new BaseClass().takeScreenshotForStep("Offer Rescined"));
        Thread.sleep(2000);
    }

    public void makeAnOfferClickForInterpreterName(String name) throws InterruptedException, IOException {

        BaseClass b = new BaseClass();
        if(BaseClass.isElementPresent(InterpreterMatchingTab))
            InterpreterMatchingTab.click();

        logger.addScreenCaptureFromPath(b.takeScreenshotForStep("InterpreterMatching Tab clicked"));
        Thread.sleep(2000);
        findInterpreter.click();
        logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Find Interpreter Clicked"));
        Thread.sleep(3000);
        WebElement makeAnOfferLinkForInterpreter = new BaseClass().getElementByXpath(wd,"//div[text()='"+ name  +"']/../following-sibling::td[5]");
        makeAnOfferLinkForInterpreter.click();
        logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Make an Offer Clicked"));
        Thread.sleep(2000);
    }

    public String makeAnOfferforAny() throws InterruptedException, IOException {
        BaseClass b = new BaseClass();
        logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Details Tab"));
        if(BaseClass.isElementPresent(InterpreterMatchingTab))
            InterpreterMatchingTab.click();
        logger.addScreenCaptureFromPath(b.takeScreenshotForStep("InterpreterMatching Tab clicked"));
        logger.log(Status.PASS,"Matching Tab clicked");
        Thread.sleep(2000);
        findInterpreter.click();
        Thread.sleep(3000);
        logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Find Interpreter Clicked"));
        logger.log(Status.PASS,"Find Interpreter Clicked");
          WebElement interPreterFirstemail = new BaseClass().getElementByXpath(wd,"//span[text()='make an offer']/../../../preceding-sibling::td[4]/div");
         String firstEmail  = interPreterFirstemail.getText();
         makeAnOffer.click();
          logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Make an Offer Clicked"));
        logger.log(Status.PASS,"Make an Offer Clicked");


        Thread.sleep(2000);
        return firstEmail;
    }
}



