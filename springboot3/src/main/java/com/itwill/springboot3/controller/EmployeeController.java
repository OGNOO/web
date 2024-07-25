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

import com.itwill.springboot3.domain.JobHistory;
import com.itwill.springboot3.dto.EmployeeDetailDto;
import com.itwill.springboot3.dto.EmployeeListItemDto;
import com.itwill.springboot3.dto.JobHistoryDto;
import com.itwill.springboot3.service.EmployeeService;
import com.itwill.springboot3.service.JobHistoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	private final JobHistoryService jobHistoryService;

	@GetMapping("/list")
	public void employeeList(@RequestParam(name = "p", defaultValue = "0") int pageNo, Model model) {
		log.info("직원목록");
		Sort sort = Sort.by(Sort.Order.asc("id"));
		Page<EmployeeListItemDto> empList = employeeService.empList(pageNo, sort);
		model.addAttribute("empList", empList);

	}

	@GetMapping("/detail/{id}")
	public String employeeDetail(@PathVariable(name = "id") Integer id, Model model) {
		log.info("직원상세");
		log.info("id={}", id);
		EmployeeDetailDto employee = employeeService.empDetail(id);
		model.addAttribute("employee", employee);

		return "/employee/details";
	}

	@GetMapping("/jobHistory")
	public String jobHistoryList(Model model) {
		log.debug("jobHistoryList()");
		List<JobHistory> jobHistoryList = jobHistoryService.jobHistoryList();
		model.addAttribute("jobHistoryList", jobHistoryList);
		List<JobHistoryDto> jobHistoryDtos = jobHistoryService.jobHistoryDtoList();
		model.addAttribute("jobHistoryDtos", jobHistoryDtos);
		System.out.println(jobHistoryDtos);

		return "/employee/jobHistory";
	}

}
