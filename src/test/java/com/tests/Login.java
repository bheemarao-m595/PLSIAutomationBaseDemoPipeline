package com.tests;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v108.network.model.Response;
import org.openqa.selenium.devtools.v108.network.Network;

import com.base.BaseClass;
import com.pom.LoginPage;
import com.utils.SeleniumUIUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class Login   {
	LoginPage login = new LoginPage();
	ExtentTest logger;
	ExtentReports extent;
	public static 	ExtentHtmlReporter htmlReporter;


	@Test
	public void  testing()
	{


		{


//		WebDriverManager.chromedriver().browserInDocker().browserVersion("latest-5");
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Johnraju.chinnam.SSTECH\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
			ChromeDriver chromeDriver = new ChromeDriver();
			DevTools chromeDevTools = chromeDriver.getDevTools();
			chromeDevTools.createSession();

			chromeDevTools.send(Network.enable(Optional.of(1000000), Optional.empty(), Optional.empty()));
			chromeDevTools.addListener(Network.responseReceived(), response -> {
						System.out.println(response.getResponse().getStatus());
						System.out.println(response.getResponse().getUrl());
					});

				chromeDriver.get("https://qa20.elysiumanalytics.ai/");

			String timeStamp = new SimpleDateFormat("dd-MM-YYYY_HH-mm-ss").format(new Date());
			String reportPath = System.getProperty("user.dir")+"\\Reports\\Report_"+timeStamp+".html";
			System.out.println("report path : "+ reportPath);
			htmlReporter = new ExtentHtmlReporter(reportPath);

			extent = new ExtentReports ();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
			extent.setSystemInfo("Environment", "Automation Testing");
			extent.setSystemInfo("User Name", "Rajkumar SM");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			logger = 	extent.createTest("starting home page");
			chromeDriver.findElement(By.id("txt-email")).sendKeys("sstech.admin@sstech.us");
			chromeDriver.findElement(By.id("txt-password")).sendKeys("pssword");
			chromeDriver.findElement(By.id("btn-login")).click();
			Assert.assertTrue(true);
				logger.log(Status.PASS,"John");


		/*	chromeDriver.findElement(By.id("txt-email")).sendKeys("sstech.admin@sstech.us");
			chromeDriver.findElement(By.id("txt-password")).sendKeys("pssword");
			chromeDriver.findElement(By.id("btn-login")).click();*/




   extent.flush();



		}
	}
	
	

	
}
