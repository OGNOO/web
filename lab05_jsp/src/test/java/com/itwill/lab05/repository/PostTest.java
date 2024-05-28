package com.itwill.lab05.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostTest {
	private static final Logger log = LoggerFactory.getLogger(PostTest.class);
	private PostDao dao = PostDao.INSTANCE;

	// JUnit 모듈에서 단위 테스트를 하기 위해서 호출하는 메서드.
	// (1) public void. (2) 아규먼트를 갖지 않음.
//	@Test
	public void test() {
		// Post 타입 객체 생성 - Builder 디자인 패턴.
		Post p = Post.builder().title("테스트").author("관리자").content("builder design pattern").id(1).build();
		// assertNotNull(arg): arg 가 null 이 아니면 JUnit 테스트 성공, null 이면 테스트 실패.
		// assertNull(arg): arg 가 null 이면 단위 테스트 성공, null 이 아니면 테스트 실패.
		Assertions.assertNotNull(p);

		log.debug("p = {}", p);
		Post p1 = new Post(10, "aaa", "aaa", "aaa", null, null);
		log.debug("p1 = {}", p1);
	}

//	@Test
	public void testSelect() {
		Assertions.assertNotNull(dao); // PostDao 타입 객체가 null 이 아니면 단위 테스트 성공.
//		log.debug("dao = {}", dao);
		List<Post> result = dao.select();
//		Assertions.assertEquals(5, result.size());

		for (Post p : result) {
			log.debug(p.toString());
		}
	}

//	@Test
	public void testSelectById() {
		Post res = dao.select(2);
		if (res != null) {
			log.debug(res.toString());
		} else {
			log.debug("null");
		}
	}

//	@Test
	public void testInsert() {
		// TODO: PostDao.insert 메서드 단위 테스트
		Post p = Post.builder().title("12가").content("12나").author("12다").build();
		int res = dao.insert(p);
		Assertions.assertEquals(1, res); // PostDao 의 insert 메서드 호출.
		log.debug("res = {}", res);
		// -> insert 메서드의 리턴 값 (삽입된 행의 개수)가 1이면 단위 테스트 성공.
		testSelect();
	}

//	@Test
	public void testDelete() {
		// TODO : PostDao.delete 메서드 단위 테스트
		int res = dao.delete(2);
		Assertions.assertEquals(1, res);
		log.debug("res = {}", res);
		testSelect();
	}

	@Test
	public void testUpdateById() {
		int res = dao.updateById("qqq", "www", "eee", 202);
//		Assertions.assertEquals(1, res);
		if (res != 0) {
			log.debug("res = {}", res);
		} else {
			log.debug("업데이트 id 없음");
		}
		testSelect();

	}
}
