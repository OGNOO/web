package com.itwill.springboot3.dto;

import com.itwill.springboot3.domain.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeListItemDto {
	private Integer employeeId;
	private String employeeName;
	private String phoneNumber;
	private String jobTitle;
	private String departmentName;

	public static EmployeeListItemDto fromEntity(Employee employee) {
		String deptName = (employee.getDepartment() != null) ? employee.getDepartment().getDepartmentName() : null;
		String jobTitle = (employee.getJob() != null) ? employee.getJob().getJobTitle() : null;

		return EmployeeListItemDto.builder().employeeId(employee.getId())
				.employeeName(employee.getFirstName() + " " + employee.getLastName())
				.phoneNumber(employee.getPhoneNumber()).jobTitle(jobTitle).departmentName(deptName).build();
	}
}
