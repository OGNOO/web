package com.itwill.springboot5.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString(callSuper = true) // 상위 클래스의 toString()을 호출해서 toString() 메서드를 작성.
@EqualsAndHashCode(callSuper = true) // 상위 클래스의 필드들도 사용해서 equals(), hashCode() 작성
@Entity
@Table(name = "POSTS")
public class Post extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITLE", length = 100)
	@NotBlank(message = "제목은 필수 입력 항목입니다.")
	@Size(min = 1, max = 100, message = "제목은 1자 이상, 100자 이하로 입력해야 합니다.")
	private String title;

	@Column(name = "CONTENT", length = 1000)
	@NotBlank(message = "내용은 필수 입력 항목입니다.")
	@Size(min = 1, max = 1000, message = "내용은 1자 이상, 1000자 이하로 입력해야 합니다.")
	private String content;

	@Column(name = "AUTHOR", length = 20, updatable = false) // 업데이트 할땐 이 컬럼 무시됨
	@NotBlank(message = "작성자는 필수 입력 항목입니다.")
	@Size(min = 1, max = 20, message = "작성자는 1자 이상, 20자 이하로 입력해야 합니다.")
	private String author;

	// update 기능(제목/내용 수정)에서 사용할 공개 메서드
//	public Post update(String title, String content) {
//		this.title = title;
//		this.content = content;
//
//		return this;
//	}
}
