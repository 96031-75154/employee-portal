package com.emp.main.bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FetchEmpDeptRes {
	private Long empId;
	private String empNm;
	private String gender;
    private Date birthDt;
    private Integer age;
    private String bloodGp;
    private String religion;
    private BigDecimal salary;
    private String mobileNo;
    private String email;
    private String pincode;
    private String streetNm;
    private String cityNm;
    private String countryNm;
    private List<FetchEmpDeptBean> deptList;
}
