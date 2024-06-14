package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class CommentDaoTest {

	@Autowired
	private CommentDao commentDao;

//	@Test
	public void testSelectAll() {
		Assertions.assertNotNull(commentDao);
		log.debug("testSelectAll()");

		List<Comment> list = commentDao.selectByPostId(125);
		for (Comment c : list) {

			log.debug(c.toString());
		}
	}

//	@Test
	public void testInsert() {
		Comment c = Comment.builder().postId(124).userName("rewq").ctext("테스트").build();
		int res = commentDao.insertComment(c);
		System.out.println(res);
	}

//	@Test
	public void testUpdateComment() {
		Comment c = Comment.builder().id(2).ctext("rewq").build();
		int res = commentDao.updateComment(c);
		System.out.println(res);
	}

//	@Test
	public void testDeleteByIdComment() {
		int res = commentDao.deleteByIdComment(2);
		System.out.println(res);
	}

//	@Test
	public void testDeleteByPostIdComment() {
		int res = commentDao.deleteByPostIdComment(125);
		System.out.println(res);
	}

//	@Test
	public void testSelectCommentCount() {
		int res = commentDao.selectCommentCount(124);

		System.out.println(res);
	}

	@Test
	public void testSelectById() {
		Comment comment = commentDao.selectById(6);
		Assertions.assertNotNull(comment);
		System.out.println(comment);
	}
}
