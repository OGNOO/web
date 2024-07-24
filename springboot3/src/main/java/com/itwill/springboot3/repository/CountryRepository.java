package com.itwill.springboot3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.springboot3.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

}
