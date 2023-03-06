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

public class Interpreter_ADDInterpreterpage
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
    private WebElement city;
    @FindBy(xpath = "//input[@id='react-select-21-input']")
    private WebElement state;
    @FindBy(xpath = "//input[@name='Email']")
    private WebElement email;
    @FindBy(xpath = "//input[@name='PostalCode']")
    private WebElement postalCode;
    @FindBy(xpath = "//input[@name='UserName']")
    private WebElement userName;
    @FindBy(xpath = "//input[@name='Password']")
    private WebElement password1;
    @FindBy(xpath = "//input[@name='ConfirmPassword']")
    private WebElement confirmPassword;
    @FindBy(xpath = "//button[@id=\"btn-add-interpreter\"]")
    private WebElement addinterpreterbutton;

    public Interpreter_ADDInterpreterpage(WebDriver d) {

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
        city.sendKeys(datasheet.get("Select City"));
        city.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        state.sendKeys(datasheet.get("Select State"));
        state.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        email.sendKeys(datasheet.get("Select Email"));
        email.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        postalCode.sendKeys(datasheet.get("Select Postalcode"));
        postalCode.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        userName.sendKeys(datasheet.get("Select Username"));
        userName.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        password1.sendKeys(datasheet.get("Select Password1"));
        password1.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        confirmPassword.sendKeys(datasheet.get("Select Confirmpassword"));
        confirmPassword.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        WebDriver d = BaseClass.driver;
        JavascriptExecutor js = (JavascriptExecutor)d;
        js.executeScript("arguments[0].scrollIntoView(true);", addinterpreterbutton);
        Thread.sleep(3000);
        addinterpreterbutton.click();


    }
}
