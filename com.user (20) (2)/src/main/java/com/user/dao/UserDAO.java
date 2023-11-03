package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.user.util.DBManager;
import com.user.vo.GradeVO;
import com.user.vo.KakaoUserVO;
import com.user.vo.NoticeVO;
import com.user.vo.UserInfoVO;

public class UserDAO {

	private UserDAO() {}
	
	private static UserDAO instance = new UserDAO();
	
	public static UserDAO getInstance() {
		return instance;
	}

	//아이디에 맞는 유저정보 가져오기
	public KakaoUserVO getUser(String userid) {
		String sql = "select * from kakaouser where userid = ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		KakaoUserVO vo = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			
			if(rs.next()) {
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
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(rs, ps, con);
		}
		
		return vo;
	}

	//로그인 체크
	public KakaoUserVO loginCheck(String userid) {
		String sql = "select * from kakaouser where userid = ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		KakaoUserVO vo = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			
			if(rs.next()) { //pwd 랑 admin 여부만 가지고 오면 됨..
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
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(rs, ps, con);
		}
		return vo;
	}

	//모든 유저정보 조회
	public List<KakaoUserVO> getAllUser() {
		List<KakaoUserVO> list = new ArrayList<KakaoUserVO>();
		String sql = "select * from kakaouser";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		KakaoUserVO vo = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
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
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(rs, ps, con);
		}
		return list;
	}

	public List<UserInfoVO> getGrade() {
		List<UserInfoVO> list = new ArrayList<UserInfoVO>();
		String sql = "select g.usergrade as usergrade, k.* from grade g, kakaouser k where g.gradecode=k.gradecode";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserInfoVO vo = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				vo = new UserInfoVO();
				vo.setUserid(rs.getString("userid"));
				vo.setGradecode(rs.getInt("gradecode"));
				vo.setKorName(rs.getString("korName"));
				vo.setEngName(rs.getString("engName"));
				vo.setUsernum(rs.getLong("usernum"));
				vo.setGender(rs.getInt("gender"));
				vo.setMail(rs.getString("mail"));
				vo.setPhone(rs.getString("phone"));
				vo.setPostcode(rs.getInt("postcode"));
				vo.setAddress(rs.getString("address"));
				vo.setAdmin(rs.getInt("admin"));
				vo.setUsergrade(rs.getString("usergrade"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(rs, ps, con);
		}
		
		return list;
	}

	//전체 게시물의 개수
	public int getTotalCount() {
		String sql = "select count(row_num) as count from(SELECT ROW_NUMBER() OVER(ORDER BY k.korname, k.engname) AS row_num from kakaouser k)";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = -1;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(rs, ps, con);
		}
		
		return result;
	}

	public List<UserInfoVO> pageList(int onePage, int curPage) { //1p당 게시물의 수 5 
		List<UserInfoVO> list = new ArrayList<UserInfoVO>();
		String sql = "select * from(SELECT ROW_NUMBER() OVER(ORDER BY l.regidate desc) AS ROW_NUM, l.regidate ,k.*,g.usergrade FROM userlog l, kakaouser k, grade g where g.gradecode=k.gradecode and k.userid = l.userid) userinfo where userinfo.ROW_NUM between ? and ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserInfoVO vo = null;
		int start = 1+(curPage-1)*onePage;// 1 6 11 
		int end = curPage*onePage;; //5 10 15
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				vo = new UserInfoVO();
				vo.setUserid(rs.getString("userid"));
				vo.setGradecode(rs.getInt("gradecode"));
				vo.setKorName(rs.getString("korName"));
				vo.setEngName(rs.getString("engName"));
				vo.setUsernum(rs.getLong("usernum"));
				vo.setGender(rs.getInt("gender"));
				vo.setMail(rs.getString("mail"));
				vo.setPhone(rs.getString("phone"));
				vo.setPostcode(rs.getInt("postcode"));
				vo.setAddress(rs.getString("address"));
				vo.setAdmin(rs.getInt("admin"));
				vo.setUsergrade(rs.getString("usergrade"));
				vo.setNum(rs.getInt("ROW_NUM"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(rs, ps, con);
		}
		
		return list;
	}

	//검색어에 따른 리스트 반환
	public List<UserInfoVO> getSearch(String type, String keyword, int onePage, int curPage, int totalCount) {
		List<UserInfoVO> list = new ArrayList<UserInfoVO>();
		//String sql = "select * FROM (SELECT ROW_NUMBER() OVER(ORDER BY l.regidate desc) AS ROW_NUM, l.regidate ,k.*,g.usergrade FROM userlog l, kakaouser k, grade g where g.gradecode=k.gradecode and k.userid = l.userid and k." + type + " = ? ) userinfo where userinfo.row_num between ? and ?";
		String sql = "select * FROM (SELECT ROW_NUMBER() OVER(ORDER BY l.regidate desc) AS ROW_NUM, l.regidate ,k.*,g.usergrade FROM userlog l, kakaouser k, grade g where g.gradecode=k.gradecode and k.userid = l.userid and k." + type + " like '%"+keyword+"%' ) userinfo where userinfo.row_num between ? and ?";
		System.out.println(sql);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserInfoVO vo = null;
		int start = 1+(curPage-1)*onePage;// 1 6 11 
		int end = curPage*onePage; //5 10 15
//		if(curPage == 1) {
//			end = totalCount;
//		}
	
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			//ps.setString(1, keyword);
			ps.setInt(1, start);//start
			ps.setInt(2, end); //end
			System.out.println(keyword+":"+start+":"+end);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				vo = new UserInfoVO();
				vo.setUserid(rs.getString("userid"));
				vo.setGradecode(rs.getInt("gradecode"));
				vo.setKorName(rs.getString("korName"));
				vo.setEngName(rs.getString("engName"));
				vo.setUsernum(rs.getLong("usernum"));
				vo.setGender(rs.getInt("gender"));
				vo.setMail(rs.getString("mail"));
				vo.setPhone(rs.getString("phone"));
				vo.setPostcode(rs.getInt("postcode"));
				vo.setAddress(rs.getString("address"));
				vo.setAdmin(rs.getInt("admin"));
				vo.setUsergrade(rs.getString("usergrade"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(rs, ps, con);
		}
		
		return list;
	}

	//검색어 조건에 따른 전체 게시물 개수
	public int getSearchTotalCount(String type, String keyword) {
		//String sql = "select count(row_num) as count from(SELECT ROW_NUMBER() OVER(ORDER BY k.korname, k.engname) AS row_num from kakaouser k where ? = ?)";
//		String sql = "select count(*) as count from(select k.*, g.usergrade from kakaouser k, grade g where k.gradecode=g.gradecode) where " + type + " = ?";
		String sql = "select count(*) FROM (SELECT ROW_NUMBER() OVER(ORDER BY l.regidate desc) AS ROW_NUM, l.regidate ,k.*,g.usergrade FROM userlog l, kakaouser k, grade g where g.gradecode=k.gradecode and k.userid = l.userid and k." + type + " like '%"+keyword+"%' ) userinfo";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = -1;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			//ps.setString(1, type);
			//ps.setString(1, keyword);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
				//result = 20;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(rs, ps, con);
		}
		System.out.println("result : "+result);
//		
		return result;
	}
	
	//업데이트유저패스워드
	public int updateUserPwd(String userid, String pwd) {
			String sql = "update kakaouser set pwd = ? where userid=?";
			Connection con = null;
			PreparedStatement ps = null;
			int result = -1;
			
			try {
				con = DBManager.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, pwd);
				ps.setString(2, userid);
				
				result = ps.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.getClose(ps, con);
			}
			System.out.println("updateUserPwd result : "+ result);
//			
			return result;
		}
	
	//joinInform에서 입력받은 정보로 회원가입
	public int insertKakaoUser(String korName, String engName, String gender, String usernum, String userid, String pwd, String phone, String mail, String postcode, String address) {
		int result = -1;
		String sql = "insert into kakaouser values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setInt(2, 0); //gradecode
			ps.setString(3, korName);
			ps.setString(4, engName);
			ps.setLong(5, Long.parseLong(usernum));
			ps.setString(6, pwd);
			ps.setInt(7, Integer.parseInt(gender));
			ps.setString(8, mail);
			ps.setString(9, phone);
			ps.setInt(10, Integer.parseInt(postcode));
			ps.setString(11, address);
			ps.setInt(12, 0); //admin

			result = ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		
		return result;
	}

	//유저의 약관동의 부분(추후 아이디따라 업데이트 되는 쿼리로 변경예정...)
	public int insertTermsLog01(String userid) {
		int result = -1;
		String sql = "insert into termslog values(?, ?, ?)";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, userid);
			ps.setInt(2, 1);
			ps.setInt(3, 1);

			result = ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		
		return result;
	}
	public int insertTermsLog02(String userid) {
		int result = -1;
		String sql = "insert into termslog values(?, ?, ?)";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, userid);
			ps.setInt(2, 2);
			ps.setInt(3, 1);
			
			result = ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		
		return result;
	}
	public int insertTermsLog03(String userid) {
		int result = -1;
		String sql = "insert into termslog values(?, ?, ?)";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, userid);
			ps.setInt(2, 3);
			ps.setInt(3, 1);
			
			result = ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		
		return result;
	}
	//유저로그 삽입
	public int insertUserLog(String userid) {
		int result = -1;
		String sql = "insert into userlog values(?, sysdate, sysdate, sysdate, ? , ?)"; 

		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, userid);
			ps.setInt(2, 0);
			ps.setInt(3, 0);

			result = ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		
		return result;
		
	}
	
//userid=user02, gradecode=0, korName=보람, engName=boram,
		//usernum=9411112111111, pwd=1111d, gender=2, mail=boram@naver.com, 
		//phone=010-2222-1111, postcode=22222, address=수원시 영통구, admin=0
		public int updateUser(KakaoUserVO vo) {
			String sql = "update kakaouser set korName=?, engName=?, pwd = ?, gender=?, mail=?, phone=?, postcode=?, address=? where userid=?";
			Connection con = null;
			PreparedStatement ps = null;
			int result = -1;
			
			try {
				con = DBManager.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, vo.getKorName());
				ps.setString(2, vo.getEngName());
				ps.setString(3, vo.getPwd());
				ps.setInt(4, vo.getGender());
				ps.setString(5, vo.getMail());
				ps.setString(6, vo.getPhone());
				ps.setInt(7, vo.getPostcode());
				ps.setString(8, vo.getAddress());
				ps.setString(9, vo.getUserid());
				
				result = ps.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.getClose(ps, con);
			}
			System.out.println("updateUser result : "+ result);
			
			return result;
		}

		//아이디 체크
		public int getUserid(String userid) {
			String sql = "select count(userid) as count from kakaouser where userid=?";
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int result = -1;
			
			try {
				con = DBManager.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, userid);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					result = rs.getInt("count");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.getClose(rs, ps, con);
			}
			
			return result;
		}
	
	
}
