package com.emp.main.service;

import java.util.List;
import java.util.Map;

import com.emp.main.bean.DeleteEmployeeReq;
import com.emp.main.bean.FetchDepartmentRes;
import com.emp.main.bean.FetchEmpDeptRes;
import com.emp.main.bean.FetchEmployeeReq;
import com.emp.main.bean.FetchEmployeeRes;
import com.emp.main.bean.InsertEmployeeReq;
import com.emp.main.bean.InsertEmployeeReqs;
import com.emp.main.bean.UpdateEmployeeReq;

public interface EmployeeService {

	public Boolean insertEmployeeDetails(InsertEmployeeReq employeeReq);
	
	public Boolean insertEmployeeDetailsByMapSqlParameter(InsertEmployeeReq employeeReq);
	
	public Boolean insertEmployeeDetailsByBeanProperty(InsertEmployeeReq employeeReq);

	public Boolean updateEmployeeDetails(UpdateEmployeeReq updateEmployee);
	
	public Boolean updateEmployeeDetailsByMapSqlParameter(UpdateEmployeeReq updateEmployee);
	
	public Boolean updateEmployeeDetailsByBeanProperty(UpdateEmployeeReq employeeReq);

	public Boolean inactiveEmployee(DeleteEmployeeReq deleteEmployeeReq);

	public Boolean inactiveEmployeeByMapSqlParameter(DeleteEmployeeReq deleteEmployeeReq);

	public Boolean batchInsertByBeanProperty(List<InsertEmployeeReq> insertEmployeeList);
	
	public Boolean batchUpdateByBeanProperty(List<UpdateEmployeeReq> updateEmployeeList);

	public Boolean batchInsertBySqlParameter(List<InsertEmployeeReq> insertEmployeeList);

	public Boolean batchUpdateBySqlParameter(List<UpdateEmployeeReq> updateEmployeeList);

	public Boolean batchInactiveBySqlParameter(DeleteEmployeeReq deleteEmployee);

	public Boolean batchInsertByJdbcTemplate(List<InsertEmployeeReq> insertEmployeeList);

	public Boolean batchUpdateByJdbcTemplate(List<UpdateEmployeeReq> updateEmployeeList);

	public Boolean batchInactiveByJdbcTemplate(List<Integer> idList);

	public Boolean insertAndUpdateEmployee(InsertEmployeeReqs insertEmployee);

	public Boolean insertEmployeeAndGetKeyHolder(InsertEmployeeReqs insertEmployeeReq);
	
	public FetchEmployeeReq fetchEmployeeDetails(FetchEmployeeReq fetchEmployeeReq);

	public Map<String, Object> fetchEmployeeByJdbcQueryForMap(FetchEmployeeReq fetchEmployeeReq);

	public List<Map<String, Object>> fetchEmployeeByJdbcQueryForList(FetchEmployeeReq fetchEmployeeReq);

	public Boolean insertOrUpdateEmployee(InsertEmployeeReqs insertEmployeeReq);
	
	public FetchEmployeeRes fetchEmployeeByQueryForObject(FetchEmployeeReq fetchEmployeeReq);
	
	public FetchEmployeeRes fetchEmployeeByQueryForMap(FetchEmployeeReq fetchEmployeeReq);
	
	public List<FetchEmployeeRes> fetchEmployeeByQueryForList(FetchEmployeeReq fetchEmployeeReq);

	public List<FetchEmployeeRes> fetchEmployeeByQuery(FetchEmployeeReq fetchEmployeeReq);

	public List<FetchEmpDeptRes> fetchEmployeeWithDepartmentDetails(FetchEmployeeReq fetchEmployeeReq);

	public List<FetchDepartmentRes> fetchDepartmentByGender(FetchEmployeeReq fetchEmployeeReq);
}
