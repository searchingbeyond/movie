package cn.ganlixin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ganlixin.pojo.Message;
import cn.ganlixin.pojo.User;
import cn.ganlixin.service.LoginService;
import cn.ganlixin.service.impl.LoginServiceImpl;
import cn.ganlixin.util.ResponseUtils;
import cn.ganlixin.util.Status;

/**
 * 进行数据编码设置，登录验证
 */
@WebFilter("/*")
public class MyFilter implements Filter {
	
	private LoginService loginService = new LoginServiceImpl();
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see 进行过滤操作
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		req.setCharacterEncoding("utf-8");
		
		String action = req.getParameter("action");
		
		// 如果访问首页，可以不用验证身份
		if (action == null || "index".equals(action) || "login".equals(action)) {
			chain.doFilter(req, resp);
			return;
		}
		
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		User u = loginService.checkUser(user);
		
		if (u != null) { // 已经是登录状态，此时检测用户状态
			if ("checkStatus".equals(action)) {
				System.out.println("进行的操作：" + action + "------ 传递的参数 "+ username + "--->" + password);
				Message msg = new Message(Status.AUTHORIZED, u.getNickname());
				ResponseUtils.responseJSON(resp, msg);
				return;
			}
			chain.doFilter(req, resp);
		} else {
			Message msg = new Message(Status.UNAUTHORIZED, "当前用户未登录，请登陆后进行操作！");
			ResponseUtils.responseJSON(resp, msg);
		} 
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
