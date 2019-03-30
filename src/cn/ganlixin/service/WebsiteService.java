package cn.ganlixin.service;

import java.util.List;

import cn.ganlixin.pojo.Message;
import cn.ganlixin.pojo.Website;

public interface WebsiteService {
	
	/**
	 * 保存提交的website
	 * @param ws
	 * @param imagesDir
	 * @return
	 */
	public Message saveWebsite(Website ws, String imagesDir);
	
	/**
	 * 获取所有website记录
	 * @return
	 */
	public List<Website> getAllWebsite();
	
	/**
	 * 获取所有有效的website记录
	 * @return
	 */
	public List<Website> getAllActiveWebsite();
	
	/**
	 * 获取url所指的网站图标log，并保存到指定目录下
	 * @param url 网站名称
	 * @param dir 要保存目录
	 * @return	将网站图标保存到图标库之后，返回图标的名称
	 */
	public String getIcon(String url, String dir);
	
	/**
	 * 
	 * @param id 要删除的website对应表中的id字段值
	 * @return
	 */
	public Message removeWebsite(int id);
	
	/**
	 * 冻结网站，改变active值为0
	 * @param id
	 * @return
	 */
	public Message coldWebsite(int id);
	
	/**
	 * 解除网站被冻结的状态
	 * @param id
	 * @return
	 */
	public Message releaseWebsite(int id);
}
