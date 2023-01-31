package com.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
	import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.utils.ExcelUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass
{
   public static 	ExtentHtmlReporter htmlReporter;

	 public  static WebDriver driver = null;
 public ExtentReports extent;
	ExtentTest logger;
	public static XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;    
	public ExcelUtils data;
	public static String browserType = "chrome";
//	public DriverManager dm = new DriverManager();

	public static  Map<String,String> datasheet = new HashMap<>();

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	static String methodName = "";
	
	
	@BeforeSuite
	public void start() throws IOException {
		String timeStamp = new SimpleDateFormat("dd-MM-YYYY_HH-mm-ss").format(new Date());
		String reportPath = System.getProperty("user.dir") + "\\Reports\\Report_" + timeStamp + ".html";
		System.out.println("report path : " + reportPath);
		htmlReporter = new ExtentHtmlReporter(reportPath);
       extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "PLSI");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "John ch");
		data = new ExcelUtils();
		String fp = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\PLSI_Automation_Data.xlsx";
		XSSFWorkbook wb = data.getWorkbook(fp);
//		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\Project.xlsx";
//		workbook = data.getWorkbook(path);
		datasheet = data.getMapDataForRowName(wb,"ClientMedical","CreateNew");
	   System.out.println(datasheet.size());
	}
		
	
	public XSSFSheet readSheet(String sheetName) throws IOException {
		return data.getSheet(workbook, sheetName);
	}
	
	
	public WebDriver openBrowser(String browserType) {

		String basePath = System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\";
		if(browserType.toLowerCase().equals("firefox")) {
	      //   System.setProperty("webdriver.gecko.driver", basePath+"geckodriver.exe");
	         WebDriverManager.firefoxdriver().setup();
	         driver = new FirefoxDriver();
		}else if(browserType.toLowerCase().equals("chrome")) {
			//WebDriverManager.chromedriver().setup();

			System.out.println(basePath);
	       //  System.setProperty("webdriver.chrome.driver", "C:\\Users\\Johnraju.chinnam.SSTECH\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
	         System.setProperty("webdriver.chrome.driver", basePath+"chromedriver.exe");
	         ChromeOptions opt  = new ChromeOptions();
			 opt.addArguments("--incognito");
			 driver = new ChromeDriver(opt);


		}else {
	         throw new IllegalArgumentException("The Browser Type is Undefined");
		}
		
		return driver;
	}
	
	


	public  WebElement getElementByXpath(WebDriver d ,String xpath){


		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(3));
		boolean found = false;
		WebElement w = null;

		try{
			 w = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			found = w.isDisplayed();

		}catch (NoSuchElementException e){

			e.printStackTrace();
			found = false;
		}catch (Exception e){
			e.printStackTrace();
			found = false;
		}
		finally {
			return w;
		}
	}

	public  boolean isElementByXpath(WebDriver d ,String xpath){


		boolean found = false;

		try{
			WebElement w =  d.findElement(By.xpath(xpath));
			found = w.isDisplayed();

		}catch (NoSuchElementException e){

			 e.printStackTrace();
			 found = false;
		}catch (Exception e){
			e.printStackTrace();
			found = false;
		}
		finally {
               return  found;
		}

	}

	public  void clickElement(WebElement w)
	{

		w.click();
	}

	public WebDriver getDriver(){

		if(driver !=null){

			return  driver;
		}
		else
			return 	null;
	}

	public String takeScreenshotForStep(String step){
		File SrcFile, DestFile = null;

		try {
			String basePath = System.getProperty("user.dir")+"\\ScreenShots\\";
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			DestFile=new File(basePath+step+".png");
			FileUtils.copyFile(SrcFile, DestFile);
		}catch(Exception ee) {
			ee.printStackTrace();
		}
		return DestFile.getAbsolutePath();
	}


}
 