package com.itwill.spring2.dto;

import java.time.LocalDateTime;

import com.itwill.spring2.repository.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentItemDto {
	private Integer id;
	private String userName;
	private String ctext;
	private LocalDateTime modifiedTime;

	public static CommentItemDto fromEntity(Comment comment) {

		return CommentItemDto.builder().id(comment.getId()).userName(comment.getUserName()).ctext(comment.getCtext())
				.modifiedTime(comment.getModifiedTime()).build();
	}

}
