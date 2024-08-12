package com.itwill.springboot5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot5.dto.RegisterDto;
import com.itwill.springboot5.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/signin")
	public void signin() {
		log.info("signin()");
	}

	@GetMapping("/register")
	public void register() {
	}

	@PostMapping("/register")
	public String register(@Valid RegisterDto registerDto, BindingResult result) {
		System.out.println(registerDto);
		System.out.println("\n" + registerDto.getRoles() + "\n");
		if (result.hasErrors()) {
			// 유효성 검사 실패
			return "redirect:/member/register";
		}
		memberService.createMember(registerDto);

		return "redirect:/member/signin";
	}

}
