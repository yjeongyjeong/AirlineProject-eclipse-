package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.user.vo.QnaVO;
import com.user.vo.UserVO;

public class QnaDAO {

	private static QnaDAO instance = new QnaDAO();

	private QnaDAO() {

	}

	public static QnaDAO getInstance() {
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

	public List<QnaVO> selectAllQnaList() {
		List<QnaVO> list = new ArrayList<QnaVO>();
		String sql = "SELECT * FROM qnaboard order by boardreref desc,boardreseq asc  , boardrelevel asc , boardnum desc";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		QnaVO vo = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				vo = new QnaVO();
				vo.setBoardnum(rs.getInt("boardnum"));
				vo.setBoardsubject(rs.getString("boardsubject"));
				vo.setBoardcontent(rs.getString("boardcontent"));
				vo.setBoardwriter(rs.getString("boardwriter"));
				vo.setRegidate(rs.getString("regidate"));
				vo.setModifydate(rs.getString("modifydate"));
				vo.setBoardreref(rs.getInt("boardreref"));
				vo.setBoardrelevel(rs.getInt("boardrelevel"));
				vo.setBoardreseq(rs.getInt("boardreseq"));
				vo.setReadcount(rs.getInt("readcount"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return list;
	}

	// qnaboard에 있는 데이터들의 모든 정보를 boardnum의 내림차순으로 조회 정렬
//	public List<QnaVO> selectAllQnaList() {
//		List<QnaVO> list = new ArrayList<QnaVO>();
//		String sql = "select * from qnaboard order by boardnum desc";
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		QnaVO vo = null;
//		try {
//			conn = getConnection();
//			ps = conn.prepareStatement(sql);
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				vo = new QnaVO();
//				vo.setBoardnum(rs.getInt("boardnum"));
//				vo.setBoardsubject(rs.getString("boardsubject"));
//				vo.setBoardcontent(rs.getString("boardcontent"));
//				vo.setBoardwriter(rs.getString("boardwriter"));
//				vo.setRegidate(rs.getString("regidate"));
//				vo.setModifydate(rs.getString("modifydate"));
//				vo.setBoardreref(rs.getInt("boardreref"));
//				vo.setBoardrelevel(rs.getInt("boardrelevel"));
//				vo.setBoardreseq(rs.getInt("boardreseq"));
//				vo.setReadcount(rs.getInt("readcount"));
//				list.add(vo);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(conn, ps, rs);
//		}
//		return list;
//	}

	// boardnum로 boardnum만 조회
	public int getSelectBoardNum() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select boardnum from qnaboard where boardnum=?";
		int result = 0;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				ps.setInt(1, Integer.parseInt("boardnum"));
			}
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	// 글작성(글번호(자동+1), 제목, 내용, 작성자, 작성일자(지금시간), 조회수(0))
	public int writeQna(QnaVO vo) {
		int result = 0;
		String sql = "insert into qnaboard(boardnum,boardsubject,"
				+ "boardcontent,boardwriter,regidate,boardrelevel,boardreseq,boardreref,readcount)"
				+ "values(qnaboard_seq.nextval,?,?,?,sysdate,?,?,qnaboard_seq.nextval,0)";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getBoardsubject());
			ps.setString(2, vo.getBoardcontent());
			ps.setString(3, vo.getBoardwriter());
			ps.setInt(4, vo.getBoardrelevel());
			ps.setInt(5, vo.getBoardreseq());

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		return result;
	}

	public QnaVO selectOneQnaList(int boardnum) {
		String sql = "select * from qnaboard where boardnum=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		QnaVO vo = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardnum);
			rs = ps.executeQuery();

			while (rs.next()) {
				vo = new QnaVO();
				vo.setBoardsubject(rs.getString("boardsubject"));
				vo.setBoardcontent(rs.getString("boardcontent"));
				vo.setBoardwriter(rs.getString("boardwriter"));
				vo.setBoardnum(rs.getInt("boardnum"));
				vo.setReadcount(rs.getInt("readcount"));
			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return vo;
	}

	public int updateQna(QnaVO vo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update qnaboard set boardsubject=?, boardcontent=?, boardwriter=? ,modifydate=sysdate where boardnum=?";

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, vo.getBoardsubject());
			ps.setString(2, vo.getBoardcontent());
			ps.setString(3, vo.getBoardwriter());
			ps.setInt(4, vo.getBoardnum());

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		return result;
	}

	public int deleteQna(int boardnum) {
		String sql = "delete from qnaboard where boardnum=?";
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardnum);

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		return result;
	}

	public int insertReplyQna(QnaVO vo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into qnaboard (boardnum, boardsubject,boardcontent, boardwriter, boardreref, boardrelevel, boardreseq) "
				+ "values (qnaboard_seq.nextval, ?,?, ?, ?, ?, ?)";

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);

			// "Re"를 추가한 제목을 생성
			String replySubject = "Re: " + vo.getBoardsubject();
			ps.setString(1, replySubject);
			ps.setString(2, vo.getBoardcontent());
			ps.setString(3, vo.getBoardwriter());
			ps.setInt(4, vo.getBoardreref());
			ps.setInt(5, vo.getBoardrelevel());
			ps.setInt(6, vo.getBoardreseq() + 1);

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		return result;
	}

	public int getParentRelevel(int parentBoardNum) {
		// 부모 게시글의 boardnum
		QnaVO vo = null;
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 부모 게시글의 boardrelevel 및 boardreseq 조회 쿼리 작성
			String sql = "SELECT boardrelevel FROM qnaboard WHERE boardnum = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, parentBoardNum); // 부모 게시글의 boardnum 설정

			rs = ps.executeQuery();

			if (rs.next()) {
				// 결과에서 boardrelevel 및 boardreseq 값을 가져옴

				int parentRelevel = rs.getInt("boardrelevel");

				// parentRelevel 및 parentReseq를 사용하여 답변 계층 정보 계산 가능
				result = parentRelevel;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		return result;
	}

	public int getParentReseq(int parentBoardNum) {
		// 부모 게시글의 boardnum
		QnaVO vo = null;
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 부모 게시글의 boardrelevel 및 boardreseq 조회 쿼리 작성
			String sql = "SELECT boardreseq FROM qnaboard WHERE boardnum = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, parentBoardNum); // 부모 게시글의 boardnum 설정

			rs = ps.executeQuery();

			if (rs.next()) {
				// 결과에서 boardrelevel 및 boardreseq 값을 가져옴

				int parentReseq = rs.getInt("boardreseq");

				// parentRelevel 및 parentReseq를 사용하여 답변 계층 정보 계산 가능
				result = parentReseq;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		return result;
	}

	public int getParentBoardNum(int boardnum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int parentBoardNumToReference = boardnum; // 기본적으로 boardnum 반환

		try {
			conn = getConnection();
			String sql = "SELECT boardreref, boardrelevel FROM qnaboard WHERE boardnum = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardnum);
			rs = ps.executeQuery();

			if (rs.next()) {
				int boardreref = rs.getInt("boardreref");
				int boardrelevel = rs.getInt("boardrelevel");

				// 부모 게시글이 답글인 경우에는 boardreref를 반환
				if (boardrelevel > 0) {
					parentBoardNumToReference = boardreref;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return parentBoardNumToReference;
	}

	public void updateReadCount(int boardnum) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update qnaboard set readcount = readcount+1 where boardnum =?";

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardnum);

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
	}

	public List<QnaVO> searchQnaList(String boardsubject) {
		List<QnaVO> list = new ArrayList<QnaVO>();
		String sql = "SELECT * FROM qnaboard where boardsubject like '%' || ? || '%' order by boardreref desc,boardreseq asc  , boardrelevel asc , boardnum desc";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		QnaVO vo = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, boardsubject);
			rs = ps.executeQuery();

			while (rs.next()) {
				vo = new QnaVO();
				vo.setBoardnum(rs.getInt("boardnum"));
				vo.setBoardsubject(rs.getString("boardsubject"));
				vo.setBoardcontent(rs.getString("boardcontent"));
				vo.setBoardwriter(rs.getString("boardwriter"));
				vo.setRegidate(rs.getString("regidate"));
				vo.setModifydate(rs.getString("modifydate"));
				vo.setBoardreref(rs.getInt("boardreref"));
				vo.setBoardrelevel(rs.getInt("boardrelevel"));
				vo.setBoardreseq(rs.getInt("boardreseq"));
				vo.setReadcount(rs.getInt("readcount"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return list;
	}

	public int userCheck(String username, String pass) {
		int result = 0; // 1,0,-1중 아무거나 하나 넣으면 됨
		String sql = "select pass from testuser where username =?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {
				if (rs.getString("pass").equals(pass)) {
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return result;
	}

	public UserVO getMember(String username) {
		UserVO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from testuser where username = ?";

		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, username); // sql문의 ? 에 입력 될 내용
			rs = ps.executeQuery(); // 실행 결과값
			if (rs.next()) { // 실행 결과값이 없을떄 까지 반복
				vo = new UserVO(); // 데이터를 저장할 공간인 vo객체 생성해줘야함
				vo.setUsername(rs.getString("username"));
				vo.setPass(rs.getString("pass"));
				vo.setNickname(rs.getString("nickname"));
				vo.setEmail(rs.getString("email"));
				vo.setAdmin(rs.getInt("admin"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return vo;
	}

	public int getTotal() {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select count(*) as total from qnaboard";

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				result = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	public List<QnaVO> getList(int pageNum, int amount) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<QnaVO> list = new ArrayList<>();

		String sql = "select * " + "from (select rownum rn," + " a.* " + "from (select *"
				+ " from qnaboard order by boardreref desc,boardreseq asc  , boardrelevel asc , boardnum desc) a ) " + "where rn > ? and rn <= ?";

		try {
			conn = getConnection(); // 연결

			ps = conn.prepareStatement(sql); // sql준비
			ps.setInt(1, (pageNum - 1) * amount);
			ps.setInt(2, pageNum * amount);

			rs = ps.executeQuery(); // sql문 실행

			while (rs.next()) {
				// 한바퀴 회전당 VO를 하나씩 생성
				QnaVO vo = new QnaVO();

				vo.setBoardnum(rs.getInt("boardnum"));
				vo.setBoardsubject(rs.getString("boardsubject"));
				vo.setBoardcontent(rs.getString("boardcontent"));
				vo.setBoardwriter(rs.getString("boardwriter"));
				vo.setRegidate(rs.getString("regidate"));
				vo.setModifydate(rs.getString("modifydate"));
				vo.setBoardreref(rs.getInt("boardreref"));
				vo.setBoardrelevel(rs.getInt("boardrelevel"));
				vo.setBoardreseq(rs.getInt("boardreseq"));
				vo.setReadcount(rs.getInt("readcount"));

				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return list;
	}

}
