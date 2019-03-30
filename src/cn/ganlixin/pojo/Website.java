package cn.ganlixin.pojo;

/**
 * @author ganlixin
 */
public class Website {
	
	/**
	 * 主键id
	 */
	private Integer id;
	
	/**
	 * 网站名称
	 */
	private String sitename;
	
	/**
	 * 网站的图标名称
	 */
	private String icon;
	
	/**
	 * 网站的网址
	 */
	private String url;
	
	/**
	 * 网站是否可访问
	 */
	private Integer active;
	
	/**
	 * 网站点击量
	 */
	private Integer hits;
	
	/**
	 * 网站的热度排序
	 */
	private Integer ordering;

	public Website() {
		super();
	}

	
	public Website(Integer id, String sitename, String icon, String url, Integer active, 
			Integer hits, Integer ordering) {
		super();
		this.id = id;
		this.sitename = sitename;
		this.icon = icon;
		this.url = url;
		this.active = active;
		this.hits = hits;
		this.ordering = ordering;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getOrdering() {
		return ordering;
	}

	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}
	
	@Override
	public String toString() {
		return "Website [id=" + id + ", sitename=" + sitename + ", icon=" + icon + 
				", url=" + url + ", active=" + active+ ", hits=" + hits +
				", ordering=" + ordering + "]";
	}

}
