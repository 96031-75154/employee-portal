package com.emp.main.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.main.bean.DeleteEmployeeReq;
import com.emp.main.bean.EmployeeDeptBean;
import com.emp.main.bean.FetchDepartmentRes;
import com.emp.main.bean.FetchEmpDeptBean;
import com.emp.main.bean.FetchEmpDeptRes;
import com.emp.main.bean.FetchEmployeeReq;
import com.emp.main.bean.FetchEmployeeRes;
import com.emp.main.bean.InsertEmployeeBean;
import com.emp.main.bean.InsertEmployeeReq;
import com.emp.main.bean.InsertEmployeeReqs;
import com.emp.main.bean.UpdateEmployeeReq;
import com.emp.main.dao.EmployeeDao;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Boolean insertEmployeeDetails(InsertEmployeeReq employeeReq) {
		return employeeDao.insertEmployeeDetails(employeeReq);
	}

	@Override
	public Boolean updateEmployeeDetails(UpdateEmployeeReq updateEmployee) {
		return employeeDao.updateEmployeeDetails(updateEmployee);
	}

	@Override
	public Boolean inactiveEmployee(DeleteEmployeeReq deleteEmployeeReq) {
		return employeeDao.inactiveEmployee(deleteEmployeeReq);
	}

	@Override
	public Boolean insertEmployeeDetailsByMapSqlParameter(InsertEmployeeReq employeeReq) {
		return employeeDao.insertEmployeeDetailsByMapSqlParameter(employeeReq);
	}

	@Override
	public Boolean updateEmployeeDetailsByMapSqlParameter(UpdateEmployeeReq updateEmployee) {
		return employeeDao.updateEmployeeDetailsByMapSqlParameter(updateEmployee);
	}

	@Override
	public Boolean inactiveEmployeeByMapSqlParameter(DeleteEmployeeReq deleteEmployeeReq) {
		return employeeDao.inactiveEmployeeByMapSqlParameter(deleteEmployeeReq);
	}

	@Override
	public Boolean insertEmployeeDetailsByBeanProperty(InsertEmployeeReq employeeReq) {
		return employeeDao.insertEmployeeDetailsByBeanProperty(employeeReq);
	}

	@Override
	public Boolean updateEmployeeDetailsByBeanProperty(UpdateEmployeeReq employeeReq) {
		return employeeDao.updateEmployeeDetailsByBeanProperty(employeeReq);
	}

	@Override
	public Boolean batchInsertByBeanProperty(List<InsertEmployeeReq> insertEmployeeList) {
		return employeeDao.batchInsertByBeanProperty(insertEmployeeList);
	}

	@Override
	public Boolean batchUpdateByBeanProperty(List<UpdateEmployeeReq> updateEmployeeList) {
		return employeeDao.batchUpdateByBeanProperty(updateEmployeeList);
	}

	@Override
	public Boolean batchInsertBySqlParameter(List<InsertEmployeeReq> insertEmployeeList) {
		return employeeDao.batchInsertBySqlParameter(insertEmployeeList);
	}

	@Override
	public Boolean batchUpdateBySqlParameter(List<UpdateEmployeeReq> updateEmployeeList) {
		return employeeDao.batchUpdateBySqlParameter(updateEmployeeList);
	}

	@Override
	public Boolean batchInactiveBySqlParameter(DeleteEmployeeReq deleteEmployee) {
		return employeeDao.batchInactiveBySqlParameter(deleteEmployee);
	}

	@Override
	public Boolean batchInsertByJdbcTemplate(List<InsertEmployeeReq> insertEmployeeList) {
		return employeeDao.batchInsertByJdbcTemplate(insertEmployeeList);
	}

	@Override
	public Boolean batchUpdateByJdbcTemplate(List<UpdateEmployeeReq> updateEmployeeList) {
		return employeeDao.batchUpdateByJdbcTemplate(updateEmployeeList);
	}

	@Override
	public Boolean batchInactiveByJdbcTemplate(List<Integer> idList) {
		return employeeDao.batchInactiveByJdbcTemplate(idList);
	}

	@Override
	public Boolean insertAndUpdateEmployee(InsertEmployeeReqs insertEmployeeRequest) {

		List<InsertEmployeeBean> saveEmployeeList = new ArrayList<>();

		List<InsertEmployeeBean> updateEmployeeList = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(insertEmployeeRequest.getEmployeeList())) {
			for (InsertEmployeeBean insertEmployeeObj : insertEmployeeRequest.getEmployeeList()) {
				insertEmployeeObj.setOrgId(insertEmployeeRequest.getOrgId());

				if (insertEmployeeObj.getEmpId() == null) {
					saveEmployeeList.add(insertEmployeeObj);
				} else {
					updateEmployeeList.add(insertEmployeeObj);
				}
			}
		}
		Boolean insertFlag = false;
		Boolean updateFlag = false;
		if (CollectionUtils.isNotEmpty(saveEmployeeList)) {
			insertFlag = employeeDao.insertEmployee(saveEmployeeList);
		}
		if (CollectionUtils.isNotEmpty(updateEmployeeList)) {
			updateFlag = employeeDao.updateEmployee(updateEmployeeList);
		}
		return insertFlag || updateFlag;
	}

	@Override
	public Boolean insertEmployeeAndGetKeyHolder(InsertEmployeeReqs insertEmployeeReq) {
		return employeeDao.insertEmployeeAndGetKeyHolder(insertEmployeeReq);
	}

	@Override
	public FetchEmployeeReq fetchEmployeeDetails(FetchEmployeeReq fetchEmployeeReq) {
		return employeeDao.fetchEmployeeDetails(fetchEmployeeReq);
	}

	@Override
	public Map<String, Object> fetchEmployeeByJdbcQueryForMap(FetchEmployeeReq fetchEmployeeReq) {
		return employeeDao.fetchEmployeeByJdbcQueryForMap(fetchEmployeeReq);
	}

	@Override
	public List<Map<String, Object>> fetchEmployeeByJdbcQueryForList(FetchEmployeeReq fetchEmployeeReq) {
		return employeeDao.fetchEmployeeByJdbcQueryForList(fetchEmployeeReq);
	}

	@Override
	public Boolean insertOrUpdateEmployee(InsertEmployeeReqs insertEmployeeReq) {

		Long empId = null;
		Boolean insertFlag = false;
		Boolean updateFlag = false;
		if (CollectionUtils.isNotEmpty(insertEmployeeReq.getEmployeeList())) {
			for (InsertEmployeeBean insertEmployeeObj : insertEmployeeReq.getEmployeeList()) {

				List<EmployeeDeptBean> saveEmpDeptList = new ArrayList<>();

				List<EmployeeDeptBean> updateEmpDeptList = new ArrayList<>();

				insertEmployeeObj.setOrgId(insertEmployeeReq.getOrgId());
				
				if(insertEmployeeObj.getEmpId() == null) {
					empId = employeeDao.insertEmployee(insertEmployeeObj);
					if(empId != null) {
						insertFlag = true;
					}
				} else {
					updateFlag = employeeDao.updateEmployee(insertEmployeeObj);
				}
				if(CollectionUtils.isNotEmpty(insertEmployeeObj.getEmpDeptList())) {
					for(EmployeeDeptBean employeeDeptObj : insertEmployeeObj.getEmpDeptList()) {
						employeeDeptObj.setOrgId(insertEmployeeObj.getOrgId());
						if(employeeDeptObj.getEmpDeptId() == null) {
							saveEmpDeptList.add(employeeDeptObj);
						} else {
							updateEmpDeptList.add(employeeDeptObj);
						}
					}
				}
				if(CollectionUtils.isNotEmpty(saveEmpDeptList)) {
					insertFlag = employeeDao.insertEmpDept(empId, saveEmpDeptList);
				} else {
					updateFlag = employeeDao.updateEmpDept(updateEmpDeptList);
				}
			}
		}
		return insertFlag || updateFlag;
	}

	@Override
	public FetchEmployeeRes fetchEmployeeByQueryForObject(FetchEmployeeReq fetchEmployeeReq) {
		return employeeDao.fetchEmployeeByQueryForObject(fetchEmployeeReq);
	}

	public FetchEmployeeRes fetchEmployeeByQueryForMap(FetchEmployeeReq fetchEmployeeReq) {
		
		FetchEmployeeRes employeeRes = new FetchEmployeeRes();
		
		Map<String, Object> empMap = employeeDao.fetchEmployeeByQueryForMap(fetchEmployeeReq);
		
		if(!empMap.isEmpty()) {
			employeeRes.setFirstNm((String) empMap.get("first_nm"));
			employeeRes.setMiddleNm((String) empMap.get("middle_nm"));
			employeeRes.setLastNm((String) empMap.get("last_nm"));
			employeeRes.setGender((String) empMap.get("gender"));
			employeeRes.setBirthDt((java.sql.Date) empMap.get("birth_dt"));
			employeeRes.setAge((Integer) empMap.get("age"));
			employeeRes.setBloodGp((String) empMap.get("blood_gp"));
			employeeRes.setReligion((String) empMap.get("religion"));
			employeeRes.setSalary((BigDecimal) empMap.get("salary"));
			employeeRes.setMobileNo((String) empMap.get("mobile_no"));
			employeeRes.setEmail((String) empMap.get("email"));
			employeeRes.setPincode((String) empMap.get("pincode"));
			employeeRes.setStreetNm((String) empMap.get("street_nm"));
			employeeRes.setCityNm((String) empMap.get("city_nm"));
			employeeRes.setCountryNm((String) empMap.get("country_nm"));
		}
		return employeeRes;
	}

	@Override
	public List<FetchEmployeeRes> fetchEmployeeByQueryForList(FetchEmployeeReq fetchEmployeeReq) {

		List<FetchEmployeeRes> employeeResList = null;
		
		List<Map<String, Object>> employeeList = employeeDao.fetchEmployeeByQueryForList(fetchEmployeeReq);

		if (CollectionUtils.isNotEmpty(employeeList)) {
			employeeResList = employeeList.stream().map(obj -> {
				FetchEmployeeRes employeeRes = new FetchEmployeeRes();

				employeeRes.setFirstNm((String) obj.get("first_nm"));
				employeeRes.setMiddleNm((String) obj.get("middle_nm"));
				employeeRes.setLastNm((String) obj.get("last_nm"));
				employeeRes.setGender((String) obj.get("gender"));
				employeeRes.setBirthDt((java.sql.Date) obj.get("birth_dt"));
				employeeRes.setAge((Integer) obj.get("age"));
				employeeRes.setBloodGp((String) obj.get("blood_gp"));
				employeeRes.setReligion((String) obj.get("religion"));
				employeeRes.setSalary((BigDecimal) obj.get("salary"));
				employeeRes.setMobileNo((String) obj.get("mobile_no"));
				employeeRes.setEmail((String) obj.get("email"));
				employeeRes.setPincode((String) obj.get("pincode"));
				employeeRes.setStreetNm((String) obj.get("street_nm"));
				employeeRes.setCityNm((String) obj.get("city_nm"));
				employeeRes.setCountryNm((String) obj.get("country_nm"));

				return employeeRes;
			}).toList();
		}
		return employeeResList;
	}

	@Override
	public List<FetchEmployeeRes> fetchEmployeeByQuery(FetchEmployeeReq fetchEmployeeReq) {
		return employeeDao.fetchEmployeeByQuery(fetchEmployeeReq);
	}

	@Override
	public List<FetchEmpDeptRes> fetchEmployeeWithDepartmentDetails(FetchEmployeeReq fetchEmployeeReq) {
		
		List<Map<String, Object>> employeeRowList = employeeDao.fetchEmployeeWithDepartmentDetails(fetchEmployeeReq);
		List<FetchEmpDeptRes> empResList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(employeeRowList)) {
			Integer count = 0;
			FetchEmpDeptRes employeeRes = null;
			List<FetchEmpDeptBean> empDeptList = null;
			Set<Long> uniqueSet = new HashSet<>();
			for(Map<String, Object> row : employeeRowList) {
				count++;
				if(uniqueSet.add((Long)row.get("emp_id"))) {
					if(employeeRes != null) {
						if(!CollectionUtils.isEmpty(empDeptList)) {
							employeeRes.setDeptList(empDeptList);
						}
						empResList.add(employeeRes);
					}
					employeeRes = new FetchEmpDeptRes();
					employeeRes.setEmpId((Long)row.get("emp_id"));
					employeeRes.setEmpNm((String)row.get("emp_nm"));
					employeeRes.setEmail((String)row.get("email"));
					employeeRes.setMobileNo((String)row.get("mobile_no"));
					employeeRes.setSalary((BigDecimal)row.get("salary"));
					employeeRes.setGender((String)row.get("gender"));
					employeeRes.setBirthDt((java.sql.Date)row.get("birth_dt"));
					employeeRes.setAge((Integer)row.get("age"));
					employeeRes.setBloodGp((String)row.get("blood_gp"));
					employeeRes.setReligion((String)row.get("religion"));
					employeeRes.setPincode((String)row.get("pincode"));
					employeeRes.setStreetNm((String)row.get("street_nm"));
					employeeRes.setCityNm((String)row.get("city_nm"));
					employeeRes.setCountryNm((String)row.get("country_nm"));
					empDeptList = new ArrayList<>();
				}
				FetchEmpDeptBean empDeptBean = new FetchEmpDeptBean();
				empDeptBean.setDeptId((Long)row.get("dept_id"));
				empDeptBean.setDeptNm((String)row.get("dept_nm"));
				empDeptBean.setEmpDeptId((Long)row.get("emp_dept_id"));
				empDeptList.add(empDeptBean);
			}
			if(count == employeeRowList.size() && employeeRes != null) {
				if(!CollectionUtils.isEmpty(empDeptList)) {
					employeeRes.setDeptList(empDeptList);
				}
				empResList.add(employeeRes);
			}
		}
		return empResList;
	}

	@Override
	public List<FetchDepartmentRes> fetchDepartmentByGender(FetchEmployeeReq fetchEmployeeReq) {
		
		List<Map<String, Object>> deptList = employeeDao.fetchDepartmentByGender(fetchEmployeeReq);
		List<FetchDepartmentRes> deptResList = null;
		if(CollectionUtils.isNotEmpty(deptList)) {
			deptResList = deptList.stream().map(obj -> {
				FetchDepartmentRes deptRes = new FetchDepartmentRes();
				deptRes.setDeptNm((String) obj.get("dept_nm"));
				deptRes.setGender((String) obj.get("gender"));
				deptRes.setEmpCount((Long) obj.get("emp_count"));
				return deptRes;
			}).toList();
		}
		return deptResList;
	}
}
