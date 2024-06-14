package com.itwill.spring2.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.CommentCreateDto;
import com.itwill.spring2.dto.CommentItemDto;
import com.itwill.spring2.dto.CommentUpdateDto;
import com.itwill.spring2.repository.Comment;
import com.itwill.spring2.repository.CommentDao;
import com.itwill.spring2.repository.PostDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentDao commentDao; // 생성자에 의한 의존성 주입
	private final PostDao postDao;

	public List<CommentItemDto> readByPostId(Integer id) {
		log.debug("readByPostId()");
		// 레포지토리(영속성) 계층의 메서드를 호출해서 comments 테이블의 데이터를 검색.
		List<Comment> list = commentDao.selectByPostId(id);

//		List<CommentItemDto> res = new ArrayList<>();
//		for (Comment c : list) {
//			CommentItemDto commentItemDto = CommentItemDto.fromEntity(c);
//			res.add(commentItemDto);
//		}
//		return res;

		if (!list.isEmpty()) {
			// List<Comment>를 List<CommentItemDto>로 변환해서 리턴.
			return list.stream().map(CommentItemDto::fromEntity).toList();
		} else {

			return Collections.emptyList();
		}
	}

	public int create(CommentCreateDto commentCreateDto) {
		// 유효성 검사
		if (commentCreateDto.getPostId() == 0 || commentCreateDto.getUserName().isBlank()
				|| commentCreateDto.getCtext().isBlank()) {

			return -1;
		}
		int res = commentDao.insertComment(commentCreateDto.toEntity());

		return res;
	}

	public int update(CommentUpdateDto commentUpdateDto) {
		if (commentUpdateDto.getId() == 0 || commentUpdateDto.getCtext().isBlank()) {

			return -1;
		}
		int res = commentDao.updateComment(commentUpdateDto.toEntity());

		return res;
	}

	public int delete(Integer id) {
		int res = commentDao.deleteByIdComment(id);

		return res;
	}

	public int deleteAll(Integer postId) {
		int res = commentDao.deleteByPostIdComment(postId);

		return res;
	}

}
