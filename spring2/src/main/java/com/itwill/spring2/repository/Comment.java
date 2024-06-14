package com.itwill.spring2.repository;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// DB 테이블 comments 의 모델(Model)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	private Integer id; // PK
	private Integer postId; // FK: posts 테이블의 id 컬럼을 참조.
	private String userName; // 댓글 작성자 아이디
	private String ctext; // 댓글 내용
	private LocalDateTime createdTime; // 댓글 작성시간
	private LocalDateTime modifiedTime; // 댓글 수정시간
	
}
