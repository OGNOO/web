package com.itwill.springboot4.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "QUESTIONS")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QID") // 엔티티 필드 이름과 테이블 컬럼 이름이 다를때
	private Long id;
	
	@Basic(optional = false)
	private String title;
	
	@Basic(optional = false)
	@Column(length = 1000)
	private String content;
}
