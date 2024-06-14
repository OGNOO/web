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
public class CommentCreateDto {
	private Integer postId;
	private String userName;
	private String ctext;

	public Comment toEntity() {
		return Comment.builder().postId(postId).ctext(ctext).userName(userName).build();
	}
}
