package com.emp.main.dao;

import java.util.List;
import java.util.Map;

import com.emp.main.bean.FetchEmployeeReq;
import com.emp.main.bean.InsertEmployeeReq;

public interface ExcelDao {
	public Boolean uploadEmployeeDetailsToDatabase(List<InsertEmployeeReq> employeeList);
	
	public List<Map<String, Object>> downloadEmployeeDetailsToExcel(FetchEmployeeReq fetchEmployeeReq);
}
