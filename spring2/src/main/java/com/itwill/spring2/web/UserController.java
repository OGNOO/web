package com.itwill.spring2.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.spring2.dto.UserCreateDto;
import com.itwill.spring2.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

//	private final HttpSession session;
	private final UserService userService;

	@GetMapping("/signup")
	public void signup() {
		log.debug("signup()");
	}

	@PostMapping("/signup")
	public String signup(UserCreateDto userCreateDto) {
		System.out.println("회원가입");
		int res = userService.insert(userCreateDto);
		if (res == 1) {
			return "redirect:/";
		} else {
			return "redirect:/signup";
		}
	}

	@ResponseBody
	@GetMapping("/checkId")
	public ResponseEntity<String> checkId(@RequestParam(name = "userId") String userId) {
		boolean res = userService.checkUserId(userId);
		if (res) {
			return ResponseEntity.ok("Y");
		} else {
			return ResponseEntity.ok("N");
		}
	}
	
	@GetMapping("/signin")
	public void signin() {
	}
}
