package com.pom;

import com.base.BaseClass;
import com.utils.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.Status;

import java.util.List;

import static com.base.BaseClass.datasheet;
import  static com.base.BaseClass.logger;

public class Interpreter_DetailsPage
{
    WebDriver wd;
    BaseClass b = new BaseClass();

    @FindBy(name = "StartTime")
    private WebElement create_StartTime_Available;

    @FindBy(name = "EndTime")
    private WebElement edit_EndTime_Available;

    @FindBy(name = "EndTime")
    private WebElement create_EndTime_Available;

    @FindBy(xpath = "//div[@class='MuiFormControl-root MuiTextField-root css-i44wyl'][1]//input[@name='StartDate']")
    private WebElement create_StartDate_Available;

    @FindBy(xpath = "//input[@placeholder='EndDate']")
    private WebElement create_EndDate_Available;

    @FindBy(xpath = "//label[text()='Availability ?']/../following-sibling::div//input")
    private WebElement create_Status_Availablity;

    @FindBy(xpath = "//label[text()='Availability ?']/../following-sibling::div//input")
    private WebElement edit_Status_Availablity;

    @FindBy(xpath = "//tbody[@class='MuiTableBody-root css-1xnox0e']//tr[2]//td[1]")
    private WebElement view_Int;

    @FindBy(xpath = "//span[text()='AVAILABILITY']")
    private WebElement menu_Availability;
    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-2 MuiGrid-grid-md-2 MuiGrid-grid-lg-2 css-tflyol']")
    private WebElement add_Availability;

    @FindBy(xpath = "//td[@data-date='2023-02-10']//div[@class='fc-timegrid-event-harness fc-timegrid-event-harness-inset'][1]")
    private WebElement update_Existavailable;

    @FindBy(xpath = "//td[@data-date='2023-02-10']//div[@class='fc-timegrid-event-harness fc-timegrid-event-harness-inset'][2]")
    private WebElement update_ExistUnavailable;

    @FindBy(name = "StartTime")
    private WebElement edit_StartTime_Available;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement save_Availability_Status;

    @FindBy(id = "Delete")
    private WebElement deleteAvailablity;

    @FindBy(xpath = "//span[text()='LANGUAGE PROFICIENCY']")
    private WebElement menu_LangProf;

    @FindBy(id = "AddLanguageProficiency")
    private WebElement addlangprof_Button;

    @FindBy(xpath = "//label[text()='Language ']/../following-sibling::div//input")
    private WebElement select_Lang;

    @FindBy(name = "proficiency")
    private WebElement proficiency_Text;

    @FindBy(name = "intRate")
    private WebElement intRate_Text;

    @FindBy(xpath = "//button[text()='Save']")
    private  WebElement save_Lang_Prof;

    @FindBy(xpath = "//div[text()='Hindi']//ancestor::td/following-sibling::td[3]//span[text()='delete_outline']")
    private WebElement select_LangrowtoDelete;

    @FindBy(xpath = "//span[contains(text(),'check')]")
    private WebElement confirmDelete;



   public Interpreter_DetailsPage(WebDriver d) {

        wd = d;
        PageFactory.initElements(d, this);
    }


    public void editInterpreterAvailablity() throws Throwable
    {
        String currentDateYearFormat = CommonUtils.getCurrentSystemDateyear();
        Thread.sleep(3000);

        Thread.sleep(3000);
        menu_Availability.click();

        Thread.sleep(2000);
        List<WebElement> allAvail = b.getAllElementsByXpath(wd, "//td[@data-date='" + currentDateYearFormat + "']//div[@class='fc-timegrid-event-harness fc-timegrid-event-harness-inset']");
        int  countOftimes = allAvail.size();

        WebElement avail = b.getElementByXpath(wd, "//td[@data-date='" + currentDateYearFormat + "']//div[@class='fc-timegrid-event-harness fc-timegrid-event-harness-inset'][" + countOftimes +"]//a");
        boolean b = BaseClass.isElementPresent(avail);
        avail.click();

        Thread.sleep(2000);
        edit_StartTime_Available.clear();
        edit_StartTime_Available.sendKeys(CommonUtils.addMinutesToCurrentTime(5));

        Thread.sleep(2000);
        edit_EndTime_Available.clear();
        edit_EndTime_Available.sendKeys(CommonUtils.addMinutesToCurrentTime(10));

        Thread.sleep(2000);
        edit_Status_Availablity.click();
        edit_Status_Availablity.sendKeys(datasheet.get("Status"));
        edit_Status_Availablity.sendKeys(Keys.TAB);
        save_Availability_Status.click();
        Thread.sleep(3000);


    }

    public void delete_Interpreter_Availability() throws Throwable
    {
        Thread.sleep(3000);
        menu_Availability.click();

        Thread.sleep(2000);
        String currentDateYearFormat = CommonUtils.getCurrentSystemDateyear();
        List<WebElement> allAvail = b.getAllElementsByXpath(wd, "//td[@data-date='" + currentDateYearFormat + "']//div[@class='fc-timegrid-event-harness fc-timegrid-event-harness-inset']");
        int  countOftimes = allAvail.size();
     //   WebElement update_ExistavailableDyna = b.getElementByXpath(wd, "//td[@data-date='" + currentDateYearFormat + "']//div[@class='fc-timegrid-event-harness fc-timegrid-event-harness-inset'][1]");
        WebElement update_ExistavailableDyna = b.getElementByXpath(wd, "//td[@data-date='" + currentDateYearFormat + "']//div[@class='fc-timegrid-event-harness fc-timegrid-event-harness-inset'][" + countOftimes +"]//a");
        update_ExistavailableDyna.click();

        Thread.sleep(2000);
        deleteAvailablity.click();
    }

    public void add_Proficiency() throws Throwable
    {

        Thread.sleep(2000);
        menu_LangProf.click();
        String xpath = "(//tbody[@class='MuiTableBody-root css-1xnox0e'])[2]//tr//td//div[text()='"+ datasheet.get("Language")  +"']/ancestor::td/preceding-sibling::td[1]//input";

        menu_LangProf.click();

        Thread.sleep(2000);
        boolean isPresent = b.isElementByXpath(xpath);
        if(isPresent){
            logger.log(Status.INFO,"Language" +datasheet.get("Language") +"is already present");
            delete_Proficiency(datasheet.get("Language"));
        }
            Thread.sleep(1000);
            addlangprof_Button.click();
            logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Creating language proficiency"));
            logger.log(Status.PASS,"Add Proficiency tab clicked");

            Thread.sleep(1000);
            select_Lang.click();
            select_Lang.sendKeys(datasheet.get("Language"));
            select_Lang.sendKeys(Keys.TAB);

            Thread.sleep(1000);
            proficiency_Text.click();
            String pro = datasheet.get("Proficiency");
            proficiency_Text.sendKeys(pro);

            Thread.sleep(1000);
            intRate_Text.click();
            intRate_Text.sendKeys(datasheet.get("Interpretation Rate"));
            logger.log(Status.PASS,"Rate Added");
            logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Rate Added added"));

            Thread.sleep(1000);
            save_Lang_Prof.click();
            Thread.sleep(1000);

            logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Proficiency added"));

    }

    public void delete_Proficiency(String lang) throws Throwable
    {
        Thread.sleep(2000);
        //
        // String xpath = "(//tbody[@class='MuiTableBody-root css-1xnox0e'])[2]//tr//td//div[text()='"+  lang +"']/ancestor::td/preceding-sibling::td[1]//input";
        String xpath="//div[text()='"+  lang +"']//ancestor::td/following-sibling::td[3]//span[text()='delete_outline']";
        menu_LangProf.click();

        Thread.sleep(2000);

        boolean isPresent = b.isElementByXpath(xpath);
        if(isPresent)
        {
            //WebElement menu_LangProf_checkBox = b.getElementByXpath(wd,xpath);
            //menu_LangProf_checkBox.click();

            Thread.sleep(2000);
            select_LangrowtoDelete.click();

            Thread.sleep(2000);
            confirmDelete.click();
            logger.addScreenCaptureFromPath(b.takeScreenshotForStep("After clicking Remove Language"));
        }else
            logger.log(Status.PASS,"Language not present in Remove");


    }


    public  void openInterpreterDetailsWindow(String interpreterName) throws InterruptedException {

      WebElement  interpreterLink =wd.findElement(By.xpath("//tbody[@class='MuiTableBody-root css-1xnox0e']//tr//td//div[text()='" + interpreterName + "']"));

        interpreterLink.click();
        Thread.sleep(2000);

    }

    public void create_Interpreter_Availability() throws Throwable {

      String startTime =  CommonUtils.addMinutesToCurrentTime(5);
      String endTime =  CommonUtils.addMinutesToCurrentTime(65);

        Thread.sleep(3000);
        menu_Availability.click();
        logger.log(Status.PASS,"Availability Menu Clicked");
        add_Availability.click();
        Thread.sleep(1000);
        create_StartDate_Available.sendKeys(CommonUtils.getCurrentSystemDate());
        Thread.sleep(1000);
        create_StartTime_Available.sendKeys(startTime);
        Thread.sleep(1000);
        create_EndDate_Available.sendKeys(CommonUtils.getCurrentSystemDate());
        Thread.sleep(1000);
        create_EndTime_Available.sendKeys(endTime);
        Thread.sleep(1000);
        create_Status_Availablity.click();
        Thread.sleep(1000);
        create_Status_Availablity.sendKeys(datasheet.get("Status"));
        create_Status_Availablity.sendKeys(Keys.TAB);
        logger.log(Status.PASS,"Entered Status");
        logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Required Details"));
        Thread.sleep(2000);
        save_Availability_Status.click();
        Thread.sleep(2000);
        logger.log(Status.PASS,"Save the created availability");

        logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Availability Saved"));
    }



}
