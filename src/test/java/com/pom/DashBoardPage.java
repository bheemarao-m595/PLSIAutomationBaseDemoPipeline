package com.pom;

import com.base.BaseClass;
import com.utils.CommonUtils;
import com.utils.DashBoardHeaders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DashBoardPage {

    WebDriver wd;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']")
    private WebElement appointmentsTableinDashBoard;

    @FindBy(css= ".MuiBox-root.css-w2sxf")
    private WebElement Menu;

    @FindBy(xpath= "//input[@class='MuiInputBase-input css-1bqqmdo']")
    private WebElement search;


    @FindBy(css= ".css-1txeit4")
    private WebElement newAppointment;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']")
    private WebElement allAppointmentTable;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody")
    private WebElement allAppointmentTableBody;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr")
    private WebElement allAppointmentTableBodyRows;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[2]")
    private WebElement allAppointmentTableBodyRowsDatecolumn;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[9]")
    private WebElement allAppointmentTableBodyRowsStatusColumn;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[1]")
    private WebElement allAppointmentTableBodyRowsViewColumn;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[7]")
    private WebElement allAppointmentTableBodyRowsPatientColumn;

    @FindBy(xpath= "//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[8]")
    private WebElement allAppointmentTableBodyRowsLanguageColumn;


    @FindBy(css= ".MuiBox-root.css-lhz7xj")
    private WebElement appointmentCreatedSuccessMsg;

    @FindBy(css= ".MuiBox-root.css-8kdvm0")
    private WebElement filter;





    public DashBoardPage(WebDriver d){

        wd = d;
        PageFactory.initElements(d,this);

    }


    public  void clickSearchInHomePage(String keyword){


        search.sendKeys(keyword);
    }

    public  WebElement getWebElementOfHeaderAndCellValue(DashBoardHeaders dbh, String cellValue){

         WebElement  appId = null;
         int i  =1;
         List<WebElement> rows =  wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']//th/div[1]"));
         Map<String,Integer> headIndex =new LinkedHashMap<>();


         for( WebElement r :  rows){

              String val = r.getText();
              String firtword =  val.split("\\R")[0];
                  headIndex.put(firtword,i);
              i++;
         }

         for (Map.Entry<String,Integer> e : headIndex.entrySet()){

             System.out.println(e.getKey() + "-->" + e.getValue());

         }

         DashBoardHeaders searchString = dbh;
        String  actualHeader =CommonUtils.getActualHeaderStringFromDashBoardTable(searchString);
        int headerIndex =  headIndex.get(actualHeader);

        int recordsCount = wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']//tbody//tr")).size();
        Assert.assertNotEquals(recordsCount,0,"Table is empty");
        for(int rowNumber=1;rowNumber < recordsCount;rowNumber++){

            String part1 = "//table[@class='MuiTable-root css-jiyur0']//tbody//tr[";
            String part2 ="]//td//*[text()='"+ cellValue +  "']";
            String part3 = "//td//*[text()='"+ cellValue +"']/ancestor::td/preceding-sibling::td[";

            BaseClass b = new BaseClass();
        boolean recordTEextMatching =    b.isElementByXpath(wd, part1 +rowNumber + part2);
        if(recordTEextMatching) {

           appId =   b.getElementByXpath(wd, (part3 + ( headerIndex -1 ) + "]"));
           break;

        }
        }
        System.out.println(appId.getText());

        return appId;


    }

    public  WebElement get_allAppointmentTable(){

        return allAppointmentTable;
    }

    public  List<WebElement> get_allAppointmentTableBodyRowsStatusColumn(){

        return  wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[9]"));

    }

    public  List<WebElement> get_allAppointmentTableBodyRowsLanguageColumn() {
        return wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[8]"));
    }

    public List<WebElement> get_allAppointmentTableBodyRowsViewColumn() {
        return wd.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[1]"));
    }


    public  void search(String searchFor) throws InterruptedException {
        search.sendKeys(searchFor);
        Thread.sleep(3000);
    }







}
