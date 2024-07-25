package com.itwill.springboot3.dto;

import java.time.LocalDate;

import com.itwill.springboot3.domain.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDetailDto {
	private Integer employeeId;
	private String employeeName;
	private String email;
	private String phoneNumber;
	private LocalDate hireDate;
	private String jobtitle;
	private Double salary;
	private Double commissionPct;
	private String managerName;
	private String departmentName;

	public static EmployeeDetailDto fromEntity(Employee employee) {
		String deptName = (employee.getDepartment() != null) ? employee.getDepartment().getDepartmentName() : null;
		String jobTitle = (employee.getJob() != null) ? employee.getJob().getJobTitle() : null;
		String managerName = (employee.getManager() != null)
				? employee.getManager().getFirstName() + " " + employee.getManager().getLastName()
				: null;

		return EmployeeDetailDto.builder().employeeId(employee.getId())
				.employeeName(employee.getFirstName() + " " + employee.getLastName()).email(employee.getEmail())
				.phoneNumber(employee.getPhoneNumber()).hireDate(employee.getHireDate().toLocalDate())
				.jobtitle(jobTitle).commissionPct(employee.getCommissionPct()).managerName(managerName)
				.departmentName(deptName).build();
	}
}
