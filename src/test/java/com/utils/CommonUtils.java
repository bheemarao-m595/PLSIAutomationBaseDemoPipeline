package com.utils;

import com.base.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class CommonUtils {
	
	public FileInputStream readFile(String filePath) {
		File file =    new File(filePath);
		FileInputStream read = null;
		try {
			read =  new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			 new FileNotFoundException("File not found in >>> "+ filePath);
		}
		return read;
	}

	public  static int getRandomNumberOfRange(int n){

		Random r = new Random();
		r.nextInt(n);
	  return 	r.nextInt();

	}

	public   static  String getRandomStringOfLength(int n){

		String st = "abcdefghijklmnopqrstuvwxyz";

		StringBuffer bf = new StringBuffer();


		for(int i=0;i<5;i++){

			Random r = new Random();
		 int charAt =	r.nextInt(25);
		   bf.append(st.charAt(charAt));
		}
		return  bf.toString();

	}


}
