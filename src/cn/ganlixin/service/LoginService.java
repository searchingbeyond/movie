package cn.ganlixin.service;

import cn.ganlixin.pojo.User;

/**
 * 定义与登录验证相关的接口
 * @author ganlixin
 */
public interface LoginService {
	
	/**
	 * 检测用户的身份是否合法（注意，此时检测用户的密码是已经加密过得）
	 * @param user
	 * @return
	 */
	public User checkUser(User user);
	
	/**
	 * 检测用户登录（注意此时密码并没有加密）
	 * @param user
	 * @return
	 */
	public User checkLogin(User user);
	
}
