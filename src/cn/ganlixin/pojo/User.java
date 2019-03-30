package cn.ganlixin.pojo;

/**
 * 用户类
 * @author ganlixin
 */
public class User {
	
	/**
	 * 用户表中的主键id
	 */
	private int id;
	
	/**
	 * 用户登录名
	 */
	private String username;
	
	/**
	 * 用户登录时的密码
	 */
	private String password;
	
	/**
	 * 用户的昵称（显示名）
	 */
	private String nickname;
	
	/**
	 * 用户是否有效
	 */
	private Integer active;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(int id, String username, String password, String nickname, Integer active) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.active = active;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Integer getActive() {
		return active;
	}
	
	public void setActive(Integer active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", active=" + active + "]";
	}
}
