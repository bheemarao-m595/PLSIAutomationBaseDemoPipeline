package com.pom;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.base.BaseClass;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

import static com.base.BaseClass.datasheet;

public class VG_Interpreter_ADDInterpreterpage
{
    WebDriver wd;
    @FindBy(xpath = "//a[@href='/interpreters']")
    private WebElement interpretersLinkMenu;
    @FindBy(xpath = "//button[text()=' + add Interpreter']")
    private WebElement AddInterpreter;
    @FindBy(xpath = "//input[@name='FirstName']")
    private WebElement FirstName;
    @FindBy(xpath = "//input[@name='LastName']")
    private WebElement LastName;
    @FindBy(xpath = "//input[@name='Street1']")
    private WebElement Street1;
    @FindBy(xpath = "//input[@name='HomePhone']")
    private WebElement Landline;
    @FindBy(xpath = "//input[@name='SMSCapableNumber']")
    private WebElement phonenumber;
    @FindBy(xpath = "//input[@name='City']")
    private WebElement City;
    @FindBy(xpath = "//input[@id='react-select-21-input']")
    private WebElement State;
    @FindBy(xpath = "//input[@name='Email']")
    private WebElement Email;
    @FindBy(xpath = "//input[@name='PostalCode']")
    private WebElement postalCode;
    @FindBy(xpath = "//input[@name='UserName']")
    private WebElement UserName;
    @FindBy(xpath = "//input[@name='Password']")
    private WebElement Password1;
    @FindBy(xpath = "//input[@name='ConfirmPassword']")
    private WebElement ConfirmPassword;
    @FindBy(xpath = "//button[@id=\"btn-add-interpreter\"]")
    private WebElement ADDInterpreterbutton;

    public VG_Interpreter_ADDInterpreterpage (WebDriver d) {

        wd = d;
        PageFactory.initElements(d, this);

    }

    public static Map<String, String> fieldXpath = new HashMap<>();

    public String getWebElementOfField(String label) {


        return "";
    }

    public void clickInterpreters() throws InterruptedException {
        Thread.sleep(2000);
        interpretersLinkMenu.click();

    }
    public void addInterpreter() throws InterruptedException {
        Thread.sleep(2000);
        AddInterpreter.click();
    }
    public void clickAddInterpretertabs() throws InterruptedException {
        // BaseClass.waitforElementToMakeClickable(clients);
        Thread.sleep(2000);
        FirstName.sendKeys(datasheet.get("Select FirstName"));
        Thread.sleep(3000);
        FirstName.sendKeys(Keys.TAB);
        LastName.sendKeys(datasheet.get("Select LastName"));
        Thread.sleep(3000);
        LastName.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        Street1.sendKeys(datasheet.get("Select Street1"));
        Street1.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        Landline.sendKeys(datasheet.get("Select Landline"));
        Landline.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        phonenumber.sendKeys(datasheet.get("Select SMSPHnumber"));
        phonenumber.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        City.sendKeys(datasheet.get("Select City"));
        City.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        State.sendKeys(datasheet.get("Select State"));
        State.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        Email.sendKeys(datasheet.get("Select Email"));
        Email.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        postalCode.sendKeys(datasheet.get("Select Postalcode"));
        postalCode.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        UserName.sendKeys(datasheet.get("Select Username"));
        UserName.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        Password1.sendKeys(datasheet.get("Select Password1"));
        Password1.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        ConfirmPassword.sendKeys(datasheet.get("Select Confirmpassword"));
        ConfirmPassword.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        WebDriver d = BaseClass.driver;
        JavascriptExecutor js = (JavascriptExecutor)d;
        js.executeScript("arguments[0].scrollIntoView(true);",ADDInterpreterbutton);
        Thread.sleep(3000);
        ADDInterpreterbutton.click();


    }
}
