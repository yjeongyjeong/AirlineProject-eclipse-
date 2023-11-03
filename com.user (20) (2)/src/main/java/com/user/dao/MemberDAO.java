package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.user.vo.MemberVO;


//싱글톤 패턴 -- MemberDAO 하나만 생성하겠다.

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();
	
	private MemberDAO() {}
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");		//server.xml에서 <resource>의 name 속성과 같은 값이어야 함
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public int userCheck(String userId, String pwd){
		
		int result = -1;
		String sql = "select pwd from mymember where userid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {		//데이터가 1건이므로 while 대신 if
				if(rs.getString("pwd").equals(pwd)) {
					result = 1;	//아이디 일치, 비밀번호 일치
				} else {
					result = 0; //아이디 일치, 비밀번호 불일치
				}
			} else {
				result = -1; //아이디 없음
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	//userId에 해당하는 데이터 가져오기
	public MemberVO getMember(String userId) {
		MemberVO vo = null;
		String sql = "select * from mymember where userId = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString("name");
				String id = rs.getString("userId");
				String pwd = rs.getString("pwd");
				String email = rs.getString("email");
				int admin = rs.getInt("admin");
				
				vo = new MemberVO();
				vo.setName(name);
				vo.setUserId(id);
				vo.setPwd(pwd);
				vo.setEmail(email);
				vo.setAdmin(admin);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}

	
}
