package com.itwill.spring2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.dto.PostListAjaxDto;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.dto.PostSortDto;
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
	public List<PostListAjaxDto> search(@ModelAttribute PostSearchDto postSearchDto) {
		log.debug(postSearchDto.toString());
		List<PostListAjaxDto> list = postService.search(postSearchDto);
		session.setAttribute("category", postSearchDto.getCategory());
		session.setAttribute("keyword", postSearchDto.getKeyword());

		return list;
	}

	@GetMapping("/sort")
	public List<PostListAjaxDto> sort(@ModelAttribute PostSortDto postSortDto) {
		String keywordCategorySession = (String) session.getAttribute("category");
		String keywordSession = (String) session.getAttribute("keyword");
		System.out.println(keywordCategorySession);
		System.out.println(keywordSession);
		if (keywordSession == null)
			keywordSession = "";
		postSortDto.setKeywordCategory(keywordCategorySession);
		postSortDto.setKeyword(keywordSession);

		log.debug(postSortDto.toString());
		List<PostListAjaxDto> list = postService.sort(postSortDto);
		for (PostListAjaxDto p : list) {
			log.debug(p.toString());
		}
		
		return list;
	}

}
