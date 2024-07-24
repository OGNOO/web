package com.itwill.springboot3.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	@Transactional
	public List<Employee> empList(){
		log.info("직원목록 서비스");
		Optional<List<Employee>> optionalEmpList = Optional.ofNullable(employeeRepository.findAll());
		List<Employee> empList;
		if (optionalEmpList.isPresent()) {
			empList = optionalEmpList.get();
			System.out.println(empList);
		} else {
			empList = Collections.emptyList();
		}

		return empList;
	}

	@Transactional
	public Employee empDetail(Integer id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		Employee employee;
		if (optionalEmployee.isPresent()) {
			employee = optionalEmployee.get();
		} else {
			employee = new Employee();
		}

		return employee;
	}
}
