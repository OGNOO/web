package com.itwill.springboot3.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.dto.DepartmentListItemDto;
import com.itwill.springboot3.dto.EmployeeListItemDto;
import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.repository.DepartmentRepository;
import com.itwill.springboot3.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final EmployeeRepository employeeRepository;

	@Transactional(readOnly = true)
	public Page<DepartmentListItemDto> deptList(int pageNo, Sort sort) {
		log.info("부서목록 서비스");
		Pageable pageable = PageRequest.of(pageNo, 5, sort);
		Page<Department> deptPage = departmentRepository.findAll(pageable);
		Page<DepartmentListItemDto> deptList = deptPage.map(DepartmentListItemDto::fromEntity);

		return deptList;
	}

	@Transactional(readOnly = true)
	public List<EmployeeListItemDto> deptDetail(Integer deptno) {
		log.info("부서상세 서비스");
		Optional<List<Employee>> optionalListEmployee = Optional
				.ofNullable(employeeRepository.findByDepartmentId(deptno));
		List<EmployeeListItemDto> listEmployee;
		if (optionalListEmployee.isPresent()) {
			listEmployee = optionalListEmployee.get().stream().map(EmployeeListItemDto::fromEntity).toList();
		} else {
			listEmployee = Collections.emptyList();
		}

		return listEmployee;
	}
}
