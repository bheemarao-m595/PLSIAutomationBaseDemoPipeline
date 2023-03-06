package com.pom;

import com.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static com.base.BaseClass.logger;
public class ClientDashBoardPage {


    WebDriver wd;
    public ClientDashBoardPage(WebDriver d){
        wd = d;
        PageFactory.initElements(d,this);

    }

    @FindBy(xpath= "//button[text()='+ add Client']")
    private WebElement addClientButton;

    @FindBy(xpath= "//input[@placeholder='Search...']")
    private WebElement searchBox;





    public void clickClientElementWithName(String name) throws InterruptedException {

        BaseClass b = new BaseClass();
        String xpath = "//table[@class='MuiTable-root css-jiyur0']//tr//td/div/div[text()='" + name + "']";
        boolean clientPresent = b.isElementByXpath(xpath);
        Assert.assertTrue(clientPresent,"Client not present with name :" + name);
        WebElement clientELe = b.getElementByXpath(wd,xpath);
        clientELe.click();
        Thread.sleep(3000);

    }

    public  void searchClient(String clientName){

        searchBox.sendKeys(clientName);
    }

    public boolean isClientDetailsWindowDisplayedfor(String client){

        String xpath = "//div[@role='dialog']//p[text()='Client Details for "+client+"']";
        BaseClass b = new BaseClass();
        boolean bool = b.isElementByXpath(xpath);
        return  bool;

    }
}
