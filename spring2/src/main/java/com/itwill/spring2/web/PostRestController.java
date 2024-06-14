package com.itwill.spring2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.dto.PostListAjaxDto;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.service.PostService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostRestController {
	private final PostService postService;

	@Autowired
	private HttpSession session;
	
	@GetMapping("/search")
	@ResponseBody
	public List<PostListAjaxDto> search(@ModelAttribute PostSearchDto postSearchDto) {
//		System.out.println(session.getAttribute("keyword"));
		log.debug(postSearchDto.toString());
		List<PostListAjaxDto> list = postService.search(postSearchDto);
		session.setAttribute("keyword", postSearchDto.getKeyword());

		return list;
	}
}
