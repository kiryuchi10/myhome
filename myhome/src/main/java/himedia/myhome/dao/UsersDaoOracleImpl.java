package himedia.myhome.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import himedia.myhome.vo.UserVo;

public class UsersDaoOracleImpl implements UsersDao {
	private String dbuser;
	private String dbpass;

	public UsersDaoOracleImpl(String dbuser, String dbpass) {
		this.dbuser = dbuser;
		this.dbpass = dbpass;
	}

	private Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
            conn = DriverManager.getConnection(dburl, dbuser, dbpass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
	}

	@Override
	public List<UserVo> getList() {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<UserVo> list = new ArrayList<>();

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT no, name, password,email,gender, created_at FROM users ORDER BY created_at DESC";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				Date created_at = rs.getDate("created_at");
				UserVo vo = new UserVo(no, name, password, email, gender, created_at);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public boolean insert(UserVo userVo) {
		int insertCount = 0;
		String sql = "INSERT INTO users (no, name, password, email, gender) " +
				"VALUES(seq_users_pk.nextval, ?, ?, ?, ?)";
		try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getPassword());
			pstmt.setString(3, userVo.getEmail());
			pstmt.setString(4, userVo.getGender());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("연결 에러: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("에러: " + e.getMessage());
		}
		return insertCount == 1;
	}

	@Override
	public boolean update(UserVo userVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updatedCount = 0;

		try {
			conn = getConnection();
			String sql = "UPDATE users SET name=?, password=?, email=?, gender=? WHERE no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getPassword());
			pstmt.setString(3, userVo.getEmail());
			pstmt.setString(4, userVo.getGender());
			pstmt.setLong(5, userVo.getNo());
			updatedCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return updatedCount == 1;
	}

	@Override
	public boolean delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;

		try {
			conn = getConnection();
			String sql = "DELETE FROM users WHERE no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			deletedCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deletedCount == 1;
	}

	@Override
	public UserVo getUserByIDAndPassword(String email, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo user = null;

		try {
			conn = getConnection();
			String sql = "SELECT no, name, password, email, gender FROM users WHERE email=? AND password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String gender = rs.getString("gender");

				user = new UserVo(no, name, password, email, gender);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}
}
