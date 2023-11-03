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
import com.user.vo.BoardDiaryReplyVO;
import com.user.vo.BoardDiaryVO;
import com.user.vo.PageDTO;

import oracle.sql.CLOB;

public class BoardDiaryDAOImpl implements BoardDiaryDAO {

	@Override
	public void insertBoardDiary(BoardDiaryVO vo) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "insert into flightDiary(boardNum, boardTitle, boardContent,boardWriter,regidate) values(?,?,empty_clob(),?,sysdate)";
			//오라클 명령어 empty_clob()을 이용해 공간을 확보한다. 
		try{
			con = DBManager.getConnection();
			con.setAutoCommit(false);
			//CLOB column을 업데이트 하는동안 다른 process의 접근을 막기위해 setAutoCommit(false)를 반드시 설정해야 한다. 이부분이 가장 중요하다. 
			ps = con.prepareStatement(query);
			ps.setInt(1, vo.getBoardNum());
			ps.setString(2, vo.getBoardTitle());
			ps.setString(3,vo.getBoardWriter());

			ps.executeUpdate();
			ps.close();			
			
			String query2 = " select boardContent from flightDiary where boardNum = ? for update ";	
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
	public int maxBoardNum() {
		int num = 0;
		String sql = "select nvl(max(boardNum),0) from flightDiary";
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
	public void updateBoard(BoardDiaryVO vo) throws Exception {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "update flightDiary set boardTitle=?, boardContent=empty_clob(), modifyDate=sysdate where boardNum = ?";	//empty_clob()

		try{
			con = DBManager.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(query);
			ps.setString(1, vo.getBoardTitle());
			ps.setInt(2, vo.getBoardNum());

			ps.executeUpdate();
			ps.close();			
			
			String query2 = " select boardContent from flightDiary where boardNum = ? for update ";	
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
	public BoardDiaryVO selectOneByBoardNum(int boardNum) {
		BoardDiaryVO vo = null;
		
		String sql = "select * from flightDiary where boardNum=?";
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
				vo = new BoardDiaryVO();
				vo.setBoardNum(rs.getInt("boardNum"));
				vo.setBoardTitle(rs.getString("boardTitle"));
				vo.setBoardWriter(rs.getString("boardWriter"));
				vo.setRegiDate(rs.getString("regidate"));
				vo.setModifyDate(rs.getString("modifydate"));
				vo.setReadcount(rs.getInt("readcount"));
				
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

		String sql = "update flightDiary set readcount = readcount+1 where boardNum=?";
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
		
		String sql = "delete from flightDiary where boardNum=?";
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
	public List<BoardDiaryVO> searchByType(int startNum, int endNum, String searchType, String keyword, String sort) {

		String[] strsort = {"latest", "oldest", "viewcount", "replycount", "likecount"};
		String[] sqlsort = {"boardNum desc", "boardNum asc", "readcount desc, boardNum desc", "replycount desc, boardNum desc", "likecount desc, boardNum desc"};
		
		for(int i=0;i<strsort.length;i++) {
			if(sort.equals(strsort[i])) {
				sort=sqlsort[i];
			}
		}
		
		String sql = "select B.boardNum, boardTitle, boardWriter, to_char(regidate, 'yyyy-mm-dd hh24:mi:ss') regidate, to_char(modifydate, 'yyyy-mm-dd hh24:mi:ss') modifydate, readcount, replycount, likecount "
				+ "        from(select rownum rn, A.* from(select * from flightDiary where "+ searchType +" like '%" + keyword + "%' "
				+ "		order by "+ sort +" )A)B where rn between ? and ?";
		
		List<BoardDiaryVO> list = new ArrayList<BoardDiaryVO>();
		BoardDiaryVO vo = null;
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
				vo = new BoardDiaryVO();
				vo.setBoardNum(rs.getInt("boardNum"));
				vo.setBoardTitle(rs.getString("boardTitle"));
				vo.setBoardWriter(rs.getString("boardWriter"));
				vo.setRegiDate(rs.getString("regidate"));
				vo.setModifyDate(rs.getString("modifydate"));
				vo.setReadcount(rs.getInt("readcount"));
				vo.setReplycount(rs.getInt("replycount"));
				vo.setLikecount(rs.getInt("likecount"));

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
		String sql = "select count(*) from flightDiary where "+ searchType +" like '%"+ keyword +"%'";
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
	public int maxReplyNum(int boardNum) {
		int num = 0;
		String sql = "select nvl(max(replyNum),0) from flightDiaryReply where boardNum=" + boardNum;
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
	public void insertReply(BoardDiaryReplyVO vo) {
		
		String sql = "insert into flightDiaryReply(replyNum, boardNum, replyWriter, replyContent, regiDate) values(?,?,?,?,sysdate)";
		DBManager dbManager = new DBManager();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getReplyNum());
			ps.setInt(2, vo.getBoardNum());
			ps.setString(3, vo.getReplyWriter());
			ps.setString(4, vo.getReplyContent());
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		
	}

	@Override
	public int replyCount(int boardNum) {
		String sql = "select count(*) from flightDiaryReply where boardnum=?";
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
	public List<BoardDiaryReplyVO> selectAllReply(int startNum, int endNum, int boardNum) {
		List<BoardDiaryReplyVO> list = new ArrayList<BoardDiaryReplyVO>();
		BoardDiaryReplyVO vo = null;
		//String sql = "select * from flightDiaryReply where boardNum=? order by replyNum desc";
		//String sql = "select * from (select rownum rnum, imsi.* from (select * from flightDiaryReply order by replyNum desc) imsi) where boardNum=? and rnum>=? and rnum<=?";
		String sql = "select B.* from(select rownum rn, A.* from(select * from flightDiaryReply where boardNum=? "
				+ "		order by replyNum desc)A)B where rn between ? and ?";
		DBManager dbManager = new DBManager();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			ps.setInt(2, startNum);
			ps.setInt(3, endNum);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				vo = new BoardDiaryReplyVO();
				vo.setReplyNum(rs.getInt("replyNum"));
				vo.setReplyWriter(rs.getString("replyWriter"));
				vo.setReplyContent(rs.getString("replyContent"));
				vo.setRegiDate(rs.getString("regiDate"));
				vo.setModifyDate(rs.getString("modifyDate"));
				
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
	public void updateReply(BoardDiaryReplyVO vo) {
		String sql = "update flightDiaryReply set replyContent=?, modifyDate = sysdate where replyNum=? and boardNum=?";
		DBManager dbManager = new DBManager();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getReplyContent());
			ps.setInt(2, vo.getReplyNum());
			ps.setInt(3, vo.getBoardNum());
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
	}

	@Override
	public void deleteReply(int replyNum, int boardNum) {
		String sql = "delete from flightDiaryReply where replyNum=? and boardNum=?";
		DBManager dbManager = new DBManager();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, replyNum);
			ps.setInt(2, boardNum);
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
	}

	@Override
	public BoardDiaryReplyVO selectOneReply(int replyNum, int boardNum) {
		BoardDiaryReplyVO vo = new BoardDiaryReplyVO();
		
		String sql = "select * from flightDiaryReply where replyNum=? and boardNum=?";
		DBManager dbManager = new DBManager();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, replyNum);
			ps.setInt(2, boardNum);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				vo.setBoardNum(rs.getInt("boardNum"));
				vo.setReplyNum(rs.getInt("replyNum"));
				vo.setReplyContent(rs.getString("replyContent"));
				vo.setReplyWriter(rs.getString("replyWriter"));
				vo.setRegiDate(rs.getString("regiDate"));
	 		}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, con);
		}
		
		return vo;
	}

	@Override
	public void updateReplyCount(int boardNum) {
		String sql = "update flightDiary set replycount = (select count(replyNum) from flightDiaryReply where boardNum = ?) where boardNum = ?";
		
		DBManager dbManager = new DBManager();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			ps.setInt(2, boardNum);
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
	}

	@Override
	public int insertLikecount(int boardNum, String userId) {
		int result = 0;
		String sql = "insert into flightDiaryLike values(board_diary_like_seq.nextval, ?, ?, sysdate)";
		
		DBManager dbManager = new DBManager();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			ps.setString(2, userId);
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		
		return result;
	}

	@Override
	public int deleteLikecount(int boardNum, String userId) {
		int result = 0;
		String sql = "delete from flightDiaryLike where boardNum=? and userId=?";
		
		DBManager dbManager = new DBManager();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			ps.setString(2, userId);
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		
		return result;
	}

	@Override
	public int checkLike(int boardNum, String userId) {
		int result = 0;
		String sql = "select count(*) from flightDiaryLike where boardNum=? and userId=?";
		
		DBManager dbManager = new DBManager();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			ps.setString(2, userId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		
		return result;
	}

	@Override
	public int likecount(int boardNum) {
		String sql = "select count(*) from flightDiaryLike where boardnum=?";
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
	public int updateLikecount(int boardNum) {
		int result = 0;
		
		String sql = "update flightDiary set likecount = (select count(likeNum) from flightDiaryLike where boardNum = ?) where boardNum = ?";
		
		DBManager dbManager = new DBManager();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
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
