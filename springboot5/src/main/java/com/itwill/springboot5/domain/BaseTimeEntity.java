package com.itwill.springboot5.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@MappedSuperclass // 다른 엔티티 클래스의 상위 클래스로 사용됨.
@EntityListeners(AuditingEntityListener.class)
//-> 엔티티 (최초) 생성시간, (최종) 수정시간 등을 자동으로 DB에 저장하기 위해서.
public abstract class BaseTimeEntity {

	@CreatedDate // 엔티티(최초) 생성 시간을 저장하는 필드.
	@Column(name = "createdtime", updatable = false)
	private LocalDateTime createdTime;

	@LastModifiedDate // 엔티티(최종) 수정시간을 저장하는 필드.
	@Column(name = "modifiedtime")
	private LocalDateTime modifiedTime;
}
