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
public class PostUpdateDto {
	private Integer id;
	private String title;
	private String content;
	private String author;

	public static PostUpdateDto fromEntity(Post post) {

		return PostUpdateDto.builder().id(post.getId()).title(post.getTitle()).content(post.getContent())
				.author(post.getAuthor()).build();
	}

	public Post toEntity(PostUpdateDto postUpdateDto) {
		return Post.builder().id(postUpdateDto.getId()).title(postUpdateDto.getTitle())
				.content(postUpdateDto.getContent()).author(postUpdateDto.getAuthor()).build();
	}
}
