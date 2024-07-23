package com.itwill.springboot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot2.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
