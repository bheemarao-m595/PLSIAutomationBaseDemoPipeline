package com.pom;

import com.base.BaseClass;
import com.utils.CommonUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.base.BaseClass.*;

public class ClientDetailsPage {


    WebDriver wd;
    public ClientDetailsPage(WebDriver d){
        wd = d;
        PageFactory.initElements(d,this);

    }

    @FindBy(xpath= "//button//span[text()='REQUESTERS']")
    private WebElement requestersTab;

    @FindBy(xpath= "//button//span[text()='FACILITIES']")
    private WebElement facilitiesTab;

    @FindBy(xpath= "//button//span[text()='APPOINTMENTS']")
    private WebElement appointmentsTab;

    @FindBy(id= "btn-alient-reqester-add")
    private WebElement addRequesterButton;


    @FindBy(xpath= "(//div[@role='dialog'])[2]//p")
    private WebElement addRequesterWindow;

    @FindBy(name= "FirstName")
    private WebElement firstNametext;

    @FindBy(name= "LastName")
    private WebElement lastNametext;

    @FindBy(name= "Street1")
    private WebElement street1;

    @FindBy(name= "Street2")
    private WebElement street2;

    @FindBy(name= "HomePhone")
    private WebElement landLine;

    @FindBy(name= "SMSCapableNumber")
    private WebElement smsNumber;

    @FindBy(name= "PostalCode")
    private WebElement postalCode;

    @FindBy(name= "UserName")
    private WebElement userName;

    @FindBy(name= "Password")
    private WebElement password;

    @FindBy(name= "ConfirmPassword")
    private WebElement confirmPassword;

    @FindBy(id= "btn-add-interpreter")
    private WebElement addButton;

    @FindBy(name= "City")
    private WebElement cityText;

    @FindBy(xpath= "//label[text()='State']/../following-sibling::div//input")
    private WebElement state;

    @FindBy(name= "Email")
    private WebElement email;




    public  void clickRequestersTab(){

        BaseClass.waitforElementToMakeClickable(requestersTab);
        requestersTab.click();

    }
    public  void clickAppointmentsTab(){

        BaseClass.waitforElementToMakeClickable(appointmentsTab);
        appointmentsTab.click();

    }
    public void clickFacilitiesTab(){

        BaseClass.waitforElementToMakeClickable(facilitiesTab);
       facilitiesTab.click();
    }

    public String addRequester() throws InterruptedException {


        addRequesterButton.click();
        boolean isWindowPresent = BaseClass.isElementPresent(addRequesterWindow);
        String domainforEmail = "sstech.us";
        String generatedEmail = CommonUtils.getRandomEmailIdDomain(domainforEmail);
        String lastNameSuffix = CommonUtils.getRandomStringOfLength(2);
        firstNametext.sendKeys(datasheet.get("requesterName"));
        lastNametext.sendKeys(datasheet.get("requesterLastName") +"_" + lastNameSuffix);
        street1.sendKeys(datasheet.get("street1"));
        street2.sendKeys(datasheet.get("street2"));
        landLine.sendKeys(datasheet.get("landline"));
        smsNumber.sendKeys(datasheet.get("sms"));
        cityText.sendKeys(datasheet.get("city"));
        state.click();
        state.sendKeys(datasheet.get("state"));
        state.sendKeys(Keys.TAB);
        email.sendKeys(generatedEmail);
        postalCode.sendKeys(datasheet.get("postalcode"));
        userName.sendKeys(generatedEmail);
        password.sendKeys("Welcome@1");
        confirmPassword.sendKeys("Welcome@1");
        BaseClass.goToElementVisibleArea(addButton);
        addButton.click();
        Thread.sleep(2000);
        return datasheet.get("requesterName") + " "+  datasheet.get("requesterLastName") +"_" + lastNameSuffix;

    }


}
