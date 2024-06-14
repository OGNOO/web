package com.itwill.spring2.dto;

import java.time.LocalDateTime;

import com.itwill.spring2.repository.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailsDto {
	private Integer id;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;

	public static PostDetailsDto fromEntity(Post post) {

		return PostDetailsDto.builder().id(post.getId()).title(post.getTitle()).content(post.getContent())
				.author(post.getAuthor()).createdTime(post.getCreatedTime()).modifiedTime(post.getModifiedTime())
				.build();
	}

	public Post toEntity(PostDetailsDto postDetailsDto) {
		return Post.builder().id(postDetailsDto.getId()).title(postDetailsDto.getTitle())
				.content(postDetailsDto.getContent()).author(postDetailsDto.getAuthor())
				.createdTime(postDetailsDto.getCreatedTime()).modifiedTime(postDetailsDto.getModifiedTime()).build();
	}
}
