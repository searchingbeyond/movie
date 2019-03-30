package cn.ganlixin.service.impl;

import org.apache.commons.codec.digest.DigestUtils;

import cn.ganlixin.dao.UserDao;
import cn.ganlixin.dao.impl.UserDaoImpl;
import cn.ganlixin.pojo.User;
import cn.ganlixin.service.LoginService;

/**
 * 登录验证相关的接口实现
 * @author ganlixin
 *
 */
public class LoginServiceImpl implements LoginService {
	
	private UserDao userDao = new UserDaoImpl();

	@Override
	public User checkUser(User user) {
		if ((user == null) || (user.getUsername() == null) || (user.getPassword() == null)) {
			return null;
		}
		
		return userDao.existsUser(user);
	}

	@Override
	public User checkLogin(User user) {
		String encryptedPassword = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(encryptedPassword);
		
		return checkUser(user);
	}

}
