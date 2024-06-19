package com.itwill.spring2.dto;

import com.itwill.spring2.repository.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDto {
	private String userId;
	private String password;
	private String email;

	public User toEntity() {
		return User.builder().userId(userId).password(password).email(email).build();
	}
}
