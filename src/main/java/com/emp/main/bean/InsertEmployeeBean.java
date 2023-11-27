package com.emp.main.bean;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertEmployeeBean {
	private Long orgId;
	private Long empId;
	private String firstNm;
    private String middleNm;
    private String lastNm;
    private String gender;
    private Date birthDt;
    private Integer age;
    private String bloodGp;
    private String religion;
    private Double salary;
    private String mobileNo;
    private String email;
    private String pincode;
    private String streetNm;
    private String cityNm;
    private String countryNm;
    private List<EmployeeDeptBean> empDeptList;
}
