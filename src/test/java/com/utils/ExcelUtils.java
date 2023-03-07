package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

public class ExcelUtils {

	public XSSFWorkbook getWorkbook(String filePath) throws IOException {
		CommonUtils cu = new CommonUtils();
		FileInputStream fis = cu.readFile(filePath);
//		fis.close();
		return new XSSFWorkbook(fis);
	}

	public XSSFSheet getSheet(XSSFWorkbook workbook, String sheetName) throws IOException {
		return workbook.getSheet(sheetName);
	}

	public XSSFSheet getSheet(String filePath, int sheetNumber) throws IOException {
		return getWorkbook(filePath).getSheetAt(sheetNumber);
	}

	public XSSFCell getCellValue(XSSFSheet sheet, int rowNo, int CellNo) {
		return sheet.getRow(rowNo).getCell(CellNo);
	}

	public int getRowByName(XSSFSheet sheet, String cellName) {
		int cellCount = sheet.getRow(0).getLastCellNum();
		int cellNumber = 0;
		for (int i = 0; i < cellCount; i++) {
			String name = sheet.getRow(0).getCell(i).getStringCellValue();
			if (name.equalsIgnoreCase(cellName)) {
				cellNumber = i;
				break;
			}
		}
		return cellNumber;
	}

	XSSFCell getDataByColumnName(XSSFSheet sheet, String cellName, int rowNumber) {
		int cellno = getRowByName(sheet, cellName);
		return sheet.getRow(rowNumber).getCell(cellno);
	}

	public String getDataAsString(XSSFSheet sheet, String cellName, int rowNumber) {
		return getDataByColumnName(sheet, cellName, rowNumber).getStringCellValue();
	}

	public double getDataAsNumeric(XSSFSheet sheet, String cellName, int rowNumber) {
		return getDataByColumnName(sheet, cellName, rowNumber).getNumericCellValue();
	}

	XSSFCell getDataByColumnIndex(XSSFSheet sheet, int cellNumber, int rowNumber) {
		return sheet.getRow(rowNumber).getCell(cellNumber);
	}

	public String getDataAsString(XSSFSheet sheet, int cellNumber, int rowNumber) {
		return getDataByColumnIndex(sheet, cellNumber, rowNumber).getStringCellValue();
	}

	public double getDataNumeric(XSSFSheet sheet, int cellNumber, int rowNumber) {
		return getDataByColumnIndex(sheet, cellNumber, rowNumber).getNumericCellValue();
	}

	public Map<String,String> getMapDataForRowName(Workbook wb, String sheetName, String rowName){

		    Sheet sh = wb.getSheet(sheetName);
		    Map<String,String> inputData =  new LinkedHashMap<>();
			Row firstRow = sh.getRow(0);
		     int noOfColumns = firstRow.getLastCellNum();

			 for(int k=0;k<noOfColumns;k++){

				 Cell c = firstRow.getCell(k);
				 inputData.put(c.toString(),"");

			 }

			 int rowsCount = sh.getPhysicalNumberOfRows();
			 int requiredRowNum =0;
			 for(int i=1;i< rowsCount;i++) {

				 Row tempRow = sh.getRow(i);

				 String data = tempRow.getCell(0).toString();
				 if (data.equalsIgnoreCase(rowName)) {

					 requiredRowNum = i;
					 break;
				 }
			 }

				 Row requiredRow = sh.getRow(requiredRowNum);

				 Iterator<Cell>  cells =  requiredRow.cellIterator();
				 int it =0;
				 while (cells.hasNext()){

					 Cell c = cells.next();
					 String key = firstRow.getCell(it).toString();
					 inputData.put(key,c.toString());
					 it++;

				 }

            return  inputData;

			 }


	public List<Map<String,String>> getMapDataForALlrows(Workbook wb, String sheetName) {


		Sheet sh = wb.getSheet(sheetName);
		Map<String, String> inputData = new LinkedHashMap<>();
		Row firstRow = sh.getRow(0);
		int noOfColumns = firstRow.getLastCellNum();

		Iterator<Row> rows = sh.rowIterator();

		List<Map<String, String>> allRowsMap = new ArrayList<Map<String, String>>();
		int rowsCount =0;
		while (rows.hasNext()) {

			Row r = rows.next();
			if(rowsCount ==0) {
				rowsCount++;

				continue;
			}

			Map<String, String> tempHahMap = new LinkedHashMap<>();
			int columnsCount = r.getLastCellNum();

			for (int j = 0; j < columnsCount; j++) {

				String data = r.getCell(j).toString();
				String key = firstRow.getCell(j).toString();
				tempHahMap.put(key, data);


			}
			allRowsMap.add(tempHahMap);

		}

		return allRowsMap;
	}

	public  String getSheetNameforTestMethod(Workbook wb , String methodName){

		String sheetName = "";
		Sheet sh = wb.getSheet("ReadMe");
		Map<String,String> inputData =  new LinkedHashMap<>();

		int rowsCount = sh.getPhysicalNumberOfRows();
		int requiredRowNum =0;
		boolean found = false;
		for(int i=1;i< rowsCount;i++) {

			Row tempRow = sh.getRow(i);

			String data = tempRow.getCell(0).toString();
			if (data.equalsIgnoreCase(methodName)) {

				requiredRowNum = i;
				found =true;
				break;
			}
		}

		if(found) {

			Row requiredRow = sh.getRow(requiredRowNum);
			Cell cell = requiredRow.getCell(1);
			sheetName = cell.toString();
		}
		else{
			sheetName = "NF";
		}
      return  sheetName;
	}


}
