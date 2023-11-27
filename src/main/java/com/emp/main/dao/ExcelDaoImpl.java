package com.emp.main.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.emp.main.bean.FetchEmployeeReq;
import com.emp.main.bean.InsertEmployeeReq;
import com.emp.main.exception.ListNotFoundException;

@Repository
public class ExcelDaoImpl implements ExcelDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Value("${queries.queryForInsertEmployee}")
	private String queryForInsertEmployee;
	
	@Value("${queries.queryForDownloadEmployeeExcelSheet}")
	private String queryForDownloadEmployeeExcelSheet;
	
	/*
	 * Upload employee details to repository
	 */
	@Override
	public Boolean uploadEmployeeDetailsToDatabase(List<InsertEmployeeReq> employeeList) {
		int count[] = null;
		try {
			List<BeanPropertySqlParameterSource> batchArgs = new ArrayList<>();
			employeeList.forEach(employee -> {
				batchArgs.add(new BeanPropertySqlParameterSource(employee));
			});
			count = namedParameterJdbcTemplate.batchUpdate(queryForInsertEmployee,
					batchArgs.toArray(new BeanPropertySqlParameterSource[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count.length == employeeList.size();
	}
	
	/*
	 * Download employee details to excel sheet
	 */
	public List<Map<String, Object>> downloadEmployeeDetailsToExcel(FetchEmployeeReq fetchEmployeeReq) {
		
		List<Map<String, Object>> respList = null;
		try {
			respList = namedParameterJdbcTemplate.queryForList(queryForDownloadEmployeeExcelSheet, new BeanPropertySqlParameterSource(fetchEmployeeReq));
			if(respList == null || respList.isEmpty()) {
				throw new ListNotFoundException("List not found");
			}
		} catch (Exception e) {
			throw new ListNotFoundException("List not found");
		}
		return respList;
	}
}
