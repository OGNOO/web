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

	private static final String SQL_SIGN_IN = "SELECT * FROM users WHERE userid = ? and password = ?";

	/**
	 * 로그인 할 때 필요한 메서드.
	 * 
	 * @param user 로그인을 시도한 userId, password 를 저장한 객체.
	 * @return 데이터베이스의 users 테이블에서 userId 와 password 가 일치하는 레코드가 있으면 null 이 아닌 User
	 *         타입 객체를 리턴. userId 또는 password 가 일치하지 않으면 null 을 리턴.
	 */
	public User selectByUserIdAndPassword(User user) {
		log.debug("selectByUserIdAndPassword({})", user);

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		User result = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SIGN_IN);
			stmt.setString(1, user.getUserId());
			stmt.setString(2, user.getPassword());
			rs = stmt.executeQuery();
			if (rs.next()) {
				result = fromResultSetToUser(rs);
			}
			log.debug("result = {}", result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}

		return result;
	}

	private static final String SQL_SELECT_BY_ID = "SELECT * FROM users WHERE userid = ?";

	public User selectById(String userId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		User result = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setString(1, userId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				result = fromResultSetToUser(rs);
			}
			log.debug("result = {}", result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		return result;
	}

	private static final String SQL_POINTS_UP = "Update users set points = points + 10 where userid = ?";

	public int pointsUp(String userId) {
		Connection conn = null;
		PreparedStatement stmt = null;

		int result = 0;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_POINTS_UP);
			stmt.setString(1, userId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}

		return result;
	}

	private static final String SQL_UPDATE_EMAIL = "Update users set email = ? where userid = ?";

	public int updateById(String userId, String email) {
		Connection conn = null;
		PreparedStatement stmt = null;

		int result = 0;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_EMAIL);
			stmt.setString(1, email);
			stmt.setString(2, userId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}

		return result;
	}

	private User fromResultSetToUser(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String userid = rs.getString("userid");
		String password = rs.getString("password");
		String email = rs.getString("email");
		int points = rs.getInt("points");

		User user = User.builder().id(id).userId(userid).password(password).email(email).points(points).build();

		return user;
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
