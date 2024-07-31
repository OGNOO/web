package com.itwill.springboot4.domain;

import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "QUESTION2")
public class Question2 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic(optional = false)
	private String question;

	@OneToMany // 이건 거의 안씀 - 테이블 구조 복잡해지고, 문제 발생 가능성 증가됨
	private Set<Answer2> answers;
}
