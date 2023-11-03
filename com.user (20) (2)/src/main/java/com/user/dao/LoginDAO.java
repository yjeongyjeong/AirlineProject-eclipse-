package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.user.util.DBManager;
import com.user.vo.KakaoUserVO;

public class LoginDAO {
	private LoginDAO() {}
	
	private static LoginDAO instance = new LoginDAO();
	
	public static LoginDAO getInstance() {
		return instance;
	}
	
	public int userCheck(String userid, String pwd) {
		int result = -1;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select pwd from kakaouser where userid=?";

		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();

			if (rs.next()) {
				if (rs.getString("pwd").equals(pwd)) {
					result = 1; // 아이디 존재, 비밀번호 일치
				} else {
					result = 0; // 아이디 존재, 비밀번호 불일치
				}
			} else {
				result = -1; // 아이디 없음
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, conn);
		}
		return result;
	}

	public KakaoUserVO getOneUser(String userid) {
		KakaoUserVO vo = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * from kakaouser where userid=?";

		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();

			if (rs.next()) {
				vo = new KakaoUserVO();
				vo.setUserid(rs.getString("userid"));
				vo.setGradecode(rs.getInt("gradecode"));
				vo.setKorName(rs.getString("korName"));
				vo.setEngName(rs.getString("engName"));
				vo.setUsernum(rs.getLong("usernum"));
				vo.setPwd(rs.getString("pwd"));
				vo.setGender(rs.getInt("gender"));
				vo.setMail(rs.getString("mail"));
				vo.setPhone(rs.getString("phone"));
				vo.setPostcode(rs.getInt("postcode"));
				vo.setAddress(rs.getString("address"));
				vo.setAdmin(rs.getInt("admin"));

				System.out.println(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, conn);
		}

		return vo;
	}

	public int existUserCheck(String engName, String korName, int gender, long usernum) {
		String sql = "select * from kakaouser where engName =? and korName=? and gender=? and usernum=?";

		int result = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, engName);
			ps.setString(2, korName);
			ps.setInt(3, gender);
			ps.setLong(4, usernum);

			rs = ps.executeQuery();

			if (rs.next()) {
				result = 1; //기존 가입 회원
			} else {
				result = -1; //신규 가입 회원
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int registerCheck(String userid) {

		String SQL = "select * from kakaouser where userID = ?";
		int result = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			
			if (rs.next() || userid.equals("")) { // 결과가 있다면
				result = 0; // 이미 존재하는 아이디
			} else {
				result = 1; // 가입 가능한 아이디
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, conn);
		}
		return result; // -1이면 데이터베이스 오류
	}

	public String emailDupleCheck(String email) {
		String result = "unuseable";
		String sql = "SELECT mail FROM kakaouser WHERE mail = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, email);
			rs = psmt.executeQuery();

			if (rs.next()) { 
				result = "useable";
				System.out.println("이메일 정보 있음(회원맞음)");
			} else {
				result = "unuseable";
				System.out.println("이메일 정보 없음(회원아님)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, psmt, conn);
		}

		return result;
	}

	public String getUserId(String email) {
		String sql = "select userid from kakaouser where mail=?";
		String result = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				result = rs.getString("userid");
				System.out.println("DAO에서 가져온 결과(userid): "+ result);
			} else {
				result = "오류가 발생했습니다.";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, conn);
		}
		
		return result;
	}
	
	//유저아이디중복
	//유저아이디앤드메일
	public int useridAndMailCheck(String userid, String mail) { //아이디랑 이메일 주소로 아이디 찾기..
			String sql = "select pwd from kakaouser where userid=? AND mail=?";
			int result = -1;
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				conn = DBManager.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, userid);
				ps.setString(2, mail);
				rs = ps.executeQuery();
				
				if( rs.next() ) {
					result = 1;
				} else {
					result = 0;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.getClose(rs, ps, conn);
			}
			
			return result;
		}
		
	public int useridDupleCheck(String userid) { //아이디 중복체크 확인
        int result = -1;
        String sql = "SELECT userid FROM kakaouser WHERE userid = ?";

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
           conn = DBManager.getConnection();
           psmt = conn.prepareStatement(sql);
           psmt.setString(1, userid);
           rs = psmt.executeQuery();

           if (rs.next()) { 
              result = 1;
              System.out.println("아이디 정보 있음(아이디 중복)");
           } else {
              result = -1;
              System.out.println(userid + " : 아이디 정보 없음(아이디 사용가능)");
           }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           DBManager.getClose(rs, psmt, conn);
        }

        return result;
     }
	
	

}
