package com.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
    @FindBy(xpath= "//input[@id='react-select-3-input']")
    private WebElement calculationDropdown;
    @FindBy(xpath= "//input[@name='NoShow']")
    private WebElement NoShow;
    @FindBy(xpath= "//button[text()='Save']")
    private WebElement SaveCalculation;

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


}
