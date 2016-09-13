package com.quicker.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

//excel处理工具类
public class ExcelUtil {

	/**
	 * excel表解析
	 * 
	 * @param inputStream
	 * @throws Exception
	 */
	
	public List<String> parseExcel(InputStream inputStream) throws Exception {
		Workbook wb = WorkbookFactory.create(inputStream);
		List<String> result = new ArrayList<String>();

		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(0);
		for (int cellIndex = 0; cellIndex < sheet.getRow(0).getLastCellNum(); cellIndex++) {
			row.getCell(cellIndex).setCellType(Cell.CELL_TYPE_STRING);
			result.add(
					sheet.getRow(0).getCell(cellIndex).getStringCellValue());
		}
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + "   ");
		}
		return result;
	}
}
