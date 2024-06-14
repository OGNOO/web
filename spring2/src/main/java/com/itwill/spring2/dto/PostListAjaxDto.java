package com.itwill.spring2.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itwill.spring2.repository.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostListAjaxDto {
	private Integer id;
	private String title;
	private String author;
	private String modifiedTime;

	public static PostListAjaxDto fromEntity(Post post) {
		LocalDateTime postMT = post.getModifiedTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedMT = postMT.format(formatter);

		return PostListAjaxDto.builder().id(post.getId()).title(post.getTitle()).author(post.getAuthor())
				.modifiedTime(formattedMT).build();
	}
}
