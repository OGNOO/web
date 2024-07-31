package com.itwill.springboot5.dto;

import java.time.LocalDateTime;

import com.itwill.springboot5.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostListItemDto {
	private Long id;
	private String title;
	private String author;
	private LocalDateTime modifiedTime;

	public static PostListItemDto fromEntity(Post post) {

		return PostListItemDto.builder().id(post.getId()).title(post.getTitle()).author(post.getAuthor())
				.modifiedTime(post.getModifiedTime()).build();
	}
}
