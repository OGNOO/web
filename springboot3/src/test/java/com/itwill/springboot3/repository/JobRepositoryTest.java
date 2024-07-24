package com.itwill.springboot3.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Job;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class JobRepositoryTest {

	@Autowired
	private JobRepository jobRepository;

	@Test
	@Transactional
	public void testFindAll() {
		List<Job> list = jobRepository.findAll();
		log.info(list.toString());
	}
}
