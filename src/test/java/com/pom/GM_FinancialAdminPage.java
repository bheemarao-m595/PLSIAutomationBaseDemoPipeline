package com.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class GM_FinancialAdminPage {
    WebDriver driver;
    public GM_FinancialAdminPage(WebDriver d){
        driver=d;
        PageFactory.initElements(d,this);
    }
    @FindBy (xpath = "//*[text()='Finance Admin']/../../..")
    private WebElement financeAdmin;

    @FindBy (xpath = "//span[contains(text(),'Financial Review')]")
    private WebElement financeReview;

    @FindBy (xpath = "//*[text()='FINANCIAL ARCHIVE']/..")
    private WebElement financeArchiveTab;


    @FindBy (xpath = "//button[@id='Approved']")
    private WebElement btnApprove;

    @FindBy (xpath = "//span[contains(text(),'chevron_right')]")
    private WebElement paging;

    public void navigateFinancialReviewPage() throws InterruptedException , IOException {
        Thread.sleep(2000);
        financeAdmin.click();

        Thread.sleep(2000);
        financeReview.click();

    }
    public boolean navigateFinancialArchivePage() throws InterruptedException,IOException{
        Thread.sleep(2000);
        financeAdmin.click();

        Thread.sleep(2000);
        financeArchiveTab.click();
        Thread.sleep(2000);
      String classVal =  financeArchiveTab.getAttribute("class");
      boolean highlighted =classVal.contains("Mui-selected");
       return  highlighted;

    }

    public WebElement getArchivetab(){

     return financeArchiveTab;
    }
    public void approvingFinancialAppointment(int noOfChecboxRequired,String Status) throws Throwable {


        int boxesChecked=0;
        while(boxesChecked<noOfChecboxRequired) {
            List<WebElement> rows = driver.findElements(By.xpath("//tbody[@class='MuiTableBody-root css-1xnox0e']//td[2]"));
            for(int i=0;i<rows.size();i++) {
                System.out.println(rows.get(i).getText());
                if(rows.get(i).getText().contains(Status)) {
                    Thread.sleep(1000);
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    String elePath = "(//input[@type='checkbox'])[" + (i + 2 )+ "]";

                    WebElement checkbox=driver.findElement(By.xpath(elePath));
                    js.executeScript("arguments[0].scrollIntoView();",checkbox);
                    js.executeScript("arguments[0].click();", checkbox);
                    boxesChecked++;
                    if(boxesChecked == noOfChecboxRequired)
                        break;

                }

            } // all rows executed
            if(boxesChecked < noOfChecboxRequired) { // for next page
                JavascriptExecutor js = (JavascriptExecutor) driver;
                //WebElement Element = driver.findElement(paging);
                js.executeScript("arguments[0].scrollIntoView();",paging );
                Thread.sleep(2000);
                paging.click();
                Thread.sleep(3000);
            }
        } //end of while
    }
    public void approvingAppointment() throws InterruptedException ,IOException{
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-700)");
        Thread.sleep(2000);
        btnApprove.click();

    }
}
