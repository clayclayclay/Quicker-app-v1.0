package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

public class ExcelCreateTest {
	
	public void test() {
		try {
			FileOutputStream out = new FileOutputStream("D:\\excel\\test.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("学生信息统计");
			HSSFRow row0 = sheet.createRow(0);
			HSSFCell cell = row0.createCell(0);
			cell.setCellValue("学号");
			cell = row0.createCell(1);
			cell.setCellValue("姓名");
			wb.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
