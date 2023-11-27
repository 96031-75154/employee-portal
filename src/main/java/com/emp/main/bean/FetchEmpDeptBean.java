package com.emp.main.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FetchEmpDeptBean {
	private Long empDeptId;
	private Long deptId;
	private String deptNm;
}
