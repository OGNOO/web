package com.itwill.springboot5.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot5.domain.Comment;
import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.CommentRegisterDto;
import com.itwill.springboot5.repository.CommentRepository;
import com.itwill.springboot5.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;

	public Comment create(CommentRegisterDto commentRegisterDto) {
		log.info("{}", commentRegisterDto);
		Post post;
		Optional<Post> optionalPost = postRepository.findById(commentRegisterDto.getPostId());
		if (optionalPost.isPresent()) {
			post = optionalPost.get();
		} else {
			return new Comment();
		}

		Comment comment = Comment.builder().post(post).ctext(commentRegisterDto.getCtext())
				.writer(commentRegisterDto.getWriter()).build();

		// DB에 저장 (insert 쿼리 실행)
		Comment c = commentRepository.save(comment);
		System.out.println("\n c===" + c + "\n");

		return comment;
	}

	@Transactional(readOnly = true)
	public Page<Comment> readCommentList(Long postId, int pageNo) {
		log.info("readCommentList [postId = {}], [pageNo = {}]", postId, pageNo);
		Post post = postRepository.findById(postId).orElseThrow();

		Pageable pageable = PageRequest.of(pageNo, 5, Sort.by("modifiedTime").descending());

		Page<Comment> data = commentRepository.findByPost(post, pageable);
		log.info("data.getNumber() = {}", data.getNumber());
		log.info("data.getTotalPages() = {}", data.getTotalPages());

		return data;
	}

	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
	}

	public void updateComment(Long commentId, String ctext) {
		Comment comment = commentRepository.findById(commentId).orElseThrow();
		System.out.println(comment);
		Comment updateComment = Comment.builder().id(comment.getId()).post(comment.getPost()).ctext(ctext).writer("NN")
				.build();
		commentRepository.save(updateComment);
	}

}
