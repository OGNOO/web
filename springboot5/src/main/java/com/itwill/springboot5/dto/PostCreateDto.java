package com.itwill.springboot5.dto;

import com.itwill.springboot5.domain.Post;

import lombok.Data;

@Data
public class PostCreateDto {
	private String author;
	private String title;
	private String content;

	public Post toEntity() {
		return Post.builder().author(author).title(title).content(content).build();
	}
}
