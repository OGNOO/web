package com.itwill.springboot5.dto;

import com.itwill.springboot5.domain.Post;

import lombok.Data;

@Data
public class PostUpdateDto {
	private Long id;
	private String title;
	private String content;

	public Post toEntity() {
		return Post.builder().id(id)
				.author("author").title(title).content(content).build();
	}
}
