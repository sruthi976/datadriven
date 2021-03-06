package utilities;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
Workbook wb;
//constructor for reading excelpath
public ExcelFileUtil(String excelpath) throws Throwable {
	FileInputStream fi = new FileInputStream(excelpath);
	wb = WorkbookFactory.create(fi);
}
//method for counting no of rows in a sheet
public int rowCount(String sheetname) {
	return wb.getSheet(sheetname).getLastRowNum();
}
//method for counting no of rows in a sheet
public int cellCount(String sheetname) {
	return wb.getSheet(sheetname).getRow(0).getLastCellNum();
}
//method for reading cell data
public String getCellData(String sheetname,int row,int column) {
	String data = "";
	if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
		int celldata = (int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
		data = String.valueOf(celldata);
	}
	else
	{
		data = wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
}
//method for cell data
public void setCellData(String sheetname,int row,int column,String status,String writeExcel) throws Throwable {
	//get sheet from workbook
	Sheet ws=wb.getSheet(sheetname);
	//get row from sheet
	Row rowNum=ws.getRow(row);
	//create a cell in row
	Cell cell = rowNum.createCell(column);
	//write status in cell
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("Pass"))
	{
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
	}
	else if (status.equalsIgnoreCase("Fail"))
	{
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Blocked"))
	{
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
	}
	FileOutputStream fo = new FileOutputStream(writeExcel);
	wb.write(fo);
}
}
