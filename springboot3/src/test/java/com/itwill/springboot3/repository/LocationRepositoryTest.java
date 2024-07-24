package com.itwill.springboot3.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Location;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class LocationRepositoryTest {
	@Autowired
	private LocationRepository locationRepository;

	@Test
	@Transactional
	public void testFindAll() {
		List<Location> list = locationRepository.findAll();
		log.info(list.toString());
	}

	@Test
	@Transactional
	public void testFindById() {
		Location location = locationRepository.findById(1000).orElseThrow();
		log.info("location={}", location);
		log.info("location.country={}", location.getCountry());
	}

}
