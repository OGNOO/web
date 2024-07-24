package com.itwill.springboot3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.springboot3.domain.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
