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
public class PostDaoTest {

	@Autowired
	private PostDao postDao;

	@Test
	public void testSelectAll() {
//		Assertions.assertNotNull(postDao);
		log.debug("testSelectAll()");

		List<Post> list = postDao.selectOrderByIdDesc();
		for (Post p : list) {

			log.debug(p.toString());
		}
	}

//	@Test
	public void testSelectId() {
		Post p = postDao.selectByIdDesc(62);
		Assertions.assertNotNull(p);
		log.debug(p.toString());

		Post p1 = postDao.selectByIdDesc(2);
		Assertions.assertNull(p1);
	}

//	@Test
	public void testInsertPost() {
		Post p = Post.builder().title("xptmxm1").author("테스트1").content("내용1").build();
		int res = postDao.insertPost(p);

		Assertions.assertEquals(1, res);
	}

//	@Test
	public void testDeleteById() {
		int res = postDao.deleteById(62);
		Assertions.assertEquals(1, res);
	}

//	@Test
	public void testUpdateByAuthor() {
		Post p = Post.builder().title("xptmxm3").author("테스트").content("내용3").build();
		int res = postDao.updateById(p);

		Assertions.assertEquals(2, res);
	}
}
