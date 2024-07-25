package com.itwill.springboot3.dto;

import com.itwill.springboot3.domain.Department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentListItemDto {
	private Integer departmentId;
	private String departmentName;
	private String manager;
	private String departmentCity;

	public static DepartmentListItemDto fromEntity(Department department) {
		String managerName = (department.getEmployee() != null)
				? department.getEmployee().getFirstName() + ' ' + department.getEmployee().getLastName()
				: null;

		return DepartmentListItemDto.builder().departmentId(department.getId())
				.departmentName(department.getDepartmentName()).manager(managerName)
				.departmentCity(department.getLocation().getCity()).build();
	}
}
