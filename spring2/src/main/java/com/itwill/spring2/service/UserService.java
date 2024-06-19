package com.itwill.spring2.service;

import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.UserCreateDto;
import com.itwill.spring2.repository.User;
import com.itwill.spring2.repository.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
	private final UserDao userDao;

	public boolean checkUserId(String userid) {
		log.debug(userid);
		User user = userDao.selectByUserId(userid);

		if (user == null) {

			return true;
		} else {

			return false;
		}
	}

	public int insert(UserCreateDto userCreateDto) {
		System.out.println(userCreateDto);
		
		int res = userDao.insert(userCreateDto.toEntity());
		return res;
	}
}