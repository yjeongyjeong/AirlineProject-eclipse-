package com.user.dao;

import java.io.CharArrayReader;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.user.util.DBManager;
import com.user.vo.BoardEventFileVO;
import com.user.vo.BoardEventVO;
import com.user.vo.PageDTO;

import oracle.sql.CLOB;

public class BoardEventDAOImpl implements BoardEventDAO{

	@Override
	public int insertBoardEvent(BoardEventVO vo) throws Exception{
		int result = -1;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "insert into boardEvent(boardNum, boardTitle, boardContent, startDate, endDate, regidate) values(?,?,empty_clob(),to_date(?, 'YY-MM-DD hh24:mi:ss'),to_date(?, 'YY-MM-DD hh24:mi:ss'),sysdate)";
			//오라클 명령어 empty_clob()을 이용해 공간을 확보한다. 
		try{
			con = DBManager.getConnection();
			con.setAutoCommit(false);
			//CLOB column을 업데이트 하는동안 다른 process의 접근을 막기위해 setAutoCommit(false)를 반드시 설정해야 한다. 이부분이 가장 중요하다. 
			ps = con.prepareStatement(query);
			ps.setInt(1, vo.getBoardNum());
			ps.setString(2, vo.getBoardTitle());
			ps.setString(3,vo.getStartDate());
			ps.setString(4,vo.getEndDate());

			result = ps.executeUpdate();
			ps.close();			
			
			String query2 = " select boardContent from boardEvent where boardNum = ? for update ";	
			//for update를 이용해 CLOB column을 lock한다. 
			ps = con.prepareStatement(query2);
			ps.setInt(1,vo.getBoardNum());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				CLOB clob = (oracle.sql.CLOB)rs.getClob(1); 
				Writer writer = clob.getCharacterOutputStream();
				Reader src = new CharArrayReader((vo.getBoardContent()).toCharArray());
				char[] buffer = new char[1024];
				int read = 0;
				
				while ( (read = src.read(buffer,0,1024)) != -1) {
						writer.write(buffer, 0, read); // write clob.
				}
				src.close(); 
				writer.close();
			}	
			con.commit();
			con.setAutoCommit(true);
			//CLOB column에 데이터을 저장하였다면 commit()을 실행시키고 con.setAutoCommit(true)로 다시 설정한다. 

			} finally{
			 DBManager.getClose(rs, ps, con);
		}
		return result;
	}
	
	@Override
	public int maxBoardNum() {
		int num = 0;
		String sql = "select nvl(max(boardNum),0) from boardEvent";
		DBManager dbManager = new DBManager();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, con);
		}

		return num;
	}
	
	@Override
	public int maxBoardNum2() {
		int num = 0;
		String sql = "select board_event_seq.nextval from boardEvent";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, con);
		}

		return num;
	}
	
	@Override
	public BoardEventVO selectOneByBoardNum(int boardNum) {
		BoardEventVO vo = null;
		
		String sql = "select * from boardEvent where boardNum=?";
		DBManager dbManager = new DBManager();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			rs = ps.executeQuery();

			if (rs.next()) {
				vo = new BoardEventVO();
				vo.setBoardNum(rs.getInt("boardNum"));
				vo.setBoardTitle(rs.getString("boardTitle"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setEndDate(rs.getString("endDate"));
				vo.setRegiDate(rs.getString("regidate"));
				vo.setModifyDate(rs.getString("modifydate"));
				vo.setReadCount(rs.getInt("readcount"));
				
				//vo.setBoardContent(rs.getString("boardContent"));
				StringBuffer output = new StringBuffer();
				Reader input = rs.getCharacterStream("boardContent");
				char[] buffer = new char[1024];
				int byteRead;
				while((byteRead=input.read(buffer,0,1024))!=-1){
					output.append(buffer,0,byteRead);
				}
				input.close();
				vo.setBoardContent(output.toString()); 

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, con);
		}

		return vo;
	}
	
	@Override
	public void updateReadcount(int boardNum) {

		String sql = "update boardEvent set readcount = readcount+1 where boardNum=?";
		DBManager dbManager = new DBManager();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		
	}
	
	@Override
	public int deleteBoard(int boardNum) {
		int result = -1;
		
		String sql = "delete from boardEvent where boardNum=?";
		DBManager dbManager = new DBManager();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		
		return result;
	}
	
	@Override
	public void updateBoard(BoardEventVO vo) throws Exception {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "update boardEvent set startDate=to_date(?, 'YY-MM-DD hh24:mi:ss'), endDate=to_date(?, 'YY-MM-DD hh24:mi:ss'), boardContent=empty_clob(), boardTitle=?, modifyDate=sysdate where boardNum = ?";	//empty_clob()
		try{
			con = DBManager.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(query);
			ps.setString(1, vo.getStartDate());
			ps.setString(2, vo.getEndDate());
			ps.setString(3, vo.getBoardTitle());
			ps.setInt(4, vo.getBoardNum());

			ps.executeUpdate();
			ps.close();			
			
			String query2 = " select boardContent from boardEvent where boardNum = ? for update ";	
			//for update를 이용해 CLOB column을 lock한다. 
			ps = con.prepareStatement(query2);
			ps.setInt(1,vo.getBoardNum());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				CLOB clob = (oracle.sql.CLOB)rs.getClob(1); 
				Writer writer = clob.getCharacterOutputStream();
				Reader src = new CharArrayReader((vo.getBoardContent()).toCharArray());
				char[] buffer = new char[1024];
				int read = 0;
				
				while ( (read = src.read(buffer,0,1024)) != -1) {
						writer.write(buffer, 0, read); // write clob.
				}
				src.close(); 
				writer.close();
			}	
			con.commit();
			con.setAutoCommit(true);
			//CLOB column에 데이터을 저장하였다면 commit()을 실행시키고 con.setAutoCommit(true)로 다시 설정한다. 

			} finally{
			 DBManager.getClose(rs, ps, con);
		}
		
	}
	
	@Override
	public List<BoardEventVO> searchByType(int startNum, int endNum, String searchType, String keyword) {
		
		String sql = "select B.boardNum, boardTitle, startDate, endDate, regidate, modifydate, readcount, repImg "
				+ "        from(select rownum rn, A.* from(select * from boardEvent where "+ searchType +" like '%" + keyword + "%' "
				+ "		order by endDate asc, boardNum desc )A)B where rn between ? and ?";
		
		List<BoardEventVO> list = new ArrayList<BoardEventVO>();
		BoardEventVO vo = null;
		DBManager dbManager = new DBManager();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, startNum);
			ps.setInt(2, endNum);
			rs = ps.executeQuery();

			while (rs.next()) {
				vo = new BoardEventVO();
				vo.setBoardNum(rs.getInt("boardNum"));
				vo.setBoardTitle(rs.getString("boardTitle"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setEndDate(rs.getString("endDate"));
				vo.setRegiDate(rs.getString("regidate"));
				vo.setModifyDate(rs.getString("modifydate"));
				vo.setReadCount(rs.getInt("readCount"));
				vo.setRepImg(rs.getString("repImg"));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, con);
		}

		return list;
	}
	
	@Override
	public PageDTO pageCalcu(HttpServletRequest request, int boardCount) {
		int page = request.getParameter("page") == null? 1: Integer.parseInt(request.getParameter("page"));
		int limit = request.getParameter("viewLimit") == null? 10: Integer.parseInt(request.getParameter("viewLimit"));;
		PageDTO paging = new PageDTO();
		paging.calcu(page, limit, boardCount);  
		return paging;
	}
	
	@Override
	public int searchCount(String searchType, String keyword) {
		String sql = "select count(*) from boardEvent where "+ searchType +" like '%"+ keyword +"%'";
		int result = 0;
		
		DBManager dbManager = new DBManager();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);		
			rs = ps.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, con);
		}

		return result;
	}

	@Override
	public int insertBoardEventFile(BoardEventFileVO vo) {
		
		int result = -1;
		String sql = "insert into boardEventFile values(board_event_file_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
		
		DBManager dbManager = new DBManager();

		Connection con = null;
		PreparedStatement ps = null;

		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);		

			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getBoardNum());
			ps.setString(2, vo.getOriFileName());
			ps.setString(3, vo.getSavedFileName());
			ps.setString(4, vo.getExtension());
			ps.setString(5, vo.getRepImgYn());
			ps.setInt(6, vo.getFileSize());
			ps.setString(7, vo.getFileOrder());
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		
		return result;
	}

	@Override
	public List<BoardEventFileVO> searchFileByBoardNum(int boardNum) {
		String sql = "select * from boardEventFile where boardNum=? order by repImgYn desc, fileOrder asc";
		List<BoardEventFileVO> list = new ArrayList<BoardEventFileVO>();
		BoardEventFileVO vo = null;
		DBManager dbManager = new DBManager();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			rs = ps.executeQuery();

			while (rs.next()) {
				vo = new BoardEventFileVO();
				vo.setBoardNum(rs.getInt("boardNum"));
				vo.setOriFileName(rs.getString("oriFileName"));
				vo.setSavedFileName(rs.getString("savedFileName"));
				vo.setExtension(rs.getString("extension"));
				vo.setRepImgYn(rs.getString("repImgYn"));
				vo.setFileSize(rs.getInt("fileSize"));
				vo.setFileOrder(rs.getString("fileOrder"));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, con);
		}

		return list;
	}

	@Override
	public int fileCount(int boardNum) {
		String sql = "select count(*) from boardEventFile where boardNum=?";
		int result = 0;
		
		DBManager dbManager = new DBManager();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			rs = ps.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, con);
		}

		return result;
	}

	@Override
	public int fileDelete(int boardNum) {
		int result = -1;
		
		String sql = "delete from boardEventFile where boardNum=?";
		DBManager dbManager = new DBManager();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		
		return result;
	}

	@Override
	public List<BoardEventFileVO> findRepImg() {
		String sql = "select * from boardEventFile where repImgYn='Y'";
		List<BoardEventFileVO> list = new ArrayList<BoardEventFileVO>();
		BoardEventFileVO vo = null;
		DBManager dbManager = new DBManager();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				vo = new BoardEventFileVO();
				vo.setBoardNum(rs.getInt("boardNum"));
				vo.setOriFileName(rs.getString("oriFileName"));
				vo.setSavedFileName(rs.getString("savedFileName"));
				vo.setExtension(rs.getString("extension"));
				vo.setRepImgYn(rs.getString("repImgYn"));
				vo.setFileSize(rs.getInt("fileSize"));
				vo.setFileOrder(rs.getString("fileOrder"));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, con);
		}

		return list;
	}

	@Override
	public int updateRepImg(String ori_file_name, int boardNum) {
		int result = 0;
		
		String sql = "update boardEvent set repImg = ? where boardNum = ?";
		
		DBManager dbManager = new DBManager();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, ori_file_name);
			ps.setInt(2, boardNum);
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		
		return result;
	}
	
}
