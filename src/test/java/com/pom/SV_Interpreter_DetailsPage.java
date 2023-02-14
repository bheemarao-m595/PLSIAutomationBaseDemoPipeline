package com.pom;

import com.base.BaseClass;
import com.utils.CommonUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.base.BaseClass.datasheet;
import  static com.base.BaseClass.logger;

public class SV_Interpreter_DetailsPage
{
    WebDriver wd;

    @FindBy(xpath = "//tbody[@class='MuiTableBody-root css-1xnox0e']//tr[2]//td[1]")
    private WebElement view_Int;

    @FindBy(xpath = "//span[text()='AVAILABILITY']")
    private WebElement menu_Availability;

    @FindBy(xpath = "//td[@data-date='2023-02-13']//div[@class='fc-timegrid-event-harness fc-timegrid-event-harness-inset'][1]")
    private WebElement update_Existavailable;

    @FindBy(xpath = "//td[@data-date='2023-02-13']//div[@class='fc-timegrid-event-harness fc-timegrid-event-harness-inset'][2]")
    private WebElement update_ExistUnavailable;


    @FindBy(name = "StartTime")
    private WebElement edit_StartTime_Available;


    @FindBy(name = "EndTime")
    private WebElement edit_EndTime_Available;

    @FindBy(xpath = "//label[text()='Availability ?']/../following-sibling::div//input")
    private WebElement edit_Status_Availablity;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement save_Availability_Status;

    @FindBy(id = "Delete")
    private WebElement delete_Availablity;

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

    @FindBy(xpath = "//tbody[@class='MuiTableBody-root css-1xnox0e']//tr[1]//span/input")
    private WebElement select_Langrow;

    @FindBy(id = "RemoveLanguageProficiency")
    private WebElement remove_Select_Lang;

    BaseClass b ;

   public SV_Interpreter_DetailsPage (WebDriver d) {

        wd = d;
        PageFactory.initElements(d, this);
       b = new BaseClass();

    }

    public void edit_Interpreter_Availablity() throws Throwable
    {
        Thread.sleep(3000);
        view_Int.click();

        Thread.sleep(3000);
        menu_Availability.click();

        Thread.sleep(2000);
        String currentDateYearFormat = CommonUtils.getCurrentSystemDateyear();
        WebElement avail = b.getElementByXpath(wd, "//td[@data-date=' " + currentDateYearFormat + "']//div[@class='fc-timegrid-event-harness fc-timegrid-event-harness-inset'][1]");
        avail.click();

//        Thread.sleep(2000);
//        update_Existavailable.click();

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


        Thread.sleep(3000);
        save_Availability_Status.click();


    }

    public void delete_Interpreter_Availablity() throws Throwable
    {
        Thread.sleep(3000);
        view_Int.click();

        Thread.sleep(3000);
        menu_Availability.click();

        Thread.sleep(2000);
        update_Existavailable.click();

        Thread.sleep(2000);
        delete_Availablity.click();
    }

    public void add_Proficiency() throws Throwable
    {
        Thread.sleep(3000);
        view_Int.click();

        Thread.sleep(2000);
        menu_LangProf.click();

        Thread.sleep(2000);
        addlangprof_Button.click();
        logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Created language proficiency from any interpreter"));

        Thread.sleep(2000);
        select_Lang.click();
        select_Lang.sendKeys(datasheet.get("Language"));
        select_Lang.sendKeys(Keys.TAB);

        Thread.sleep(2000);
        proficiency_Text.click();
        String pro = datasheet.get("Proficiency");
        proficiency_Text.sendKeys(pro);

        Thread.sleep(2000);
        intRate_Text.click();
        intRate_Text.sendKeys(datasheet.get("Interpretation Rate"));

        Thread.sleep(2000);
        save_Lang_Prof.click();
        Thread.sleep(2000);

        logger.addScreenCaptureFromPath(b.takeScreenshotForStep("Created language proficiency from any interpreter"));

        try {
            delete_Proficiency(datasheet.get("Language"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete_Proficiency(String lang) throws Throwable
    {
        Thread.sleep(3000);
        if(BaseClass.isElementPresent(view_Int))
        view_Int.click();

        Thread.sleep(2000);
        String xpath = "//tbody[@class='MuiTableBody-root css-1xnox0e']//tr//td[text()='" + lang + "']/preceding-sibling::td/span/input";

        menu_LangProf.click();

        Thread.sleep(2000);
        WebElement menu_LangProf_checkBox = b.getElementByXpath(wd,xpath);
        menu_LangProf_checkBox.click();

        Thread.sleep(2000);
        remove_Select_Lang.click();

    }







}
