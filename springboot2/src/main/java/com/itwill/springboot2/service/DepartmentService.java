package com.itwill.springboot2.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {

	private final DepartmentRepository departmentRepository;

	@Transactional
	public List<Department> deptList() {
		log.info("부서목록 서비스");
		Optional<List<Department>> optionalDeptList = Optional.ofNullable(departmentRepository.findAll());
		List<Department> deptList;
		if (optionalDeptList.isPresent()) {
			deptList = optionalDeptList.get();
		} else {
			deptList = Collections.emptyList();
		}

		return deptList;
	}

	@Transactional
	public List<Employee> deptDetail(Integer deptno) {
		log.info("부서상세 서비스");
		Optional<Department> optionalDepartment = departmentRepository.findById(deptno);
		List<Employee> employeels;
		if (optionalDepartment.isPresent()) {
			Department department = optionalDepartment.get();
			employeels = department.getEmployee();
		} else {
			employeels = Collections.emptyList();
		}

		return employeels;
	}

}
