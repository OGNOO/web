package com.itwill.springboot3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping("/list")
	public String employeeList(Model model) {
		log.info("직원목록");
		List<Employee> empList = employeeService.empList();
		model.addAttribute("empList", empList);

		return "/employee/list";
	}

	@GetMapping("/detail/{id}")
	public String employeeDetail(@PathVariable Integer id, Model model) {
		log.info("직원상세");
		log.info("id={}", id);
		Employee employee = employeeService.empDetail(id);
		model.addAttribute("employee", employee);

		return "/employee/details";
	}

}
