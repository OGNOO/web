package com.itwill.springboot3.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot3.dto.DepartmentListItemDto;
import com.itwill.springboot3.dto.EmployeeListItemDto;
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
	public void departmentList(@RequestParam(name = "p", defaultValue = "0") int pageNo, Model model) {
		log.info("부서목록");
		Sort sort = Sort.by(Sort.Order.asc("id"));
		Page<DepartmentListItemDto> deptList = departmentService.deptList(pageNo, sort);
		model.addAttribute("deptList", deptList);

	}

	@GetMapping("/detail/{deptno}")
	public String departmentDetail(@PathVariable(name = "deptno") Integer deptno, Model model) {
		log.info("부서상세");
		List<EmployeeListItemDto> deptEmp = departmentService.deptDetail(deptno);
		model.addAttribute("empList", deptEmp);

		return "/department/details";
	}

	@GetMapping("/details")
	public void details(@RequestParam(name = "dname") String departmentName, Model model) {
		log.info("departmentName={}", departmentName);
		Integer deptno = departmentService.selectDeptId(departmentName);
		List<EmployeeListItemDto> deptEmp = departmentService.deptDetail(deptno);
		model.addAttribute("empList", deptEmp);
	}

}
