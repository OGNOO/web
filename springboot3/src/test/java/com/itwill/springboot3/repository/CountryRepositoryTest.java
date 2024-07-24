package com.itwill.springboot3.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Country;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CountryRepositoryTest {
	@Autowired
	private CountryRepository countryRepository;

	@Test
	@Transactional
	public void testFindAll() {
		List<Country> list = countryRepository.findAll();
		log.info(list.toString());
	}

	@Test
	@Transactional
	public void testFindById() {
		Country country = countryRepository.findById("AR").orElseThrow();
		log.info("country={}", country);
		log.info("country.region={}", country.getRegion());
	}
}
