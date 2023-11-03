package com.user.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	public static Connection getConnection() throws Exception {
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		Connection con = ds.getConnection();
		System.out.println("con연결확인 : "+con);
		return con;
	}
	
	public static void getClose(ResultSet rs, PreparedStatement ps, Connection con) {
		try {
			if(rs != null)rs.close();
			if(ps != null)ps.close();
			if(con != null)con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getClose(PreparedStatement ps, Connection con) {
		try {
			if(ps != null)ps.close();
			if(con != null)con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
