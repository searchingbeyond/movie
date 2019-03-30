package cn.ganlixin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ganlixin.pojo.Message;
import cn.ganlixin.pojo.Website;
import cn.ganlixin.service.WebsiteService;
import cn.ganlixin.service.impl.WebsiteServiceImpl;
import cn.ganlixin.util.ResponseUtils;
import cn.ganlixin.util.Status;

@WebServlet("/website")
public class WebsiteServlet extends HttpServlet {
	
	private WebsiteService websiteService = new WebsiteServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		String action = req.getParameter("action");
		if (action == null) {
			list(req, resp);
			return;
		}
		
		switch(action) {
			case "index":
				activeList(req, resp); break;
				
			case "add":
				add(req, resp); break;
				
			case "delete":
				delete(req, resp); break;
				
			case "change":
				update(req, resp); break;
				
			case "admin":
				list(req, resp); break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
	/**
	 * 删除website记录的事件处理器
	 * @param req
	 * @param resp
	 */
	public void delete(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		
		Message data = websiteService.removeWebsite(id);
		ResponseUtils.responseJSON(resp, data);
	}
	
	/**
	 * 获取website（有效的）列表的事件处理器
	 * @param req
	 * @param resp
	 */
	public void activeList(HttpServletRequest req, HttpServletResponse resp) {
		List<Website> list = websiteService.getAllActiveWebsite();
		ResponseUtils.responseJSON(resp, list);
	}
	
	/**
	 * 获取website（全部site）列表的事件处理器
	 * @param req
	 * @param resp
	 */
	public void list(HttpServletRequest req, HttpServletResponse resp) {
		List<Website> list = websiteService.getAllWebsite();
		ResponseUtils.responseJSON(resp, list);
	}
	
	/**
	 * 添加website的事件处理器
	 * @param req
	 * @param resp
	 */
	public void add(HttpServletRequest req, HttpServletResponse resp) {
		
		String sitename = req.getParameter("sitename");
		String url = req.getParameter("url");
		
		Website ws = new Website();
		ws.setSitename(sitename);
		ws.setUrl(url);
		
		String imagesDir = getServletContext().getRealPath("resource/images/");
		
		Message msg = websiteService.saveWebsite(ws, imagesDir);
		
		ResponseUtils.responseJSON(resp, msg);
	}
	
	/**
	 * 修改website记录的active
	 * @param req
	 * @param resp
	 */
	public void update(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("--------------");
		String id = req.getParameter("id");
		String active = req.getParameter("active");
		if (id == null || active == null) {
			ResponseUtils.responseJSON(resp, new Message(Status.PARAMETER_WRONG, "参数错误"));
			return;
		}
		if (active.equals("1")) {
			System.out.println("将id为 " + id + " 的website记录设置为冻结状态");
			Message msg = websiteService.coldWebsite(Integer.parseInt(id));
			ResponseUtils.responseJSON(resp, msg);
		} else {
			System.out.println("将id为 " + id + " 的website记录设置为正常");
			Message msg = websiteService.releaseWebsite(Integer.parseInt(id));
			ResponseUtils.responseJSON(resp, msg);
		}
	}
}
