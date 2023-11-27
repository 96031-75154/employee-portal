package com.emp.main.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emp.main.bean.ExcelSheetRes;
import com.emp.main.bean.FetchEmployeeReq;
import com.emp.main.excelgenerator.ExcelGenerator;
import com.emp.main.service.ExcelService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ExcelSheetController {
	
	@Autowired
	private ExcelService excelService;
	
	/*
	 * API for upload employee details from excel to database 
	 */
	@PostMapping("/uploadEmployeeDetailsToDatabase")
	public ResponseEntity<Boolean> uploadEmployeeDetailsToDatabase(@RequestParam("file") MultipartFile readExcelFile) throws IOException, ParseException {
		
		String fileName = readExcelFile.getOriginalFilename();
		if(!fileName.substring(fileName.length()-5, fileName.length()).equals(".xlsx")) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		Boolean multipartFile = excelService.uploadEmployeeDetailsToDatabase(readExcelFile);
		return new ResponseEntity<Boolean>(multipartFile, HttpStatus.OK);
	}	
	
	/*
	 * API for download employee details from database to excel
	 */
	@PostMapping("/downloadEmployeeDetailsToExcel")
	public ResponseEntity<Boolean> downloadEmployeeDetailsToExcel(@RequestBody FetchEmployeeReq fetchEmployeeReq, HttpServletResponse response) throws IOException {
		
		List<ExcelSheetRes> respList = excelService.downloadEmployeeDetailsToExcel(fetchEmployeeReq);
		if(respList == null || respList.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
		ExcelGenerator excelGenerator = new ExcelGenerator(respList);
		excelGenerator.generateExcelFile(response);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
