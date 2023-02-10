package com.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationPanelPage {

    WebDriver wd;

    public NavigationPanelPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }
    @FindBy(xpath= "//a[@href='/interpreters']")
    private WebElement Interpreters;

    @FindBy(xpath= "//a[@href='/interpreter/interpreter-home']")
    private WebElement Home_Interpreter;

    public void click_Home_Interpreter(){
        Home_Interpreter.click();
    }

    public void click_Interpreters(){
        Interpreters.click();
    }




}
