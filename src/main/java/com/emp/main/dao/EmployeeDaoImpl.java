package com.emp.main.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.emp.main.bean.DeleteEmployeeBean;
import com.emp.main.bean.DeleteEmployeeReq;
import com.emp.main.bean.EmployeeDeptBean;
import com.emp.main.bean.FetchEmployeeReq;
import com.emp.main.bean.FetchEmployeeRes;
import com.emp.main.bean.InsertEmployeeBean;
import com.emp.main.bean.InsertEmployeeReq;
import com.emp.main.bean.InsertEmployeeReqs;
import com.emp.main.bean.UpdateEmployeeReq;
import com.emp.main.exception.ListNotFoundException;

import io.micrometer.common.util.StringUtils;
//import org.apache.commons.lang.StringUtils;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${queries.queryForInsertEmployee}")
	private String queryForInsertEmployee;

	@Value("${queries.queryForUpdateEmployee}")
	private String queryForUpdateEmployee;

	@Value("${queries.queryForPartialDelete}")
	private String queryForPartialDelete;

	@Value("${queries.queryForInsert}")
	private String queryForInsert;

	@Value("${queries.queryForUpdate}")
	private String queryForUpdate;

	@Value("${queries.queryForSoftDelete}")
	private String queryForSoftDelete;

	@Value("${queries.queryForObjectByJdbcTemplate}")
	private String queryForObjectByJdbcTemplate;

	@Value("${queries.queryForMapByJdbcTemplate}")
	private String queryForMapByJdbcTemplate;

	@Value("${queries.queryForListByJdbcTemplate}")
	private String queryForListByJdbcTemplate;

	@Value("${queries.queryForInsertEmpDept}")
	private String queryForInsertEmpDept;

	@Value("${queries.queryForUpdateEmpDept}")
	private String queryForUpdateEmpDept;

	@Value("${queries.queryForFetchEmpQuery}")
	private String queryForFetchEmpQuery;

	@Value("${queries.queryForFetchEmpObj}")
	private String queryForFetchEmpObj;

	@Value("${queries.queryForFetchEmpList}")
	private String queryForFetchEmpList;

	@Value("${queries.queryForFetchEmpMap}")
	private String queryForFetchEmpMap;

	@Value("${queries.queryForFetchEmpDeptDetails}")
	private String queryForFetchEmpDeptDetails;
	
	@Value("${queries.queryForDepartmentByGender}")
	private String queryForDepartmentByGender;

	@Override
	public Boolean insertEmployeeDetails(InsertEmployeeReq employeeReq) {
		Integer count = 0;

		try {
			Map<String, Object> params = new HashMap<>();

			params.put("orgId", employeeReq.getOrgId());
			params.put("firstNm", employeeReq.getFirstNm());
			params.put("middleNm", employeeReq.getMiddleNm());
			params.put("lastNm", employeeReq.getLastNm());
			params.put("gender", employeeReq.getGender());
			params.put("birthDt", employeeReq.getBirthDt());
			params.put("age", employeeReq.getAge());
			params.put("bloodGp", employeeReq.getBloodGp());
			params.put("religion", employeeReq.getReligion());
			params.put("salary", employeeReq.getSalary());
			params.put("mobileNo", employeeReq.getMobileNo());
			params.put("email", employeeReq.getEmail());
			params.put("pincode", employeeReq.getPincode());
			params.put("streetNm", employeeReq.getStreetNm());
			params.put("cityNm", employeeReq.getCityNm());
			params.put("countryNm", employeeReq.getCountryNm());

			count = namedParameterJdbcTemplate.update(queryForInsertEmployee, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count == 1;
	}

	@Override
	public Boolean updateEmployeeDetails(UpdateEmployeeReq updateEmployee) {
		Boolean status = false;
		try {
			Map<String, Object> params = new HashMap<>();

			params.put("orgId", updateEmployee.getOrgId());
			params.put("empId", updateEmployee.getEmpId());
			params.put("firstNm", updateEmployee.getFirstNm());
			params.put("middleNm", updateEmployee.getMiddleNm());
			params.put("lastNm", updateEmployee.getLastNm());
			params.put("gender", updateEmployee.getGender());
			params.put("birthDt", updateEmployee.getBirthDt());
			params.put("age", updateEmployee.getAge());
			params.put("bloodGp", updateEmployee.getBloodGp());
			params.put("religion", updateEmployee.getReligion());
			params.put("salary", updateEmployee.getSalary());
			params.put("mobileNo", updateEmployee.getMobileNo());
			params.put("email", updateEmployee.getEmail());
			params.put("pincode", updateEmployee.getPincode());
			params.put("streetNm", updateEmployee.getStreetNm());
			params.put("cityNm", updateEmployee.getCityNm());
			params.put("countryNm", updateEmployee.getCountryNm());

			status = namedParameterJdbcTemplate.update(queryForUpdateEmployee, params) == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Boolean inactiveEmployee(DeleteEmployeeReq deleteEmployeeReq) {
		Integer count = 0;
		try {
			for (DeleteEmployeeBean deleteEmployee : deleteEmployeeReq.getEmployeeList()) {
				deleteEmployee.setOrgId(deleteEmployeeReq.getOrgId());
				Map<String, Object> params = new HashMap<>();
				params.put("orgId", deleteEmployee.getOrgId());
				params.put("empId", deleteEmployee.getEmpId());
				Integer rowAffected = namedParameterJdbcTemplate.update(queryForPartialDelete, params);
				count += rowAffected;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count == deleteEmployeeReq.getEmployeeList().size();
	}

	@Override
	public Boolean insertEmployeeDetailsByMapSqlParameter(InsertEmployeeReq employeeReq) {

		Integer count = 0;
		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("orgId", employeeReq.getOrgId());
			parameter.addValue("firstNm", employeeReq.getFirstNm());
			parameter.addValue("middleNm", employeeReq.getMiddleNm());
			parameter.addValue("lastNm", employeeReq.getLastNm());
			parameter.addValue("gender", employeeReq.getGender());
			parameter.addValue("birthDt", employeeReq.getBirthDt());
			parameter.addValue("age", employeeReq.getAge());
			parameter.addValue("bloodGp", employeeReq.getBloodGp());
			parameter.addValue("religion", employeeReq.getReligion());
			parameter.addValue("salary", employeeReq.getSalary());
			parameter.addValue("mobileNo", employeeReq.getMobileNo());
			parameter.addValue("email", employeeReq.getEmail());
			parameter.addValue("pincode", employeeReq.getPincode());
			parameter.addValue("streetNm", employeeReq.getStreetNm());
			parameter.addValue("cityNm", employeeReq.getCityNm());
			parameter.addValue("countryNm", employeeReq.getCountryNm());

			count = namedParameterJdbcTemplate.update(queryForInsertEmployee, parameter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count == 1;
	}

	@Override
	public Boolean updateEmployeeDetailsByMapSqlParameter(UpdateEmployeeReq updateEmployee) {

		Boolean status = false;

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("orgId", updateEmployee.getOrgId());
			parameter.addValue("empId", updateEmployee.getEmpId());
			parameter.addValue("firstNm", updateEmployee.getFirstNm());
			parameter.addValue("middleNm", updateEmployee.getMiddleNm());
			parameter.addValue("lastNm", updateEmployee.getLastNm());
			parameter.addValue("gender", updateEmployee.getGender());
			parameter.addValue("birthDt", updateEmployee.getBirthDt());
			parameter.addValue("age", updateEmployee.getAge());
			parameter.addValue("bloodGp", updateEmployee.getBloodGp());
			parameter.addValue("religion", updateEmployee.getReligion());
			parameter.addValue("salary", updateEmployee.getSalary());
			parameter.addValue("mobileNo", updateEmployee.getMobileNo());
			parameter.addValue("email", updateEmployee.getEmail());
			parameter.addValue("pincode", updateEmployee.getPincode());
			parameter.addValue("streetNm", updateEmployee.getStreetNm());
			parameter.addValue("cityNm", updateEmployee.getCityNm());
			parameter.addValue("countryNm", updateEmployee.getCountryNm());

			status = namedParameterJdbcTemplate.update(queryForUpdateEmployee, parameter) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Boolean inactiveEmployeeByMapSqlParameter(DeleteEmployeeReq deleteEmployeeReq) {
		MapSqlParameterSource[] parameterSource = new MapSqlParameterSource[deleteEmployeeReq.getEmployeeList().size()];
		Integer rowAffected = 0;
		int batchUpdate[] = null;
		try {
			for (DeleteEmployeeBean deleteEmployee : deleteEmployeeReq.getEmployeeList()) {
				deleteEmployee.setOrgId(deleteEmployeeReq.getOrgId());
				MapSqlParameterSource parameter = new MapSqlParameterSource();
				parameter.addValue("orgId", deleteEmployee.getOrgId());
				parameter.addValue("empId", deleteEmployee.getEmpId());
				parameterSource[rowAffected++] = parameter;
			}
			batchUpdate = namedParameterJdbcTemplate.batchUpdate(queryForPartialDelete, parameterSource);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return batchUpdate.length == deleteEmployeeReq.getEmployeeList().size();
	}

	@Override
	public Boolean insertEmployeeDetailsByBeanProperty(InsertEmployeeReq employeeReq) {
		Integer count = 0;

		try {
			count = namedParameterJdbcTemplate.update(queryForInsertEmployee,
					new BeanPropertySqlParameterSource(employeeReq));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count > 0;
	}

	@Override
	public Boolean updateEmployeeDetailsByBeanProperty(UpdateEmployeeReq updateEmployeeReq) {
		Boolean status = false;

		try {
			status = namedParameterJdbcTemplate.update(queryForUpdateEmployee,
					new BeanPropertySqlParameterSource(updateEmployeeReq)) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Boolean batchInsertByBeanProperty(List<InsertEmployeeReq> insertEmployeeList) {
		int count[] = null;
		try {
			List<BeanPropertySqlParameterSource> batchArgs = new ArrayList<>();
			insertEmployeeList.forEach(employee -> {
				batchArgs.add(new BeanPropertySqlParameterSource(employee));
			});
			count = namedParameterJdbcTemplate.batchUpdate(queryForInsertEmployee,
					batchArgs.toArray(new BeanPropertySqlParameterSource[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count.length == insertEmployeeList.size();
	}

	@Override
	public Boolean batchUpdateByBeanProperty(List<UpdateEmployeeReq> updateEmployeeList) {
		int count[] = null;
		try {
			List<BeanPropertySqlParameterSource> batchArgs = new ArrayList<>();
			updateEmployeeList.forEach(employee -> {
				batchArgs.add(new BeanPropertySqlParameterSource(employee));
			});
			count = namedParameterJdbcTemplate.batchUpdate(queryForUpdateEmployee,
					batchArgs.toArray(new BeanPropertySqlParameterSource[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count.length == updateEmployeeList.size();
	}

	@Override
	public Boolean batchInsertBySqlParameter(List<InsertEmployeeReq> insertEmployeeList) {
		int[] insertCount = null;
		try {
			SqlParameterSource[] batchArray = SqlParameterSourceUtils.createBatch(insertEmployeeList.toArray());
			insertCount = namedParameterJdbcTemplate.batchUpdate(queryForInsertEmployee, batchArray);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return insertCount.length == insertEmployeeList.size();
	}

	@Override
	public Boolean batchUpdateBySqlParameter(List<UpdateEmployeeReq> updateEmployeeList) {
		int[] updateCount = null;
		try {
			SqlParameterSource[] batchArray = SqlParameterSourceUtils.createBatch(updateEmployeeList.toArray());
			updateCount = namedParameterJdbcTemplate.batchUpdate(queryForUpdateEmployee, batchArray);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return updateCount.length == updateEmployeeList.size();
	}

	@Override
	public Boolean batchInactiveBySqlParameter(DeleteEmployeeReq deleteEmployeeReq) {
		int count = 0;
		int[] updateCount = null;
		SqlParameterSource[] batchDelete = new SqlParameterSource[deleteEmployeeReq.getEmployeeList().size()];
		try {
			for (DeleteEmployeeBean deleteEmployee : deleteEmployeeReq.getEmployeeList()) {
				deleteEmployee.setOrgId(deleteEmployeeReq.getOrgId());
				MapSqlParameterSource params = new MapSqlParameterSource();
				params.addValue("orgId", deleteEmployee.getOrgId());
				params.addValue("empId", deleteEmployee.getEmpId());
				batchDelete[count++] = params;
			}
			updateCount = namedParameterJdbcTemplate.batchUpdate(queryForPartialDelete, batchDelete);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return updateCount.length == deleteEmployeeReq.getEmployeeList().size();
	}

	@Override
	public Boolean batchInsertByJdbcTemplate(List<InsertEmployeeReq> insertEmployeeList) {
		int[] rowAffected = null;
		try {
			rowAffected = jdbcTemplate.batchUpdate(queryForInsert, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					InsertEmployeeReq insertBatch = insertEmployeeList.get(i);
					ps.setString(1, insertBatch.getFirstNm());
					ps.setString(2, insertBatch.getMiddleNm());
					ps.setString(3, insertBatch.getLastNm());
					ps.setString(4, insertBatch.getGender());
					ps.setDate(5, insertBatch.getBirthDt());
					ps.setInt(6, insertBatch.getAge());
					ps.setString(7, insertBatch.getBloodGp());
					ps.setString(8, insertBatch.getReligion());
					ps.setDouble(9, insertBatch.getSalary());
					if (StringUtils.isNotBlank(insertBatch.getMobileNo())) {
						ps.setString(10, insertBatch.getMobileNo());
					} else {
						ps.setNull(10, java.sql.Types.VARCHAR);
					}
					if (StringUtils.isNotBlank(insertBatch.getEmail())) {
						ps.setString(11, insertBatch.getEmail());
					} else {
						ps.setNull(11, java.sql.Types.VARCHAR);
					}
					ps.setString(12, insertBatch.getPincode());
					ps.setString(13, insertBatch.getStreetNm());
					ps.setString(14, insertBatch.getCityNm());
					ps.setString(15, insertBatch.getCountryNm());
				}

				@Override
				public int getBatchSize() {
					return insertEmployeeList.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return rowAffected.length == insertEmployeeList.size();
	}

	@Override
	public Boolean batchUpdateByJdbcTemplate(List<UpdateEmployeeReq> updateEmployeeList) {
		int[] rowAffected = null;
		try {
			rowAffected = jdbcTemplate.batchUpdate(queryForUpdate, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					UpdateEmployeeReq updateBatch = updateEmployeeList.get(i);
					ps.setString(1, updateBatch.getFirstNm());
					ps.setString(2, updateBatch.getMiddleNm());
					ps.setString(3, updateBatch.getLastNm());
					ps.setString(4, updateBatch.getGender());
					ps.setDate(5, updateBatch.getBirthDt());
					ps.setInt(6, updateBatch.getAge());
					ps.setString(7, updateBatch.getBloodGp());
					ps.setString(8, updateBatch.getReligion());
					ps.setDouble(9, updateBatch.getSalary());
					if (StringUtils.isNotBlank(updateBatch.getMobileNo())) {
						ps.setString(10, updateBatch.getMobileNo());
					} else {
						ps.setNull(10, java.sql.Types.VARCHAR);
					}
					if (StringUtils.isNotBlank(updateBatch.getEmail())) {
						ps.setString(11, updateBatch.getEmail());
					} else {
						ps.setNull(11, java.sql.Types.VARCHAR);
					}
					ps.setString(12, updateBatch.getPincode());
					ps.setString(13, updateBatch.getStreetNm());
					ps.setString(14, updateBatch.getCityNm());
					ps.setString(15, updateBatch.getCountryNm());
					ps.setInt(16, 1);
					ps.setDate(17, Date.valueOf(LocalDate.now()));
					ps.setLong(18, updateBatch.getEmpId());
				}

				@Override
				public int getBatchSize() {
					return updateEmployeeList.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return rowAffected.length == updateEmployeeList.size();
	}

	@Override
	public Boolean batchInactiveByJdbcTemplate(List<Integer> idList) {
		int[] rowAffected = null;
		try {
			rowAffected = jdbcTemplate.batchUpdate(queryForSoftDelete, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Integer empId = idList.get(i);
					ps.setLong(1, empId);
				}

				@Override
				public int getBatchSize() {
					return idList.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowAffected.length == idList.size();
	}

	@Override
	public Boolean insertEmployee(List<InsertEmployeeBean> saveEmpList) {
		int[] rowAffected = null;
		try {
			rowAffected = namedParameterJdbcTemplate.batchUpdate(queryForInsertEmployee,
					SqlParameterSourceUtils.createBatch(saveEmpList.toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowAffected.length == saveEmpList.size();
	}

	@Override
	public Boolean updateEmployee(List<InsertEmployeeBean> updateEmpList) {
		int[] rowAffected = null;
		try {
			rowAffected = namedParameterJdbcTemplate.batchUpdate(queryForUpdateEmployee,
					SqlParameterSourceUtils.createBatch(updateEmpList.toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowAffected.length == updateEmpList.size();
	}

	@Override
	public Boolean insertEmployeeAndGetKeyHolder(InsertEmployeeReqs insertEmployee) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int insertCount = 0;
		try {
			for (InsertEmployeeBean insertEmp : insertEmployee.getEmployeeList()) {
				MapSqlParameterSource parameters = new MapSqlParameterSource();
				insertEmp.setOrgId(insertEmployee.getOrgId());

				parameters.addValue("orgId", insertEmp.getOrgId());
				parameters.addValue("firstNm", insertEmp.getFirstNm());
				parameters.addValue("middleNm", insertEmp.getMiddleNm());
				parameters.addValue("lastNm", insertEmp.getLastNm());
				parameters.addValue("gender", insertEmp.getGender());
				parameters.addValue("birthDt", insertEmp.getBirthDt());
				parameters.addValue("age", insertEmp.getAge());
				parameters.addValue("bloodGp", insertEmp.getBloodGp());
				parameters.addValue("religion", insertEmp.getReligion());
				parameters.addValue("salary", insertEmp.getSalary());
				parameters.addValue("mobileNo", insertEmp.getMobileNo());
				parameters.addValue("email", insertEmp.getEmail());
				parameters.addValue("pincode", insertEmp.getPincode());
				parameters.addValue("streetNm", insertEmp.getStreetNm());
				parameters.addValue("cityNm", insertEmp.getCityNm());
				parameters.addValue("countryNm", insertEmp.getCountryNm());

				insertCount = namedParameterJdbcTemplate.update(queryForInsertEmployee, parameters, keyHolder);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return insertCount > 0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public FetchEmployeeReq fetchEmployeeDetails(FetchEmployeeReq fetchEmployeeReq) {

		FetchEmployeeReq fetchEmployee = null;
		try {
			fetchEmployee = jdbcTemplate.queryForObject(queryForObjectByJdbcTemplate,
					new Object[] { fetchEmployeeReq.getEmpId(), fetchEmployeeReq.getOrgId() },
					new BeanPropertyRowMapper<>(FetchEmployeeReq.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fetchEmployee;
	}

	@Override
	public Map<String, Object> fetchEmployeeByJdbcQueryForMap(FetchEmployeeReq fetchEmployeeReq) {

		Map<String, Object> queryForMap = new HashMap<>();
		try {
			queryForMap = jdbcTemplate.queryForMap(queryForMapByJdbcTemplate, fetchEmployeeReq.getEmpId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryForMap;
	}

	@Override
	public List<Map<String, Object>> fetchEmployeeByJdbcQueryForList(FetchEmployeeReq fetchEmployeeReq) {

		List<Map<String, Object>> queryForList = new ArrayList<>();
		try {
			queryForList = jdbcTemplate.queryForList(queryForListByJdbcTemplate, fetchEmployeeReq.getOrgId(),
					fetchEmployeeReq.getReligion());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryForList;
	}

	@Override
	public Long insertEmployee(InsertEmployeeBean insertEmployeeObj) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			namedParameterJdbcTemplate.update(queryForInsertEmployee,
					new BeanPropertySqlParameterSource(insertEmployeeObj), keyHolder, new String[] { "emp_id" });
		} catch (Exception e) {
			e.printStackTrace();
		}
		Number number = keyHolder.getKey();
		return number.longValue();
	}

	@Override
	public Boolean insertEmpDept(Long empId, List<EmployeeDeptBean> saveEmpDeptList) {
		int[] rowAffected = null;
		try {
			rowAffected = jdbcTemplate.batchUpdate(queryForInsertEmpDept, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					EmployeeDeptBean employeeDeptObj = saveEmpDeptList.get(i);
					ps.setLong(1, empId);
					ps.setLong(2, employeeDeptObj.getDeptId());
					ps.setLong(3, employeeDeptObj.getOrgId());
				}

				@Override
				public int getBatchSize() {
					return saveEmpDeptList.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowAffected.length == saveEmpDeptList.size();
	}

	@Override
	public Boolean updateEmpDept(List<EmployeeDeptBean> updateEmpDeptList) {
		int[] rowAffeted = null;
		try {
			rowAffeted = jdbcTemplate.batchUpdate(queryForUpdateEmpDept, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					EmployeeDeptBean employeeDeptObj = updateEmpDeptList.get(i);
					ps.setLong(1, employeeDeptObj.getOrgId());
					ps.setLong(2, employeeDeptObj.getDeptId());
					ps.setLong(3, employeeDeptObj.getEmpDeptId());
				}

				@Override
				public int getBatchSize() {
					return updateEmpDeptList.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowAffeted.length == updateEmpDeptList.size();
	}

	@Override
	public Boolean updateEmployee(InsertEmployeeBean insertEmployeeObj) {
		Boolean updateFlag = false;
		try {
			updateFlag = namedParameterJdbcTemplate.update(queryForUpdateEmployee,
					new BeanPropertySqlParameterSource(insertEmployeeObj)) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateFlag;
	}

	@Override
	public FetchEmployeeRes fetchEmployeeByQueryForObject(FetchEmployeeReq fetchEmployeeReq) {

		FetchEmployeeRes employeeRes = null;
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("empId", fetchEmployeeReq.getEmpId());
			paramMap.put("orgId", fetchEmployeeReq.getOrgId());

			employeeRes = namedParameterJdbcTemplate.queryForObject(queryForFetchEmpObj, paramMap,
					new BeanPropertyRowMapper<>(FetchEmployeeRes.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeRes;
	}

	@Override
	public Map<String, Object> fetchEmployeeByQueryForMap(FetchEmployeeReq fetchEmployeeReq) {

		Map<String, Object> empMap = null;
		try {
			empMap = namedParameterJdbcTemplate.queryForMap(queryForFetchEmpMap,
					new BeanPropertySqlParameterSource(fetchEmployeeReq));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empMap;
	}

	@Override
	public List<Map<String, Object>> fetchEmployeeByQueryForList(FetchEmployeeReq fetchEmployeeReq) {

		List<Map<String, Object>> empMapList = null;
		try {
			StringBuilder builder = new StringBuilder(queryForFetchEmpList);

			if (StringUtils.isNotBlank(fetchEmployeeReq.getFirstNm())) {
				builder.append(" and concat(first_nm, ' ',middle_nm, ' ',last_nm) ilike '%'||:firstNm||'%'");
			}
			if (StringUtils.isNotBlank(fetchEmployeeReq.getEmail())) {
				builder.append(" and email=:email");
			}
			if (StringUtils.isNotBlank(fetchEmployeeReq.getMobileNo())) {
				builder.append(" and mobile_no=:mobileNo");
			}
			if (StringUtils.isNotBlank(fetchEmployeeReq.getGender())) {
				builder.append(" and gender=:gender");
			}
			if (StringUtils.isNotBlank(fetchEmployeeReq.getCityNm())) {
				builder.append(" and city_nm=:cityNm");
			}
			if (fetchEmployeeReq.getPageNo() != null) {
				builder.append(" limit 30 offset :pageNo*30");
			}

			empMapList = namedParameterJdbcTemplate.queryForList(builder.toString(),
					new BeanPropertySqlParameterSource(fetchEmployeeReq));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empMapList;
	}

	@Override
	public List<FetchEmployeeRes> fetchEmployeeByQuery(FetchEmployeeReq fetchEmployeeReq) {
		List<FetchEmployeeRes> empResList = new ArrayList<>();
		try {
			empResList = namedParameterJdbcTemplate.query(queryForFetchEmpQuery,
					new BeanPropertySqlParameterSource(fetchEmployeeReq),
					new BeanPropertyRowMapper<>(FetchEmployeeRes.class));
			if (empResList == null || empResList.isEmpty()) {
				throw new ListNotFoundException("List not found");
			}
		} catch (Exception e) {
			throw new ListNotFoundException("List not found");
		}
		return empResList;
	}

	@Override
	public List<Map<String, Object>> fetchEmployeeWithDepartmentDetails(FetchEmployeeReq fetchEmployeeReq) {

		List<Map<String, Object>> empDeptList = new ArrayList<>();
		try {
			empDeptList = namedParameterJdbcTemplate.queryForList(queryForFetchEmpDeptDetails,
					new BeanPropertySqlParameterSource(fetchEmployeeReq));
			if (empDeptList == null || empDeptList.isEmpty()) {
				throw new ListNotFoundException("List not found");
			}
		} catch (Exception e) {
			throw new ListNotFoundException("List not found");
		}
		return empDeptList;
	}
	
	public List<Map<String, Object>> fetchDepartmentByGender(FetchEmployeeReq fetchEmployeeReq) {
		List<Map<String, Object>> deptList = null;
		try {
			deptList = namedParameterJdbcTemplate.queryForList(queryForDepartmentByGender, 
					new BeanPropertySqlParameterSource(fetchEmployeeReq));
			if (deptList == null || deptList.isEmpty()) {
				throw new ListNotFoundException("List not found");
			}
		} catch (Exception e) {
			throw new ListNotFoundException("List not found");
		}
		return deptList;
	}
}
