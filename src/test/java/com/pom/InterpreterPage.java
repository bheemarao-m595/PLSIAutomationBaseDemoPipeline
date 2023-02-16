package com.pom;

import com.base.BaseClass;
import com.utils.CommonUtils;
import com.utils.DashBoardHeaders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(xpath= "//tbody[@class='MuiTableBody-root css-1xnox0e']//tr[1]//td[1]/div/div")
    private WebElement appointment;

    @FindBy(xpath= "//button[@id='Accept']")
    private WebElement Accept;

    @FindBy(xpath= "//a[@href='/interpreter/interpreter-home']")
    private WebElement IntrepreterTab;
    @FindBy(xpath= "//button[@class='MuiButtonBase-root MuiTab-root MuiTab-textColorPrimary css-1auuvg7'][1]")
    private WebElement OfferedTab;
    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr[1]/td[1]/div/div")
    private WebElement AppointmentOffered;
    @FindBy(xpath= "//button[@id='Accept']")
    private WebElement AcceptTab;

    @FindBy(xpath= "//img[@class='MuiBox-root css-1iulk6e']/..")
    private WebElement findInterpreter;

    @FindBy(xpath= "//span[text()='make an offer']")
    private WebElement makeAnOffer;

    @FindBy(xpath= "//*[local-name()='svg' and @data-testid='ReplayIcon']/*[local-name()='path']")
    private WebElement rescindOfferbutton;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr[1]/td[7]")
    private WebElement PatientConsumer;

    @FindBy(xpath= "//span[text()='INTERPRETER MATCHING']/..")
    private WebElement InterpreterMatchingTab;


    public   void enterSearch(String val){

        search.sendKeys(val);


    }

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']")
    private WebElement tableInterpreterList;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']//thead/tr/th/div")
    private WebElement tableInterpreterListColumnNames;

    public List<WebElement> tableInterpreterListColumnNames() {
        return wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']//thead/tr/th/div"));
    }

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody")
    private WebElement tableInterpreterListBody;

    public  WebElement get_tableInterpreterListBody(){
        return tableInterpreterListBody;
    }

    @FindBy(xpath= "//span[text()='APPOINTMENTS']")
    private WebElement tabAppointments;

    public void clickTabAppointments() {
        tabAppointments.click();
    }

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[1]")
    private WebElement allAppointmentTableBodyRowsViewInterpretercolumn;

    public List<WebElement> allAppointmentTableBodyRowsViewInterpretercolumn() {
        return wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[1]"));
    }

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td")
    private WebElement allAppointmentTableBody_td;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']")
    private WebElement allAppointmentTableBody_tr;
    public By allAppointmentTableBody_tr() {
        return By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr");
    }
    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']")
    private WebElement noResults;

    public boolean isDisplayed_noResults(){

        return noResults.isDisplayed();

    }


    @FindBy(xpath= "//span[text()='LANGUAGE PROFICIENCY']")
    private WebElement tabLanguageProficiency;
    public void clickTabLanguageProficiency() {
        tabLanguageProficiency.click();
    }
    @FindBy(xpath= "//div[@class='MuiBox-root css-xj7u6x']//table")
    private WebElement tableAppointmentsList;

    @FindBy(xpath= "//div[@class='MuiBox-root css-xj7u6x']//input[@placeholder='Search...']")
    private WebElement tableAppointmentsListSearch;
    public void enterTableAppointmentsListSearch(String val) {

        tableAppointmentsListSearch.sendKeys(val);
    }
    @FindBy(xpath= "//div[@class='MuiBox-root css-xj7u6x']//table/thead/tr/th/div")
    private WebElement tableAppointmentsListColumnNames;
    public List<WebElement> tableAppointmentsListColumnNames() {

        return wd.findElements(By.xpath("//div[@class='MuiBox-root css-xj7u6x']//table/thead/tr/th/div"));

    }
    @FindBy(xpath= "//div[@class='MuiBox-root css-xj7u6x']//table/tbody")
    private WebElement tableAppointmentsListBody;
    public WebElement tableAppointmentsListBody() {

        return tableAppointmentsListBody;

    }
    @FindBy(xpath= "//div[@class='MuiBox-root css-xj7u6x']//table/tbody/tr/td[7]")
    private WebElement tableAppointmentList_Col_Language;
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

        appointment.click();
        Thread.sleep(3000);
    }

    public  void interpreterAccept() throws InterruptedException {
        IntrepreterTab.click();
        Thread.sleep(2000);
        OfferedTab.click();
        Thread.sleep(2000);
        AppointmentOffered.click();
        Thread.sleep(3000);
        AcceptTab.click();
        Thread.sleep(3000);
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

    public void makeAnOfferClick() throws InterruptedException {
        if(BaseClass.isElementPresent(InterpreterMatchingTab))
       InterpreterMatchingTab.click();
        Thread.sleep(3000);
        findInterpreter.click();
        makeAnOffer.click();
        }
}



