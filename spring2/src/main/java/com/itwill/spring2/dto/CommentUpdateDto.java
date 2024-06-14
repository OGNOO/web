package com.itwill.spring2.dto;

import com.itwill.spring2.repository.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateDto {
	private Integer id;
	private String ctext;

	// CommentUpdateDto 타입을 Comment 타입으로 변환해서 리턴.
	public Comment toEntity() {

		return Comment.builder().id(id).ctext(ctext).build();
	}
}
