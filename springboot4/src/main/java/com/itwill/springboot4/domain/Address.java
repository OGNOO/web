package com.itwill.springboot4.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable // 다른 엔티티 클래스의 필드로 포함되는 객체.
public class Address {
	private Integer postalCode;
	private String city;
	private String street;
	private Integer streetNo;
}
