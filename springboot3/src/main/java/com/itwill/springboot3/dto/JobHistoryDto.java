package com.itwill.springboot3.dto;

import java.time.LocalDate;

public interface JobHistoryDto {

	Integer getEmployeeId();

	String getFullName();

	LocalDate getStartDate();

	LocalDate getEndDate();

	String getJobTitle();

	String getDepartmentName();

}
