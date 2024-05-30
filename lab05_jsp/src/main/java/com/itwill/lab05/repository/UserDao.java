package com.itwill.lab05.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.datasource.DataSourceUtil;
import com.zaxxer.hikari.HikariDataSource;

public enum UserDao {
	INSTANCE;

	private static final Logger log = LoggerFactory.getLogger(UserDao.class);
	private final HikariDataSource ds = DataSourceUtil.getInstance().getDataSource();

	private static final String SQL_INSERT = "insert into users (userid, password, email) values (?, ?, ?)";

	public int createUser(User user) {
		log.debug("createUser(): {}", user);
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			int i = 1;
			stmt.setString(i++, user.getUserId());
			stmt.setString(i++, user.getPassword());
			stmt.setString(i++, user.getEmail());

			result = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}

		return result;
	}

	private static final String SQL_SELECT_BY_USERID = "SELECT COUNT(*) FROM users WHERE userid = ?";

	public int selectUser(String userId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_USERID);
			stmt.setString(1, userId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt("count(*)");
			}
			log.debug("result = {}", result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		return result;
	}

	private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
		// DB 자원들을 해제하는 순선: 생성된 순서의 반대로. rs => stmt => conn
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void closeResources(Connection conn, Statement stmt) {
		closeResources(conn, stmt, null);
	}

}
