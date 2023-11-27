package com.emp.main.excelgenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.emp.main.bean.ExcelSheetRes;

import jakarta.servlet.http.HttpServletResponse;

public class ExcelGenerator {
	
	@SuppressWarnings("unused")
	private List<ExcelSheetRes> excelSheetResList;
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
	
	public ExcelGenerator(List<ExcelSheetRes> excelSheetResList) {
		this.excelSheetResList = excelSheetResList;
		this.workbook = new XSSFWorkbook();
	}
	
	/* 
	 * create header row columns
	 */
	@SuppressWarnings("unused")
	private void writeHeader() {
		sheet = workbook.createSheet("excelsheet");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "orgId", style);
        createCell(row, 1, "empId", style);
        createCell(row, 2, "firstNm", style);
        createCell(row, 3, "middleNm", style);
        createCell(row, 4, "lastNm", style);
        createCell(row, 5, "gender", style);
        createCell(row, 6, "birthDt", style);
        createCell(row, 7, "age", style);
        createCell(row, 8, "bloodGp", style);
        createCell(row, 9, "religion", style);
        createCell(row, 10, "salary", style);
        createCell(row, 11, "mobileNo", style);
        createCell(row, 12, "email", style);
        createCell(row, 13, "pincode", style);
        createCell(row, 14, "streetNm", style);
        createCell(row, 15, "cityNm", style);
        createCell(row, 16, "countryNm", style);
    }
	
	/* 
	 * Validate cell data
	 */
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if(valueOfCell instanceof BigDecimal) {
        	cell.setCellValue(((BigDecimal) valueOfCell).doubleValue());
        } else if(valueOfCell instanceof java.sql.Date) {
        	cell.setCellValue((java.sql.Date) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }
    
    /* 
	 * Iterate the database records and insert into cell
	 */
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (ExcelSheetRes record: excelSheetResList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getOrgId(), style);
            createCell(row, columnCount++, record.getEmpId(), style);
            createCell(row, columnCount++, record.getFirstNm(), style);
            createCell(row, columnCount++, record.getMiddleNm(), style);
            createCell(row, columnCount++, record.getLastNm(), style);
            createCell(row, columnCount++, record.getGender(), style);
            createCell(row, columnCount++, record.getBirthDt(), style);
            createCell(row, columnCount++, record.getAge(), style);
            createCell(row, columnCount++, record.getBloodGp(), style);
            createCell(row, columnCount++, record.getReligion(), style);
            createCell(row, columnCount++, record.getSalary(), style);
            createCell(row, columnCount++, record.getMobileNo(), style);
            createCell(row, columnCount++, record.getEmail(), style);
            createCell(row, columnCount++, record.getPincode(), style);
            createCell(row, columnCount++, record.getStreetNm(), style);
            createCell(row, columnCount++, record.getCityNm(), style);
            createCell(row, columnCount++, record.getCountryNm(), style);
        }
    }
    
    /* 
	 * Generate excel sheet
	 */
	public void generateExcelFile(HttpServletResponse response) throws IOException {
		writeHeader();
		write();
		FileOutputStream outputStream = new FileOutputStream("D:\\downloademp.xlsx");
		try {
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		workbook.close();
		outputStream.close();
	}
}
