package com.listeners;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.utils.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ListenerTest implements ITestListener {

	@Override
	public void onFinish(ITestContext result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String t =result.getThrowable().getMessage();


	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

		String methodName = result.getMethod().getMethodName();
		BaseClass b = new BaseClass();
		b.setMethodName(methodName);

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
		ExcelUtils data = new ExcelUtils();
		try {
			wb = data.getWorkbook(testDataFilePath);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String sheetName = data.getSheetNameforTestMethod(wb,methodName);
		if(sheetName.equalsIgnoreCase("NF"))
		{
			BaseClass.datasheet.put("UserName","Ravi.thota@sstech.us");
			BaseClass.datasheet.put("Password","Welcome@1");
		}
		else
		{
			BaseClass.setModuleName(sheetName);
			if(wb  != null)
				BaseClass.datasheet = data.getMapDataForRowName(wb,sheetName,methodName);
		}

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}
}
