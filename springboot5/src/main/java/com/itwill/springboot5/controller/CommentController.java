package com.itwill.springboot5.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.springboot5.domain.Comment;
import com.itwill.springboot5.dto.CommentRegisterDto;
import com.itwill.springboot5.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comment")
public class CommentController {

	private final CommentService commentService;

	@PostMapping("")
	public ResponseEntity<Comment> registerComment(@RequestBody CommentRegisterDto commentRegisterDto) {
		log.info("commentRegisterDto={}", commentRegisterDto);
		Comment comment = commentService.create(commentRegisterDto);

		return ResponseEntity.ok(comment);
	}

	@GetMapping("/all/{postId}")
	public ResponseEntity<Page<Comment>> showComment(@PathVariable(name = "postId") Long postId,
			@RequestParam(name = "p", defaultValue = "0") Integer pageNo) {
		Page<Comment> res = commentService.readCommentList(postId, pageNo);

		return ResponseEntity.ok(res);
	}

	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<Integer> deleteComment(@PathVariable(name = "commentId") Long commentId) {
		// Comment 삭제 로직 구현
		log.info("\ncid={}", commentId);
		commentService.deleteComment(commentId);

		return ResponseEntity.ok(1);
	}

	@PutMapping("/update/{cid}")
	public ResponseEntity<Integer> updateComment(@PathVariable(name = "cid") Long commentId, @RequestParam(name = "ctext") String ctext) {
		log.info("\ncid={}\nctext={}", commentId, ctext);
		commentService.updateComment(commentId, ctext);
		
		return ResponseEntity.ok(1);
	}
}
