package com.itwill.springboot5.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot5.domain.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CommentRepositoryTest {
	@Autowired
	private CommentRepository commentRepository;

	@Test
	void testFindAll() {
		assertThat(commentRepository).isNotNull();
		log.debug("findAll() 테스트");
		List<Comment> comments = commentRepository.findAll();
		comments.forEach(i -> System.out.println(i));
	}
}
