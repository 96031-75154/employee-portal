package com.emp.main.bean;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelSheetRes {
	private Long empId;
	private Long orgId;
	private String firstNm;
    private String middleNm;
    private String lastNm;
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
}
