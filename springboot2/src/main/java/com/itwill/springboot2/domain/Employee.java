package com.itwill.springboot2.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// ORM(Object Relation Mapping) -> JPA(Java Persistence API) -> Hibernate
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity // 데이터베이스 테이블과 매핑하는 자바 객체
@Table(name = "EMP")
public class Employee {
  @Id
  @Column(name = "EMPNO")
  private Integer id;

  @Column
  private String ename;

  @Column
  private String job;

  @ToString.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "MGR")
  private Employee manager;

  @Column
  private LocalDateTime hiredate;

  @Column(name = "SAL")
  private Double salary;

  @Column(name = "COMM")
  private Double commission;

  @ToString.Exclude // ToString 메서드에서 출력 문자열에서 제외
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DEPTNO")
  private Department department;

}
