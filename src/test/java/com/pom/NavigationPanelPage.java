package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationPanelPage {

    WebDriver wd;

    @FindBy(xpath= "//a[@href='/interpreters']")
    private WebElement interpreters;

    @FindBy(xpath= "//a[@href='/interpreter/interpreter-home']")
    private WebElement homeInterpreter;

    @FindBy(xpath= "//a[@href='/clients']")
    private WebElement clientsLink;

    public NavigationPanelPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }

    public void click_Home_Interpreter(){

        homeInterpreter.click();
    }

    public void click_Interpreters(){

        interpreters.click();
    }

     public  void clickClientsLinkinLeftPanel() throws InterruptedException {
         clientsLink.click();
         Thread.sleep(2000);
     }



}
