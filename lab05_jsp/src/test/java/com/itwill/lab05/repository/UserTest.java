package com.itwill.lab05.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest {
	private static final Logger log = LoggerFactory.getLogger(UserTest.class);
	private UserDao dao = UserDao.INSTANCE;

//	@Test
	public void signUptest() {
		User u = User.builder().userId("qqqq").password("qqqdsadas").email("ewqewqeq").build();
		int res = dao.createUser(u);
		log.debug("res = {}", res);
	}
	
	@Test
	public void signInTest() {
		// userId 와 password 가 일치하는 경우
		User u = User.builder().userId("qqqq").password("qqqdsadas").build();
		User res = dao.selectByUserIdAndPassword(u);
		
		Assertions.assertNotNull(res);
		log.debug("res = {}", res.toString());
	}
}
