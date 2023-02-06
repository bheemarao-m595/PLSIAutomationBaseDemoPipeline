package com.pom;

import com.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Edit_Medical_AppointmentPage {
	public WebDriver driver;


	@FindBy(css= "input[placeholder='Search...']")
	private WebElement Search;

	@FindBy(css= "/html/body/div/div[3]/div/div/div[2]/table/tbody/tr/td[1]")
	private WebElement selectView;

	@FindBy(css= "//img[@class='MuiBox-root css-1vwda0p']")
	private WebElement editAppo;

	@FindBy(css= "//input[@name='AppointmentstartTime']")
	private WebElement appointmentstarttime1;

	@FindBy(xpath= "//input[@name='AppointmentEndTime']")
	private WebElement appointmentendtime1;

	@FindBy(xpath= "//input[@id='react-select-17-input']")
	private WebElement Client1;

	@FindBy(css= "#react-select-22-input")
	private WebElement Requestedlanguage1;

	@FindBy(xpath= "//button[@type='submit']")
	private WebElement savebutton;

	public Edit_Medical_AppointmentPage(WebDriver d){

		driver = d;
		PageFactory.initElements(d,this);

	}


	public void clickEdit(){
		editAppo.click();

	}

	public  void editAppointment(){

		appointmentstarttime1.click();
		appointmentendtime1.sendKeys(BaseClass.datasheet.get(""));

		appointmentendtime1.click();
		appointmentendtime1.sendKeys(BaseClass.datasheet.get(""));
		savebutton.click();

	}


}
