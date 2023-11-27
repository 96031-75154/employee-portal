package com.emp.main.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emp.main.bean.ExcelSheetRes;
import com.emp.main.bean.FetchEmployeeReq;
import com.emp.main.bean.InsertEmployeeReq;
import com.emp.main.dao.ExcelDao;

@Service
public class ExcelServiceImpl implements ExcelService {

	@Autowired
	private ExcelDao excelDao;
	
	/*
	 * Method to implement upload employee details to database
	 */
	@SuppressWarnings({ "resource", "unused", "deprecation" })
	@Override
	public Boolean uploadEmployeeDetailsToDatabase(MultipartFile readExcelFile) throws IOException, ParseException {

		List<InsertEmployeeReq> employeeList = new ArrayList<>();
		XSSFWorkbook workbook = new XSSFWorkbook(readExcelFile.getInputStream());
		XSSFSheet workSheet = workbook.getSheetAt(0);
		
		Integer count = 0;
		for (Row row : workSheet) {
			if (count == 0) {
				count++;
				continue;
			}
			InsertEmployeeReq employeeReq = new InsertEmployeeReq();

			employeeReq.setOrgId((long) row.getCell(0).getNumericCellValue());
			employeeReq.setFirstNm(row.getCell(1).getStringCellValue());
			employeeReq.setMiddleNm(row.getCell(2).getStringCellValue());
			employeeReq.setLastNm(row.getCell(3).getStringCellValue());
			employeeReq.setGender(row.getCell(4).getStringCellValue());
			java.util.Date utilDate = row.getCell(5).getDateCellValue();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getDate());
			employeeReq.setBirthDt(sqlDate);
			employeeReq.setAge((int) row.getCell(6).getNumericCellValue());
			employeeReq.setBloodGp(row.getCell(7).getStringCellValue());
			employeeReq.setReligion(row.getCell(8).getStringCellValue());
			employeeReq.setSalary(row.getCell(9).getNumericCellValue());
			if(row.getCell(10).getCellType() == CellType.NUMERIC) {
				employeeReq.setMobileNo(String.valueOf(row.getCell(10).getNumericCellValue()));
			}
			employeeReq.setEmail(row.getCell(11).getStringCellValue());
			if(row.getCell(12).getCellType() == CellType.NUMERIC) {
				employeeReq.setPincode(String.valueOf(row.getCell(12).getNumericCellValue()));
			}
			employeeReq.setStreetNm(row.getCell(13).getStringCellValue());
			employeeReq.setCityNm(row.getCell(14).getStringCellValue());
			employeeReq.setCountryNm(row.getCell(15).getStringCellValue());

			employeeList.add(employeeReq);
			count++;
		}
		return count - 1 == workSheet.getPhysicalNumberOfRows() - 1 && excelDao.uploadEmployeeDetailsToDatabase(employeeList);
	}

	
	/*
	 * Method to implement download employee details to excel sheet
	 */
	@SuppressWarnings("unused")
	@Override
	public List<ExcelSheetRes> downloadEmployeeDetailsToExcel(FetchEmployeeReq fetchEmployeeReq) {
		
		List<Map<String, Object>> respList = excelDao.downloadEmployeeDetailsToExcel(fetchEmployeeReq);
		List<ExcelSheetRes> excelSheetResList = null;
		
		if(CollectionUtils.isNotEmpty(respList)) {
			excelSheetResList = respList.stream().map(obj -> {
				ExcelSheetRes sheetRes = new ExcelSheetRes();
				
				sheetRes.setOrgId((Long) obj.get("org_id"));
				sheetRes.setEmpId((Long) obj.get("emp_id"));
				sheetRes.setFirstNm((String) obj.get("first_nm"));
				sheetRes.setMiddleNm((String) obj.get("middle_nm"));
				sheetRes.setLastNm((String) obj.get("last_nm"));
				sheetRes.setGender((String) obj.get("gender"));
				sheetRes.setBirthDt((java.sql.Date) obj.get("birth_dt"));
				sheetRes.setAge((Integer) obj.get("age"));
				sheetRes.setBloodGp((String) obj.get("blood_gp"));
				sheetRes.setReligion((String) obj.get("religion"));
				sheetRes.setSalary((BigDecimal) obj.get("salary"));
				sheetRes.setMobileNo((String) obj.get("mobile_no"));
				sheetRes.setEmail((String) obj.get("email"));
				sheetRes.setPincode((String) obj.get("pincode"));
				sheetRes.setStreetNm((String) obj.get("street_nm"));
				sheetRes.setCityNm((String) obj.get("city_nm"));
				sheetRes.setCountryNm((String) obj.get("country_nm"));
				return sheetRes;
			}).toList();
		}
		return excelSheetResList;
	}
}
