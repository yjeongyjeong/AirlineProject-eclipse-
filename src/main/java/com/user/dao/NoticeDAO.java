package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.util.DBManager;
import com.user.vo.NoticeFileVO;
import com.user.vo.NoticeVO;
import com.user.vo.UserVO;

public class NoticeDAO {
	private NoticeDAO() {}
	
	private static NoticeDAO instance = new NoticeDAO();
	
	public static NoticeDAO getInstance() {
		return instance;
	}
	
	/*
	 * //공지사항 게시물 개수 카운트 public int getNoticeCount() { String sql =
	 * "select count(boardnum) as count from notice"; Connection con = null;
	 * PreparedStatement ps = null; ResultSet rs = null; int result = -1;
	 * 
	 * try { con = DBManager.getConnection(); ps = con.prepareStatement(sql); rs =
	 * ps.executeQuery();
	 * 
	 * if(rs.next()) { result = rs.getInt("count"); } }catch(Exception e) {
	 * e.printStackTrace(); }finally { DBManager.getClose(rs, ps, con); } return
	 * result; }
	 * 
	 * //공지사항 리스트 가져오기 public List<NoticeVO> getNoticeList(int curPage, int onePage)
	 * { List<NoticeVO> list = new ArrayList<NoticeVO>(); String sql =
	 * "select * from(SELECT ROW_NUMBER() OVER(ORDER BY n.regidate desc) AS ROW_NUM, n.* ,k.admin FROM kakaouser k, notice n where k.userid = n.userid) userinfo where userinfo.ROW_NUM between ? and ?"
	 * ; Connection con = null; PreparedStatement ps = null; ResultSet rs = null;
	 * NoticeVO vo = null; int start = 1+(curPage-1)*onePage;// 1 6 11 int end =
	 * curPage*onePage;; //5 10 15
	 * 
	 * try { con = DBManager.getConnection(); ps = con.prepareStatement(sql);
	 * ps.setInt(1, start); ps.setInt(2, end); rs = ps.executeQuery();
	 * 
	 * while(rs.next()) { vo = new NoticeVO(); vo.setUserid(rs.getString("userid"));
	 * vo.setBoardnum(rs.getInt("boardnum"));
	 * vo.setBoardsubject(rs.getString("boardsubject"));
	 * vo.setBoardcontent(rs.getString("boardcontent"));
	 * vo.setRegidate(rs.getString("regidate"));
	 * vo.setModifydate(rs.getString("modifydate"));
	 * vo.setViews(rs.getInt("views")); list.add(vo); } }catch(Exception e) {
	 * e.printStackTrace(); }finally { DBManager.getClose(rs, ps, con); }
	 * 
	 * return list; }
	 * 
	 * //상세페이지 조회 public NoticeVO getNoticeDetail(int boardnum) { String sql =
	 * "select * from notice where boardnum = ?"; Connection con = null;
	 * PreparedStatement ps = null; ResultSet rs = null; NoticeVO vo = null;
	 * 
	 * try { con = DBManager.getConnection(); ps = con.prepareStatement(sql);
	 * ps.setInt(1, boardnum); rs = ps.executeQuery();
	 * 
	 * if(rs.next()) { vo = new NoticeVO(); vo.setUserid(rs.getString("userid"));
	 * vo.setBoardnum(rs.getInt("boardnum"));
	 * vo.setBoardsubject(rs.getString("boardsubject"));
	 * vo.setBoardcontent(rs.getString("boardcontent"));
	 * vo.setRegidate(rs.getString("regidate"));
	 * vo.setModifydate(rs.getString("modifydate"));
	 * vo.setViews(rs.getInt("views")); } }catch(Exception e) { e.printStackTrace();
	 * }finally { DBManager.getClose(rs, ps, con); } return vo; }
	 * 
	 * //조회수 카운트 public void countViews(int originView, int boardnum) { String sql =
	 * "update notice set views = ? where boardnum = ?"; Connection con = null;
	 * PreparedStatement ps = null; ResultSet rs = null;
	 * 
	 * try { con = DBManager.getConnection(); ps = con.prepareStatement(sql);
	 * ps.setInt(1, originView+1); ps.setInt(2, boardnum); rs = ps.executeQuery();
	 * 
	 * }catch(Exception e) { e.printStackTrace(); }finally { DBManager.getClose(rs,
	 * ps, con); } }
	 * 
	 * //공지사항 글쓰기 _내용 => 결과값(vo)를 바로 가져다 쓰기 위해 vo 타입으로 반환 public int
	 * insertNotice(NoticeVO vo) { String sql =
	 * "insert into notice(boardnum,boardsubject,boardcontent,regidate,modifydate, userid, views"
	 * + " values(boardnum_seq.nextval, ?,?,sysdate,sysdate,?,0,?,?,?,?)";
	 * Connection con = null; PreparedStatement ps = null; int result = -1; //
	 * NoticeVO vo = null;
	 * 
	 * try { con = DBManager.getConnection(); ps = con.prepareStatement(sql);
	 * ps.setString(1, vo.getBoardsubject()); ps.setString(2, vo.getBoardcontent());
	 * ps.setString(3, vo.getUserid()); result = ps.executeUpdate();
	 * 
	 * }catch(Exception e) { e.printStackTrace(); }finally { DBManager.getClose(ps,
	 * con); }
	 * 
	 * return result; }
	 * 
	 * //공지사항 글쓰기_이미지 업로드 public int insertNoticePicture(NoticeVO vo, int boardnum)
	 * { String sql =
	 * "insert into notice(boardnum, origin,extension, fileurl) values("+boardnum+
	 * ",?,?,?)"; Connection con = null; PreparedStatement ps = null; int result =
	 * -1; // NoticeVO vo = null;
	 * 
	 * try { con = DBManager.getConnection(); ps = con.prepareStatement(sql);
	 * ps.setString(1, vo.getOrigin()); ps.setString(2, vo.getExtention());
	 * ps.setString(3, vo.getFileUrl()); result = ps.executeUpdate();
	 * 
	 * }catch(Exception e) { e.printStackTrace(); }finally { DBManager.getClose(ps,
	 * con); }
	 * 
	 * return result; }
	 * 
	 * public int getBoardnum() { String sql =
	 * "select BOARDNUM_SEQ.currval as seq FROM dual"; Connection con = null;
	 * PreparedStatement ps = null; ResultSet rs = null; int result = -1;
	 * 
	 * try { con = DBManager.getConnection(); ps = con.prepareStatement(sql); rs =
	 * ps.executeQuery();
	 * 
	 * if(rs.next()) { result = rs.getInt("seq"); } }catch(Exception e) {
	 * e.printStackTrace(); }finally { DBManager.getClose(rs, ps, con); } return
	 * result; }
	 * 
	 * //이미지 가져오기 public NoticeVO getImages(int boardnum) { String sql =
	 * "select * from notice where boardnum = ?"; Connection con = null;
	 * PreparedStatement ps = null; ResultSet rs = null; NoticeVO vo = null;
	 * 
	 * try { con = DBManager.getConnection(); ps = con.prepareStatement(sql);
	 * ps.setInt(1, boardnum); rs = ps.executeQuery();
	 * 
	 * if(rs.next()) { vo = new NoticeVO(); vo.setBoardnum(rs.getInt("boardnum"));
	 * vo.setOrigin(rs.getString("origin"));
	 * vo.setExtention(rs.getString("extension"));
	 * vo.setFileUrl(rs.getString("fileUrl")); } }catch(Exception e) {
	 * e.printStackTrace(); }finally { DBManager.getClose(rs, ps, con); } return vo;
	 * }
	 * 
	 * //공지사항 수정 public int updateNotice(NoticeVO vo) { String sql =
	 * "update notice set boardsubject=?, boardcontent=? where boardnum=?";
	 * Connection con = null; PreparedStatement ps = null; int result = -1; //
	 * NoticeVO vo = null;
	 * 
	 * try { con = DBManager.getConnection(); ps = con.prepareStatement(sql);
	 * ps.setString(1, vo.getBoardsubject()); ps.setString(2, vo.getBoardcontent());
	 * ps.setInt(3, vo.getBoardnum()); result = ps.executeUpdate();
	 * 
	 * }catch(Exception e) { e.printStackTrace(); }finally { DBManager.getClose(ps,
	 * con); }
	 * 
	 * return result; }
	 * 
	 * //공지사항 그림 수정 public int updateNoticePicture(NoticeVO vo, int boardnum) {
	 * String sql =
	 * "update notice set origin=?, extension=?, fileUrl=? where boardnum=?";
	 * Connection con = null; PreparedStatement ps = null; int result = -1;
	 * 
	 * try { con = DBManager.getConnection(); ps = con.prepareStatement(sql);
	 * ps.setString(1, vo.getOrigin()); ps.setString(2, vo.getExtention());
	 * ps.setString(3, vo.getFileUrl()); ps.setInt(4, boardnum); result =
	 * ps.executeUpdate();
	 * 
	 * }catch(Exception e) { e.printStackTrace(); }finally { DBManager.getClose(ps,
	 * con); } return result; }
	 * 
	 * //게시글 삭제 public int deleteNotice(int boardnum) { String sql =
	 * "delete from notice where boardnum=?"; Connection con = null;
	 * PreparedStatement ps = null; int result = -1;
	 * 
	 * try { con = DBManager.getConnection(); ps = con.prepareStatement(sql);
	 * ps.setInt(1, boardnum); result = ps.executeUpdate();
	 * 
	 * 
	 * }catch(Exception e) { e.printStackTrace(); }finally { DBManager.getClose(ps,
	 * con); }
	 * 
	 * return result; }
	 */
	
	//--------------------------------------여기서 부터 추가된 부분
	public List<NoticeVO> getList(int pageNum, int amount) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<NoticeVO> list = new ArrayList<>();

		String sql = "select * from (select rownum rn, a.* from (select *"
				+ " from boardnotice order by boardnum desc) a ) where rn > ? and rn <= ?";

		try {
			con = DBManager.getConnection();// 연결

			ps = con.prepareStatement(sql); // sql준비
			ps.setInt(1, (pageNum - 1) * amount);
			ps.setInt(2, pageNum * amount);

			rs = ps.executeQuery(); // sql문 실행

			while (rs.next()) {
				// 한바퀴 회전당 VO를 하나씩 생성
				NoticeVO vo = new NoticeVO();

				vo.setBoardnum(rs.getInt("boardnum"));
				vo.setBoardsubject(rs.getString("boardsubject"));
				vo.setBoardcontent(rs.getString("boardcontent"));
				vo.setRegidate(rs.getString("regidate"));
				vo.setModifydate(rs.getString("modifydate"));
				vo.setBoardwriter(rs.getString("boardwriter"));;

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, con);
		}

		return list;
	}
	
	
	public int getTotal() {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select count(*) as total from boardnotice";

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				result = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, con);
		}
		return result;
	}
	
	public UserVO getMember(String userid) {
		UserVO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from kakaouser where userid = ?";

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid); // sql문의 ? 에 입력 될 내용
			rs = ps.executeQuery(); // 실행 결과값
			if (rs.next()) { // 실행 결과값이 없을떄 까지 반복
				vo = new UserVO(); // 데이터를 저장할 공간인 vo객체 생성해줘야함
				vo.setUsername(rs.getString("userid"));
				vo.setPass(rs.getString("pwd"));
				vo.setNickname(rs.getString("korName"));
				vo.setEmail(rs.getString("email"));
				vo.setAdmin(rs.getInt("admin"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, con);
		}
		return vo;
	}
	
	public int userCheck(String userid, String pwd) {
		int result = 0; // 1,0,-1중 아무거나 하나 넣으면 됨
		String sql = "select pwd from kakaouser where userid =?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();

			if (rs.next()) {
				if (rs.getString("pwd").equals(pwd)) {
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(rs, ps, con);
		}
		return result;
	}
	
	public int writeNotice(NoticeVO vo) {
		int result = 0;
		String sql = "insert into boardnotice(boardnum,boardsubject,"
				+ "boardcontent,regidate,boardwriter)"
				+ " values(notice_seq.nextval,?,?,sysdate,?)";

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getBoardsubject());
			ps.setString(2, vo.getBoardcontent());
			ps.setString(3, vo.getBoardwriter());
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		return result;
	}
	
	
	public NoticeVO getOneNotice(int boardnum){
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs =null;
		String sql = "select * from boardnotice where boardnum=?";
		NoticeVO vo = null;
		try {
			con = DBManager.getConnection();
			ps= con.prepareStatement(sql);
			ps.setInt(1, boardnum);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				vo = new NoticeVO();
				vo.setBoardsubject(rs.getString("boardsubject"));
				vo.setBoardcontent(rs.getString("boardcontent"));
				vo.setBoardnum(rs.getInt("boardnum"));
				vo.setBoardwriter(rs.getString("boardwriter"));
				vo.setRegidate(rs.getString("regidate"));
				vo.setModifydate(rs.getString("modifydate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(rs, ps, con);
		}
		return vo;
	}

	public NoticeFileVO getOneNoticeFile(int boardnum){
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs =null;
		String sql = "select * from filenotice where boardnum=?";
		NoticeFileVO vo = null;
		try {
			con = DBManager.getConnection();
			ps= con.prepareStatement(sql);
			ps.setInt(1, boardnum);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				vo = new NoticeFileVO();
				vo.setFileurl(rs.getString("fileurl"));
				vo.setExtension(rs.getString("extension"));
				vo.setOrigin(rs.getString("origin"));
				vo.setUuid(rs.getString("uuid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(rs, ps, con);
		}
		return vo;
	}
	
	public int deleteNoticeBoard(int boardnum) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql ="DELETE FROM boardnotice WHERE BOARDNUM = ?";
				
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardnum);
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(ps, con);
		}
		return result;
	}
	
	public void deleteNoticeFile(int boardnum) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql ="DELETE FROM filenotice WHERE BOARDNUM = ?";
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardnum);
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(ps, con);
		}
	}
	public int updateNoticeBoard(NoticeVO vo) {
		int result = 0;
		String sql = "update boardnotice set boardnum=boardnum,"
				+ "boardsubject=boardsubject, boardcontent=?, modifydate=sysdate,"
				+ "boardwriter=boardwriter where boardnum=?";
		Connection con=null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getBoardcontent());
			ps.setInt(2, vo.getBoardnum());
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(ps, con);
		}
		return result;
	}

	public List<NoticeVO> searchNotice(String boardsubject) {
		String sql = "SELECT * FROM boardnotice where boardsubject like '%' || ? || '%'";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		NoticeVO vo = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, boardsubject);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				vo = new NoticeVO();
				vo.setBoardnum(rs.getInt("boardnum"));
				vo.setBoardsubject(rs.getString("boardsubject"));
				vo.setBoardcontent(rs.getString("boardcontent"));
				vo.setBoardwriter(rs.getString("boardwriter"));
				vo.setRegidate(rs.getString("regidate"));
				vo.setModifydate(rs.getString("modifydate"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(rs, ps, con);
		}
		return list;
		
	}
	
	//filevo, noticevo 합친것(아직안씀)
	public int fileWriteNotice(NoticeVO vo, NoticeFileVO fvo) {
		int result = 0;
		String sql = "insert all "
				+ "into boardnotice(boardnum,boardsubject,"
				+ "boardcontent,regidate,boardwriter)"
				+ "values(notice_seq.nextval,?,?,sysdate,?)"
				+ "into filenotice(boardnum,origin,uuid,"
				+ "fileurl)"
				+ "values(notice_seq.nextval,?,"
				+ "?,?) select * from dual";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getBoardsubject());
			ps.setString(2, vo.getBoardcontent());
			ps.setString(3, vo.getBoardwriter());
			ps.setString(4, fvo.getOrigin());
			ps.setString(5, fvo.getUuid());
			ps.setString(6, fvo.getFileurl());
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getClose(ps, con);
		}
		return result;
	}
	
	public NoticeFileVO selectFileNotice(int boardnum) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from filenotice where boardnum=?";
		NoticeFileVO vo = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardnum);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				vo = new NoticeFileVO();
				vo.setBoardnum(boardnum);
				vo.setOrigin(rs.getString("origin"));
				vo.setUuid(rs.getString("uuid"));
				vo.setExtension(rs.getString("extension"));
				vo.setFileurl(rs.getString("fileurl"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(rs, ps, con);
		}
		return vo;
	}
	
	public int updateNoticeFile(NoticeFileVO fvo) {
		int result= 0;
		String sql = "update filenotice set "
				+ "fileurl=? ,uuid=?, extension=?,"
				+ "origin=? where boardnum=?";
		Connection con=null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, fvo.getFileurl());
			ps.setString(2, fvo.getUuid());
			ps.setString(3, fvo.getExtension());
			ps.setString(4, fvo.getOrigin());
			ps.setInt(5, fvo.getBoardnum());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.getClose(ps, con);
		}
		return result;
	}	


	
	
}
