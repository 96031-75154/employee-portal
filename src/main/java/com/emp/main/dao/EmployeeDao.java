package com.emp.main.dao;

import java.util.List;
import java.util.Map;

import com.emp.main.bean.DeleteEmployeeReq;
import com.emp.main.bean.EmployeeDeptBean;
import com.emp.main.bean.FetchEmployeeReq;
import com.emp.main.bean.FetchEmployeeRes;
import com.emp.main.bean.InsertEmployeeBean;
import com.emp.main.bean.InsertEmployeeReq;
import com.emp.main.bean.InsertEmployeeReqs;
import com.emp.main.bean.UpdateEmployeeReq;

public interface EmployeeDao {

	//======================== Insert Employee Details ==================
	public Boolean insertEmployeeDetails(InsertEmployeeReq employeeReq);
	
	public Boolean insertEmployeeDetailsByMapSqlParameter(InsertEmployeeReq employeeReq);
	
	public Boolean insertEmployeeDetailsByBeanProperty(InsertEmployeeReq employeeReq);
	
	//======================== Update Employee Details ==================
	public Boolean updateEmployeeDetails(UpdateEmployeeReq updateEmployee);
	
	public Boolean updateEmployeeDetailsByMapSqlParameter(UpdateEmployeeReq updateEmployee);
	
	public Boolean updateEmployeeDetailsByBeanProperty(UpdateEmployeeReq employeeReq);

	//======================== Soft delete employee details ==================
	public Boolean inactiveEmployee(DeleteEmployeeReq deleteEmployeeReq);

	public Boolean inactiveEmployeeByMapSqlParameter(DeleteEmployeeReq deleteEmployeeReq);
	
	//======================== Batch insert for employee details ================
	public Boolean batchInsertByBeanProperty(List<InsertEmployeeReq> insertEmployeeList);
	
	public Boolean batchInsertBySqlParameter(List<InsertEmployeeReq> insertEmployeeList);

	//======================== Batch update for employee details =================
	public Boolean batchUpdateByBeanProperty(List<UpdateEmployeeReq> updateEmployeeList);

	public Boolean batchUpdateBySqlParameter(List<UpdateEmployeeReq> updateEmployeeList);

	public Boolean batchInactiveBySqlParameter(DeleteEmployeeReq deleteEmployee);

	//======================== Batch insert by JdbcTemplate =================
	public Boolean batchInsertByJdbcTemplate(List<InsertEmployeeReq> insertEmployeeList);

	//======================== Batch update by JdbcTemplate =================
	public Boolean batchUpdateByJdbcTemplate(List<UpdateEmployeeReq> updateEmployeeList);

	public Boolean batchInactiveByJdbcTemplate(List<Integer> idList);
	
	//======================== Batch insert and update for single API ==========
	public Boolean insertEmployee(List<InsertEmployeeBean> saveEmpList);
	
	public Boolean updateEmployee(List<InsertEmployeeBean> updateEmpList);
	
	//======================== Insert Employee and get Key Holder ==============
	public Boolean insertEmployeeAndGetKeyHolder(InsertEmployeeReqs insertEmployee);
	
	public FetchEmployeeReq fetchEmployeeDetails(FetchEmployeeReq fetchEmployeeReq);

	public Map<String, Object> fetchEmployeeByJdbcQueryForMap(FetchEmployeeReq fetchEmployeeReq);

	public List<Map<String, Object>> fetchEmployeeByJdbcQueryForList(FetchEmployeeReq fetchEmployeeReq);
	
	public Long insertEmployee(InsertEmployeeBean insertEmployeeObj);
	
	public Boolean updateEmployee(InsertEmployeeBean insertEmployeeObj);

	public Boolean insertEmpDept(Long empId, List<EmployeeDeptBean> saveEmpDeptList);

	public Boolean updateEmpDept(List<EmployeeDeptBean> updateEmpDeptList);
	
	//========================= Fetch Employee Details =========================
	public FetchEmployeeRes fetchEmployeeByQueryForObject(FetchEmployeeReq fetchEmployeeReq);
	
	public Map<String, Object> fetchEmployeeByQueryForMap(FetchEmployeeReq fetchEmployeeReq);
	
	public List<Map<String, Object>> fetchEmployeeByQueryForList(FetchEmployeeReq fetchEmployeeReq);

	public List<FetchEmployeeRes> fetchEmployeeByQuery(FetchEmployeeReq fetchEmployeeReq);
	
	public List<Map<String, Object>> fetchEmployeeWithDepartmentDetails(FetchEmployeeReq fetchEmployeeReq);
	
	public List<Map<String, Object>> fetchDepartmentByGender(FetchEmployeeReq fetchEmployeeReq);
}
