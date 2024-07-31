package com.itwill.springboot5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostSearchRequestDto {
	private String category;
	private String keyword;
	private int p; // 검색 결과 목록의 페이지 번호(0부터 시작)
}
