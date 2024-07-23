package com.itwill.springboot2.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentService departmentService;

	@GetMapping("/department/list")
	public String departmentList(Model model) {
		log.info("부서목록");
		List<Department> deptList = departmentService.deptList();
		model.addAttribute("deptList", deptList);

		return "/department/list";
	}

	@GetMapping("/department/detail/{deptno}")
	public String departmentDetail(@PathVariable int deptno, Model model) {
		log.info("부서상세");
		// log.info("deptno={}", deptno);
		List<Employee> deptEmp = departmentService.deptDetail(deptno);
		model.addAttribute("deptEmp", deptEmp);

		return "/department/details";
	}
}
