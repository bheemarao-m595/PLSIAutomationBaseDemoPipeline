package com.pom;


import com.base.BaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.FindBy;

public class LoginPage {



    WebDriver wd;
    @FindBy(id= "form_auth_signin_logininfo_email")
    private WebElement user;

    @FindBy(id= "form_auth_signin_logininfo_password")
    private WebElement pwd;

    @FindBy(id= "btn_auth_signinbasic")
    private WebElement login;


    @CacheLookup
    @FindBy(name="SubmitLogin")
    WebElement logoutBtn;

    public LoginPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }



   public   void enterUserName(String val){

        user.sendKeys(val);


    }

    public  void enterPassword(String password){


        pwd.sendKeys(password);
    }

    public  void clickLogin() throws InterruptedException {

        WebDriver d = BaseClass.driver;
        JavascriptExecutor js = (JavascriptExecutor)d;
        js.executeScript("arguments[0].scrollIntoView(true);",login);

        Thread.sleep(3000);
        login.click();
    }
}
