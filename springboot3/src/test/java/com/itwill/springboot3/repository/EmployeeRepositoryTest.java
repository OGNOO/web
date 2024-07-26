package com.itwill.springboot3.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class EmployeeRepositoryTest {

	@Autowired // 의존성 주입
	private EmployeeRepository empRepo;

	// @Test
	@Transactional
	public void testDependencyInjection() {
		// EmployeeRepository 객체를 의존성 주입을 받을 수 있는 지 테스트.
		assertThat(empRepo).isNotNull();
		log.info("empRepo={}", empRepo);
		/*
		 * Repository<T, ID> |__ CrudRepository<T, ID>, PagingAndSortingRepository<T,
		 * ID> |__ JpaRepository<T, ID> |__ EmployeeRepository<T, ID> |__
		 * SimpleJpaRepository<T, ID>
		 */
	}

	// @Test
	@Transactional
	public void testFindAll() {
		// employees 테이블 전체 검색 테스트:
		long count = empRepo.count();
		assertThat(count).isEqualTo(214L);

		List<Employee> list = empRepo.findAll();
		log.info("employees[0]={}", list.get(0));
	}

//	@Test
	@Transactional
	public void testFindById() {
		// 1. empRepo.findById() 메서드 테스트
		// 2. Employees 테이블 - Jobs 테이블의 관계 테스트(JOB_ID - JOB_ID)
		// 3. Employees 테이블 - Employees 테이블의 관계 테스트(MANAGER_ID - Employee_ID)

		// 테이블에 id(사번)가 존재하는 경우:
		Employee emp = empRepo.findById(120).orElseThrow();
		log.info("emp={}", emp);
		log.info("emp.job={}", emp.getJob());
		log.info("emp.manager={}", emp.getManager());
		log.info("emp.department={}", emp.getDepartment());
	}

//	@Test
	@Transactional
	void testFindByDepartmentId() {
		List<Employee> res = empRepo.findByDepartmentId(50);
		res.forEach(i -> System.out.println(i));
	}

//	@Test
	@Transactional
	void testFindByFirstName() {
		List<Employee> res = empRepo.findByFirstName("Diana");
		res.forEach(i -> System.out.println(i));
	}

//	@Test
	@Transactional
	void testFindBySalary() {
//		List<Employee> res = empRepo.findBySalaryGreaterThan(15000.0);
//		List<Employee> res = empRepo.findBySalaryLessThan(1000.0);
		List<Employee> res = empRepo.findBySalaryBetween(10000.0, 15000.0);
		res.forEach(i -> System.out.println(i));
	}

//	@Test
	@Transactional
	void testFindByName() {
//		List<Employee> res = empRepo.findByName("Te", "te");
		List<Employee> res = empRepo.findByName2("Te");
		System.out.println(res);
		res.forEach(i -> System.out.println(i));
	}

//	@Test
	@Transactional
	void testFindByDeptName() {
		List<Employee> res = empRepo.findByDeptName("IT");
		System.out.println(res);
		res.forEach(i -> System.out.println(i));
	}

//	@Test
	@Transactional
	void testFindByDeptCityName() {
		List<Employee> res = empRepo.findByDeptCityName("Roma");
		System.out.println(res);
		res.forEach(i -> System.out.println(i));
	}

//	@Test
	@Transactional
	void testFindByDeptCountryName() {
		List<Employee> res = empRepo.findByDeptCountryName("Canada");
		System.out.println(res);
		res.forEach(i -> System.out.println(i));
	}

}