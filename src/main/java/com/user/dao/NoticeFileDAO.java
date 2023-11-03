package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class NoticeFileDAO {

	private NoticeFileDAO() {
	}

	private NoticeFileDAO instance = new NoticeFileDAO();

	public NoticeFileDAO getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// DB연동 닫아주는 메서드(conn,ps,rs) select일 때
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (conn != null)
				conn.close();
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DB연동 닫아주는 메서드 (conn,ps) rs가 없는 것(select를 제외한 update,delete,insert)
	public void close(Connection conn, PreparedStatement ps) {
		try {
			if (conn != null)
				conn.close();
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	
}
