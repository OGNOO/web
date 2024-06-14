package com.itwill.spring2.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.dto.PostListAjaxDto;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostRestController {

	private final PostService postService;

	@GetMapping("/post/search")
	@ResponseBody
	public List<PostListAjaxDto> search(@ModelAttribute PostSearchDto postSearchDto) {
		log.debug(postSearchDto.toString());
		List<PostListAjaxDto> list = postService.search(postSearchDto);

		return list;
	}
}
