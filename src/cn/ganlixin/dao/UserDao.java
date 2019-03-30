package cn.ganlixin.dao;

import java.sql.ResultSet;

import cn.ganlixin.pojo.User;

public interface UserDao {
	
	/**
	 * 判断用户表中是否存在该用户
	 * @param user
	 * @return
	 */
	public boolean existsUsername(String username);
	
	/**
	 * 检测用户是否合法， 如果合法，就返回用户信息
	 * @param user
	 * @return
	 */
	public User existsUser(User user);
}
