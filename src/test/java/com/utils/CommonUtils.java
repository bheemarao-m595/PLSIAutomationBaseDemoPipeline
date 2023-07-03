package com.utils;


import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
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


		for(int i=0;i<n;i++){

			Random r = new Random();
		 int charAt =	r.nextInt(25);
		   bf.append(st.charAt(charAt));
		}
		return  bf.toString();

	}
	public   static  String getRandomNumberOfLength(int n){

		String st = "0123456789";

		StringBuffer bf = new StringBuffer();


		for(int i=0;i<n;i++){

			Random r = new Random();
			int charAt =	r.nextInt(9);
			bf.append(st.charAt(charAt));
		}
		return  bf.toString();

	}

	public  static  String getActualHeaderStringFromDashBoardTable(DashBoardHeaders dbh){

        String actualHeader = "";
		switch (dbh) {
			case VIEW:
				actualHeader = "VIEW";
				break;
			case DATE:
				actualHeader = "DATE";
				break;
			case START:
				actualHeader = "START";
				break;
			case END:
				actualHeader = "END";
				break;
			case CHECKINDURATION:
				actualHeader = "CHECK IN DURATION";
				break;
			case MRN_RN:
				actualHeader = "MRN/RN";
				break;
			case  PATIENT_CONSUMER:
				actualHeader = "PATIENT/CONSUMER";
				break;
			case REQUESTED_LANGUAGE:
				actualHeader = "REQUESTED LANGUAGE";
				break;
			case STATUS:
				actualHeader = "STATUS";
				break;
			case INTERPRETER:
				actualHeader ="INTERPRETER";
				break;
			case  CLIENT:
				actualHeader = "CLIENT";
				break;
			case  FACILITY:
				actualHeader = "FACILITY";
				break;
			case BUILDING:
				actualHeader = "BUILDING";
				break;
			case CLINIC_DEPARTMENT:
				actualHeader = "CLINIC / DEPARTMENT";
				break;
			case  APPT_TYPE:
				actualHeader = "APPT TYPE";
				break;
			case  ACTIONS:
				actualHeader  = "ACTIONS";
				break;

		}
		return actualHeader;
	}

	public static String getCurrentSystemDate()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		 return  dtf.format(now);
	}

	public static String getCurrentSystemDateyear()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		return  dtf.format(now);
	}
	public  static  String addMinutesToCurrentTime(int minToAdd){

		Calendar currentTimeNow = Calendar.getInstance();
		currentTimeNow.add(Calendar.MINUTE, minToAdd);
		SimpleDateFormat formatDate = new SimpleDateFormat("hh:mma");
		return  formatDate.format(currentTimeNow.getTime()).toString();

	}

	public static String readPropertiesFileValues(String filePath, String key){

		String val ="";
		try {
			String fullPath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + filePath;
			FileInputStream fin = new FileInputStream(fullPath);
			Properties prop = new Properties();
			prop.load(fin);

		val =	prop.get(key).toString();

		}catch (Exception e){
			e.printStackTrace();
		}
		return  val;

	}
	public static   void writeToPropertiesFile(String filePath, String key, String val){

		FileOutputStream outputStream = null;
		try{

			String fullPath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + filePath;
			PropertiesConfiguration props = new PropertiesConfiguration(fullPath);
			props.setProperty(key,val);
			props.save();
		}catch (Exception e){
       e.printStackTrace();
		}
	}

	public   static  String getRandomEmailIdDomain(String domain){

	String emaildId= getRandomStringOfLength(5);
	  return  emaildId + "@" + domain;

	}


}
