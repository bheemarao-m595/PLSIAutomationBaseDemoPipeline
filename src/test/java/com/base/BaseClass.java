package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;

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
	public  static ExtentTest logger = null;
	static String moduleName = "";
	String reportPath ="FinalReport.html";

	public static String getModuleName() {
		return moduleName;
	}

	public static void setModuleName(String moduleName) {
		BaseClass.moduleName = moduleName;
	}



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
		reportPath = System.getProperty("user.dir") + "\\Reports\\Report_" + timeStamp + ".html";
		htmlReporter = new ExtentHtmlReporter(reportPath);
        extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", prop.getProperty("application"));
		extent.setSystemInfo("Environment", prop.getProperty("environment"));
		extent.setSystemInfo("User Name", prop.getProperty("author"));
		data = new ExcelUtils();
		driver =openBrowser();
		driver.manage().window().maximize();


	}
		
	
	public XSSFSheet readSheet(String sheetName) throws IOException {
		return data.getSheet(workbook, sheetName);
	}
	
	
	public WebDriver openBrowser() throws IOException {

		String basePath = System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\";
		String proppath = System.getProperty("user.dir") + "\\src\\main\\resources\\Application.properties";
		FileInputStream fin = new FileInputStream(proppath);
		Properties prop = new Properties();

		prop.load(fin);
		if(prop.get("driver").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", basePath+"geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(prop.get("driver").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", basePath+"chromedriver.exe");
			ChromeOptions opt  = new ChromeOptions();
			opt.addArguments("--incognito");
			driver = new ChromeDriver(opt);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get(prop.getProperty("url"));
			try {
				Thread.sleep(1000);
			}catch (InterruptedException intr){
				intr.printStackTrace();
			}



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

	public  List<WebElement> getAllElementsByXpath(WebDriver d ,String xpath){


	return 	d.findElements(By.xpath(xpath));

	}


	public  boolean isElementByXpath(String xpath){


		boolean found = false;

		try{
			WebElement w =  driver.findElement(By.xpath(xpath));
			found = w.isEnabled();
		}catch (NoSuchElementException e){

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

	public WebDriver getDriver() throws IOException {

		if(driver !=null){

			return  driver;
		}
		else
			return 	openBrowser();
	}

	public static  String takeScreenshotForStep(String step){
		File SrcFile, DestFile = null;
		String timeStamp = new SimpleDateFormat("dd-MM-YYYY_HH-mm-ss").format(new Date());

		try {
			String basePath = System.getProperty("user.dir")+"\\ScreenShots\\";
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			DestFile=new File(basePath+timeStamp+"_"+step+".png");
			FileUtils.copyFile(SrcFile, DestFile);
		}catch(Exception ee) {
			ee.printStackTrace();
		}
		return DestFile.getAbsolutePath();
	}

	/**
	 * Author : John
	 * Purpose : It will take the screenshot of the individual element in focus
	 * @param step
	 * @param el
	 * @return returns path of the screenshot taken
	 */
	public String takeScreenshotForStep(String step, WebElement el){
		File SrcFile, DestFile = null;
		try {      String basePath = System.getProperty("user.dir")+"\\ScreenShots\\";
			SrcFile =  el.getScreenshotAs(OutputType.FILE);
			DestFile=new File(basePath+step+".png");
			FileUtils.copyFile(SrcFile, DestFile);   }
	catch(Exception ee) {
		ee.printStackTrace();
	}   return DestFile.getAbsolutePath();}

	@AfterSuite
	public  void tearDown() throws IOException {

		extent.flush();

		File fileOrg = new File(reportPath);
		File fileDest = new File(System.getProperty("user.dir")+"\\Reports\\FinalReport.html");

		FileUtils.copyFile(fileOrg,fileDest);
		//driver.close();
		driver.quit();
	}

	/**
	 * Author: John
	 * Purpose : It will return boolean value based on the clickability of the element and visibility
	 *
	 * @param el
	 * @return
	 */
	public static boolean isElementPresent(WebElement el){

		try{

			WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		    WebElement web =	w.until(ExpectedConditions.elementToBeClickable(el));
		    return  web.isDisplayed();

		}catch (Exception e){
			return  false;
		}

	}
	public static  void waitforElementToMakeClickable(WebElement el){

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(el));
		}catch (Exception e){
			e.printStackTrace();
		}


	}

	public static String getFilePathOfTestDataFile(){

		Properties prop = new Properties();
		String proppath = System.getProperty("user.dir") + "\\src\\main\\resources\\Application.properties";

		try {
			FileInputStream fin = new FileInputStream(proppath);
			prop.load(fin);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		XSSFWorkbook wb;
		String testDataFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\" +prop.get("testDataFile");

		 return  testDataFilePath;
	}

	/**
	 * Author: John
	 * Purpose: It will go to the visible area of the element passed
	 * @param el  Element to be make visible
	 */
	public static  void goToElementVisibleArea(WebElement el) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",el);
		Thread.sleep(2000);

	}

	public static void clickWithJavaScript(WebElement element) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}

	public int readNumberOfRowsInTable(WebElement ele) {//this reads number of rows in any given table.

		List<WebElement> rowList = ele.findElements(By.tagName("tr"));
		return rowList.size();

	}

	/**
	 * Author: Ashwini
	 * Purpose: It will if the list of words are in alphabetical order.
	 */

	public boolean stringSort(List<String> words){
		for(int a=0;a<words.size()-1;a++)
		{
			if(words.get(a).compareToIgnoreCase(words.get(a+1))>0)
			{
				return false;
			}
		}
		return true;
	}

	public static boolean isDataPresentinTable(String xpathOfTable, String targetData, boolean headerPresent){

		System.out.println(targetData);
		boolean found = false;
		List<WebElement> rows = driver.findElements(By.xpath(xpathOfTable+"//tr"));
		for(WebElement row : rows){

			List<WebElement> tds = row.findElements(By.tagName("td"));
			for(WebElement td :tds){

				String cellData = td.getText();
				if(cellData != null && (!cellData.isEmpty()))
					if(targetData.equalsIgnoreCase(cellData)) {
						found = true;
						break;
					}
			}
		}
		return found;
	}



}
 