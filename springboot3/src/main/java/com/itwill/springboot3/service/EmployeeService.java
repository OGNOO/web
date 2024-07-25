package com.itwill.springboot3.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.dto.EmployeeDetailDto;
import com.itwill.springboot3.dto.EmployeeListItemDto;
import com.itwill.springboot3.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Transactional(readOnly = true)
	public Page<EmployeeListItemDto> empList(int pageNo, Sort sort) {
		log.info("직원목록 서비스");
		Pageable pageable = PageRequest.of(pageNo, 10, sort);
		Page<Employee> employeePage = employeeRepository.findAll(pageable);
		log.info("hasPre = {}", employeePage.hasPrevious());
		log.info("haxNext = {}", employeePage.hasNext());
		log.info("getTotalPages={}", employeePage.getTotalPages());
		Page<EmployeeListItemDto> employeeListItemDtos = employeePage.map(EmployeeListItemDto::fromEntity);

		return employeeListItemDtos;
	}

	@Transactional(readOnly = true)
	public EmployeeDetailDto empDetail(Integer id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		EmployeeDetailDto employeeDetailDto;
		if (optionalEmployee.isPresent()) {
			employeeDetailDto = EmployeeDetailDto.fromEntity(optionalEmployee.get());
		} else {
			employeeDetailDto = new EmployeeDetailDto();
		}

		return employeeDetailDto;
	}
}
