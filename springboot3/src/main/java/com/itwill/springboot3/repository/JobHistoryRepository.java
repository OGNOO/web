package com.itwill.springboot3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.springboot3.domain.JobHistory;
import com.itwill.springboot3.domain.JobHistoryId;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, JobHistoryId> {

}
