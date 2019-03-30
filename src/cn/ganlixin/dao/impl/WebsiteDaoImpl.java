package cn.ganlixin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.ganlixin.dao.WebsiteDao;
import cn.ganlixin.pojo.Website;
import cn.ganlixin.util.DbUtils;

public class WebsiteDaoImpl implements WebsiteDao {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@Override
	public Website select(Website ws) {
		return null;
	}

	@Override
	public Website selectOneById(int id) {
		try {
			conn = DbUtils.getConnection();
			pstmt = conn.prepareStatement("select id, sitename, icon, url, active, hits, ordering from website where id=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			Website ws = new Website();
			ws.setId(id);
			ws.setSitename(rs.getString("sitename"));
			ws.setUrl(rs.getString("url"));
			ws.setIcon(rs.getString("icon"));
			ws.setHits(rs.getInt("hits"));
			ws.setActive(rs.getInt("active"));
			ws.setOrdering(rs.getInt("ordering"));
			
			return ws;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.free(conn, pstmt, rs);
		}
		
		return null;
	}
	
	@Override
	public List<Website> selectAllActive() {
		try {
			conn = DbUtils.getConnection();
			pstmt = conn.prepareStatement("select id, sitename, icon, url, active, hits, ordering from website where active=1");
			rs = pstmt.executeQuery();
			
			List<Website> list = new ArrayList<>();
			while (rs.next() ){
				Website ws = new Website();
				ws.setId(rs.getInt("id"));
				ws.setSitename(rs.getString("sitename"));
				ws.setIcon(rs.getString("icon"));
				ws.setUrl(rs.getString("url"));
				ws.setActive(rs.getInt("active"));
				ws.setHits(rs.getInt("hits"));
				ws.setOrdering(rs.getInt("ordering"));
				list.add(ws);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.free(conn, pstmt, rs);
		}
		
		return null;
	}


	@Override
	public List<Website> selectAll() {
		try {
			conn = DbUtils.getConnection();
			pstmt = conn.prepareStatement("select id, sitename, icon, url, active, hits, ordering from website");
			rs = pstmt.executeQuery();
			
			List<Website> list = new ArrayList<>();
			while (rs.next() ){
				Website ws = new Website();
				ws.setId(rs.getInt("id"));
				ws.setSitename(rs.getString("sitename"));
				ws.setIcon(rs.getString("icon"));
				ws.setUrl(rs.getString("url"));
				ws.setActive(rs.getInt("active"));
				ws.setHits(rs.getInt("hits"));
				ws.setOrdering(rs.getInt("ordering"));
				list.add(ws);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.free(conn, pstmt, rs);
		}
		
		return null;
	}

	@Override
	public int delete(Website ws) {
		return 0;
	}

	@Override
	public int deleteById(int id) {
		try {
			conn = DbUtils.getConnection();
			pstmt = conn.prepareStatement("delete from website where id=?");
			pstmt.setInt(1, id);
			int count = pstmt.executeUpdate();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.free(conn, pstmt);
		}
		return 0;
	}

	@Override
	public int add(Website ws) {
		try {
			conn = DbUtils.getConnection();
			pstmt = conn.prepareStatement("insert into website (id, sitename, icon, url, hits, ordering) values (null, ?, ?, ?, ?, ?)");
			
			pstmt.setObject(1, ws.getSitename());
			pstmt.setObject(2, ws.getIcon());
			pstmt.setObject(3, ws.getUrl());
			pstmt.setObject(4, ws.getHits());
			pstmt.setObject(5, ws.getOrdering());
			
			int count = pstmt.executeUpdate();
			
			if (count > 0) {
				System.out.println("成功添加website记录 ： " + ws);
			} else {
				System.out.println("添加website记录失败， 数据为 ： " + ws);
			}

			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.free(conn, pstmt);
		}
		
		return 0;
	}

	@Override
	public int update(Website ws) {
		return 0;
	}

	@Override
	public int deleteAll() {
		return 0;
	}

	@Override
	public int freezeById(int id) {
		try {
			conn = DbUtils.getConnection();
			pstmt = conn.prepareStatement("update website set active=0 where id=?");
			pstmt.setInt(1, id);
			int count = pstmt.executeUpdate();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.free(conn, pstmt);
		}
		return 0;
	}

	@Override
	public int freeById(int id) {
		try {
			conn = DbUtils.getConnection();
			pstmt = conn.prepareStatement("update website set active=1 where id=?");
			pstmt.setInt(1, id);
			int count = pstmt.executeUpdate();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.free(conn, pstmt);
		}
		return 0;
	}
}
