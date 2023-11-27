package com.emp.main.bean;

import java.sql.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FetchEmployeeReq {
	@ApiModelProperty(value = "It indicates the orgId of the employee belongs to", required = true)
	private Long orgId;
	@ApiModelProperty(value = "It indicates the empId", required = true)
	private Long empId;
	@ApiModelProperty(value = "It indicates the firstNm of the employee", required = true)
	private String firstNm;
	@ApiModelProperty(value = "It indicates the middleNm of the employee", required = true)
    private String middleNm;
	@ApiModelProperty(value = "It indicates the lastNm of the employee", required = true)
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
    private Integer pageNo;
}
