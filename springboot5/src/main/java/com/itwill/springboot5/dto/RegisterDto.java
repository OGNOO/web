package com.itwill.springboot5.dto;

import java.util.Set;

import com.itwill.springboot5.domain.Member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Datapublic class RegisterDto {

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	private String email;

	@NotEmpty
	private Set<String> roles;

	public Member toEntity() {
		return Member.builder().username(username).password(password).email(email).build();
	}
}
