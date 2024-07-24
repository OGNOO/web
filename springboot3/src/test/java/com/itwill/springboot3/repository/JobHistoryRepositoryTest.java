package com.itwill.springboot3.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.JobHistory;
import com.itwill.springboot3.domain.JobHistoryId;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class JobHistoryRepositoryTest {

	@Autowired
	private JobHistoryRepository jobHistoryRepository;

	@Test
	@Transactional
	public void testFindAll() {
		List<JobHistory> list = jobHistoryRepository.findAll();
		log.info(list.toString());
	}

	@Test
	@Transactional
	public void testFindById() {
		String dateStr = "1997-09-21 00:00:00.000";

		// DateTimeFormatter 정의
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

		// 문자열을 LocalDateTime으로 변환
		LocalDateTime testDateTime = LocalDateTime.parse(dateStr, formatter);
		JobHistory jobHistory = jobHistoryRepository
				.findById(JobHistoryId.builder().employeeId(101).startDate(testDateTime).build()).orElseThrow();
		log.info("jobHistory={}", jobHistory);
		log.info("jobHistory.job={}", jobHistory.getJob());
		log.info("jobHistory.department={}", jobHistory.getDepartment());
	}
}
