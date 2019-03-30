package cn.ganlixin.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import cn.ganlixin.dao.WebsiteDao;
import cn.ganlixin.dao.impl.WebsiteDaoImpl;
import cn.ganlixin.pojo.Message;
import cn.ganlixin.pojo.Website;
import cn.ganlixin.service.WebsiteService;
import cn.ganlixin.util.IOUtil;
import cn.ganlixin.util.Status;

public class WebsiteServiceImpl implements WebsiteService {
	
	private WebsiteDao websiteDao = new WebsiteDaoImpl();;

	@Override
	public Message saveWebsite(Website ws, String imagesDir) {
		
		// 先下载图标，保存到图标库，然后获取图标文件名称
		String iconName = getIcon(ws.getUrl(), imagesDir);
		
		if (iconName == null) {
			return new Message(Status.FAILED, "获取网站信息失败，请确认网站是否可以访问，如果可以正常访问，请联系管理员");
		}
		
		ws.setIcon(iconName);
		
		String url = ws.getUrl();
		if (url.endsWith("/")) {
			ws.setUrl(url.substring(0, url.length()-1));
		}
		int count = websiteDao.add(ws);
		
		if (count > 0) {
			return new Message(Status.SUCCESS, "success");
		} else {
			return new Message(Status.FAILED, "failed");
		}
	}

	@Override
	public List<Website> getAllWebsite() {
		return websiteDao.selectAll();
	}
	
	@Override
	public List<Website> getAllActiveWebsite() {
		return websiteDao.selectAllActive();
	}


	@Override
	public String getIcon(String url, String dir) {
		if (! url.endsWith("/")) {
			url += "/";
		}
		
		URL iconPath = null;
		InputStream _is = null;
		OutputStream _os = null;
		
		try {
			iconPath = new URL(url + "favicon.ico");
			URLConnection conn = iconPath.openConnection();
			
			// 设置user-agent，预防403
			conn.setRequestProperty("user-agent", "Chrome/73.0.3683.86");
			
			_is = conn.getInputStream();
			
			// 将时间戳设置为图标名称
			String iconName = System.currentTimeMillis() + ".ico";
			_os = new FileOutputStream(new File(dir + iconName));
			
			byte[] data = new byte[1024*10]; // 10KB

			int length = -1;
			while ((length = _is.read(data)) != -1) {
				_os.write(data, 0, length);
			}
			_os.flush();
			return iconName;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtil.close(_is);
			IOUtil.close(_os);
		}
		return null;
	}

	@Override
	public Message removeWebsite(int id) {
		
		if (id <= 0) {
			return new Message(Status.FAILED, "id值不合法");
		}
		
		int count = websiteDao.deleteById(id);
		if (count > 0) {
			return new Message(Status.SUCCESS, "删除成功");
		} else {
			return new Message(Status.FAILED, "删除失败");
		}
	}

	@Override
	public Message coldWebsite(int id) {
		int count = websiteDao.freezeById(id);
		if (count > 0) {
			return new Message(Status.SUCCESS, "冻结成功成功");
		} else {
			return new Message(Status.FAILED, "冻结失败");
		}
	}

	@Override
	public Message releaseWebsite(int id) {
		int count = websiteDao.freeById(id);
		if (count > 0) {
			return new Message(Status.SUCCESS, "解冻成功成功");
		} else {
			return new Message(Status.FAILED, "解冻失败");
		}
	}
}
