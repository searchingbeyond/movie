package cn.ganlixin.dao;

import java.util.List;

import cn.ganlixin.pojo.Website;

/**
 * 与website表相关的操作接口定义
 * @author ganlixin
 *
 */
public interface WebsiteDao {
	
	/**
	 * 查询指定的website记录
	 * @param ws
	 * @return
	 */
	public Website select(Website ws);
	
	/**
	 * 根据website的id查询出对应的记录
	 * @param id
	 * @return	Website
	 */
	public Website selectOneById(int id);
	
	/**
	 * 查询出所有的website记录（包括有效和无效的两种）
	 * @return List<Website>
	 */
	public List<Website> selectAll();

	/**
	 * 查询出所有有效的网站
	 * @return
	 */
	public List<Website> selectAllActive();
	
	/**
	 * 删除指定的website记录
	 * @param ws
	 * @return
	 */
	public int delete(Website ws);
	
	/**
	 * 根据id删除website记录（改变标志位字段）
	 * @param id
	 * @return
	 */
	public int deleteById(int id);
	
	/**
	 * 添加一条website记录
	 * @param ws
	 * @return
	 */
	public int add(Website ws);
	
	/**
	 * 更新一条website记录
	 * @param ws
	 * @return
	 */
	public int update(Website ws);
	
	/**
	 * 删除所有的website记录
	 * @return
	 */
	public int deleteAll();
	
	/**
	 * 冻结网站
	 * @param id
	 * @return
	 */
	public int freezeById(int id);
	
	/**
	 * 解封网站
	 * @param id
	 * @return
	 */
	public int freeById(int id);
	
}
