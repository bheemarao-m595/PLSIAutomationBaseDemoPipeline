package com.pom;

import com.base.BaseClass;
import com.utils.CommonUtils;
import com.utils.DashBoardHeaders;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.base.BaseClass.datasheet;

public class DashBoardPage {

    WebDriver wd;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']")
    private WebElement appointmentsTableinDashBoard;

    @FindBy(css= ".MuiBox-root.css-w2sxf")
    private WebElement Menu;

    @FindBy(xpath= "//input[@class='MuiInputBase-input css-1bqqmdo']")
    private WebElement search;


    @FindBy(css= ".css-1txeit4")
    private WebElement newAppointment;

    public boolean newAppointmentIsDisplayed(){
        return newAppointment.isDisplayed();
    }

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']")
    private WebElement allAppointmentTable;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody")
    private WebElement allAppointmentTableBody;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr")
    private WebElement allAppointmentTableBodyRows;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[2]")
    private WebElement allAppointmentTableBodyRowsDatecolumn;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[9]")
    private WebElement allAppointmentTableBodyRowsStatusColumn;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[1]")
    private WebElement allAppointmentTableBodyRowsViewColumn;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[7]")
    private WebElement allAppointmentTableBodyRowsPatientColumn;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[8]")
    private WebElement allAppointmentTableBodyRowsLanguageColumn;


    @FindBy(css= ".MuiBox-root.css-lhz7xj")
    private WebElement appointmentCreatedSuccessMsg;

    @FindBy(css= ".MuiBox-root.css-8kdvm0")
    private WebElement filter;

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

    @FindBy(xpath = "//tbody[@class='MuiTableBody-root css-1xnox0e']//tr[2]//td[9]")
    private WebElement click_Status;

    @FindBy(xpath = "//label[@id='typo_Statusform_apptstatus']/../following-sibling::div//input")
    private WebElement appointmentStatus;

    @FindBy(id = "btn_Statusform_save")
    private WebElement save_Status;


    public DashBoardPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }

    public void updateQuickStatus(String patientName) throws InterruptedException {

        WebElement statusLink =  wd.findElement(By.xpath("//*[text()='" + patientName     + "']//ancestor::td/following-sibling::td[2]//div//span"));

        statusLink.click();
        Thread.sleep(2000);
        appointmentStatus.click();
        appointmentStatus.sendKeys(datasheet.get("Appointment Status"));
        appointmentStatus.sendKeys(Keys.TAB);

        Thread.sleep(2000);
        save_Status.click();
        Thread.sleep(2000);

    }

    public  void updatePatientNotes(String patientName) throws InterruptedException {

        WebElement patientLink =  wd.findElement(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr//td//div[text()='" + patientName + "']"));

        patientLink.click();
        Thread.sleep(2000);

        scheduler_notes.clear();
        preference.sendKeys("Test Data");
        Thread.sleep(2000);

        requester.clear();
        requester.sendKeys("Test Data");
        Thread.sleep(2000);

        scheduler_notes.clear();
        scheduler_notes.sendKeys("Test Data");
        Thread.sleep(2000);
        Save.click();
        Thread.sleep(2000);

    }

    public  void clickSearchInHomePage(String keyword){


        search.sendKeys(keyword);
    }

    public  WebElement getWebElementOfHeaderAndCellValue(DashBoardHeaders dbh, String cellValue){

        WebElement  appId = null;
        DashBoardHeaders searchString = dbh;
        String  actualHeader =CommonUtils.getActualHeaderStringFromDashBoardTable(searchString);

        int i = 1;
        List<WebElement> rows = wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']//th/div[1]"));
        Map<String, Integer> headIndex = new LinkedHashMap<>();


        for (WebElement r : rows) {

            String val = r.getText();
            String firtword = val.split("\\R")[0];
            headIndex.put(firtword, i);
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

    public  WebElement get_allAppointmentTable(){

        return allAppointmentTable;
    }

    public  List<WebElement> get_allAppointmentTableBodyRowsStatusColumn(){

        return  wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[9]"));

    }

    public  List<WebElement> get_allAppointmentTableBodyRowsLanguageColumn() {
        return wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[8]"));
    }

    public List<WebElement> get_allAppointmentTableBodyRowsViewColumn() {
        return wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[1]"));
    }


    public  void search(String searchFor) throws InterruptedException {
        search.sendKeys(searchFor);
        Thread.sleep(3000);
    }

    public  WebElement getAppIDWebElement(String text){

        WebElement appId =   wd.findElement(By.xpath("//tbody[@class='MuiTableBody-root css-1xnox0e']//tr//td/div/div[text()='"+ text +"']"));

        return  appId;

    }





}
