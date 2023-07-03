
package com.pom;

import com.base.BaseClass;
import com.utils.CommonUtils;
import com.utils.DashBoardHeaders;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InterpreterDashboardPage {

    WebDriver wd;

    public InterpreterDashboardPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }

    @FindBy(xpath= "//span[text()='OFFERED']")
    private WebElement offeredTab;

    @FindBy(xpath= "//input[@class='MuiInputBase-input css-1bqqmdo']")
    private WebElement search;

    @FindBy(xpath= "//span[text()='OFFERED']/div")
    private WebElement offeredTabCount;


    @FindBy(xpath= "//span[text()='ACCEPTED']")
    private WebElement acceptedTab;

    @FindBy(xpath= "//span[text()='AVAILABLE']")
    private WebElement availableTab;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']/tbody")
    private WebElement interpreterDashboardAppointmentTable;

    @FindBy(xpath= "//table[@class='MuiTable-root css-v6tz28']/tbody")
    private WebElement tableInterpreterListBody;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']//tr/td[1]/div/div")
    private WebElement firstInterpreterDashboardAppointmentTableColView;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']//tr[1]/td[12]")
    private WebElement interpreterDashboardAppointmentTableColStatus;

    @FindBy(xpath= "//p[@class='MuiTypography-root MuiTypography-body1 css-1dm1q28']")
    private WebElement interpreterDashboardAppointmentTitle;

    @FindBy(xpath= "//button[text()='Decline Appointment']")
    private WebElement declineButton;

    @FindBy(xpath= "//button[text()='Check In']")
    private WebElement checkInButton;

    @FindBy(xpath= "//button[text()='Check Out']")
    private WebElement checkOutButton;

    @FindBy(xpath= "//button[text()='Accept Appointment']")
    private WebElement acceptButton;

    @FindBy(xpath= "//button[text()='Return Appointment']")
    private WebElement returnAppointmentButton;

    @FindBy(xpath= "//div[@class='MuiTooltip-tooltip MuiTooltip-tooltipArrow MuiTooltip-tooltipPlacementTop css-1p9alne']")
    private WebElement hoverTextOnReturnAppointmentButton;

    @FindBy(xpath= "//p[text()='Finalize Appointment']")
    private WebElement finalizeAppointmentPageTitle;

    @FindBy(xpath= "//div[@class='MuiBox-root css-1esysrx']/div/div[2]/button")
    private WebElement finalizeAppointmentButton;

    @FindBy(xpath= "//p[text()='View Financial Breakdown - undefined']/button")
    private WebElement finalizeAppointmentPageCrossButton;

    @FindBy(xpath= "//button[text()='EDIT INTERPRETER']")
    private WebElement editInterpreterButton;

    @FindBy(xpath= "//p[ contains (text(),'Appointment -')]")
    private WebElement appointmentPageTitle;

    @FindBy(xpath= "//p[ contains (text(),'Appointment -')]/button")
    private WebElement appointmentCrossButton;

    @FindBy(xpath= "//p[text()='View Financial Breakdown - undefined']")
    private WebElement financialBreakDownPageTitle;

    @FindBy(xpath= "//button[text()='View Financial Breakdown']")
    private WebElement financialBreakDownButton;

    @FindBy(xpath= "//p[text()='View Financial Breakdown - undefined']/button")
    private WebElement financialBreakDownPageCrossButton;

    @FindBy(xpath= "//input[contains(@id,'react-select')]")
    private WebElement finalizeAppointmentDropdownsList;

    @FindBy(xpath= "//span[text()='Can Self-Book for Appointments']")
    private WebElement canSelfBookAppointment;

    @FindBy(xpath= "//button[text()='Cancel']")
    private WebElement cancel;

    @FindBy(xpath= "//button[text()='close']")
    private WebElement close;



    public  String getTextOfferedTab()  {
        return offeredTabCount.getText();
    }

    public boolean availableTabIsDisplayed(){
        return  BaseClass.isElementPresent(availableTab);
    }

    public void clickAvailableTab(){

        availableTab.click();
    }

    public  void clickOfferedTab(){
        offeredTab.click();
    }

    public  void enterSearch(String searchFor){
        search.sendKeys(searchFor);
        search.sendKeys(Keys.TAB);

    }

    public  WebElement get_InterpreterDashboardAppointmentTable(){

        return interpreterDashboardAppointmentTable;
    }

    public  void clickAcceptedTab(){

        acceptedTab.click();
    }


    public WebElement getFirstInterpreterDashboardAppointmentTableColView(){

        return firstInterpreterDashboardAppointmentTableColView;
    }

    public void clickFirstInterpreterDashboardAppointmentTableColView() throws InterruptedException {
        firstInterpreterDashboardAppointmentTableColView.click();
        Thread.sleep(3000);
        /*BaseClass.clickWithJavaScript(firstInterpreterDashboardAppointmentTableColView);
        Thread.sleep(3000);*/
    }

    public void clickAppointmentIdWithText(String apptId) throws InterruptedException {
        BaseClass b = new BaseClass();

        WebElement ele = b.getElementByXpath(wd,"//table[@class='MuiTable-root css-v6tz28']//tr/td/div/div[text()='"+apptId+"']");
        ele.click();
        Thread.sleep(2000);
    }

    public List<WebElement> get_InterpreterDashboardAppointmentTableColView(){
        return  wd.findElements(By.xpath("//table[@class='MuiTable-root css-v6tz28']//tr/td[1]/div/div"));

    }

    public List<WebElement> get_InterpreterDashboardAppointmentTableColStatus(){
        return  wd.findElements(By.xpath("//table[@class='MuiTable-root css-v6tz28']//tr[1]/td[15]"));

    }


    public String getTitleInterpreterDashboardAppointmentTitle(){
        return interpreterDashboardAppointmentTitle.getText();
    }

    public  String getTextInterpreterDashboardAppointmentClickTitle(){
        return interpreterDashboardAppointmentTitle.getText();
    }

    public  void clickDeclineButton()
    {
        declineButton.click();
    }



    public  void clickAcceptButton(){

        acceptButton.click();
    }



    public  void hoverReturnAppointmentButton(){

        Actions actions = new Actions(wd);

        actions.moveToElement(returnAppointmentButton).perform();

    }

    public  void clickReturnAppointmentButton(){

        returnAppointmentButton.click();
    }



    public boolean isDisplayed_HoverTextOnReturnAppointmentButton(){

        return hoverTextOnReturnAppointmentButton.isDisplayed();

    }
    public  String get_HoverTextOnReturnAppointmentButton(){

        return hoverTextOnReturnAppointmentButton.getText();
    }



    public  void clickCheckInButton(){

        checkInButton.click();
    }


    public  void clickCheckOutButton(){

        checkOutButton.click();
    }



    public  void clickFinalizeAppointmentButton(){

        finalizeAppointmentButton.click();
    }

    public  void clickFinalizeAppointmentPageCrossButton(){

        finalizeAppointmentPageCrossButton.click();
    }

    public  void clickFinancialBreakDownButton(){

        financialBreakDownButton.click();
    }

    public  void clickFinancialBreakDownPageCrossButton(){

        financialBreakDownPageCrossButton.click();
    }


    public  void clickAppointmentCrossButton(){

        appointmentCrossButton.click();
    }


    public  void clickEditInterpreterButton(){

        editInterpreterButton.click();
    }

    public WebElement getCanSelfBookAppointment(){

        return canSelfBookAppointment;
    }

    public void clickCancel(){

        cancel.click();
    }



    public void clickClose(){
        close.click();
    }


    public List<WebElement> get_FinalizeAppointmentDropdownsList(){
        return  wd.findElements(By.xpath("//input[contains(@id,'react-select')]"));

    }

    public  WebElement getWebElementOfHeaderAndCellValue(DashBoardHeaders dbh, String cellValue){

        WebElement  appId = null;
        DashBoardHeaders searchString = dbh;
        String  actualHeader = CommonUtils.getActualHeaderStringFromDashBoardTable(searchString);

        int i = 1;
        List<WebElement> rows = wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']//th/div[1]"));
        Map<String, Integer> headIndex = new LinkedHashMap<>();


        for (WebElement r : rows) {

            String val = r.getText();
            String firstword = val.split("\\R")[0];
            headIndex.put(firstword, i);
            i++;
        }

        int headerIndex = headIndex.get(actualHeader);

        int recordsCount = wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']//tbody//tr")).size();
        Assert.assertNotEquals(recordsCount, 0, "Table is empty");
        for (int rowNumber = 1; rowNumber <= recordsCount; rowNumber++) {

            String part1 = "//table[@class='MuiTable-root css-jiyur0']//tbody//tr[";
            String part2 = "]//td//*[text()='" + cellValue + "']";
            String part3 = "//td//*[text()='" + cellValue + "']/ancestor::td/preceding-sibling::td[";

            BaseClass b = new BaseClass();
            boolean recordTEextMatching = b.isElementByXpath( part1 + rowNumber + part2);
            if (recordTEextMatching) {

                appId = b.getElementByXpath(wd, (part3 + (headerIndex - 1) + "]"));
                break;

            }
        }

        return appId;


    }

    public  WebElement get_tableInterpreterListBody(){

        return tableInterpreterListBody;
    }



}



