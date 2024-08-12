package com.itwill.springboot5.domain;

public enum MemberRole {
	USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), STEP("ROLE_STEP");

	private String authority;

	MemberRole(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return this.authority;
	}
}
