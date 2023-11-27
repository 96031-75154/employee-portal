package com.emp.main.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.main.bean.DeleteEmployeeReq;
import com.emp.main.bean.FetchDepartmentRes;
import com.emp.main.bean.FetchEmpDeptRes;
import com.emp.main.bean.FetchEmployeeReq;
import com.emp.main.bean.FetchEmployeeRes;
import com.emp.main.bean.InsertEmployeeReq;
import com.emp.main.bean.InsertEmployeeReqs;
import com.emp.main.bean.UpdateEmployeeReq;
import com.emp.main.service.EmployeeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successful Response"),
		@ApiResponse(code = 201, message = "Resource created successfully"),
		@ApiResponse(code = 302, message = "You are being redirected to another resource"),
		@ApiResponse(code = 401, message = "You are not authenticated. Please provide valid details"),
		@ApiResponse(code = 403, message = "You are not authorized to access this resource"),
		@ApiResponse(code = 404, message = "Resource you are trying to reach is not found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
})
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	/* 
	 * Insert and update details
	 */
	
	@ApiOperation(value = "Insert new employee details", response = Boolean.class)
	@PostMapping("/insertEmployeeDetails")
	public ResponseEntity<Boolean> insertEmployeeDetails(@RequestBody InsertEmployeeReq employeeReq) {
		Boolean insert = employeeService.insertEmployeeDetails(employeeReq);
		return new ResponseEntity<Boolean>(insert, HttpStatus.CREATED);
	}

	@ApiOperation(value = "update existing employee details", response = Boolean.class)
	@PostMapping("/updateEmployeeDetails")
	public ResponseEntity<Boolean> updateEmployeeDetails(@RequestBody UpdateEmployeeReq updateEmployee) {
		Boolean update = employeeService.updateEmployeeDetails(updateEmployee);
		return new ResponseEntity<Boolean>(update, HttpStatus.OK);
	}

	@ApiOperation(value = "Soft delete employee details", response = Boolean.class)
	@PostMapping("/inactiveEmployee")
	public ResponseEntity<Boolean> inactiveEmployee(@RequestBody DeleteEmployeeReq deleteEmployeeReq) {
		Boolean deleteEmployee = employeeService.inactiveEmployee(deleteEmployeeReq);
		return new ResponseEntity<Boolean>(deleteEmployee, HttpStatus.GONE);
	}

	@ApiOperation(value = "Insert employee details", response = Boolean.class)
	@PostMapping("/insertEmployeeDetailsByMapSqlParameter")
	public ResponseEntity<Boolean> insertEmployeeDetailsByMapSqlParameter(@RequestBody InsertEmployeeReq employeeReq) {
		Boolean insert = employeeService.insertEmployeeDetailsByMapSqlParameter(employeeReq);
		return new ResponseEntity<Boolean>(insert, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update employee details", response = Boolean.class)
	@PostMapping("/updateEmployeeDetailsByMapSqlParameter")
	public ResponseEntity<Boolean> updateEmployeeDetailsByMapSqlParameter(@RequestBody UpdateEmployeeReq updateEmployee) {
		Boolean update = employeeService.updateEmployeeDetailsByMapSqlParameter(updateEmployee);
		return new ResponseEntity<Boolean>(update, HttpStatus.OK);
	}

	@ApiOperation(value = "Inactive employee details", response = Boolean.class)
	@PostMapping("/inactiveEmployeeByMapSqlParameter")
	public ResponseEntity<Boolean> inactiveEmployeeByMapSqlParameter(@RequestBody DeleteEmployeeReq deleteEmployeeReq) {
		Boolean deleteEmployee = employeeService.inactiveEmployeeByMapSqlParameter(deleteEmployeeReq);
		return new ResponseEntity<Boolean>(deleteEmployee, HttpStatus.GONE);
	}

	@ApiOperation(value = "Insert employee details", response = Boolean.class)
	@PostMapping("/insertEmployeeDetailsByBeanProperty")
	public ResponseEntity<Boolean> insertEmployeeDetailsByBeanProperty(@RequestBody InsertEmployeeReq employeeReq) {
		Boolean saveEmp = employeeService.insertEmployeeDetailsByBeanProperty(employeeReq);
		return new ResponseEntity<Boolean>(saveEmp, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update employee details", response = Boolean.class)
	@PostMapping("/updateEmployeeDetailsBeanProperty")
	public ResponseEntity<Boolean> updateEmployeeDetailsByBeanProperty(@RequestBody UpdateEmployeeReq employeeReq) {
		Boolean editEmp = employeeService.updateEmployeeDetailsByBeanProperty(employeeReq);
		return new ResponseEntity<Boolean>(editEmp, HttpStatus.OK);
	}

	@ApiOperation(value = "Batch insert employees details", response = Boolean.class)
	@PostMapping("/batchInsertBySqlParameter")
	public ResponseEntity<Boolean> batchInsertBySqlParameter(@RequestBody List<InsertEmployeeReq> insertEmployeeList) {
		Boolean batchInsert = employeeService.batchInsertBySqlParameter(insertEmployeeList);
		return new ResponseEntity<Boolean>(batchInsert, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Batch update employees details", response = Boolean.class)
	@PostMapping("/batchUpdateBySqlParameter")
	public ResponseEntity<Boolean> batchUpdateBySqlParameter(@RequestBody List<UpdateEmployeeReq> updateEmployeeList) {
		Boolean batchUpdate = employeeService.batchUpdateBySqlParameter(updateEmployeeList);
		return new ResponseEntity<Boolean>(batchUpdate, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Batch inactive seleted employees details", response = Boolean.class)
	@PostMapping("/batchInactiveBySqlParameter")
	public ResponseEntity<Boolean> batchInactiveBySqlParameter(@RequestBody DeleteEmployeeReq deleteEmployee) {
		Boolean batchIactive = employeeService.batchInactiveBySqlParameter(deleteEmployee);
		return new ResponseEntity<Boolean>(batchIactive, HttpStatus.GONE);
	}
	
	/* 
	 * Batch insert and update employee details 
	 */
	
	@ApiOperation(value = "Batch insert employees details", response = Boolean.class)
	@PostMapping("/batchInsertByBeanProperty")
	public ResponseEntity<Boolean> batchInsertByBeanProperty(@RequestBody List<InsertEmployeeReq> insertEmployeeList) {
		Boolean batchInsert = employeeService.batchInsertByBeanProperty(insertEmployeeList);
		return new ResponseEntity<Boolean>(batchInsert, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Batch update employees details", response = Boolean.class)
	@PostMapping("/batchUpdateByBeanProperty")
	public ResponseEntity<Boolean> batchUpdateByBeanProperty(@RequestBody List<UpdateEmployeeReq> updateEmployeeList) {
		Boolean batchUpdate = employeeService.batchUpdateByBeanProperty(updateEmployeeList);
		return new ResponseEntity<Boolean>(batchUpdate, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Batch insert employees details", response = Boolean.class)
	@PostMapping("/batchInsertByJdbcTemplate")
	public ResponseEntity<Boolean> batchInsertByJdbcTemplate(@RequestBody List<InsertEmployeeReq> insertEmployeeList) {
		Boolean batchInsert = employeeService.batchInsertByJdbcTemplate(insertEmployeeList);
		return new ResponseEntity<Boolean>(batchInsert, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Batch update employees details", response = Boolean.class)
	@PostMapping("/batchUpdateByJdbcTemplate")
	public ResponseEntity<Boolean> batchUpdateByJdbcTemplate(@RequestBody List<UpdateEmployeeReq> updateEmployeeList) {
		Boolean batchUpdate = employeeService.batchUpdateByJdbcTemplate(updateEmployeeList);
		return new ResponseEntity<Boolean>(batchUpdate, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Batch inactive selected employees details", response = Boolean.class)
	@PostMapping("/batchInactiveByJdbcTemplate")
	public ResponseEntity<Boolean> batchInactiveByJdbcTemplate(@RequestBody List<Integer> idList) {
		Boolean idsBatch = employeeService.batchInactiveByJdbcTemplate(idList);
		return new ResponseEntity<Boolean>(idsBatch, HttpStatus.GONE);
	}
	
	@ApiOperation(value = "Insert and update employee details", response = Boolean.class)
	@PostMapping("/insertAndUpdateEmployee")
	public ResponseEntity<Boolean> insertAndUpdateEmployee(@RequestBody InsertEmployeeReqs insertEmployee) {
		Boolean insertEmp = employeeService.insertAndUpdateEmployee(insertEmployee);
		return new ResponseEntity<Boolean>(insertEmp, HttpStatus.OK);
	}
	
	/* 
	 * Insert and get keyholder generator
	 */
	
	@ApiOperation(value = "Insert employee and get empId", response = Boolean.class)
	@PostMapping("/insertEmployeeAndGetKeyHolder")
	public ResponseEntity<Boolean> insertEmployeeAndGetKeyHolder(@RequestBody InsertEmployeeReqs insertEmployeeReq) {
		Boolean insertEmp = employeeService.insertEmployeeAndGetKeyHolder(insertEmployeeReq);
		return new ResponseEntity<Boolean>(insertEmp, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Fetch employee details", response = FetchEmployeeReq.class)
	@PostMapping("/fetchEmployeeDetails")
	public ResponseEntity<FetchEmployeeReq> fetchEmployeeDetails(@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return new ResponseEntity<FetchEmployeeReq>(employeeService.fetchEmployeeDetails(fetchEmployeeReq), HttpStatus.FOUND);
	}
	
	@ApiOperation(value = "Fetch employee details using map", response = Map.class, responseContainer = "Map")
	@PostMapping("/fetchEmployeeByJdbcQueryForMap")
	public ResponseEntity<Map<String, Object>> fetchEmployeeByJdbcQueryForMap(@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return new ResponseEntity<Map<String, Object>>(employeeService.fetchEmployeeByJdbcQueryForMap(fetchEmployeeReq), HttpStatus.FOUND);
	}
	
	@ApiOperation(value = "Fetch all employee details", response = List.class, responseContainer = "List")
	@PostMapping("/fetchEmployeeByJdbcQueryForList")
	public ResponseEntity<List<Map<String, Object>>> fetchEmployeeByJdbcQueryForList(@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return new ResponseEntity<List<Map<String,Object>>>(employeeService.fetchEmployeeByJdbcQueryForList(fetchEmployeeReq), HttpStatus.FOUND);
	}
	
	/* 
	 * Insert and update employee along with department details
	 */
	
	@ApiOperation(value = "Insert and update employee along with department details", response = Boolean.class)
	@PostMapping("/insertOrUpdateEmployee")
	public ResponseEntity<Boolean> insertOrUpdateEmployee(@RequestBody InsertEmployeeReqs insertEmployeeReq) {
		return new ResponseEntity<Boolean>(employeeService.insertOrUpdateEmployee(insertEmployeeReq), HttpStatus.OK);
	}
	
	/* 
	 * Fetch employee details
	 */
	
	@ApiOperation(value = "Fetch employee details using queryForObject", response = FetchEmployeeRes.class)
	@PostMapping("/fetchEmployeeByQueryForObject")
	public ResponseEntity<FetchEmployeeRes> fetchEmployeeByQueryForObject(@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return new ResponseEntity<FetchEmployeeRes>(employeeService.fetchEmployeeByQueryForObject(fetchEmployeeReq), HttpStatus.FOUND);
	}
	
	@ApiOperation(value = "Fetch employee details using queryForMap", response = FetchEmployeeRes.class, responseContainer = "Map")
	@PostMapping("/fetchEmployeeByQueryForMap")
	public ResponseEntity<FetchEmployeeRes> fetchEmployeeByQueryForMap(@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return new ResponseEntity<FetchEmployeeRes>(employeeService.fetchEmployeeByQueryForMap(fetchEmployeeReq), HttpStatus.FOUND);
	}
	
	@ApiOperation(value = "Fetch employee details using queryForList", response = FetchEmployeeRes.class, responseContainer = "List")
	@PostMapping("/fetchEmployeeByQueryForList")
	public ResponseEntity<List<FetchEmployeeRes>> fetchEmployeeByQueryForList(@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return new ResponseEntity<List<FetchEmployeeRes>>(employeeService.fetchEmployeeByQueryForList(fetchEmployeeReq), HttpStatus.FOUND);
	}
	
	@ApiOperation(value = "Fetch employee details using query", response = FetchEmployeeRes.class, responseContainer = "List")
	@PostMapping("/fetchEmployeeByQuery")
	public ResponseEntity<List<FetchEmployeeRes>> fetchEmployeeByQuery(@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return new ResponseEntity<List<FetchEmployeeRes>>(employeeService.fetchEmployeeByQuery(fetchEmployeeReq), HttpStatus.OK);
	}
	
	/* 
	 *	Fetch employee and department details
	 */
	@ApiOperation(value = "Fetch employee along with department details", response = FetchEmpDeptRes.class, responseContainer = "List")
	@PostMapping("/fetchEmployeeWithDepartmentDetails")
	public ResponseEntity<List<FetchEmpDeptRes>> fetchEmployeeWithDepartmentDetails(@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return new ResponseEntity<List<FetchEmpDeptRes>>(employeeService.fetchEmployeeWithDepartmentDetails(fetchEmployeeReq), HttpStatus.OK);
	}
	
	/*
	 *	List number of employees with department details based on the gender
	 */
	@ApiOperation(value = "Fetch department details by gender", response = FetchDepartmentRes.class, responseContainer = "List")
	@PostMapping("/fetchDepartmentByGender")
	public ResponseEntity<List<FetchDepartmentRes>> fetchDepartmentByGender(@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return new ResponseEntity<List<FetchDepartmentRes>>(employeeService.fetchDepartmentByGender(fetchEmployeeReq), HttpStatus.OK);
	}
}
