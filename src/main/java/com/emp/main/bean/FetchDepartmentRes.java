package com.emp.main.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchDepartmentRes {
	private String deptNm;
	private String gender;
	private Long empCount;
}
