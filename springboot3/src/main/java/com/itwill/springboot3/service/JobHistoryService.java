package com.itwill.springboot3.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.JobHistory;
import com.itwill.springboot3.dto.JobHistoryDto;
import com.itwill.springboot3.repository.JobHistoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobHistoryService {

	private final JobHistoryRepository jobHistoryRepository;

	@Transactional
	public List<JobHistory> jobHistoryList() {
		log.info("로그목록 서비스");
		Optional<List<JobHistory>> optionalJobHistoryList = Optional.ofNullable(jobHistoryRepository.findAll());
		List<JobHistory> jobHistoryList;
		if (optionalJobHistoryList.isPresent()) {
			jobHistoryList = optionalJobHistoryList.get();
		} else {
			jobHistoryList = Collections.emptyList();
		}

		return jobHistoryList;
	}

	@Transactional
	public List<JobHistoryDto> jobHistoryDtoList() {
		log.info("인터페이스 프로젝션을 사용한 로그목록 서비스");
		Optional<List<JobHistoryDto>> optionalJobHistoryDtoList = Optional
				.ofNullable(jobHistoryRepository.findAllJobHistoryDtos());
		List<JobHistoryDto> jobHistoryDtos;
		if (optionalJobHistoryDtoList.isPresent()) {
			jobHistoryDtos = optionalJobHistoryDtoList.get();
		} else {
			jobHistoryDtos = Collections.emptyList();
		}
		
		return jobHistoryDtos;
	}
}
