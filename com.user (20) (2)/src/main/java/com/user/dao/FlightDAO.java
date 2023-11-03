package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.user.util.DBManager;
import com.user.vo.FlightVO;

public class FlightDAO {

	private FlightDAO() {}
	
	private static FlightDAO instance = new FlightDAO();
	
	public static FlightDAO getInstance() {
		return instance;
	}

	//항공운항 스케줄 조회
	public List<FlightVO> getFlightList() {
		String sql = "select * from flight2";
		List<FlightVO> list = new ArrayList<FlightVO>();
		FlightVO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				vo = new FlightVO();
				vo.setDepartureState(rs.getString("departureState"));
				vo.setRegidate(rs.getString("regidate"));
				vo.setAirline(rs.getString("airline"));
				vo.setFlightnum(rs.getString("flightnum"));
				vo.setDepairportcode(rs.getString("depairportcode"));
				vo.setDepairport(rs.getString("depairport"));
				vo.setArrairportcode(rs.getString("arrairportcode"));
				vo.setArrairport(rs.getString("arrairport"));
				vo.setPlantimes(rs.getString("plantimes"));
				vo.setEstimate(rs.getString("estimate"));
				vo.setArrtime(rs.getString("arrtime"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return list;
	}
	
	//전체 개수 카운트
	public int getCount() {
		String sql = "select count(regidate) as count from flight2";
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return result;
	}
	
	//페이징
	public List<FlightVO> getPaging(int curPage, int page){
		int start = (curPage-1)*page + 1;
		int end = curPage*page;
		String sql = "SELECT * FROM(SELECT f.*, ROW_NUMBER() over(ORDER BY f.REGIDATE  desc) AS row_num FROM flight2 f)WHERE row_num BETWEEN ? AND ?";
		List<FlightVO> list = new ArrayList<FlightVO>();
		FlightVO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				vo = new FlightVO();
				vo.setDepartureState(rs.getString("departureState"));
				vo.setRegidate(rs.getString("regidate"));
				vo.setAirline(rs.getString("airline"));
				vo.setFlightnum(rs.getString("flightnum"));
				vo.setDepairportcode(rs.getString("depairportcode"));
				vo.setDepairport(rs.getString("depairport"));
				vo.setArrairportcode(rs.getString("arrairportcode"));
				vo.setArrairport(rs.getString("arrairport"));
				vo.setPlantimes(rs.getString("plantimes"));
				vo.setEstimate(rs.getString("estimate"));
				vo.setArrtime(rs.getString("arrtime"));
				vo.setNum(rs.getInt("row_num"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return list;
	}

	//검색 시 전체개수 카운트
	public int getsearchCount(String department, String arrive, String formattedDate) {
		String sql = "select count(regidate) as count from flight2 where regidate = ? and depairport = ? and arrairport=?";
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, formattedDate);
			ps.setString(2, department);
			ps.setString(3, arrive);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return result;
	}

	//검색어 페이징
	public List<FlightVO> getSearchPaging(int curPage, int page, String department, String arrive,String formattedDate) {
			int start = (curPage-1)*page + 1;
			int end = curPage*page;
			String sql = "SELECT * FROM(SELECT f.*, ROW_NUMBER() over(ORDER BY f.REGIDATE  desc) AS row_num FROM flight2 f where regidate = ? and depairport = ? and arrairport=?)WHERE row_num BETWEEN ? AND ?";
			List<FlightVO> list = new ArrayList<FlightVO>();
			FlightVO vo = null;
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				con = DBManager.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, formattedDate);
				ps.setString(2, department);
				ps.setString(3, arrive);
				ps.setInt(4, start);
				ps.setInt(5, end);
				rs = ps.executeQuery();
				while(rs.next()) {
					vo = new FlightVO();
					vo.setDepartureState(rs.getString("departureState"));
					vo.setRegidate(rs.getString("regidate"));
					vo.setAirline(rs.getString("airline"));
					vo.setFlightnum(rs.getString("flightnum"));
					vo.setDepairportcode(rs.getString("depairportcode"));
					vo.setDepairport(rs.getString("depairport"));
					vo.setArrairportcode(rs.getString("arrairportcode"));
					vo.setArrairport(rs.getString("arrairport"));
					vo.setPlantimes(rs.getString("plantimes"));
					vo.setEstimate(rs.getString("estimate"));
					vo.setArrtime(rs.getString("arrtime"));
					vo.setNum(rs.getInt("row_num"));
					list.add(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				DBManager.getClose(rs, ps, con);
			}
			
			return list;
	}

	//티켓구매(테이블 인서트)
	public int buyTicket(FlightVO vo) {
		String sql = "insert into flightres values(flightres_seq.nextval, ?, sysdate, ?, ?, ?, 150000,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		int result = -1;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1,vo.getUserid());
			ps.setString(2,vo.getDepairport());
			ps.setString(3,vo.getArrairport());
			ps.setString(4,vo.getFlightnum());
			ps.setString(5,vo.getAirline());
			ps.setString(6, vo.getRegidate());
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(ps, con);
		}
		
		return result;
	}
	

	//티켓구매후 리턴되는 페이지에서 내역 조회
	public FlightVO getOneTicket(String userid, int seq) {
		String sql = "select * from flightres where resno=? and userid=?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		FlightVO vo = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, seq);
			ps.setString(2, userid);

			rs = ps.executeQuery();
			if(rs.next()) {
				vo = new FlightVO();
				vo.setPurchasedate(rs.getString("purchasedate"));
				vo.setUserid(userid);
				vo.setAirline(rs.getString("airline"));
				vo.setFlightnum(rs.getString("flightnum"));
				vo.setDepairport(rs.getString("depairport"));
				vo.setArrairport(rs.getString("arrairport"));
				vo.setPrice(rs.getInt("price"));
				vo.setResno(rs.getString("resno"));
				vo.setRegidate(rs.getString("regidate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return vo;
	}
	
	
	//티켓구매 시퀀스 
	public int getSeqnum() {
		String sql = "select flightres_seq.currval as seq FROM dual";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = -1;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("seq");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(rs, ps, con);
		}	
		return result;
	}

	//티켓구매 취소
	public int cancelTicket(String resno) {
		String sql = "delete from flightres where resno=?";
		Connection con = null;
		PreparedStatement ps = null;
		int result = -1;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, resno);
			result = ps.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(ps, con);
		}	
		
		return result;
	}

	//관리자 페이지 전체회원 티켓구매현황 조회
	public List<FlightVO> getBuyList() {
		String sql = "select * from flightres";
		List<FlightVO> list = new ArrayList<FlightVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		FlightVO vo = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				vo = new FlightVO();
				vo.setPurchasedate(rs.getString("purchasedate"));
				vo.setUserid(rs.getString("userid"));
				vo.setAirline(rs.getString("airline"));
				vo.setFlightnum(rs.getString("flightnum"));
				vo.setDepairport(rs.getString("depairport"));
				vo.setArrairport(rs.getString("arrairport"));
				vo.setPrice(rs.getInt("price"));
				vo.setResno(rs.getString("resno"));
				vo.setRegidate(rs.getString("regidate"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return list;
	}

	//관리자 회원구매내역 전체 게시글 개수
	public int getBuyCount() {
		String sql = "select count(resno) as count from flightres";
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return result;
	}
	
	//기본 페이징_구매
	public List<FlightVO> getBuyPaging(int curPage, int page) {
		int start = (curPage-1)*page + 1;
		int end = curPage*page;
		String sql = "SELECT * FROM(SELECT f.*, ROW_NUMBER() over(ORDER BY f.REGIDATE  desc) AS row_num FROM flightres f)WHERE row_num BETWEEN ? AND ? order by purchasedate desc";
		List<FlightVO> list = new ArrayList<FlightVO>();
		FlightVO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				vo = new FlightVO();
				vo.setRegidate(rs.getString("regidate"));
				vo.setAirline(rs.getString("airline"));
				vo.setFlightnum(rs.getString("flightnum"));
				vo.setDepairport(rs.getString("depairport"));
				vo.setArrairport(rs.getString("arrairport"));
				vo.setPurchasedate(rs.getString("purchasedate"));
				vo.setPrice(rs.getInt("price"));
				vo.setUserid(rs.getString("userid"));
				vo.setNum(rs.getInt("row_num"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return list;
	}
	

	//관리자 회원구매내역 전체 게시글 개수(검색어_전체 포함)
	public int getBuysearchCount(String userid, String formattedDate) {
		String sql = "select count(userid) as count from flightres where userid=? and regidate = ?";
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setString(2, formattedDate);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return result;
	}

	//관리자 회원구매내역 전체 게시글 개수(검색어_아이디)
	public int getBuysearchCountForuserid(String userid) {
		String sql = "select count(userid) as count from flightres where userid=?";
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return result;
	}

	//관리자 회원구매내역 전체 게시글 개수(검색어_날짜)
	public int getBuysearchCountForDate(String formattedDate) {
		String sql = "select count(userid) as count from flightres where regidate = ?";
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, formattedDate);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return result;
	}

	//검색어 페이징
	public List<FlightVO> getBuySearchPaging(int curPage, int page, String userid, String formattedDate) {
		int start = (curPage-1)*page + 1;
		int end = curPage*page;
		String sql = "SELECT * FROM(SELECT f.*, ROW_NUMBER() over(ORDER BY f.REGIDATE  desc) AS row_num FROM flightres f where userid = ? and regidate = ?)WHERE row_num BETWEEN ? AND ? order by purchasedate desc";
		List<FlightVO> list = new ArrayList<FlightVO>();
		FlightVO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setString(2, formattedDate);
			ps.setInt(3, start);
			ps.setInt(4, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				vo = new FlightVO();
				vo = new FlightVO();
				vo.setRegidate(rs.getString("regidate"));
				vo.setAirline(rs.getString("airline"));
				vo.setFlightnum(rs.getString("flightnum"));
				vo.setDepairport(rs.getString("depairport"));
				vo.setArrairport(rs.getString("arrairport"));
				vo.setPurchasedate(rs.getString("purchasedate"));
				vo.setPrice(rs.getInt("price"));
				vo.setUserid(rs.getString("userid"));
				vo.setNum(rs.getInt("row_num"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return list;
	}

	public List<FlightVO> getBuySearchPagingForid(int curPage, int page, String userid) {
		int start = (curPage-1)*page + 1;
		int end = curPage*page;
		String sql = "SELECT * FROM(SELECT f.*, ROW_NUMBER() over(ORDER BY f.REGIDATE  desc) AS row_num FROM flightres f where userid = ?)WHERE row_num BETWEEN ? AND ?";
		List<FlightVO> list = new ArrayList<FlightVO>();
		FlightVO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				vo = new FlightVO();
				vo = new FlightVO();
				vo.setRegidate(rs.getString("regidate"));
				vo.setAirline(rs.getString("airline"));
				vo.setFlightnum(rs.getString("flightnum"));
				vo.setDepairport(rs.getString("depairport"));
				vo.setArrairport(rs.getString("arrairport"));
				vo.setPurchasedate(rs.getString("purchasedate"));
				vo.setPrice(rs.getInt("price"));
				vo.setUserid(rs.getString("userid"));
				vo.setNum(rs.getInt("row_num"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return list;
	}

	public List<FlightVO> getBuySearchPagingForDate(int curPage, int page, String formattedDate) {
		int start = (curPage-1)*page + 1;
		int end = curPage*page;
		String sql = "SELECT * FROM(SELECT f.*, ROW_NUMBER() over(ORDER BY f.REGIDATE  desc) AS row_num FROM flightres f where regidate = ?)WHERE row_num BETWEEN ? AND ?";
		List<FlightVO> list = new ArrayList<FlightVO>();
		FlightVO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, formattedDate);
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				vo = new FlightVO();
				vo = new FlightVO();
				vo.setRegidate(rs.getString("regidate"));
				vo.setAirline(rs.getString("airline"));
				vo.setFlightnum(rs.getString("flightnum"));
				vo.setDepairport(rs.getString("depairport"));
				vo.setArrairport(rs.getString("arrairport"));
				vo.setPurchasedate(rs.getString("purchasedate"));
				vo.setPrice(rs.getInt("price"));
				vo.setUserid(rs.getString("userid"));
				vo.setNum(rs.getInt("row_num"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return list;
	}

	//아이디별 티켓구매 리스트 조회
	public List<FlightVO> getTicketListById(int curPage, int onePage, String userid) {
		String sql = "SELECT * FROM(SELECT f.*, ROW_NUMBER() over(ORDER BY f.REGIDATE  desc) AS row_num FROM flightres f where userid=?)WHERE row_num BETWEEN ? AND ?";
		List<FlightVO> list = new ArrayList<FlightVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		FlightVO vo = null;
		int start = (curPage-1)*onePage + 1;
		int end = curPage*onePage;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				vo = new FlightVO();
				vo.setPurchasedate(rs.getString("purchasedate"));
				vo.setUserid(rs.getString("userid"));
				vo.setAirline(rs.getString("airline"));
				vo.setFlightnum(rs.getString("flightnum"));
				vo.setDepairport(rs.getString("depairport"));
				vo.setArrairport(rs.getString("arrairport"));
				vo.setPrice(rs.getInt("price"));
				vo.setResno(rs.getString("resno"));
				vo.setRegidate(rs.getString("regidate"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return list;
	}

	//구매버튼 실행 시 num을 기준으로 내용 가져옴 _ 검색어 미적용
	public FlightVO getFlightInfo(String num) {
		String sql = "SELECT * FROM(SELECT f.*, ROW_NUMBER() over(ORDER BY f.REGIDATE  desc) AS row_num FROM flight2 f)WHERE row_num = ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		FlightVO vo = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(num));
			rs = ps.executeQuery();
			while(rs.next()) {
				vo = new FlightVO();
				vo.setAirline(rs.getString("airline"));
				vo.setFlightnum(rs.getString("flightnum"));
				vo.setDepairport(rs.getString("depairport"));
				vo.setArrairport(rs.getString("arrairport"));
				vo.setRegidate(rs.getString("regidate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return vo;
	}

	//아이디별 티켓 구매 개수
	public int getTicketCountById(String userid) {
		String sql = "select count(userid) as count from flightres where userid = ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
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
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return result;
	}

	public FlightVO getFlightInfoBySearch(String num, String department, String arrive, String checkin) {
		String sql = "SELECT * FROM(SELECT f.*, ROW_NUMBER() over(ORDER BY f.REGIDATE  desc) AS row_num FROM flight2 f WHERE regidate =? and depairport=? and arrairport=?)where row_num=?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		FlightVO vo = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, checkin);
			ps.setString(2, department);
			ps.setString(3, arrive);
			ps.setInt(4, Integer.parseInt(num));
			rs = ps.executeQuery();
			while(rs.next()) {
				vo = new FlightVO();
				vo.setAirline(rs.getString("airline"));
				vo.setFlightnum(rs.getString("flightnum"));
				vo.setDepairport(rs.getString("depairport"));
				vo.setArrairport(rs.getString("arrairport"));
				vo.setRegidate(rs.getString("regidate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.getClose(rs, ps, con);
		}
		
		return vo;
	}

	
	
	
}
