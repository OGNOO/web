package com.itwill.lab05.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.User;
import com.itwill.lab05.repository.UserDao;

public enum UserService {
	INSTANCE;

	private static final Logger log = LoggerFactory.getLogger(PostService.class);
	// Persistence(Repository) 계층의 기능(메서드)들을 사용하기 위해서.
	private final UserDao userDao = UserDao.INSTANCE;

	public int create(User user) {
		log.debug("create(post = {})", user);

		// Repository 계층의 메서드를 사용해서 DB 테이블에 행을 삽입(insert)
		int result = userDao.createUser(user);

		return result; // insert된 행의 개수를 리턴.
	}

	public int select(String userId) {
		int result = userDao.selectUser(userId);
		return result;
	}

	public User singIn(String userid, String password) {
		log.debug("signIn(userid={}, password={})", userid, password);

		// DTO(Data Transfer Object)
		User dto = User.builder().userId(userid).password(password).build();
		User user = userDao.selectByUserIdAndPassword(dto);
		log.debug("로그인 결과 = {}", user);

		return user;
	}

	public User read(String userId) {
		User user = userDao.selectById(userId);
		
		return user;
	}

	public int update(String userId, String email) {
		int res =  userDao.updateById(userId, email);
		
		return res;
	}
}
