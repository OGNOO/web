package com.itwill.spring2.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleDriver;

@Slf4j // log4j-slf4j2 라이브러리를 이용한 로그 출력
@ExtendWith(SpringExtension.class) // 스프링 JUnit 테스트를 실행하는 메인 클래스.
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
//스프링 컨텍스트(환경 변수) 파일(들)의 경로(이름).

public class JdbcTest {

	@Test // 테스트 메서드 - 단위 테스트에서 실행(호출)할 메서드
	public void testOracleJdbc() throws SQLException {
		// JDBC 1. OJBC 라이브러리를 드라이버 매니저에 등록
		DriverManager.registerDriver(new OracleDriver());
		log.debug("testOracleJdbc()");

		final String url = "jdbc:oracle:thin:@localhost:1521:xe";
		final String user = "jspstudy";
		final String pwd = "jspstudy";

		Connection conn = DriverManager.getConnection(url, user, pwd);
		Assertions.assertNotNull(conn);
		log.debug("conn = {}", conn);
		
		conn.close();
		log.debug("오라클 연결 해제 성공");
	}
	
}
