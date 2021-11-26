package com.allenplus.genric;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLib {
	/**
	 *@author Mayank
	 */
	
	static String  filepath="C:/Users/ginger/git/AllenDSAT/TestData/TestData1.xls";
	/**
	 * @description this Method is used to read data from excel sheet
	 * @param rowNum
	 * @param cellNum
	 * @return 
	 */
	public static String readExcelData(String sheet,int rowNum, int cellNum){
		
//		@SuppressWarnings("unused")
		String data="";
		
		try 
		{
			HSSFWorkbook wb=(HSSFWorkbook) WorkbookFactory.create(new FileInputStream(new File(filepath)));
			data=wb.getSheet(sheet).getRow(rowNum).getCell(cellNum).getStringCellValue();
		}
		catch(EncryptedDocumentException e)
		{
			e.printStackTrace();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(InvalidFormatException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return data;
	}
	
	
	/**
	 * @description This method is Used to write Data to excel sheet
	 * @param rowNum
	 * @param cellNum
	 */

	@SuppressWarnings("deprecation")
	public static void writeExcelData(String sheet, int rowNum, int cellNum) {
		
		try 
		{
			Workbook wb=WorkbookFactory.create(new FileInputStream(new File(filepath)));
			Cell c=wb.getSheet(sheet).getRow(rowNum).createCell(cellNum);
			
			c.setCellType(Cell.CELL_TYPE_STRING);
			FileOutputStream fos=new FileOutputStream(new File(filepath));
			
			c.setCellValue("Pass");
			wb.write(fos);
		}
		catch(EncryptedDocumentException e)
		{
			e.printStackTrace();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(InvalidFormatException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
