package com.itwill.springboot3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.itwill.springboot3.domain.JobHistory;
import com.itwill.springboot3.domain.JobHistoryId;
import com.itwill.springboot3.dto.JobHistoryDto;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, JobHistoryId> {

	 @Query(value = "SELECT jh.EMPLOYEE_ID AS employeeId, "
        + "COALESCE(e.FIRST_NAME, '') || ' ' || COALESCE(e.LAST_NAME, '') AS fullName, "
        + "jh.START_DATE AS startDate, "
        + "jh.END_DATE AS endDate, "
        + "j.JOB_TITLE AS jobTitle, "
        + "d.DEPARTMENT_NAME AS departmentName "
        + "FROM JOB_HISTORY jh "
        + "LEFT JOIN EMPLOYEES e ON e.EMPLOYEE_ID = jh.EMPLOYEE_ID "
        + "LEFT JOIN JOBS j ON j.JOB_ID = jh.JOB_ID "
        + "LEFT JOIN DEPARTMENTS d ON d.DEPARTMENT_ID = jh.DEPARTMENT_ID",
        nativeQuery = true)
	    List<JobHistoryDto> findAllJobHistoryDtos();
}
