package com.itwill.spring2.dto;

import com.itwill.spring2.repository.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateDto {

	private String title;
	private String content;
	private String author;

	public Post toEntity(PostCreateDto postCreateDto) {
		return Post.builder().title(postCreateDto.getTitle()).author(postCreateDto.getAuthor())
				.content(postCreateDto.getContent()).build();
	}
}
