package com.itwill.springboot3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.springboot3.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  List<Employee> findByDepartmentId(Integer id);
}