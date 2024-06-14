package com.itwill.spring2.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.dto.CommentItemDto;
import com.itwill.spring2.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
	private final CommentService commentService;

	@GetMapping("/all/{pid}")
	@ResponseBody
	public List<CommentItemDto> read(@PathVariable Integer pid) {
		log.debug("read() = {}", pid);
		List<CommentItemDto> list = commentService.readByPostId(pid);

		return list;
	}
}
