package com.emp.main.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.emp.main.bean.ExcelSheetRes;
import com.emp.main.bean.FetchEmployeeReq;

public interface ExcelService {

	public Boolean uploadEmployeeDetailsToDatabase(MultipartFile readExcelFile) throws IOException, ParseException;
	
	public List<ExcelSheetRes> downloadEmployeeDetailsToExcel(FetchEmployeeReq fetchEmployeeReq);
}
