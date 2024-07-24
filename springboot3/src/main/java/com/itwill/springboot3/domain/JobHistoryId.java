package com.itwill.springboot3.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class JobHistoryId implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer employeeId;
	private LocalDateTime startDate;

	// equals() 메소드 구현
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		JobHistoryId that = (JobHistoryId) o;
		return Objects.equals(employeeId, that.employeeId) && Objects.equals(startDate, that.startDate);
	}

	// hashCode() 메소드 구현
	@Override
	public int hashCode() {
		return Objects.hash(employeeId, startDate);
	}
}
