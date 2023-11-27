package com.emp.main.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertEmployeeReqs {
	private Long orgId;
	private List<InsertEmployeeBean> employeeList;
}
