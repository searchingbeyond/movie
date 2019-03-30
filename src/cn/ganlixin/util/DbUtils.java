package cn.ganlixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 封装的数据库操作工具类
 * @author ganlixin
 *
 */
public class DbUtils {

	/**
	 * 数据库驱动类名称
	 */
	private static String driverClassName;
	
	/**
	 * 数据库连接的url
	 */
	private static String url;
	
	/**
	 * 连接数据库使用的用户名
	 */
	private static String username;
	
	/**
	 * 连接数据库用户名对应的密码
	 */
	private static String password;
	
	/**
	 * 加载数据库配置文件，并建立数据库连接池内的连接
	 */
	static {
		InputStream _is = null;
		try {
			Properties props = new Properties();
			_is = DbUtils.class.getClassLoader().getResourceAsStream("cn/ganlixin/config/db.properties");
			props.load(_is);
			
			driverClassName = props.getProperty("jdbc.driverClassName");
			url = props.getProperty("jdbc.url");
			username = props.getProperty("jdbc.username");
			password = props.getProperty("jdbc.password");
			
			Class.forName(driverClassName);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (_is != null) {
					_is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取一个数据库连接
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 释放数据库连接到连接池中
	 */
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 释放连接
	 * @param conn
	 */
	public static void free(Connection conn) {
		closeConnection(conn);
	}
	
	/**
	 * 释放数据库连接到连接池，并释放预处理对象
	 * @param conn
	 * @param pstmt
	 */
	public static void free(Connection conn, PreparedStatement pstmt) {
		
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		closeConnection(conn);
	}
	
	/**
	 * 首先释放结果集，然后释放预处理对象，最后释放数据库连接到连接池
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public static void free(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		free(conn, pstmt);
	}
	
	public static void free(PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
