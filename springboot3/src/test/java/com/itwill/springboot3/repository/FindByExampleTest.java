package com.itwill.springboot3.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import com.itwill.springboot3.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class FindByExampleTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	void test() {
		log.info("test");
		Employee emp = Employee.builder().firstName("Lex").commissionPct(null).build();
		Example<Employee> ex = Example.of(emp);
		List<Employee> employees = employeeRepository.findAll(ex);
		employees.forEach(i -> System.out.println(i));
	}
}
