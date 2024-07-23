package com.itwill.springboot2.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity // 데이터베이스 테이블과 매핑하는 자바 객체
@Table(name = "DEPT")
public class Department {
  @Id
  @Column(name = "DEPTNO")
  private Integer id;

  @Column(name = "DNAME")
  private String departmentName;

  @Column(name = "LOC")
  private String location;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
  @ToString.Exclude
  // mappedBy: Employee 엔티티에서 @ManyToOne 애너테이션이 설정된 필드 이름.
  private List<Employee> Employee;
}
