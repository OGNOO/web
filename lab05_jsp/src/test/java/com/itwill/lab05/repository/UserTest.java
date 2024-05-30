package com.itwill.lab05.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest {
	private static final Logger log = LoggerFactory.getLogger(UserTest.class);
	private UserDao dao = UserDao.INSTANCE;

	@Test
	public void test() {
		User u = User.builder().userId("qqqq").password("qqqdsadas").email("ewqewqeq").build();
		int res = dao.createUser(u);
		log.debug("res = {}", res);
	}
}
