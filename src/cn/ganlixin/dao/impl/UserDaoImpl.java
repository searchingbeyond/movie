package cn.ganlixin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.ganlixin.dao.UserDao;
import cn.ganlixin.pojo.User;
import cn.ganlixin.util.DbUtils;

public class UserDaoImpl implements UserDao {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@Override
	public boolean existsUsername(String username) {
		
		conn = DbUtils.getConnection();
		try {
			pstmt = conn.prepareStatement("select username from user where username=?");
			
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.free(conn, pstmt, rs);
		}
		
		return false;
	}

	@Override
	public User existsUser(User user) {
		conn = DbUtils.getConnection();
		try {
			pstmt = conn.prepareStatement("select username, password, nickname from user where username=? and password=?");
			
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			
			rs = pstmt.executeQuery();
			
			User u = new User();
			
			if (rs.next()) {
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setNickname(rs.getString("nickname"));
				return u;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.free(conn, pstmt, rs);
		}
		
		return null;
	}
}
