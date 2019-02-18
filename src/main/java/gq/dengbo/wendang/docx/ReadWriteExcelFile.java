package gq.dengbo.wendang.docx;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteExcelFile {
	public static void main(String[] args) throws Exception {
//		writeSLSFile();
//		readXLSFile();
		
//		writeXLSXFile();
		readXLSXFile();
	}

	private static void writeXLSXFile() throws Exception {
		String excelFileName = "Test.xlsx";
		String sheetName = "Sheet1";
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);
		
		for (int i = 0; i < 5; i++) {
			XSSFRow row = sheet.createRow(i);
			
			for (int j = 0; j < 5; j++) {
				XSSFCell cell = row.createCell(j);
				
				cell.setCellValue("Cell "+ i +" "+j);
			}
		}
		FileOutputStream fileOut = new FileOutputStream(excelFileName);
		
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		
	}

	public static void readXLSXFile() throws IOException{
		InputStream ExcelFileToRead = new FileInputStream("Test.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		
		Iterator rows = sheet.rowIterator();
		
		while (rows.hasNext()) {
			row = (XSSFRow)rows.next();
			Iterator cells = row.cellIterator();
			while (cells.hasNext()) {
				cell = (XSSFCell)cells.next();
				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
					System.out.print(cell.getStringCellValue()+" ");
				}
				else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					System.out.print(cell.getNumericCellValue()+" ");
				}
				else {
					
				}
			}
			System.out.println();
		}
		
		
	}
}
