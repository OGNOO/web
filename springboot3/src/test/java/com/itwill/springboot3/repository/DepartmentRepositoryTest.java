package com.itwill.springboot3.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.dto.EmployeeListItemDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class DepartmentRepositoryTest {

	@Autowired
	private DepartmentRepository departmentRepository;

//	@Test
	@Transactional
	public void testFindAll() {
		List<Department> list = departmentRepository.findAll();
		log.info(list.toString());
	}

//	@Test
	@Transactional
	public void testFindById() {
		Department department = departmentRepository.findById(150).orElseThrow();
		log.info("dept={}", department);
		log.info("dept.manager={}", department.getEmployee());
		log.info("dept.location={}", department.getLocation());
	}
}
