package com.itwill.springboot5.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "COMMENTS")
public class Comment extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_ID")
	private Post post;

	@Column(name = "CTEXT", length = 500)
	@NotBlank(message = "댓글 내용은 필수 입력 항목입니다.")
	@Size(min = 1, max = 500, message = "댓글 내용은 1자 이상, 1000자 이하로 입력해야 합니다.")
	private String ctext;

	@Column(name = "WRITER", length = 20, updatable = false) // 업데이트 할땐 이 컬럼 무시됨
	@NotBlank(message = "작성자는 필수 입력 항목입니다.")
	@Size(min = 1, max = 20, message = "작성자는 1자 이상, 20자 이하로 입력해야 합니다.")
	private String writer;

}
