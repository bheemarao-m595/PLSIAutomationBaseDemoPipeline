package com.pom;


import com.base.BaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {

    WebDriver wd;

    @FindBy(xpath= "//span[text()='logout']")
    private WebElement logOut;

    @FindBy(id= "form_auth_signin_logininfo_email")
    private WebElement user;

    @FindBy(id= "form_auth_signin_logininfo_password")
    private WebElement password;

    @FindBy(id= "btn_auth_signinbasic")
    private WebElement login;

    @CacheLookup
    @FindBy(xpath="//*[text()='logout']/ancestor::button")
    WebElement logoutBtn;

    public LoginPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }



   public   void enterUserName(String val){

        user.sendKeys(val);

    }
    public  void enterPassword(String pwd){


        password.sendKeys(pwd);
    }

    public  void doLogin(String userName, String pwd) throws InterruptedException {

         if(BaseClass.isElementPresent(logOut)){

             click_logOut();
         }
         if(BaseClass.isElementPresent(user)){
             enterUserName(userName);
             enterPassword(pwd);

             BaseClass.goToElementVisibleArea(login);
             Thread.sleep(3000);
             login.click();
             Thread.sleep(3000);
         }




    }

    public void click_logOut(){

        wd.get("http://uat.ims.client.sstech.us/login");
        if(BaseClass.isElementPresent(logOut))
        {
            logOut.click();
            try{
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public boolean waitForLoginComplete(){
        boolean  successlogin = true;

        try {
            WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOf(logoutBtn));
        }catch (Exception e){
            e.printStackTrace();
            successlogin = false;
        }
        return  successlogin;
    }


}
