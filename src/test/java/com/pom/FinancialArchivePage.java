package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class FinancialArchivePage {
    WebDriver driver;
    public FinancialArchivePage(WebDriver driver){

        PageFactory.initElements(driver,this);
    }
    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[2]")
    private WebElement headerFin_Status;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[3]")
    private WebElement headerViews;

    @FindBy (xpath="//table[@class='MuiTable-root css-jiyur0']//th[4]")
    private WebElement headerAppDate;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[5]")
    private WebElement headerStatus;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[6]")
    private WebElement headerInterpreter;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[7]")
    private WebElement headerActualLangInt;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[8]")
    private WebElement headerClient;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[9]")
    private WebElement headerFacility;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[10]")
    private WebElement headerBLDG;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[11]")
    private WebElement headerClinicDept;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[12]")
    private WebElement headerRequestStart;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[13]")
    private WebElement headerRequestEnd;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[14]")
    private WebElement headerActualCheckIn;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[15]")
    private WebElement headerActualCheckOut;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[16]")
    private  WebElement headerActualDuration;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[17]")
    private WebElement headerInterPtgCost;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[18]")
    private WebElement headerTravelRate;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[19]")
    private WebElement headerTravelMin;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[20]")
    private WebElement headerMileAge;

    @FindBy (xpath = "//table[@class='MuiTable-root css-jiyur0']//th[21]")
    private WebElement headerTollsPRKNG;

    public void sortColumnsFinancialArchive() throws InterruptedException, IOException {

        Thread.sleep(2000);
        headerFin_Status.click();

        Thread.sleep(2000);
        headerViews.click();

        Thread.sleep(2000);
        headerAppDate.click();

        Thread.sleep(2000);
        headerStatus.click();

        Thread.sleep(2000);
        headerInterpreter.click();

        Thread.sleep(2000);
        headerActualLangInt.click();

        Thread.sleep(2000);
        headerClient.click();

        Thread.sleep(2000);
        headerFacility.click();

        Thread.sleep(2000);
        headerBLDG.click();

        Thread.sleep(2000);
        headerClinicDept.click();

        Thread.sleep(2000);
        headerRequestStart.click();

        Thread.sleep(2000);
        headerRequestEnd.click();

        Thread.sleep(2000);
        headerActualCheckIn.click();

        Thread.sleep(2000);
        headerActualCheckOut.click();

        Thread.sleep(2000);
        headerActualDuration.click();

        Thread.sleep(2000);
        headerInterPtgCost.click();

        Thread.sleep(2000);
        headerTravelRate.click();

        Thread.sleep(2000);
        headerTravelMin.click();

        Thread.sleep(2000);
        headerMileAge.click();

        Thread.sleep(2000);
        headerTollsPRKNG.click();
    }
}
