package cn.ganlixin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ganlixin.pojo.Message;
import cn.ganlixin.pojo.User;
import cn.ganlixin.service.LoginService;
import cn.ganlixin.service.impl.LoginServiceImpl;
import cn.ganlixin.util.ResponseUtils;
import cn.ganlixin.util.Status;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private LoginService loginService = new LoginServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if (username == null || password == null) {
			ResponseUtils.responseJSON(resp, new Message(Status.UNAUTHORIZED, "用户名或密码不能为空"));
			return;
		}
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user = loginService.checkLogin(user);
		
		if (user == null) {
			ResponseUtils.responseJSON(resp, new Message(Status.UNAUTHORIZED, "用户名或密码错误"));
			return;
		} else {
			// 登录成功
			HttpSession session = req.getSession();
			session.setAttribute("username", user.getUsername());
			session.setAttribute("password", user.getPassword());
			ResponseUtils.responseJSON(resp, new Message(Status.AUTHORIZED, "登录成功"));
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
