package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.utils.ExcelUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass
{
    public static 	ExtentHtmlReporter htmlReporter;
	public  static WebDriver driver = null;
    public static ExtentReports extent;
	public static XSSFWorkbook workbook = null;
	public ExcelUtils data;
	static String methodName = "";

	public static String getModuleName() {
		return moduleName;
	}

	public static void setModuleName(String moduleName) {
		BaseClass.moduleName = moduleName;
	}

	static String moduleName = "";
   public  static 	ExtentTest logger = null;

	public static  Map<String,String> datasheet = new HashMap<>();

	public static  String getMethodName() {

		return methodName;
	}

	public void setMethodName(String methodName) {

		this.methodName = methodName;
	}

	
	@BeforeSuite
	public void start() throws IOException {

		String basePath = System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\";
		String proppath = System.getProperty("user.dir") + "\\src\\main\\resources\\Application.properties";
		FileInputStream fin = new FileInputStream(proppath);
		Properties prop = new Properties();
		prop.load(fin);

		String timeStamp = new SimpleDateFormat("dd-MM-YYYY_HH-mm-ss").format(new Date());
		String reportPath = System.getProperty("user.dir") + "\\Reports\\Report_" + timeStamp + ".html";
		System.out.println("report path : " + reportPath);
		htmlReporter = new ExtentHtmlReporter(reportPath);
        extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", prop.getProperty("application"));
		extent.setSystemInfo("Environment", prop.getProperty("environment"));
		extent.setSystemInfo("User Name", prop.getProperty("author"));
		data = new ExcelUtils();

		String testDataFile =  prop.getProperty("testDataFile");
		String fp = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\" + testDataFile;
		//XSSFWorkbook wb = data.getWorkbook(fp);
		//datasheet = data.getMapDataForRowName(wb,"ClientMedical","CreateNew");
	}
		
	
	public XSSFSheet readSheet(String sheetName) throws IOException {
		return data.getSheet(workbook, sheetName);
	}
	
	
	public WebDriver openBrowser(String browserType) throws IOException {

		String basePath = System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\";
		String proppath = System.getProperty("user.dir") + "\\src\\main\\resources\\Application.properties";
		FileInputStream fin = new FileInputStream(proppath);
		Properties prop = new Properties();

		prop.load(fin);
		if(browserType.toLowerCase().equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", basePath+"geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(prop.get("driver").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", basePath+"chromedriver.exe");
			ChromeOptions opt  = new ChromeOptions();
			opt.addArguments("--incognito");
			driver = new ChromeDriver(opt);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get(prop.getProperty("url"));


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

	@AfterSuite
	public  void tearDown(){

		extent.flush();
		//driver.close();
	}



}
 