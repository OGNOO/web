package com.itwill.springboot5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	// HomeController 없어도 됨 파일 이름이 index 가 있으면

	@GetMapping("/")
	public String home() {
		log.info("home()");
		
		return "index";
	}
}
