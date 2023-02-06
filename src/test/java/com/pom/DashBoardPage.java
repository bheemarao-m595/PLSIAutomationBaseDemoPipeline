package com.pom;

import com.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashBoardPage {

    WebDriver wd;
    @FindBy(xpath= "//input[@placeholder='Search...']")
    private WebElement search;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']")
    private WebElement appointmentsTableinDashBoard;




    public DashBoardPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }


    public  void clickSearchInHomePage(String keyword){


        search.sendKeys(keyword);
    }

    public  WebElement getWebElementOfHeader(String header,String cellValue){

         List<WebElement> rows =  wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']//th/div[1]"));


          Map<String,Integer> headIndex =new HashMap<>();

           int i  =1;
         for( WebElement r :  rows){

              String val = r.getText();
              String firtword =  val.split("\\R")[0];
                  headIndex.put(firtword,i);
              i++;
         }


         for (Map.Entry<String,Integer> e : headIndex.entrySet()){

             System.out.println(e.getKey() + "-->" + e.getValue());

         }

         System.out.println("hi");
        WebElement  appId = null;

        int headerIndex =  headIndex.get(header);

        int recordsCount = wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']//tbody//tr")).size();

        for(int rowNumber=1;rowNumber < recordsCount;rowNumber++){

            String part1 = "//table[@class='MuiTable-root css-jiyur0']//tbody//tr[";
            String part2 ="]//td//*[text()='"+ cellValue +  "']";
            String part3 = "//td//*[text()='New']/ancestor::td/preceding-sibling::td[";

            BaseClass b = new BaseClass();
        boolean recordTEextMatching =    b.isElementByXpath(wd, part1 +rowNumber + part2);
        if(recordTEextMatching) {

           appId =   b.getElementByXpath(wd, (part3 + ( 16 - headerIndex + 1 ) + "]"));
           break;

        }


        }
        System.out.println(appId.getText());

        return appId;


////table[@class='MuiTable-root css-jiyur0']//tbody//tr//span[8]/../../../../preceding-sibling::td[8]/div
    }
}
