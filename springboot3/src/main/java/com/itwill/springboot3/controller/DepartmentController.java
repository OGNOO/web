package com.itwill.springboot3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

	private final DepartmentService departmentService;

	@GetMapping("/list")
	public String departmentList(Model model) {
		log.info("부서목록");
		List<Department> deptList = departmentService.deptList();
		model.addAttribute("deptList", deptList);

		return "/department/list";
	}

	@GetMapping("/detail/{deptno}")
	public String departmentDetail(@PathVariable Integer deptno, Model model) {
		log.info("부서상세");
		List<Employee> deptEmp = departmentService.deptDetail(deptno);
		model.addAttribute("deptEmp", deptEmp);
		System.out.println(deptEmp);

		return "/department/details";
	}
}
